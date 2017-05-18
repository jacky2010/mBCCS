package com.viettel.mbccs.screen.sell.orders.fragment.orderdetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.viettel.mbccs.data.model.GoodItem;
import com.viettel.mbccs.screen.sell.orders.adapter.OrderDetailAdapter;
import com.viettel.mbccs.screen.serialpicker.SerialPickerActivity;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrderDetailFragmentPresenter implements OrderDetailFragmentContract.Presenter {
    private Context context;
    private OrderDetailFragmentContract.View view;
    private List<GoodItem> goodItemList;

    public ObservableField<OrderDetailAdapter> adapterOrderDetail;
    public ObservableField<String> idOrder;
    public ObservableField<String> totalMoney;
    public ObservableField<String> payMoney;
    public ObservableField<String> discountMoney;
    public ObservableField<String> taxPercent;
    public ObservableField<String> taxMoney;

    public OrderDetailFragmentPresenter(Context context, OrderDetailFragmentContract.View view) {
        this.context = context;
        this.view = view;
        adapterOrderDetail = new ObservableField<>();
        idOrder = new ObservableField<>();
        totalMoney = new ObservableField<>();
        payMoney = new ObservableField<>();
        discountMoney = new ObservableField<>();
        taxPercent = new ObservableField<>();
        taxMoney = new ObservableField<>();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public void setAdapterOrderDetail(OrderDetailAdapter adapterOrderDetail) {
        this.adapterOrderDetail.set(adapterOrderDetail);
    }

    @Override
    public void itemOrderDetailClick(int position) {

    }

    @Override
    public void selectSerialClick(int position) {
        Activity activity = (Activity) context;
        String json = GsonUtils.Object2String(goodItemList.get(position));
        Intent intent = new Intent(activity, SerialPickerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BundleConstant.GOOD_ITEM, json);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void getDetailOrder(String idOrder) {
        // TODO: 5/16/17 get Detail Order from API
        this.idOrder.set(idOrder);
        this.totalMoney.set("10000");
        this.payMoney.set("10000");
        this.discountMoney.set("10000");
        this.taxPercent.set("10000");
        this.taxMoney.set("10000");

        if (true) {
            goodItemList = new ArrayList<>();
            view.setData(goodItemList);
        } else {
            // TODO: 5/16/17 error
        }
    }

    public void onCancel() {
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.getSupportFragmentManager().popBackStack();
    }

    public void onClickSell() {

    }
}
