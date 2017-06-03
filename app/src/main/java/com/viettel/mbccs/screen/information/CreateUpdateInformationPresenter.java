package com.viettel.mbccs.screen.information;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.ApDomain;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegiterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetApDomainResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
import com.viettel.mbccs.screen.information.adapter.InformationCustomerAdapter;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class CreateUpdateInformationPresenter
        implements CreateUpdateInformationContract.Presenter, AdapterView.OnItemSelectedListener {
    private Context context;
    private CreateUpdateInformationContract.View view;
    private QLKhachHangRepository qlKhachHangRepository;
    private CompositeSubscription subscriptions;
    private boolean typeCreate;
    private List<ApDomain> dataPassportType;
    private int positionPassportType;

    public ObservableField<InformationCustomerAdapter> informationCustomerAdapter;
    public ObservableField<String> title;
    public ObservableBoolean isHideData;
    public ObservableBoolean isHideBtnCreate;

    public ObservableField<String> isdn;
    public ObservableField<String> idNo;
    public ObservableField<ArrayAdapter<String>> adapterPassportType;

    public CreateUpdateInformationPresenter(Context context,
            CreateUpdateInformationContract.View view) {
        this.context = context;
        this.view = view;
        qlKhachHangRepository = QLKhachHangRepository.getInstance();
        subscriptions = new CompositeSubscription();

        informationCustomerAdapter = new ObservableField<>();
        title = new ObservableField<>();
        isHideData = new ObservableBoolean();
        isHideBtnCreate = new ObservableBoolean();

        isdn = new ObservableField<>();
        idNo = new ObservableField<>();
        adapterPassportType = new ObservableField<>();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    public void onCancel() {
        view.onCancel();
    }

    public void onSearch() {
        if (StringUtils.isEmpty(isdn.get()) || StringUtils.isEmpty(idNo.get())) {
            view.showDialogValidate();
            return;
        }
        view.showLoading();
        GetRegiterSubInfoRequest getRegiterSubInfoRequest = new GetRegiterSubInfoRequest();
        getRegiterSubInfoRequest.setIsdn(isdn.get());
        getRegiterSubInfoRequest.setIdNo(idNo.get());
        getRegiterSubInfoRequest.setIdType(dataPassportType.get(positionPassportType).getCode());

        DataRequest<GetRegiterSubInfoRequest> request = new DataRequest<>();
        request.setApiCode(ApiCode.GetRegiterSubInfo);
        request.setParameterApi(getRegiterSubInfoRequest);
        //
        Subscription subscription = qlKhachHangRepository.getRegiterSubInfo(request)
                .subscribe(new MBCCSSubscribe<GetRegiterSubInfoResponse>() {
                    @Override
                    public void onSuccess(GetRegiterSubInfoResponse object) {
                        if (object.getCustomer() == null) {
                            isHideData.set(true);
                            isHideBtnCreate.set(false);
                        } else {
                            view.onSearchSuccess(object);
                            isHideData.set(false);
                            isHideBtnCreate.set(true);
                        }
                        view.hideLoading();
                    }

                    @Override
                    public void onError(BaseException error) {
                        view.hideLoading();
                        view.onSearchError(error);
                    }
                });
        subscriptions.add(subscription);

        //        isHideData.set(true);
        //        isHideBtnCreate.set(false);
    }

    public void onRegisterNewPayment() {
        view.onRegisterNewPayment();
    }

    public void setInformationCustomerAdapter(InformationCustomerAdapter adapter) {
        informationCustomerAdapter.set(adapter);
    }

    public void setTypeCreate(boolean typeCreate) {
        this.typeCreate = typeCreate;
        title.set(
                this.typeCreate ? context.getString(R.string.create_update_information_create_title)
                        : context.getString(R.string.create_update_information_update_title));
    }

    public void setAdapterPassportType(ArrayAdapter<String> adapter) {
        adapterPassportType.set(adapter);
    }

    public void getDataSpinnerPassport() {
        view.showLoading();
        DataRequest<GetApDomainRequest> request = new DataRequest<>();
        GetApDomainRequest getApDomainRequest = new GetApDomainRequest();
        getApDomainRequest.setType(ApDomain.Type.LOAI_GIAY_TO);

        request.setParameterApi(getApDomainRequest);
        request.setApiCode(ApiCode.GetListBusTypeIdRequire);

        Subscription subscription = qlKhachHangRepository.getApDomain(request)
                .subscribe(new MBCCSSubscribe<GetApDomainResponse>() {
                    @Override
                    public void onSuccess(GetApDomainResponse object) {
                        if (dataPassportType != null && dataPassportType.size() != 0) {
                            dataPassportType.clear();
                        }
                        dataPassportType = object.getApDomainList();
                        view.getDataSpinnerPassportSuccess(dataPassportType);
                        view.hideLoading();
                    }

                    @Override
                    public void onError(BaseException error) {
                        view.getDataSpinnerPassportError(error);
                        view.hideLoading();
                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_select_passport_type:
                positionPassportType = position;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
