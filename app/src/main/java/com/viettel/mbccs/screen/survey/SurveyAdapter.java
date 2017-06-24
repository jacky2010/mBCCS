package com.viettel.mbccs.screen.survey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.Survey;
import com.viettel.mbccs.databinding.ItemSurveyListBinding;
import com.viettel.mbccs.databinding.ItemSurveyNodataBinding;
import com.viettel.mbccs.widget.viewholderbinding.BaseRecyclerViewAdapterBinding;
import com.viettel.mbccs.widget.viewholderbinding.BaseViewHolderBinding;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 23/06/2017.
 */

public class SurveyAdapter
        extends BaseRecyclerViewAdapterBinding<SurveyAdapter.SurveyViewHolder, Survey> {

    private OnRecyclerItemListener<Survey> mSurveyOnRecyclerItemListener;

    public OnRecyclerItemListener<Survey> getSurveyOnRecyclerItemListener() {
        return mSurveyOnRecyclerItemListener;
    }

    public void setSurveyOnRecyclerItemListener(
            OnRecyclerItemListener<Survey> surveyOnRecyclerItemListener) {
        mSurveyOnRecyclerItemListener = surveyOnRecyclerItemListener;
    }

    public SurveyAdapter(Context context, List<Survey> list) {
        super(context, list);
    }

    @Override
    protected SurveyViewHolder getViewHolder(ViewGroup parent) {
        return new SurveyViewHolder(
                ItemSurveyListBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public BaseViewHolderBinding getNoDataViewHolder(ViewGroup parent) {
        return new SurveyNoData(
                ItemSurveyNodataBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    class SurveyNoData extends BaseViewHolderBinding<ItemSurveyNodataBinding, EmptyObject> {

        public SurveyNoData(ItemSurveyNodataBinding binding) {
            super(binding);
        }
    }

    class SurveyViewHolder extends BaseViewHolderBinding<ItemSurveyListBinding, Survey> {
        public SurveyViewHolder(final ItemSurveyListBinding binding) {
            super(binding);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSurveyOnRecyclerItemListener != null) {
                        mSurveyOnRecyclerItemListener.onItemClick(getAdapterPosition(),
                                mList.get(getAdapterPosition()));
                    }
                }
            });

            binding.btnProcess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.getRoot().performClick();
                }
            });
        }

        @Override
        public void bindData(Survey survey) {
            super.bindData(survey);
            mBinding.setPresenter(survey);
        }
    }
}
