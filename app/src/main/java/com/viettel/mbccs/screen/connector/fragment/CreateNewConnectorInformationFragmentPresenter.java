package com.viettel.mbccs.screen.connector.fragment;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.BR;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.constance.Data;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
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

    private Customer customer;
    private Contract contract;

    private String nameCustomer;
    private String dateBirthday;
    private String txtNumberPassport;
    private String dateCreatePassport;
    private String outDatePassport;
    private String whereCreatePassport;
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

    private SpinnerAdapter<ApDomainByType> adapterSpinnerCustomerType;
    private SpinnerAdapter<ApDomainByType> adapterSpinnerPassportType;

    private String address2;
    private String idDistrict2;
    private String idPrecinct2;
    private String idProvince2;
    private String noIsdn;
    private String serial;
    private String informationStock;

    private ArrayAdapter<String> adapterSpinner2DichVu;
    private ArrayAdapter<String> adapterSpinner2GoiCuoc;
    private ArrayAdapter<String> adapterSpinner2LoaiThueBao;
    private ArrayAdapter<String> adapterSpinner2HTHoaMang;

    private List<ApDomainByType> dataSpinnerCustomerType;
    private List<ApDomainByType> dataSpinnerPassportType;



    public CreateNewConnectorInformationFragmentPresenter(Context context,
            CreateNewConnectorInformationFragmentContract.View view) {
        this.context = context;
        if (view instanceof CreateNewConnectorInformationFragmentContract.ViewFragment1) {
            this.createNewView1 =
                    (CreateNewConnectorInformationFragmentContract.ViewFragment1) view;
        } else {
            this.createNewView2 =
                    (CreateNewConnectorInformationFragmentContract.ViewFragment2) view;
        }

        qlKhachHangRepository = QLKhachHangRepository.getInstance();
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
    }


    @Override
    public void unSubscribe() {

    }

    public void loadDataCreateView1() {
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

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        adapterSpinnerCustomerType.setTextHint("-Tất cả-");
        adapterSpinnerCustomerType.setUsehintValue(true);

        adapterSpinnerPassportType = new SpinnerAdapter<>(context, dataSpinnerPassportType);
        adapterSpinnerPassportType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        createNewView1.loadDataSpinnerSuccess();
        createNewView1.hideLoading();
    }

    public Observable<GetApDomainByTypeResponse> getDataSpinnerPassport() {
        DataRequest<GetApDomainByTypeRequest> request = new DataRequest<>();
        GetApDomainByTypeRequest apDomainRequest = new GetApDomainByTypeRequest();
        apDomainRequest.setType(ApDomainByType.Type.LOAI_GIAY_TO);

        request.setParameterApi(apDomainRequest);
        request.setApiCode(ApiCode.GetApDomainByType);

        return qlKhachHangRepository.getApDomainByType(request);
    }

    public Observable<GetApDomainByTypeResponse> getDataTypeCustomer() {
        DataRequest<GetApDomainByTypeRequest> request = new DataRequest<>();
        GetApDomainByTypeRequest apDomainRequest = new GetApDomainByTypeRequest();
        apDomainRequest.setType(ApDomainByType.Type.LOAI_GIAY_TO);

        request.setParameterApi(apDomainRequest);
        request.setApiCode(ApiCode.GetApDomainByType);

        return qlKhachHangRepository.getApDomainByType(request);
    }

    /*---------------------------------- onClick View ---------------------------------------*/
    public void onCancel() {
        if (createNewView1 != null) createNewView1.onCancel();
        if (createNewView2 != null) createNewView2.onCancel();
    }

    public void onNext() {
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
    public String getOutDatePassport() {
        return outDatePassport;
    }

    public void setOutDatePassport(String outDatePassport) {
        this.outDatePassport = outDatePassport;
        notifyPropertyChanged(BR.outDatePassport);
    }

    @Bindable
    public String getWhereCreatePassport() {
        return whereCreatePassport;
    }

    public void setWhereCreatePassport(String whereCreatePassport) {
        this.whereCreatePassport = whereCreatePassport;
        notifyPropertyChanged(BR.whereCreatePassport);
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
    public String getNoIsdn() {
        return noIsdn;
    }

    public void setNoIsdn(String noIsdn) {
        this.noIsdn = noIsdn;
        notifyPropertyChanged(BR.noIsdn);
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
    public ArrayAdapter<String> getAdapterSpinner2DichVu() {
        return adapterSpinner2DichVu;
    }

    public void setAdapterSpinner2DichVu(ArrayAdapter<String> adapterSpinner2DichVu) {
        this.adapterSpinner2DichVu = adapterSpinner2DichVu;
        notifyPropertyChanged(BR.adapterSpinner2DichVu);
    }

    @Bindable
    public ArrayAdapter<String> getAdapterSpinner2GoiCuoc() {
        return adapterSpinner2GoiCuoc;
    }

    public void setAdapterSpinner2GoiCuoc(ArrayAdapter<String> adapterSpinner2GoiCuoc) {
        this.adapterSpinner2GoiCuoc = adapterSpinner2GoiCuoc;
        notifyPropertyChanged(BR.adapterSpinner2GoiCuoc);
    }

    @Bindable
    public ArrayAdapter<String> getAdapterSpinner2LoaiThueBao() {
        return adapterSpinner2LoaiThueBao;
    }

    public void setAdapterSpinner2LoaiThueBao(ArrayAdapter<String> adapterSpinner2LoaiThueBao) {
        this.adapterSpinner2LoaiThueBao = adapterSpinner2LoaiThueBao;
        notifyPropertyChanged(BR.adapterSpinner2LoaiThueBao);
    }

    @Bindable
    public ArrayAdapter<String> getAdapterSpinner2HTHoaMang() {
        return adapterSpinner2HTHoaMang;
    }

    public void setAdapterSpinner2HTHoaMang(ArrayAdapter<String> adapterSpinner2HTHoaMang) {
        this.adapterSpinner2HTHoaMang = adapterSpinner2HTHoaMang;
        notifyPropertyChanged(BR.adapterSpinner2HTHoaMang);
    }
    /*---------------------------------- End Set - Get ---------------------------------------*/

    public void setData(Customer customer, Contract contract) {
        this.customer = customer;
        this.contract = contract;
//        if (imageFront != null) setImageFront(imageFront);
        if (customer == null || contract == null) return;
        setDataToView();
    }

    private void setDataToView() {
        setNameCustomer(customer.getCustomerName());
        setDateBirthday(customer.getBirthDate());
        if (customer.getSex().equals(Data.Gender.MALE)) {
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
