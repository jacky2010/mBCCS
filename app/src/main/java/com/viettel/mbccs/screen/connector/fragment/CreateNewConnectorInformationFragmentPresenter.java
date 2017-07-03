package com.viettel.mbccs.screen.connector.fragment;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.BR;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.Data;
import com.viettel.mbccs.constance.MobileType;
import com.viettel.mbccs.constance.TelServiceId;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProductRequest;
import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProductResponse;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.utils.PatternUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.CustomSelectAddress;
import java.util.Date;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public class CreateNewConnectorInformationFragmentPresenter extends BaseObservable
        implements CreateNewConnectorInformationFragmentContract.Presenter {

    private Context context;
    private CreateNewConnectorInformationFragmentContract.ViewFragment1 createNewView1;
    private CreateNewConnectorInformationFragmentContract.ViewFragment2 createNewView2;
    private QLKhachHangRepository qlKhachHangRepository;
    private CompositeSubscription subscriptions;
    private String stringError;

    private Customer customer;
    private Contract contract;

    private Customer customerCurrent;
    private Contract contractCurrent;

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
    private String address;
    private String idDistrict;
    private String idPrecinct;
    private String idProvince;

    private boolean isCheckMale = true;
    private boolean isCheckFemale;
    private int selectionPassport;

    private Bitmap imageFront;
    private Bitmap imageBackside;
    private Bitmap imagePortrait;
    private String imageUrlFront;
    private String imageUrlBackside;
    private String imageUrlPortrait;

    private String address2;
    private String idDistrict2;
    private String idPrecinct2;
    private String idProvince2;
    private String isdn;
    private String serial;
    private String informationStock;

    private String nameCustomerError;
    private String txtNumberPassportError;
    private String placeCreatePassportError;
    private String isdnError;
    private String serialError;
    private String informationStockError;

    private SpinnerAdapter<ApDomainByType> adapterSpinnerCustomerType;
    private SpinnerAdapter<ApDomainByType> adapterSpinnerPassportType;
    private SpinnerAdapter<Data.DataField> adapterSpinner2DichVu;
    private SpinnerAdapter<ApDomainByType> adapterSpinner2GoiCuoc;
    private SpinnerAdapter<ApDomainByType> adapterSpinner2LoaiThueBao;
    private SpinnerAdapter<ApDomainByType> adapterSpinner2HTHoaMang;

    private List<ApDomainByType> dataSpinnerCustomerType;
    private List<ApDomainByType> dataSpinnerPassportType;
    private List<Data.DataField> dataSpinner2DichVu;

    private int positionSpinnerCustomerType;
    private int positionSpinnerPassportType;
    private int positionSpinner2DichVu;


    public CreateNewConnectorInformationFragmentPresenter(Context context) {
        this.context = context;

        qlKhachHangRepository = QLKhachHangRepository.getInstance();
        subscriptions = new CompositeSubscription();
        stringError = context.getString(R.string.input_empty);
    }

    public void setView(CreateNewConnectorInformationFragmentContract.View view) {
        if (view instanceof CreateNewConnectorInformationFragmentContract.ViewFragment1) {
            this.createNewView1 =
                    (CreateNewConnectorInformationFragmentContract.ViewFragment1) view;
        } else {
            this.createNewView2 =
                    (CreateNewConnectorInformationFragmentContract.ViewFragment2) view;
        }
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
    }


    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    void loadDataCreateView1() {
        createNewView1.showLoading();
        Subscription subscription = Observable.zip(getDataSpinnerPassport(), getDataTypeCustomer(),
                new Func2<GetApDomainByTypeResponse, GetApDomainByTypeResponse, DataSpinner>() {
                    @Override
                    public DataSpinner call(GetApDomainByTypeResponse response,
                            GetApDomainByTypeResponse response2) {
                        return new DataSpinner(response, response2);
                    }
                }).flatMap(new Func1<DataSpinner, Observable<DataSpinner>>() {
            @Override
            public Observable<DataSpinner> call(DataSpinner spinner) {
                GetApDomainByTypeResponse spinnerGiayTo = spinner.getSpinnerGiayTo();
                GetApDomainByTypeResponse spinnerCustomerType = spinner.getSpinnerCustomerType();
                if (spinnerGiayTo == null
                        || spinnerGiayTo.getApDomainByTypeList() == null
                        || spinnerGiayTo.getApDomainByTypeList().size() == 0
                        || spinnerCustomerType == null
                        || spinnerCustomerType.getApDomainByTypeList() == null
                        || spinnerCustomerType.getApDomainByTypeList().size() == 0) {
                    BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                    baseErrorResponse.setError(201, "Error load data");
                    return Observable.error(BaseException.toServerError(baseErrorResponse));
                } else {
                    return Observable.just(spinner);
                }
            }
        }).subscribe(new MBCCSSubscribe<DataSpinner>() {
            @Override
            public void onSuccess(DataSpinner object) {
                dataSpinnerCustomerType = object.getSpinnerCustomerType().getApDomainByTypeList();
                dataSpinnerPassportType = object.getSpinnerGiayTo().getApDomainByTypeList();
                setDataCreateView1();
            }

            @Override
            public void onError(BaseException error) {
                createNewView1.hideLoading();
                createNewView1.loadDataSpinnerError(error);
            }
        });
        subscriptions.add(subscription);
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
        createNewView1.loadDataSpinnerSuccess();
        setMaxDateBirthday(DateUtils.minDateBirthday());
        setDateBirthday(
                DateUtils.convertToString(DateUtils.minDateBirthday(), DateUtils.TIMEZONE_FORMAT,
                        null));
        setMinDateCreatePassport(DateUtils.minDateBirthday());
        setMaxDateCreatePassport(new Date());
        setMinOutDatePassport(new Date());
        createNewView1.hideLoading();
    }

    void loadDataCreateView2() {
        createNewView2.showLoading();
        dataSpinner2DichVu = Data.connectorTelServiceType();

    }

    private void setDataCreateView2() {
        adapterSpinner2DichVu = new SpinnerAdapter<>(context, dataSpinner2DichVu);
        adapterSpinner2DichVu.notifyDataSetChanged();
        adapterSpinner2DichVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionSpinner2DichVu = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        createNewView2.hideLoading();
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

    private Observable<GetApDomainByTypeResponse> getDataTypeCustomer() {
        DataRequest<GetApDomainByTypeRequest> request = new DataRequest<>();
        GetApDomainByTypeRequest apDomainRequest = new GetApDomainByTypeRequest();
        apDomainRequest.setType(ApDomainByType.Type.LOAI_GIAY_TO);
        apDomainRequest.setSubType(MobileType.TRA_TRUOC);

        request.setWsRequest(apDomainRequest);
        request.setWsCode(WsCode.GetApDomainByType);

        return qlKhachHangRepository.getApDomainByType(request);
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

    /*---------------------------------- onClick View ---------------------------------------*/
    public void onCancelNewView1() {
        if (createNewView1 != null) createNewView1.onCancel();
    }

    public void onCancelNewView2() {
        if (createNewView2 != null) createNewView2.onCancel();
    }

    public void onNext() {
        getCustomer();

        if (StringUtils.isEmpty(customerCurrent.getIdIssuePlace())) {
            createNewView1.validateCustomerError(context.getString(
                    R.string.create_new_connector_information_validate_place_id_no));
            return;
        }

        if (StringUtils.isEmpty(customerCurrent.getAddress())) {
            createNewView1.validateCustomerError(
                    context.getString(R.string.create_new_connector_information_validate_address));
            return;
        }

        imageFront = createNewView1.imageFront();
        imageBackside = createNewView1.imageBackside();
        imageBackside = createNewView1.imagePortrait();
        createNewView1.onNext();
    }

    public void onEnter() {
        createNewView2.onEnter();
    }

    public void onSelectImage(View view) {

    }

    private void getCustomer() {
        if (customerCurrent == null) customerCurrent = new Customer();
        CustomSelectAddress.Address address = createNewView1.getAddress();
        customerCurrent.setName(getNameCustomer());
        customerCurrent.setBirthDate(createNewView1.getBirthDate());
        customerCurrent.setIdIssueDate(createNewView1.getDateCreatePassport());
        customerCurrent.setIdExpireDate(createNewView1.getDateOutDatePassport());
        customerCurrent.setIdIssuePlace(getPlaceCreatePassport());
        customerCurrent.setAddress(address.getAddress());
        customerCurrent.setProvince(address.getAreaProvince().getProvince());
        customerCurrent.setDistrict(address.getAreaDistrict().getDistrict());
        customerCurrent.setPrecinct(address.getAreaPrecinct().getPrecinct());
        customerCurrent.setIdNo(getTxtNumberPassport());
    }

    private boolean validateCustomer() {
        boolean validate = true;
        if (!PatternUtils.validateString(customerCurrent.getName(),
                PatternUtils.PATTERN_TEXT_LENGTH_100)) {
            setNameCustomerError(
                    context.getString(R.string.create_new_connector_information_validate_name));
            validate = false;
        }

        if (StringUtils.isEmpty(customerCurrent.getIdNo())) {
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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public String getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(String idDistrict) {
        this.idDistrict = idDistrict;
        notifyPropertyChanged(BR.idDistrict);
    }

    @Bindable
    public String getIdPrecinct() {
        return idPrecinct;
    }

    public void setIdPrecinct(String idPrecinct) {
        this.idPrecinct = idPrecinct;
        notifyPropertyChanged(BR.idPrecinct);
    }

    @Bindable
    public String getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(String idProvince) {
        this.idProvince = idProvince;
        notifyPropertyChanged(BR.idProvince);
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
    public Bitmap getImageFront() {
        return imageFront;
    }

    public void setImageFront(Bitmap imageFront) {
        this.imageFront = imageFront;
        notifyPropertyChanged(BR.imageFront);
    }

    @Bindable
    public Bitmap getImageBackside() {
        return imageBackside;
    }

    public void setImageBackside(Bitmap imageBackside) {
        this.imageBackside = imageBackside;
        notifyPropertyChanged(BR.imageBackside);
    }

    @Bindable
    public Bitmap getImagePortrait() {
        return imagePortrait;
    }

    public void setImagePortrait(Bitmap imagePortrait) {
        this.imagePortrait = imagePortrait;
        notifyPropertyChanged(BR.imagePortrait);
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
    public SpinnerAdapter<ApDomainByType> getAdapterSpinnerCustomerType() {
        return adapterSpinnerCustomerType;
    }

    public void setAdapterSpinnerCustomerType(SpinnerAdapter<ApDomainByType> adapterSpinnerCustomerType) {
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
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
        notifyPropertyChanged(BR.address2);
    }

    @Bindable
    public String getIdDistrict2() {
        return idDistrict2;
    }

    public void setIdDistrict2(String idDistrict2) {
        this.idDistrict2 = idDistrict2;
        notifyPropertyChanged(BR.idDistrict2);
    }

    @Bindable
    public String getIdPrecinct2() {
        return idPrecinct2;
    }

    public void setIdPrecinct2(String idPrecinct2) {
        this.idPrecinct2 = idPrecinct2;
        notifyPropertyChanged(BR.idPrecinct2);
    }

    @Bindable
    public String getIdProvince2() {
        return idProvince2;
    }

    public void setIdProvince2(String idProvince2) {
        this.idProvince2 = idProvince2;
        notifyPropertyChanged(BR.idProvince2);
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
    public SpinnerAdapter<ApDomainByType> getAdapterSpinner2GoiCuoc() {
        return adapterSpinner2GoiCuoc;
    }

    public void setAdapterSpinner2GoiCuoc(SpinnerAdapter<ApDomainByType> adapterSpinner2GoiCuoc) {
        this.adapterSpinner2GoiCuoc = adapterSpinner2GoiCuoc;
        notifyPropertyChanged(BR.adapterSpinner2GoiCuoc);
    }

    @Bindable
    public SpinnerAdapter<ApDomainByType> getAdapterSpinner2LoaiThueBao() {
        return adapterSpinner2LoaiThueBao;
    }

    public void setAdapterSpinner2LoaiThueBao(
            SpinnerAdapter<ApDomainByType> adapterSpinner2LoaiThueBao) {
        this.adapterSpinner2LoaiThueBao = adapterSpinner2LoaiThueBao;
        notifyPropertyChanged(BR.adapterSpinner2LoaiThueBao);
    }

    @Bindable
    public SpinnerAdapter<ApDomainByType> getAdapterSpinner2HTHoaMang() {
        return adapterSpinner2HTHoaMang;
    }

    public void setAdapterSpinner2HTHoaMang(
            SpinnerAdapter<ApDomainByType> adapterSpinner2HTHoaMang) {
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

    public void setData(Customer customer, Contract contract) {
        this.customer = customer;
        this.contract = contract;
        if (customer == null) return;
        setDataCustomer();
    }

    private void setDataCustomer() {
        setNameCustomer(customer.getCustomerName());
        setDateBirthday(customer.getBirthDate());
        if (customer.getSex() != null && customer.getSex().equals(Data.Gender.MALE)) {
            setCheckMale(true);
            setCheckFemale(false);
        } else {
            setCheckMale(false);
            setCheckFemale(true);
        }
        setTxtNumberPassport(customer.getIdNo());
        setDateCreatePassport(customer.getIdIssueDate());
        setOutDatePassport(customer.getIdExpireDate());
        setIdProvince(customer.getProvince());
        setIdDistrict(customer.getDistrict());
        setIdPrecinct(customer.getPrecinct());
        setAddress(customer.getAddress());
    }

    private class DataSpinner {
        private GetApDomainByTypeResponse spinnerGiayTo;
        private GetApDomainByTypeResponse spinnerCustomerType;

        public DataSpinner() {
        }

        public DataSpinner(GetApDomainByTypeResponse spinnerGiayTo,
                GetApDomainByTypeResponse spinnerTypeCustomer) {
            this.spinnerGiayTo = spinnerGiayTo;
            this.spinnerCustomerType = spinnerTypeCustomer;
        }

        public GetApDomainByTypeResponse getSpinnerGiayTo() {
            return spinnerGiayTo;
        }

        public void setSpinnerGiayTo(GetApDomainByTypeResponse spinnerGiayTo) {
            this.spinnerGiayTo = spinnerGiayTo;
        }

        public GetApDomainByTypeResponse getSpinnerCustomerType() {
            return spinnerCustomerType;
        }

        public void setSpinnerCustomerType(GetApDomainByTypeResponse spinnerCustomerType) {
            this.spinnerCustomerType = spinnerCustomerType;
        }
    }
}
