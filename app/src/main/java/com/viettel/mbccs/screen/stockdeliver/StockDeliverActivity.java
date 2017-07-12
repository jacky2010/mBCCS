package com.viettel.mbccs.screen.stockdeliver;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.constance.RoleWareHouse;
import com.viettel.mbccs.constance.StockTransStatus;
import com.viettel.mbccs.constance.StockTransType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListExpCmdRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListExpCmdResponse;
import com.viettel.mbccs.screen.importwarehousefromstaff.importnote.CreateImportStockFromStaffActivity;
import com.viettel.mbccs.screen.importwarehousefromstaff.importnote.CreateNoteFromStaffActivity;
import com.viettel.mbccs.screen.warehousecommon.exportsuccess.ExportSuccessDialog;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by buidinhviet on 6/13/17.
 */

public class StockDeliverActivity extends BaseListOrderActivity {
    List<String> funtions = new ArrayList<>();

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void doSearch() {

        DataRequest<GetListExpCmdRequest> mDataRequest = new DataRequest<>();
        GetListExpCmdRequest mRequest = new GetListExpCmdRequest();
        showLoading();
        mRequest.setStockTransType(StockTransType.TRANS_EXPORT);
        if (getPositionStatus() == 0) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_NON_NOTE);
        }

        if (getPositionStatus() == 1) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_NOTED);
        }

        if (getPositionStatus() == 2) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_DONE);
        }

        if (getPositionStatus() == 3) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_EXP_IMPED);
        }

        if (getPositionStatus() == 4) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_CANCEL);
        }

        if (getPositionStatus() == 5) {
            //TODO: will confirm after
            mRequest.setStockTransType(StockTransType.TRANS_EXPORT);
            mRequest.setStockTransStatus(StockTransStatus.TRANS_REJECT);
        }

        mRequest.setStartDate(getFromDateString());
        mRequest.setEndDate(getToDateString());
        mRequest.setFromOwnerId(loadShopId());
        mRequest.setFromOwnerType(OwnerType.SHOP);
        mRequest.setToOwnerId(mUserRepository.getUserInfo().getShop().getShopId());
        mRequest.setToOwnerType(OwnerType.SHOP);
        mDataRequest.setWsCode(WsCode.GetListExpCmd);
        mDataRequest.setWsRequest(mRequest);
        mBanHangKhoTaiChinhRepository.getListExpCmd(mDataRequest)
                .subscribe(new MBCCSSubscribe<GetListExpCmdResponse>() {
                    @Override
                    public void onSuccess(GetListExpCmdResponse object) {

                        if (object != null && object.getStockTranses() != null) {
                            for (StockTrans stockTrans : object.getStockTranses()) {
                                setActionName3Step(stockTrans);
                            }
                            setDataSearch(object.getStockTranses());
                        } else {
                            setDataSearch(new ArrayList<StockTrans>());
                        }
                        onSearchSuccess();
                    }

                    @Override
                    public void onError(BaseException error) {
                        showErrorDialog(error);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        hideLoadingDialog();
                    }
                });
    }

    private void setActionName3Step(StockTrans stockTrans) {
        switch ((int) stockTrans.getStockTransStatus()) {
            case (int) StockTransStatus.TRANS_NON_NOTE:
                if (funtions.contains(RoleWareHouse.LAP_PHIEU_XUAT)) {
                    stockTrans.setActionName(
                            getString(R.string.commmon_warehouse_action_create_note));
                }

                break;
            case (int) StockTransStatus.TRANS_NOTED:
                if (funtions.contains(RoleWareHouse.XUAT_KHO)) {
                    stockTrans.setActionName(getString(R.string.common_label_export));
                }
                break;
            default:
                stockTrans.setActionName(getString(R.string.nv_trahangcaptren_action_detail));
                break;
        }
    }

    @Override
    public void onItemStockTransClick(StockTrans stockTrans) {
        Intent intent = null;
        switch ((int) stockTrans.getStockTransStatus()) {
            case (int) StockTransStatus.TRANS_NON_NOTE:
                if (funtions.contains(RoleWareHouse.LAP_PHIEU_XUAT)) {
                    intent = new Intent(this, CreateNoteFromStaffActivity.class);
                }

                break;
            case (int) StockTransStatus.TRANS_NOTED:
                if (funtions.contains(RoleWareHouse.XUAT_KHO)) {
                    intent = new Intent(this, CreateImportStockFromStaffActivity.class);
                }
                break;
            default:
                ExportSuccessDialog exportSuccessDialog =
                        ExportSuccessDialog.newInstance(stockTrans, String.format(
                                getString(R.string.warehouse_label_export_success_code),
                                String.valueOf(stockTrans.getStockTransId())),
                                String.format(getString(R.string.warehouse_label_receive),
                                        String.valueOf(stockTrans.getToOwnerId())));
                exportSuccessDialog.setOnDialogDismissListener(
                        new ExportSuccessDialog.OnDialogDismissListener() {

                            @Override
                            public void onDialogDissmis() {
                            }
                        });
                exportSuccessDialog.show(getSupportFragmentManager(), "");
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public String getItemCountStringFormat() {
        return getString(R.string.stock_deliver_shipment);
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.stock_delivery_title);
    }

    @Override
    public boolean isShowAddButton() {
        funtions = mUserRepository.getFunctionsCodes();
        if (funtions.contains(RoleWareHouse.LAP_LENH_XUAT)) {
            return true;
        }
        return false;
    }

    private long loadShopId() {
        return mUserRepository.getUserInfo().getShop().getShopId();
    }

    @Override
    public void init() {
        funtions = mUserRepository.getFunctionsCodes();
        setStatus(Arrays.asList(getResources().getStringArray(R.array.xuatkhocd_status)));
        String shopName = mUserRepository.getUserInfo().getShop().getShopName();
        setWareHouseData(Arrays.asList(String.valueOf(shopName)));
    }

    @Override
    public String getWareHouseTitle() {
        return getString(R.string.activity_list_order_return_upper_kho_xuat);
    }

    @Override
    public void onAddClick() {
        Intent intent = new Intent(StockDeliverActivity.this, CreateCmdExpShopActivity.class);
        startActivity(intent);
    }
}
