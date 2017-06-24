package com.viettel.mbccs.screen.survey;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.Survey;
import com.viettel.mbccs.databinding.ActivitySurveyBinding;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by eo_cuong on 6/10/17.
 */

public class SurveyActivity extends BaseDataBindActivity<ActivitySurveyBinding, SurveyPresenter>
        implements SurveyContract.ViewModel {

    private Survey mSurvey;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_survey;
    }

    @Override
    protected void initData() {

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        mSurvey = bundle.getParcelable(Constants.BundleConstant.SURVEY);
        if (mSurvey == null) {
            return;
        }

        mPresenter = new SurveyPresenter(this, this, getSupportFragmentManager(), mSurvey);
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
        DialogUtils.showDialog(SurveyActivity.this, null,
                getString(R.string.survey_kpp_msg_exit_confirm),
                getString(R.string.common_label_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }, getString(R.string.common_label_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
    }

    @Override
    public void nextQuestion(int i) {
        mBinding.viewpagerQuestion.setCurrentItem(i);
    }

    @Override
    public void onSendSurveySuccess() {
        Dialog dialog = DialogUtils.showDialog(SurveyActivity.this,
                getString(R.string.survey_kpp_msg_survey_complete),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setResult(RESULT_OK);
                        finish();
                    }
                });

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }
}
