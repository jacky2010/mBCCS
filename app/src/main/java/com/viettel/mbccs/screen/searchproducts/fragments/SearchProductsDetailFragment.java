package com.viettel.mbccs.screen.searchproducts.fragments;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.databinding.FragmentSearchProductsDetailBinding;
import com.viettel.mbccs.screen.searchproducts.adapters.SearchProductsDetailFragmentAdapter;

import java.util.Arrays;

/**
 * Created by minhnx on 5/20/17.
 */

public class SearchProductsDetailFragment extends BaseDataBindFragment<FragmentSearchProductsDetailBinding, SearchProductsDetailPresenter>
        implements SearchProductsDetailContract.ViewModel {

    private AppCompatActivity mActivity;
    private SearchProductsDetailFragmentAdapter fragmentAdapter;

    public static SearchProductsDetailFragment newInstance() {
        return new SearchProductsDetailFragment();
    }

    @Override
    public void setPresenter(SearchProductsDetailContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new SearchProductsDetailPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();

        fragmentAdapter = new SearchProductsDetailFragmentAdapter(getChildFragmentManager());
        mPresenter.setSearchProductsDetailFragmentAdapter(fragmentAdapter);
        mBinding.tabLayout.setupWithViewPager(mBinding.vpPager);

        fragmentAdapter.setData(Arrays.asList(getResources().getStringArray(R.array.search_products_detail_title_tabs)));
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_search_products_detail;
    }

    @Override
    protected void initView() {
        try {
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }
}
