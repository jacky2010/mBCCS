package com.viettel.mbccs.screen.sell.orders;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
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

public class SellOrdersPresenter {
    private Context context;
    private SellOrdersContract.View sellOrdersView;
    private BanHangKhoTaiChinhRepository banHangKhoTaiChinhRepository;
    private UserRepository userRepository;
    private CompositeSubscription subscriptions;
    private List<ChannelInfo> channelInfoList;
    private List<String> dataSpinnerChannel;
    private ChannelInfo channelInfoSelect;

    public ObservableField<StaffInfo> staffInfo;
    public ObservableField<Shop> shop;
    public ObservableField<ArrayAdapter<String>> spinnerAdapterChannel;
    public ObservableField<SellOrdersFragmentAdapter> sellOrdersFragmentAdapter;
    public ObservableBoolean isHideSearch;
    public ObservableField<String> textSearch;

    public SellOrdersPresenter(Context context, SellOrdersContract.View sellOrdersView) {
        this.context = context;
        this.sellOrdersView = sellOrdersView;
        banHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();
        dataSpinnerChannel = new ArrayList<>();
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
        g.setStaffId(staffInfo.get().getStaffId());
        g.setChannelTypeId(staffInfo.get().getChannelTypeId());

        DataRequest<GetListChannelByOwnerTypeIdRequest> request = new DataRequest<>();
        request.setParameterApi(g);
        request.setApiCode(ApiCode.GetListChannelByOwnerTypeId);

        Subscription subscription =
                banHangKhoTaiChinhRepository.getListChannelByOwnerTypeId(request)
                        .subscribe(new MBCCSSubscribe<GetListChannelByOwnerTypeIdResponse>() {
                            @Override
                            public void onSuccess(GetListChannelByOwnerTypeIdResponse object) {
                                if (object == null || object.getChannelInfoList() == null) {
                                    sellOrdersView.hideLoading();
                                    sellOrdersView.getListChannelByOwnerTypeIdError("Không có dữ liệu về kênh");
                                    return;
                                }
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
        sellOrdersView.showLoading();
        long dateFrom = sellOrdersView.getDateFrom();
        long dateTo = sellOrdersView.getDateTo();

        if (!ValidateUtils.isTimeForDay(dateFrom, dateTo, 30)) {
            sellOrdersView.showErrorDate();
            return;
        }

        GetListOrderRequest getListOrderRequest = new GetListOrderRequest();
        getListOrderRequest.setShopId(staffInfo.get().getShopId());
        getListOrderRequest.setStaffId(staffInfo.get().getStaffId());
        getListOrderRequest.setIsdnChannel(channelInfoSelect.getChannelId());
        getListOrderRequest.setToDate(sellOrdersView.getStringDateTo());
        getListOrderRequest.setFromDate(sellOrdersView.getStringDateFrom());
        getListOrderRequest.setOrderStatus(1);

        // TODO: 6/12/17
//        getListOrderRequest.setOrderStatus(54366);

        DataRequest<GetListOrderRequest> baseRequest = new DataRequest<>();
        baseRequest.setApiCode(ApiCode.GetListOrder);
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
                context.getString(R.string.sell_orders_gone_search, shop.get().getShopName(),
                        staffInfo.get().getStaffName(), channelInfoSelect.getManagementName()));
    }

    public void setSellOrdersFragmentAdapter(SellOrdersFragmentAdapter sellOrdersFragmentAdapter) {
        this.sellOrdersFragmentAdapter.set(sellOrdersFragmentAdapter);
    }

    public void setPositionSelectChange(int position) {
        channelInfoSelect = channelInfoList.get(position);
    }
}
