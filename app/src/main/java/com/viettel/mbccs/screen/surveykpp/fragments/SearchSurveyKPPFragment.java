package com.viettel.mbccs.screen.surveykpp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.SurveyItem;
import com.viettel.mbccs.databinding.FragmentSearchSurveyKppBinding;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by minhnx on 5/20/17.
 */

public class SearchSurveyKPPFragment extends BaseDataBindFragment<FragmentSearchSurveyKppBinding, SearchSurveyKPPPresenter>
        implements SearchSurveyKPPContract.ViewModel {

    private AppCompatActivity mActivity;

    public static SearchSurveyKPPFragment newInstance() {
        return new SearchSurveyKPPFragment();
    }

    @Override
    public void setPresenter(SearchSurveyKPPContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new SearchSurveyKPPPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_search_survey_kpp;
    }

    @Override
    protected void initView() {

    }

    private void initListeners() {

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
    public void onPrepareSurvey(SurveyItem item) {
        try {
            ProcessSurveyKPPFragment fragment = ProcessSurveyKPPFragment.newInstance();

            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.ITEM_LIST, GsonUtils.Object2String(item));

            fragment.setArguments(args);

            mActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_main, fragment)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
