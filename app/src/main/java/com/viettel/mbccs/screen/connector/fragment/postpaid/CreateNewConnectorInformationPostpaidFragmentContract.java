package com.viettel.mbccs.screen.connector.fragment.postpaid;

import android.graphics.Bitmap;
import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.remote.request.ConnectSubscriberRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.widget.model.AddressApp;
import java.util.List;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public interface CreateNewConnectorInformationPostpaidFragmentContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {
        void setPresenter(Presenter presenter);

        void setViewModel(CreateNewConnectorInformationPostpaidViewModel viewModel);

        void loadDataSpinnerError(BaseException error);

        void onCancel();

        AddressApp getAddress();

        boolean getFragmentVisible();

    }
    interface ViewFragment1 extends View {

        Bitmap imageFront();

        Bitmap imageBackside();

        Bitmap imagePortrait();

        String getBirthDate();

        String getDateCreatePassport();

        String getBirthDateRegister();

        String getDateCreateRegister();

        void loadDataSpinnerDNError(BaseException error);
    }

    interface ViewFragment2 extends View {
        void loadDataSpinnerHanMucError(BaseException error);
    }

    interface ViewFragment3 extends View {

        String getNgayDangKy();

        String getNgayHieuLuc();

        String getNgayHetHan();

        String getNgayThu();

        AddressApp getAddressApp();

        void connectSubscriber(ConnectSubscriberRequest request, UserInfo info, List<String> dataImage);

        void loadDataSpinner3TenNganHang(BaseException error);
    }
}
