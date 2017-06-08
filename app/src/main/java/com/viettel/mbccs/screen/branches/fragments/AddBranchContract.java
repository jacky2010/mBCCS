package com.viettel.mbccs.screen.branches.fragments;

import android.graphics.Bitmap;

import com.google.android.gms.location.places.Place;
import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.data.model.KeyValue;

import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class AddBranchContract {
    interface ViewModel extends BaseView<Presenter> {
        void onBranchAdded(BranchItem branchItem);
        void onBranchAddFailed();
        void onChooseManager(List<KeyValue> items);
        void onChooseBTS(List<KeyValue> items);
        void showError(String message);
        void onSelectImage(android.view.View v);
    }

    interface Presenter extends BasePresenter {
        void onGetManagerSuccess(KeyValue item);
        void onGetBTSSuccess(KeyValue item);
        void onChannelTypeChanged(int index);
        void onDocumentTypeChanged(int index);
        void setDocumentImage(Bitmap bitmap);
        void setContractImage(Bitmap bitmap);
        void onPlaceSelected(Place place);
    }
}
