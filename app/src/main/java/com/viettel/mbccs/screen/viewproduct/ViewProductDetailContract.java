package com.viettel.mbccs.screen.viewproduct;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ModelSale;

/**
 * Created by minhnx on 5/19/17.
 */

public class ViewProductDetailContract {
    interface ViewModel extends BaseView<Presenter> {
        void onCancel();
    }

    interface Presenter extends BasePresenter {
        void displayProductDetail(ModelSale item);
        void onImagePageChanged(int index);
    }
}
