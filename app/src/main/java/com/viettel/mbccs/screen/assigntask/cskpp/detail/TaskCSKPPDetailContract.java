package com.viettel.mbccs.screen.assigntask.cskpp.detail;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public interface TaskCSKPPDetailContract {

    interface ViewModel extends BaseView<Presenter> {
        void onBackPressed();

        void onReject();

        void onAccept();

        void onUpdate();
    }

    interface Presenter extends BasePresenter {
    }
}
