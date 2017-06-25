package com.viettel.mbccs.screen.searchproducts.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.screen.searchproducts.adapters.SearchProductsDetailFragmentAdapter;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchProductsDetailContract {

    interface ViewModel extends BaseView<Presenter> {
        void showError(String message);
    }

    interface Presenter extends BasePresenter {
        void setSearchProductsDetailFragmentAdapter(SearchProductsDetailFragmentAdapter adapter);
    }
}
