package com.viettel.mbccs.screen.trahangcaptren;

import android.content.DialogInterface;
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
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class ListOrderReturnUpperActivity extends BaseListOrderActivity {

    private List<Shop> mShopList = new ArrayList<>();

    @Override
    public void doSearch() {
        showLoading();
        DataRequest<GetListExpCmdRequest> mDataRequest = new DataRequest<>();
        GetListExpCmdRequest mRequest = new GetListExpCmdRequest();
        if (getPositionStatus() == 0) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_DONE);
            mRequest.setStockTransType(StockTransType.TRANS_EXPORT);
        }

        if (getPositionStatus() == 1) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_EXP_IMPED);
            mRequest.setStockTransType(StockTransType.TRANS_EXPORT);
        }

        if (getPositionStatus() == 2) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_REJECT);
            mRequest.setStockTransType(StockTransType.TRANS_IMPORT);
        }

        mRequest.setStartDate(getFromDateString());
        mRequest.setEndDate(getToDateString());
        mRequest.setFromOwnerId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        mRequest.setFromOwnerType(OwnerType.SHOP);
        mRequest.setToOwnerId(mUserRepository.getUserInfo().getShop().getParentShopId());
        mRequest.setToOwnerType(OwnerType.SHOP);
        mDataRequest.setWsCode(WsCode.GetListExpCmd);
        mDataRequest.setWsRequest(mRequest);
        mBanHangKhoTaiChinhRepository.getListExpCmd(mDataRequest)
                .subscribe(new MBCCSSubscribe<GetListExpCmdResponse>() {
                    @Override
                    public void onSuccess(GetListExpCmdResponse object) {
                        if (object != null && object.getStockTranses() != null) {
                            for (StockTrans stockTrans : object.getStockTranses()) {
                                stockTrans.setActionName(
                                        getString(R.string.nv_trahangcaptren_action_detail));
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
    public void onItemStockTransClick(StockTrans stockTrans) {

    }

    @Override
    public String getItemCountStringFormat() {
        return getString(R.string.activity_list_order_return_upper_co_phieu_chua_nhan);
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.activity_list_order_return_upper_xuat_kho_tra_hang_cap_tren);
    }

    @Override
    public boolean isShowAddButton() {
        return true;
    }

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void init() {
        setStatus(Arrays.asList(
                getResources().getStringArray(R.array.export_from_staff_3_step_status)));

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
            return;
        }

        showLoading();
        GetListShopRequest request = new GetListShopRequest();
        request.setParentShopId(mUserRepository.getUserInfo().getShop().getParentShopId());

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
        return getString(R.string.activity_list_order_return_upper_kho_nhan);
    }
}
