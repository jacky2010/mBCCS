package com.viettel.mbccs.screen.changesim.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ChangeSimItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchChangeSimContract {
    interface ViewModel extends BaseView<Presenter> {
        void onSimFound(String isdn, String documentType, String documentId);
        void onSimNotFound(String isdn, String documentType, String documentId);
        void onPrepareChangeSim(ChangeSimItem item);
        void showError(String message);
    }

    interface Presenter extends BasePresenter {
        void searchSim();
        void onDocumentTypeChanged(int index);
    }
}
