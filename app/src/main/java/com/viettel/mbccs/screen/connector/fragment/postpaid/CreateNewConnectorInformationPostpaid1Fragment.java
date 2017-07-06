package com.viettel.mbccs.screen.connector.fragment.postpaid;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.FragmentCreateNewConnectorInformationPostpaid1Binding;
import com.viettel.mbccs.permission.PermissionListener;
import com.viettel.mbccs.permission.PermissionsUtil;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.widget.CustomSelectImageNo;
import com.viettel.mbccs.widget.model.AddressApp;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public class CreateNewConnectorInformationPostpaid1Fragment extends BaseFragment
        implements CreateNewConnectorInformationPostpaidFragmentContract.ViewFragment1,
        CustomSelectImageNo.SelectImageCallback {
    public static final String STRING_NAME = "CreateNewConnectorInformationPostpaid1Fragment";

    private FragmentCreateNewConnectorInformationPostpaid1Binding binding;
    private CreateNewConnectorInformationPostpaidFragmentPresenter presenter;
    private CreateNewConnectorInformationPostpaidViewModel viewModel;
    private boolean isFirst = true;

    public static CreateNewConnectorInformationPostpaid1Fragment newInstance() {
        Bundle bundle = new Bundle();
        CreateNewConnectorInformationPostpaid1Fragment fragment =
                new CreateNewConnectorInformationPostpaid1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateNewConnectorInformationPostpaid1Binding.inflate(inflater, container,
                false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setPresenter(presenter);
        binding.setViewModel(viewModel);
        binding.imageSelect.setSelectImageCallback(this);
    }

    @Override
    public void onStop() {
        presenter.unSubscribe();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        if (binding != null) {
            binding.imageSelect.recycleBitmap();
        }
        super.onDestroy();
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
    public void setPresenter(
            CreateNewConnectorInformationPostpaidFragmentContract.Presenter presenter) {
        this.presenter = (CreateNewConnectorInformationPostpaidFragmentPresenter) presenter;
    }

    @Override
    public void setViewModel(CreateNewConnectorInformationPostpaidViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onCancel() {
        getActivity().onBackPressed();
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
    public void loadDataSpinnerError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().onBackPressed();
            }
        }, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            binding.imageSelect.setResultIntent(data, requestCode);
        }
    }

    @Override
    public void onSelectImage(final Intent intent, final int type) {
        PermissionsUtil.requestPermission(getActivity(), new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                startActivityForResult(intent, type);
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {

            }
        }, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE });
    }

    public String getBirthDate() {
        return binding.birthDate.getStringDate();
    }

    @Override
    public String getDateCreatePassport() {
        return binding.createNewConnectorInformationPostpaidNgayCap.getStringDate();
    }

    @Override
    public String getBirthDateRegister() {
        return binding.birthDateRegister.getStringDate();
    }

    @Override
    public String getDateCreateRegister() {
        return binding.registerDateCreate.getStringDate();
    }

    @Override
    public AddressApp getAddress() {
        return binding.customSelectAddressCreateConnector.getAddress();
    }

    @Override
    public boolean getFragmentVisible() {
        return getUserVisibleHint();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isFirst && isVisibleToUser) {
            presenter.loadDataCreateBaseView();
            isFirst = false;
        }

        if (!isFirst && !isVisibleToUser && !presenter.validateCustomer()) {
            presenter.onPageChange(0);
        }
    }
}
