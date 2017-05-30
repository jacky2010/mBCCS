package com.viettel.mbccs.screen.sell.channel.payment;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.screen.sell.retail.payment.PaymentInforContract;

/**
 * Created by eo_cuong on 5/20/17.
 */

public interface PaymentInforChannelContract {
    interface Presenter extends BasePresenter {

    }

    interface ViewModel extends BaseView<PaymentInforContract.Presenter> {

        void onBack();

        void goToSaveTransConfirm(DataRequest<GetInfoSaleTranRequest> request, SaleTrans saleTrans,ChannelInfo channelInfo);
    }
}
