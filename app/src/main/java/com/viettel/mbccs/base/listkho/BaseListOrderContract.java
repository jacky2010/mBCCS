package com.viettel.mbccs.base.listkho;

import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public interface BaseListOrderContract {

    interface ViewModel extends BaseSearchListViewContract.ViewModel<Presenter> {

    }

    interface Presenter extends BaseSearchListViewContract.Presenter {
    }
}
