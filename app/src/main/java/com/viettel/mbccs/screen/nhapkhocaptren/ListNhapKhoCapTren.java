package com.viettel.mbccs.screen.nhapkhocaptren;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.constance.RoleWareHouse;
import com.viettel.mbccs.constance.StockTransStatus;
import com.viettel.mbccs.constance.StockTransType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.OwnerCode;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListExpCmdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOwnerCodeRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListExpCmdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOwneCodeReponse;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FRAMGIA\bui.dinh.viet on 12/07/2017.
 */

public class ListNhapKhoCapTren extends BaseListOrderActivity {

    public static final int CODE = 123;
    List<String> funtions = new ArrayList<>();

    @Override
    public void onItemClicked(Object object) {
    }

    @Override
    public void doSearch() {



        DataRequest<GetListExpCmdRequest> mDataRequest = new DataRequest<>();
        GetListExpCmdRequest mRequest = new GetListExpCmdRequest();
        showLoading();

        setStockTran2StepRequest(mRequest);
        mRequest.setStartDate(getFromDateString());
        mRequest.setEndDate(getToDateString());

        //get parent id
        mRequest.setFromOwnerId(mUserRepository.getUserInfo().getShop().getParentShopId());
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
                                //check for 3 step
                                setActionName2Step(stockTrans);
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

    private void setStockTran2StepRequest(GetListExpCmdRequest mRequest) {
        if (getPositionStatus() == 0) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_DONE);
            mRequest.setStockTransType(StockTransType.TRANS_EXPORT);
        }

        if (getPositionStatus() == 1) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_NOTED);
            mRequest.setStockTransType(StockTransType.TRANS_IMPORT);
        }

        if (getPositionStatus() == 2) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_DONE);
            mRequest.setStockTransType(StockTransType.TRANS_IMPORT);
        }

        if (getPositionStatus() == 3) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_CANCEL);
            mRequest.setStockTransType(StockTransType.TRANS_IMPORT);
        }
    }

    private void setActionName2Step(StockTrans stockTrans) {
        stockTrans.setActionName(getString(R.string.nv_trahangcaptren_action_detail));

        switch ((int) stockTrans.getStockTransStatus()) {
            case (int) StockTransStatus.TRANS_DONE:
                if (funtions.contains(RoleWareHouse.LAP_PHIEU_NHAP)
                        && stockTrans.getActionType() == StockTransType.TRANS_EXPORT) {
                    stockTrans.setActionName(
                            getString(R.string.commmon_warehouse_action_create_note));
                }

                break;
            case (int) StockTransStatus.TRANS_NOTED:
                if (funtions.contains(RoleWareHouse.NHAP_KHO)) {
                    stockTrans.setActionName(
                            getString(R.string.commmon_warehouse_action_create_import));
                }
                break;
            default:
                stockTrans.setActionName(getString(R.string.nv_trahangcaptren_action_detail));
        }
    }

    @Override
    public void onItemStockTransClick(StockTrans stockTrans) {
        Intent intent = new Intent(this, LapPhieuNhapKhoCapTrenActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        bundle.putBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY, true);
        switch ((int) stockTrans.getStockTransStatus()) {
            case (int) StockTransStatus.TRANS_DONE:
                if (funtions.contains(RoleWareHouse.LAP_PHIEU_NHAP)
                        && stockTrans.getActionType() == StockTransType.TRANS_EXPORT) {
                    intent = new Intent(this, LapPhieuNhapKhoCapTrenActivity.class);
                    bundle.putBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY, false);
                }

                break;
            case (int) StockTransStatus.TRANS_NOTED:
                if (funtions.contains(RoleWareHouse.NHAP_KHO)) {
                    intent = new Intent(this, NhapKhoTuCapTren.class);
                    bundle.putBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY, false);
                }
                break;
            default:
                bundle.putBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY, false);
                break;
        }

        intent.putExtras(bundle);
        startActivityForResult(intent, CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE && resultCode == RESULT_OK) {
            doSearch();
        }
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.import_high_store_title);
    }

    @Override
    public boolean isShowAddButton() {
        return false;
    }

    @Override
    public void init() {
        setStatus(Arrays.asList(
                getResources().getStringArray(R.array.import_from_staff_2_step_status)));
        setWareHouseData(
                Arrays.asList(String.valueOf(mUserRepository.getUserInfo().getShop().getParentShopName())));
        funtions = mUserRepository.getFunctionsCodes();
    }

    @Override
    public String getWareHouseTitle() {
        return getString(R.string.activity_list_order_return_upper_kho_xuat);
    }
}
