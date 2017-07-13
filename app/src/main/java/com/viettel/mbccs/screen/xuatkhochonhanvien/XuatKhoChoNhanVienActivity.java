package com.viettel.mbccs.screen.xuatkhochonhanvien;

import android.content.DialogInterface;
import android.content.Intent;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.constance.RoleWareHouse;
import com.viettel.mbccs.constance.STEP_WAREHOUSE;
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
 * Created by HuyQuyet on 7/11/17.
 */

public class XuatKhoChoNhanVienActivity extends BaseListOrderActivity {

    List<String> staffListName = new ArrayList<>();
    List<OwnerCode> ownerCodes = new ArrayList<>();
    List<String> funtions = new ArrayList<>();

    @Override
    public void doSearch() {
        if (ownerCodes.size() == 0) {
            DialogUtils.showDialog(XuatKhoChoNhanVienActivity.this, null,
                    getString(R.string.error_load_data), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }, false);
        }

        GetListExpCmdRequest mRequest = new GetListExpCmdRequest();
        showLoading();

        if (Constants.FuntionConstant.ENVIROMENT_STEP == 2) {
            mRequest.setStockTransStatus((long) (getPositionStatus() + 2));
            mRequest.setStockTransType(StockTransType.TRANS_EXPORT);
        } else {
            mRequest.setStockTransStatus((long) (getPositionStatus() + 1));
            mRequest.setStockTransType(StockTransType.TRANS_EXPORT);
        }

        mRequest.setStockTransType(StockTransType.TRANS_EXPORT);
        mRequest.setStartDate(getFromDateString());
        mRequest.setEndDate(getToDateString());

        mRequest.setFromOwnerId(mUserRepository.getUserInfo().getShop().getShopId());
        mRequest.setFromOwnerType(OwnerType.SHOP);

        mRequest.setToOwnerId(ownerCodes.get(getPositionWareHouser()).getStaffId());
        mRequest.setToOwnerType(OwnerType.STAFF);

        DataRequest<GetListExpCmdRequest> mDataRequest = new DataRequest<>();
        mDataRequest.setWsCode(WsCode.GetListExpCmd);
        mDataRequest.setWsRequest(mRequest);
        mBanHangKhoTaiChinhRepository.getListExpCmd(mDataRequest)
                .subscribe(new MBCCSSubscribe<GetListExpCmdResponse>() {
                    @Override
                    public void onSuccess(GetListExpCmdResponse object) {

                        if (object != null && object.getStockTranses() != null) {
                            for (StockTrans stockTrans : object.getStockTranses()) {
                                //check for 3 step
                                if (Constants.FuntionConstant.ENVIROMENT_STEP
                                        == STEP_WAREHOUSE.STEP_3) {
                                    stockTrans.setActionName(
                                            getString(R.string.nv_trahangcaptren_action_detail));

                                    switch ((int) stockTrans.getStockTransStatus()) {
                                        case (int) StockTransStatus.TRANS_DONE:
                                            if (funtions.contains(RoleWareHouse.LAP_LENH_NHAP)
                                                    && stockTrans.getActionType()
                                                    == StockTransType.TRANS_EXPORT) {
                                                stockTrans.setActionName(getString(
                                                        R.string.commmon_warehouse_action_create_cmd));
                                            }

                                            break;
                                        case (int) StockTransStatus.TRANS_NON_NOTE:
                                            if (funtions.contains(RoleWareHouse.LAP_PHIEU_NHAP)) {
                                                stockTrans.setActionName(getString(
                                                        R.string.commmon_warehouse_action_create_note));
                                            }
                                            break;
                                        case (int) StockTransStatus.TRANS_NOTED:
                                            if (funtions.contains(RoleWareHouse.NHAP_KHO)) {
                                                stockTrans.setActionName(getString(
                                                        R.string.commmon_warehouse_action_create_import));
                                            }
                                            break;
                                        default:
                                            stockTrans.setActionName(getString(
                                                    R.string.nv_trahangcaptren_action_detail));
                                    }
                                }

                                //check for 2 step

                                if (Constants.FuntionConstant.ENVIROMENT_STEP
                                        == STEP_WAREHOUSE.STEP_2) {
                                    stockTrans.setActionName(
                                            getString(R.string.nv_trahangcaptren_action_detail));

                                    switch ((int) stockTrans.getStockTransStatus()) {

                                        case (int) StockTransStatus.TRANS_NOTED:
                                            //                                            if (funtions.contains(RoleWareHouse.LAP_PHIEU_XUAT)) {
                                            stockTrans.setActionName(getString(
                                                    R.string.xuat_kho_cho_nhan_vien_xuat_kho));
                                            //                                            }
                                            break;
                                        case (int) StockTransStatus.TRANS_DONE:
                                        case (int) StockTransStatus.TRANS_EXP_IMPED:
                                        case (int) StockTransStatus.TRANS_CANCEL:
                                        case (int) StockTransStatus.TRANS_REJECT:
                                            stockTrans.setActionName(getString(
                                                    R.string.nv_trahangcaptren_action_detail));
                                            break;
                                        default:
                                            stockTrans.setActionName(getString(
                                                    R.string.nv_trahangcaptren_action_detail));
                                    }
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
    public void onItemStockTransClick(StockTrans stockTrans) {

    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.xuat_kho_cho_nhan_vien_title);
    }

    @Override
    public boolean isShowAddButton() {
        return true;
    }

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void onAddClick() {
        Intent intent;
        if (Constants.FuntionConstant.ENVIROMENT_STEP == STEP_WAREHOUSE.STEP_2) {
            intent = new Intent(XuatKhoChoNhanVienActivity.this,
                    LapPhieuXuatKhoChoNhanVienActivity.class);
        } else {
            intent = new Intent(XuatKhoChoNhanVienActivity.this,
                    LapLenhXuatKhoChoNhanVienActivity.class);
        }

        startActivity(intent);
    }

    @Override
    public void init() {
        if (Constants.FuntionConstant.ENVIROMENT_STEP == STEP_WAREHOUSE.STEP_2) {
            setStatus(Arrays.asList(
                    getResources().getStringArray(R.array.export_from_shop_2_step_status)));
        } else {
            setStatus(Arrays.asList(
                    getResources().getStringArray(R.array.export_from_shop_3_step_status)));
        }

        loadStaffList();
        funtions = mUserRepository.getFunctionsCodes();
    }

    @Override
    public String getWareHouseTitle() {
        return getString(R.string.xuat_kho_cho_nhan_vien_ma_kho_nhan);
    }

    private void loadStaffList() {
        showLoading();
        DataRequest<GetListOwnerCodeRequest> dataRequest = new DataRequest<>();
        GetListOwnerCodeRequest request = new GetListOwnerCodeRequest();
        request.setShopId(String.valueOf(mUserRepository.getUserInfo().getShop().getShopId()));
        dataRequest.setWsCode(WsCode.GetListOwnerCode);
        dataRequest.setWsRequest(request);
        mUserRepository.getListOwnerCode(dataRequest)
                .subscribe(new MBCCSSubscribe<GetListOwneCodeReponse>() {
                    @Override
                    public void onSuccess(GetListOwneCodeReponse object) {
                        if (object != null && object.getOwnerCodes() != null) {
                            ownerCodes.addAll(object.getOwnerCodes());
                            for (OwnerCode ownerCode : ownerCodes) {
                                staffListName.add(ownerCode.getName());
                            }
                            setWareHouseData(staffListName);
                        }
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialog(XuatKhoChoNhanVienActivity.this, null,
                                getString(R.string.error_load_data),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                }, false);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        hideLoading();
                    }
                });
    }
}
