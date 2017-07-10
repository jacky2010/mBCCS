package com.viettel.mbccs.base.searchlistview;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.source.remote.response.BaseException;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public interface BaseSearchListViewContract {

    interface ViewModel<T extends Presenter> extends BaseView<Presenter> {
        void onAddClick();

        void onBackPressed();

        void onItemClicked(Object object);

        void showErrorDialog(BaseException e);
    }

    interface Presenter extends BasePresenter {
    }
}
