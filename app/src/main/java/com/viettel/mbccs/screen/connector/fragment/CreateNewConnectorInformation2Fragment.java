package com.viettel.mbccs.screen.connector.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.remote.request.ConnectSubscriberRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.FragmentCreateNewConnectorInformation2Binding;
import com.viettel.mbccs.screen.connector.fragment.confirm.ConfirmConnectSubscriberFragment;
import com.viettel.mbccs.screen.connector.fragment.confirm.ConfirmConnectSubscriberPresenter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.widget.CustomSelectAddress;

/**
 * Created by HuyQuyet on 6/5/17.
 */

public class CreateNewConnectorInformation2Fragment extends BaseFragment
        implements CreateNewConnectorInformationFragmentContract.ViewFragment2 {
    public static final String STRING_NAME = "CreateNewConnectorInformation2Fragment";
    private FragmentCreateNewConnectorInformation2Binding binding;
    private CreateNewConnectorInformationFragmentPresenter presenter;

    public static CreateNewConnectorInformation2Fragment newInstance() {
        Bundle bundle = new Bundle();
        CreateNewConnectorInformation2Fragment fragment =
                new CreateNewConnectorInformation2Fragment();
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
        binding = FragmentCreateNewConnectorInformation2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setPresenter(presenter);
        presenter.loadDataCreateView2();
    }

    @Override
    public void onDestroy() {
        presenter.unSubscribe();
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
    public void setPresenter(CreateNewConnectorInformationFragmentContract.Presenter presenter) {
        this.presenter = (CreateNewConnectorInformationFragmentPresenter) presenter;
    }

    @Override
    public void loadDataSpinnerError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        }, false);
    }

    @Override
    public void onCancel() {
//        getActivity().getSupportFragmentManager().popBackStack();
        getActivity().onBackPressed();
    }

    @Override
    public CustomSelectAddress.Address getAddress() {
        return binding.customSelectAddressCreateConnector2.getAddress();
    }

    @Override
    public void connectSubscriber(ConnectSubscriberRequest request, UserInfo userInfo) {
        ConfirmConnectSubscriberFragment fragment = ConfirmConnectSubscriberFragment.newInstance();
        ConfirmConnectSubscriberPresenter presenter =
                new ConfirmConnectSubscriberPresenter(getActivity(), fragment, request, userInfo);

        FragmentTransaction transaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_connector_mobile, fragment);
        transaction.addToBackStack(ConfirmConnectSubscriberFragment.STRING_NAME);
        transaction.commit();
    }
}
