package com.viettel.mbccs.screen.assigntask.cskpp.create;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StaffInfo;

/**
 * Created by Anh Vu Viet on 5/23/2017.
 */

public interface CreatingCSKPPTaskContract {

    interface ViewModel extends BaseView<Presenter> {

        void openStaffPicker();

        void onBackPressed();

        void showAssignTaskDialog();

        void showSuccessDialog();

        long getFromDate();

        long getToDate();

        StaffInfo getStaff();
    }

    interface Presenter extends BasePresenter {

        void createTask();
    }
}
