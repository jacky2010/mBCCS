package com.viettel.mbccs.screen.searchproducts.fragments;

import android.os.Bundle;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by minhnx on 5/19/17.
 */

public class ViewProductDetailContract {

    interface ViewModel extends BaseView<Presenter> {
        void showError(String message);

        void goToDialogFragment(Bundle args);
    }

    interface Presenter extends BasePresenter {
        void displayDetail();
    }
}
