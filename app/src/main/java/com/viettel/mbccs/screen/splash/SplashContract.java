package com.viettel.mbccs.screen.splash;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by HuyQuyet on 6/1/17.
 */

public interface SplashContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {
        void gotoLogin();

        void gotoMain();
    }
}
