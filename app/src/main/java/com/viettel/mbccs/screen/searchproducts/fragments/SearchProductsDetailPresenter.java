package com.viettel.mbccs.screen.searchproducts.fragments;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.data.source.SellAnyPayRepository;
import com.viettel.mbccs.screen.searchproducts.adapters.SearchProductsDetailFragmentAdapter;

import java.text.DecimalFormat;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchProductsDetailPresenter implements SearchProductsDetailContract.Presenter {

    private DecimalFormat nf = new DecimalFormat("###");

    public ObservableField<SearchProductsDetailFragmentAdapter> fragmentAdapter;

    private Context context;
    private SearchProductsDetailContract.ViewModel viewModel;

    private SellAnyPayRepository anyPayRepository;

    public SearchProductsDetailPresenter(Context context, final SearchProductsDetailContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initListeners();
        initData();
    }

    private void initData() {
        try {
            fragmentAdapter = new ObservableField<>();

            anyPayRepository = SellAnyPayRepository.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        try {

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

    @Override
    public void setSearchProductsDetailFragmentAdapter(SearchProductsDetailFragmentAdapter adapter) {
        fragmentAdapter.set(adapter);
    }
}
