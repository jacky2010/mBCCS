package com.viettel.mbccs.screen.searchproducts;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchProductsPresenter implements SearchProductsContract.Presenter {

    private Context context;
    private SearchProductsContract.ViewModel viewModel;
    public ObservableField<String> title;

    public SearchProductsPresenter(Context context, SearchProductsContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData() {
        try {
            title = new ObservableField<>(context.getString(R.string.search_products_title));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {
    }

    public void onCancel() {
        viewModel.onCancel();
    }
}
