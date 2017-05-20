package com.viettel.mbccs.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseDataBindActivity<T extends ViewDataBinding,K> extends BaseActivity {

    protected T mBinding;
    protected K mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mBinding =  DataBindingUtil.setContentView(this, getIdLayout());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setTitleToolbar(int idTitle) {
        super.setTitleToolbar(idTitle);
    }
}
