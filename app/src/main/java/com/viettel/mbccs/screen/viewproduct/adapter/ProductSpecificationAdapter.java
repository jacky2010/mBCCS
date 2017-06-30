package com.viettel.mbccs.screen.viewproduct.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KeyValueIcon;
import com.viettel.mbccs.databinding.ItemProductSpecificationBinding;

import java.util.List;

/**
 * Created by minhnx on 6/25/17.
 */

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<KeyValueIcon> mStockItems;

    public ProductSpecificationAdapter(Context context, List<KeyValueIcon> stockItems) {
        mContext = context;
        mStockItems = stockItems;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<KeyValueIcon> getItems() {
        return mStockItems;
    }

    public void setItems(List<KeyValueIcon> items) {
        mStockItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                (ItemProductSpecificationBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_product_specification, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mStockItems.get(position));
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }

    @Override
    public int getItemCount() {
        return mStockItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemProductSpecificationBinding mBinding;
        KeyValueIcon mStockItem;

        public ViewHolder(final ItemProductSpecificationBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(KeyValueIcon item) {
            mStockItem = item;
            ItemProductSpecificationPresenter itemProductPresenter = new ItemProductSpecificationPresenter(mContext, item);
            mBinding.setItem(itemProductPresenter);
        }
    }
}