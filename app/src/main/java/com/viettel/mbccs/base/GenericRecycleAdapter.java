package com.viettel.mbccs.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.viettel.mbccs.R;
import com.viettel.mbccs.callback.OnListenerItemRecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericRecycleAdapter<T, K extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter {

    public enum RecyclerViewType {
        VIEW_TYPE_ITEM(0),
        VIEW_TYPE_LOADING(1),
        VIEW_TYPE_CUSTOM(2);

        int mValue;

        RecyclerViewType(int value) {
            mValue = value;
        }

        public int value() {
            return mValue;
        }
    }

    protected List<T> mList;
    protected Context mContext;
    protected boolean mIsMoreData;
    protected int mSelectedItem;

    public GenericRecycleAdapter(List<T> mList, Context context) {
        this.mList = mList;
        this.mContext = context;
    }

    public abstract int getLayout(int viewType);

    protected int getLayoutLoadMore() {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerViewType.VIEW_TYPE_LOADING.value()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutLoadMore(), parent, false);
            return new LoadingViewHolder(view);
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(getLayout(viewType), parent, false);
        return getHolderViewHolder(v,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadingViewHolder) {
            Glide.with(mContext)
                    .load(R.mipmap.ic_launcher)
                    .into(((LoadingViewHolder) holder).mLoadMore);
        } else {
            onSet(mList.get(position), (K) holder, position);
        }
    }

    protected abstract RecyclerView.ViewHolder getHolderViewHolder(View v, int viewType);

    protected abstract void onItem(T item, int position);

    protected abstract void onSet(T item, K holder, int position);

    public void setSelectedItem(int mSelectedItem) {
        this.mSelectedItem = mSelectedItem;
    }

    public void setDataSource(List<T> item) {
        mList = item;
        notifyDataSetChanged();
    }

    public void addDataSource(List<T> item) {
        if (mList == null) {
            mList = new ArrayList<>();
        }

        if (item != null) {
            mList.addAll(item);
            notifyDataSetChanged();
        }
    }

    public boolean isMoreData() {
        return mIsMoreData;
    }

    public void setMoreData(boolean isMoreData) {
        this.mIsMoreData = isMoreData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (isMoreData()) {
            return mList == null ? 1 : mList.size() + 1;
        }

        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == (getItemCount() - 1) && isMoreData())
                ? RecyclerViewType.VIEW_TYPE_LOADING.value() :
                RecyclerViewType.VIEW_TYPE_ITEM.value();
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        ImageView mLoadMore;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            mLoadMore = (ImageView) itemView.findViewById(R.id.image_loading);
        }
    }

    protected OnListenerItemRecyclerView<T> mOnClickItemRecycleView;

    public void setOnClickItemRecycleView(OnListenerItemRecyclerView mOnClickItemRecycleView) {
        this.mOnClickItemRecycleView = mOnClickItemRecycleView;
    }
}