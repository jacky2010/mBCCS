package com.viettel.mbccs.screen.viewproduct.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.databinding.FragmentProductDetailImageBinding;

/**
 * Created by minhnx on 5/20/17.
 */

public class ProductDetailImageFragment extends BaseDataBindFragment<FragmentProductDetailImageBinding, ProductDetailImagePresenter>
        implements ProductDetailImageContract.ViewModel {

    private AppCompatActivity mActivity;

    private KeyValue mImage;

    public static ProductDetailImageFragment newInstance(KeyValue image) {
        return new ProductDetailImageFragment(image);
    }

    public ProductDetailImageFragment() {
    }

    @SuppressLint("ValidFragment")
    public ProductDetailImageFragment(KeyValue image) {
        this.mImage = image;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new ProductDetailImagePresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);
        mPresenter.showImage(mImage);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_product_detail_image;
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
//        getBaseActivity().goToDialogFragment(new DialogConfirmSellAnyPayFragment(), args);
    }
}
