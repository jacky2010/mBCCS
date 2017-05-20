package com.viettel.mbccs.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jacky on 5/18/17.
 */

public abstract class BaseDataBindFragment<T extends ViewDataBinding, K> extends BaseDataFragment {

    protected T mBinding;
    protected K mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getIdLayoutRes(), container,
                false);
        initView();
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public BaseDataBindActivity getBaseDataBindActivity() {
        if (getActivity() instanceof BaseDataBindActivity) {
            return (BaseDataBindActivity) getActivity();
        }
        return null;
    }
}
