package com.viettel.mbccs.screen.assigntask;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityTaskSearchListBinding;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public abstract class BaseListTaskActivity<T extends BaseListTaskPresenter> extends BaseDataBindActivity<ActivityTaskSearchListBinding, T>
        implements ListAssignTaskContract.ViewModel {

    public static final int REQUEST_CODE_TASK_INFO = 57;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_task_search_list;
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
    public long getFromDate() {
        return mBinding.fromDate.getDateInMilis();
    }

    @Override
    public long getToDate() {
        return mBinding.toDate.getDateInMilis();
    }
}
