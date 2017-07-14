package com.viettel.mbccs.screen.trahangcaptren;

import android.content.DialogInterface;
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
import com.viettel.mbccs.screen.trahangcaptren.create.CreateTicketActivity;
import com.viettel.mbccs.screen.trahangcaptren.create.LapLenhTraHangCapTrenActivity;
import com.viettel.mbccs.screen.trahangcaptren.create.TraHangCapTrenActivity;
import com.viettel.mbccs.screen.warehousecommon.exportsuccess.ExportSuccessDialog;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class ListOrderReturnUpperActivity extends BaseListOrderActivity {

    private List<Long> mShopList = new ArrayList<>();

    private List<String> mFunctionList = new ArrayList<>();

    @Override
    public void doSearch() {
        showLoading();
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
            //TODO: will confirm later
            mRequest.setStockTransStatus(StockTransStatus.TRANS_REJECT);
        }

        mRequest.setStartDate(getFromDateString());
        mRequest.setEndDate(getToDateString());
        mRequest.setFromOwnerId(mUserRepository.getUserInfo().getShop().getShopId());
        mRequest.setFromOwnerType(OwnerType.SHOP);
        mRequest.setToOwnerId(mShopList.get(getPositionWareHouser()));
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
                if (mFunctionList.contains(RoleWareHouse.LAP_PHIEU_XUAT)) {
                    stockTrans.setActionName(
                            getString(R.string.commmon_warehouse_action_create_note));
                }

                break;
            case (int) StockTransStatus.TRANS_NOTED:
                if (mFunctionList.contains(RoleWareHouse.XUAT_KHO)) {
                    stockTrans.setActionName(getString(R.string.common_label_export));
                }
                break;
            default:
                stockTrans.setActionName(getString(R.string.nv_trahangcaptren_action_detail));
                break;
        }
    }

    @Override
    public void onItemStockTransClick(final StockTrans stockTrans) {
        Intent intent = null;
        switch ((int) stockTrans.getStockTransStatus()) {
            case (int) StockTransStatus.TRANS_NON_NOTE:
                if (mFunctionList.contains(RoleWareHouse.LAP_PHIEU_XUAT)) {
                    intent = new Intent(this, CreateTicketActivity.class);
                }
                break;
            case (int) StockTransStatus.TRANS_NOTED:
                if (mFunctionList.contains(RoleWareHouse.XUAT_KHO)) {
                    intent = new Intent(this, TraHangCapTrenActivity.class);
                }
                break;
            case (int) StockTransStatus.TRANS_CANCEL:
                ExportSuccessDialog exportSuccessDialog =
                        ExportSuccessDialog.newInstance(stockTrans, String.format(
                                getString(R.string.warehouse_label_export_success_code),
                                String.valueOf(stockTrans.getStockTransId())),
                                String.format(getString(R.string.warehouse_label_receive),
                                        String.valueOf(stockTrans.getToOwnerId())), false);
                exportSuccessDialog.setOnDialogDismissListener(
                        new ExportSuccessDialog.OnDialogDismissListener() {

                            @Override
                            public void onDialogDissmis() {
                            }
                        });
                exportSuccessDialog.show(getSupportFragmentManager(), "");
                return;
            default:
                ExportSuccessDialog exportSuccessDialog1 =
                        ExportSuccessDialog.newInstance(stockTrans, String.format(
                                getString(R.string.warehouse_label_export_success_code),
                                String.valueOf(stockTrans.getStockTransId())),
                                String.format(getString(R.string.warehouse_label_receive),
                                        String.valueOf(stockTrans.getToOwnerId())));
                exportSuccessDialog1.setOnDialogDismissListener(
                        new ExportSuccessDialog.OnDialogDismissListener() {

                            @Override
                            public void onDialogDissmis() {
                            }
                        });
                exportSuccessDialog1.show(getSupportFragmentManager(), "");
                return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.activity_list_order_return_upper_xuat_kho_tra_hang_cap_tren);
    }

    @Override
    public boolean isShowAddButton() {
        List<String> functionCodes = mUserRepository.getFunctionsCodes();
        return functionCodes.contains(RoleWareHouse.XUAT_KHO);
    }

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void init() {
        setStatus(Arrays.asList(getResources().getStringArray(R.array.xuatkhocd_status)));

        if (mUserRepository.getUserInfo().getShop().getParentShopId() == 0) {
            BaseException e = BaseException.toUnexpectedError(
                    new Throwable(getString(R.string.common_msg_error_general)));
            showErrorDialog(e, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });
        }

        setWareHouseData(Collections.singletonList(
                String.valueOf(mUserRepository.getUserInfo().getShop().getParentShopId())));
        mShopList.add(mUserRepository.getUserInfo().getShop().getParentShopId());
        mFunctionList = mUserRepository.getFunctionsCodes();
    }

    @Override
    public String getWareHouseTitle() {
        return getString(R.string.activity_list_order_return_upper_kho_nhan);
    }

    @Override
    public void onAddClick() {
        super.onAddClick();
        Intent intent = new Intent(this, LapLenhTraHangCapTrenActivity.class);
        startActivity(intent);
    }
}
