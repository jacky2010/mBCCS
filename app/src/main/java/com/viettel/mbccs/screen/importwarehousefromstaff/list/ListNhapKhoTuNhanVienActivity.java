package com.viettel.mbccs.screen.importwarehousefromstaff.list;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.constance.OwnerType;
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
import com.viettel.mbccs.screen.importwarehousefromstaff.importnote.CreateCmdFromStaffActivity;
import com.viettel.mbccs.screen.importwarehousefromstaff.importnote
        .CreateImportStockFromStaffActivity;
import com.viettel.mbccs.screen.importwarehousefromstaff.importnote.CreateNoteFromStaffActivity;
import com.viettel.mbccs.screen.importwarehousefromstaff.importnote
        .CreateNoteNoCmdStockFromStaffActivity;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eo_cuong on 7/6/17.
 */

public class ListNhapKhoTuNhanVienActivity extends BaseListOrderActivity {

    public static final int CODE = 123;

    List<String> staffListName = new ArrayList<>();
    List<OwnerCode> mOwnerCodes = new ArrayList<>();

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void doSearch() {
        if (mOwnerCodes.size() == 0) {
            DialogUtils.showDialogError(ListNhapKhoTuNhanVienActivity.this,
                    new BaseException(BaseException.Type.UNEXPECTED, new Throwable()));
            return;
        }

        DataRequest<GetListExpCmdRequest> mDataRequest = new DataRequest<>();
        GetListExpCmdRequest mRequest = new GetListExpCmdRequest();
        showLoading();
        if (Constants.FuntionConstant.IMPORT_FROM_STAFF_STEP == 2) {
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
        } else {
            if (getPositionStatus() == 0) {
                mRequest.setStockTransStatus(StockTransStatus.TRANS_DONE);
                mRequest.setStockTransType(StockTransType.TRANS_EXPORT);
            }

            if (getPositionStatus() == 1) {
                mRequest.setStockTransStatus(StockTransStatus.TRANS_NON_NOTE);
                mRequest.setStockTransType(StockTransType.TRANS_IMPORT);
            }

            if (getPositionStatus() == 2) {
                mRequest.setStockTransStatus(StockTransStatus.TRANS_NOTED);
                mRequest.setStockTransType(StockTransType.TRANS_IMPORT);
            }

            if (getPositionStatus() == 3) {
                mRequest.setStockTransStatus(StockTransStatus.TRANS_DONE);
                mRequest.setStockTransType(StockTransType.TRANS_IMPORT);
            }

            if (getPositionStatus() == 4) {
                mRequest.setStockTransStatus(StockTransStatus.TRANS_CANCEL);
                mRequest.setStockTransType(StockTransType.TRANS_IMPORT);
            }
        }

        mRequest.setStartDate(getFromDateString());
        mRequest.setEndDate(getToDateString());
        mRequest.setFromOwnerId(mOwnerCodes.get(getPositionWareHouser()).getStaffId());
        mRequest.setFromOwnerType(OwnerType.STAFF);
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
                                //set action
                                switch ((int) stockTrans.getStockTransStatus()) {
                                    case (int) StockTransStatus.TRANS_DONE:
                                        if (Constants.FuntionConstant.IMPORT_FROM_STAFF_STEP == 3) {
                                            stockTrans.setActionName(getString(
                                                    R.string.commmon_warehouse_action_create_cmd));
                                        } else {
                                            stockTrans.setActionName(getString(
                                                    R.string.commmon_warehouse_action_create_note));
                                        }

                                        if (stockTrans.getStockTransType()
                                                == StockTransType.TRANS_IMPORT) {
                                            stockTrans.setActionName(getString(
                                                    R.string.nv_trahangcaptren_action_detail));
                                        }

                                        break;
                                    case (int) StockTransStatus.TRANS_NON_NOTE:
                                        stockTrans.setActionName(getString(
                                                R.string.commmon_warehouse_action_create_note));
                                        break;
                                    case (int) StockTransStatus.TRANS_NOTED:
                                        stockTrans.setActionName(getString(
                                                R.string.commmon_warehouse_action_create_import));
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
    public void onItemStockTransClick(StockTrans stockTrans) {
        Intent intent;
        switch ((int) stockTrans.getStockTransStatus()) {
            case (int) StockTransStatus.TRANS_DONE:
                if (Constants.FuntionConstant.IMPORT_FROM_STAFF_STEP == 3) {
                    intent = new Intent(this, CreateCmdFromStaffActivity.class);
                } else {
                    intent = new Intent(this, CreateNoteNoCmdStockFromStaffActivity.class);
                }

                break;
            case (int) StockTransStatus.TRANS_NON_NOTE:
                intent = new Intent(this, CreateNoteFromStaffActivity.class);
                break;
            case (int) StockTransStatus.TRANS_NOTED:
                intent = new Intent(this, CreateImportStockFromStaffActivity.class);
                break;
            case (int) StockTransStatus.TRANS_EXP_IMPED:
                intent = new Intent(this, CreateImportStockFromStaffActivity.class);
                break;
            default:
                return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
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
    public String getItemCountStringFormat() {
        return getString(R.string.nhapkhonhanvien_count_noted);
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.nhapkhonhanvien_list_title);
    }

    @Override
    public boolean isShowAddButton() {
        return false;
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
                            mOwnerCodes.addAll(object.getOwnerCodes());
                            for (OwnerCode ownerCode : mOwnerCodes) {
                                staffListName.add(ownerCode.getName());
                            }
                            setWareHouseData(staffListName);
                        }
                    }

                    @Override
                    public void onError(BaseException error) {

                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        hideLoading();
                    }
                });
    }

    @Override
    public void init() {
        if (Constants.FuntionConstant.IMPORT_FROM_STAFF_STEP == 2) {
            setStatus(Arrays.asList(
                    getResources().getStringArray(R.array.import_from_staff_2_step_status)));
        } else {
            setStatus(Arrays.asList(
                    getResources().getStringArray(R.array.import_from_staff_3_step_status)));
        }

        loadStaffList();
    }

    @Override
    public String getWareHouseTitle() {
        return getString(R.string.activity_list_order_return_upper_kho_xuat);
    }
}
