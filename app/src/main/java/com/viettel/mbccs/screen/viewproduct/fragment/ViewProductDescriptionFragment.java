package com.viettel.mbccs.screen.viewproduct.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.databinding.FragmentViewProductDescriptionBinding;

import java.util.List;

/**
 * Created by minhnx on 5/20/17.
 */

public class ViewProductDescriptionFragment extends BaseDataBindFragment<FragmentViewProductDescriptionBinding, ViewProductDescriptionPresenter>
        implements ViewProductDescriptionContract.ViewModel {

    private AppCompatActivity mActivity;

    private String description;
    private List<KeyValue> features;
    private List<KeyValue> comments;

    public static ViewProductDescriptionFragment newInstance(String description, List<KeyValue> features, List<KeyValue> comments) {
        return new ViewProductDescriptionFragment(description, features, comments);
    }

    public ViewProductDescriptionFragment() {
    }

    @SuppressLint("ValidFragment")
    public ViewProductDescriptionFragment(String description, List<KeyValue> features, List<KeyValue> comments) {
        this.description = description;
        this.features = features;
        this.comments = comments;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new ViewProductDescriptionPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);
        mPresenter.showDetailTabs(description, features, comments);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_view_product_description;
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
