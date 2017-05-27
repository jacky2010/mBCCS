package com.viettel.mbccs.screen.config;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by HuyQuyet on 5/19/17.
 */

public class ConfigContract {
    interface Presenter extends BasePresenter{
    }

    interface View extends BaseView<Presenter> {

        void onFinish();

        void selectCountry();

        void selectLanguage();

        void changeLanguage(String language);
    }
}
