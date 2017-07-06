package com.viettel.mbccs.screen.stockdeliver.billinput;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.screen.stockdeliver.billinput.adapter.StockTransDetailAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseBillInputDetailPresenter implements BaseBillInputDetailContract.Presenter,
        StockTransDetailAdapter.OnSerialDetailAdapterListener {
    private Context mContext;
    private BaseBillInputDetailContract.ViewModel mViewModel;
    private StockTrans mStockTrans;
    public ObservableField<String> cmdCode;
    public ObservableField<String> receiveWarehouse;
    public ObservableField<String> dayCreated;
    public ObservableField<String> status;
    public ObservableField<String> screenTitle;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private DataRequest<GetListStockTransDetailRequest> mDataRequest;
    private DataRequest<CreateExpStockRequest> mCreateExpStockRequest;
    private ArrayList<StockTransDetail> mStockTransDetails = new ArrayList<>();
    private StockTransDetailAdapter mAdapter;
    private CompositeSubscription mSubscription;
    private int currentPosition = -1;
    public ObservableField<Drawable> iconLeft;

    public BaseBillInputDetailPresenter(Context context,
            BaseBillInputDetailContract.ViewModel viewModel, StockTrans stockTrans) {
        mContext = context;
        mViewModel = viewModel;
        this.mStockTrans = stockTrans;
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mSubscription = new CompositeSubscription();
        initData();
        loadData();
    }

    private void initData() {
        cmdCode = new ObservableField<>();
        receiveWarehouse = new ObservableField<>();
        dayCreated = new ObservableField<>();
        status = new ObservableField<>();
        iconLeft = new ObservableField<>();
        screenTitle = new ObservableField<>();
        screenTitle.set(
                mContext.getString(R.string.xuatkhochonhanvien_lablel_chi_tiet_xuat_kho_title));
        cmdCode.set(String.format(
                mContext.getString(R.string.common_cmd_prepare_export_detail_label_cmd_code),
                String.valueOf(mStockTrans.getStockTransId())));

        receiveWarehouse.set(String.format(mContext.getString(
                R.string.common_cmd_prepare_export_detail_label_receive_warehouse),
                String.valueOf(mStockTrans.getToOwnerId())));

        dayCreated.set(String.format(
                mContext.getString(R.string.common_cmd_prepare_export_detail_label_day_cmd_created),
                mStockTrans.getCreateDatetime()));

        status.set(String.format(
                mContext.getString(R.string.common_cmd_prepare_export_detail_label_status),
                mStockTrans.getStockTransStatusName()));

        mAdapter = new StockTransDetailAdapter(mContext, mStockTransDetails);
        mAdapter.setOnSerialDetailAdapterListener(this);
    }

    private void loadData() {
        mDataRequest = new DataRequest<>();
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

    private void bindData(ListStockTransDetailsReponse object) {
        mStockTransDetails.clear();
        mStockTransDetails.addAll(object.getStockTransDetails());
        mAdapter.notifyDataSetChanged();
    }

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
        mStockTransDetails.clear();
        mStockTransDetails.addAll(listFake);
        reponse.setStockTransDetails(listFake);
        return reponse;
    }

    public StockTransDetailAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {
        mSubscription.clear();
    }

    public void export() {

        createExpStock();
    }

    private void createExpStock() {
        if (mViewModel.getActionMode().equals(BaseBillInputDetaiActivity.INPUT_BILL_MODE)) {
            DialogUtils.showDialogStyle(mContext,
                    R.string.activity_create_ticket_return_upper_lap_phieu,
                    R.string.xuatkhocapduoi_message_create_bill_input_store, R.string.order2,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startCreateInputCommand();
                        }
                    }, R.string.common_label_close, null);
            return;
        }

        DialogUtils.showDialogStyle(mContext, R.string.dialog_text_input_store,
                R.string.xuatkhocapduoi_message_input_store, R.string.order2,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startCreateInputCommand();
                    }
                }, R.string.common_label_close, null);
        if (!validate()) {
            return;
        }
    }

    private void startCreateInputCommand() {
        mViewModel.showLoading();
        mCreateExpStockRequest = new DataRequest<>();
        mCreateExpStockRequest.setWsCode(WsCode.CreateExpStock);
        CreateExpStockRequest request = new CreateExpStockRequest();
        request.setStockTransId(mStockTrans.getStockTransId());
        List<StockSerial> stockSerials = new ArrayList<>();
        for (StockTransDetail stockTransDetail : mStockTransDetails) {
            stockSerials.add(stockTransDetail.getStockSerial());
        }
        request.setStockSerials(stockSerials);
        mCreateExpStockRequest.setWsRequest(request);
        Subscription subcription =
                mBanHangKhoTaiChinhRepository.createExpStock(mCreateExpStockRequest).subscribe(

                        new MBCCSSubscribe<EmptyObject>() {
                            @Override
                            public void onSuccess(EmptyObject object) {

                                mViewModel.onCreateExpStockSuccess(mStockTransDetails);
                                billCreateSuccess();
                            }

                            @Override
                            public void onError(BaseException error) {

                                DialogUtils.showDialog(mContext, null, error.getMessage(), null);
                                mViewModel.onCreateExpStockSuccess(mStockTransDetails);
                                billCreateSuccess();
                            }

                            @Override
                            public void onRequestFinish() {
                                super.onRequestFinish();
                                mViewModel.hideLoading();
                            }
                        });
        mSubscription.add(subcription);
    }

    public boolean validate() {
        for (StockTransDetail stockTransDetail : mStockTransDetails) {
            if (!stockTransDetail.isPickSerialOk()) {
                DialogUtils.showDialog(mContext, String.format(mContext.getString(
                        R.string.common_cmd_prepare_export_detail_msg_error_choose_serial),
                        stockTransDetail.getStockModelName()));
                return false;
            }
        }

        return true;
    }

    public void reject() {

    }

    public void onCancel() {

    }

    @Override
    public void onItemClick(int position, StockSerial data) {

    }

    @Override
    public void onSerialPickerSuccess(List<String> serials) {
        mStockTransDetails.get(currentPosition).setSerials(serials);
        mAdapter.notifyItemChanged(currentPosition);
    }

    private void billCreateSuccess() {
        iconLeft.set(mContext.getResources().getDrawable(R.drawable.ic_close_white));
        screenTitle.set(mContext.getString(R.string.config_notification));
    }

    @Override
    public void onViewSerialDetail(StockSerial stockSerial) {
        mViewModel.viewSerialDetail(stockSerial);
    }
}
