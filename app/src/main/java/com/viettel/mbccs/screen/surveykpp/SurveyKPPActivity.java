package com.viettel.mbccs.screen.surveykpp;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivitySurveyKppBinding;
import com.viettel.mbccs.screen.surveykpp.fragments.SearchSurveyKPPFragment;

/**
 * Created by minhnx on 5/20/17.
 */

public class SurveyKPPActivity extends BaseDataBindActivity<ActivitySurveyKppBinding, SurveyKPPPresenter>
        implements SurveyKPPContract.ViewModel {

    @Override
    public void setPresenter(SurveyKPPContract.Presenter presenter) {

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
    protected int getIdLayout() {
        return R.layout.activity_survey_kpp;
    }

    @Override
    protected void initData() {
        mPresenter = new SurveyKPPPresenter(this, this);
        mBinding.setPresenter(mPresenter);

        changeToSearchTab();
    }

    @Override
    public void onCancel() {
        onBackPressed();
    }

    @Override
    public void changeToSearchTab() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, SearchSurveyKPPFragment.newInstance())
//                .addToBackStack(null)
                .commit();
    }
}
