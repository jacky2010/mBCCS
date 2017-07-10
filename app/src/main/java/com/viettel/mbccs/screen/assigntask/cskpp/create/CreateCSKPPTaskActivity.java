package com.viettel.mbccs.screen.assigntask.cskpp.create;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.ActivityCreateCskppTaskBinding;
import com.viettel.mbccs.screen.assigntask.arising.create.CreateArisingTaskActivity;
import com.viettel.mbccs.screen.assigntask.staffpicker.StaffPickerActivity;
import com.viettel.mbccs.screen.common.success.DialogFullScreen;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.CustomDialog;

/**
 * Created by FRAMGIA\vu.viet.anh on 23/05/2017.
 */

public class CreateCSKPPTaskActivity
        extends BaseDataBindActivity<ActivityCreateCskppTaskBinding, CreateCSKPPTaskPresenter>
        implements CreatingCSKPPTaskContract.ViewModel {

    private StaffInfo mSelectedStaff;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_cskpp_task;
    }

    @Override
    protected void initData() {
        mPresenter = new CreateCSKPPTaskPresenter(this, this);
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
        startActivityForResult(new Intent(this, StaffPickerActivity.class), CreateArisingTaskActivity.REQUEST_STAFF_INFO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CreateArisingTaskActivity.REQUEST_STAFF_INFO && resultCode == RESULT_OK && data != null) {
            mSelectedStaff = data.getParcelableExtra(Constants.BundleConstant.STAFF_INFO);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showAssignTaskDialog() {
        new CustomDialog(this, getString(R.string.confirm),
                getString(R.string.create_CSKPP_task_activity_ban_co_chac_muon_giao_viec, "ABC"),
                false, getString(R.string.common_label_close), getString(R.string.assign), null,
                new CustomDialog.OnInputDialogListener() {
                    @Override
                    public void onClick(DialogInterface var1, int var2, String input) {
                        mPresenter.createTask();
                    }
                }, null, false, false).show();
    }

    @Override
    public void showSuccessDialog() {
        Dialog dia = new DialogFullScreen.Builder(
                CreateCSKPPTaskActivity.this).setCenterContent(true)
                .setAutoClose(true)
                .setTitle(getString(
                        R.string.create_CSKPP_task_activity_giao_viec_thanh_cong))
                .setContent(
                        getString(R.string.tin_nhan_thong_bao_da_gui_toi_nhan_vien))
                .build();

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
