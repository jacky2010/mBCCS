package com.viettel.mbccs.base.createorder;

/**
 * Created by Anh Vu Viet on 6/26/2017.
 */

public interface BaseCreateOrderContract {

    interface ViewModel extends BaseCreateOrderSuccessContract.ViewModel {

        void onReject();

        void onCreate();
    }

    interface Presenter extends BaseCreateOrderSuccessContract.Presenter {

    }
}
