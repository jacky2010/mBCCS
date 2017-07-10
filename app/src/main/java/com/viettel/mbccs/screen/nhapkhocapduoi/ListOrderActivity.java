package com.viettel.mbccs.screen.nhapkhocapduoi;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.constance.OwnerType;
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
import java.util.Collections;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class ListOrderActivity extends BaseListOrderActivity {

    private List<Shop> mShopList = new ArrayList<>();

    @Override
    public void onItemStockTransClick(StockTrans stockTrans) {
        Intent intent;
        switch ((int) stockTrans.getStockTransStatus()) {
            case (int) StockTransStatus.TRANS_DONE:
                intent = new Intent(this, CreateCmdFromStaffActivity.class);
                break;
            case (int) StockTransStatus.TRANS_NON_NOTE:
                intent = new Intent(this, CreateNoteFromStaffActivity.class);
                break;
            case (int) StockTransStatus.TRANS_NOTED:
                intent = new Intent(this, CreateImportStockFromStaffActivity.class);
                break;
            default:
                return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public String getItemCountStringFormat() {
        return getString(R.string.activity_list_order_warehouse_lenh_chua_lap_phieu);
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
        List<String> status = new ArrayList<>();
        Collections.addAll(status, getResources().getStringArray(R.array.nhapkhonhanvien_status));
        setStatus(status);

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
        int positionStatus = getPositionStatus();
        showLoading();
        DataRequest<GetListExpCmdRequest> dataRequest = new DataRequest<>();
        GetListExpCmdRequest request = new GetListExpCmdRequest();
        if (getPositionStatus() == 0) {
            request.setStockTransStatus(StockTransStatus.TRANS_NON_NOTE);
        }

        if (getPositionStatus() == 1) {
            request.setStockTransStatus(StockTransStatus.TRANS_NOTED);
        }

        if (getPositionStatus() == 2) {
            request.setStockTransStatus(StockTransStatus.TRANS_DONE);
        }

        if (getPositionStatus() == 3) {
            request.setStockTransStatus(StockTransStatus.TRANS_CANCEL);
        }

        request.setStockTransType(StockTransType.TRANS_EXPORT);
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
                        // FIXME: 7/9/2017 Fake?
                        object = new GetListExpCmdResponse();
                        List<StockTrans> stockTranses = new ArrayList<StockTrans>();
                        StockTrans stockTrans = new StockTrans();
                        stockTrans.setStockTransId(2342352);
                        stockTrans.setToOwnerId(234235);
                        stockTrans.setCreateDatetime("2017-07-05T01:28:46+07:00");
                        stockTrans.setStockTransStatusName("hang moi");
                        stockTrans.setStockTransStatus(StockTransStatus.TRANS_NON_NOTE);

                        StockTrans stockTrans1 = new StockTrans();
                        stockTrans1.setStockTransId(1237);
                        stockTrans1.setToOwnerId(23424);
                        stockTrans1.setCreateDatetime("2017-07-05T01:28:46+07:00");
                        stockTrans1.setStockTransStatusName("hang moi");
                        stockTrans1.setStockTransStatus(StockTransStatus.TRANS_NOTED);
                        stockTranses.add(stockTrans);
                        stockTranses.add(stockTrans1);

                        object.setStockTranses(stockTranses);

                        if (object != null && object.getStockTranses() != null) {
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
        onSearchSuccess();
    }

    @Override
    public void onAddClick() {
        super.onAddClick();
    }
}
