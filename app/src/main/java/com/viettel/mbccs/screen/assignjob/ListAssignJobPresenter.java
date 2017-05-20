package com.viettel.mbccs.screen.assignjob;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewPresenter;
import com.viettel.mbccs.data.model.JobModel;
import com.viettel.mbccs.widget.SpinnerWithBorder.HintAdapter;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public class ListAssignJobPresenter extends BaseSearchListViewPresenter<JobModel>
        implements ListAssignJobContract.Presenter {

    public ObservableInt jobTypePosition = new ObservableInt();

    public ObservableInt jobStatusPosition = new ObservableInt();

    public ObservableField<HintAdapter<String>> jobTypeAdapter =
            new ObservableField<HintAdapter<String>>() {
                @Override
                public void set(HintAdapter<String> value) {
                    super.set(value);
                    jobTypePosition.set(value.getCount());
                }
            };

    public ObservableField<HintAdapter<String>> jobStatusAdapter =
            new ObservableField<HintAdapter<String>>() {
                @Override
                public void set(HintAdapter<String> value) {
                    super.set(value);
                    jobStatusPosition.set(value.getCount());
                }
            };

    public ListAssignJobPresenter(Context context,
                                  BaseSearchListViewContract.ViewModel viewModel) {
        super(context, viewModel);
        // TODO: Fake data
        listData.add(new JobModel("CV 1", JobModel.JobType.TYPE_ARISING, System.currentTimeMillis(),
                JobModel.JobStatus.DONE));
        listData.add(new JobModel("CV 2", JobModel.JobType.TYPE_TEAM, System.currentTimeMillis(),
                JobModel.JobStatus.INPROGRESS));
        listData.add(new JobModel("CV 3", JobModel.JobType.TYPE_CSKPP, System.currentTimeMillis(),
                JobModel.JobStatus.NOT_ACCEPTED));
        listData.add(new JobModel("CV 4", JobModel.JobType.TYPE_ARISING, System.currentTimeMillis(),
                JobModel.JobStatus.REJECTED));
        listData.add(new JobModel("CV 4", JobModel.JobType.TYPE_ARISING, System.currentTimeMillis(),
                JobModel.JobStatus.REJECTED));
        listData.add(new JobModel("CV 4", JobModel.JobType.TYPE_ARISING, System.currentTimeMillis(),
                JobModel.JobStatus.REJECTED));
        listData.add(new JobModel("CV 4", JobModel.JobType.TYPE_ARISING, System.currentTimeMillis(),
                JobModel.JobStatus.REJECTED));
        listData.add(new JobModel("CV 4", JobModel.JobType.TYPE_ARISING, System.currentTimeMillis(),
                JobModel.JobStatus.REJECTED));
        HintAdapter<String> adapter =
                new HintAdapter<>(mContext, android.R.layout.simple_spinner_item,
                        android.R.id.text1,
                        mContext.getResources().getStringArray(R.array.job_type));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobTypeAdapter.set(adapter);

        HintAdapter<String> adapter1 =
                new HintAdapter<>(mContext, android.R.layout.simple_spinner_item,
                        android.R.id.text1,
                        mContext.getResources().getStringArray(R.array.job_status));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobStatusAdapter.set(adapter1);
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
        return mContext.getString(R.string.search_job_hint);
    }

    @Override
    protected RecyclerView.Adapter getListAdapter() {
        return new AssignJobAdapter(mContext, listData);
    }

    @Override
    public void onAddClick() {
        mViewModel.onAddClick();
    }
}
