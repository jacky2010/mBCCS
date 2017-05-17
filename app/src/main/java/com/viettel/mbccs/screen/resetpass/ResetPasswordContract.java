package com.viettel.mbccs.screen.resetpass;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by FRAMGIA\bui.dinh.viet on 16/05/2017.
 */

public interface ResetPasswordContract {
    interface ViewModel extends BaseView<Presenter> {

        void changePasswordSuccess();

        void onBackClick();
    }

    interface Presenter extends BasePresenter {

        void sendCodeClick();

        void changePassword(String password);
    }
}
