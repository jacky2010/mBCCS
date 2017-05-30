package com.viettel.mbccs.screen.information.fragment;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.source.QLKhachHangRepository;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class CreateUpdateInformationFragmentPresenter
        implements CreateUpdateInformationFragmentContract.Presenter {
    private Context context;
    private CreateUpdateInformationFragmentContract.View view;
    private QLKhachHangRepository qlKhachHangRepository;
    private CompositeSubscription subscriptions;

    @CreateUpdateInformationFragment.Type
    private int typeFragment;

    public ObservableField<String> title;
    public ObservableField<String> textBtnRegisterUpdate;

    public ObservableField<String> txtPhoneNumber;
    public ObservableField<String> txtSerial;
    public ObservableField<ArrayAdapter<String>> adapterGoiCuoc;
    public ObservableField<ArrayAdapter<String>> adapterProvince;
    public ObservableField<ArrayAdapter<String>> adapterDistrict;
    public ObservableField<ArrayAdapter<String>> adapterWards;
    public ObservableField<ArrayAdapter<String>> adapterTypePassport;
    public ObservableField<ArrayAdapter<String>> adapterHTThanhToan;

    public ObservableField<String> txtNameCustomer;
    public ObservableField<String> txtAddressDetail;
    public ObservableField<String> txtNumberPassport;
    public ObservableField<Drawable> imageFront;
    public ObservableField<Drawable> imageBackside;
    public ObservableField<Drawable> imagePortrait;

    public ObservableBoolean isCheckMale;
    public ObservableBoolean isCheckFemale;
    public ObservableBoolean isShowContractInformation;

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
        adapterProvince = new ObservableField<>();
        adapterDistrict = new ObservableField<>();
        adapterWards = new ObservableField<>();
        adapterTypePassport = new ObservableField<>();
        adapterHTThanhToan = new ObservableField<>();

        txtNameCustomer = new ObservableField<>();
        txtAddressDetail = new ObservableField<>();
        txtNumberPassport = new ObservableField<>();
        imageFront = new ObservableField<>(
                context.getResources().getDrawable(R.drawable.ic_select_image));
        imageBackside = new ObservableField<>(
                context.getResources().getDrawable(R.drawable.ic_select_image));
        imagePortrait = new ObservableField<>(
                context.getResources().getDrawable(R.drawable.ic_select_image));

        isCheckMale = new ObservableBoolean(true);
        isCheckFemale = new ObservableBoolean();

        isShowContractInformation = new ObservableBoolean();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    /*---------------------- on Click in View -----------------------------*/

    public void onCancel() {
        view.onCancel();
    }

    public void onClickGetOTP() {

    }

    public void onClickRegisterUpdate() {

    }

    public void onSelectImage(View v) {
        view.onSelectImage(v);

    }

    /*---------------------- End on Click in View -----------------------------*/

    public void setTypeFragment(int type) {
        this.typeFragment = type;
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
}
