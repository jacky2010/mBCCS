package com.viettel.mbccs.screen.assigntask;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewPresenter;
import com.viettel.mbccs.data.model.TaskModel;
import com.viettel.mbccs.screen.assigntask.adapters.AssignTaskAdapter;
import com.viettel.mbccs.widget.SpinnerWithBorder.HintAdapter;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public class ListAssignTaskPresenter extends BaseSearchListViewPresenter<TaskModel>
        implements ListAssignTaskContract.Presenter, AssignTaskAdapter.OnTaskClickListener {

    public ObservableInt taskTypePosition = new ObservableInt();

    public ObservableInt taskStatusPosition = new ObservableInt();

    public ObservableField<HintAdapter<String>> taskTypeAdapter =
            new ObservableField<HintAdapter<String>>() {
                @Override
                public void set(HintAdapter<String> value) {
                    super.set(value);
                    taskTypePosition.set(value.getCount());
                }
            };

    public ObservableField<HintAdapter<String>> taskStatusAdapter =
            new ObservableField<HintAdapter<String>>() {
                @Override
                public void set(HintAdapter<String> value) {
                    super.set(value);
                    taskStatusPosition.set(value.getCount());
                }
            };

    public ListAssignTaskPresenter(Context context, ListAssignTaskContract.ViewModel viewModel) {
        super(context, viewModel);
        // TODO: Fake data
        listData.add(
                new TaskModel("CV 1", TaskModel.TaskType.TYPE_ARISING, System.currentTimeMillis(),
                        TaskModel.TaskStatus.DONE, "startDate", "endDate", "assignDate",
                        "description"));
        listData.add(new TaskModel("CV 2", TaskModel.TaskType.TYPE_TEAM, System.currentTimeMillis(),
                TaskModel.TaskStatus.INPROGRESS, "startDate", "endDate", "assignDate",
                "description"));
        listData.add(
                new TaskModel("CV 3", TaskModel.TaskType.TYPE_CSKPP, System.currentTimeMillis(),
                        TaskModel.TaskStatus.NOT_ACCEPTED, "startDate", "endDate", "assignDate",
                        "description"));
        listData.add(
                new TaskModel("CV 4", TaskModel.TaskType.TYPE_ARISING, System.currentTimeMillis(),
                        TaskModel.TaskStatus.REJECTED, "startDate", "endDate", "assignDate",
                        "description"));
        HintAdapter<String> adapter =
                new HintAdapter<>(mContext, android.R.layout.simple_spinner_item,
                        android.R.id.text1,
                        mContext.getResources().getStringArray(R.array.task_type));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskTypeAdapter.set(adapter);

        HintAdapter<String> adapter1 =
                new HintAdapter<>(mContext, android.R.layout.simple_spinner_item,
                        android.R.id.text1,
                        mContext.getResources().getStringArray(R.array.task_status));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskStatusAdapter.set(adapter1);
    }

    @Override
    public void doSearch() {

    }

    @Override
    public void onSearchSuccess() {

    }

    @Override
    public void onSearchFail() {

    }

    @Override
    public String getSearchHint() {
        return mContext.getString(R.string.search_task_hint);
    }

    @Override
    public String getToolbarTitle() {
        return mContext.getString(R.string.quan_ly_giao_viec);
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }

    @Override
    protected RecyclerView.Adapter getListAdapter() {
        AssignTaskAdapter adapter = new AssignTaskAdapter(mContext, listData);
        adapter.setOnTaskClickListener(this);
        return adapter;
    }

    @Override
    public void onAddClick() {
        mViewModel.onAddClick();
    }

    @Override
    public void onTaskClick(TaskModel task) {
        ((ListAssignTaskContract.ViewModel) mViewModel).onTaskClicked(task);
    }
}
