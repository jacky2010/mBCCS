package com.viettel.mbccs.screen.connector.mobile;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.BR;
import com.viettel.mbccs.constance.Data;
import com.viettel.mbccs.constance.MobileType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.remote.request.CheckIdNoRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.CheckIdNoResponse;
import com.viettel.mbccs.screen.connector.adapter.ConnectorMobileAdapter;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class ConnectorMobilePresenter extends BaseObservable
        implements ConnectorMobileContract.Presenter,
        ConnectorMobileAdapter.ConnectorMobileAdapterCallback {
    private Context context;
    private ConnectorMobileContract.View viewConnectorMobile;
    private QLKhachHangRepository qlKhachHangRepository;
    private CompositeSubscription subscriptions;

    private boolean isHideSearch = false;
    private boolean isSelectPrepaid;
    private boolean isSelectPostpaid;

    private String textSearch;
    private String txtPassport;

    private int countResult;

    private SpinnerAdapter<Data.DataField> adapterSpinnerMobileService;
    private ConnectorMobileAdapter connectorMobileAdapter;

    private List<Contract> contractList;
    private List<Data.DataField> mobileServiceList;
    private Customer customer;
    private int positionMobileService;

    ConnectorMobilePresenter(Context context, ConnectorMobileContract.View viewConnectorMobile) {
        this.context = context;
        this.viewConnectorMobile = viewConnectorMobile;
    }

    @Override
    public void subscribe() {
        qlKhachHangRepository = QLKhachHangRepository.getInstance();
        subscriptions = new CompositeSubscription();
        contractList = new ArrayList<>();
        mobileServiceList = Data.connectorMobileServiceString();
        adapterSpinnerMobileService = new SpinnerAdapter<>(context, mobileServiceList);
        adapterSpinnerMobileService.notifyDataSetChanged();
        adapterSpinnerMobileService.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        positionMobileService = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        setTextHideSearch();
        setSelectPrepaid(true);
        setSelectPostpaid(false);
    }

    @Override
    public void unSubscribe() {

    }

        /* --------------------------- Set Get ---------------------------------- */

    @Bindable
    public boolean isHideSearch() {
        return isHideSearch;
    }

    public void setHideSearch(boolean hideSearch) {
        isHideSearch = hideSearch;
        notifyPropertyChanged(BR.hideSearch);
    }

    @Bindable
    public boolean isSelectPrepaid() {
        return isSelectPrepaid;
    }

    public void setSelectPrepaid(boolean selectPrepaid) {
        isSelectPrepaid = selectPrepaid;
        notifyPropertyChanged(BR.selectPrepaid);
    }

    @Bindable
    public boolean isSelectPostpaid() {
        return isSelectPostpaid;
    }

    public void setSelectPostpaid(boolean selectPostpaid) {
        isSelectPostpaid = selectPostpaid;
        notifyPropertyChanged(BR.selectPostpaid);
    }

    @Bindable
    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
        notifyPropertyChanged(BR.textSearch);
    }

    @Bindable
    public String getTxtPassport() {
        return txtPassport;
    }

    public void setTxtPassport(String txtPassport) {
        this.txtPassport = txtPassport;
        notifyPropertyChanged(BR.txtPassport);
    }

    @Bindable
    public int getCountResult() {
        return countResult;
    }

    public void setCountResult(int countResult) {
        this.countResult = countResult;
        notifyPropertyChanged(BR.countResult);
    }

    @Bindable
    public SpinnerAdapter<Data.DataField> getAdapterSpinnerMobileService() {
        return adapterSpinnerMobileService;
    }

    public void setAdapterSpinnerMobileService(
            SpinnerAdapter<Data.DataField> adapterSpinnerMobileService) {
        this.adapterSpinnerMobileService = adapterSpinnerMobileService;
        notifyPropertyChanged(BR.adapterSpinnerMobileService);
    }

    @Bindable
    public ConnectorMobileAdapter getConnectorMobileAdapter() {
        return connectorMobileAdapter;
    }

    public void setConnectorMobileAdapter(ConnectorMobileAdapter connectorMobileAdapter) {
        this.connectorMobileAdapter = connectorMobileAdapter;
        notifyPropertyChanged(BR.connectorMobileAdapter);
    }

    @Bindable
    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
        notifyPropertyChanged(BR.contractList);
    }

    /* --------------------------- End Set Get ---------------------------------- */

    /* --------------------------- onClick View ---------------------------------- */

    public void onCancel() {
        viewConnectorMobile.onCancel();
    }

    public void expandSearch() {
        setHideSearch(!isHideSearch());
        setTextHideSearch();
    }

    private void setTextHideSearch() {
        setTextSearch((isSelectPrepaid ? "Thuê bao trả trước" : "Thuê bao trả sau")
                + " - "
                + "Mobile"
                + " - "
                + (StringUtils.isEmpty(txtPassport) ? "Trống" : txtPassport));
    }

    public void onSearch() {
        if (StringUtils.isEmpty(txtPassport)) {
            viewConnectorMobile.txtPassportEmpty();
            return;
        }
        viewConnectorMobile.showLoading();

        CheckIdNoRequest checkIdNoRequest = new CheckIdNoRequest();
        checkIdNoRequest.setIdNo(txtPassport);
        checkIdNoRequest.setServiceType(mobileServiceList.get(positionMobileService).getCode());
        checkIdNoRequest.setSubType(isSelectPrepaid() ? MobileType.TRA_TRUOC : MobileType.TRA_SAU);

        DataRequest<CheckIdNoRequest> request = new DataRequest<>();
        request.setWsCode(WsCode.CheckIdNo);
        request.setWsRequest(checkIdNoRequest);

        Subscription subscription = qlKhachHangRepository.checkIdNo(request)
                .subscribe(new MBCCSSubscribe<CheckIdNoResponse>() {
                    @Override
                    public void onSuccess(CheckIdNoResponse object) {
                        if (object == null) {

                        } else {
                            customer = object.getCustomer();
                            contractList = object.getContractList();
                            viewConnectorMobile.checkIdNoSuccess(customer, contractList);
                        }
                        viewConnectorMobile.hideLoading();
                    }

                    @Override
                    public void onError(BaseException error) {
                        viewConnectorMobile.hideLoading();
                        viewConnectorMobile.searchError(error);
                    }
                });
        subscriptions.add(subscription);
    }

    public void onClickCreateNew() {
        viewConnectorMobile.onItemClick(-1);
    }
    /* --------------------------- End onClick View ---------------------------------- */

    @Override
    public void onItemClick(int position) {
        viewConnectorMobile.onItemClick(position);
    }
}
