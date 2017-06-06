package com.viettel.mbccs.screen.assigntask.arising.create;

import android.content.Context;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;

/**
 * Created by Anh Vu Viet on 5/23/2017.
 */

public class CreateArisingTaskPresenter implements CreatingArisingTaskContract.Presenter {

    public ObservableField<String> taskName;

    public ObservableField<String> taskDescription;

    private Context mContext;

    private CreatingArisingTaskContract.ViewModel mViewModel;

    public CreateArisingTaskPresenter(Context context,
            CreatingArisingTaskContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        taskName = new ObservableField<>();
        taskDescription = new ObservableField<>();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public String getToolbarTitle() {
        return mContext.getString(R.string.create_arising_task_presenter_giao_viec_phat_sinh);
    }

    public void onSelectStaffClick() {
        mViewModel.openStaffPicker();
    }

    public void close() {
        mViewModel.onBackPressed();
    }

    public void assign() {
        // TODO: 5/27/2017 Validate info
        mViewModel.assignTask();
    }
}
