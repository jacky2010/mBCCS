package com.viettel.mbccs.screen.survey;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.SurveyType;
import com.viettel.mbccs.data.model.SurveyQuestion;
import com.viettel.mbccs.utils.DialogUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 6/10/17.
 */

public class SurveyPresenter implements SurveyContract.Presenter {

    private Context mContext;
    private SurveyContract.ViewModel mViewModel;
    private SurveyFragmentAdapter mSurveyFragmentAdapter;
    private List<SurveyQuestion> mSurveyQuestions = new ArrayList<>();
    public ObservableField<Boolean> isNoSurvey;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFragmentManager;
    public ObservableField<String> nextTitle;
    private int currentPosition = 0;

    public SurveyPresenter(Context context, SurveyContract.ViewModel viewModel,
            FragmentManager fragmentManager) {
        mContext = context;
        mViewModel = viewModel;
        this.mFragmentManager = fragmentManager;
        init();
        getSurvey();
    }

    private void init() {
        nextTitle = new ObservableField<>();
        isNoSurvey = new ObservableField<>();
        isNoSurvey.set(false);
        nextTitle.set(mContext.getString(R.string.common_label_continue));
        mSurveyFragmentAdapter = new SurveyFragmentAdapter(mFragmentManager, mContext, mFragments);
    }

    private void getSurvey() {
        //TODO
        //fake

        SurveyQuestion surveyQuestion1 = new SurveyQuestion();
        surveyQuestion1.setType(SurveyType.SINGLE_CHOICE);
        surveyQuestion1.setContent("Cau hoi 1");
        SurveyQuestion.Answer answer = new SurveyQuestion.Answer();
        answer.setContent("anwser");
        answer.setCode(1);

        SurveyQuestion.Answer answer1 = new SurveyQuestion.Answer();
        answer1.setContent("anwser1");
        answer1.setCode(1);

        SurveyQuestion.Answer answer2 = new SurveyQuestion.Answer();
        answer2.setContent("anwser2");
        answer2.setCode(1);

        List<SurveyQuestion.Answer> answers = new ArrayList<>();
        answers.add(answer);
        answers.add(answer1);
        answers.add(answer2);

        surveyQuestion1.setAnswers(answers);
        mSurveyQuestions.add(surveyQuestion1);

        //

        SurveyQuestion surveyQuestion2 = new SurveyQuestion();
        surveyQuestion2.setType(SurveyType.MULTI_CHOICE);
        surveyQuestion2.setContent("Cau hoi 2");
        SurveyQuestion.Answer answer21 = new SurveyQuestion.Answer();
        answer21.setContent("anwser");
        answer21.setCode(1);

        SurveyQuestion.Answer answer22 = new SurveyQuestion.Answer();
        answer22.setContent("anwser1");
        answer22.setCode(1);

        SurveyQuestion.Answer answer23 = new SurveyQuestion.Answer();
        answer23.setContent("anwser2");
        answer23.setCode(1);

        List<SurveyQuestion.Answer> answers2 = new ArrayList<>();
        answers2.add(answer21);
        answers2.add(answer22);
        answers2.add(answer23);

        surveyQuestion2.setAnswers(answers2);
        mSurveyQuestions.add(surveyQuestion2);

        //

        SurveyQuestion surveyQuestion3 = new SurveyQuestion();
        surveyQuestion3.setType(SurveyType.SINGLE_CHOICE);
        surveyQuestion3.setContent("Cau hoi 3");
        SurveyQuestion.Answer answer31 = new SurveyQuestion.Answer();
        answer31.setContent("anwser");
        answer31.setCode(1);

        SurveyQuestion.Answer answer32 = new SurveyQuestion.Answer();
        answer32.setContent("anwser1");
        answer32.setCode(1);

        SurveyQuestion.Answer answer33 = new SurveyQuestion.Answer();
        answer33.setContent("anwser2");
        answer33.setCode(1);

        List<SurveyQuestion.Answer> answers3 = new ArrayList<>();
        answers3.add(answer31);
        answers3.add(answer32);
        answers3.add(answer33);

        surveyQuestion3.setAnswers(answers3);
        mSurveyQuestions.add(surveyQuestion3);

        isNoSurvey.set(false);
        addFragment();
    }

    private void addFragment() {
        mFragments.clear();
        for (SurveyQuestion surveyQuestion : mSurveyQuestions) {
            mFragments.add(SurveyFragment.newInstance(surveyQuestion));
        }
        mSurveyFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public SurveyFragmentAdapter getSurveyFragmentAdapter() {
        return mSurveyFragmentAdapter;
    }

    public void onCancel() {
        mViewModel.onCancel();
    }

    public void onNext() {
        SurveyFragment fragment = (SurveyFragment) mSurveyFragmentAdapter.getItem(currentPosition);
        List<SurveyQuestion.Answer> answers = fragment.getAnwsers();
        if (answers.size() == 0) {
            DialogUtils.showDialogError(mContext, R.string.survey_error_no_answer);
            return;
        }
        if (currentPosition == (mSurveyQuestions.size() - 1)) {
            //submit
            return;
        }
        mViewModel.nextQuestion(currentPosition + 1);
    }

    public void setSurveyFragmentAdapter(SurveyFragmentAdapter surveyFragmentAdapter) {
        mSurveyFragmentAdapter = surveyFragmentAdapter;
    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
        if (position == (mSurveyQuestions.size() - 1)) {
            nextTitle.set(mContext.getString(R.string.common_label_complete));
        }
    }
}
