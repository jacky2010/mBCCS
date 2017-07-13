package com.viettel.mbccs.screen.connector.fragment.prepaid;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.BR;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.Data;
import com.viettel.mbccs.constance.Gender;
import com.viettel.mbccs.constance.MobileType;
import com.viettel.mbccs.constance.TelServiceId;
import com.viettel.mbccs.constance.TelServiceType;
import com.viettel.mbccs.constance.WarehouseType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.model.BusType;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.ContractBank;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.Product;
import com.viettel.mbccs.data.model.RegType;
import com.viettel.mbccs.data.model.SubType;
import com.viettel.mbccs.data.model.Subscriber;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.CheckIdNoRequest;
import com.viettel.mbccs.data.source.remote.request.ConnectSubscriberRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProductRequest;
import com.viettel.mbccs.data.source.remote.request.GetListRegTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSubTypeRequest;
import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProductResponse;
import com.viettel.mbccs.data.source.remote.response.GetListRegTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSubTypeResponse;
import com.viettel.mbccs.screen.connector.listener.OnPageChange;
import com.viettel.mbccs.utils.DatabaseUtils;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.utils.PatternUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.model.AddressApp;
import java.util.Date;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func4;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public class CreateNewConnectorInformationPrepaidFragmentPresenter extends BaseObservable
        implements CreateNewConnectorInformationPrepaidFragmentContract.Presenter {

    private Context context;
    private CreateNewConnectorInformationPrepaidFragmentContract.ViewFragment1 createNewView1;
    private CreateNewConnectorInformationPrepaidFragmentContract.ViewFragment2 createNewView2;
    private QLKhachHangRepository qlKhachHangRepository;
    private UserRepository userRepository;
    private CompositeSubscription subscriptions;
    private OnPageChange onPageChange;
    private String stringError;
    private String stringLoadDataError;

    private Customer customer;
    private Contract contract;
    private CheckIdNoRequest checkIdNoRequest;

    private Customer customerCurrent;
    private Subscriber subscriberCurrent;
    private Contract contractCurrent;
    private ContractBank contractBankCurrent;

    private String nameCustomer;
    private String dateBirthday;
    private Date maxDateBirthday;
    private Date minDateBirthday;
    private String txtNumberPassport;
    private String dateCreatePassport;
    private Date maxDateCreatePassport;
    private Date minDateCreatePassport;
    private String outDatePassport;
    private Date maxOutDatePassport;
    private Date minOutDatePassport;
    private String placeCreatePassport;
    private AddressApp area1;
    private AddressApp area2;

    private boolean isCheckMale;
    private boolean isCheckFemale;
    private int selectionPassport;

    private Bitmap customerCurrentImageFront;
    private Bitmap customerCurrentImageBackside;
    private Bitmap customerCurrentImagePortrait;
    private String imageUrlFront;
    private String imageUrlBackside;
    private String imageUrlPortrait;

    private String isdn;
    private String serial;
    private String informationStock;

    private String nameCustomerError;
    private String txtNumberPassportError;
    private String placeCreatePassportError;
    private String isdnError;
    private String serialError;
    private String informationStockError;

    private SpinnerAdapter<BusType> adapterSpinnerCustomerType;
    private SpinnerAdapter<ApDomainByType> adapterSpinnerPassportType;

    private SpinnerAdapter<Data.DataField> adapterSpinner2DichVu;
    private SpinnerAdapter<Product> adapterSpinner2GoiCuoc;
    private SpinnerAdapter<SubType> adapterSpinner2LoaiThueBao;
    private SpinnerAdapter<RegType> adapterSpinner2HTHoaMang;

    private List<BusType> dataSpinnerCustomerType;
    private List<ApDomainByType> dataSpinnerPassportType;
    private List<Data.DataField> dataSpinner2DichVu;
    private List<Product> dataSpinner2GoiCuoc;
    private List<SubType> dataSpinner2LoaiThueBao;
    private List<RegType> dataSpinner2HTHoaMang;

    private int positionSpinnerCustomerType;
    private int positionSpinnerPassportType;
    private int positionSpinner2DichVu;
    private int positionSpinner2GoiCuoc;
    private int positionSpinner2LoaiThueBao;
    private int positionSpinner2HTHoaMang;

    public CreateNewConnectorInformationPrepaidFragmentPresenter(Context context, Customer customer,
            Contract contract, CheckIdNoRequest checkIdNoRequest) {
        this.context = context;

        qlKhachHangRepository = QLKhachHangRepository.getInstance();
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();
        stringError = context.getString(R.string.input_empty);
        stringLoadDataError = context.getString(R.string.error_load_data);
        this.customer = customer;
        this.contract = contract;
        this.checkIdNoRequest = checkIdNoRequest;
    }

    public void setView1(CreateNewConnectorInformationPrepaidFragmentContract.ViewFragment1 view) {
        this.createNewView1 = view;
        this.createNewView1.setPresenter(this);
    }

    public void setView2(CreateNewConnectorInformationPrepaidFragmentContract.ViewFragment2 view) {
        this.createNewView2 = view;
        this.createNewView2.setPresenter(this);
    }

    @Override
    public void subscribe() {
    }


    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    void loadDataCreateView() {
        createNewView1.showLoading();
        Subscription subscription =
                observableDataSpinnerView().subscribe(new MBCCSSubscribe<DataSpinnerView>() {
                    @Override
                    public void onSuccess(DataSpinnerView object) {
                        dataSpinnerCustomerType = object.getSpinnerCustomerType().getBusTypeList();
                        dataSpinnerPassportType = object.getSpinnerGiayTo().getApDomainByTypeList();
                        dataSpinner2GoiCuoc = object.getSpinner2GoiCuoc().getListProduct();
                        dataSpinner2LoaiThueBao = object.getSpinner2LoaiThueBao().getSubTypeList();
                        dataSpinner2DichVu = Data.connectorTelServiceType();
                        setDataCreateView();
                    }

                    @Override
                    public void onError(BaseException error) {
                        createNewView1.hideLoading();
                        createNewView1.loadDataSpinnerError(error);
                    }
                });
        subscriptions.add(subscription);
    }

    private void setDataCreateView() {
        setDataCreateView1();
        setDataCreateView2();
        createNewView1.hideLoading();
    }


    private void setDataCreateView1() {
        adapterSpinnerCustomerType = new SpinnerAdapter<>(context, dataSpinnerCustomerType);
        adapterSpinnerCustomerType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        positionSpinnerCustomerType = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        notifyPropertyChanged(BR.adapterSpinnerCustomerType);

        adapterSpinnerPassportType = new SpinnerAdapter<>(context, dataSpinnerPassportType);
        adapterSpinnerPassportType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        positionSpinnerPassportType = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        notifyPropertyChanged(BR.adapterSpinnerPassportType);
        setDataCustomer();

        setMaxDateBirthday(DateUtils.maxDateBirthday());
        setDateBirthday(
                DateUtils.convertToString(DateUtils.maxDateBirthday(), DateUtils.TIMEZONE_FORMAT,
                        null));
        setMinDateCreatePassport(DateUtils.maxDateBirthday());
        setMaxDateCreatePassport(new Date());
        setMinOutDatePassport(new Date());
    }

    private void setDataCreateView2() {
        adapterSpinner2DichVu = new SpinnerAdapter<>(context, dataSpinner2DichVu);
        adapterSpinner2DichVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionSpinner2DichVu = position;
                if (dataSpinner2HTHoaMang != null) {
                    dataSpinner2HTHoaMang.clear();
                    adapterSpinner2HTHoaMang.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        notifyPropertyChanged(BR.adapterSpinner2DichVu);

        adapterSpinner2GoiCuoc = new SpinnerAdapter<>(context, dataSpinner2GoiCuoc);
        adapterSpinner2GoiCuoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionSpinner2GoiCuoc = position;
                if (dataSpinner2HTHoaMang != null) {
                    dataSpinner2HTHoaMang.clear();
                    adapterSpinner2HTHoaMang.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        notifyPropertyChanged(BR.adapterSpinner2GoiCuoc);

        adapterSpinner2LoaiThueBao = new SpinnerAdapter<>(context, dataSpinner2LoaiThueBao);
        adapterSpinner2LoaiThueBao.notifyDataSetChanged();
        adapterSpinner2LoaiThueBao.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        positionSpinner2LoaiThueBao = position;
                        getDataHTHoaMang();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        notifyPropertyChanged(BR.adapterSpinner2LoaiThueBao);
    }

    private void getDataHTHoaMang() {
        if (createNewView2.getFragmentVisible()) createNewView2.showLoading();
        Subscription subscription =
                getDataSpinner2HTHoaMang().subscribe(new MBCCSSubscribe<GetListRegTypeResponse>() {
                    @Override
                    public void onSuccess(GetListRegTypeResponse object) {
                        if (object == null
                                || object.getRegTypeList() == null
                                || object.getRegTypeList().size() == 0) {
                            BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                            baseErrorResponse.setError(201, stringLoadDataError);
                            onError(BaseException.toServerError(baseErrorResponse));
                        }
                        dataSpinner2HTHoaMang = object.getRegTypeList();
                        setViewSpinner2HTHoaMang();
                    }

                    @Override
                    public void onError(BaseException error) {
                        createNewView2.loadDataSpinnerError(error);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        createNewView2.hideLoading();
                    }
                });
        subscriptions.add(subscription);
    }

    private void setViewSpinner2HTHoaMang() {
        if (dataSpinner2HTHoaMang == null) return;
        if (adapterSpinner2HTHoaMang != null) adapterSpinner2HTHoaMang.notifyDataSetChanged();
        adapterSpinner2HTHoaMang = new SpinnerAdapter<>(context, dataSpinner2HTHoaMang);
        adapterSpinner2HTHoaMang.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        positionSpinner2HTHoaMang = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        setAdapterSpinner2HTHoaMang(adapterSpinner2HTHoaMang);
    }

    private Observable<GetApDomainByTypeResponse> getDataSpinnerPassport() {
        DataRequest<GetApDomainByTypeRequest> request = new DataRequest<>();
        GetApDomainByTypeRequest apDomainRequest = new GetApDomainByTypeRequest();
        apDomainRequest.setType(ApDomainByType.Type.LOAI_GIAY_TO);
        apDomainRequest.setSubType(MobileType.TRA_TRUOC);

        request.setWsRequest(apDomainRequest);
        request.setWsCode(WsCode.GetApDomainByType);

        return qlKhachHangRepository.getApDomainByType(request);
    }

    private Observable<GetListBusTypeResponse> getDataTypeCustomer() {
        GetListBusTypeRequest getListBusTypeRequest = new GetListBusTypeRequest();

        DataRequest<GetListBusTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListBusTypeRequest);
        request.setWsCode(WsCode.GetListBusType);

        return qlKhachHangRepository.getListBusType(request);
    }

    private Observable<GetListProductResponse> getDataSpinner2GoiCuoc() {
        GetListProductRequest getListProductRequest = new GetListProductRequest();
        getListProductRequest.setSubType(MobileType.TRA_TRUOC);
        getListProductRequest.setTelServiceId(TelServiceId.Mobile);

        DataRequest<GetListProductRequest> request = new DataRequest<>();
        request.setWsRequest(getListProductRequest);
        request.setWsCode(WsCode.GetListProduct);
        return qlKhachHangRepository.getListProduct(request);
    }

    private Observable<GetListSubTypeResponse> getDataSpinner2LoaiThueBao() {
        GetListSubTypeRequest getListSubTypeRequest = new GetListSubTypeRequest();
        getListSubTypeRequest.setServiceType(TelServiceType.Mobile);

        DataRequest<GetListSubTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListSubTypeRequest);
        request.setWsCode(WsCode.GetListSubType);
        return qlKhachHangRepository.getListSubType(request);
    }

    private Observable<GetListRegTypeResponse> getDataSpinner2HTHoaMang() {
        GetListRegTypeRequest getListRegTypeRequest = new GetListRegTypeRequest();
        getListRegTypeRequest.setSubType(
                dataSpinner2LoaiThueBao.get(positionSpinner2LoaiThueBao).getSubType());
        getListRegTypeRequest.setTelServiceId(
                dataSpinner2DichVu.get(positionSpinner2DichVu).getId());
        getListRegTypeRequest.setProductCode(
                dataSpinner2GoiCuoc.get(positionSpinner2GoiCuoc).getOfferCode());
        getListRegTypeRequest.setChannelTypeId("");

        DataRequest<GetListRegTypeRequest> request = new DataRequest<>();
        request.setWsRequest(getListRegTypeRequest);
        request.setWsCode(WsCode.GetListRegType);
        return qlKhachHangRepository.getListRegType(request);
    }

    /*---------------------------------- onClick View ---------------------------------------*/
    public void onCancelNewView1() {
        if (createNewView1 != null) createNewView1.onCancel();
    }

    public void onCancelNewView2() {
        //        if (createNewView2 != null) createNewView2.onCancel();
        if (onPageChange != null) {
            onPageChange.onPageChange(0);
        }
    }

    public void onNext() {
        if (!validateCustomer()) {
            return;
        }

        if (onPageChange != null) {
            onPageChange.onPageChange(1);
        }
    }

    public void onEnter() {
        if (!validateCustomer()) {
            if (onPageChange != null) {
                this.onPageChange.onPageChange(0);
            }
            return;
        }
        if (!validateSubscriber()) {
            return;
        }

        createNewView2.showLoading();

        UserInfo userInfo = userRepository.getUserInfo();
        getContract();
        getContractBankCurrent();
        ConnectSubscriberRequest connectSubscriberRequest = new ConnectSubscriberRequest();
        connectSubscriberRequest.setContract(contractCurrent);
        connectSubscriberRequest.setContractBank(contractBankCurrent);
        connectSubscriberRequest.setCustomer(customerCurrent);
        connectSubscriberRequest.setSubscriber(subscriberCurrent);
        connectSubscriberRequest.setStaffCode(userInfo.getStaffInfo().getStaffCode());
        connectSubscriberRequest.setShopCode(userInfo.getShop().getShopCode());
        connectSubscriberRequest.setOwnerType(WarehouseType.KHO_NHAN_VIEN);
        connectSubscriberRequest.setOwnerId(userInfo.getStaffInfo().getStaffId());
        connectSubscriberRequest.setChannelTypeId(null);
        connectSubscriberRequest.setSubType(checkIdNoRequest.getSubType());

        List<String> dataImage =
                DatabaseUtils.getBitmapAndSaveDatabase(customer, customerCurrentImageFront,
                        customerCurrentImageBackside, customerCurrentImagePortrait);

        createNewView2.connectSubscriber(connectSubscriberRequest, userInfo, dataImage);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (createNewView2 != null) createNewView2.hideLoading();
            }
        }, 300);

    }

    public void onSelectImage(View view) {

    }

    private void getCustomer() {
        if (customerCurrent == null) customerCurrent = new Customer();
        AddressApp address = createNewView1.getAddress();
        customerCurrent.setAddress(address.getAddress());
        customerCurrent.setAreaCode(address.getAreaPrecinct().getAreaCode());
        customerCurrent.setBirthDate(createNewView1.getBirthDate());
        customerCurrent.setBusType(
                dataSpinnerCustomerType.get(positionSpinnerCustomerType).getBusType());
        customerCurrent.setCustId(customer.getCustId());
        customerCurrent.setDistrict(address.getAreaDistrict().getDistrict());
        customerCurrent.setIdIssueDate(createNewView1.getDateCreatePassport());
        customerCurrent.setIdIssuePlace(getPlaceCreatePassport());
        customerCurrent.setIdNo(getTxtNumberPassport());
        customerCurrent.setIdType(
                dataSpinnerPassportType.get(positionSpinnerPassportType).getCode());
        customerCurrent.setName(getNameCustomer());
        customerCurrent.setNationality(customer.getNationality());
        customerCurrent.setPrecinct(address.getAreaPrecinct().getPrecinct());
        customerCurrent.setProvince(address.getAreaProvince().getProvince());
        customerCurrent.setSex(isCheckMale ? Gender.MALE : Gender.FEMALE);
        customerCurrent.setStatus(customer.getStatus());

        customerCurrent.setIdExpireDate(createNewView1.getDateOutDatePassport());

        customerCurrentImageFront = createNewView1.imageFront();
        customerCurrentImageBackside = createNewView1.imageBackside();
        customerCurrentImagePortrait = createNewView1.imagePortrait();
    }

    private void getSubscriber() {
        if (subscriberCurrent == null) subscriberCurrent = new Subscriber();
        AddressApp address = createNewView2.getAddress();

        // TODO: 7/7/17
        subscriberCurrent.setActStatus(null);
        subscriberCurrent.setContractId(null);
        subscriberCurrent.setCreateDate(DateUtils.getCurrentDate());
        //        subscriberCurrent.setCustId(null);
        subscriberCurrent.setDeposit(null);
        subscriberCurrent.setImsi(null);
        subscriberCurrent.setIsdn(getIsdn());
        subscriberCurrent.setNumResetZone(null);
        subscriberCurrent.setPayType(null);
        subscriberCurrent.setProductCode(
                dataSpinner2GoiCuoc.get(positionSpinner2GoiCuoc).getOfferCode());
        subscriberCurrent.setPromotionCode(null);
        subscriberCurrent.setQuota(null);
        subscriberCurrent.setReasonDepositId(null);
        subscriberCurrent.setRegType(
                dataSpinner2HTHoaMang.get(positionSpinner2HTHoaMang).getType());
        subscriberCurrent.setSerial(getSerial());
        subscriberCurrent.setServiceType(dataSpinner2DichVu.get(positionSpinner2DichVu).getCode());
        subscriberCurrent.setTelecomServiceId(
                dataSpinner2DichVu.get(positionSpinner2DichVu).getId());
        subscriberCurrent.setShopCode(null);
        subscriberCurrent.setStaDatetime(DateUtils.getCurrentDate());
        subscriberCurrent.setStaffId(null);
        subscriberCurrent.setStatus(null);
        subscriberCurrent.setSubId(null);
        subscriberCurrent.setUserCreated(null);
        subscriberCurrent.setUserUsing(null);
        subscriberCurrent.setSubType(
                dataSpinner2LoaiThueBao.get(positionSpinner2LoaiThueBao).getSubType());

        subscriberCurrent.setAddress(address.getAddress());
        subscriberCurrent.setProvince(address.getAreaProvince().getProvince());
        subscriberCurrent.setDistrict(address.getAreaDistrict().getDistrict());
        subscriberCurrent.setPrecinct(address.getAreaPrecinct().getPrecinct());
    }

    private void getContract() {
        if (contractCurrent == null) contractCurrent = new Contract();
    }

    private void getContractBankCurrent() {
        if (contractBankCurrent == null) {
            contractBankCurrent = new ContractBank();
        }
    }

    public boolean validateCustomer() {
        getCustomer();
        boolean validate = true;

        if (StringUtils.isEmpty(customerCurrent.getIdIssuePlace())) {
            setPlaceCreatePassportError(stringError);
            validate = false;
        }

        if (StringUtils.isEmpty(customerCurrent.getIdNo())) {
            setTxtNumberPassportError(stringError);
            validate = false;
        }

        if (!PatternUtils.validateString(customerCurrent.getName(),
                PatternUtils.PATTERN_TEXT_LENGTH_100)) {
            setNameCustomerError(
                    context.getString(R.string.create_new_connector_information_validate_name));
            validate = false;
        }
        return validate;
    }

    private boolean validateSubscriber() {
        getSubscriber();
        boolean validate = true;
        if (StringUtils.isEmpty(subscriberCurrent.getSerial())) {
            setSerialError(stringError);
            validate = false;
        }

        if (StringUtils.isEmpty(subscriberCurrent.getIsdn())) {
            setIsdnError(stringError);
            validate = false;
        }

        return validate;
    }
    /*---------------------------------- End onClick View ---------------------------------------*/

    /*---------------------------------- Set - Get ---------------------------------------*/

    @Bindable
    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
        notifyPropertyChanged(BR.nameCustomer);
    }

    @Bindable
    public String getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(String dateBirthday) {
        this.dateBirthday = dateBirthday;
        notifyPropertyChanged(BR.dateBirthday);
    }

    @Bindable
    public Date getMaxDateBirthday() {
        return maxDateBirthday;
    }

    public void setMaxDateBirthday(Date maxDateBirthday) {
        this.maxDateBirthday = maxDateBirthday;
        notifyPropertyChanged(BR.maxDateBirthday);
    }

    @Bindable
    public Date getMinDateBirthday() {
        return minDateBirthday;
    }

    public void setMinDateBirthday(Date minDateBirthday) {
        this.minDateBirthday = minDateBirthday;
        notifyPropertyChanged(BR.minDateBirthday);
    }

    @Bindable
    public String getTxtNumberPassport() {
        return txtNumberPassport;
    }

    public void setTxtNumberPassport(String txtNumberPassport) {
        this.txtNumberPassport = txtNumberPassport;
        notifyPropertyChanged(BR.txtNumberPassport);
    }

    @Bindable
    public String getDateCreatePassport() {
        return dateCreatePassport;
    }

    public void setDateCreatePassport(String dateCreatePassport) {
        this.dateCreatePassport = dateCreatePassport;
        notifyPropertyChanged(BR.dateCreatePassport);
    }

    @Bindable
    public Date getMaxDateCreatePassport() {
        return maxDateCreatePassport;
    }

    public void setMaxDateCreatePassport(Date maxDateCreatePassport) {
        this.maxDateCreatePassport = maxDateCreatePassport;
        notifyPropertyChanged(BR.maxDateCreatePassport);
    }

    @Bindable
    public Date getMinDateCreatePassport() {
        return minDateCreatePassport;
    }

    public void setMinDateCreatePassport(Date minDateCreatePassport) {
        this.minDateCreatePassport = minDateCreatePassport;
        notifyPropertyChanged(BR.minDateCreatePassport);
    }

    @Bindable
    public String getOutDatePassport() {
        return outDatePassport;
    }

    public void setOutDatePassport(String outDatePassport) {
        this.outDatePassport = outDatePassport;
        notifyPropertyChanged(BR.outDatePassport);
    }

    @Bindable
    public Date getMaxOutDatePassport() {
        return maxOutDatePassport;
    }

    public void setMaxOutDatePassport(Date maxOutDatePassport) {
        this.maxOutDatePassport = maxOutDatePassport;
        notifyPropertyChanged(BR.maxOutDatePassport);
    }

    @Bindable
    public Date getMinOutDatePassport() {
        return minOutDatePassport;
    }

    public void setMinOutDatePassport(Date minOutDatePassport) {
        this.minOutDatePassport = minOutDatePassport;
        notifyPropertyChanged(BR.minOutDatePassport);
    }

    @Bindable
    public String getPlaceCreatePassport() {
        return placeCreatePassport;
    }

    public void setPlaceCreatePassport(String placeCreatePassport) {
        this.placeCreatePassport = placeCreatePassport;
        notifyPropertyChanged(BR.placeCreatePassport);
    }

    @Bindable
    public AddressApp getArea1() {
        return area1;
    }

    public void setArea1(AddressApp area1) {
        this.area1 = area1;
        notifyPropertyChanged(BR.area1);
    }

    @Bindable
    public boolean isCheckMale() {
        return isCheckMale;
    }

    public void setCheckMale(boolean checkMale) {
        isCheckMale = checkMale;
        notifyPropertyChanged(BR.checkMale);
    }

    @Bindable
    public boolean isCheckFemale() {
        return isCheckFemale;
    }

    public void setCheckFemale(boolean checkFemale) {
        isCheckFemale = checkFemale;
        notifyPropertyChanged(BR.checkFemale);
    }

    @Bindable
    public int getSelectionPassport() {
        return selectionPassport;
    }

    public void setSelectionPassport(int selectionPassport) {
        this.selectionPassport = selectionPassport;
        notifyPropertyChanged(BR.selectionPassport);
    }

    @Bindable
    public String getImageUrlFront() {
        return imageUrlFront;
    }

    public void setImageUrlFront(String imageUrlFront) {
        this.imageUrlFront = imageUrlFront;
        notifyPropertyChanged(BR.imageUrlFront);
    }

    @Bindable
    public String getImageUrlBackside() {
        return imageUrlBackside;
    }

    public void setImageUrlBackside(String imageUrlBackside) {
        this.imageUrlBackside = imageUrlBackside;
        notifyPropertyChanged(BR.imageUrlBackside);
    }

    @Bindable
    public String getImageUrlPortrait() {
        return imageUrlPortrait;
    }

    public void setImageUrlPortrait(String imageUrlPortrait) {
        this.imageUrlPortrait = imageUrlPortrait;
        notifyPropertyChanged(BR.imageUrlPortrait);
    }

    @Bindable
    public SpinnerAdapter<BusType> getAdapterSpinnerCustomerType() {
        return adapterSpinnerCustomerType;
    }

    public void setAdapterSpinnerCustomerType(SpinnerAdapter<BusType> adapterSpinnerCustomerType) {
        this.adapterSpinnerCustomerType = adapterSpinnerCustomerType;
        notifyPropertyChanged(BR.adapterSpinnerCustomerType);
    }

    @Bindable
    public SpinnerAdapter<ApDomainByType> getAdapterSpinnerPassportType() {
        return adapterSpinnerPassportType;
    }

    public void setAdapterSpinnerPassportType(SpinnerAdapter<ApDomainByType> adapterSpinnerPassportType) {
        this.adapterSpinnerPassportType = adapterSpinnerPassportType;
        notifyPropertyChanged(BR.adapterSpinnerPassportType);
    }

    /**
     * ----------------------------------------------------------
     */
    @Bindable
    public AddressApp getArea2() {
        return area2;
    }

    public void setArea2(AddressApp area2) {
        this.area2 = area2;
        notifyPropertyChanged(BR.area2);
    }

    @Bindable
    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
        notifyPropertyChanged(BR.isdn);
    }

    @Bindable
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
        notifyPropertyChanged(BR.serial);
    }

    @Bindable
    public String getInformationStock() {
        return informationStock;
    }

    public void setInformationStock(String informationStock) {
        this.informationStock = informationStock;
        notifyPropertyChanged(BR.informationStock);
    }

    @Bindable
    public SpinnerAdapter<Data.DataField> getAdapterSpinner2DichVu() {
        return adapterSpinner2DichVu;
    }

    public void setAdapterSpinner2DichVu(SpinnerAdapter<Data.DataField> adapterSpinner2DichVu) {
        this.adapterSpinner2DichVu = adapterSpinner2DichVu;
        notifyPropertyChanged(BR.adapterSpinner2DichVu);
    }

    @Bindable
    public SpinnerAdapter<Product> getAdapterSpinner2GoiCuoc() {
        return adapterSpinner2GoiCuoc;
    }

    public void setAdapterSpinner2GoiCuoc(SpinnerAdapter<Product> adapterSpinner2GoiCuoc) {
        this.adapterSpinner2GoiCuoc = adapterSpinner2GoiCuoc;
        notifyPropertyChanged(BR.adapterSpinner2GoiCuoc);
    }

    @Bindable
    public SpinnerAdapter<SubType> getAdapterSpinner2LoaiThueBao() {
        return adapterSpinner2LoaiThueBao;
    }

    public void setAdapterSpinner2LoaiThueBao(SpinnerAdapter<SubType> adapterSpinner2LoaiThueBao) {
        this.adapterSpinner2LoaiThueBao = adapterSpinner2LoaiThueBao;
        notifyPropertyChanged(BR.adapterSpinner2LoaiThueBao);
    }

    @Bindable
    public SpinnerAdapter<RegType> getAdapterSpinner2HTHoaMang() {
        return adapterSpinner2HTHoaMang;
    }

    public void setAdapterSpinner2HTHoaMang(SpinnerAdapter<RegType> adapterSpinner2HTHoaMang) {
        this.adapterSpinner2HTHoaMang = adapterSpinner2HTHoaMang;
        notifyPropertyChanged(BR.adapterSpinner2HTHoaMang);
    }

    @Bindable
    public String getIsdnError() {
        return isdnError;
    }

    public void setIsdnError(String isdnError) {
        this.isdnError = isdnError;
        notifyPropertyChanged(BR.isdnError);
    }

    @Bindable
    public String getSerialError() {
        return serialError;
    }

    public void setSerialError(String serialError) {
        this.serialError = serialError;
        notifyPropertyChanged(BR.serialError);
    }

    @Bindable
    public String getInformationStockError() {
        return informationStockError;
    }

    public void setInformationStockError(String informationStockError) {
        this.informationStockError = informationStockError;
        notifyPropertyChanged(BR.informationStockError);
    }

    @Bindable
    public String getNameCustomerError() {
        return nameCustomerError;
    }

    public void setNameCustomerError(String nameCustomerError) {
        this.nameCustomerError = nameCustomerError;
        notifyPropertyChanged(BR.nameCustomerError);
    }

    @Bindable
    public String getTxtNumberPassportError() {
        return txtNumberPassportError;
    }

    public void setTxtNumberPassportError(String txtNumberPassportError) {
        this.txtNumberPassportError = txtNumberPassportError;
        notifyPropertyChanged(BR.txtNumberPassportError);
    }

    @Bindable
    public String getPlaceCreatePassportError() {
        return placeCreatePassportError;
    }

    public void setPlaceCreatePassportError(String placeCreatePassportError) {
        this.placeCreatePassportError = placeCreatePassportError;
        notifyPropertyChanged(BR.placeCreatePassportError);
    }

    /*---------------------------------- End Set - Get ---------------------------------------*/

    private void setDataCustomer() {
        if (customer == null) return;
        setNameCustomer(customer.getCustomerName());
        setDateBirthday(customer.getBirthDate());
        if (customer.getSex() != null && customer.getSex().equals(Gender.MALE)) {
            setCheckMale(true);
            setCheckFemale(false);
        } else {
            setCheckMale(false);
            setCheckFemale(true);
        }
        setTxtNumberPassport(customer.getIdNo());
        setDateCreatePassport(customer.getIdIssueDate());
        setOutDatePassport(customer.getIdExpireDate());

        AddressApp address = new AddressApp();
        address.setIdProvince(customer.getProvince());
        address.setIdDistrict(customer.getDistrict());
        address.setIdPrecinct(customer.getPrecinct());
        address.setAddress(customer.getAddress());
        setArea1(address);
    }

    private Observable<DataSpinnerView> observableDataSpinnerView() {
        return Observable.zip(getDataSpinnerPassport(), getDataTypeCustomer(),
                getDataSpinner2GoiCuoc(), getDataSpinner2LoaiThueBao(),
                new Func4<GetApDomainByTypeResponse, GetListBusTypeResponse, GetListProductResponse, GetListSubTypeResponse, DataSpinnerView>() {
                    @Override
                    public DataSpinnerView call(GetApDomainByTypeResponse response,
                            GetListBusTypeResponse response2, GetListProductResponse response3,
                            GetListSubTypeResponse response4) {
                        return new DataSpinnerView(response, response2, response3, response4);
                    }
                }).flatMap(new Func1<DataSpinnerView, Observable<DataSpinnerView>>() {
            @Override
            public Observable<DataSpinnerView> call(DataSpinnerView spinner) {
                GetApDomainByTypeResponse spinnerGiayTo = spinner.getSpinnerGiayTo();
                GetListBusTypeResponse spinnerCustomerType = spinner.getSpinnerCustomerType();
                GetListProductResponse spinner2GoiCuoc = spinner.getSpinner2GoiCuoc();
                GetListSubTypeResponse spinner2LoaiThueBao = spinner.getSpinner2LoaiThueBao();

                if (spinnerGiayTo == null
                        || spinnerGiayTo.getApDomainByTypeList() == null
                        || spinnerGiayTo.getApDomainByTypeList().size() == 0

                        || spinnerCustomerType == null
                        || spinnerCustomerType.getBusTypeList() == null
                        || spinnerCustomerType.getBusTypeList().size() == 0

                        || spinner2GoiCuoc == null
                        || spinner2GoiCuoc.getListProduct() == null
                        || spinner2GoiCuoc.getListProduct().size() == 0

                        || spinner2LoaiThueBao == null
                        || spinner2LoaiThueBao.getSubTypeList() == null
                        || spinner2LoaiThueBao.getSubTypeList().size() == 0) {
                    BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                    baseErrorResponse.setError(201, stringLoadDataError);
                    return Observable.error(BaseException.toServerError(baseErrorResponse));
                } else {
                    return Observable.just(spinner);
                }
            }
        });
    }

    void setAddressView2() {
        AddressApp address = new AddressApp();
        address.setIdProvince(customerCurrent.getProvince());
        address.setIdDistrict(customerCurrent.getDistrict());
        address.setIdPrecinct(customerCurrent.getPrecinct());
        address.setAddress(customerCurrent.getAddress());
        setArea2(address);
    }

    private class DataSpinnerView {
        private GetApDomainByTypeResponse spinnerGiayTo;
        private GetListBusTypeResponse spinnerCustomerType;
        private GetListProductResponse spinner2GoiCuoc;
        private GetListSubTypeResponse spinner2LoaiThueBao;

        public DataSpinnerView() {
        }

        DataSpinnerView(GetApDomainByTypeResponse spinnerGiayTo,
                GetListBusTypeResponse spinnerCustomerType, GetListProductResponse spinner2GoiCuoc,
                GetListSubTypeResponse spinner2LoaiThueBao) {
            this.spinnerGiayTo = spinnerGiayTo;
            this.spinnerCustomerType = spinnerCustomerType;
            this.spinner2GoiCuoc = spinner2GoiCuoc;
            this.spinner2LoaiThueBao = spinner2LoaiThueBao;
        }

        GetApDomainByTypeResponse getSpinnerGiayTo() {
            return spinnerGiayTo;
        }

        GetListBusTypeResponse getSpinnerCustomerType() {
            return spinnerCustomerType;
        }

        GetListProductResponse getSpinner2GoiCuoc() {
            return spinner2GoiCuoc;
        }

        GetListSubTypeResponse getSpinner2LoaiThueBao() {
            return spinner2LoaiThueBao;
        }
    }

    public void setOnPageChange(OnPageChange listener) {
        this.onPageChange = listener;
    }

    public void onPageChange(int position) {
        if (onPageChange != null) {
            this.onPageChange.onPageChange(position);
        }
    }
}
