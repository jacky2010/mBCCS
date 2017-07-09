package com.viettel.mbccs.screen.assigntask.arising.create;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.source.remote.response.BaseException;

/**
 * Created by Anh Vu Viet on 5/23/2017.
 */

public interface CreateArisingTaskContract {

    interface ViewModel extends BaseView<Presenter> {

        void openStaffPicker();

        void onBackPressed();

        void showAssignTaskDialog();

        void showSuccessDialog();

        long getFromDate();

        long getToDate();

        StaffInfo getStaff();

        void showErrorDialog(BaseException e);
    }

    interface Presenter extends BasePresenter {

        void createTask();
    }
}
