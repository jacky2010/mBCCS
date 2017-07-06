package com.viettel.mbccs.screen.connector.fragment.prepaid;

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
import com.viettel.mbccs.databinding.FragmentCreateNewConnectorInformationPrepaid2Binding;
import com.viettel.mbccs.screen.connector.fragment.confirm.ConfirmConnectSubscriberFragment;
import com.viettel.mbccs.screen.connector.fragment.confirm.ConfirmConnectSubscriberPresenter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.widget.model.AddressApp;
import java.util.List;

/**
 * Created by HuyQuyet on 6/5/17.
 */

public class CreateNewConnectorInformationPrepaid2Fragment extends BaseFragment
        implements CreateNewConnectorInformationPrepaidFragmentContract.ViewFragment2 {
    public static final String STRING_NAME = "CreateNewConnectorInformation2Fragment";
    private FragmentCreateNewConnectorInformationPrepaid2Binding binding;
    private CreateNewConnectorInformationPrepaidFragmentPresenter presenter;
    private boolean isFirst = true;

    public static CreateNewConnectorInformationPrepaid2Fragment newInstance() {
        Bundle bundle = new Bundle();
        CreateNewConnectorInformationPrepaid2Fragment fragment =
                new CreateNewConnectorInformationPrepaid2Fragment();
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
        binding = FragmentCreateNewConnectorInformationPrepaid2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setPresenter(presenter);
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
    public void setPresenter(CreateNewConnectorInformationPrepaidFragmentContract.Presenter presenter) {
        this.presenter = (CreateNewConnectorInformationPrepaidFragmentPresenter) presenter;
    }

    @Override
    public void loadDataSpinnerError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: 7/6/17
            }
        }, false);
    }

    @Override
    public void onCancel() {
        getActivity().onBackPressed();
    }

    @Override
    public AddressApp getAddress() {
        return binding.customSelectAddressCreateConnector2.getAddress();
    }

    @Override
    public boolean getFragmentVisible() {
        return getUserVisibleHint();
    }

    @Override
    public void connectSubscriber(ConnectSubscriberRequest request, UserInfo userInfo,
            List<String> dataImage) {
        ConfirmConnectSubscriberFragment fragment = ConfirmConnectSubscriberFragment.newInstance();
        ConfirmConnectSubscriberPresenter presenter =
                new ConfirmConnectSubscriberPresenter(getActivity(), fragment, request, userInfo,
                        dataImage);

        FragmentTransaction transaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_connector_mobile_prepaid_view_pager, fragment);
        transaction.addToBackStack(ConfirmConnectSubscriberFragment.STRING_NAME);
        transaction.commit();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isFirst = false;
            presenter.setAddressView2();
        }
    }
}
