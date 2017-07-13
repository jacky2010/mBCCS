package com.viettel.mbccs.screen.nhanvientrahang.createNote;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.constance.StockStateId;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockNotNoteRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.response.BaseCreateCmdNoteResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelAllResponse;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.StockTotalCompare;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
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
    private int positionStatus = 0;

    public LapPhieuXuatTraHangPresenter(Context context,
            LapPhieuXuatTraHangContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mUserRepository = UserRepository.getInstance();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mSubscription = new CompositeSubscription();
        init();
        loadData();
    }

    public void loadData() {
        mViewModel.showLoading();
        DataRequest<GetListStockModelRequest> mGetListStockModelRequestBaseRequest =
                new DataRequest<>();
        mGetListStockModelRequestBaseRequest.setWsCode(WsCode.GetListStockModelAll);
        GetListStockModelRequest request = new GetListStockModelRequest();
        request.setSaleTransType(SaleTranType.SALE_RETAIL);
        request.setOwnerId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        request.setOwnerType(OwnerType.STAFF);
        mGetListStockModelRequestBaseRequest.setWsRequest(request);
        Subscription subscription = mBanHangKhoTaiChinhRepository.getListStockModelAll(
                mGetListStockModelRequestBaseRequest)
                .subscribe(new MBCCSSubscribe<GetListStockModelAllResponse>() {
                    @Override
                    public void onSuccess(GetListStockModelAllResponse object) {
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
                        mUserRepository.getUserInfo().getStaffInfo().getStaffName()));
        mStatusAdapter = new SpinnerAdapter<String>(mContext, mStatus);
        mAdapter = new StockLapPhieuAdapter(mContext, mStockTotals);
        mAdapter.setOnStockLapPhieuListener(this);

        mStockReceiveName.set(mUserRepository.getUserInfo().getShop().getShopName());
        mStatus.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.stock_status)));
        mStatusAdapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionStatus = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
        createExpStockNotNote();
    }

    private void reloadStockListCount() {
        countStock.set(String.format(mContext.getString(R.string.view_ware_house_list_stock),
                mStockTotals.size()));
    }

    private boolean validate() {

        return true;
    }

    private void createExpStockNotNote() {

        mViewModel.showLoading();

        DataRequest<CreateExpStockNotNoteRequest> dataRequest = new DataRequest<>();
        dataRequest.setWsCode(WsCode.CreateExpStockNotNote);
        CreateExpStockNotNoteRequest request = new CreateExpStockNotNoteRequest();
        request.setFromOwnerId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        request.setFromOwnerType(OwnerType.STAFF);
        request.setToOwnerId(mUserRepository.getUserInfo().getShop().getShopId());
        request.setToOwnerType(OwnerType.SHOP);
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        request.setDiscountPolicy(
                Long.valueOf(mUserRepository.getUserInfo().getStaffInfo().getDiscountPolicy()));
        request.setReasonId(Constants.FuntionConstant.STAFF_EXPORT_SHOP_REASON_ID);
        List<StockSerial> stockSerials = new ArrayList<>();
        for (StockTotal stockTotal : mStockTotals) {
            StockSerial stockSerial = stockTotal.getStockSerial();
            stockSerial.setStateId(
                    positionStatus == 0 ? StockStateId.TYPE_NEW : StockStateId.TYPE_OLD);
            if (stockSerial.getQuantity() != 0) {
                stockSerials.add(stockSerial);
            }
        }

        request.setStockSerials(stockSerials);
        dataRequest.setWsRequest(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.createExpStockNotNote(dataRequest).subscribe(

                        new MBCCSSubscribe<BaseCreateCmdNoteResponse>() {
                            @Override
                            public void onSuccess(BaseCreateCmdNoteResponse object) {
                                if (object != null && object.getStockTrans() != null) {
                                    mViewModel.onCreateExpSuccess(mStockTotals,
                                            object.getStockTrans());
                                }
                            }

                            @Override
                            public void onError(BaseException error) {
                                DialogUtils.showDialogError(mContext, error);
                                //fake success
                                //mViewModel.onCreateExpSuccess(mStockTotals);
                            }

                            @Override
                            public void onRequestFinish() {
                                super.onRequestFinish();
                                mViewModel.hideLoading();
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
    public void onClickItem(StockTotal o, int position) {

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
