package com.viettel.mbccs.screen.sell.orders.fragment.orderdetail;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.Session;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetOrderInfoResponse;
import com.viettel.mbccs.screen.sell.orders.adapter.OrderDetailAdapter;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
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
    private List<SaleOrdersDetail> saleOrdersDetailList;
    private SaleTrans saleTrans;

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
        amountNotTax = new ObservableField<>();
        amountTax = new ObservableField<>();
        discount = new ObservableField<>();
        vAT = new ObservableField<>();
        tax = new ObservableField<>();
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
    public void itemOrderDetailClick(int position) {

    }

    @Override
    public void selectSerialClick(int position) {

        if (saleOrdersDetailList == null) {
            return;
        }

        SaleOrdersDetail saleOrdersDetail = saleOrdersDetailList.get(position);

        view.pickSerial(saleOrdersDetail);
    }

    public void getDetailOrder(long idOrder) {
        //        view.showLoading();
        // TODO: 5/16/17 get Detail Order from API
        this.idOrder.set(String.valueOf(idOrder));
        GetOrderInfoRequest g = new GetOrderInfoRequest();
        g.setSaleOrderId(idOrder);

        DataRequest<GetOrderInfoRequest> request = new DataRequest<>();
        request.setParameterApi(g);
        request.setApiCode(ApiCode.GetOrderInfo);
        request.setApiKey("demo");
        request.setSession(new Session());

        Subscription subscription = banHangKhoTaiChinhRepository.getOrderInfo(request)
                .subscribe(new MBCCSSubscribe<GetOrderInfoResponse>() {
                    @Override
                    public void onSuccess(GetOrderInfoResponse object) {
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
        subscriptions.add(subscription);
        saleTrans = new SaleTrans();
        saleTrans.setAmountNotTax(100000);
        saleTrans.setAmountTax(100000);
        saleTrans.setDiscount(100000);
        saleTrans.setVAT(100000);
        saleTrans.setTax(100000);

        saleOrdersDetailList = new ArrayList<>();
        view.setData(saleOrdersDetailList);
        setDataDisplayMoney();
    }

    private void setDataDisplayMoney() {
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
        view.clickCancelSell(saleTrans);
    }

    public void onClickSell() {
        view.onClickSell(saleTrans);
    }

    public void setSerialBlockList(List<Integer> list) {
        //        if (list.size())
    }

    @Override
    public void onSerialPickerSuccess(List<SerialBO> serialBlockBySerials) {
        //TODO upte serialBO list
    }
}
