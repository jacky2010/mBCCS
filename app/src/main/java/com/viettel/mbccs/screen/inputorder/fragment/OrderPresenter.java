package com.viettel.mbccs.screen.inputorder.fragment;

import com.viettel.mbccs.data.model.BillRange;
import com.viettel.mbccs.screen.inputorder.fragment.adapter.OrderAdapter;
import java.util.ArrayList;
import java.util.List;

public class OrderPresenter implements OrderContract.Presenter {
    private OrderContract.ViewModel mViewModel;
    private int mIndexTab;
    public OrderAdapter adapter;
    private List<BillRange> mBillRangeList = new ArrayList<>();

    public OrderPresenter(OrderContract.ViewModel viewModel, int indexTab) {
        mViewModel = viewModel;
        mIndexTab = indexTab;
    }

    public void setAdapter(OrderAdapter adapter) {
        this.adapter = adapter;
    }

    public OrderAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void subscribe() {
        dumyData();
        this.adapter.setBillRangeList(mBillRangeList);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void onInputOrderClick() {
        mViewModel.inputOrderSuccess();
    }

    private void dumyData() {
        mBillRangeList.add(new BillRange("123345", "123346", 40, "25/05/2017", "Viettel"));
        mBillRangeList.add(new BillRange("123345", "123346", 40, "25/05/2017", "Viettel"));
        mBillRangeList.add(new BillRange("123345", "123346", 40, "25/05/2017", "Viettel"));
        mBillRangeList.add(new BillRange("123345", "123346", 40, "25/05/2017", "Viettel"));
        mBillRangeList.add(new BillRange("123345", "123346", 40, "25/05/2017", "Viettel"));
        mBillRangeList.add(new BillRange("123345", "123346", 40, "25/05/2017", "Viettel"));
        mBillRangeList.add(new BillRange("123345", "123346", 40, "25/05/2017", "Viettel"));
        mBillRangeList.add(new BillRange("123345", "123346", 40, "25/05/2017", "Viettel"));
        mBillRangeList.add(new BillRange("123345", "123346", 40, "25/05/2017", "Viettel"));
        mBillRangeList.add(new BillRange("123345", "123346", 40, "25/05/2017", "Viettel"));
    }
}
