package com.viettel.mbccs.screen.viewproduct.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KeyValueIcon;
import com.viettel.mbccs.databinding.ItemProductCommentBinding;

import java.util.List;

/**
 * Created by minhnx on 6/25/17.
 */

public class ProductCommentAdapter extends RecyclerView.Adapter<ProductCommentAdapter.ViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<KeyValueIcon> mComments;

    public ProductCommentAdapter(Context context, List<KeyValueIcon> comments) {
        mContext = context;
        mComments = comments;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<KeyValueIcon> getItems() {
        return mComments;
    }

    public void setItems(List<KeyValueIcon> items) {
        mComments = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                (ItemProductCommentBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_product_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mComments.get(position));
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemProductCommentBinding mBinding;
        KeyValueIcon mStockItem;

        public ViewHolder(final ItemProductCommentBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(KeyValueIcon item) {
            mStockItem = item;
            ItemProductCommentPresenter itemProductPresenter = new ItemProductCommentPresenter(mContext, item);
            mBinding.setItem(itemProductPresenter);
        }
    }
}
