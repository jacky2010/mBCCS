package com.viettel.mbccs.screen.branches.btspicker;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.databinding.ItemBtsBinding;

import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class BTSPickerAdaper extends RecyclerView.Adapter<BTSPickerAdaper.BTSViewHolder> {
    private List<KeyValue> items;

    private OnBTSClickListener listener;

    public BTSPickerAdaper(List<KeyValue> items) {
        this.items = items;
    }

    @Override
    public BTSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BTSViewHolder((ItemBtsBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_bts, parent, false));
    }

    @Override
    public void onBindViewHolder(BTSViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public OnBTSClickListener getOnChannelListener() {
        return listener;
    }

    public void setOnBTSClickListener(OnBTSClickListener onChannelListener) {
        listener = onChannelListener;
    }

    class BTSViewHolder extends RecyclerView.ViewHolder {

        ItemBtsBinding mBinding;

        public BTSViewHolder(ItemBtsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(items.get(getAdapterPosition()));
                    }
                }
            });
        }

        public void bind(KeyValue item) {
            mBinding.setItem(item);
        }
    }

    public interface OnBTSClickListener {
        void onItemClick(KeyValue item);
    }
}
