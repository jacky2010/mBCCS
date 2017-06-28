package com.viettel.mbccs.screen.searchproducts.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.databinding.FragmentViewProductDetailBinding;
import com.viettel.mbccs.screen.common.picker.KeyValuePickerActivity;
import com.viettel.mbccs.screen.sellanypay.dialogs.DialogConfirmSellAnyPayFragment;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by minhnx on 5/20/17.
 */

public class ViewProductDetailFragment extends BaseDataBindFragment<FragmentViewProductDetailBinding, ViewProductDetailPresenter>
        implements ViewProductDetailContract.ViewModel {

    private AppCompatActivity mActivity;

    public static ViewProductDetailFragment newInstance() {
        return new ViewProductDetailFragment();
    }

    @Override
    public void setPresenter(ViewProductDetailContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new ViewProductDetailPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_view_product_detail;
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
