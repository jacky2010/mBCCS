package com.viettel.mbccs.widget.viewholderbinding;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by FRAMGIA\hoang.van.cuong on 21/06/2017.
 */

public class BaseViewHolderBinding<T extends ViewDataBinding, K> extends RecyclerView.ViewHolder {

    public T mBinding;

    public BaseViewHolderBinding(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bindData(K k) {

    }
}
