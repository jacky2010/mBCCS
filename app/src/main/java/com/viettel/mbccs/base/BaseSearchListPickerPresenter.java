package com.viettel.mbccs.base;

import android.content.Context;
import android.databinding.ObservableField;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewPresenter;

/**
 * Created by FRAMGIA\vu.viet.anh on 26/05/2017.
 */

public abstract class BaseSearchListPickerPresenter<T>
        extends BaseSearchListViewPresenter<T, BaseSearchListViewContract.ViewModel> {

    public ObservableField<String> text;

    public BaseSearchListPickerPresenter(Context context,
            BaseSearchListViewContract.ViewModel viewModel) {
        super(context, viewModel);
        text = new ObservableField<>();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void onAddClick() {

    }
}
