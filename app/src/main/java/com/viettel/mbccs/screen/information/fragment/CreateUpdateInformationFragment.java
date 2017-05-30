package com.viettel.mbccs.screen.information.fragment;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.databinding.FragmentCreateUpdateInformationBinding;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class CreateUpdateInformationFragment extends BaseFragment
        implements CreateUpdateInformationFragmentContract.View {

    @IntDef({ Type.CREATE_INFORMATION, Type.CREATE_INFORMATION_CLONE })
    public @interface Type {
        int CREATE_INFORMATION = 1;
        int CREATE_INFORMATION_CLONE = 2;
    }

    private static final String ARG_TYPE_FRAGMENT = "TYPE_FRAGMENT";
    private FragmentCreateUpdateInformationBinding binding;
    private CreateUpdateInformationFragmentPresenter presenter;
    @Type
    private int typeFragment;

    public static CreateUpdateInformationFragment newInstance(@Type int typeFragment) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TYPE_FRAGMENT, typeFragment);
        CreateUpdateInformationFragment fragment = new CreateUpdateInformationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeFragment = getArguments().getInt(ARG_TYPE_FRAGMENT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateUpdateInformationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CreateUpdateInformationFragmentPresenter(getActivity(), this);
        presenter.setTypeFragment(typeFragment);
        binding.setPresenter(presenter);
    }

    @Override
    public void onStop() {
        presenter.unSubscribe();
        super.onStop();
    }

    @Override
    public void setPresenter(CreateUpdateInformationFragmentContract.Presenter presenter) {

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
    public void onCancel() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onSelectImage(View v) {
        switch (v.getId()) {
            case R.id.image_front:
                break;
            case R.id.image_backside:
                break;
            case R.id.image_portrait:
                break;
        }
    }
}
