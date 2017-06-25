package com.viettel.mbccs.screen.searchproducts;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchProductsContract {
    interface ViewModel extends BaseView<Presenter> {
        void onCancel();
        void changeToSearchTab();
    }

    interface Presenter extends BasePresenter {
    }
}
