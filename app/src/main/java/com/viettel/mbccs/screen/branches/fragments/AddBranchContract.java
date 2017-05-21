package com.viettel.mbccs.screen.branches.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.BranchItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class AddBranchContract {
    interface ViewModel extends BaseView<Presenter> {
        void onBranchAdded(BranchItem branchItem);
        void onBranchAddFailed();
    }

    interface Presenter extends BasePresenter {
    }
}
