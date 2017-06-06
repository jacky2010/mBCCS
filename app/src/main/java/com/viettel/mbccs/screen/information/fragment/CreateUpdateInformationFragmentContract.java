package com.viettel.mbccs.screen.information.fragment;

import android.graphics.Bitmap;
import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ApDomain;
import com.viettel.mbccs.data.model.District;
import com.viettel.mbccs.data.model.Precinct;
import com.viettel.mbccs.data.model.Province;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import java.util.List;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public interface CreateUpdateInformationFragmentContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

        void onCancel();

        Province getProvince();

        District getDistrict();

        Precinct getPrecinct();

        String getAddress();

        String getBirthDate();

        Bitmap imageFront();

        Bitmap imageBackside();

        Bitmap imagePortrait();

        void setBirthDate(String birthDate);

        void registerCustomerInfoError(BaseException error);

        void registerUpdateCustomerInfoSuccess(String result, boolean isRegister);

        void getDataSpinnerPassportSuccess(List<ApDomain> type);

        void getDataHTTTSuccess(List<ApDomain> type);

        void getDataSpinnerError(BaseException error);

        void getOTPError(BaseException error);

        void checkOTPError(BaseException error);

        void isOTPEmpty();

        void selectNoticeChargeError();

        void customerError();

        void IsdnImsiError();

        void updateAllSubInfoError(BaseException error);
    }
}
