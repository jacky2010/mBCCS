package com.viettel.mbccs.screen.connector.fragment;

import android.graphics.Bitmap;
import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.source.remote.response.BaseException;

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

        void onNext();

        Bitmap imageFront();

        Bitmap imageBackside();

        Bitmap imagePortrait();

        void loadDataSpinnerError(BaseException error);

        void loadDataSpinnerSuccess();

        void validateNameCustomerError();
    }

    interface ViewFragment2 extends View {

        void onEnter();
    }
}
