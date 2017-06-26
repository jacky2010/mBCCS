package com.viettel.mbccs.screen.information;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.Subscriber;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetAllSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubInfoResponse;
import com.viettel.mbccs.screen.information.adapter.InformationCustomerAdapter;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.callback.DrawableClickListener;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class CreateUpdateInformationPresenter implements CreateUpdateInformationContract.Presenter {
    private Context context;
    private CreateUpdateInformationContract.View view;
    private QLKhachHangRepository qlKhachHangRepository;
    private CompositeSubscription subscriptions;
    private boolean typeCreate;
    private List<ApDomainByType> dataPassportType;
    private int positionPassportType;

    public ObservableField<InformationCustomerAdapter> informationCustomerAdapter;
    public ObservableField<String> title;
    public ObservableBoolean isHideData;
    public ObservableBoolean isHideBtnCreate;

    public ObservableField<String> isdn;
    public ObservableField<String> idNo;
    public ObservableField<SpinnerAdapter<ApDomainByType>> adapterPassportType;

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
        // TODO: 6/22/17 fix data test
        isdn = new ObservableField<>("628312606");
        idNo = new ObservableField<>("162989033");
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
        if (typeCreate) {
            GetRegisterSubInfoRequest getRegisterSubInfoRequest = new GetRegisterSubInfoRequest();
            getRegisterSubInfoRequest.setIsdn(isdn.get());
            getRegisterSubInfoRequest.setIdNo(idNo.get());
            getRegisterSubInfoRequest.setIdType(
                    dataPassportType.get(positionPassportType).getCode());

            DataRequest<GetRegisterSubInfoRequest> request = new DataRequest<>();
            request.setWsCode(WsCode.GetRegisterSubInfo);
            request.setWsRequest(getRegisterSubInfoRequest);

            Subscription subscription = qlKhachHangRepository.getRegiterSubInfo(request)
                    .subscribe(new MBCCSSubscribe<GetRegisterSubInfoResponse>() {
                        @Override
                        public void onSuccess(GetRegisterSubInfoResponse object) {
                            if (object == null || object.getCustomer() == null) {
                                isHideData.set(true);
                                isHideBtnCreate.set(false);
                            } else {
                                view.onSearchDKTTSuccess(object);
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
        } else {
            GetAllSubInfoRequest getAllSubInfoRequest = new GetAllSubInfoRequest();
            getAllSubInfoRequest.setIsdn(isdn.get());
            getAllSubInfoRequest.setIdNo(idNo.get());
            getAllSubInfoRequest.setIdType(
                    dataPassportType.get(positionPassportType).getCode());

            DataRequest<GetAllSubInfoRequest> request = new DataRequest<>();
            request.setWsCode(WsCode.GetAllSubInfo);
            request.setWsRequest(getAllSubInfoRequest);

            Subscription subscription = qlKhachHangRepository.getAllSubInfo(request)
                    .subscribe(new MBCCSSubscribe<GetAllSubInfoResponse>() {
                        @Override
                        public void onSuccess(GetAllSubInfoResponse object) {
//                            if (object == null || object.getCustomer() == null) {
//                                isHideData.set(true);
//                                isHideBtnCreate.set(false);
//                            } else {
//                                view.onSearchDKTTSuccess(object);
//                                isHideData.set(false);
//                                isHideBtnCreate.set(true);
//                            }
//                            view.hideLoading();
                        }

                        @Override
                        public void onError(BaseException error) {
                            view.hideLoading();
                            view.onSearchError(error);
                        }
                    });
            subscriptions.add(subscription);
        }

        //        isHideData.set(true);
        //        isHideBtnCreate.set(false);
    }

    public void onRegisterNewPayment() {
        GetRegisterSubInfoResponse getRegisterSubInfoResponse = new GetRegisterSubInfoResponse();
        Subscriber subscriber = new Subscriber();
        subscriber.setIsdn(isdn.get());
        Customer customer = new Customer();
        customer.setIdNo(idNo.get());
        customer.setIdType(dataPassportType.get(positionPassportType).getCode());
        getRegisterSubInfoResponse.setCustomer(customer);
        getRegisterSubInfoResponse.setSubscriber(subscriber);
        view.onRegisterNewPayment(getRegisterSubInfoResponse);
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

    public void getDataSpinnerPassport() {
        view.showLoading();
        DataRequest<GetApDomainByTypeRequest> request = new DataRequest<>();
        GetApDomainByTypeRequest getApDomainRequest = new GetApDomainByTypeRequest();
        getApDomainRequest.setType(ApDomainByType.Type.LOAI_GIAY_TO);

        request.setWsRequest(getApDomainRequest);
        request.setWsCode(WsCode.GetApDomainByType);

        Subscription subscription = qlKhachHangRepository.getApDomainByType(request)
                .subscribe(new MBCCSSubscribe<GetApDomainByTypeResponse>() {
                    @Override
                    public void onSuccess(GetApDomainByTypeResponse object) {
                        if (dataPassportType != null && dataPassportType.size() != 0) {
                            dataPassportType.clear();
                        }
                        dataPassportType = object.getApDomainByTypeList();
                        setSpinnerPassportType();
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

    public void onDrawableClick(View v, @DrawableClickListener.DrawablePosition int target) {
        switch (target) {
            case DrawableClickListener.DrawablePosition.RIGHT:
                onDrawableClick(v);
                break;
        }
    }

    private void onDrawableClick(View v) {
        switch (v.getId()) {
            case R.id.edit_isdn:
                isdn.set("");
                break;
            case R.id.edit_id_no:
                idNo.set("");
                break;
        }
    }

    private void setSpinnerPassportType() {
        adapterPassportType.set(new SpinnerAdapter<>(context, dataPassportType));
        adapterPassportType.get()
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        positionPassportType = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
    }
}
