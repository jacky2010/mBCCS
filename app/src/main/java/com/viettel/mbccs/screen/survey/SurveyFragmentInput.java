package com.viettel.mbccs.screen.survey;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.SurveyQuestion;
import com.viettel.mbccs.databinding.FragmentSurveyInputBinding;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by FRAMGIA\hoang.van.cuong on 23/06/2017.
 */

public class SurveyFragmentInput extends BaseDataBindFragment<FragmentSurveyInputBinding,EmptyObject> {
    private SurveyQuestion mSurveyQuestion;
    public ObservableField<String> textAnswer;
    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mSurveyQuestion = bundle.getParcelable(Constants.BundleConstant.SURVEY_QUESTION);
        if (mSurveyQuestion == null) {
            return;
        }
        if (mSurveyQuestion.getSurveyQuestionAnswers() == null) {
            return;
        }
        textAnswer = new ObservableField<>();
        mBinding.setData(this);
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_survey_input;
    }

    @Override
    protected void initView() {

    }

    public static SurveyFragmentInput newInstance(SurveyQuestion surveyQuestion) {
        SurveyFragmentInput surveyFragment = new SurveyFragmentInput();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.SURVEY_QUESTION, surveyQuestion);
        surveyFragment.setArguments(bundle);
        return surveyFragment;
    }

    public String getAnswerInput() {
        return textAnswer.get();
    }

    public SurveyQuestion getSurveyQuestion() {
        return mSurveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        mSurveyQuestion = surveyQuestion;
    }
}
