package com.viettel.mbccs.screen.surveykpp.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.SurveyItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchSurveyKPPContract {
    interface ViewModel extends BaseView<Presenter> {
        void showError(String message);
        void onPrepareSurvey(SurveyItem item);
    }

    interface Presenter extends BasePresenter {
        void searchSurveys();
    }
}
