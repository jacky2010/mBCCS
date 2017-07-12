package com.viettel.mbccs.screen.kpp.order.ordersuccess;

import android.content.Context;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.screen.kpp.order.ordersuccess.adapter.OrderSuccessAdapter;
import java.util.ArrayList;
import java.util.List;

public class OrderSuccessPresenter implements OrderSuccessContract.Presenter {

    public ObservableField<String> titleCode;
    public ObservableField<String> titleStaffName;
    private Context mContext;
    private OrderSuccessContract.ViewModel mViewModel;
    private OrderSuccessAdapter mAdapter;
    private ArrayList<StockTotal> mList;
    private ArrayList<StockTotal> filerList = new ArrayList<>();
    public ObservableField<String> saleOrderId;
    public ObservableField<String> saleOrderReceive;

    public OrderSuccessPresenter(Context context, OrderSuccessContract.ViewModel viewModel,
            ArrayList<StockTotal> stockTotals) {
        mContext = context;
        mViewModel = viewModel;
        this.mList = stockTotals;
        init();
    }

    public OrderSuccessPresenter(Context context, OrderSuccessContract.ViewModel viewModel,
            ArrayList<StockTotal> stockTotals, String saleOrderIdString, String channelName) {
        mContext = context;
        mViewModel = viewModel;
        this.mList = stockTotals;
        init();
        this.saleOrderId.set(
                String.format(mContext.getString(R.string.kpp_order_label_sale_order_id),
                        saleOrderIdString));
        this.saleOrderReceive.set(
                String.format(mContext.getString(R.string.kpp_order_label_sale_order_receive),
                        channelName));
    }

    private void init() {
        for (StockTotal stockTotal : mList) {
            if (stockTotal.getCountChoice() > 0) {
                filerList.add(stockTotal);
            }
        }

        titleCode = new ObservableField<>();
        titleStaffName = new ObservableField<>();
        saleOrderId = new ObservableField<>();
        saleOrderReceive = new ObservableField<>();
        mAdapter = new OrderSuccessAdapter(mContext, filerList);
    }

    public void closeClick() {
        mViewModel.closeClick();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public OrderSuccessAdapter getAdapter() {
        return mAdapter;
    }
}
