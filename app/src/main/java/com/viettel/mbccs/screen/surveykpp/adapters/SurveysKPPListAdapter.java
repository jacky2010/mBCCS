package com.viettel.mbccs.screen.surveykpp.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.SurveyItem;
import com.viettel.mbccs.databinding.ItemSurveyKppBinding;

import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class SurveysKPPListAdapter extends RecyclerView.Adapter<SurveysKPPListAdapter.SurveyViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<SurveyItem> mSurveyItems;
    private OnItemClickListener listener;

    public SurveysKPPListAdapter(Context context, List<SurveyItem> surveyItems) {
        mContext = context;
        mSurveyItems = surveyItems;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<SurveyItem> getSurveyItems() {
        return mSurveyItems;
    }

    public void setSurveyItems(List<SurveyItem> branchItems) {
        mSurveyItems = branchItems;
    }

    @Override
    public SurveyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SurveyViewHolder(
                (ItemSurveyKppBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_survey_kpp, parent, false));
    }

    @Override
    public void onBindViewHolder(SurveyViewHolder holder, int position) {
        holder.bind(mSurveyItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mSurveyItems.size();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (!b) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();
                }
            });
        }
    }

    class SurveyViewHolder extends RecyclerView.ViewHolder {

        ItemSurveyKppBinding mBinding;
        SurveyItem mSurveyItem;

        public SurveyViewHolder(final ItemSurveyKppBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.btnProcess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onClick(view, mSurveyItem);
                }
            });
        }

        public void bind(SurveyItem item) {
            mSurveyItem = item;
            ItemSurveyKPPPresenter itemSurveyPresenter = new ItemSurveyKPPPresenter(mContext, item);
            mBinding.setPresenter(itemSurveyPresenter);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onClick(View view, SurveyItem item);
    }
}