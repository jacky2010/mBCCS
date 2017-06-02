package com.viettel.mbccs.screen.information.fragment;

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

        void onSelectImage(android.view.View v);

        Province getProvince();

        District getDistrict();

        Precinct getPrecinct();

        String getAddress();

        String getBirthDate();

        void setBirthDate(String birthDate);

        void registerCustomerInfoError(BaseException error);

        void registerCustomerInfoSuccess(String result);

        void getDataSpinnerPassportSuccess(List<ApDomain> type);

        void getDataSpinnerPassportError(BaseException error);
    }
}
