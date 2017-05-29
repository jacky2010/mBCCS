package com.viettel.mbccs.screen.branches.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.BranchItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchBranchContract {
    interface ViewModel extends BaseView<Presenter> {
        void onDocumentFound(String documentId);
        void onDocumentNotFound(String documentId);
        void onAddNewDocument();
        void showError(String message);
        void onPrepareUpdateBranch(BranchItem item);
    }

    interface Presenter extends BasePresenter {
        void searchDocument();
    }
}
