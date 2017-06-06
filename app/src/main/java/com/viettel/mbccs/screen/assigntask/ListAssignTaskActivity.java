package com.viettel.mbccs.screen.assigntask;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.data.model.TaskModel;
import com.viettel.mbccs.databinding.ActivityTaskSearchListBinding;
import com.viettel.mbccs.databinding.LayoutPopupSpinnerBinding;
import com.viettel.mbccs.screen.assigntask.arising.create.CreateArisingTaskActivity;
import com.viettel.mbccs.screen.assigntask.cskpp.create.CreateCSKPPTaskActivity;
import com.viettel.mbccs.screen.assigntask.cskpp.detail.TaskCSKPPDetailActivity;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by Anh Vu Viet on 5/20/2017.
 */

public class ListAssignTaskActivity
        extends BaseDataBindActivity<ActivityTaskSearchListBinding, ListAssignTaskPresenter>
        implements ListAssignTaskContract.ViewModel {

    public static int REQUEST_CODE_TASK_INFO = 57;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_task_search_list;
    }

    @Override
    protected void initData() {
        mPresenter = new ListAssignTaskPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(BaseSearchListViewContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
                            case TaskModel.TaskType.TYPE_CSKPP:
                                startActivity(new Intent(ListAssignTaskActivity.this,
                                        CreateCSKPPTaskActivity.class));
                                break;
                            case TaskModel.TaskType.TYPE_ARISING:
                                startActivity(new Intent(ListAssignTaskActivity.this,
                                        CreateArisingTaskActivity.class));
                                break;
                            case TaskModel.TaskType.TYPE_TEAM:
                                break;
                        }
                    }
                })
                .setNegativeButton(R.string.close, null)
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public void onItemClicked(Object object) {
        // TODO: 5/28/2017 Phan quyen user nguoi giao/duoc giao
        switch (((TaskModel) object).getStatus()) {
            case TaskModel.TaskStatus.NOT_ACCEPTED:
                Intent intent = new Intent(this, TaskCSKPPDetailActivity.class);
                intent.putExtra(Constants.BundleConstant.TASK_INFO, (TaskModel) object);
                startActivityForResult(intent, REQUEST_CODE_TASK_INFO);
                break;
            case TaskModel.TaskStatus.INPROGRESS:
                break;
        }
    }
}
