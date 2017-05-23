package com.viettel.mbccs.screen.kpp.order.addnew;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 5/22/17.
 */

public class AddNewOrderPresenter implements AddNewOrderContract.Presenter {

    public ObservableField<String> titleOrder;
    public ObservableField<String> amount;
    private Context mContext;
    private AddNewOrderContract.ViewModel mViewModel;
    private StockTotalAdapter mAdapter;
    private CompositeSubscription mCompositeSubscription;
    private ArrayList<StockTotal> mStockTotals = new ArrayList<>();
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private BaseRequest<KPPOrderRequest> mKPPOrderRequestBaseRequest;

    public AddNewOrderPresenter(Context context, AddNewOrderContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mCompositeSubscription = new CompositeSubscription();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        init();
        loadData();
    }

    private void init() {
        titleOrder = new ObservableField<>();
        amount = new ObservableField<>();
        mAdapter = new StockTotalAdapter(mContext, mStockTotals);
    }

    private void loadData() {
        //  fakeData();
    }

    private void fakeData() {
        StockTotal stock1 = new StockTotal();
        StockTotal stock2 = new StockTotal();
        StockTotal stock3 = new StockTotal();
        StockTotal stock4 = new StockTotal();
        mStockTotals.add(stock1);
        mStockTotals.add(stock2);
        mStockTotals.add(stock3);
        mStockTotals.add(stock4);
        mAdapter.notifyDataSetChanged();
    }

    public void cancelClick() {
        ((Activity) mContext).finish();
    }

    public void orderClick() {
        DialogUtils.showDialog(mContext, R.string.confirm, R.string.confirm_kpp_order,
                R.string.order2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createOrder();
                    }
                }, R.string.close, null);
    }

    private void createOrder() {
        mViewModel.showLoading();
        mKPPOrderRequestBaseRequest = new BaseRequest<>();
        mKPPOrderRequestBaseRequest.setWsCode(WsCode.CreateSaleOrders);
        KPPOrderRequest request = new KPPOrderRequest();
        request.setStaffId(1);
        request.setChannelStaffId(1);
        request.setListStockModel(Common.convertStockTotalsToStockModels(mStockTotals));
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.createSaleOrders(mKPPOrderRequestBaseRequest)
                        .subscribe(new MBCCSSubscribe<BaseResponse>() {
                            @Override
                            public void onSuccess(BaseResponse object) {
                                mViewModel.gotoSuccessScreen(mStockTotals);
                            }

                            @Override
                            public void onError(BaseException error) {
//                                DialogUtils.showDialogError(mContext, null, error.getMessage(),
//                                        null);
                                mViewModel.gotoSuccessScreen(mStockTotals);
                            }

                            @Override
                            public void onRequestFinish() {
                                super.onRequestFinish();
                                mViewModel.hideLoading();
                            }
                        });

        //TODO fake

    }

    public void addNewStock() {

        mViewModel.goGoStockPicker();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mCompositeSubscription.clear();
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }

    public StockTotalAdapter getAdapter() {
        return mAdapter;
    }

    public void mergeStockTotalList(StockTotal stockTotal) {
        for (int i = 0; i < mStockTotals.size(); i++) {
            if (mStockTotals.get(i).equals(stockTotal)) {
                mStockTotals.get(i)
                        .setCountChoice(
                                mStockTotals.get(i).getCountChoice() + stockTotal.getCountChoice());
                return;
            }
        }
        mStockTotals.add(stockTotal);
    }

    @Override
    public void pickStockTotalListSuccess(List<StockTotal> stockTotals) {
        for (StockTotal stockTotal : stockTotals) {
            mergeStockTotalList(stockTotal);
        }
        mAdapter.notifyDataSetChanged();
        //TODO add list stock total
    }
}
