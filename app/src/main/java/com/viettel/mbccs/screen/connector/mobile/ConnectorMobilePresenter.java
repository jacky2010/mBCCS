package com.viettel.mbccs.screen.connector.mobile;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.BR;
import com.viettel.mbccs.R;
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

    private boolean isSelectPrepaid;
    private boolean isSelectPostpaid;
    private String stringPrepaid;
    private String stringPostpaid;

    private String textSearch;
    private String txtPassport;
    private String txtPassportError;
    private boolean isHideContractList;
    private boolean isHideCreate;

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
        mobileServiceList = Data.connectorTelServiceType();
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
        stringPrepaid = context.getString(R.string.connector_mobile_thue_bao_tra_truoc);
        stringPostpaid = context.getString(R.string.connector_mobile_thue_bao_tra_sau);
        setHideContractList(true);
        setHideCreate(true);
        setTxtPassport("17150362");
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    /* --------------------------- Set Get ---------------------------------- */
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

    @Bindable
    public String getTxtPassportError() {
        return txtPassportError;
    }

    public void setTxtPassportError(String txtPassportError) {
        this.txtPassportError = txtPassportError;
        notifyPropertyChanged(BR.txtPassportError);
    }

    @Bindable
    public boolean isHideContractList() {
        return isHideContractList;
    }

    public void setHideContractList(boolean hideContractList) {
        isHideContractList = hideContractList;
        notifyPropertyChanged(BR.hideContractList);
    }

    @Bindable
    public boolean isHideCreate() {
        return isHideCreate;
    }

    public void setHideCreate(boolean hideCreate) {
        isHideCreate = hideCreate;
        notifyPropertyChanged(BR.hideCreate);
    }

    /* --------------------------- End Set Get ---------------------------------- */

    /* --------------------------- onClick View ---------------------------------- */

    public void onCancel() {
        viewConnectorMobile.onCancel();
    }

    public void setTextHideSearch() {
        setTextSearch(
                (isSelectPrepaid ? stringPrepaid : stringPostpaid) + " - " + mobileServiceList.get(
                        positionMobileService).getName() + " - " + (StringUtils.isEmpty(txtPassport)
                        ? context.getString(R.string.connector_mobile_empty) : txtPassport));
    }

    public void onSearch() {
        if (StringUtils.isEmpty(txtPassport)) {
            setTxtPassportError(context.getString(R.string.input_empty));
            return;
        }
        viewConnectorMobile.showLoading();

        final CheckIdNoRequest checkIdNoRequest = new CheckIdNoRequest();
        checkIdNoRequest.setIdNo(txtPassport);
        checkIdNoRequest.setServiceType(mobileServiceList.get(positionMobileService).getCode());
        checkIdNoRequest.setSubType(isSelectPrepaid() ? MobileType.TRA_TRUOC : MobileType.TRA_SAU);

        DataRequest<CheckIdNoRequest> request = new DataRequest<>();
        request.setWsCode(WsCode.CheckIdNo);
        request.setWsRequest(checkIdNoRequest);

        final Subscription subscription = qlKhachHangRepository.checkIdNo(request)
                .subscribe(new MBCCSSubscribe<CheckIdNoResponse>() {
                    @Override
                    public void onSuccess(CheckIdNoResponse object) {
                        if (object == null) {
                            // TODO: 7/4/17
                            return;
                        }
                        customer = object.getCustomer();
                        if (customer == null) {
                            return;
                        }
                        contractList = object.getContractList();
                        if (contractList != null && contractList.size() != 0) {
                            setHideContractList(false);
                            setHideCreate(true);
                            viewConnectorMobile.checkIdNoSuccess(customer, contractList);
                        } else {
                            setHideContractList(true);
                            setHideCreate(false);
                            viewConnectorMobile.getCustomerSuccess(customer);
                        }

                        viewConnectorMobile.setDataFormSearch(checkIdNoRequest);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewConnectorMobile.closeFormSearch();
                            }
                        }, 100);
                    }

                    @Override
                    public void onError(BaseException error) {
                        viewConnectorMobile.searchError(error);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        viewConnectorMobile.hideLoading();
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
