package com.viettel.mbccs.screen.viewwarehouse;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.screen.viewwarehouse.adapter.ViewWarehouseListOrderAdapter;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/21/17.
 */

public class ViewWarehousePresenter implements ViewWarehouseContract.Presenter,
        ViewWarehouseListOrderAdapter.ViewWarehouseListOrderAdapterCallback {
    private Context context;
    private ViewWarehouseContract.View view;
    private BanHangKhoTaiChinhRepository banHangKhoTaiChinhRepository;
    private UserRepository userRepository;
    private CompositeSubscription subscriptions;
    private List<StockTotal> stockTotalList;

    public ObservableBoolean showRightIcon;
    public ObservableField<ViewWarehouseListOrderAdapter> adapterOrders;
    public ObservableField<RecyclerView.ItemDecoration> itemDecoration;
    public ObservableInt totalStock;

    public ViewWarehousePresenter(Context context, ViewWarehouseContract.View view) {
        this.context = context;
        this.view = view;
        banHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();

        showRightIcon = new ObservableBoolean();
        adapterOrders = new ObservableField<>();
        itemDecoration = new ObservableField<>();
        totalStock = new ObservableInt();
    }

    @Override
    public void subscribe() {
        showRightIcon.set(true);
        UserInfo userInfo = userRepository.getUserInfo();
        GetListStockModelRequest g = new GetListStockModelRequest();
        g.setOwnerId(userInfo.getStaffInfo().getStaffId());
        g.setSaleTransType(SaleTranType.SALE_RETAIL);
        /**
         * if OwnerId == StaffId => OwnerType = 2L
         * if OwnerId == ShopId => OwnerType = 1L
         */
        g.setOwnerType(2L);

        DataRequest<GetListStockModelRequest> request = new DataRequest<>();
        request.setWsRequest(g);
        request.setWsCode(WsCode.GetListStockModel);

        Subscription subscription = banHangKhoTaiChinhRepository.getListStockModel(request)
                .subscribe(new MBCCSSubscribe<GetListStockModelResponse>() {
                    @Override
                    public void onSuccess(GetListStockModelResponse object) {
                        if (object == null || object.getStockTotalList() == null)
                        {
                            BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                            baseErrorResponse.setError(201, context.getString(R.string.no_data));
                            onError(BaseException.toServerError(baseErrorResponse));
                            return;
                        }
                        stockTotalList = object.getStockTotalList();
                        view.setData(stockTotalList);
                        totalStock.set(stockTotalList.size());
                    }

                    @Override
                    public void onError(BaseException error) {
                        view.onError(error);
                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    public void onCancel() {
        view.onCancel();
    }

    public void onSearch() {
        view.onSearch();
    }

    public void setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration.set(itemDecoration);
    }

    public void setAdapterOrders(ViewWarehouseListOrderAdapter adapter) {
        adapterOrders.set(adapter);
    }

    @Override
    public void onClickViewSerial(int position) {
        view.onClickViewSerial(stockTotalList.get(position));
    }
}
