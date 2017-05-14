package com.viettel.mbccs.base.searchtableview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.databinding.FragmentBaseSearchTableviewBinding;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public abstract class BaseSearchTableViewFragment extends BaseFragment
        implements BaseSearchTableViewContract.ViewModel {

    protected FragmentBaseSearchTableviewBinding mBinding;
    protected BaseSearchTableViewPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base_search_tableview, container, false);
        mPresenter = getPresenter();
        mBinding.setPresenter(mPresenter);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.view_stub);
        viewStub.setLayoutResource(getSearchFormId());
        viewStub.inflate();
    }

    protected abstract BaseSearchTableViewPresenter getPresenter();

    @LayoutRes
    protected abstract int getSearchFormId();
}
