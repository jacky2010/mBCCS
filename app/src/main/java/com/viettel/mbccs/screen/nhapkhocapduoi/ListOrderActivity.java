package com.viettel.mbccs.screen.nhapkhocapduoi;

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
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListExpCmdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListExpCmdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListShopResponse;
import com.viettel.mbccs.screen.nhapkhocapduoi.createorder.CreateCmdFromStaffActivity;
import com.viettel.mbccs.screen.nhapkhocapduoi.createorder.CreateImportStockFromStaffActivity;
import com.viettel.mbccs.screen.nhapkhocapduoi.createorder.CreateNoteFromStaffActivity;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class ListOrderActivity extends BaseListOrderActivity {

    private List<Shop> mShopList = new ArrayList<>();

    private List<String> mFunctionList = new ArrayList<>();

    @Override
    public void onItemStockTransClick(StockTrans stockTrans) {
        Intent intent = new Intent(this, CreateCmdFromStaffActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY, true);

        switch ((int) stockTrans.getStockTransStatus()) {
            case (int) StockTransStatus.TRANS_DONE:
                if (mFunctionList.contains(RoleWareHouse.LAP_LENH_NHAP)
                        && stockTrans.getActionType() == StockTransType.TRANS_EXPORT) {
                    intent = new Intent(this, CreateCmdFromStaffActivity.class);
                    bundle.putBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY, false);
                }
                break;
            case (int) StockTransStatus.TRANS_NON_NOTE:
                if (mFunctionList.contains(RoleWareHouse.LAP_PHIEU_NHAP)) {
                    intent = new Intent(ListOrderActivity.this, CreateNoteFromStaffActivity.class);
                    bundle.putBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY, false);
                }
                break;
            case (int) StockTransStatus.TRANS_NOTED:
                if (mFunctionList.contains(RoleWareHouse.NHAP_KHO)) {
                    intent = new Intent(ListOrderActivity.this,
                            CreateImportStockFromStaffActivity.class);
                    bundle.putBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY, false);
                }
                break;
            default:
                stockTrans.setActionName(getString(R.string.nv_trahangcaptren_action_detail));
        }
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.list_order_presenter_nhap_kho_cap_duoi);
    }

    @Override
    public boolean isShowAddButton() {
        return false;
    }

    @Override
    public void init() {
        setStatus(Arrays.asList(
                getResources().getStringArray(R.array.import_from_staff_3_step_status)));

        showLoading();
        GetListShopRequest request = new GetListShopRequest();
        request.setParentShopId(mUserRepository.getUserInfo().getShop().getShopId());

        DataRequest<GetListShopRequest> dataRequest = new DataRequest<>();
        dataRequest.setWsRequest(request);
        dataRequest.setWsCode(WsCode.GetListShop);
        mBanHangKhoTaiChinhRepository.getListShop(dataRequest)
                .subscribe(new MBCCSSubscribe<GetListShopResponse>() {
                    @Override
                    public void onSuccess(GetListShopResponse object) {
                        if (object != null && object.getShopList() != null && !object.getShopList()
                                .isEmpty()) {
                            mShopList.addAll(object.getShopList());
                            List<String> shopList = new ArrayList<>();
                            for (Shop s : object.getShopList()) {
                                shopList.add(s.getShopName());
                            }
                            setWareHouseData(shopList);
                        }
                    }

                    @Override
                    public void onError(BaseException error) {
                        showErrorDialog(error);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        hideLoading();
                    }
                });

        mFunctionList = mUserRepository.getFunctionsCodes();
    }

    @Override
    public String getWareHouseTitle() {
        return getString(R.string.activity_list_order_return_upper_kho_xuat);
    }

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void doSearch() {
        showLoading();
        DataRequest<GetListExpCmdRequest> dataRequest = new DataRequest<>();
        GetListExpCmdRequest request = new GetListExpCmdRequest();
        if (getPositionStatus() == 0) {
            request.setStockTransStatus(StockTransStatus.TRANS_DONE);
            request.setStockTransType(StockTransType.TRANS_EXPORT);
        }

        if (getPositionStatus() == 1) {
            request.setStockTransStatus(StockTransStatus.TRANS_NON_NOTE);
            request.setStockTransType(StockTransType.TRANS_IMPORT);
        }

        if (getPositionStatus() == 2) {
            request.setStockTransStatus(StockTransStatus.TRANS_NOTED);
            request.setStockTransType(StockTransType.TRANS_IMPORT);
        }

        if (getPositionStatus() == 3) {
            request.setStockTransStatus(StockTransStatus.TRANS_DONE);
            request.setStockTransType(StockTransType.TRANS_IMPORT);
        }

        if (getPositionStatus() == 4) {
            request.setStockTransStatus(StockTransStatus.TRANS_CANCEL);
            request.setStockTransType(StockTransType.TRANS_IMPORT);
        }

        request.setStartDate(getFromDateString());
        request.setEndDate(getToDateString());
        request.setFromOwnerId(mShopList.get(getPositionWareHouser()).getShopId());
        request.setFromOwnerType(OwnerType.SHOP);
        request.setToOwnerId(mUserRepository.getUserInfo().getShop().getShopId());
        request.setToOwnerType(OwnerType.SHOP);
        dataRequest.setWsCode(WsCode.GetListExpCmd);
        dataRequest.setWsRequest(request);
        mBanHangKhoTaiChinhRepository.getListExpCmd(dataRequest)
                .subscribe(new MBCCSSubscribe<GetListExpCmdResponse>() {
                    @Override
                    public void onSuccess(GetListExpCmdResponse object) {
                        if (object != null && object.getStockTranses() != null) {
                            for (StockTrans stockTrans : object.getStockTranses()) {
                                //check for 3 step
                                stockTrans.setActionName(
                                        getString(R.string.nv_trahangcaptren_action_detail));

                                switch ((int) stockTrans.getStockTransStatus()) {
                                    case (int) StockTransStatus.TRANS_DONE:
                                        if (mFunctionList.contains(RoleWareHouse.LAP_LENH_NHAP)
                                                && stockTrans.getActionType()
                                                == StockTransType.TRANS_EXPORT) {
                                            stockTrans.setActionName(getString(
                                                    R.string.commmon_warehouse_action_create_cmd));
                                        }
                                        break;
                                    case (int) StockTransStatus.TRANS_NON_NOTE:
                                        if (mFunctionList.contains(RoleWareHouse.LAP_PHIEU_NHAP)) {
                                            stockTrans.setActionName(getString(
                                                    R.string.commmon_warehouse_action_create_note));
                                        }
                                        break;
                                    case (int) StockTransStatus.TRANS_NOTED:
                                        if (mFunctionList.contains(RoleWareHouse.NHAP_KHO)) {
                                            stockTrans.setActionName(getString(
                                                    R.string.commmon_warehouse_action_create_import));
                                        }
                                        break;
                                    default:
                                        stockTrans.setActionName(getString(
                                                R.string.nv_trahangcaptren_action_detail));
                                }
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

    @Override
    public void onAddClick() {
    }
}
