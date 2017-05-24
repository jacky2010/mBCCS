package com.viettel.mbccs.screen.assignjob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.data.model.JobModel;
import com.viettel.mbccs.databinding.ActivityJobSearchListBinding;
import com.viettel.mbccs.databinding.LayoutPopupSpinnerBinding;
import com.viettel.mbccs.screen.assignjob.arising.CreateArisingJobActivity;

/**
 * Created by Anh Vu Viet on 5/20/2017.
 */

public class ListAssignJobActivity
        extends BaseDataBindActivity<ActivityJobSearchListBinding, ListAssignJobPresenter>
        implements ListAssignJobContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_job_search_list;
    }

    @Override
    protected void initData() {
        mPresenter = new ListAssignJobPresenter(this, this);
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
                        getResources().getStringArray(R.array.job_type_no_home));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final LayoutPopupSpinnerBinding binding = LayoutPopupSpinnerBinding.inflate(getLayoutInflater());
        binding.setLabel(getString(R.string.job_type));
        binding.setAdapter(adapter);
        new AlertDialog.Builder(this).setTitle(R.string.select_assign_job_type)
                .setView(binding.getRoot())
                .setPositiveButton(R.string.text_continue, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (binding.spinner.getSpinner().getSelectedItemPosition()) {
                            case JobModel.JobType.TYPE_CSKPP:
                                break;
                            case JobModel.JobType.TYPE_ARISING:
                                startActivity(new Intent(ListAssignJobActivity.this,
                                        CreateArisingJobActivity.class));
                                break;
                            case JobModel.JobType.TYPE_TEAM:
                                break;
                        }
                    }
                })
                .setNegativeButton(R.string.close, null)
                .setCancelable(false)
                .create()
                .show();
    }
}
