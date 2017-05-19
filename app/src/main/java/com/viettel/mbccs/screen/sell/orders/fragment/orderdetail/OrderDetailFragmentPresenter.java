package com.viettel.mbccs.screen.sell.orders.fragment.orderdetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.model.Session;
import com.viettel.mbccs.data.source.SellOrdersRepository;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.OrderInfoResponse;
import com.viettel.mbccs.screen.sell.orders.adapter.OrderDetailAdapter;
import com.viettel.mbccs.screen.serialpicker.SerialPickerActivity;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrderDetailFragmentPresenter implements OrderDetailFragmentContract.Presenter {
    private Context context;
    private OrderDetailFragmentContract.View view;
    private List<ModelSale> goodItemList;
    private SellOrdersRepository sellOrdersRepository;
    private List<SaleOrdersDetail> saleOrdersDetailList;
    private SaleTrans saleTrans;

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
        sellOrdersRepository = SellOrdersRepository.getInstance();

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
        //        String json = GsonUtils.Object2String(goodItemList.get(position));
        Intent intent = new Intent(activity, SerialPickerActivity.class);
        Bundle bundle = new Bundle();
        //        bundle.putString(Constants.BundleConstant.GOOD_ITEM, json);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, 0);
    }

    public void getDetailOrder(long idOrder) {
        view.showLoading();
        // TODO: 5/16/17 get Detail Order from API
        this.idOrder.set(String.valueOf(idOrder));
        GetOrderInfoRequest g = new GetOrderInfoRequest();
        g.setSaleOrderId(idOrder);

        BaseRequest<GetOrderInfoRequest> request = new BaseRequest<>();
        request.setRequest(g);
        request.setWsCode(WsCode.GetOrderInfo);
        request.setApiKey("demo");
        request.setSession(new Session());

        sellOrdersRepository.getOrderInfo(request)
                .subscribe(new MBCCSSubscribe<OrderInfoResponse>() {
                    @Override
                    public void onSuccess(OrderInfoResponse object) {
                        saleOrdersDetailList = object.getSaleOrdersDetailList();
                        saleTrans = object.getSaleTrans();
                        view.setData(saleOrdersDetailList);
                        setDataDisplayMoney();
                        view.hideLoading();
                    }

                    @Override
                    public void onError(BaseException error) {
                        view.hideLoading();
                        view.getOrderInfoError(error);
                    }
                });
    }

    private void setDataDisplayMoney() {
        this.totalMoney.set(String.valueOf(saleTrans.getAmountTax()));
        this.payMoney.set(String.valueOf(saleTrans.getAmountNotTax()));
        this.discountMoney.set(String.valueOf(saleTrans.getDiscount()));
        this.taxPercent.set(String.valueOf(saleTrans.getvAT()));
        this.taxMoney.set(String.valueOf(saleTrans.getTax()));
    }

    public void onCancel() {
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.getSupportFragmentManager().popBackStack();
    }

    public void onCancelSell() {

    }

    public void onClickSell() {

    }

    public void setSerialBlockList(List<Integer> list) {
        //        if (list.size())
    }
}
