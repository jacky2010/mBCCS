package com.viettel.mbccs.screen.information;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubInfoResponse;

import java.util.List;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public interface CreateUpdateInformationContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

        void onCancel();

        void onSearchSuccess(GetRegisterSubInfoResponse data);

        void onSearchError(BaseException error);

        void onRegisterNewPayment();

        void getDataSpinnerPassportSuccess(List<ApDomainByType> dataPassportType);

        void getDataSpinnerPassportError(BaseException error);

        void showDialogValidate();
    }
}
