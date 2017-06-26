package com.viettel.mbccs.screen.information.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.constance.Data;
import com.viettel.mbccs.constance.MobileService;
import com.viettel.mbccs.constance.MobileType;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.Product;
import com.viettel.mbccs.data.model.Subscriber;
import com.viettel.mbccs.data.model.UploadImage;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.ChecOTPRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProductRequest;
import com.viettel.mbccs.data.source.remote.request.GetOTPRequest;
import com.viettel.mbccs.data.source.remote.request.RegisterCustomerInfoRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.CheckOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetAllSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProductResponse;
import com.viettel.mbccs.data.source.remote.response.GetOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.RegisterCustomerInfoResponse;
import com.viettel.mbccs.data.source.remote.response.SpinnerApDomain;
import com.viettel.mbccs.data.source.remote.response.UpdateAllSubInfoResponse;
import com.viettel.mbccs.service.service.UploadImageService;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.utils.ImageUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.callback.DrawableClickListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func4;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 08:06.
 */

public class CreateUpdateInformationFragmentPresenter
        implements CreateUpdateInformationFragmentContract.Presenter {
    private final int TimeDelayHideDialogLoading = 100;
    private final String StringNull = null;
    private final Integer IntegerNull = null;
    private Context context;
    private CreateUpdateInformationFragmentContract.View view;
    private QLKhachHangRepository qlKhachHangRepository;
    private UserRepository userRepository;
    private CompositeSubscription subscriptions;

    @CreateUpdateInformationFragment.Type
    private int typeFragment;
    private GetRegisterSubInfoResponse dataRegister;
    private GetAllSubInfoResponse dataUpdate;
    private Customer customerResponse;
    private Subscriber subscriberResponse;
    private Contract contractResponse;

    private List<Product> dataGoiCuoc;
    private List<ApDomainByType> dataPassportType;
    private List<ApDomainByType> dataHTTT;
    private int positionSelectionPassportType;
    private int positionSelectionHTTT;
    private int positionSelectionGoiCuoc;

    public ObservableField<String> title;
    public ObservableField<String> textBtnRegisterUpdate;

    public ObservableField<SpinnerAdapter<Product>> adapterGoiCuoc;
    public ObservableField<SpinnerAdapter<ApDomainByType>> adapterTypePassport;
    public ObservableField<SpinnerAdapter<ApDomainByType>> adapterHTThanhToan;

    public ObservableField<String> txtPhoneNumber;
    public ObservableField<String> txtSerial;
    public ObservableField<String> txtNameCustomer;
    public ObservableField<String> txtNumberPassport;
    
    public ObservableField<String> imageUrlFront;
    public ObservableField<String> imageUrlBackside;
    public ObservableField<String> imageUrlPortrait;
    public ObservableField<String> idProvince;
    public ObservableField<String> idDistrict;
    public ObservableField<String> idPrecinct;
    public ObservableField<String> address;
    public ObservableField<String> textOTP;
    public ObservableField<String> setDate;

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
    public ObservableBoolean isEnableIdNo;
    public ObservableInt selectionPassport;
    public ObservableInt selectionHTTT;

    CreateUpdateInformationFragmentPresenter(Context context,
            CreateUpdateInformationFragmentContract.View view) {
        this.context = context;
        this.view = view;
        qlKhachHangRepository = QLKhachHangRepository.getInstance();
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();

        title = new ObservableField<>();
        textBtnRegisterUpdate = new ObservableField<>();

        txtPhoneNumber = new ObservableField<>();
        txtSerial = new ObservableField<>();
        adapterGoiCuoc = new ObservableField<>();
        adapterTypePassport = new ObservableField<>();
        adapterHTThanhToan = new ObservableField<>();
        adapterGoiCuoc = new ObservableField<>();

        txtNameCustomer = new ObservableField<>();
        txtNumberPassport = new ObservableField<>();

        idProvince = new ObservableField<>();
        idDistrict = new ObservableField<>();
        idPrecinct = new ObservableField<>();
        address = new ObservableField<>();
        textOTP = new ObservableField<>();
        setDate = new ObservableField<>();

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
        isEnableIdNo = new ObservableBoolean(true);
        selectionPassport = new ObservableInt();
        selectionHTTT = new ObservableInt();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    void getDataSpinner() {
        view.showLoading();
        Subscription subscription =
                Observable.zip(getDataSpinnerPassport(), getDataSpinnerHTThanhToan(),
                        getDataSpinnerHTNhanTB(), getDataListProduct(),
                        new Func4<GetApDomainByTypeResponse, GetApDomainByTypeResponse, GetApDomainByTypeResponse, GetListProductResponse, SpinnerApDomain>() {
                            @Override
                            public SpinnerApDomain call(GetApDomainByTypeResponse response,
                                    GetApDomainByTypeResponse response2,
                                    GetApDomainByTypeResponse response3,
                                    GetListProductResponse response4) {
                                return new SpinnerApDomain(response, response2, response3,
                                        response4);
                            }
                        }).flatMap(new Func1<SpinnerApDomain, Observable<SpinnerApDomain>>() {
                    @Override
                    public Observable<SpinnerApDomain> call(SpinnerApDomain domain) {
                        GetApDomainByTypeResponse spinnerGiayTo = domain.getSpinnerGiayTo();
                        GetApDomainByTypeResponse spinnerHTTT = domain.getSpinnerHTTT();
                        GetApDomainByTypeResponse spinnerHTNhanTB = domain.getSpinnerHTNhanTB();
                        GetListProductResponse spinnerListProduct = domain.getSpinnerListProduct();
                        if (spinnerGiayTo == null
                                || spinnerGiayTo.getApDomainByTypeList().size() == 0
                                || spinnerHTTT == null
                                || spinnerHTTT.getApDomainByTypeList().size() == 0
                                || spinnerHTNhanTB == null
                                || spinnerHTNhanTB.getApDomainByTypeList().size() == 0
                                || spinnerListProduct == null) {
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
                        dataPassportType = object.getSpinnerGiayTo().getApDomainByTypeList();

                        if (dataGoiCuoc != null && dataGoiCuoc.size() != 0) {
                            dataGoiCuoc.clear();
                        }

                        dataGoiCuoc = object.getSpinnerListProduct().getListProduct();
                        if (typeFragment
                                == CreateUpdateInformationFragment.Type.UPDATE_INFORMATION) {
                            if (dataHTTT != null && dataHTTT.size() != 0) {
                                dataHTTT.clear();
                            }
                            dataHTTT = object.getSpinnerHTTT().getApDomainByTypeList();
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

    private Observable<GetApDomainByTypeResponse> getDataSpinnerPassport() {
        DataRequest<GetApDomainByTypeRequest> request = new DataRequest<>();
        GetApDomainByTypeRequest apDomainRequest = new GetApDomainByTypeRequest();
        apDomainRequest.setType(ApDomainByType.Type.LOAI_GIAY_TO);

        request.setWsRequest(apDomainRequest);
        request.setWsCode(WsCode.GetApDomainByType);

        return qlKhachHangRepository.getApDomainByType(request);
    }

    private Observable<GetApDomainByTypeResponse> getDataSpinnerHTThanhToan() {
        DataRequest<GetApDomainByTypeRequest> request = new DataRequest<>();
        GetApDomainByTypeRequest apDomainRequest = new GetApDomainByTypeRequest();
        apDomainRequest.setType(ApDomainByType.Type.HINH_THUC_THANH_TOAN);

        request.setWsRequest(apDomainRequest);
        request.setWsCode(WsCode.GetApDomainByType);

        return qlKhachHangRepository.getApDomainByType(request);
    }

    private Observable<GetApDomainByTypeResponse> getDataSpinnerHTNhanTB() {
        DataRequest<GetApDomainByTypeRequest> request = new DataRequest<>();
        GetApDomainByTypeRequest apDomainRequest = new GetApDomainByTypeRequest();
        apDomainRequest.setType(ApDomainByType.Type.HINH_THUC_NHAN_THONG_BAO_CUOC);

        request.setWsRequest(apDomainRequest);
        request.setWsCode(WsCode.GetApDomainByType);

        return qlKhachHangRepository.getApDomainByType(request);
    }

    private Observable<GetListProductResponse> getDataListProduct() {
        GetListProductRequest getListProductRequest = new GetListProductRequest();
        getListProductRequest.setSubType(MobileType.TRA_TRUOC);
        getListProductRequest.setTelServiceId(MobileService.Mobile);

        DataRequest<GetListProductRequest> request = new DataRequest<>();
        request.setWsRequest(getListProductRequest);
        request.setWsCode(WsCode.GetListProduct);
        return qlKhachHangRepository.getListProduct(request);
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
        request.setWsCode(WsCode.GetOTP);
        request.setWsRequest(getOTPRequest);

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
                }, TimeDelayHideDialogLoading);
                return;
            }

            ChecOTPRequest checOTPRequest = new ChecOTPRequest();
            checOTPRequest.setIsdn(txtPhoneNumber.get());
            checOTPRequest.setOtp(textOTP.get());

            DataRequest<ChecOTPRequest> request = new DataRequest<>();
            request.setWsCode(WsCode.CheckOTP);
            request.setWsRequest(checOTPRequest);

            Subscription subscription = qlKhachHangRepository.checOTP(request)
                    .subscribe(new MBCCSSubscribe<CheckOTPResponse>() {
                        @Override
                        public void onSuccess(CheckOTPResponse object) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    view.isSendImage();
                                    view.hideLoading();
                                }
                            }, TimeDelayHideDialogLoading);
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
                    view.hideLoading();
                }
            }, TimeDelayHideDialogLoading);
        }
    }

    private Customer getDataCustomer() {
        Area areaProvince = view.getAreaProvince();
        Area areaDistrict = view.getAreaDistrict();
        Area areaPrecinct = view.getAreaPrecinct();

        Customer customer = new Customer();

        customer.setAddress(view.getAddress());
        customer.setAreaCode(areaPrecinct.getAreaCode());
        customer.setBirthDate(view.getBirthDate());
        customer.setBusType(
                customerResponse != null && customerResponse.getBusType() != null ? customerResponse
                        .getBusType() : StringNull);

        customer.setCustId(customerResponse != null && customerResponse.getCustId() != null
                ? customerResponse.getCustId() : null);

        customer.setDistrict(areaDistrict == null ? StringNull : areaDistrict.getDistrict());
        //        customer.setHome(StringNull);
        //        customer.setIdIssueDate(StringNull);
        customer.setIdNo(txtNumberPassport.get());
        customer.setIdType(dataPassportType.get(positionSelectionPassportType).getCode());
        customer.setName(txtNameCustomer.get());

        customer.setNationality(
                customerResponse != null && customerResponse.getNationality() != null
                        ? customerResponse.getNationality() : StringNull);

        customer.setPrecinct(areaPrecinct == null ? StringNull : areaPrecinct.getPrecinct());
        customer.setProvince(areaProvince == null ? StringNull : areaProvince.getProvince());
        customer.setSex(isCheckFemale.get() ? Data.Gender.FEMALE : Data.Gender.MALE);

        customer.setStatus(customerResponse != null && customerResponse.getStatus() != null
                ? customerResponse.getStatus() : null);

        //        customer.setStreetBlockName(StringNull);
        //        customer.setStreetName(StringNull);
        return customer;
    }

    private Subscriber getDataSubscriber() {
        Subscriber subscriber = new Subscriber();
        subscriber.setSubID(
                subscriberResponse == null ? IntegerNull : subscriberResponse.getSubID());
        subscriber.setIsdn(txtPhoneNumber.get());
        subscriber.setCustReqId(
                subscriberResponse == null ? IntegerNull : subscriberResponse.getCustReqId());
        subscriber.setImsi(subscriberResponse == null ? StringNull : subscriberResponse.getImsi());
        subscriber.setSerial(txtSerial.get());
        subscriber.setPackageName(
                subscriberResponse == null ? StringNull : subscriberResponse.getPackageName());
        subscriber.setTelecomServiceId(MobileService.Mobile);

        switch (typeFragment) {
            case CreateUpdateInformationFragment.Type.CREATE_INFORMATION:
                subscriber.setSubType(MobileType.TRA_TRUOC);
                break;
            case CreateUpdateInformationFragment.Type.CREATE_INFORMATION_CLONE:
                break;
            case CreateUpdateInformationFragment.Type.UPDATE_INFORMATION:
                break;
        }

        return subscriber;
    }

    private Contract getDataContract() {
        List<String> noticeChargeList = new ArrayList<>();
        if (checkboxSms.get()) noticeChargeList.add(Contract.TypeNoticeChange.SMS);
        if (checkboxEmail.get()) noticeChargeList.add(Contract.TypeNoticeChange.EMAIL);
        if (checkboxAtHome.get()) noticeChargeList.add(Contract.TypeNoticeChange.AT_HOME);

        Contract contract;
        if (contractResponse != null) {
            contract = contractResponse;
        } else {
            contract = new Contract();
        }
        contract.setPayMethodCode(dataHTTT.get(positionSelectionHTTT).getCode());
        contract.setNoticeCharge(noticeChargeList);

        return contract;
    }

    private boolean compareDate() {
        Date birthDate = DateUtils.stringToDate(getDataCustomer().getBirthDate(),
                DateUtils.CALENDAR_DATE_FORMAT_DD_MM_YY, Locale.getDefault());

        Date date = new Date(System.currentTimeMillis());
        return birthDate.before(date);
    }

    void clickSendData(boolean isSendImage) {
        view.showLoading();
        Customer customer = getDataCustomer();
        if (StringUtils.isEmpty(customer.getProvince())
                || StringUtils.isEmpty(customer.getName())
                || StringUtils.isEmpty(customer.getIdNo())
                || !compareDate()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.hideLoading();
                    view.customerError();
                }
            }, TimeDelayHideDialogLoading);
            return;
        }

        if (StringUtils.isEmpty(getDataSubscriber().getIsdn()) || StringUtils.isEmpty(
                getDataSubscriber().getSerial())) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.hideLoading();
                    view.IsdnImsiError();
                }
            }, TimeDelayHideDialogLoading);
            return;
        }

        if (isSendImage) {
            Intent intent = new Intent(context, UploadImageService.class);
            intent.putParcelableArrayListExtra(UploadImageService.ARG_DATA_INTENT,
                    (ArrayList<? extends Parcelable>) getBitmapAndSaveDatabase(customer));
            context.startService(intent);
        } else {
            getBitmapAndSaveDatabase(getDataCustomer());
        }

        if (typeFragment == CreateUpdateInformationFragment.Type.UPDATE_INFORMATION) {
            if (getDataContract().getNoticeCharge().size() == 0) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.hideLoading();
                        view.selectNoticeChargeError();
                    }
                }, TimeDelayHideDialogLoading);
                return;
            }

            UpdateAllSubInfoRequest updateAllSubInfoRequest = new UpdateAllSubInfoRequest();
            updateAllSubInfoRequest.setContract(getDataContract());
            updateAllSubInfoRequest.setCustomer(customer);
            updateAllSubInfoRequest.setSubscriber(getDataSubscriber());

            DataRequest<UpdateAllSubInfoRequest> request = new DataRequest<>();
            request.setWsRequest(updateAllSubInfoRequest);
            request.setWsCode(WsCode.UpdateAllSubInfo);

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
            UserInfo userInfo = userRepository.getUserInfo();

            RegisterCustomerInfoRequest registerCustomerInfoRequest =
                    new RegisterCustomerInfoRequest();
            registerCustomerInfoRequest.setIsdn(getDataSubscriber().getIsdn());
            registerCustomerInfoRequest.setSerial(getDataSubscriber().getSerial());
            registerCustomerInfoRequest.setSubType(MobileType.TRA_TRUOC);
            registerCustomerInfoRequest.setShopCode(userInfo.getShop().getShopCode());
            registerCustomerInfoRequest.setStaffCode(userInfo.getStaffInfo().getStaffCode());

            registerCustomerInfoRequest.setCustomer(customer);

            DataRequest<RegisterCustomerInfoRequest> request = new DataRequest<>();
            request.setWsCode(WsCode.RegisterCustomerInfo);
            request.setWsRequest(registerCustomerInfoRequest);

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

    boolean isExistsImageUpload() {
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
            case DrawableClickListener.DrawablePosition.BOTTOM:
                break;
            case DrawableClickListener.DrawablePosition.LEFT:
                break;
            case DrawableClickListener.DrawablePosition.TOP:
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
    void setTypeFragment(int type, @Nullable GetRegisterSubInfoResponse dataRegister,
            @Nullable GetAllSubInfoResponse dataUpdate) {
        this.typeFragment = type;
        switch (typeFragment) {
            case CreateUpdateInformationFragment.Type.CREATE_INFORMATION:
                title.set(context.getString(
                        R.string.fragment_create_update_information_create_title));
                this.dataRegister = dataRegister;

                customerResponse = dataRegister.getCustomer();
                subscriberResponse = dataRegister.getSubscriber();

                textBtnRegisterUpdate.set(context.getString(
                        R.string.fragment_create_update_information_create_dang_ky));
                isShowContractInformation.set(false);
                break;
            case CreateUpdateInformationFragment.Type.CREATE_INFORMATION_CLONE:
                title.set(context.getString(
                        R.string.fragment_create_update_information_create_clone_title));
                this.dataRegister = dataRegister;

                customerResponse = dataRegister.getCustomer();
                subscriberResponse = dataRegister.getSubscriber();
                textBtnRegisterUpdate.set(context.getString(
                        R.string.fragment_create_update_information_create_dang_ky));
                isShowContractInformation.set(false);
                break;
            case CreateUpdateInformationFragment.Type.UPDATE_INFORMATION:
                title.set(context.getString(
                        R.string.fragment_create_update_information_update_title));
                this.dataUpdate = dataUpdate;

                customerResponse = dataUpdate.getCustomer();
                subscriberResponse = dataUpdate.getSubscriber();
                contractResponse = dataUpdate.getContract();

                textBtnRegisterUpdate.set(context.getString(
                        R.string.fragment_create_update_information_create_cap_nhat));
                isShowContractInformation.set(true);
                break;
        }
    }

    private void setData() {
        if (dataPassportType != null && dataPassportType.size() != 0) {
            adapterTypePassport.set(new SpinnerAdapter<>(context, dataPassportType));
            adapterTypePassport.get()
                    .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                long id) {
                            positionSelectionPassportType = position;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
        }

        if (dataGoiCuoc != null && dataGoiCuoc.size() != 0) {
            adapterGoiCuoc.set(new SpinnerAdapter<>(context, dataGoiCuoc));
            adapterGoiCuoc.get()
                    .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                long id) {
                            positionSelectionGoiCuoc = position;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
        }

        if (dataHTTT != null && dataHTTT.size() != 0) {
            adapterHTThanhToan.set(new SpinnerAdapter<>(context, dataHTTT));
            adapterHTThanhToan.get()
                    .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                long id) {
                            positionSelectionHTTT = position;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
        }

        if (dataRegister == null || dataUpdate == null) {
            view.hideLoading();
            return;
        }

        if (subscriberResponse != null) {
            txtPhoneNumber.set(subscriberResponse.getIsdn());
            if (!StringUtils.isEmpty(subscriberResponse.getIsdn())) {
                isEnableIsdn.set(false);
            }
            // TODO: 6/26/17
            subscriberResponse.setSerial("8925509002901845693");
            txtSerial.set(subscriberResponse.getSerial());
            if (!StringUtils.isEmpty(subscriberResponse.getSerial())) {
                isEnableSerial.set(false);
            }
        }
        if (customerResponse != null) {
            txtNameCustomer.set(customerResponse.getName());
            if (customerResponse.getSex() != null && customerResponse.getSex()
                    .equals(Data.Gender.MALE)) {
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
            if (!StringUtils.isEmpty(customerResponse.getIdNo())) {
                isEnabledTxtNumberPassport.set(false);
            }
            if (customerResponse.getBirthDate() != null) {
                setDate.set(customerResponse.getBirthDate());
            }

            for (int i = 0; i < dataPassportType.size(); i++) {
                if (dataPassportType.get(i).getCode().equals(customerResponse.getIdType())) {
                    selectionPassport.set(i);
                    break;
                }
            }
        }
        if (contractResponse != null) {
            if (contractResponse.getNoticeCharge() != null
                    && contractResponse.getNoticeCharge().size() != 0) {
                for (String s : contractResponse.getNoticeCharge()) {
                    if (s.equals(Contract.TypeNoticeChange.SMS)) {
                        checkboxSms.set(true);
                    }

                    if (s.equals(Contract.TypeNoticeChange.EMAIL)) {
                        checkboxEmail.set(true);
                    }

                    if (s.equals(Contract.TypeNoticeChange.AT_HOME)) {
                        checkboxAtHome.set(true);
                    }
                }
            }

            if (contractResponse.getPayMethodCode() != null) {
                for (int i = 0; i < dataHTTT.size(); i++) {
                    if (dataHTTT.get(i).getCode().equals(contractResponse.getPayMethodCode())) {
                        selectionHTTT.set(i);
                        break;
                    }
                }
            }
        }
        view.hideLoading();
    }

    /*----------------------- End Set - Get ---------------------------------------*/
}
