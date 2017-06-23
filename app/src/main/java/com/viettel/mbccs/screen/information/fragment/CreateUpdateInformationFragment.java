package com.viettel.mbccs.screen.information.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
import com.viettel.mbccs.databinding.FragmentCreateUpdateInformationBinding;
import com.viettel.mbccs.screen.common.success.DialogFullScreen;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.widget.CustomSelectImageNo;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class CreateUpdateInformationFragment extends BaseFragment
        implements CreateUpdateInformationFragmentContract.View,
        CustomSelectImageNo.SelectImageCallback {
    public static final String STRING_NAME = "CreateUpdateInformationFragment";

    @IntDef({ Type.CREATE_INFORMATION, Type.CREATE_INFORMATION_CLONE, Type.UPDATE_INFORMATION })
    public @interface Type {
        int CREATE_INFORMATION = 1;
        int CREATE_INFORMATION_CLONE = 2;
        int UPDATE_INFORMATION = 3;
    }

    private static final String ARG_TYPE_FRAGMENT = "TYPE_FRAGMENT";
    private static final String ARG_DATA = "DATA";

    private FragmentCreateUpdateInformationBinding binding;
    private CreateUpdateInformationFragmentPresenter presenter;
    @Type
    private int typeFragment;
    private GetRegiterSubInfoResponse data;

    public static CreateUpdateInformationFragment newInstance(@Type int typeFragment,
            @Nullable GetRegiterSubInfoResponse data) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TYPE_FRAGMENT, typeFragment);
        bundle.putParcelable(ARG_DATA, data);
        CreateUpdateInformationFragment fragment = new CreateUpdateInformationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeFragment = getArguments().getInt(ARG_TYPE_FRAGMENT);
        data = getArguments().getParcelable(ARG_DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateUpdateInformationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CreateUpdateInformationFragmentPresenter(getActivity(), this);

        presenter.setTypeFragment(typeFragment, data);
        presenter.getDataSpinner();
        binding.imageSelect.setSelectImageCallback(this);
        binding.setPresenter(presenter);
    }

    @Override
    public void onDestroy() {
        presenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    public void setPresenter(CreateUpdateInformationFragmentContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void onCancel() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public Area getAreaProvince() {
        return binding.customSelectAddressCreateInformation.getAreaProvince();
    }

    @Override
    public Area getAreaDistrict() {
        return binding.customSelectAddressCreateInformation.getAreaDistrict();
    }

    @Override
    public Area getAreaPrecinct() {
        return binding.customSelectAddressCreateInformation.getAreaPrecinct();
    }

    @Override
    public String getAddress() {
        return binding.customSelectAddressCreateInformation.getAddress();
    }

    @Override
    public String getBirthDate() {
        return binding.dateBirthday.getStringDate();
    }

    @Override
    public void registerCustomerInfoError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error);
    }

    @Override
    public void registerUpdateCustomerInfoSuccess(String result, boolean isRegister) {
        Dialog dia = new DialogFullScreen.Builder(getActivity()).setCenterContent(true)
                .setTitle(isRegister ? getString(
                        R.string.fragment_create_update_information_create_dk_thanh_cong)
                        : getString(
                                R.string.fragment_create_update_information_update_thanh_cong))
                .setContent(result)
                .setPositiveButton(getString(R.string.OK))
                .setListener(new DialogFullScreen.SuccessDialogListener() {
                    @Override
                    public void onPositiveButtonClick(Dialog dialog) {
                        dialog.dismiss();
                        getActivity().getSupportFragmentManager().popBackStack();
                    }

                    @Override
                    public void onNegativeButtonClick(Dialog dialog) {

                    }

                    @Override
                    public void onDialogClose() {

                    }
                })
                .build();
        dia.setCancelable(false);
        dia.setCanceledOnTouchOutside(false);
        dia.show();
    }

    @Override
    public void getDataSpinnerError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        }, false);
    }

    @Override
    public void getOTPError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error);
    }

    @Override
    public void checkOTPError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error);
    }

    @Override
    public void isOTPEmpty() {
        DialogUtils.showDialog(getActivity(),
                getString(R.string.fragment_create_update_information_update_opt_is_empty));
    }

    @Override
    public void selectNoticeChargeError() {
        DialogUtils.showDialog(getActivity(),
                getString(R.string.fragment_create_update_information_update_notice_charge_empty));
    }

    @Override
    public void customerError() {
        DialogUtils.showDialog(getActivity(), getString(
                R.string.fragment_create_update_information_update_information_customer_empty));
    }

    @Override
    public void IsdnImsiError() {
        DialogUtils.showDialog(getActivity(),
                getString(R.string.fragment_create_update_information_update_isdn_serial_empty));
    }

    @Override
    public void updateAllSubInfoError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error);
    }

    @Override
    public void isSendImage() {
        if (presenter.isExistsImageUpload()) {
            DialogUtils.showDialog(getActivity(), "Send Image", "Send image now", "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.clickSendData(true);
                        }
                    }, "No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.clickSendData(false);
                        }
                    });
        } else {
        presenter.clickSendData(false);
        }
    }

    @Override
    public Bitmap imageFront() {
        return binding.imageSelect.getBitmapImageFront();
    }

    @Override
    public Bitmap imageBackside() {
        return binding.imageSelect.getBitmapImageBackside();
    }

    @Override
    public Bitmap imagePortrait() {
        return binding.imageSelect.getBitmapImagePortrait();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            binding.imageSelect.setResultIntent(data, requestCode);
        }
    }

    @Override
    public void onSelectImage(Intent intent, int type) {
        startActivityForResult(intent, type);
    }
}
