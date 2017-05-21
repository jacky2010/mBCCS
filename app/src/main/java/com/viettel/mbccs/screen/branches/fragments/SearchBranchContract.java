package com.viettel.mbccs.screen.branches.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchBranchContract {
    interface ViewModel extends BaseView<Presenter> {
        void onDocumentFound(String documentId);
        void onDocumentNotFound(String documentId);
        void onAddNewDocument();
        void showError(String message);
    }

    interface Presenter extends BasePresenter {
        void searchDocument();
    }
}
