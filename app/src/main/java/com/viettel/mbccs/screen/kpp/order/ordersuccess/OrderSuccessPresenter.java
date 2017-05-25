package com.viettel.mbccs.screen.kpp.order.ordersuccess;

import android.content.Context;
import android.databinding.ObservableField;
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

    public OrderSuccessPresenter(Context context, OrderSuccessContract.ViewModel viewModel,
            ArrayList<StockTotal> stockTotals) {
        mContext = context;
        mViewModel = viewModel;
        this.mList = stockTotals;
        init();
    }

    private void init() {
        titleCode = new ObservableField<>();
        titleStaffName = new ObservableField<>();
        mAdapter = new OrderSuccessAdapter(mContext, mList);
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
