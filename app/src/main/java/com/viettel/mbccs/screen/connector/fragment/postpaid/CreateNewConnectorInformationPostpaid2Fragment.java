package com.viettel.mbccs.screen.connector.fragment.postpaid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.FragmentCreateNewConnectorInformationPostpaid2Binding;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.widget.model.AddressApp;

/**
 * Created by HuyQuyet on 6/5/17.
 */

public class CreateNewConnectorInformationPostpaid2Fragment extends BaseFragment
        implements CreateNewConnectorInformationPostpaidFragmentContract.ViewFragment2 {
    public static final String STRING_NAME = "CreateNewConnectorInformationPostpaid2Fragment";

    private FragmentCreateNewConnectorInformationPostpaid2Binding binding;
    private CreateNewConnectorInformationPostpaidFragmentPresenter presenter;
    private CreateNewConnectorInformationPostpaidViewModel viewModel;
    private boolean isFirst = true;

    public static CreateNewConnectorInformationPostpaid2Fragment newInstance() {
        Bundle bundle = new Bundle();
        CreateNewConnectorInformationPostpaid2Fragment fragment =
                new CreateNewConnectorInformationPostpaid2Fragment();
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
        binding = FragmentCreateNewConnectorInformationPostpaid2Binding.inflate(inflater, container,
                false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setPresenter(presenter);
        binding.setViewModel(viewModel);
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
    public void setPresenter(
            CreateNewConnectorInformationPostpaidFragmentContract.Presenter presenter) {
        this.presenter = (CreateNewConnectorInformationPostpaidFragmentPresenter) presenter;
    }

    @Override
    public void setViewModel(CreateNewConnectorInformationPostpaidViewModel viewModel) {
        this.viewModel = viewModel;
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
    public void loadDataSpinnerHanMucError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            presenter.setAddressView2();
            isFirst = false;
        }
    }

    @Override
    public boolean getFragmentVisible(){
        return getUserVisibleHint();
    }
}
