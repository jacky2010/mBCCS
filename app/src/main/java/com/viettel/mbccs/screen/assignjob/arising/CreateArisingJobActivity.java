package com.viettel.mbccs.screen.assignjob.arising;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityCreateArisingJobBinding;

/**
 * Created by FRAMGIA\vu.viet.anh on 23/05/2017.
 */

public class CreateArisingJobActivity extends BaseDataBindActivity<ActivityCreateArisingJobBinding,
        CreateArisingJobPresenter> implements CreatingArisingJobContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_arising_job;
    }

    @Override
    protected void initData() {
        mPresenter = new CreateArisingJobPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(CreatingArisingJobContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
