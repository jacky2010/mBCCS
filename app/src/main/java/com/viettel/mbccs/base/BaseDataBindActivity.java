package com.viettel.mbccs.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseDataBindActivity<T,K> extends BaseActivity {

    protected T mBinding;
    protected K mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = initBinding();
        initData();
    }

    protected abstract T initBinding();

    protected abstract void initData();

}
