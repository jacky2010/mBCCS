package com.viettel.mbccs.screen.nvxacnhanhang;

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
import com.viettel.mbccs.screen.warehousecommon.exportsuccess.ExportSuccessDialog;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.viettel.mbccs.screen.xuatkhochonhanvien.XuatKhoChoNhanVienActivity.REQUEST_CODE;

public class NvXacNhanHangActivity extends BaseListOrderActivity {
    private List<String> funtions = new ArrayList<>();
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
            mRequest.setStockTransType(StockTransType.TRANS_EXPORT);
        }

        if (getPositionStatus() == 1) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_DONE);
            mRequest.setStockTransType(StockTransType.TRANS_IMPORT);
        }

        if (getPositionStatus() == 1) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_CANCEL);
            mRequest.setStockTransType(StockTransType.TRANS_IMPORT);
        }

        mRequest.setStartDate(getFromDateString());
        mRequest.setEndDate(getToDateString());
        mRequest.setFromOwnerId(mUserRepository.getUserInfo().getShop().getShopId());
        mRequest.setFromOwnerType(OwnerType.SHOP);
        mRequest.setToOwnerId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        mRequest.setToOwnerType(OwnerType.STAFF);
        mDataRequest.setWsCode(WsCode.GetListExpCmd);
        mDataRequest.setWsRequest(mRequest);
        mBanHangKhoTaiChinhRepository.getListExpCmd(mDataRequest)
                .subscribe(new MBCCSSubscribe<GetListExpCmdResponse>() {
                    @Override
                    public void onSuccess(GetListExpCmdResponse object) {
                        if (object != null && object.getStockTranses() != null) {
                            for (StockTrans stockTrans : object.getStockTranses()) {
                                stockTrans.setActionName(
                                        getString(R.string.xuat_kho_cho_nhan_vien_chi_tiet));

                                switch ((int) stockTrans.getStockTransStatus()) {
                                    case (int) StockTransStatus.TRANS_DONE:
                                        if (funtions.contains(RoleWareHouse.NHAP_KHO)
                                                && stockTrans.getActionType()
                                                == StockTransType.TRANS_EXPORT) {
                                            stockTrans.setActionName(
                                                    getString(R.string.nv_xac_nhan_hang_nhap_kho));
                                        }
                                        break;
                                    default:
                                        stockTrans.setActionName(getString(
                                                R.string.xuat_kho_cho_nhan_vien_chi_tiet));
                                        break;
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
        Bundle bundle = new Bundle();
        String cmdCodeTitle = "";
        String cmdNameTitle = "";
        String title = "";
        switch ((int) stockTrans.getStockTransStatus()) {
            case (int) StockTransStatus.TRANS_DONE:
                if (stockTrans.getActionType() == StockTransType.TRANS_EXPORT) {
                    if (funtions.contains(RoleWareHouse.NHAP_KHO)) {
                        intent = new Intent(this, NvXacNhanHangActivity.class);
                        bundle.putBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY, false);
                    } else {
                        intent = new Intent(this, NvXacNhanHangActivity.class);
                        bundle.putBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY, true);
                    }
                } else {
                    title = getString(R.string.nv_xac_nhan_hang_chi_tiet_phieu_nhap);
                    cmdCodeTitle = getString(R.string.nv_xac_nhan_hang_chi_tiet_phieu_nhap_s,
                            String.valueOf(stockTrans.getStockTransId()));
                }

                break;

            default:
                intent = new Intent(this, NvXacNhanHangActivity.class);
                bundle.putBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY, true);
                break;
        }

        if (intent == null) {
            cmdNameTitle = getString(R.string.nv_xac_nhan_hang_nhan_vien_nhan,
                    String.valueOf(stockTrans.getToOwnerId()));
            ExportSuccessDialog exportSuccessDialog =
                    ExportSuccessDialog.newInstance(stockTrans, title, cmdCodeTitle, cmdNameTitle);
            exportSuccessDialog.setOnDialogDismissListener(
                    new ExportSuccessDialog.OnDialogDismissListener() {
                        @Override
                        public void onDialogDismiss() {
                        }
                    });
            exportSuccessDialog.show(getSupportFragmentManager(), "");
        } else {

            bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_CODE);
        }
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
        setStatus(Arrays.asList(getResources().getStringArray(R.array.nhan_vien_xav_nhan_hang)));
        setWareHouseData(Arrays.asList(
                String.valueOf(mUserRepository.getUserInfo().getShop().getShopName())));
        funtions = mUserRepository.getFunctionsCodes();
    }

    @Override
    public String getWareHouseTitle() {
        return getString(R.string.nv_xac_nhan_hang_ma_kho_xuat);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            doSearch();
        }
    }
}
