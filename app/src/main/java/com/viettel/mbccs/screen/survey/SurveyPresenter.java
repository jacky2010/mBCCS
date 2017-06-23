package com.viettel.mbccs.screen.survey;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.constance.SurveyType;
import com.viettel.mbccs.data.model.Survey;
import com.viettel.mbccs.data.model.SurveyQuestion;
import com.viettel.mbccs.data.source.SurveyKPPRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.SendSurveyKPPRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.SendSurveyKPPResponse;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

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
    private Survey mSurvey;
    private UserRepository mUserRepository;
    private SurveyKPPRepository mSurveyKPPRepository;
    private CompositeSubscription mCompositeSubscription;

    public SurveyPresenter(Context context, SurveyContract.ViewModel viewModel,
            FragmentManager fragmentManager, Survey survey) {
        mContext = context;
        mViewModel = viewModel;
        this.mFragmentManager = fragmentManager;
        mUserRepository = UserRepository.getInstance();
        mSurveyKPPRepository = SurveyKPPRepository.getInstance();
        mCompositeSubscription = new CompositeSubscription();
        mSurvey = survey;
        init();
        //  getSurvey();
    }

    private void init() {
        nextTitle = new ObservableField<>();
        isNoSurvey = new ObservableField<>();
        isNoSurvey.set(false);
        nextTitle.set(mContext.getString(R.string.common_label_continue));
        mSurveyFragmentAdapter = new SurveyFragmentAdapter(mFragmentManager, mContext, mFragments);
        mSurveyQuestions = mSurvey.getSurveyQuestions();
        addFragment();
    }

    private void sendSurvey() {
        mViewModel.showLoading();
        DataRequest<SendSurveyKPPRequest> dataRequest = new DataRequest<>();
        dataRequest.setApiCode(ApiCode.SendSurveyKPP);
        SendSurveyKPPRequest request = new SendSurveyKPPRequest();
        request.setLanguage(mUserRepository.getLanguageFromSharePrefs());
        request.setUserName(mUserRepository.getLoginUserName());
        SendSurveyKPPRequest.SurveyResponse surveyResponse =
                new SendSurveyKPPRequest.SurveyResponse();
        surveyResponse.setSurveyId(mSurvey.getId());
        List<SendSurveyKPPRequest.SurveyResponseAnswer> surveyResponseAnswers = new ArrayList<>();

        for (Fragment fragment : mFragments) {
            SendSurveyKPPRequest.SurveyResponseAnswer surveyResponseAnswer =
                    new SendSurveyKPPRequest.SurveyResponseAnswer();
            if (fragment instanceof SurveyFragmentSelect) {
                surveyResponseAnswer.setSurveyQuestionId(
                        ((SurveyFragmentSelect) fragment).getSurveyQuestion().getId());
                surveyResponseAnswer.setAnswer(
                        TextUtils.join(",", ((SurveyFragmentSelect) fragment).getAnwsers()));
            }
            if (fragment instanceof SurveyFragmentInput) {
                surveyResponseAnswer.setSurveyQuestionId(
                        ((SurveyFragmentInput) fragment).getSurveyQuestion().getId());
                surveyResponseAnswer.setAnswer(((SurveyFragmentInput) fragment).getAnswerInput());
            }
            surveyResponseAnswers.add(surveyResponseAnswer);
        }
        surveyResponse.setSurveyResponseAnswer(surveyResponseAnswers);
        request.setSurveyResponse(surveyResponse);
        dataRequest.setParameterApi(request);
        Subscription subscription = mSurveyKPPRepository.sendSurveyKPP(dataRequest)
                .subscribe(new MBCCSSubscribe<SendSurveyKPPResponse>() {
                    @Override
                    public void onSuccess(SendSurveyKPPResponse object) {

                        mViewModel.onSendSurveySuccess();
                    }

                    @Override
                    public void onError(BaseException error) {

                        DialogUtils.showDialog(mContext, error.getMessage());
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });
    }

    private void addFragment() {
        mFragments.clear();
        for (SurveyQuestion surveyQuestion : mSurveyQuestions) {
            if (surveyQuestion.getType() == SurveyType.SINGLE_CHOICE
                    || surveyQuestion.getType() == SurveyType.MULTI_CHOICE) {
                mFragments.add(SurveyFragmentSelect.newInstance(surveyQuestion));
            } else {
                mFragments.add(SurveyFragmentInput.newInstance(surveyQuestion));
            }
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
        Fragment fragment = mSurveyFragmentAdapter.getItem(currentPosition);

        if (fragment instanceof SurveyFragmentSelect) {
            SurveyQuestion surveyQuestion = ((SurveyFragmentSelect) fragment).getSurveyQuestion();
            List<SurveyQuestion.Answer> answers = ((SurveyFragmentSelect) fragment).getAnwsers();
            if (surveyQuestion.getIsRequired().equals("1") && answers.size() == 0) {
                DialogUtils.showDialog(mContext, R.string.survey_error_no_answer);
                return;
            }
        }

        if (fragment instanceof SurveyFragmentInput) {
            SurveyQuestion surveyQuestion = ((SurveyFragmentInput) fragment).getSurveyQuestion();
            String answerText = ((SurveyFragmentInput) fragment).getAnswerInput();
            if (surveyQuestion.getIsRequired().equals("1") && TextUtils.isEmpty(answerText)) {
                DialogUtils.showDialog(mContext, R.string.survey_error_no_answer);
                return;
            }
        }

        if (currentPosition == (mSurveyQuestions.size() - 1)) {
            sendSurvey();
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
