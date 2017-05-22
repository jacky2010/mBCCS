package com.viettel.mbccs.screen.viewwarehouse;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.Session;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.screen.viewwarehouse.adapter.ViewWarehouseListOrderAdapter;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/21/17.
 */

public class ViewWarehousePresenter implements ViewWarehouseContract.Presenter,
        ViewWarehouseListOrderAdapter.ViewWarehouseListOrderAdapterCallback{
    private Context context;
    private ViewWarehouseContract.View view;
    private BanHangKhoTaiChinhRepository banHangKhoTaiChinhRepository;
    private CompositeSubscription subscriptions;

    public ObservableBoolean showRightIcon;
    public ObservableField<ViewWarehouseListOrderAdapter> adapterOrders;
    public ObservableField<RecyclerView.ItemDecoration> itemDecoration;
    public ObservableInt totalStock;

    public ViewWarehousePresenter(Context context, ViewWarehouseContract.View view) {
        this.context = context;
        this.view = view;
        banHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        subscriptions = new CompositeSubscription();

        showRightIcon = new ObservableBoolean();
        adapterOrders = new ObservableField<>();
        itemDecoration = new ObservableField<>();
        totalStock = new ObservableInt();
    }

    @Override
    public void subscribe() {
        showRightIcon.set(true);
        // TODO: 5/21/17 get data user from sharePref
        //        userRepository.getDataUser();
        GetListStockModelRequest g = new GetListStockModelRequest();
        // TODO: 5/22/17 fake data
        g.setOwnerId(1);
        g.setOwnerType(1);

        BaseRequest<GetListStockModelRequest> request = new BaseRequest<>();
        request.setRequest(g);
        request.setWsCode(WsCode.GetListStockModel);
        request.setApiKey("demo");
        request.setSession(new Session());

        Subscription subscription = banHangKhoTaiChinhRepository.getListStockModel(request)
                .subscribe(new MBCCSSubscribe<List<StockTotal>>() {
                    @Override
                    public void onSuccess(List<StockTotal> object) {
                        view.setData(object);
                        totalStock.set(object.size());
                    }

                    @Override
                    public void onError(BaseException error) {
                        view.onError(BaseException.toUnexpectedError(new Exception()));
                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    public void onCancel() {

    }

    public void onSearch() {

    }

    public void setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration.set(itemDecoration);
    }

    public void setAdapterOrders(ViewWarehouseListOrderAdapter adapter) {
        adapterOrders.set(adapter);
    }

    @Override
    public void onClickViewSerial(int position) {

    }
}
