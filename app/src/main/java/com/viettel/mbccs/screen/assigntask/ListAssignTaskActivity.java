package com.viettel.mbccs.screen.assigntask;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.TaskShopManagement;
import com.viettel.mbccs.databinding.LayoutPopupSpinnerBinding;
import com.viettel.mbccs.screen.assigntask.arising.create.CreateArisingTaskActivity;
import com.viettel.mbccs.screen.assigntask.cskpp.create.CreateCSKPPTaskActivity;
import com.viettel.mbccs.screen.assigntask.cskpp.detail.TaskCSKPPDetailActivity;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by Anh Vu Viet on 5/20/2017.
 */

public class ListAssignTaskActivity
        extends BaseListTaskActivity<ListAssignTaskPresenter> {

    @Override
    protected void initData() {
        mPresenter = new ListAssignTaskPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void onAddClick() {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.task_type_no_home));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final LayoutPopupSpinnerBinding binding = LayoutPopupSpinnerBinding.inflate(getLayoutInflater());
        binding.setLabel(getString(R.string.task_type));
        binding.setAdapter(adapter);
        new AlertDialog.Builder(this).setTitle(R.string.select_assign_task_type)
                .setView(binding.getRoot())
                .setPositiveButton(R.string.text_continue, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (binding.spinner.getSpinner().getSelectedItemPosition()) {
                            case 0:
                                startActivity(new Intent(ListAssignTaskActivity.this,
                                        CreateCSKPPTaskActivity.class));
                                break;
                            case 1:
                                startActivity(new Intent(ListAssignTaskActivity.this,
                                        CreateArisingTaskActivity.class));
                                break;
                            default:
                                break;
                        }
                    }
                })
                .setNegativeButton(R.string.common_label_close, null)
                .setCancelable(false)
                .create()
                .show();
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
