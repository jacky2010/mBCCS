package com.viettel.mbccs.screen.nhanvientrahang.list;

import android.content.Intent;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.constance.StockTransStatus;
import com.viettel.mbccs.constance.StockTransType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListExpCmdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListExpCmdResponse;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.screen.nhanvientrahang.createNote.LapPhieuXuatTraHangActivity;
import com.viettel.mbccs.screen.warehousecommon.exportsuccess.ExportSuccessDialog;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eo_cuong on 7/5/17.
 */

public class ListNhanVienTraHangActivity extends BaseListOrderActivity {

    public static final int CREATE_NOTE_REQUEST = 1235;

    @Override
    public void doSearch() {
        DataRequest<GetListExpCmdRequest> mDataRequest = new DataRequest<>();
        GetListExpCmdRequest mRequest = new GetListExpCmdRequest();
        showLoading();
        if (getPositionStatus() == 0) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_DONE);
            setItemCountStringFormat(
                    getString(R.string.nv_trahangcaptren_list_label_count_exported));
        }

        if (getPositionStatus() == 1) {
            mRequest.setStockTransStatus(StockTransStatus.TRANS_REJECT);
            setItemCountStringFormat(
                    getString(R.string.nv_trahangcaptren_list_label_count_canceled));
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
    public void onItemStockTransClick(final StockTrans stockTrans) {
        showLoading();
        DataRequest<GetListStockTransDetailRequest> dataRequest = new DataRequest<>();
        GetListStockTransDetailRequest request = new GetListStockTransDetailRequest();
        request.setStockTransId(stockTrans.getStockTransId());
        dataRequest.setWsCode(WsCode.GetListStockTransDetail);
        dataRequest.setWsRequest(request);
        mBanHangKhoTaiChinhRepository.getListStockTransDetail(dataRequest)
                .subscribe(new MBCCSSubscribe<ListStockTransDetailsReponse>() {

                    @Override
                    public void onSuccess(ListStockTransDetailsReponse object) {

                        //fake
                        //object = fakeData();

                        if (object != null && object.getStockTransDetails() != null) {
                            ExportSuccessDialog exportSuccessDialog =
                                    ExportSuccessDialog.newInstance(stockTrans, String.format(
                                            getString(R.string.warehouse_label_export_success_code),
                                            String.valueOf(stockTrans.getStockTransId())),
                                            String.format(
                                                    getString(R.string.warehouse_label_receive),
                                                    String.valueOf(stockTrans.getToOwnerId())));
                            exportSuccessDialog.setOnDialogDismissListener(
                                    new ExportSuccessDialog.OnDialogDismissListener() {

                                        @Override
                                        public void onDialogDissmis() {
                                        }
                                    });
                            exportSuccessDialog.show(getSupportFragmentManager(), "");
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
    public String getItemCountStringFormat() {
        return getString(R.string.nv_trahangcaptren_list_label_count_exported);
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.nv_trahangcaptren_title);
    }

    @Override
    public boolean isShowAddButton() {
        return true;
    }

    @Override
    public void onAddClick() {
        super.onAddClick();
        Intent intent =
                new Intent(ListNhanVienTraHangActivity.this, LapPhieuXuatTraHangActivity.class);
        startActivityForResult(intent, CREATE_NOTE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_NOTE_REQUEST && resultCode == RESULT_OK) {
            doSearch();
        }
    }

    @Override
    public void onItemClicked(Object object) {

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
        return getString(R.string.activity_list_order_return_upper_kho_nhan);
    }

    //fake get detail
    public ListStockTransDetailsReponse fakeData() {

        ListStockTransDetailsReponse reponse = new ListStockTransDetailsReponse();

        List<StockTransDetail> listFake = new ArrayList<>();

        StockTransDetail stockTransDetail1 = new StockTransDetail();
        stockTransDetail1.setQuantity(12);
        stockTransDetail1.setStockModelCode("AA-SS");
        stockTransDetail1.setStockModelId(1000554);
        stockTransDetail1.setStockModelName("SP1");

        StockTransDetail stockTransDetail2 = new StockTransDetail();
        stockTransDetail2.setQuantity(12);
        stockTransDetail2.setStockModelCode("FF-SS");
        stockTransDetail2.setStockModelId(1000554);
        stockTransDetail2.setStockModelName("SP1");

        StockTransDetail stockTransDetail3 = new StockTransDetail();
        stockTransDetail3.setQuantity(18);
        stockTransDetail3.setStockModelCode("CCC-SS");
        stockTransDetail3.setStockModelId(1000554);
        stockTransDetail3.setStockModelName("SP1");

        listFake.add(stockTransDetail1);
        listFake.add(stockTransDetail2);
        listFake.add(stockTransDetail3);
        reponse.setStockTransDetails(listFake);
        return reponse;
    }
}
