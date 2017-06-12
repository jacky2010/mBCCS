package com.viettel.mbccs.screen.kppfeedback.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KPPFeedback;
import com.viettel.mbccs.databinding.ItemKppFeedbackBinding;

import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class KPPFeedbackListAdapter extends RecyclerView.Adapter<KPPFeedbackListAdapter.KPPFeedbackViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<KPPFeedback> mFeedbackItems;
    private OnItemClickListener listener;

    public KPPFeedbackListAdapter(Context context, List<KPPFeedback> items) {
        mContext = context;
        mFeedbackItems = items;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<KPPFeedback> getFeedbackItems() {
        return mFeedbackItems;
    }

    public void setFeedbackItems(List<KPPFeedback> items) {
        mFeedbackItems = items;
    }

    @Override
    public KPPFeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KPPFeedbackViewHolder(
                (ItemKppFeedbackBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_kpp_feedback, parent, false));
    }

    @Override
    public void onBindViewHolder(KPPFeedbackViewHolder holder, int position) {
        holder.bind(mFeedbackItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mFeedbackItems.size();
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

    class KPPFeedbackViewHolder extends RecyclerView.ViewHolder {

        ItemKppFeedbackBinding mBinding;
        KPPFeedback mFeedbackItem;

        public KPPFeedbackViewHolder(final ItemKppFeedbackBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.ivRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onClick(view, mFeedbackItem);
                }
            });

            mBinding.llLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onClick(view, mFeedbackItem);
                }
            });
        }

        public void bind(KPPFeedback item) {
            mFeedbackItem = item;
            ItemKPPFeedbackPresenter itemPresenter = new ItemKPPFeedbackPresenter(mContext, item);
            mBinding.setPresenter(itemPresenter);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onClick(View view, KPPFeedback item);
    }
}