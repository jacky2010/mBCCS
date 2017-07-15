package com.viettel.mbccs.screen.connector.fragment.postpaid;

import android.content.DialogInterface;
import android.graphics.Bitmap;
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
import com.viettel.mbccs.databinding.FragmentCreateNewConnectorInformationPostpaid3Binding;
import com.viettel.mbccs.screen.connector.fragment.confirm.ConfirmConnectSubscriberFragment;
import com.viettel.mbccs.screen.connector.fragment.confirm.ConfirmConnectSubscriberPresenter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.widget.model.AddressApp;

/**
 * Created by HuyQuyet on 6/5/17.
 */

public class CreateNewConnectorInformationPostpaid3Fragment extends BaseFragment
        implements CreateNewConnectorInformationPostpaidFragmentContract.ViewFragment3 {
    public static final String STRING_NAME = "CreateNewConnectorInformation2Fragment";

    private FragmentCreateNewConnectorInformationPostpaid3Binding binding;
    private CreateNewConnectorInformationPostpaidFragmentPresenter presenter;
    private CreateNewConnectorInformationPostpaidViewModel viewModel;
    private boolean isFirst = true;

    public static CreateNewConnectorInformationPostpaid3Fragment newInstance() {
        Bundle bundle = new Bundle();
        CreateNewConnectorInformationPostpaid3Fragment fragment =
                new CreateNewConnectorInformationPostpaid3Fragment();
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
        binding = FragmentCreateNewConnectorInformationPostpaid3Binding.inflate(inflater, container,
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
    public void onCancel() {

    }

    @Override
    public AddressApp getAddress() {
        return binding.customSelectAddressCreateConnector3.getAddress();
    }

    @Override
    public boolean getFragmentVisible() {
        return getUserVisibleHint();
    }

    @Override
    public String getNgayDangKy() {
        return binding.dateNgayKy.getStringDate();
    }

    @Override
    public String getNgayHieuLuc() {
        return binding.dateFromNgayHieuLuc.getStringDate();
    }

    @Override
    public String getNgayHetHan() {
        return binding.dateToNgayHieuLuc.getStringDate();
    }

    @Override
    public String getNgayThu() {
        return binding.dateNgayThu.getStringDate();
    }

    @Override
    public AddressApp getAddressApp() {
        return binding.customSelectAddressCreateConnector3.getAddress();
    }

    @Override
    public void connectSubscriber(ConnectSubscriberRequest request, UserInfo info,
            Bitmap customerCurrentImageFront, Bitmap customerCurrentImageBackside,
            Bitmap customerCurrentImagePortrait) {
        ConfirmConnectSubscriberFragment fragment = ConfirmConnectSubscriberFragment.newInstance();
        ConfirmConnectSubscriberPresenter presenter =
                new ConfirmConnectSubscriberPresenter(getActivity(), fragment, request, info,
                        customerCurrentImageFront, customerCurrentImageBackside,
                        customerCurrentImagePortrait);

        FragmentTransaction transaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_connector_mobile_postpaid_view_pager, fragment);
        transaction.addToBackStack(ConfirmConnectSubscriberFragment.STRING_NAME);
        transaction.commit();
    }

    @Override
    public void loadDataSpinner3TenNganHang(BaseException error) {
        // TODO: 7/8/17
        DialogUtils.showDialogError(getActivity(), error, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        }, false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        //        if (!isFirst && !isVisibleToUser && !presenter.validateSubscriber()) {
        //            presenter.onPageChange(1);
        //        }
    }
}
