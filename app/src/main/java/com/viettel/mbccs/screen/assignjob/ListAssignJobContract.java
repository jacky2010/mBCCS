package com.viettel.mbccs.screen.assignjob;

import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public interface ListAssignJobContract {

    interface ViewModel extends BaseSearchListViewContract.ViewModel<Presenter> {
    }

    interface Presenter extends BaseSearchListViewContract.Presenter {
    }
}
