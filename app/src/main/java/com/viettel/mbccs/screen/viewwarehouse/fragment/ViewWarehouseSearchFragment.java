package com.viettel.mbccs.screen.viewwarehouse.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.databinding.FragmentViewWareHouseSearchBinding;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class ViewWarehouseSearchFragment extends BaseFragment
        implements ViewWarehouseSearchContract.View {
    private FragmentViewWareHouseSearchBinding binding;
    private ViewWarehouseSearchPresenter presenter;

    public static ViewWarehouseSearchFragment newInstance() {
        Bundle bundle = new Bundle();
        ViewWarehouseSearchFragment fragment = new ViewWarehouseSearchFragment();
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
        binding = FragmentViewWareHouseSearchBinding.inflate(inflater, container, false);
        presenter = new ViewWarehouseSearchPresenter(getActivity(), this);
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
}
