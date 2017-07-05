package com.viettel.mbccs.screen.nhanvientrahang;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockNotHaveCmdRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.StockTotalCompare;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by FRAMGIA\hoang.van.cuong on 28/06/2017.
 */

public class LapPhieuXuatTraHangPresenter implements LapPhieuXuatTraHangContract.Presenter,
        StockLapPhieuAdapter.OnStockLapPhieuListener {

    private Context mContext;
    private LapPhieuXuatTraHangContract.ViewModel mViewModel;
    private StockLapPhieuAdapter mAdapter;
    private ArrayList<StockTotal> mStockTotals = new ArrayList<>();
    public ObservableField<String> titleExportFrom;
    public ObservableField<String> mStockReceiveName;
    public ObservableField<String> countStock;
    private SpinnerAdapter<String> mStatusAdapter;
    private List<String> mStatus = new ArrayList<>();
    private UserRepository mUserRepository;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private CompositeSubscription mSubscription;
    private int currentPosition = -1;

    public LapPhieuXuatTraHangPresenter(Context context,
            LapPhieuXuatTraHangContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mUserRepository = UserRepository.getInstance();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mSubscription = new CompositeSubscription();
        init();
        fake();
        loadData();
    }

    private void fake() {
        mStockReceiveName.set(mUserRepository.getUserInfo().getChannelInfo().getManagementName());
        mStatus.addAll(Arrays.asList("NEW", "OLD"));
    }

    public void loadData() {
        DataRequest<GetListStockModelRequest> mGetListStockModelRequestBaseRequest =
                new DataRequest<>();
        mGetListStockModelRequestBaseRequest.setWsCode(WsCode.GetListStockModel);
        GetListStockModelRequest request = new GetListStockModelRequest();
        //request.setStockTypeId(StockTotalType.TYPE_NEW);
        //request.setStateId(StockTotalType.TYPE_NEW);
        request.setOwnerType(2L);
        request.setSaleTransType(SaleTranType.SALE_CHANNEL);
        request.setOwnerId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        mGetListStockModelRequestBaseRequest.setWsRequest(request);
        mViewModel.showLoading();
        Subscription subscription = mBanHangKhoTaiChinhRepository.getListStockModel(
                mGetListStockModelRequestBaseRequest)
                .subscribe(new MBCCSSubscribe<GetListStockModelResponse>() {
                    @Override
                    public void onSuccess(GetListStockModelResponse object) {
                        if (object != null && object.getStockTotalList() != null) {
                            if (object.getStockTotalList().size() == 0) {
                                DialogUtils.showDialog(mContext, R.string.common_msg_no_data);
                            }
                            mStockTotals.clear();
                            mStockTotals.addAll(object.getStockTotalList());
                            mAdapter.notifyDataSetChanged();
                            reloadStockListCount();
                            return;
                        }
                        DialogUtils.showDialog(mContext, R.string.common_msg_no_data);
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialog(mContext, null, error.getMessage(), null);
                        //fake();
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                        ActivityUtils.hideKeyboard((Activity) mContext);
                    }
                });

        mSubscription.add(subscription);
    }

    private void init() {
        mStockReceiveName = new ObservableField<>();
        countStock = new ObservableField<>();
        titleExportFrom = new ObservableField<>();
        titleExportFrom.set(
                String.format(mContext.getString(R.string.nhanvien_xuattra_label_export_from),
                        mUserRepository.getUserInfo().getChannelInfo().getChannelName()));
        mStatusAdapter = new SpinnerAdapter<String>(mContext, mStatus);
        mAdapter = new StockLapPhieuAdapter(mContext, mStockTotals);
        mAdapter.setOnStockLapPhieuListener(this);
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mSubscription.clear();
    }

    public void addClick() {
        mViewModel.goGoStockPicker(mStockTotals);
    }

    public void export() {
        if (!validate()) {
            return;
        }
        createExpStockNotHaveCmd();
    }

    private void reloadStockListCount() {
        countStock.set(String.format(mContext.getString(R.string.view_ware_house_list_stock),
                mStockTotals.size()));
    }

    private boolean validate() {

        return true;
    }

    private void createExpStockNotHaveCmd() {

        DataRequest<CreateExpStockNotHaveCmdRequest> dataRequest = new DataRequest<>();
        dataRequest.setWsCode(WsCode.CreateExpStockNotHaveCmd);
        CreateExpStockNotHaveCmdRequest request = new CreateExpStockNotHaveCmdRequest();
        request.setFromOwnerId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        //TODO define fromOwnerTypeID
        request.setFromOwnerType(mUserRepository.getUserInfo().getStaffInfo().getChannelTypeId());
        request.setToOwnerId(mUserRepository.getUserInfo().getChannelInfo().getChannelId());
        request.setToOwnerType(mUserRepository.getUserInfo().getChannelInfo().getChannelType());
        request.setStockTransCode("");
        //        request.setReasonId();
        List<StockSerial> stockSerials = new ArrayList<>();
        for (StockTotal stockTotal : mStockTotals) {
            stockSerials.add(stockTotal.getStockSerial());
        }
        request.setStockSerials(stockSerials);
        dataRequest.setWsRequest(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.createExpStockNotHaveCmd(dataRequest).subscribe(

                        new MBCCSSubscribe<EmptyObject>() {
                            @Override
                            public void onSuccess(EmptyObject object) {
                                mViewModel.onCreateExpSuccess(mStockTotals);
                            }

                            @Override
                            public void onError(BaseException error) {
                                DialogUtils.showDialogError(mContext, error);
                                //fake success
                                mViewModel.onCreateExpSuccess(mStockTotals);
                            }
                        });

        mSubscription.add(subscription);
    }

    public StockLapPhieuAdapter getAdapter() {
        return mAdapter;
    }

    public SpinnerAdapter<String> getStatusAdapter() {
        return mStatusAdapter;
    }

    @Override
    public void onSerialPickerSuccess(List<String> serials) {
        mStockTotals.get(currentPosition).setSerials(serials);
    }

    @Override
    public void pickStockTotalListSuccess(List<StockTotal> stockTotals) {
        for (StockTotal stockTotal : stockTotals) {
            mergeStockTotalList(stockTotal);
        }
        Collections.sort(mStockTotals, new StockTotalCompare());
        mAdapter.notifyDataSetChanged();
    }

    public void mergeStockTotalList(StockTotal stockTotal) {
        for (int i = 0; i < mStockTotals.size(); i++) {
            if (mStockTotals.get(i).equals(stockTotal)) {
                mStockTotals.get(i).setCountChoice(stockTotal.getCountChoice());
                return;
            }
        }
        mStockTotals.add(stockTotal);
    }

    @Override
    public void onClickItem(Object o, int position) {

    }

    @Override
    public void onSerialPickerClick(int position, StockTotal stockTotal) {
        currentPosition = position;
        mViewModel.onSerialPicker(stockTotal);
    }

    @Override
    public void onRemoveStock() {
        reloadStockListCount();
    }
}
