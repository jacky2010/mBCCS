package com.viettel.mbccs.screen.viewwarehouse.fragment;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.BR;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ShopLevel;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTTKDRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetListShopResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTTKDResponse;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class ViewWarehouseSearchPresenter extends BaseObservable
        implements ViewWarehouseSearchContract.Presenter {
    private Context context;
    private ViewWarehouseSearchContract.View view;
    private BanHangKhoTaiChinhRepository banHangKhoTaiChinhRepository;
    private CompositeSubscription subscriptions;

    private List<String> dataStock;
    private List<String> dataStatus;

    private ArrayAdapter<String> adapterStock;
    private ArrayAdapter<String> adapterStatus;

    private String codeProvince;
    private String codeBusinessCenter;
    private String codeStore;
    private String codeWareHouse;

    public ViewWarehouseSearchPresenter(Context context, ViewWarehouseSearchContract.View view) {
        this.context = context;
        this.view = view;
        banHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        subscriptions = new CompositeSubscription();
        setUp();
    }

    private void setUp() {
        dataStock = new ArrayList<>();
        dataStock.add(context.getString(R.string.view_ware_house_search_all));
        adapterStock = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dataStock);
        adapterStock.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataStatus = new ArrayList<>();
        adapterStatus =
                new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dataStatus);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void subscribe() {
        getDataProvince();
        getDataTTKD();
        getDataShop();
    }

    private void getDataShop() {
        // TODO: 5/23/17 fake data
        GetListShopRequest getListShopRequest = new GetListShopRequest();
        getListShopRequest.setParentShopId(1);

        DataRequest<GetListShopRequest> request = new DataRequest<>();
        request.setParameterApi(getListShopRequest);
        request.setApiCode(ApiCode.GetListShop);
        // TODO: 5/23/17 fake data
        Subscription subscription = banHangKhoTaiChinhRepository.getListShop(request)
                .subscribe(new MBCCSSubscribe<GetListShopResponse>() {
                    @Override
                    public void onSuccess(GetListShopResponse object) {

                    }

                    @Override
                    public void onError(BaseException error) {

                    }
                });

        subscriptions.add(subscription);
    }

    private void getDataTTKD() {
        // TODO: 5/23/17 fake data
        GetListTTKDRequest getListTTKDRequest = new GetListTTKDRequest();
        //        getListTTKDRequest.setShopId(1);
        getListTTKDRequest.setShopLevel(ShopLevel.TTKD);

        DataRequest<GetListTTKDRequest> request = new DataRequest<>();
        request.setParameterApi(getListTTKDRequest);
        request.setApiCode(ApiCode.GetListTTKD);
        // TODO: 5/23/17 fake data
        Subscription subscription = banHangKhoTaiChinhRepository.getListTTKD(request)
                .subscribe(new MBCCSSubscribe<GetListTTKDResponse>() {
                    @Override
                    public void onSuccess(GetListTTKDResponse object) {

                    }

                    @Override
                    public void onError(BaseException error) {

                    }
                });

        subscriptions.add(subscription);
    }

    private void getDataProvince() {
        DataRequest<GetListProvinceRequest> request = new DataRequest<>();
        request.setParameterApi(new GetListProvinceRequest());
        request.setApiCode(ApiCode.GetListProvince);
        Subscription subscription = banHangKhoTaiChinhRepository.getListProvince(request)
                .subscribe(new MBCCSSubscribe<GetListProvinceResponse>() {
                    @Override
                    public void onSuccess(GetListProvinceResponse object) {

                    }

                    @Override
                    public void onError(BaseException error) {

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

    public void onFilter() {

    }

    @Bindable
    public ArrayAdapter<String> getAdapterStock() {
        return adapterStock;
    }

    public void setAdapterStock(ArrayAdapter<String> adapterStock) {
        this.adapterStock = adapterStock;
        notifyPropertyChanged(BR.adapterStock);
    }

    @Bindable
    public ArrayAdapter<String> getAdapterStatus() {
        return adapterStatus;
    }

    public void setAdapterStatus(ArrayAdapter<String> adapterStatus) {
        this.adapterStatus = adapterStatus;
        notifyPropertyChanged(BR.adapterStatus);
    }

    public String getCodeProvince() {
        return codeProvince;
    }

    public void setCodeProvince(String codeProvince) {
        this.codeProvince = codeProvince;
    }

    @Bindable
    public String getCodeBusinessCenter() {
        return codeBusinessCenter;
    }

    public void setCodeBusinessCenter(String codeBusinessCenter) {
        this.codeBusinessCenter = codeBusinessCenter;
    }

    @Bindable
    public String getCodeStore() {
        return codeStore;
    }

    public void setCodeStore(String codeStore) {
        this.codeStore = codeStore;
    }

    @Bindable
    public String getCodeWareHouse() {
        return codeWareHouse;
    }

    public void setCodeWareHouse(String codeWareHouse) {
        this.codeWareHouse = codeWareHouse;
    }
}
