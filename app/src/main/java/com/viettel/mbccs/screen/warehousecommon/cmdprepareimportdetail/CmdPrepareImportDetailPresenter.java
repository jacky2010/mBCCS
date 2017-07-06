package com.viettel.mbccs.screen.warehousecommon.cmdprepareimportdetail;

import android.content.Context;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.CreateImportStockRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.response.BaseCreateCmdNote;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.screen.warehousecommon.exportwarehouse.StockTransDetailAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by FRAMGIA\hoang.van.cuong on 04/07/2017.
 */

public class CmdPrepareImportDetailPresenter implements CmdPrepareImportDetailContract.Presenter,
        StockTransDetailAdapter.OnStockTransDetailAdapterListener {

    private CmdPrepareImportDetailContract.ViewModel mViewModel;
    private Context mContext;
    public ObservableField<String> title;
    private StockTrans mStockTrans;
    public ObservableField<String> cmdCode;
    public ObservableField<String> receiveWarehouse;
    public ObservableField<String> dayCreated;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private DataRequest<GetListStockTransDetailRequest> mDataRequest;
    private DataRequest<CreateImportStockRequest> mCreateImportStockRequestDataRequest;
    private ArrayList<StockTransDetail> mStockTransDetails = new ArrayList<>();
    private StockTransDetailAdapter mAdapter;
    private CompositeSubscription mSubscription;
    private int currentPosition = -1;

    public CmdPrepareImportDetailPresenter(Context context,
            CmdPrepareImportDetailContract.ViewModel viewModel, StockTrans stockTrans) {
        mContext = context;
        mViewModel = viewModel;
        this.mStockTrans = stockTrans;
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mSubscription = new CompositeSubscription();
        initData();
        loadData();
    }

    private void initData() {
        title = new ObservableField<>();
        title.set(mViewModel.getToolBarTitle());
        cmdCode = new ObservableField<>();
        receiveWarehouse = new ObservableField<>();
        dayCreated = new ObservableField<>();

        cmdCode.set(String.format(
                mContext.getString(R.string.common_cmd_prepare_export_detail_label_cmd_code),
                String.valueOf(mStockTrans.getStockTransId())));

        receiveWarehouse.set(String.format(
                mContext.getString(R.string.common_cmd_prepare_import_label_export_warehouse),
                String.valueOf(mStockTrans.getToOwnerId())));

        dayCreated.set(String.format(
                mContext.getString(R.string.common_cmd_prepare_import_label_day_created),
                mStockTrans.getCreateDatetime()));

        mAdapter = new StockTransDetailAdapter(mContext, mStockTransDetails,
                mContext.getString(R.string.item_view_ware_house_view_serial));
        mAdapter.setOnStockTransAdapterListerner(this);
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

    public void importStock() {

        createImportStock();
    }

    private void createImportStock() {
        mViewModel.showLoading();
        mCreateImportStockRequestDataRequest = new DataRequest<>();
        mCreateImportStockRequestDataRequest.setWsCode(WsCode.CreateExpStock);
        CreateImportStockRequest request = new CreateImportStockRequest();
        request.setStockTransId((mStockTrans.getStockTransId()));
        List<StockSerial> stockSerials = new ArrayList<>();
        for (StockTransDetail stockTransDetail : mStockTransDetails) {
            stockSerials.add(stockTransDetail.getStockSerial());
        }
        mCreateImportStockRequestDataRequest.setWsRequest(request);
        Subscription subcription = mBanHangKhoTaiChinhRepository.createImportStock(
                mCreateImportStockRequestDataRequest).subscribe(

                new MBCCSSubscribe<BaseCreateCmdNote>() {
                    @Override
                    public void onSuccess(BaseCreateCmdNote object) {

                        mViewModel.onCreateImportStockSuccess(mStockTransDetails, mStockTrans);
                    }

                    @Override
                    public void onError(BaseException error) {

                        DialogUtils.showDialog(mContext, null, error.getMessage(), null);
                        mViewModel.onCreateImportStockSuccess(mStockTransDetails, mStockTrans);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });
        mSubscription.add(subcription);
    }

    public void reject() {

    }

    public void onCancel() {

    }

    @Override
    public void onItemClick(int position, StockTransDetail data) {

    }

    @Override
    public void onSerialPickerClick(int position, StockTransDetail stockTransDetail) {
        currentPosition = position;
        mViewModel.onSerialPicker(stockTransDetail);
    }

    @Override
    public void onSerialPickerSuccess(List<String> serials) {
        mStockTransDetails.get(currentPosition).setSerials(serials);
        mAdapter.notifyItemChanged(currentPosition);
    }
}
