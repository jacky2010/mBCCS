package com.viettel.mbccs.screen.searchproducts.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.CompareProducts;
import com.viettel.mbccs.databinding.ItemProductFeatureBinding;

import java.util.List;

/**
 * Created by minhnx on 6/25/17.
 */

public class ProductFeaturesAdapter extends RecyclerView.Adapter<ProductFeaturesAdapter.ViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<CompareProducts> mItems;

    public ProductFeaturesAdapter(Context context, List<CompareProducts> items) {
        mContext = context;
        mItems = items;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<CompareProducts> getItems() {
        return mItems;
    }

    public void setItems(List<CompareProducts> items) {
        mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                (ItemProductFeatureBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_product_feature, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemProductFeatureBinding mBinding;
        CompareProducts mItem;

        public ViewHolder(final ItemProductFeatureBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(CompareProducts item) {
            mItem = item;
            ItemProductFeaturePresenter itemProductPresenter = new ItemProductFeaturePresenter(mContext, item);
            mBinding.setItem(itemProductPresenter);
        }
    }
}
