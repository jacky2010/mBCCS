package com.viettel.mbccs.screen.connector.fragment.confirm;

import android.app.Dialog;
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
import com.viettel.mbccs.screen.connector.ConnectMobileViewPagerBaseActivity;
import com.viettel.mbccs.screen.connector.listener.OnBackPressActivity;
import com.viettel.mbccs.utils.DialogUtils;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class ConfirmConnectSubscriberFragment extends BaseFragment
        implements ConfirmConnectSubscriberContract.View, OnBackPressActivity {
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
        ((ConnectMobileViewPagerBaseActivity) getActivity()).setOnBackPressActivity(this);
    }

    @Override
    public void onStop() {
        presenter.unSubscribe();
        ((ConnectMobileViewPagerBaseActivity) getActivity()).removeOnBackPressActivity();
        super.onStop();
    }

    @Override
    public void onClose() {
        onBackPressActivity();
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

        //        DialogUtils.showDialog(getActivity(), "Send Image", "Send image now", "OK",
        //                new DialogInterface.OnClickListener() {
        //                    @Override
        //                    public void onClick(DialogInterface dialog, int which) {
        //                        presenter.clickSendImage(true);
        //                        showDialog(result, isRegister);
        //                    }
        //                }, "No", new DialogInterface.OnClickListener() {
        //                    @Override
        //                    public void onClick(DialogInterface dialog, int which) {
        //                        presenter.clickSendImage(false);
        //                        showDialog(result, isRegister);
        //                    }
        //                });

        presenter.clickSendImage(true);
        showDialog();

    }

    private void showDialog(){
        Dialog dia = new DialogFullScreen.Builder(getActivity()).setCenterContent(true)
                .setTitle(getString(R.string.confirm_connect_subscriber_dialog_dau_noi_thanh_cong))
                .setContent(getString(R.string.confirm_connect_subscriber_dialog_gui_tin_nhan))
                .setPositiveButton(
                        getString(R.string.confirm_connect_subscriber_dialog_toi_dang_ky_vas))
                .setNegativeButton(getString(R.string.confirm_connect_subscriber_dialog_bo_qua))
                .setDescription(getString(R.string.confirm_connect_subscriber_dialog_dang_ky_vas))
                .setListener(new DialogFullScreen.SuccessDialogListener() {
                    @Override
                    public void onPositiveButtonClick(Dialog dialog) {
                        // TODO: 7/4/17 fake
                        dialog.dismiss();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //                                startActivity(
                                //                                        new Intent(getActivity(), ConnectorMobileActivity.class));
                                getActivity().finish();
                            }
                        }, 200);
                    }

                    @Override
                    public void onNegativeButtonClick(Dialog dialog) {
                        dialog.dismiss();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //                                startActivity(
                                //                                        new Intent(getActivity(), ConnectorMobileActivity.class));
                                getActivity().finish();
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

    @Override
    public void onBackPressActivity() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
