package com.viettel.mbccs.screen.connector.fragment;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public interface CreateNewConnectorInformationFragmentContract {

    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {

        void onCancel();
    }

    interface ViewFragment1 extends View {

    }

    interface ViewFragment2 extends View {

    }
}
