package com.viettel.mbccs.screen.searchproducts.fragments;

import android.os.Bundle;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.KeyValue;

import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class ListProductsDetailContract {

    interface ViewModel extends BaseView<Presenter> {
        void showError(String message);

        void goToDialogFragment(Bundle args);

        void onChooseManufacturer(List<KeyValue> items);

        void onChooseScreenSize(List<KeyValue> items);

        void onChooseCamera(List<KeyValue> items);

        void onChoosePriceRange(List<KeyValue> items);

        void onChooseModel(List<KeyValue> items);

        void onChooseFeature(List<KeyValue> items);

        void changeToDetailScreen(Bundle args);
    }

    interface Presenter extends BasePresenter {
        void basicSearch();

        void advancedSearch();

        void onGetManufacturerSuccess(KeyValue item);

        void onGetScreenSizeSuccess(KeyValue item);

        void onGetCameraSuccess(KeyValue item);

        void onGetPriceRangeSuccess(KeyValue item);

        void onGetModelSuccess(KeyValue item);

        void onGetFeatureSuccess(KeyValue item);
    }
}
