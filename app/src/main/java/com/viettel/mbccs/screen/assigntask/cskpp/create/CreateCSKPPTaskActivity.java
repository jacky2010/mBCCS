package com.viettel.mbccs.screen.assigntask.cskpp.create;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityCreateCskppTaskBinding;
import com.viettel.mbccs.screen.assigntask.staffpicker.StaffPickerActivity;
import com.viettel.mbccs.screen.common.success.DialogFullScreen;

/**
 * Created by FRAMGIA\vu.viet.anh on 23/05/2017.
 */

public class CreateCSKPPTaskActivity
        extends BaseDataBindActivity<ActivityCreateCskppTaskBinding, CreateCSKPPTaskPresenter>
        implements CreatingCSKPPTaskContract.ViewModel {

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
    public void setPresenter(CreatingCSKPPTaskContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openStaffPicker() {
        // TODO: 5/27/2017 Pass on info to get list
        startActivity(new Intent(this, StaffPickerActivity.class));
    }

    @Override
    public void assignTask() {
        new AlertDialog.Builder(this).setTitle(R.string.confirm)
                .setMessage(
                        getString(R.string.create_CSKPP_task_activity_ban_co_chac_muon_giao_viec,
                                "ABC")) // TODO: 31/05/2017 ten kpp
                .setPositiveButton(R.string.assign, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 5/27/2017 Api call
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
                })
                .setNegativeButton(R.string.common_label_close, null)
                .setCancelable(false)
                .create()
                .show();
    }
}
