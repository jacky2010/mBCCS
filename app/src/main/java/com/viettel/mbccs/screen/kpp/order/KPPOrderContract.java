package com.viettel.mbccs.screen.kpp.order;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by eo_cuong on 5/21/17.
 */

public interface KPPOrderContract {

    interface Presenter extends BasePresenter {

        void onChannelSelectedChagne(int position);

        void refreshData();
    }

    interface ViewModel extends BaseView<Presenter> {

        void gotoAddNewOrder();

        long getFromDate();

        long getToDate();

        void collapseForm();

        void findFinished();
    }
}
