package com.viettel.mbccs.screen.survey;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.Survey;
import com.viettel.mbccs.databinding.ActivitySurveyKppListBinding;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by FRAMGIA\hoang.van.cuong on 23/06/2017.
 */

public class SurveyListActivity
        extends BaseDataBindActivity<ActivitySurveyKppListBinding, SurveyListPresenter>
        implements SurveyListContract.ViewModel {

    public static final int REQUEST_SURVEY = 123;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_survey_kpp_list;
    }

    @Override
    protected void initData() {
        mPresenter = new SurveyListPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(SurveyListContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void onSurveyClick(Survey survey) {
        Intent intent = new Intent(SurveyListActivity.this, SurveyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.SURVEY, survey);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_SURVEY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SURVEY && resultCode == RESULT_OK) {
            mPresenter.reloadData();
        }
    }

    @Override
    public void close() {
        finish();
    }
}
