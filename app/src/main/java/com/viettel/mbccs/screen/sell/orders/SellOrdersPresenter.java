package com.viettel.mbccs.screen.sell.orders;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.Shop;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOrderResponse;
import com.viettel.mbccs.screen.sell.orders.adapter.SellOrdersFragmentAdapter;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.ValidateUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/15/17.
 */

public class SellOrdersPresenter {
    private Context context;
    private SellOrdersContract.View sellOrdersView;
    private BanHangKhoTaiChinhRepository banHangKhoTaiChinhRepository;
    private UserRepository userRepository;
    private CompositeSubscription subscriptions;
    private List<ChannelInfo> channelInfoList;
    private ChannelInfo channelInfoSelect;

    public ObservableField<StaffInfo> staffInfo;
    public ObservableField<Shop> shop;
    public ObservableField<SpinnerAdapter<ChannelInfo>> spinnerAdapterChannel;
    public ObservableField<SellOrdersFragmentAdapter> sellOrdersFragmentAdapter;
    public ObservableBoolean isHideSearch;
    public ObservableField<String> textSearch;

    public SellOrdersPresenter(Context context, SellOrdersContract.View sellOrdersView) {
        this.context = context;
        this.sellOrdersView = sellOrdersView;
        banHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();
        channelInfoList = new ArrayList<>();
        channelInfoSelect = new ChannelInfo();

        sellOrdersFragmentAdapter = new ObservableField<>();
        staffInfo = new ObservableField<>();
        shop = new ObservableField<>();
        spinnerAdapterChannel = new ObservableField<>();
        isHideSearch = new ObservableBoolean(false);
        textSearch = new ObservableField<>();
    }

    public void subscribe() {
        sellOrdersView.showLoading();
        UserInfo userInfo = userRepository.getUserInfo();
        staffInfo.set(userInfo.getStaffInfo());
        shop.set(userInfo.getShop());

        GetListChannelByOwnerTypeIdRequest g = new GetListChannelByOwnerTypeIdRequest();
        g.setStaffId(String.valueOf(staffInfo.get().getStaffId()));
//        g.setChannelTypeId(staffInfo.get().getChannelTypeId());

        DataRequest<GetListChannelByOwnerTypeIdRequest> request = new DataRequest<>();
        request.setWsRequest(g);
        request.setWsCode(WsCode.GetListChannelByOwnerTypeId);

        Subscription subscription =
                banHangKhoTaiChinhRepository.getListChannelByOwnerTypeId(request)
                        .subscribe(new MBCCSSubscribe<GetListChannelByOwnerTypeIdResponse>() {
                            @Override
                            public void onSuccess(GetListChannelByOwnerTypeIdResponse object) {
                                if (object == null || object.getChannelInfoList() == null) {
                                    sellOrdersView.hideLoading();
                                    sellOrdersView.getListChannelByOwnerTypeIdError(
                                            context.getString(
                                                    R.string.sell_orders_no_data_channel));
                                    return;
                                }
                                channelInfoList = object.getChannelInfoList();
                                //                                for (ChannelInfo c : channelInfoList) {
                                //                                    dataSpinnerChannel.add(c.getManagementName());
                                //                                }
                                spinnerAdapterChannel.set(
                                        new SpinnerAdapter<>(context, channelInfoList));
                                spinnerAdapterChannel.get()
                                        .setOnItemSelectedListener(
                                                new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(
                                                            AdapterView<?> parent, View view,
                                                            int position, long id) {
                                                        channelInfoSelect =
                                                                channelInfoList.get(position);
                                                    }

                                                    @Override
                                                    public void onNothingSelected(
                                                            AdapterView<?> parent) {

                                                    }
                                                });
                                setTextHideSearch();
                                sellOrdersView.hideLoading();
                            }

                            @Override
                            public void onError(BaseException error) {
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

    public void clickSearch() {
        long dateFrom = sellOrdersView.getDateFrom();
        long dateTo = sellOrdersView.getDateTo();

        if (!ValidateUtils.isTimeForDay(dateFrom, dateTo, 30)) {
            sellOrdersView.showErrorDate();
            return;
        }

        sellOrdersView.showLoading();

        GetListOrderRequest getListOrderRequest = new GetListOrderRequest();
        getListOrderRequest.setShopId(staffInfo.get().getShopId());
        getListOrderRequest.setStaffId(channelInfoSelect.getChannelId());
        getListOrderRequest.setEndDate(sellOrdersView.getStringDateTo());
        getListOrderRequest.setStartDate(sellOrdersView.getStringDateFrom());
//        getListOrderRequest.setOrderStatus(staffInfo.get().getStatus());

        DataRequest<GetListOrderRequest> baseRequest = new DataRequest<>();
        baseRequest.setWsCode(WsCode.GetListOrder);
        baseRequest.setWsRequest(getListOrderRequest);

        banHangKhoTaiChinhRepository.getListOrder(baseRequest)
                .subscribe(new MBCCSSubscribe<GetListOrderResponse>() {
                    @Override
                    public void onSuccess(GetListOrderResponse object) {
                        sellOrdersView.setDataResult(object.getSaleOrdersList(), channelInfoSelect);
                        sellOrdersView.hideLoading();
                    }

                    @Override
                    public void onError(BaseException error) {
                        sellOrdersView.getDataError(error);
                        sellOrdersView.hideLoading();
                    }
                });
    }

    public void expandSearch() {
        isHideSearch.set(!isHideSearch.get());
        setTextHideSearch();
    }

    public void setTextHideSearch() {
        textSearch.set(context.getString(R.string.sell_orders_gone_search, shop.get().getShopName(),
                staffInfo.get().getStaffName(), channelInfoSelect.getManagementName()));
    }

    public void setSellOrdersFragmentAdapter(SellOrdersFragmentAdapter sellOrdersFragmentAdapter) {
        this.sellOrdersFragmentAdapter.set(sellOrdersFragmentAdapter);
    }

//    public void setPositionSelectChange(int position) {
//        channelInfoSelect = channelInfoList.get(position);
//    }
}
