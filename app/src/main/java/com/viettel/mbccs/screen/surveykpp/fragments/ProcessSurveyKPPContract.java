package com.viettel.mbccs.screen.surveykpp.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by minhnx on 5/19/17.
 */

public class ProcessSurveyKPPContract {
    interface ViewModel extends BaseView<Presenter> {
        void showError(String message);
        void onBackPressed();
    }

    interface Presenter extends BasePresenter {
        void onSurveyCompleted(String answersJson);
        void onSurveyTerminated();
    }
}
