package com.viettel.mbccs.screen.assigntask.cskpp.detail;

import android.content.DialogInterface;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.TaskModel;
import com.viettel.mbccs.databinding.ActivityTaskCskppDetailBinding;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.CustomDialog;

/**
 * Created by Anh Vu Viet on 5/28/2017.
 */

public class TaskCSKPPDetailActivity extends
        BaseDataBindActivity<ActivityTaskCskppDetailBinding, TaskCSKPPDetailPresenter>
        implements TaskCSKPPDetailContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_task_cskpp_detail;
    }

    @Override
    protected void initData() {
        mPresenter = new TaskCSKPPDetailPresenter(this, this,
                (TaskModel) getIntent().getParcelableExtra(Constants.BundleConstant.TASK_INFO), false);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(TaskCSKPPDetailContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onReject() {
        // TODO: 5/28/2017 Handle on click
        new CustomDialog(this, R.string.confirm,
                R.string.ban_co_chac_muon_tu_choi, false,
                R.string.common_label_close, R.string.reject, null,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }, null, false).show();
    }

    @Override
    public void onAccept() {
        // TODO: 5/28/2017 Handle on click
        new CustomDialog(this, R.string.confirm,
                R.string.ban_co_chac_muon_nhan_viec, false,
                R.string.common_label_close, R.string.nhan_viec, null,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }, null, false).show();
    }

    @Override
    public void onUpdate() {

    }
}
