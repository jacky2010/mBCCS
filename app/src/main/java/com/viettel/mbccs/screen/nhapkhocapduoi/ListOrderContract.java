package com.viettel.mbccs.screen.nhapkhocapduoi;

import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public interface ListOrderContract {

    interface ViewModel extends BaseSearchListViewContract.ViewModel<Presenter> {

    }

    interface Presenter extends BaseSearchListViewContract.Presenter {
    }
}
