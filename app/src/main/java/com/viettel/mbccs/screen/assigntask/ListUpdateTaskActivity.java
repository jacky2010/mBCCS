package com.viettel.mbccs.screen.assigntask;

import android.content.Intent;

import com.viettel.mbccs.data.model.TaskShopManagement;
import com.viettel.mbccs.screen.assigntask.cskpp.detail.TaskCSKPPDetailActivity;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class ListUpdateTaskActivity extends BaseListTaskActivity<ListUpdateTaskPresenter> {

    @Override
    protected void initData() {
        mPresenter = new ListUpdateTaskPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mBinding.drawer.animateOpen();
    }

    @Override
    public void onAddClick() {
    }

    @Override
    public void onItemClicked(Object object) {
        // TODO: 5/28/2017 Phan quyen user nguoi giao/duoc giao
        switch (((TaskShopManagement) object).getStatus()) {
            case TaskShopManagement.TaskStaffStatus.STATUS_NEW:
                Intent intent = new Intent(this, TaskCSKPPDetailActivity.class);
                intent.putExtra(Constants.BundleConstant.TASK_INFO, (TaskShopManagement) object);
                startActivityForResult(intent, REQUEST_CODE_TASK_INFO);
                break;
            case TaskShopManagement.TaskStatus.STATUS_ASSIGNED:
                break;
        }
    }
}
