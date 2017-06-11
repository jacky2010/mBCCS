package com.viettel.mbccs.screen.survey;

import android.support.v4.view.ViewPager;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivitySurveyBinding;

/**
 * Created by eo_cuong on 6/10/17.
 */

public class SurveyActivity extends BaseDataBindActivity<ActivitySurveyBinding, SurveyPresenter>
        implements SurveyContract.ViewModel {
    @Override
    protected int getIdLayout() {
        return R.layout.activity_survey;
    }

    @Override
    protected void initData() {
        mPresenter = new SurveyPresenter(this, this, getSupportFragmentManager());
        mBinding.setPresenter(mPresenter);

        mBinding.viewpagerQuestion.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPresenter.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setPresenter(SurveyContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onCancel() {
        finish();
    }

    @Override
    public void nextQuestion(int i) {
        mBinding.viewpagerQuestion.setCurrentItem(i);
    }
}
