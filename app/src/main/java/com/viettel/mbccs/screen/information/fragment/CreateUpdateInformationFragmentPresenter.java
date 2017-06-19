package com.viettel.mbccs.screen.information.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.constance.Data;
import com.viettel.mbccs.data.model.ApDomain;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.Subscriber;
import com.viettel.mbccs.data.model.UploadImage;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.remote.request.ChecOTPRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainRequest;
import com.viettel.mbccs.data.source.remote.request.GetOTPRequest;
import com.viettel.mbccs.data.source.remote.request.RegisterCustomerInfoRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.CheckOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainResponse;
import com.viettel.mbccs.data.source.remote.response.GetOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.RegisterCustomerInfoResponse;
import com.viettel.mbccs.data.source.remote.response.SpinnerApDomain;
import com.viettel.mbccs.data.source.remote.response.UpdateAllSubInfoResponse;
import com.viettel.mbccs.service.service.UploadImageService;
import com.viettel.mbccs.utils.ImageUtils;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.callback.DrawableClickListener;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class CreateUpdateInformationFragmentPresenter
        implements CreateUpdateInformationFragmentContract.Presenter,
        AdapterView.OnItemSelectedListener {
    private Context context;
    private CreateUpdateInformationFragmentContract.View view;
    private QLKhachHangRepository qlKhachHangRepository;
    private CompositeSubscription subscriptions;

    @CreateUpdateInformationFragment.Type
    private int typeFragment;
    private GetRegiterSubInfoResponse data;
    private Customer customerResponse;
    private Subscriber subscriberResponse;

    private List<ApDomain> dataPassportType;
    private List<ApDomain> dataHTTT;
    private int positionSelectionPassportType;
    private int positionSelectionHTTT;

    public ObservableField<String> title;
    public ObservableField<String> textBtnRegisterUpdate;

    public ObservableField<ArrayAdapter<String>> adapterGoiCuoc;
    public ObservableField<ArrayAdapter<String>> adapterTypePassport;
    public ObservableField<ArrayAdapter<String>> adapterHTThanhToan;

    public ObservableField<String> txtPhoneNumber;
    public ObservableField<String> txtSerial;
    public ObservableField<String> txtNameCustomer;
    public ObservableField<String> txtNumberPassport;
    public ObservableField<Bitmap> imageFront;
    public ObservableField<Bitmap> imageBackside;
    public ObservableField<Bitmap> imagePortrait;

    public ObservableField<String> imageUrlFront;
    public ObservableField<String> imageUrlBackside;
    public ObservableField<String> imageUrlPortrait;
    public ObservableField<String> idProvince;
    public ObservableField<String> idDistrict;
    public ObservableField<String> idPrecinct;
    public ObservableField<String> address;
    public ObservableField<String> textOTP;

    public ObservableBoolean isEnabledTxtNumberPassport;
    public ObservableBoolean isEnabledSelectGender;

    public ObservableBoolean isCheckMale;
    public ObservableBoolean isCheckFemale;
    public ObservableBoolean checkboxSms;
    public ObservableBoolean checkboxEmail;
    public ObservableBoolean checkboxAtHome;
    public ObservableBoolean isShowContractInformation;
    public ObservableBoolean isEnableIsdn;
    public ObservableBoolean isEnableSerial;
    public ObservableInt selectionPassport;

    public CreateUpdateInformationFragmentPresenter(Context context,
            CreateUpdateInformationFragmentContract.View view) {
        this.context = context;
        this.view = view;
        qlKhachHangRepository = QLKhachHangRepository.getInstance();
        subscriptions = new CompositeSubscription();

        title = new ObservableField<>();
        textBtnRegisterUpdate = new ObservableField<>();

        txtPhoneNumber = new ObservableField<>();
        txtSerial = new ObservableField<>();
        adapterGoiCuoc = new ObservableField<>();
        adapterTypePassport = new ObservableField<>();
        adapterHTThanhToan = new ObservableField<>();

        txtNameCustomer = new ObservableField<>();
        txtNumberPassport = new ObservableField<>();
        //        imageFront = new ObservableField<>();
        //        imageBackside = new ObservableField<>();
        //        imagePortrait = new ObservableField<>();

        idProvince = new ObservableField<>();
        idDistrict = new ObservableField<>();
        idPrecinct = new ObservableField<>();
        address = new ObservableField<>();
        textOTP = new ObservableField<>();

        isEnabledTxtNumberPassport = new ObservableBoolean(true);
        isEnabledSelectGender = new ObservableBoolean(true);

        isCheckMale = new ObservableBoolean(true);
        isCheckFemale = new ObservableBoolean();
        checkboxSms = new ObservableBoolean();
        checkboxEmail = new ObservableBoolean();
        checkboxAtHome = new ObservableBoolean();

        isShowContractInformation = new ObservableBoolean();
        isEnableIsdn = new ObservableBoolean(true);
        isEnableSerial = new ObservableBoolean(true);
        selectionPassport = new ObservableInt();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    public void getDataSpinner() {
        view.showLoading();
        Subscription subscription =
                Observable.zip(getDataSpinnerPassport(), getDataSpinnerHTThanhToan(),
                        getDataSpinnerHTNhanTB(),
                        new Func3<GetApDomainResponse, GetApDomainResponse, GetApDomainResponse, SpinnerApDomain>() {
                            @Override
                            public SpinnerApDomain call(GetApDomainResponse response,
                                    GetApDomainResponse response2, GetApDomainResponse response3) {
                                return new SpinnerApDomain(response, response2, response3);
                            }
                        }).flatMap(new Func1<SpinnerApDomain, Observable<SpinnerApDomain>>() {
                    @Override
                    public Observable<SpinnerApDomain> call(SpinnerApDomain domain) {
                        GetApDomainResponse spinnerGiayTo = domain.getSpinnerGiayTo();
                        GetApDomainResponse spinnerHTTT = domain.getSpinnerHTTT();
                        GetApDomainResponse spinnerHTNhanTB = domain.getSpinnerHTNhanTB();
                        if (spinnerGiayTo == null
                                || spinnerGiayTo.getApDomainList().size() == 0
                                || spinnerHTTT == null
                                || spinnerHTTT.getApDomainList().size() == 0
                                || spinnerHTNhanTB == null
                                || spinnerHTNhanTB.getApDomainList().size() == 0) {
                            BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                            baseErrorResponse.setError(201, "");
                            return Observable.error(BaseException.toServerError(baseErrorResponse));
                        } else {
                            return Observable.just(domain);
                        }
                    }
                }).subscribe(new MBCCSSubscribe<SpinnerApDomain>() {
                    @Override
                    public void onSuccess(SpinnerApDomain object) {
                        if (dataPassportType != null && dataPassportType.size() != 0) {
                            dataPassportType.clear();
                        }
                        dataPassportType = object.getSpinnerGiayTo().getApDomainList();
                        view.getDataSpinnerPassportSuccess(dataPassportType);

                        if (typeFragment
                                == CreateUpdateInformationFragment.Type.UPDATE_INFORMATION) {
                            if (dataHTTT != null && dataHTTT.size() != 0) {
                                dataHTTT.clear();
                            }
                            dataHTTT = object.getSpinnerHTTT().getApDomainList();
                            view.getDataHTTTSuccess(dataHTTT);
                        }
                        setData();
                    }

                    @Override
                    public void onError(BaseException error) {
                        view.getDataSpinnerError(error);
                        view.hideLoading();
                    }
                });
        subscriptions.add(subscription);
    }

    public Observable<GetApDomainResponse> getDataSpinnerPassport() {
        DataRequest<GetApDomainRequest> request = new DataRequest<>();
        GetApDomainRequest apDomainRequest = new GetApDomainRequest();
        apDomainRequest.setType(ApDomain.Type.LOAI_GIAY_TO);

        request.setParameterApi(apDomainRequest);
        request.setApiCode(ApiCode.GetListBusTypeIdRequire);

        return qlKhachHangRepository.getApDomain(request);
    }

    private Observable<GetApDomainResponse> getDataSpinnerHTThanhToan() {
        DataRequest<GetApDomainRequest> request = new DataRequest<>();
        GetApDomainRequest apDomainRequest = new GetApDomainRequest();
        apDomainRequest.setType(ApDomain.Type.HINH_THUC_THANH_TOAN);

        request.setParameterApi(apDomainRequest);
        request.setApiCode(ApiCode.GetListBusTypeIdRequire);

        return qlKhachHangRepository.getApDomain(request);
    }

    private Observable<GetApDomainResponse> getDataSpinnerHTNhanTB() {
        DataRequest<GetApDomainRequest> request = new DataRequest<>();
        GetApDomainRequest apDomainRequest = new GetApDomainRequest();
        apDomainRequest.setType(ApDomain.Type.HINH_THUC_NHAN_THONG_BAO_CUOC);

        request.setParameterApi(apDomainRequest);
        request.setApiCode(ApiCode.GetListBusTypeIdRequire);

        return qlKhachHangRepository.getApDomain(request);
    }

    /*---------------------- on Click in View -----------------------------*/

    public void onCancel() {
        view.onCancel();
    }

    public void onClickGetOTP() {
        view.showLoading();
        GetOTPRequest getOTPRequest = new GetOTPRequest();
        getOTPRequest.setIsdn(txtPhoneNumber.get());

        DataRequest<GetOTPRequest> request = new DataRequest<>();
        request.setApiCode(ApiCode.GetOTP);
        request.setParameterApi(getOTPRequest);

        Subscription subscription = qlKhachHangRepository.getOTP(request)
                .subscribe(new MBCCSSubscribe<GetOTPResponse>() {
                    @Override
                    public void onSuccess(GetOTPResponse object) {
                        view.hideLoading();
                    }

                    @Override
                    public void onError(BaseException error) {
                        view.getOTPError(error);
                        view.hideLoading();
                    }
                });
        subscriptions.add(subscription);
    }

    public void onClickRegisterUpdate() {
        view.showLoading();
        if (typeFragment == CreateUpdateInformationFragment.Type.UPDATE_INFORMATION) {
            if (StringUtils.isEmpty(textOTP.get())) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.isOTPEmpty();
                        view.hideLoading();
                    }
                }, 100);
                return;
            }

            ChecOTPRequest checOTPRequest = new ChecOTPRequest();
            checOTPRequest.setIsdn(txtPhoneNumber.get());
            checOTPRequest.setOtp(textOTP.get());

            DataRequest<ChecOTPRequest> request = new DataRequest<>();
            request.setApiCode(ApiCode.CheckOTP);
            request.setParameterApi(checOTPRequest);

            Subscription subscription = qlKhachHangRepository.checOTP(request)
                    .subscribe(new MBCCSSubscribe<CheckOTPResponse>() {
                        @Override
                        public void onSuccess(CheckOTPResponse object) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    view.isSendImage();
                                    // TODO: 6/10/17 fix download offline
                                    //                                    view.hideLoading();
                                }
                            }, 100);
                        }

                        @Override
                        public void onError(BaseException error) {
                            view.checkOTPError(error);
                        }
                    });
            subscriptions.add(subscription);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.isSendImage();
                    // TODO: 6/10/17 fix download offline
                    //                    view.hideLoading();
                }
            }, 100);
        }
    }

    private Customer getDataCustomer() {
        Area areaProvince = view.getAreaProvince();
        Area areaDistrict = view.getAreaDistrict();
        Area areaPrecinct = view.getAreaPrecinct();
        String address = view.getAddress();
        // TODO: 5/31/17
        String sex = "M";

        Customer customer = new Customer();
        customer.setName(txtNameCustomer.get());
        customer.setBirthDate(view.getBirthDate());
        customer.setSex(sex);
        customer.setProvince(areaProvince == null ? "0" : areaProvince.getProvince());
        customer.setDistrict(areaDistrict == null ? "0" : areaDistrict.getDistrict());
        customer.setPrecinct(areaPrecinct == null ? "0" : areaPrecinct.getPrecinct());
        customer.setAddress(address);
        customer.setIdType(1);
        //        customer.setIdType(dataPassportType.get(positionSelectionPassportType).getCode());
        customer.setIdNo(txtNumberPassport.get());
        return customer;
    }

    private Subscriber getDataSubscriber() {
        Subscriber subscriber = new Subscriber();
        subscriber.setSubID(subscriberResponse.getSubID());
        subscriber.setCustReqId(subscriberResponse.getCustReqId());
        subscriber.setSubType(subscriberResponse.getSubType());
        subscriber.setIsdn(txtPhoneNumber.get());
        subscriber.setImsi(subscriberResponse.getImsi());
        subscriber.setSerial(txtSerial.get());
        subscriber.setPackageName(subscriberResponse.getPackageName());

        return subscriber;
    }

    private Contract getDataContract() {
        List<String> noticeChargeList = new ArrayList<>();
        if (checkboxSms.get()) noticeChargeList.add(Contract.TypeNoticeChange.SMS);
        if (checkboxEmail.get()) noticeChargeList.add(Contract.TypeNoticeChange.EMAIL);
        if (checkboxAtHome.get()) noticeChargeList.add(Contract.TypeNoticeChange.AT_HOME);

        Contract contract = new Contract();
        contract.setPayMethodCode(dataHTTT.get(positionSelectionHTTT).getCode());
        contract.setNoticeCharge(noticeChargeList);
        return contract;
    }

    public void clickSendData(boolean isSendImage) {
        // TODO: 6/10/17 fix download offline
        //        view.showLoading();
        Customer customer = getDataCustomer();
        if (StringUtils.isEmpty(customer.getProvince())) {
            view.hideLoading();
            view.customerError();
            return;
        }

        if (StringUtils.isEmpty(getDataSubscriber().getIsdn()) || StringUtils.isEmpty(
                getDataSubscriber().getSerial())) {
            view.hideLoading();
            view.IsdnImsiError();
            return;
        }
        //        if (isSendImage) {
        Intent intent = new Intent(context, UploadImageService.class);
        intent.putParcelableArrayListExtra(UploadImageService.ARG_DATA_INTENT,
                (ArrayList<? extends Parcelable>) getBitmapAndSaveDatabase(customer));
        context.startService(intent);
        //        } else {
        //            getBitmapAndSaveDatabase(getDataCustomer());
        //        }

        if (typeFragment == CreateUpdateInformationFragment.Type.UPDATE_INFORMATION) {
            if (getDataContract().getNoticeCharge().size() == 0) {
                view.hideLoading();
                view.selectNoticeChargeError();
                return;
            }

            UpdateAllSubInfoRequest updateAllSubInfoRequest = new UpdateAllSubInfoRequest();
            updateAllSubInfoRequest.setContract(getDataContract());
            updateAllSubInfoRequest.setCustomer(customer);
            updateAllSubInfoRequest.setSubscriber(getDataSubscriber());

            DataRequest<UpdateAllSubInfoRequest> request = new DataRequest<>();
            request.setParameterApi(updateAllSubInfoRequest);
            request.setApiCode(ApiCode.UpdateAllSubInfo);

            Subscription subscription = qlKhachHangRepository.updateAllSubInfo(request)
                    .subscribe(new MBCCSSubscribe<UpdateAllSubInfoResponse>() {
                        @Override
                        public void onSuccess(UpdateAllSubInfoResponse object) {
                            view.registerUpdateCustomerInfoSuccess(object.getResult(), false);
                            view.hideLoading();
                        }

                        @Override
                        public void onError(BaseException error) {
                            view.updateAllSubInfoError(error);
                            view.hideLoading();
                        }
                    });
            subscriptions.add(subscription);
        } else {

            RegisterCustomerInfoRequest registerCustomerInfoRequest =
                    new RegisterCustomerInfoRequest();
            registerCustomerInfoRequest.setIsdn(getDataSubscriber().getIsdn());
            registerCustomerInfoRequest.setSerial(getDataSubscriber().getSerial());

            registerCustomerInfoRequest.setCustomer(customer);

            DataRequest<RegisterCustomerInfoRequest> request = new DataRequest<>();
            request.setApiCode(ApiCode.RegisterCustomerInfo);
            request.setParameterApi(registerCustomerInfoRequest);

            Subscription subscription = qlKhachHangRepository.registerCustomerInfo(request)
                    .subscribe(new MBCCSSubscribe<RegisterCustomerInfoResponse>() {
                        @Override
                        public void onSuccess(RegisterCustomerInfoResponse object) {
                            view.registerUpdateCustomerInfoSuccess(object.getResult(), true);
                            view.hideLoading();
                        }

                        @Override
                        public void onError(BaseException error) {
                            view.registerCustomerInfoError(error);
                            view.hideLoading();
                        }
                    });
            subscriptions.add(subscription);
        }
    }

    public boolean isExistsImageUpload() {
        return view.imageFront() != null
                || view.imageBackside() != null
                || view.imagePortrait() != null;
    }

    private List<UploadImage> getBitmapAndSaveDatabase(Customer customer) {
        List<UploadImage> uploadImageList = new ArrayList<>();
        Bitmap imageFront = view.imageFront();
        Bitmap imageBackside = view.imageBackside();
        Bitmap imagePortrait = view.imagePortrait();

        if (imageFront != null) {
            UploadImage uploadImage =
                    setDataUploadImage(customer, imageFront, UploadImage.ImageType.FRONT);
            uploadImage.save();
            uploadImageList.add(uploadImage);
        }

        if (imageBackside != null) {
            UploadImage uploadImage =
                    setDataUploadImage(customer, imageBackside, UploadImage.ImageType.BACKSIDE);
            uploadImage.save();
            uploadImageList.add(uploadImage);
        }

        if (imagePortrait != null) {
            UploadImage uploadImage =
                    setDataUploadImage(customer, imagePortrait, UploadImage.ImageType.PORTRAIT);
            uploadImage.save();
            uploadImageList.add(uploadImage);
        }
        return uploadImageList;
    }

    private UploadImage setDataUploadImage(Customer customer, Bitmap bitmap,
            @UploadImage.ImageType int imageType) {
        String imageBase64 =
                ImageUtils.encodeBitmapToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100);
        UploadImage uploadImage = new UploadImage();
        uploadImage.setIdImage(System.currentTimeMillis());
        uploadImage.setActionBusiness("");
        uploadImage.setObjectId(customer.getCustomerId());
        uploadImage.setOrder(imageType);
        uploadImage.setDocType(1);
        uploadImage.setFileName(String.valueOf(System.currentTimeMillis()));
        uploadImage.setImageData(imageBase64);
        uploadImage.setStatus(UploadImage.StatusUpload.WAITING);
        return uploadImage;
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
            case R.id.edit_number_passport:
                txtNumberPassport.set("");
                break;
            case R.id.edit_id_no:
                if (isEnableIsdn.get()) {
                    txtPhoneNumber.set("");
                }
                break;
            case R.id.edit_serial:
                if (isEnableSerial.get()) {
                    txtSerial.set("");
                }
                break;
        }
    }

    /*---------------------- End on Click in View -----------------------------*/

    /*----------------------- Set - Get ---------------------------------------*/
    public void setTypeFragment(int type, GetRegiterSubInfoResponse data) {
        this.typeFragment = type;
        this.data = data;
        switch (typeFragment) {
            case CreateUpdateInformationFragment.Type.CREATE_INFORMATION:
                title.set(context.getString(
                        R.string.fragment_create_update_information_create_title));
                textBtnRegisterUpdate.set(context.getString(
                        R.string.fragment_create_update_information_create_dang_ky));
                isShowContractInformation.set(false);
                break;
            case CreateUpdateInformationFragment.Type.CREATE_INFORMATION_CLONE:
                title.set(context.getString(
                        R.string.fragment_create_update_information_create_clone_title));
                textBtnRegisterUpdate.set(context.getString(
                        R.string.fragment_create_update_information_create_dang_ky));
                isShowContractInformation.set(false);
                break;
            case CreateUpdateInformationFragment.Type.UPDATE_INFORMATION:
                title.set(context.getString(
                        R.string.fragment_create_update_information_update_title));
                textBtnRegisterUpdate.set(context.getString(
                        R.string.fragment_create_update_information_create_cap_nhat));
                isShowContractInformation.set(true);
                break;
        }
    }

    private void setData() {
        if (data == null) return;
        customerResponse = data.getCustomer();
        subscriberResponse = data.getSubscriber();
        if (customerResponse == null || subscriberResponse == null) return;
        // TODO: 6/1/17 set data gói cước
        // set information subscriberResponse
        txtPhoneNumber.set(subscriberResponse.getIsdn());
        if (!StringUtils.isEmpty(subscriberResponse.getIsdn())) {
            isEnableIsdn.set(false);
        }
        txtSerial.set(subscriberResponse.getSerial());
        if (!StringUtils.isEmpty(subscriberResponse.getSerial())) {
            isEnableSerial.set(false);
        }

        // set information customerResponse
        txtNameCustomer.set(customerResponse.getName());
        if (customerResponse.getSex().equals(Data.Gender.MALE)) {
            isCheckMale.set(true);
            isCheckFemale.set(false);
        } else {
            isCheckMale.set(false);
            isCheckFemale.set(true);
        }
        isEnabledSelectGender.set(false);

        address.set(customerResponse.getAddress());
        idProvince.set(customerResponse.getProvince());
        idDistrict.set(customerResponse.getDistrict());
        idPrecinct.set(customerResponse.getPrecinct());
        txtNumberPassport.set(customerResponse.getIdNo());
        if (!StringUtils.isEmpty(customerResponse.getIdNo())) isEnabledTxtNumberPassport.set(false);
        view.setBirthDate(customerResponse.getBirthDate());

        for (int i = 0; i < dataPassportType.size(); i++) {
            if (dataPassportType.get(i)
                    .getCode()
                    .equals(String.valueOf(customerResponse.getIdType()))) {
                selectionPassport.set(i);
                break;
            }
        }
        view.hideLoading();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_select_type_passport:
                positionSelectionPassportType = position;
                break;

            case R.id.spinner_select_httt:
                positionSelectionHTTT = position;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setAdapterPassportType(ArrayAdapter<String> adapter) {
        adapterTypePassport.set(adapter);
    }

    public void setadapterHTThanhToan(ArrayAdapter<String> adapter) {
        adapterHTThanhToan.set(adapter);
    }
    /*----------------------- End Set - Get ---------------------------------------*/
}
