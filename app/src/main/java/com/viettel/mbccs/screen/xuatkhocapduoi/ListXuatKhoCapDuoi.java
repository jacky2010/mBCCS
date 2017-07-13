package com.viettel.mbccs.screen.xuatkhocapduoi;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.constance.RoleWareHouse;
import com.viettel.mbccs.constance.StockTransStatus;
import com.viettel.mbccs.constance.StockTransType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.Shop;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListExpCmdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListExpCmdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListShopResponse;
import com.viettel.mbccs.screen.warehousecommon.exportsuccess.ExportSuccessDialog;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.viettel.mbccs.R.string.common_label_notice;

/**
 * Created by buidinhviet on 6/13/17.
 */

public class ListXuatKhoCapDuoi extends BaseListOrderActivity {

    List<String> funtions = new ArrayList<>();
    List<String> shopNames = new ArrayList<>();
    List<Shop> mListShop = new ArrayList<>();

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
            mRequest.setStockTransStatus(StockTransStatus.TRANS_REJECT);
        }

        mRequest.setStartDate(getFromDateString());
        mRequest.setEndDate(getToDateString());
        mRequest.setFromOwnerId(loadShopId());
        mRequest.setFromOwnerType(OwnerType.SHOP);
        mRequest.setToOwnerId(mListShop.get(getPositionWareHouser()).getShopId());
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
        stockTrans.setActionName(getString(R.string.nv_trahangcaptren_action_detail));
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
                    intent = new Intent(this, LapPhieuXuatKhoCapDuoiActivity.class);
                } else {
                    showDialogExportSuccess(
                            String.format(getString(R.string.common_import_label_import_cmd),
                                    String.valueOf(stockTrans.getStockTransId())), stockTrans,
                            false);
                    return;
                }

                break;
            case (int) StockTransStatus.TRANS_NOTED:
                if (funtions.contains(RoleWareHouse.XUAT_KHO)) {
                    intent = new Intent(this, XuatKhoCapDuoiActivity.class);
                } else {
                    showDialogExportSuccess(
                            String.format(getString(R.string.common_import_label_import_note),
                                    String.valueOf(stockTrans.getStockTransId())), stockTrans,
                            false);
                    return;
                }
                break;
            case (int) StockTransStatus.TRANS_DONE:
                showDialogExportSuccess(
                        String.format(getString(R.string.warehouse_label_export_success_code),
                                String.valueOf(stockTrans.getStockTransId())), stockTrans, true);
                return;
            case (int) StockTransStatus.TRANS_CANCEL:
                showDialogExportSuccess(
                        String.format(getString(R.string.common_import_label_import_cmd),
                                String.valueOf(stockTrans.getStockTransId())), stockTrans, false);
                return;
            default:
                showDialogExportSuccess(
                        String.format(getString(R.string.common_import_label_import_cmd),
                                String.valueOf(stockTrans.getStockTransId())), stockTrans, true);
                return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void showDialogExportSuccess(String title, StockTrans stockTrans,
            boolean isShowSerial) {
        ExportSuccessDialog exportSuccessDialog1 =
                ExportSuccessDialog.newInstance(stockTrans, getString(common_label_notice), title,
                        String.format(getString(R.string.warehouse_label_receive),
                                String.valueOf(stockTrans.getToOwnerId())), isShowSerial);
        exportSuccessDialog1.setOnDialogDismissListener(
                new ExportSuccessDialog.OnDialogDismissListener() {

                    @Override
                    public void onDialogDismiss() {

                    }
                });
        exportSuccessDialog1.show(getSupportFragmentManager(), "");
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

    private void loadStaffList() {
        showLoading();
        DataRequest<GetListShopRequest> dataRequest = new DataRequest<>();
        GetListShopRequest request = new GetListShopRequest();
        request.setParentShopId((mUserRepository.getUserInfo().getShop().getShopId()));
        dataRequest.setWsCode(WsCode.GetListShop);
        dataRequest.setWsRequest(request);
        BanHangKhoTaiChinhRepository.getInstance()
                .getListShop(dataRequest)
                .subscribe(new MBCCSSubscribe<GetListShopResponse>() {
                    @Override
                    public void onSuccess(GetListShopResponse object) {
                        if (object != null && object.getShopList() != null) {
                            mListShop.addAll(object.getShopList());
                            for (Shop shop : mListShop) {
                                shopNames.add(shop.getShopName());
                            }
                            setWareHouseData(shopNames);
                        }
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialogError(ListXuatKhoCapDuoi.this, error);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        hideLoading();
                    }
                });
    }

    @Override
    public void init() {
        funtions = mUserRepository.getFunctionsCodes();
        setStatus(Arrays.asList(getResources().getStringArray(R.array.xuatkhocd_status)));
        loadStaffList();
    }

    @Override
    public String getWareHouseTitle() {
        return getString(R.string.activity_list_order_return_upper_kho_xuat);
    }

    @Override
    public void onAddClick() {
        Intent intent = new Intent(ListXuatKhoCapDuoi.this, LapLenhXuatKhoCapDuoiActivity.class);
        startActivity(intent);
    }
}
