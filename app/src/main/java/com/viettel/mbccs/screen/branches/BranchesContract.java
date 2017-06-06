package com.viettel.mbccs.screen.branches;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by minhnx on 5/19/17.
 */

public class BranchesContract {
    interface ViewModel extends BaseView<Presenter> {
        void onCancel();
    }

    interface Presenter extends BasePresenter {
    }
}
