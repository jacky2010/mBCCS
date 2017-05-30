package com.viettel.mbccs.screen.information;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ApDomain;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
import java.util.List;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public interface CreateUpdateInformationContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

        void onCancel();

        void onSearchSuccess(GetRegiterSubInfoResponse data);

        void onSearchError(BaseException error);

        void onRegisterNewPayment();

        void getDataSpinnerPassportSuccess(List<ApDomain> dataPassportType);

        void getDataSpinnerPassportError(BaseException error);

        void showDialogValidate();
    }
}
