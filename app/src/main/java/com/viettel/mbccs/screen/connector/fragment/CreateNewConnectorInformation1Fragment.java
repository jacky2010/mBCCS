package com.viettel.mbccs.screen.connector.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.databinding.FragmentCreateNewConnectorInformation1Binding;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public class CreateNewConnectorInformation1Fragment extends Fragment
        implements CreateNewConnectorInformationFragmentContract.ViewFragment1 {
    public static final String STRING_NAME = "CreateNewConnectorInformationFragment";
    private static final String ARG_CUSTOMER = "CUSTOMER";
    private static final String ARG_CONTRACT = "CONTRACT";

    private FragmentCreateNewConnectorInformation1Binding binding;
    private CreateNewConnectorInformationFragmentPresenter presenter;

    private Customer customer;
    private Contract contract;

    public static CreateNewConnectorInformation1Fragment newInstance(Customer customer,
            Contract contract) {
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
    }

    @Override
    public void onDestroy() {
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
}
