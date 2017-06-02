package com.viettel.mbccs.screen.assigntask.cskpp.update;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public interface TaskCSKPPUpdateContract {

    interface ViewModel extends BaseView<Presenter> {
        void onBackPressed();

        void onAccept();
    }

    interface Presenter extends BasePresenter {
    }
}
