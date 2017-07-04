package com.viettel.mbccs.screen.connector.fragment.confirm;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.source.remote.response.BaseException;

/**
 * Created by HuyQuyet on 7/3/17.
 */

public class ConfirmConnectSubscriberContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {
        void setPresenter(Presenter presenter);

        void connectSubscriberSuccess();

        void connectSubscriberError(BaseException error);

        void onClose();
    }
}
