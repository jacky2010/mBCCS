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
import com.viettel.mbccs.screen.importwarehousefromstaff.importnote.CreateCmdFromStaffActivity;
import com.viettel.mbccs.screen.importwarehousefromstaff.importnote.CreateImportStockFromStaffActivity;
import com.viettel.mbccs.screen.importwarehousefromstaff.importnote.CreateNoteFromStaffActivity;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by buidinhviet on 6/13/17.
 */

public class StockDeliverActivity extends BaseListOrderActivity {

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void doSearch() {

        DataRequest<GetListExpCmdRequest> mDataRequest = new DataRequest<>();
        GetListExpCmdRequest mRequest = new GetListExpCmdRequest();
        showLoading();
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
            mRequest.setStockTransStatus(StockTransStatus.TRANS_CANCEL);
        }

        mRequest.setStockTransType(StockTransType.TRANS_IMPORT);
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
                        //fake
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
    }

    @Override
    public void onItemStockTransClick(StockTrans stockTrans) {
        Intent intent;
        switch ((int) stockTrans.getStockTransStatus()) {
            case (int) StockTransStatus.TRANS_DONE:
                intent = new Intent(this, CreateCmdFromStaffActivity.class);
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
        return getString(R.string.stock_deliver_shipment);
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.stock_delivery_title);
    }

    @Override
    public boolean isShowAddButton() {
        List<String> functionCode = mUserRepository.getFunctionsCodes();
        if (functionCode.contains(RoleWareHouse.LAP_LENH_XUAT)) {
            return true;
        }
        return true;
    }

    private long loadShopId() {
        return mUserRepository.getUserInfo().getShop().getShopId();
    }

    @Override
    public void init() {
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



     /*@Override
    public void openCreateCommand() {
//        Intent intent = new Intent(this, BaseCreateCommandNoteActivity.class);
//        StockTrans stockTrans = new StockTrans();
//        stockTrans.setStockTransId(1237);
//        stockTrans.setToOwnerId(1232);
//        stockTrans.setCreateDatetime("2017-01-02");
//        stockTrans.setStockTransStatusName("hang moi");
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
//        intent.putExtras(bundle);
//        intent.putExtra(BaseCreateCommandNoteActivity.NAME_SCREEN, getString(R.string.xuatkhocapduoi_title_create_command));
//        intent.putExtra(BaseCreateCommandNoteActivity.FUNCTION, BaseCreateCommandNoteActivity.ACTION_CREATE_CMD);
//        startActivity(intent);
    }*/
}
