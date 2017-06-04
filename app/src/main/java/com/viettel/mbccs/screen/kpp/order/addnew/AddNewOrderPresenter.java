package com.viettel.mbccs.screen.kpp.order.addnew;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
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
    private DataRequest<KPPOrderRequest> mKPPOrderRequestBaseRequest;

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
        titleOrder.set("Đặt hàng từ KPP : POS_1233");
        amount = new ObservableField<>();
        caculatePrice();
        mAdapter = new StockTotalAdapter(mContext, mStockTotals);
        mAdapter.setStockTotalListener(new StockTotalAdapter.StockTotalListener() {
            @Override
            public void onStockQuantityChange() {
                caculatePrice();
            }
        });
    }

    private void caculatePrice() {
        float totalMoney = 0;
        for (StockTotal stockTotal : mStockTotals) {
            totalMoney += stockTotal.getCountChoice() * stockTotal.getPrice();
        }
        amount.set(String.format(mContext.getString(R.string.kpp_order_label_amount),
                Common.formatDouble(totalMoney)));
    }

    private void loadData() {
        //  fakeData();
    }

    private void fakeData() {
        StockTotal stock1 = new StockTotal();
        stock1.setStockModelName("Iphone 7");
        StockTotal stock2 = new StockTotal();
        stock2.setStockModelName("Galaxy s8");
        StockTotal stock3 = new StockTotal();
        stock3.setStockModelName("Oppo F1s");
        StockTotal stock4 = new StockTotal();
        stock4.setStockModelName("LG G5");
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
        if (!isValidate()) {
            return;
        }
        DialogUtils.showDialog(mContext, R.string.confirm, R.string.confirm_kpp_order,
                R.string.order2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createOrder();
                    }
                }, R.string.common_label_close, null);
    }

    private void createOrder() {

        mViewModel.showLoading();
        mKPPOrderRequestBaseRequest = new DataRequest<>();
        mKPPOrderRequestBaseRequest.setApiCode(ApiCode.CreateSaleOrders);
        KPPOrderRequest request = new KPPOrderRequest();
        request.setStaffId(1);
        request.setChannelStaffId(1);
        request.setListStockModel(Common.convertStockTotalsToStockModels(mStockTotals));
        mKPPOrderRequestBaseRequest.setParameterApi(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.createSaleOrders(mKPPOrderRequestBaseRequest)
                        .subscribe(new MBCCSSubscribe<DataResponse>() {
                            @Override
                            public void onSuccess(DataResponse object) {
                                mViewModel.gotoSuccessScreen(mStockTotals);
                            }

                            @Override
                            public void onError(BaseException error) {
                                DialogUtils.showDialogError(mContext, null, error.getMessage(),
                                        null);
                                //mViewModel.gotoSuccessScreen(mStockTotals);
                            }

                            @Override
                            public void onRequestFinish() {
                                super.onRequestFinish();
                                mViewModel.hideLoading();
                            }
                        });
        mCompositeSubscription.add(subscription);
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
        caculatePrice();
        //TODO add list stock total
    }

    public boolean isValidate() {

        int count = 0;
        for (StockTotal item : mStockTotals) {
            if (item.getCountChoice() > 0) {
                count++;
                break;
            }
        }
        if (count == 0) {
            DialogUtils.showDialogError(mContext, R.string.no_item_order);
            return false;
        }

        return true;
    }
}
