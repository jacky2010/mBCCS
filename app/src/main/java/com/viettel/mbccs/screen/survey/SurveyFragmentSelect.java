package com.viettel.mbccs.screen.survey;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.model.SurveyQuestion;
import com.viettel.mbccs.databinding.FragmentSurveySelectBinding;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 6/10/17.
 */

public class SurveyFragmentSelect extends BaseFragment {

    private SurveyQuestion mSurveyQuestion;
    private SurveyQuestionAdapter mAdapter;
    private List<SurveyQuestion.Answer> mAnwsers = new ArrayList<>();
    private FragmentSurveySelectBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_survey_select, container, false);
        return mBinding.getRoot();
    }

    public static SurveyFragmentSelect newInstance(SurveyQuestion surveyQuestion) {
        SurveyFragmentSelect surveyFragment = new SurveyFragmentSelect();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.SURVEY_QUESTION, surveyQuestion);
        surveyFragment.setArguments(bundle);
        return surveyFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        mAnwsers.addAll(mSurveyQuestion.getSurveyQuestionAnswers());
        mAdapter = new SurveyQuestionAdapter(getActivity(), mSurveyQuestion);

        mBinding.setData(this);
    }



    public List<SurveyQuestion.Answer> getAnwsers() {
        return mAdapter.getSelectedAnwser();
    }

    public SurveyQuestion getSurveyQuestion() {
        return mSurveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        mSurveyQuestion = surveyQuestion;
    }

    public SurveyQuestionAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(SurveyQuestionAdapter adapter) {
        mAdapter = adapter;
    }
}
