package com.viettel.mbccs.screen.nvxacnhanhang;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.constance.StockTransStatus;
import com.viettel.mbccs.constance.StockTransType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListExpCmdRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListExpCmdResponse;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NvXacNhanHangActivity extends BaseListOrderActivity {

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void doSearch() {
        DataRequest<GetListExpCmdRequest> mDataRequest = new DataRequest<>();
        GetListExpCmdRequest mRequest = new GetListExpCmdRequest();
        showLoading();
        if (getPositionStatus() == 0) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_DONE);
        }

        if (getPositionStatus() == 1) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_CANCEL);
        }
        mRequest.setStockTransType(StockTransType.TRANS_EXPORT);
        mRequest.setStartDate(getFromDateString());
        mRequest.setEndDate(getToDateString());
        mRequest.setFromOwnerId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        mRequest.setFromOwnerType(OwnerType.STAFF);
        mRequest.setToOwnerId(Long.parseLong(getWareHouseData().get(getPositionWareHouser())));
        mRequest.setToOwnerType(OwnerType.SHOP);
        mDataRequest.setWsCode(WsCode.GetListExpCmd);
        mDataRequest.setWsRequest(mRequest);
        mBanHangKhoTaiChinhRepository.getListExpCmd(mDataRequest)
                .subscribe(new MBCCSSubscribe<GetListExpCmdResponse>() {
                    @Override
                    public void onSuccess(GetListExpCmdResponse object) {
                        //fake
                        object = new GetListExpCmdResponse();
                        List<StockTrans> stockTranses = new ArrayList<StockTrans>();
                        StockTrans stockTrans = new StockTrans();
                        stockTrans.setStockTransId(2342352);
                        stockTrans.setToOwnerId(234235);
                        stockTrans.setCreateDatetime("2017-07-05T01:28:46+07:00");
                        stockTrans.setStockTransStatusName("hang moi");
                        StockTrans stockTrans1 = new StockTrans();
                        stockTrans1.setStockTransId(1237);
                        stockTrans1.setToOwnerId(23424);
                        stockTrans1.setCreateDatetime("2017-07-05T01:28:46+07:00");
                        stockTrans1.setStockTransStatusName("hang moi");
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
    }

    @Override
    public void onItemStockTransClick(StockTrans stockTrans) {

    }

    @Override
    public String getItemCountStringFormat() {
        return null;
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.nv_xac_nhan_hang_title);
    }

    @Override
    public boolean isShowAddButton() {
        return false;
    }

    @Override
    public void init() {
        setStatus(Arrays.asList(
                getResources().getStringArray(R.array.nhanvien_tranhang_captren_status)));
        setWareHouseData(
                Arrays.asList(String.valueOf(mUserRepository.getUserInfo().getShop().getShopId())));
    }

    @Override
    public String getWareHouseTitle() {
        return getString(R.string.nv_xac_nhan_hang_ma_kho_xuat);
    }
}
