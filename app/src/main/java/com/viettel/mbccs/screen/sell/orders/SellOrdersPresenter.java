package com.viettel.mbccs.screen.sell.orders;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.Session;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOrderResponse;
import com.viettel.mbccs.screen.sell.orders.adapter.SellOrdersFragmentAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.ValidateUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/15/17.
 */

public class SellOrdersPresenter implements AdapterView.OnItemSelectedListener {
    private Context context;
    private SellOrdersContract.View sellOrdersView;
    private BanHangKhoTaiChinhRepository banHangKhoTaiChinhRepository;
    private CompositeSubscription subscriptions;
    private List<ChannelInfo> channelInfoList;
    private List<String> dataSpinnerChannel;
    private ChannelInfo channelInfoSelect;

    public ObservableField<StaffInfo> staffInfo;
    public ObservableField<ArrayAdapter<String>> spinnerAdapterChannel;
    public ObservableField<SellOrdersFragmentAdapter> sellOrdersFragmentAdapter;
    public ObservableBoolean isHideSearch;
    public ObservableField<String> textSearch;

    public SellOrdersPresenter(Context context, SellOrdersContract.View sellOrdersView) {
        this.context = context;
        this.sellOrdersView = sellOrdersView;
        banHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        subscriptions = new CompositeSubscription();
        dataSpinnerChannel = new ArrayList<>();
        channelInfoList = new ArrayList<>();
        channelInfoSelect = new ChannelInfo();

        sellOrdersFragmentAdapter = new ObservableField<>();
        staffInfo = new ObservableField<>();
        spinnerAdapterChannel = new ObservableField<>();
        isHideSearch = new ObservableBoolean(false);
        textSearch = new ObservableField<>();
    }

    public void subscribe() {
        // TODO: 5/18/17 get data StaffInfo from SharedPreferences
        sellOrdersView.showLoading();
        staffInfo.set(new StaffInfo());

        GetListChannelByOwnerTypeIdRequest g = new GetListChannelByOwnerTypeIdRequest();
//        g.setShopId(staffInfo.get().getShopId());
//        g.setStaffId(staffInfo.get().getStaffId());
//        g.setChannelTypeId(staffInfo.get().getChannelTypeId());

        g.setChannelTypeId(213);
        g.setStaffId(54366);

        DataRequest<GetListChannelByOwnerTypeIdRequest> request = new DataRequest<>();
        request.setParameterApi(g);

        // TODO: 5/18/17 get data
        Session session = new Session();
        session.setSessionId("54578345638");

        request.setUserName("smac");
        request.setApiCode(ApiCode.GetListChannelByOwnerTypeId);
        request.setToken("54353-543346-65464564-6546");
        request.setApiKey("123456");
        request.setSession(session);

        Subscription subscription =
                banHangKhoTaiChinhRepository.getListChannelByOwnerTypeId(request)
                        .subscribe(new MBCCSSubscribe<GetListChannelByOwnerTypeIdResponse>() {
                            @Override
                            public void onSuccess(GetListChannelByOwnerTypeIdResponse object) {
                                channelInfoList = object.getChannelInfoList();
                                for (ChannelInfo c : channelInfoList) {
                                    dataSpinnerChannel.add(c.getManagementName());
                                }
                                spinnerAdapterChannel.set(new ArrayAdapter<>(context,
                                        android.R.layout.simple_spinner_item, dataSpinnerChannel));
                                spinnerAdapterChannel.get()
                                        .setDropDownViewResource(
                                                android.R.layout.simple_spinner_dropdown_item);

                                sellOrdersView.hideLoading();
                            }

                            @Override
                            public void onError(BaseException error) {
                                // TODO: 5/18/17 show error
                                sellOrdersView.hideLoading();
                                sellOrdersView.getListChannelByOwnerTypeIdError(error);
                            }
                        });
        subscriptions.add(subscription);
    }

    public void unSubscribe() {
        subscriptions.clear();
    }

    public void onCancel() {
        Activity activity = (Activity) context;
        activity.setResult(Activity.RESULT_CANCELED);
        activity.finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerChannel:
                channelInfoSelect = channelInfoList.get(position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void clickSearch() {
        sellOrdersView.showLoading();
        long dateFrom = sellOrdersView.getDateFrom();
        long dateTo = sellOrdersView.getDateTo();

        if (!ValidateUtils.isTimeForDay(dateFrom, dateTo, 30)) {
            sellOrdersView.showErrorDate();
            return;
        }

        GetListOrderRequest getListOrderRequest = new GetListOrderRequest();
        //        getListOrderRequest.setShopId(staffInfo.get().getShopId());
        //        getListOrderRequest.setStaffId(staffInfo.get().getStaffId());
        //        getListOrderRequest.setIsdnChannel(channelInfoSelect.getChannelId());
        //        getListOrderRequest.setToDate(String.valueOf(dateTo));
        //        getListOrderRequest.setFromDate(String.valueOf(dateFrom));
        //        getListOrderRequest.setOrderStatus(1);

        // TODO: 5/29/17 fake data
        getListOrderRequest.setShopId(213);
        getListOrderRequest.setStaffId(3243);
        getListOrderRequest.setOrderStatus(54366);
        getListOrderRequest.setIsdnChannel(23);
        getListOrderRequest.setFromDate("02/05/2017 00:00:00");
        getListOrderRequest.setToDate("02/08/2017 00:00:00");

        DataRequest<GetListOrderRequest> baseRequest = new DataRequest<>();
        Session session = new Session();
        session.setSessionId("54578345638");

        baseRequest.setSession(session);
        baseRequest.setUserName("smac");
        baseRequest.setApiCode(ApiCode.GetListOrder);
        baseRequest.setApiKey("123456");
        baseRequest.setToken("54353-543346-65464564-6546");
        baseRequest.setParameterApi(getListOrderRequest);

        banHangKhoTaiChinhRepository.getListOrder(baseRequest)
                .subscribe(new MBCCSSubscribe<GetListOrderResponse>() {
                    @Override
                    public void onSuccess(GetListOrderResponse object) {
                        sellOrdersView.setDataResult(object.getSaleOrdersList(), channelInfoSelect);
                        sellOrdersView.hideLoading();
                    }

                    @Override
                    public void onError(BaseException error) {
                        // TODO: 5/16/17 error
                        DialogUtils.showDialogError(context, null, error.getMessage(), null);
                        sellOrdersView.getDataError(error);
                        sellOrdersView.hideLoading();
                    }
                });
    }

    public void expandSearch() {
        isHideSearch.set(!isHideSearch.get());
        textSearch.set(
                context.getString(R.string.sell_orders_gone_search, staffInfo.get().getShopName(),
                        staffInfo.get().getStaffName(), channelInfoSelect.getManagementName()));
    }

    public void setSellOrdersFragmentAdapter(SellOrdersFragmentAdapter sellOrdersFragmentAdapter) {
        this.sellOrdersFragmentAdapter.set(sellOrdersFragmentAdapter);
    }
}
