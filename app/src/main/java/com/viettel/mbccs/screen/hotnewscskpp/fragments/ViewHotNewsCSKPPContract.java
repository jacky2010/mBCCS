package com.viettel.mbccs.screen.hotnewscskpp.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.HotNewsCSKPPItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class ViewHotNewsCSKPPContract {
    interface ViewModel extends BaseView<Presenter> {
        void showError(String message);
        void onBackPressed();
        void showNewsContent(String content);
    }

    interface Presenter extends BasePresenter {
        void onNewsContentLoaded(HotNewsCSKPPItem item);
    }
}
