package com.viettel.mbccs.screen.assigntask.arising.create;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.ActivityCreateArisingTaskBinding;
import com.viettel.mbccs.screen.assigntask.staffpicker.StaffPickerActivity;
import com.viettel.mbccs.screen.common.success.DialogFullScreen;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.CustomDialog;

/**
 * Created by FRAMGIA\vu.viet.anh on 23/05/2017.
 */

public class CreateArisingTaskActivity extends
        BaseDataBindActivity<ActivityCreateArisingTaskBinding, CreateArisingTaskPresenter>
        implements CreateArisingTaskContract.ViewModel {

    public static final int REQUEST_STAFF_INFO = 1002;

    private StaffInfo mSelectedStaff;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_arising_task;
    }

    @Override
    protected void initData() {
        mPresenter = new CreateArisingTaskPresenter(this, this);
        mBinding.setPresenter(mPresenter);
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
    public void openStaffPicker() {
        startActivityForResult(new Intent(this, StaffPickerActivity.class), REQUEST_STAFF_INFO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_STAFF_INFO && resultCode == RESULT_OK && data != null) {
            mSelectedStaff = data.getParcelableExtra(Constants.BundleConstant.STAFF_INFO);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showAssignTaskDialog() {
        new CustomDialog(this, R.string.confirm, R.string.ban_co_chac_muon_giao_viec_phat_sinh,
                false, R.string.common_label_close, R.string.assign, null, new CustomDialog
                .OnInputDialogListener() {
            @Override
            public void onClick(DialogInterface var1, int var2, String input) {
                mPresenter.createTask();
            }
        }, null, false, false).show();
    }

    @Override
    public void showSuccessDialog() {
        Dialog dia = new DialogFullScreen.Builder(CreateArisingTaskActivity.this)
                .setCenterContent(true).setAutoClose(true).setTitle(getString(R.string
                        .create_arising_task_activity_giao_viec_thanh_cong)).setContent(getString
                        (R.string.tin_nhan_thong_bao_da_gui_toi_nhan_vien)).build();

        dia.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                finish();
            }
        });

        dia.setCancelable(false);
        dia.setCanceledOnTouchOutside(false);

        dia.show();
    }

    @Override
    public long getFromDate() {
        return mBinding.fromDate.getDateInMilis();
    }

    @Override
    public long getToDate() {
        return mBinding.toDate.getDateInMilis();
    }

    @Override
    public StaffInfo getStaff() {
        return mSelectedStaff;
    }

    @Override
    public void showErrorDialog(BaseException e) {
        DialogUtils.showDialogError(this, e);
    }
}
