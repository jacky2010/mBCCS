package com.viettel.mbccs.screen.warehousecommon.importcmdnotestock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.createorder.BaseCreateOrderPresenter;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.CreateImportCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateImportNoteRequest;
import com.viettel.mbccs.data.source.remote.request.CreateImportStockRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.DestroyStockTransRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.response.BaseCreateCmdNoteResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.screen.nhapkhocapduoi.adapters.ListGoodsDetailAdapter;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 7/7/17.
 */

public class BaseCreateImportWareHousePresenter extends BaseCreateOrderPresenter<StockTransDetail> {

    private BaseCreateImportWareHouseContract.ViewModel viewModel;
    private StockTrans mStockTrans;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private UserRepository mUserRepository;
    private CompositeSubscription mSubscription;
    private ListGoodsDetailAdapter adapter;

    public BaseCreateImportWareHousePresenter(Context context,
            BaseCreateImportWareHouseContract.ViewModel viewModel) {
        super(context, viewModel);
        this.viewModel = (BaseCreateImportWareHouseContract.ViewModel) mViewModel;
    }

    public BaseCreateImportWareHousePresenter(Context context,
            BaseCreateImportWareHouseContract.ViewModel viewModel, StockTrans stockTrans) {
        super(context, viewModel);
        this.viewModel = (BaseCreateImportWareHouseContract.ViewModel) mViewModel;
        mStockTrans = stockTrans;
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        mSubscription = new CompositeSubscription();
        init();
        getStockTransDetail();
    }

    private void init() {
        switch (viewModel.getActionType()) {
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_CMD:
                setButtonTitle(mContext.getString(R.string.commmon_warehouse_action_create_cmd));
                break;
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_NOTE:
                setButtonTitle(mContext.getString(R.string.commmon_warehouse_action_create_note));
                break;
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_IMPORT:
                setButtonTitle(mContext.getString(R.string.commmon_warehouse_action_create_import));
                break;
        }
    }

    @Override
    public void reject() {
        viewModel.onReject();
    }

    @Override
    public void create() {
        viewModel.onCreate();
    }

    @Override
    public void rejectCmd(String input) {
        viewModel.showLoading();
        DataRequest<DestroyStockTransRequest> dataRequest = new DataRequest<>();
        DestroyStockTransRequest request = new DestroyStockTransRequest();
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        request.setStockTransId(mStockTrans.getStockTransId());
        request.setNote(input);
        dataRequest.setWsCode(WsCode.DestroyStockTrans);
        dataRequest.setWsRequest(request);
        mBanHangKhoTaiChinhRepository.destroyStockTrans(dataRequest)
                .subscribe(new MBCCSSubscribe<EmptyObject>() {

                    @Override
                    public void onSuccess(EmptyObject object) {
                        ((BaseCreateImportWareHouseActivity) mContext).finish();
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialogError(mContext, error);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        viewModel.hideLoading();
                    }
                });
    }

    @Override
    public void approveCmd() {
        switch (viewModel.getActionType()) {
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_CMD:
                createCmd();
                break;
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_NOTE:
                if (viewModel.getStep() == BaseCreateImportWareHouseActivity.STEP_2) {
                    createNoteNoCMD();
                } else {
                    createNote();
                }

                break;
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_IMPORT:
                createImport();
                break;
        }
    }

    private void createCmd() {
        viewModel.showLoading();
        DataRequest<CreateImportCmdRequest> dataRequest = new DataRequest<>();
        CreateImportCmdRequest request = new CreateImportCmdRequest();
        request.setStockTransId(mStockTrans.getStockTransId());
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        dataRequest.setWsCode(WsCode.CreateCmd);
        dataRequest.setWsRequest(request);
        mBanHangKhoTaiChinhRepository.createImportCmd(dataRequest)
                .subscribe(new MBCCSSubscribe<BaseCreateCmdNoteResponse>() {

                    @Override
                    public void onSuccess(BaseCreateCmdNoteResponse object) {
                        //fake
                        if (object != null && object.getStockTrans() != null) {
                            viewModel.createCmdNoteStockSuccess(object.getStockTrans());
                        }
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialog(mContext, error.getMessage());
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        viewModel.hideLoading();
                    }
                });
    }

    private void createNote() {
        viewModel.showLoading();
        DataRequest<CreateImportNoteRequest> dataRequest = new DataRequest<>();
        CreateImportNoteRequest request = new CreateImportNoteRequest();
        request.setStockTransId(mStockTrans.getStockTransId());
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        dataRequest.setWsCode(WsCode.CreateImpNote);
        dataRequest.setWsRequest(request);
        mBanHangKhoTaiChinhRepository.createImportNote(dataRequest)
                .subscribe(new MBCCSSubscribe<BaseCreateCmdNoteResponse>() {

                    @Override
                    public void onSuccess(BaseCreateCmdNoteResponse object) {
                        //fake
                        if (object != null && object.getStockTrans() != null) {
                            viewModel.createCmdNoteStockSuccess(object.getStockTrans());
                        }
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialog(mContext, error.getMessage());
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        viewModel.hideLoading();
                    }
                });
    }

    private void createNoteNoCMD() {
        viewModel.showLoading();
        DataRequest<CreateImportNoteRequest> dataRequest = new DataRequest<>();
        CreateImportNoteRequest request = new CreateImportNoteRequest();
        request.setStockTransId(mStockTrans.getStockTransId());
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        dataRequest.setWsCode(WsCode.CreateImpNoteNoCMD);
        dataRequest.setWsRequest(request);
        mBanHangKhoTaiChinhRepository.createImportNote(dataRequest)
                .subscribe(new MBCCSSubscribe<BaseCreateCmdNoteResponse>() {

                    @Override
                    public void onSuccess(BaseCreateCmdNoteResponse object) {
                        //fake
                        if (object != null && object.getStockTrans() != null) {
                            viewModel.createCmdNoteStockSuccess(object.getStockTrans());
                        }
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialog(mContext, error.getMessage());
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        viewModel.hideLoading();
                    }
                });
    }

    private void createImport() {
        viewModel.showLoading();
        DataRequest<CreateImportStockRequest> dataRequest = new DataRequest<>();
        CreateImportStockRequest request = new CreateImportStockRequest();
        request.setStockTransId(mStockTrans.getStockTransId());
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        dataRequest.setWsCode(WsCode.CreateImpStock);
        dataRequest.setWsRequest(request);
        mBanHangKhoTaiChinhRepository.createImportStock(dataRequest)
                .subscribe(new MBCCSSubscribe<BaseCreateCmdNoteResponse>() {

                    @Override
                    public void onSuccess(BaseCreateCmdNoteResponse object) {
                        //fake
                        if (object != null && object.getStockTrans() != null) {
                            viewModel.createCmdNoteStockSuccess(object.getStockTrans());
                        }
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialog(mContext, error.getMessage());
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        viewModel.hideLoading();
                    }
                });
    }

    private void getStockTransDetail() {
        DataRequest mDataRequest = new DataRequest<>();
        mDataRequest.setWsCode(WsCode.GetListStockTransDetail);
        GetListStockTransDetailRequest request = new GetListStockTransDetailRequest();
        request.setStockTransId(mStockTrans.getStockTransId());
        mDataRequest.setWsRequest(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.getListStockTransDetail(mDataRequest).subscribe(

                        new MBCCSSubscribe<ListStockTransDetailsReponse>() {
                            @Override
                            public void onSuccess(ListStockTransDetailsReponse object) {
                                if (object != null && object.getStockTransDetails() != null) {
                                    bindData(object);
                                }

                                //fake
                                bindData(fakeData());
                            }

                            @Override
                            public void onError(BaseException error) {
                                //                                DialogUtils.showDialog
                                // (mContext, error.getMessage());
                                bindData(fakeData());
                            }
                        });
        mSubscription.add(subscription);
    }

    //fake data
    public ListStockTransDetailsReponse fakeData() {

        StockSerial stockSerial = new StockSerial();
        List<SerialBO> serialBOs = new ArrayList<>();
        serialBOs.add(new SerialBO("111111", "111115"));
        serialBOs.add(new SerialBO("111117", "111119"));
        stockSerial.setSerialBOs(serialBOs);

        ListStockTransDetailsReponse reponse = new ListStockTransDetailsReponse();

        List<StockTransDetail> listFake = new ArrayList<>();

        StockTransDetail stockTransDetail1 = new StockTransDetail();
        stockTransDetail1.setQuantity(12);
        stockTransDetail1.setStockModelCode("AA-SS");
        stockTransDetail1.setStockModelId(1000554);
        stockTransDetail1.setStockModelName("SP1");
        stockTransDetail1.setStockSerial(stockSerial);

        StockTransDetail stockTransDetail2 = new StockTransDetail();
        stockTransDetail2.setQuantity(12);
        stockTransDetail2.setStockModelCode("FF-SS");
        stockTransDetail2.setStockModelId(1000554);
        stockTransDetail2.setStockModelName("SP1");
        stockTransDetail2.setStockSerial(stockSerial);

        StockTransDetail stockTransDetail3 = new StockTransDetail();
        stockTransDetail3.setQuantity(18);
        stockTransDetail3.setStockModelCode("CCC-SS");
        stockTransDetail3.setStockModelId(1000554);
        stockTransDetail3.setStockModelName("SP1");
        stockTransDetail3.setStockSerial(stockSerial);

        listFake.add(stockTransDetail1);
        listFake.add(stockTransDetail2);
        listFake.add(stockTransDetail3);
        reponse.setStockTransDetails(listFake);
        return reponse;
    }

    private void bindData(ListStockTransDetailsReponse object) {
        mList.clear();
        mList.addAll(object.getStockTransDetails());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void subscribe() {
        super.subscribe();
    }

    @Override
    public void unSubscribe() {
        super.unSubscribe();
        mSubscription.clear();
    }

    @Override
    public boolean getShowButton() {
        return true;
    }

    @Override
    public String getToolbarTitle() {
        return viewModel.getTitleToolbarHeader();
    }

    @Override
    public String getOrderCode() {
        return mContext.getString(R.string.activity_create_order_ma_lenh,
                String.valueOf(mStockTrans.getStockTransId()));
    }

    @Override
    public String getImportWarehouseCode() {
        return "";
    }

    @Override
    public String getExportWarehouseCode() {
        return mContext.getString(R.string.activity_create_order_success_kho_xuat,
                String.valueOf(mStockTrans.getFromOwnerId()));
    }

    @Override
    public String getCreatedDate() {
        return mContext.getString(R.string.activity_create_order_success_ngay_lap_lenh_xuat_kho,
                DateUtils.convertStringToStringFormat(mStockTrans.getCreateDatetime(),
                        DateUtils.DATE_FORMAT1));
    }

    @Override
    public RecyclerView.Adapter getListAdapter() {
        adapter = new ListGoodsDetailAdapter(mContext, mList);
        return adapter;
    }
}
