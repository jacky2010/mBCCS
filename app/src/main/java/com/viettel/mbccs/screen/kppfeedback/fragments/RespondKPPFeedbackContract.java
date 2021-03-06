package com.viettel.mbccs.screen.kppfeedback.fragments;

import android.os.Bundle;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.KPPFeedback;

/**
 * Created by minhnx on 5/19/17.
 */

public class RespondKPPFeedbackContract {
    interface ViewModel extends BaseView<Presenter> {
        void showError(String message);
        void onBackPressed();
        void goToSuccessDialog(Bundle args);
    }

    interface Presenter extends BasePresenter {
        void fillFeedbackContent(KPPFeedback item);
    }
}
