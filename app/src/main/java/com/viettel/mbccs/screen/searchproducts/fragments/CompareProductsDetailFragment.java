package com.viettel.mbccs.screen.searchproducts.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.databinding.FragmentCompareProductsDetailBinding;
import com.viettel.mbccs.screen.sellanypay.dialogs.DialogConfirmSellAnyPayFragment;

/**
 * Created by minhnx on 5/20/17.
 */

public class CompareProductsDetailFragment extends BaseDataBindFragment<FragmentCompareProductsDetailBinding, CompareProductsDetailPresenter>
        implements CompareProductsDetailContract.ViewModel {


    private AppCompatActivity mActivity;

    public static CompareProductsDetailFragment newInstance() {
        return new CompareProductsDetailFragment();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new CompareProductsDetailPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_compare_products_detail;
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

    @Override
    public void goToDialogFragment(Bundle args) {
        getBaseActivity().goToDialogFragment(new DialogConfirmSellAnyPayFragment(), args);
    }
}
