package com.viettel.mbccs.screen.assigntask;

import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public interface ListAssignTaskContract {

    interface ViewModel extends BaseSearchListViewContract.ViewModel<Presenter> {
        long getFromDate();

        long getToDate();

//        void onTaskClicked(TaskShopManagement task);
    }

    interface Presenter extends BaseSearchListViewContract.Presenter {
    }
}
