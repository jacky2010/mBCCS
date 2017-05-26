package com.viettel.mbccs.screen.assigntask;

import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.data.model.TaskModel;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public interface ListAssignTaskContract {

    interface ViewModel extends BaseSearchListViewContract.ViewModel<Presenter> {

        void onTaskClicked(TaskModel task);
    }

    interface Presenter extends BaseSearchListViewContract.Presenter {
    }
}
