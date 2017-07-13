package com.viettel.mbccs.screen.xuatkhochonhanvien;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.viettel.mbccs.screen.warehousecommon.exportsuccess.ExportSuccessDialog;
import com.viettel.mbccs.screen.xuatkhochonhanvien.threestep.LapLenh3XuatKhoChoNhanVienActivity;
import com.viettel.mbccs.screen.xuatkhochonhanvien.threestep.LapPhieu3XuatKhoChoNhanVienActivity;
import com.viettel.mbccs.screen.xuatkhochonhanvien.threestep.XuatKho3XuatKhoChoNhanVienActivity;
import com.viettel.mbccs.screen.xuatkhochonhanvien.twostep.LapPhieu2XuatKhoChoNhanVienActivity;
import com.viettel.mbccs.screen.xuatkhochonhanvien.twostep.XuatKho2XuatKhoChoNhanVienActivity;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.viettel.mbccs.R.string.xuat_kho_cho_nhan_vien_chi_tiet_phieu_xuat;

/**
 * Created by HuyQuyet on 7/11/17.
 */

public class XuatKhoChoNhanVienActivity extends BaseListOrderActivity {
    public static final int REQUEST_CODE = 101;
    public static final String DATA_RESULT = "data_result";
    private List<String> staffListName = new ArrayList<>();
    private List<OwnerCode> ownerCodes = new ArrayList<>();
    private List<String> funtions = new ArrayList<>();

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
                                            getString(R.string.xuat_kho_cho_nhan_vien_chi_tiet));

                                    switch ((int) stockTrans.getStockTransStatus()) {
                                        case (int) StockTransStatus.TRANS_NON_NOTE:
                                            if (funtions.contains(RoleWareHouse.LAP_PHIEU_NHAP)) {
                                                stockTrans.setActionName(getString(
                                                        R.string.xuat_kho_cho_nhan_vien_lap_phieu));
                                            }
                                            break;
                                        case (int) StockTransStatus.TRANS_NOTED:
                                            if (funtions.contains(RoleWareHouse.NHAP_KHO)) {
                                                stockTrans.setActionName(getString(
                                                        R.string.xuat_kho_cho_nhan_vien_xuat_kho));
                                            }
                                            break;
                                        case (int) StockTransStatus.TRANS_EXP_IMPED:
                                        case (int) StockTransStatus.TRANS_CANCEL:
                                        case (int) StockTransStatus.TRANS_DONE:
                                            //  if (funtions.contains(RoleWareHouse.LAP_LENH_NHAP)
                                            //    && stockTrans.getActionType()
                                            //     == StockTransType.TRANS_EXPORT) {
                                            stockTrans.setActionName(getString(
                                                    R.string.xuat_kho_cho_nhan_vien_chi_tiet));
                                            //                                            }

                                            break;
                                        default:
                                            stockTrans.setActionName(getString(
                                                    R.string.xuat_kho_cho_nhan_vien_chi_tiet));
                                    }
                                }

                                //check for 2 step

                                if (Constants.FuntionConstant.ENVIROMENT_STEP
                                        == STEP_WAREHOUSE.STEP_2) {
                                    stockTrans.setActionName(
                                            getString(R.string.nv_trahangcaptren_action_detail));

                                    switch ((int) stockTrans.getStockTransStatus()) {

                                        case (int) StockTransStatus.TRANS_NOTED:
                                            if (funtions.contains(RoleWareHouse.XUAT_KHO)) {
                                                stockTrans.setActionName(getString(
                                                    R.string.xuat_kho_cho_nhan_vien_xuat_kho));
                                            }
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
        Intent intent = null;
        String cmdCodeTitle = "";
        String cmdNameTitle = "";
        if (Constants.FuntionConstant.ENVIROMENT_STEP == STEP_WAREHOUSE.STEP_2) {
            switch ((int) stockTrans.getStockTransStatus()) {
                case (int) StockTransStatus.TRANS_NOTED:
                    if (funtions.contains(RoleWareHouse.XUAT_KHO)) {
                        intent = new Intent(this, XuatKho2XuatKhoChoNhanVienActivity.class);
                    } else {
                        cmdCodeTitle =
                                getString(R.string.xuat_kho_cho_nhan_vien_lap_phieu_thanh_cong,
                                        String.valueOf(stockTrans.getStockTransId()));
                    }
                    break;

                case (int) StockTransStatus.TRANS_CANCEL:
                    cmdCodeTitle = getString(R.string.xuat_kho_cho_nhan_vien_giao_dich_da_huy,
                            String.valueOf(stockTrans.getStockTransId()));
                    break;

                case (int) StockTransStatus.TRANS_DONE:
                    cmdCodeTitle = getString(R.string.xuat_kho_cho_nhan_vien_giao_dich_da_xuat,
                            String.valueOf(stockTrans.getStockTransId()));
                    break;

                case (int) StockTransStatus.TRANS_REJECT:
                    cmdCodeTitle = getString(R.string.xuat_kho_cho_nhan_vien_giao_dich_bi_tu_choi,
                            String.valueOf(stockTrans.getStockTransId()));
                    break;

                case (int) StockTransStatus.TRANS_EXP_IMPED:
                    cmdCodeTitle = getString(R.string.xuat_kho_cho_nhan_vien_giao_dich_da_nhap,
                            String.valueOf(stockTrans.getStockTransId()));
                    break;
            }
        } else {
            switch ((int) stockTrans.getStockTransStatus()) {
                case (int) StockTransStatus.TRANS_NON_NOTE:
                    if (funtions.contains(RoleWareHouse.LAP_PHIEU_XUAT)) {
                        intent = new Intent(this, LapPhieu3XuatKhoChoNhanVienActivity.class);
                    } else {
                        cmdCodeTitle =
                                getString(R.string.xuat_kho_cho_nhan_vien_lap_lenh_thanh_cong,
                                        String.valueOf(stockTrans.getStockTransId()));
                    }
                    break;

                case (int) StockTransStatus.TRANS_NOTED:
                    if (funtions.contains(RoleWareHouse.XUAT_KHO)) {
                        intent = new Intent(this, XuatKho3XuatKhoChoNhanVienActivity.class);
                    } else {
                        cmdCodeTitle =
                                getString(R.string.xuat_kho_cho_nhan_vien_lap_phieu_thanh_cong,
                                        String.valueOf(stockTrans.getStockTransId()));
                    }
                    break;

                case (int) StockTransStatus.TRANS_CANCEL:
                    cmdCodeTitle = getString(R.string.xuat_kho_cho_nhan_vien_giao_dich_da_huy,
                            String.valueOf(stockTrans.getStockTransId()));
                    break;

                case (int) StockTransStatus.TRANS_DONE:
                    cmdCodeTitle = getString(R.string.xuat_kho_cho_nhan_vien_giao_dich_da_xuat,
                            String.valueOf(stockTrans.getStockTransId()));
                    break;

                case (int) StockTransStatus.TRANS_REJECT:
                    cmdCodeTitle = getString(R.string.xuat_kho_cho_nhan_vien_giao_dich_bi_tu_choi,
                            String.valueOf(stockTrans.getStockTransId()));
                    break;

                case (int) StockTransStatus.TRANS_EXP_IMPED:
                    cmdCodeTitle = getString(R.string.xuat_kho_cho_nhan_vien_giao_dich_da_nhap,
                            String.valueOf(stockTrans.getStockTransId()));
                    break;
            }
        }
        if (intent == null) {
            for (OwnerCode ownerCode : ownerCodes) {
                if (ownerCode.getStaffId() == stockTrans.getToOwnerId()) {
                    cmdNameTitle = getString(R.string.xuat_kho_cho_nhan_vien_nhan_vien_nhan,
                            ownerCode.getName());
                }
            }
            //            cmdNameTitle = getString(R.string.xuat_kho_cho_nhan_vien_nhan_vien_nhan,
            //                    String.valueOf(stockTrans.getToOwnerId()));
            ExportSuccessDialog exportSuccessDialog = ExportSuccessDialog.newInstance(stockTrans,
                    getString(xuat_kho_cho_nhan_vien_chi_tiet_phieu_xuat), cmdCodeTitle,
                    cmdNameTitle);
            exportSuccessDialog.setOnDialogDismissListener(
                    new ExportSuccessDialog.OnDialogDismissListener() {
                        @Override
                        public void onDialogDismiss() {
                        }
                    });
            exportSuccessDialog.show(getSupportFragmentManager(), "");
        } else {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.xuat_kho_cho_nhan_vien_title);
    }

    @Override
    public boolean isShowAddButton() {
        boolean isShow = false;
        switch ((int) Constants.FuntionConstant.ENVIROMENT_STEP) {
            case (int) STEP_WAREHOUSE.STEP_2:
                if (funtions.contains(RoleWareHouse.LAP_PHIEU_XUAT)) isShow = true;
                break;

            case (int) STEP_WAREHOUSE.STEP_3:
                if (funtions.contains(RoleWareHouse.LAP_LENH_XUAT)) {
                    isShow = true;
                }
                break;

            default:
                isShow = false;
                break;
        }
        return isShow;
    }

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void onAddClick() {
        Intent intent;
        if (Constants.FuntionConstant.ENVIROMENT_STEP == STEP_WAREHOUSE.STEP_2) {
            intent = new Intent(XuatKhoChoNhanVienActivity.this,
                    LapPhieu2XuatKhoChoNhanVienActivity.class);
        } else {
            intent = new Intent(XuatKhoChoNhanVienActivity.this,
                    LapLenh3XuatKhoChoNhanVienActivity.class);
        }

        startActivityForResult(intent, REQUEST_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        doSearch();
    }
}
