package com.viettel.mbccs.screen.connector.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.databinding.FragmentCreateNewConnectorInformation1Binding;
import com.viettel.mbccs.widget.CustomSelectImageNo;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public class CreateNewConnectorInformation1Fragment extends Fragment
        implements CreateNewConnectorInformationFragmentContract.ViewFragment1,
        CustomSelectImageNo.SelectImageCallback {
    public static final String STRING_NAME = "CreateNewConnectorInformation1Fragment";
    private static final String ARG_CUSTOMER = "CUSTOMER";
    private static final String ARG_CONTRACT = "CONTRACT";

    private FragmentCreateNewConnectorInformation1Binding binding;
    private CreateNewConnectorInformationFragmentPresenter presenter;

    private Customer customer;
    private Contract contract;

    public static CreateNewConnectorInformation1Fragment newInstance(@Nullable Customer customer,
            @Nullable Contract contract) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_CUSTOMER, customer);
        bundle.putParcelable(ARG_CONTRACT, contract);
        CreateNewConnectorInformation1Fragment fragment =
                new CreateNewConnectorInformation1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.customer = getArguments().getParcelable(ARG_CUSTOMER);
        this.contract = getArguments().getParcelable(ARG_CONTRACT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateNewConnectorInformation1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CreateNewConnectorInformationFragmentPresenter(getActivity(), this);
        presenter.subscribe();
        binding.setPresenter(presenter);
        binding.imageSelect.setSelectImageCallback(this);
        presenter.setData(customer, contract);
    }

    @Override
    public void onDestroy() {
        if (binding != null) {
            binding.imageSelect.recycleBitmap();
        }
        presenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    public void setPresenter(CreateNewConnectorInformationFragmentContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        showLoading();
    }

    @Override
    public void hideLoading() {
        hideLoading();
    }

    @Override
    public void onCancel() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onNext() {
        CreateNewConnectorInformation2Fragment fragment;
        fragment = CreateNewConnectorInformation2Fragment.newInstance();
        FragmentTransaction transaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_connector_mobile, fragment);
        transaction.addToBackStack(CreateNewConnectorInformation2Fragment.STRING_NAME);
        transaction.commit();
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
