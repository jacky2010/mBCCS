package com.viettel.mbccs.screen.information;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetAllSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubInfoResponse;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public interface CreateUpdateInformationContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

        void onCancel();

        void onSearchDKTTSuccess(GetRegisterSubInfoResponse data);

        void onSearchCNTTSuccess(GetAllSubInfoResponse data);

        void onSearchError(BaseException error);

        void onRegisterNewPayment(GetRegisterSubInfoResponse getRegisterSubInfoResponse);

//        void getDataSpinnerPassportSuccess(List<ApDomainByType> dataPassportType);

        void getDataSpinnerPassportError(BaseException error);

        void showDialogValidate();
    }
}
