package com.viettel.mbccs.screen.main.fragments.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.model.MenuItem;
import com.viettel.mbccs.databinding.FragmentMenuBinding;
import java.util.ArrayList;

/**
 * Created by FRAMGIA\vu.viet.anh on 16/05/2017.
 */

public class MenuFragment extends BaseFragment implements MenuContract.ViewModel {

    public static final String MENU_LIST_KEY = "MENU_LIST_KEY";

    public static MenuFragment newInstance(ArrayList<MenuItem> itemList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(MENU_LIST_KEY, itemList);
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentMenuBinding binding = FragmentMenuBinding.inflate(inflater, container, false);
        ArrayList<MenuItem> list = getArguments().getParcelableArrayList(MENU_LIST_KEY);
        binding.setPresenter(new MenuPresenter(getActivity(), this, list));
        return binding.getRoot();
    }

    @Override
    public void setPresenter(MenuContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
