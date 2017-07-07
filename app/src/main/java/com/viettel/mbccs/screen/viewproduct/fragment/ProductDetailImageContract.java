package com.viettel.mbccs.screen.viewproduct.fragment;

import android.os.Bundle;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.KeyValue;

/**
 * Created by minhnx on 5/19/17.
 */

public class ProductDetailImageContract {

    interface ViewModel extends BaseView<Presenter> {
        void showError(String message);

        void goToDialogFragment(Bundle args);
    }

    interface Presenter extends BasePresenter {
        void showImage(KeyValue item);
    }
}
