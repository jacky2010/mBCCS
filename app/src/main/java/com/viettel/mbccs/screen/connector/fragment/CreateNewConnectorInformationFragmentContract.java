package com.viettel.mbccs.screen.connector.fragment;

import android.graphics.Bitmap;
import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.remote.request.ConnectSubscriberRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.widget.CustomSelectAddress;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public interface CreateNewConnectorInformationFragmentContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {
        void setPresenter(Presenter presenter);

        void loadDataSpinnerError(BaseException error);

        void onCancel();

        CustomSelectAddress.Address getAddress();
    }

    interface ViewFragment1 extends View {

        void onNext();

        Bitmap imageFront();

        Bitmap imageBackside();

        Bitmap imagePortrait();

        void loadDataSpinnerSuccess();

        String getBirthDate();

        String getDateCreatePassport();

        String getDateOutDatePassport();
    }

    interface ViewFragment2 extends View {

        void connectSubscriber(ConnectSubscriberRequest request, UserInfo userInfo);
    }
}
