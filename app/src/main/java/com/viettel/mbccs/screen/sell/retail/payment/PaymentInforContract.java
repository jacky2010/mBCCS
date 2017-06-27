package com.viettel.mbccs.screen.sell.retail.payment;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;

/**
 * Created by eo_cuong on 5/16/17.
 */

public interface PaymentInforContract {

    interface Presenter extends BasePresenter {

    }

    interface ViewModel extends BaseView<Presenter> {

        void onBack();

        void goToSaveTransConfirm(DataRequest<GetInfoSaleTranRequest> request, SaleTrans saleTrans);

        void openBankplus();

        void openWellet();
    }
}
