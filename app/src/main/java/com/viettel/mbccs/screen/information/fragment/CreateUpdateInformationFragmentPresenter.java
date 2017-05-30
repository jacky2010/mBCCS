package com.viettel.mbccs.screen.information.fragment;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.ApDomain;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.District;
import com.viettel.mbccs.data.model.Precinct;
import com.viettel.mbccs.data.model.Province;
import com.viettel.mbccs.data.model.Subscriber;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeIdRequireRequest;
import com.viettel.mbccs.data.source.remote.request.RegisterCustomerInfoRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeIdRequireResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.RegisterCustomerInfoResponse;
import com.viettel.mbccs.utils.ImageUtils;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.List;
import rx.Subscription;
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
    private Customer customer;
    private Subscriber subscriber;

    private Bitmap imageBitmapFront;
    private Bitmap imageBitmapBackside;
    private Bitmap imageBitmapPortrait;

    private List<ApDomain> dataPassportType;

    public ObservableField<String> title;
    public ObservableField<String> textBtnRegisterUpdate;

    public ObservableField<ArrayAdapter<String>> adapterGoiCuoc;
    public ObservableField<ArrayAdapter<String>> adapterTypePassport;
    public ObservableField<ArrayAdapter<String>> adapterHTThanhToan;

    public ObservableField<String> txtPhoneNumber;
    public ObservableField<String> txtSerial;
    public ObservableField<String> txtNameCustomer;
    public ObservableField<String> txtNumberPassport;
    public ObservableField<Drawable> imageFront;
    public ObservableField<Drawable> imageBackside;
    public ObservableField<Drawable> imagePortrait;
    public ObservableField<String> idProvince;
    public ObservableField<String> idDistrict;
    public ObservableField<String> idPrecinct;
    public ObservableField<String> address;

    public ObservableBoolean isEnabledTxtNumberPassport;
    public ObservableBoolean isEnabledSelectGender;

    public ObservableBoolean isCheckMale;
    public ObservableBoolean isCheckFemale;
    public ObservableBoolean isShowContractInformation;
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
        imageFront = new ObservableField<>(
                context.getResources().getDrawable(R.drawable.ic_select_image));
        imageBackside = new ObservableField<>(
                context.getResources().getDrawable(R.drawable.ic_select_image));
        imagePortrait = new ObservableField<>(
                context.getResources().getDrawable(R.drawable.ic_select_image));

        idProvince = new ObservableField<>();
        idDistrict = new ObservableField<>();
        idPrecinct = new ObservableField<>();
        address = new ObservableField<>();

        isEnabledTxtNumberPassport = new ObservableBoolean(true);
        isEnabledSelectGender = new ObservableBoolean(true);

        isCheckMale = new ObservableBoolean(true);
        isCheckFemale = new ObservableBoolean();

        isShowContractInformation = new ObservableBoolean();
        selectionPassport = new ObservableInt();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
        if (imageBitmapFront != null) {
            imageBitmapFront.recycle();
        }
        if (imageBitmapBackside != null) {
            imageBitmapBackside.recycle();
        }

        if (imageBitmapPortrait != null) {
            imageBitmapPortrait.recycle();
        }
        imageBitmapFront = null;
        imageBitmapBackside = null;
        imageBitmapPortrait = null;
    }

    public void getDataSpinnerPassport() {

        DataRequest<GetListBusTypeIdRequireRequest> request = new DataRequest<>();
        request.setParameterApi(new GetListBusTypeIdRequireRequest());
        request.setApiCode(ApiCode.GetListBusTypeIdRequire);

        Subscription subscription = qlKhachHangRepository.getListBusTypeIdRequire(request)
                .subscribe(new MBCCSSubscribe<GetListBusTypeIdRequireResponse>() {
                    @Override
                    public void onSuccess(GetListBusTypeIdRequireResponse object) {
                        if (dataPassportType != null && dataPassportType.size() != 0) {
                            dataPassportType.clear();
                        }
                        dataPassportType = object.getApDomainList();
                        view.getDataSpinnerPassportSuccess(dataPassportType);
                        setData();
                    }

                    @Override
                    public void onError(BaseException error) {
                        view.getDataSpinnerPassportError(error);
                    }
                });
        subscriptions.add(subscription);
    }

    /*---------------------- on Click in View -----------------------------*/

    public void onCancel() {
        view.onCancel();
    }

    public void onClickGetOTP() {

    }

    public void onClickRegisterUpdate() {
        view.showLoading();
        Province province = view.getProvince();
        District district = view.getDistrict();
        Precinct precinct = view.getPrecinct();
        String address = view.getAddress();
        // TODO: 5/31/17
        String sex = "M";

        RegisterCustomerInfoRequest registerCustomerInfoRequest = new RegisterCustomerInfoRequest();
        registerCustomerInfoRequest.setIsdn(txtPhoneNumber.get());
        //        registerCustomerInfoRequest.setIsdn("0123456789");

        Customer customer = new Customer();
        customer.setName(txtNameCustomer.get());
        customer.setBirthDate(view.getBirthDate());
        customer.setSex(sex);
        customer.setProvince(province == null ? "0" : province.getProvinceId());
        customer.setDistrict(district == null ? "0" : district.getDistrictId());
        customer.setPrecinct(district == null ? "0" : precinct.getPrecinctId());
        customer.setStreetName(address);
        customer.setStreetBlockName(address);
        customer.setHome(address);
        customer.setIdType(1);
        customer.setIdNo(txtNumberPassport.get());
        // TODO: 5/31/17 fake data
        //        customer.setName("HAMADOU NON0 ISSIAKO");
        //        customer.setBirthDate("11/06/2015 11:17:23");
        //        customer.setSex("M");
        //        customer.setProvince("2");
        //        customer.setDistrict("09");
        //        customer.setPrecinct("1");
        //        customer.setStreetName("1");
        //        customer.setStreetBlockName("1");
        //        customer.setHome("1");
        //        customer.setIdType(1);
        //        customer.setIdNo("116381937");

        registerCustomerInfoRequest.setCustomer(customer);

        DataRequest<RegisterCustomerInfoRequest> request = new DataRequest<>();
        request.setApiCode(ApiCode.RegisterCustomerInfo);
        request.setParameterApi(registerCustomerInfoRequest);

        Subscription subscription = qlKhachHangRepository.registerCustomerInfo(request)
                .subscribe(new MBCCSSubscribe<RegisterCustomerInfoResponse>() {
                    @Override
                    public void onSuccess(RegisterCustomerInfoResponse object) {
                        view.registerCustomerInfoSuccess(object.getResult());
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

    public void onSelectImage(View v) {
        view.onSelectImage(v);
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
        }
    }

    private void setData() {
        customer = data.getCustomer();
        subscriber = data.getSubscriber();
        if (customer == null || subscriber == null) return;
        // TODO: 6/1/17 set data gói cước
        // set information subscriber
        txtPhoneNumber.set(subscriber.getIsdn());
        txtSerial.set(subscriber.getSerial());

        // set information customer
        txtNameCustomer.set(customer.getName());
        if (customer.getSex().equals("M")) {
            isCheckMale.set(true);
            isCheckFemale.set(false);
        } else {
            isCheckMale.set(false);
            isCheckFemale.set(true);
        }
        isEnabledSelectGender.set(false);

        idProvince.set(customer.getProvince());
        idDistrict.set(customer.getDistrict());
        idPrecinct.set(customer.getPrecinct());
        address.set(customer.getStreet());
        txtNumberPassport.set(customer.getIdNo());
        if (!StringUtils.isEmpty(customer.getIdNo())) isEnabledTxtNumberPassport.set(false);
        view.setBirthDate(customer.getBirthDate());

        for (int i = 0; i < dataPassportType.size(); i++) {
            if (dataPassportType.get(i).getType().equals(String.valueOf(customer.getIdType()))){
                selectionPassport.set(i);
                break;
            }
        }
    }

    public void setImageFront(Bitmap bitmap) {
        imageBitmapFront = bitmap;
        imageFront.set(ImageUtils.bitmapToDrawable(context.getResources(), bitmap));
    }

    public void setImageBackside(Bitmap bitmap) {
        imageBitmapBackside = bitmap;
        imageBackside.set(ImageUtils.bitmapToDrawable(context.getResources(), bitmap));
    }

    public void setImagePortrait(Bitmap bitmap) {
        imageBitmapPortrait = bitmap;
        imagePortrait.set(ImageUtils.bitmapToDrawable(context.getResources(), bitmap));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setAdapterPassportType(ArrayAdapter<String> adapter) {
        adapterTypePassport.set(adapter);
    }
    /*----------------------- End Set - Get ---------------------------------------*/
}
