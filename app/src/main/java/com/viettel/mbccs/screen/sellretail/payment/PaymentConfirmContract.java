package com.viettel.mbccs.screen.sellretail.payment;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by FRAMGIA\hoang.van.cuong on 17/05/2017.
 */

public class PaymentConfirmContract {

    interface Presenter extends BasePresenter {

    }

    interface ViewModel extends BaseView<Presenter> {

        void onClose();
    }
}
