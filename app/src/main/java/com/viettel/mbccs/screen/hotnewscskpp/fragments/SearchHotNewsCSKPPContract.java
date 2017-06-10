package com.viettel.mbccs.screen.hotnewscskpp.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.HotNewsCSKPPItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchHotNewsCSKPPContract {
    interface ViewModel extends BaseView<Presenter> {
        void showError(String message);
        void onSearchCompleted();
        void openHotNewsDetail(HotNewsCSKPPItem item);
    }

    interface Presenter extends BasePresenter {
        void searchNews();
    }
}
