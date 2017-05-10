package com.viettel.mbccs.screen.login;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by eo_cuong on 5/10/17.
 */

public interface LoginContract {

    interface ViewModel extends BaseView<Presenter> {

        void onLoginSuccess();
    }

    interface Presenter extends BasePresenter {

        void login(String username, String password);
    }
}
