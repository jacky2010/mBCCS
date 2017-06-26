package com.viettel.mbccs.screen.sell.orders.fragment.orderdetail;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetOrderInfoResponse;
import com.viettel.mbccs.screen.sell.orders.adapter.OrderDetailAdapter;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrderDetailFragmentPresenter implements OrderDetailFragmentContract.Presenter {
    private Context context;
    private OrderDetailFragmentContract.View view;
    private BanHangKhoTaiChinhRepository banHangKhoTaiChinhRepository;
    private CompositeSubscription subscriptions;
    private GetOrderInfoResponse getOrderInfoResponse;

    public ObservableField<OrderDetailAdapter> adapterOrderDetail;
    public ObservableField<SaleTrans> saleTransField;
    public ObservableField<String> idOrder;
    public ObservableField<String> amountNotTax;
    public ObservableField<String> amountTax;
    public ObservableField<String> discount;
    public ObservableField<String> vAT;
    public ObservableField<String> tax;

    public OrderDetailFragmentPresenter(Context context, OrderDetailFragmentContract.View view) {
        this.context = context;
        this.view = view;
        banHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        subscriptions = new CompositeSubscription();

        adapterOrderDetail = new ObservableField<>();
        idOrder = new ObservableField<>();
        saleTransField = new ObservableField<>();
        amountNotTax = new ObservableField<>("");
        amountTax = new ObservableField<>("");
        discount = new ObservableField<>("");
        vAT = new ObservableField<>("");
        tax = new ObservableField<>("");
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    public void setAdapterOrderDetail(OrderDetailAdapter adapterOrderDetail) {
        this.adapterOrderDetail.set(adapterOrderDetail);
    }

    @Override
    public void selectSerialClick(int position) {
        if (getOrderInfoResponse.getSaleOrdersDetailList() == null) {
            return;
        }
        SaleOrdersDetail saleOrdersDetail =
                getOrderInfoResponse.getSaleOrdersDetailList().get(position);
        view.pickSerial(saleOrdersDetail);
    }

    public void getDetailOrder(SaleOrders saleOrders) {
        view.showLoading();
        this.idOrder.set(String.valueOf(saleOrders.getSaleOrdersId()));

        GetOrderInfoRequest g = new GetOrderInfoRequest();
        g.setSaleOrderId(saleOrders.getSaleOrdersId());
        g.setOwnerId(saleOrders.getStaffId());
        g.setSaleTransType(SaleTranType.SALE_CHANNEL);
        // TODO: 6/15/17 fix data
        g.setOwnerType("2");
        g.setStateId("1");
        g.setTelecomserviceId("1");


        DataRequest<GetOrderInfoRequest> request = new DataRequest<>();
        request.setWsCode(WsCode.GetOrderInfo);
        request.setWsRequest(g);

        Subscription subscription = banHangKhoTaiChinhRepository.getOrderInfo(request)
                .subscribe(new MBCCSSubscribe<GetOrderInfoResponse>() {
                    @Override
                    public void onSuccess(GetOrderInfoResponse object) {
                        getOrderInfoResponse = object;
                        view.setData(object);
                        setDataDisplayMoney();
                        view.hideLoading();
                    }

                    @Override
                    public void onError(BaseException error) {
                        view.hideLoading();
                        view.getOrderInfoError(error);
                    }
                });
        subscriptions.add(subscription);
    }

    private void setDataDisplayMoney() {
        SaleTrans saleTrans = getOrderInfoResponse.getSaleTrans();
        amountNotTax.set(Common.formatDouble(saleTrans.getAmountNotTax()));
        amountTax.set(Common.formatDouble(saleTrans.getAmountTax()));
        discount.set(Common.formatDouble(saleTrans.getDiscount()));
        vAT.set(Common.formatDouble(saleTrans.getVAT()));
        tax.set(Common.formatDouble(saleTrans.getTax()));
    }

    public void onCancel() {
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.getSupportFragmentManager().popBackStack();
    }

    public void onCancelSell() {
        view.clickCancelSell();
    }

    public void onClickSell() {
        List<SaleOrdersDetail> saleOrdersDetailList =
                getOrderInfoResponse.getSaleOrdersDetailList();
        boolean checkCountSerial = true;
        for (SaleOrdersDetail s : saleOrdersDetailList) {
            if (s.getLstSerial().size() != s.getQuantity()) {
                checkCountSerial = false;
                break;
            }
        }
        if (!checkCountSerial){
            view.checkCountSerialError();
            return;
        }
        view.onClickSell();
    }

    public void setSerialBlockList(List<Integer> list) {
        //        if (list.size())
    }

    public void setData(GetOrderInfoResponse data) {
        this.getOrderInfoResponse = data;
        setDataDisplayMoney();
    }
}
