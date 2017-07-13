package com.viettel.mbccs.screen.assigntask;

import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public interface BaseListTaskContract {

    interface ViewModel extends BaseSearchListViewContract.ViewModel<Presenter> {
        long getFromDate();

        long getToDate();

        void closeDrawer();

//        void onTaskClicked(TaskShopManagement task);
    }

    interface Presenter extends BaseSearchListViewContract.Presenter {
    }
}
