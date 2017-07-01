package com.viettel.mbccs.screen.searchproducts.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.ItemProductColorBinding;

import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class AvailableColorsListAdapter extends RecyclerView.Adapter<AvailableColorsListAdapter.ViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<Integer> mItems;

    public AvailableColorsListAdapter(Context context, List<Integer> items) {
        mContext = context;
        mItems = items;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<Integer> getItems() {
        return mItems;
    }

    public void setItems(List<Integer> items) {
        mItems = items;
    }

    public int getItem(int index) {
        return mItems.get(index);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                (ItemProductColorBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_product_color, parent, false));
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
        if (!b) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();
                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemProductColorBinding mBinding;
        int item;

        public ViewHolder(final ItemProductColorBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(int item) {
            this.item = item;
            ItemProductColorPresenter itemPresenter = new ItemProductColorPresenter(mContext, item);
            mBinding.setItem(itemPresenter);
        }
    }
}