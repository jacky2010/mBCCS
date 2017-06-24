package com.viettel.mbccs.screen.survey;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.SurveyType;
import com.viettel.mbccs.data.model.SurveyQuestion;
import com.viettel.mbccs.databinding.ItemSurveySelectAnswerBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 6/10/17.
 */

public class SurveyQuestionAdapter
        extends RecyclerView.Adapter<SurveyQuestionAdapter.SurveyQuestionViewHolder> {

    private Context mContext;
    private SurveyQuestion mSurveyQuestion;
    private int currentSelect = -1;

    public SurveyQuestionAdapter(Context context, SurveyQuestion surveyQuestion) {
        mContext = context;
        mSurveyQuestion = surveyQuestion;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public SurveyQuestion getSurveyQuestion() {
        return mSurveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        mSurveyQuestion = surveyQuestion;
    }

    @Override
    public SurveyQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SurveyQuestionViewHolder(
                (ItemSurveySelectAnswerBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_survey_select_answer, null, false));
    }

    @Override
    public void onBindViewHolder(SurveyQuestionViewHolder holder, int position) {
        holder.bind(mSurveyQuestion.getSurveyQuestionAnswers().get(position),
                currentSelect == position);
    }

    public List<SurveyQuestion.Answer> getSelectedAnwser() {
        List<SurveyQuestion.Answer> answers = new ArrayList<>();
        for (SurveyQuestion.Answer answer : mSurveyQuestion.getSurveyQuestionAnswers()) {
            if (answer.isSelect()) {
                answers.add(answer);
            }
        }
        return answers;
    }

    @Override
    public int getItemCount() {
        return mSurveyQuestion.getSurveyQuestionAnswers().size();
    }

    public class SurveyQuestionViewHolder extends RecyclerView.ViewHolder {

        ItemSurveySelectAnswerBinding mBinding;
        SurveyQuestion.Answer mAnswer;

        public SurveyQuestionViewHolder(ItemSurveySelectAnswerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.layoutQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentSelect = getAdapterPosition();
                    if (mSurveyQuestion.getType() == SurveyType.MULTI_CHOICE) {
                        mAnswer.setSelect(!mAnswer.isSelect());
                    }
                    notifyDataSetChanged();
                }
            });
        }

        public void bind(SurveyQuestion.Answer answer, boolean selected) {
            mAnswer = answer;
            if (mSurveyQuestion.getType() == SurveyType.SINGLE_CHOICE) {
                answer.setSelect(selected);
            }
            mBinding.setItem(mSurveyQuestion);
            mBinding.setAnswer(answer);
        }
    }
}
