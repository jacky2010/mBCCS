package com.viettel.mbccs.screen.assigntask.staffpicker;

import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;

/**
 * Created by Anh Vu Viet on 5/27/2017.
 */

public class StaffPickerContract {

    interface Presenter extends BaseSearchListViewContract.Presenter {

        void onTextChange(String s);
    }

    interface ViewModel extends BaseSearchListViewContract.ViewModel {

    }
}
