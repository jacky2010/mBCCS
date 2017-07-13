package com.viettel.mbccs.screen.viewproduct.fragment;

import android.os.Bundle;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.KeyValue;

import java.util.List;

/**
 * Created by minhnx on 7/13/17.
 */

public class ViewProductDescriptionContract {

    interface ViewModel extends BaseView<Presenter> {
        void showError(String message);

        void goToDialogFragment(Bundle args);
    }

    interface Presenter extends BasePresenter {
        void showDetailTabs(String description, List<KeyValue> features, List<KeyValue> comments);
    }
}
