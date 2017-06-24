package com.viettel.mbccs.screen.survey;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.Survey;

/**
 * Created by FRAMGIA\hoang.van.cuong on 23/06/2017.
 */

public interface SurveyListContract {

    interface Presenter extends BasePresenter {

        void reloadData();
    }

    interface ViewModel extends BaseView<Presenter> {
        void onSurveyClick(Survey survey);

        void close();
    }
}
