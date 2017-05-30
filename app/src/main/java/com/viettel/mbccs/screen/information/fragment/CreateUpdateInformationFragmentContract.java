package com.viettel.mbccs.screen.information.fragment;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public interface CreateUpdateInformationFragmentContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

        void onCancel();

        void onSelectImage(android.view.View v);
    }
}
