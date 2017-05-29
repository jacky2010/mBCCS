package com.viettel.mbccs.screen.branches.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.databinding.ItemBranchBinding;

import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class BranchesListAdapter extends RecyclerView.Adapter<BranchesListAdapter.BranchViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<BranchItem> mBranchItems;
    private OnItemClickListener listener;

    public BranchesListAdapter(Context context, List<BranchItem> branchItems) {
        mContext = context;
        mBranchItems = branchItems;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<BranchItem> getBranchItems() {
        return mBranchItems;
    }

    public void setBranchItems(List<BranchItem> branchItems) {
        mBranchItems = branchItems;
    }

    @Override
    public BranchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BranchViewHolder(
                (ItemBranchBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_branch, parent, false));
    }

    @Override
    public void onBindViewHolder(BranchViewHolder holder, int position) {
        holder.bind(mBranchItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mBranchItems.size();
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

    class BranchViewHolder extends RecyclerView.ViewHolder {

        ItemBranchBinding mBinding;
        BranchItem mBranchItem;

        public BranchViewHolder(final ItemBranchBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onClick(view, mBranchItem);
                }
            });
        }

        public void bind(BranchItem item) {
            mBranchItem = item;
            ItemBranchPresenter itemBranchPresenter = new ItemBranchPresenter(mContext, item);
            mBinding.setPresenter(itemBranchPresenter);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onClick(View view, BranchItem item);
    }
}