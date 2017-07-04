package com.viettel.mbccs.screen.assigntask.cskpp.update;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityUpdateCskppBinding;

/**
 * Created by FRAMGIA\vu.viet.anh on 29/05/2017.
 */

public class TaskCSKPPUpdateActivity
        extends BaseDataBindActivity<ActivityUpdateCskppBinding, TaskCSKPPUpdatePresenter>
        implements TaskCSKPPUpdateContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_update_cskpp;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onAccept() {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
