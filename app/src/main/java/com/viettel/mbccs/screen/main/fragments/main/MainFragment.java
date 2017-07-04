package com.viettel.mbccs.screen.main.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.databinding.FragmentMainBinding;

/**
 * Created by FRAMGIA\vu.viet.anh on 15/05/2017.
 */

public class MainFragment extends BaseFragment implements MainFragmentContract.ViewModel {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        binding.setPresenter(new MainFragmentPresenter(getActivity(), this));
        return binding.getRoot();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
