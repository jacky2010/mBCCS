package com.viettel.mbccs.screen.hotnewscskpp.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.HotNewsCSKPPItem;
import com.viettel.mbccs.databinding.ItemHotNewsCsKppBinding;

import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class HotNewsCSKPPListAdapter extends RecyclerView.Adapter<HotNewsCSKPPListAdapter.HotNewsCSKPPViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<HotNewsCSKPPItem> mNewsItems;
    private OnItemClickListener listener;

    public HotNewsCSKPPListAdapter(Context context, List<HotNewsCSKPPItem> newsItems) {
        mContext = context;
        mNewsItems = newsItems;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<HotNewsCSKPPItem> getHotNewsItems() {
        return mNewsItems;
    }

    public void setHotNewsItems(List<HotNewsCSKPPItem> newsItems) {
        mNewsItems = newsItems;
    }

    @Override
    public HotNewsCSKPPViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotNewsCSKPPViewHolder(
                (ItemHotNewsCsKppBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_hot_news_cs_kpp, parent, false));
    }

    @Override
    public void onBindViewHolder(HotNewsCSKPPViewHolder holder, int position) {
        holder.bind(mNewsItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
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

    class HotNewsCSKPPViewHolder extends RecyclerView.ViewHolder {

        ItemHotNewsCsKppBinding mBinding;
        HotNewsCSKPPItem mNewsItem;

        public HotNewsCSKPPViewHolder(final ItemHotNewsCsKppBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.ivRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onClick(view, mNewsItem);
                }
            });

            mBinding.llLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onClick(view, mNewsItem);
                }
            });
        }

        public void bind(HotNewsCSKPPItem item) {
            mNewsItem = item;
            ItemHotNewsCSKPPPresenter itemPresenter = new ItemHotNewsCSKPPPresenter(mContext, item);
            mBinding.setPresenter(itemPresenter);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onClick(View view, HotNewsCSKPPItem item);
    }
}