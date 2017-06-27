package com.viettel.mbccs.screen.information.fragment;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.source.remote.response.BaseException;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public interface CreateUpdateInformationFragmentContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

        void onCancel();

        Area getAreaProvince();

        Area getAreaDistrict();

        Area getAreaPrecinct();

        String getAddress();

        String getBirthDate();

        Bitmap imageFront();

        Bitmap imageBackside();

        Bitmap imagePortrait();

        void registerCustomerInfoError(BaseException error);

        void registerUpdateCustomerInfoSuccess(@Nullable String result, boolean isRegister);

        void getDataSpinnerError(BaseException error);

        void getOTPError(BaseException error);

        void checkOTPError(BaseException error);

        void isOTPEmpty();

        void selectNoticeChargeError();

        void customerError();

        void IsdnImsiError();

        void updateAllSubInfoError(BaseException error);

        void isSendImage();
    }
}
