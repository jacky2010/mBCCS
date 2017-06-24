package com.viettel.mbccs.screen.survey;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by eo_cuong on 6/10/17.
 */

public interface SurveyContract {

    interface Presenter extends BasePresenter {
        void onPageSelected(int position);
    }

    interface ViewModel extends BaseView<Presenter>{

        void onCancel();

        void nextQuestion(int i);

        void onSendSurveySuccess();
    }
}
