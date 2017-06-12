package com.viettel.mbccs.screen.login;

import android.support.annotation.Nullable;
import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by eo_cuong on 5/10/17.
 */

public interface LoginContract {

    interface ViewModel extends BaseView<Presenter> {

        void onLoginSuccess();

        void onForgotPassword(String username);

        void showError(int type, @Nullable String message);
    }

    interface Presenter extends BasePresenter {
    }
}
