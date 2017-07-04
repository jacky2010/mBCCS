package com.viettel.mbccs.screen.connector.fragment.confirm;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.FragmentConfirmConnectSubscriberBinding;
import com.viettel.mbccs.screen.common.success.DialogFullScreen;
import com.viettel.mbccs.screen.connector.mobile.ConnectorMobileActivity;
import com.viettel.mbccs.utils.DialogUtils;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class ConfirmConnectSubscriberFragment extends BaseFragment
        implements ConfirmConnectSubscriberContract.View {
    public static final String STRING_NAME = "ConfirmConnectSubscriberFragment";
    private FragmentConfirmConnectSubscriberBinding binding;
    private ConfirmConnectSubscriberPresenter presenter;

    public static ConfirmConnectSubscriberFragment newInstance() {
        Bundle bundle = new Bundle();
        ConfirmConnectSubscriberFragment fragment = new ConfirmConnectSubscriberFragment();
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
        binding = FragmentConfirmConnectSubscriberBinding.inflate(inflater, container, false);
        binding.setPresenter(presenter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.subscribe();
    }

    @Override
    public void onStop() {
        presenter.unSubscribe();
        super.onStop();
    }

    @Override
    public void onClose() {
        getActivity().getSupportFragmentManager().popBackStack();
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
    public void setPresenter(ConfirmConnectSubscriberContract.Presenter presenter) {
        this.presenter = (ConfirmConnectSubscriberPresenter) presenter;
    }

    @Override
    public void connectSubscriberSuccess() {
        Dialog dia = new DialogFullScreen.Builder(getActivity()).setCenterContent(true)
                .setTitle(getString(R.string.confirm_connect_subscriber_dialog_dau_noi_thanh_cong))
                .setContent(getString(R.string.confirm_connect_subscriber_dialog_gui_tin_nhan))
                .setPositiveButton(
                        getString(R.string.confirm_connect_subscriber_dialog_dang_ky_vas))
                .setNegativeButton(getString(R.string.confirm_connect_subscriber_dialog_bo_qua))
                .setListener(new DialogFullScreen.SuccessDialogListener() {
                    @Override
                    public void onPositiveButtonClick(Dialog dialog) {
                        // TODO: 7/4/17 fake
                        dialog.dismiss();
                        getActivity().finish();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(
                                        new Intent(getActivity(), ConnectorMobileActivity.class));
                            }
                        }, 200);
                    }

                    @Override
                    public void onNegativeButtonClick(Dialog dialog) {
                        dialog.dismiss();
                        getActivity().finish();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(
                                        new Intent(getActivity(), ConnectorMobileActivity.class));
                            }
                        }, 200);
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
    public void connectSubscriberError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error);
    }
}
