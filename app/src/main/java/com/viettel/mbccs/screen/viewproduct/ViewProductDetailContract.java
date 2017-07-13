package com.viettel.mbccs.screen.viewproduct;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.SearchProduct;

/**
 * Created by minhnx on 7/13/17.
 */

public class ViewProductDetailContract {
    interface ViewModel extends BaseView<Presenter> {
        void onCancel();
    }

    interface Presenter extends BasePresenter {
        void displayProductDetail(SearchProduct item);
        void onImagePageChanged(int index);
    }
}
