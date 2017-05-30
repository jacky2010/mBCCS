package com.viettel.mbccs.screen.assigntask.arising.create;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by Anh Vu Viet on 5/23/2017.
 */

public interface CreatingArisingTaskContract {

    interface ViewModel extends BaseView<Presenter> {

        void openStaffPicker();

        void onBackPressed();

        void assignTask();
    }

    interface Presenter extends BasePresenter {

    }
}
