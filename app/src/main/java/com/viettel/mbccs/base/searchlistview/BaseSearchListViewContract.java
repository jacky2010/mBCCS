package com.viettel.mbccs.base.searchlistview;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public interface BaseSearchListViewContract {

    interface ViewModel<T extends Presenter> extends BaseView<Presenter> {
        void onAddClick();

        void onBackPressed();
    }

    interface Presenter extends BasePresenter {
    }
}
