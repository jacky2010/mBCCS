package com.viettel.mbccs.screen.changesim.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ChangeSimItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class UpdateSimContract {
    interface ViewModel extends BaseView<Presenter> {
        void onChangeSimSuccessful(ChangeSimItem item);
        void onChangeSimFailed();
        void showError(String message);
        void goToDialogFragment(Bundle args);
        void onSelectImage(View view);
    }

    interface Presenter extends BasePresenter {
        void changeSim();
        void setImage1(Bitmap bitmap);
        void setImage2(Bitmap bitmap);
        void setImage3(Bitmap bitmap);
    }
}
