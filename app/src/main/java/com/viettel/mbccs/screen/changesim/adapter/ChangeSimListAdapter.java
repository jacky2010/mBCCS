package com.viettel.mbccs.screen.changesim.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.databinding.ItemChangeSimBinding;

import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class ChangeSimListAdapter extends RecyclerView.Adapter<ChangeSimListAdapter.ChangeSimViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<ChangeSimItem> mChangeSimItems;

    private OnItemClickListener listener;

    public ChangeSimListAdapter(Context context, List<ChangeSimItem>items) {
        mContext = context;
        mChangeSimItems = items;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<ChangeSimItem> getBranchItems() {
        return mChangeSimItems;
    }

    public void setBranchItems(List<ChangeSimItem> items) {
        mChangeSimItems = items;
    }

    @Override
    public ChangeSimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChangeSimViewHolder(
                (ItemChangeSimBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_change_sim, parent, false));
    }

    @Override
    public void onBindViewHolder(ChangeSimViewHolder holder, int position) {
        holder.bind(mChangeSimItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mChangeSimItems.size();
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

    class ChangeSimViewHolder extends RecyclerView.ViewHolder {

        ItemChangeSimBinding mBinding;
        ChangeSimItem mChangeSimItem;

        public ChangeSimViewHolder(final ItemChangeSimBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onClick(view, mChangeSimItem);
                }
            });
        }

        public void bind(ChangeSimItem item) {
            mChangeSimItem = item;
            ItemChangeSimPresenter itemChangeSimPresenter = new ItemChangeSimPresenter(mContext, item);
            mBinding.setPresenter(itemChangeSimPresenter);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onClick(View view, ChangeSimItem item);
    }
}