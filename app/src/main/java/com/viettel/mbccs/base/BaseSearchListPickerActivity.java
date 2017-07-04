package com.viettel.mbccs.base;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.databinding.ActivityBaseSearchListPickerBinding;

/**
 * Created by FRAMGIA\vu.viet.anh on 26/05/2017.
 */

public abstract class BaseSearchListPickerActivity extends
        BaseDataBindActivity<ActivityBaseSearchListPickerBinding, BaseSearchListPickerPresenter>
        implements BaseSearchListViewContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_base_search_list_picker;
    }

    @Override
    public void onAddClick() {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
