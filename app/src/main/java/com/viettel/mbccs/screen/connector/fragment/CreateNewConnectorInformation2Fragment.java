package com.viettel.mbccs.screen.connector.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.databinding.FragmentCreateNewConnectorInformation2Binding;

/**
 * Created by HuyQuyet on 6/5/17.
 */

public class CreateNewConnectorInformation2Fragment extends Fragment
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
    }

    @Override
    public void onDestroy() {
        presenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    public void setPresenter(CreateNewConnectorInformationFragmentContract.Presenter pre) {
        this.presenter = (CreateNewConnectorInformationFragmentPresenter) pre;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onCancel() {
//        getActivity().getSupportFragmentManager().popBackStack();
        Log.i("CreateNewConnectorInformation2Fragment", " -> onCancel: ----------------: ");
        getActivity().onBackPressed();
    }

    @Override
    public void onEnter() {
        getActivity().finish();
    }
}
