package com.viettel.mbccs.screen.branches.staffpicker;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.databinding.ItemStaffBinding;

import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class StaffPickerAdaper extends RecyclerView.Adapter<StaffPickerAdaper.StaffViewHolder> {
    private List<KeyValue> items;

    private OnStaffClickListener listener;

    public StaffPickerAdaper(List<KeyValue> sellPrograms) {
        items = sellPrograms;
    }

    @Override
    public StaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StaffViewHolder((ItemStaffBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_staff, parent, false));
    }

    @Override
    public void onBindViewHolder(StaffViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public OnStaffClickListener getOnChannelListener() {
        return listener;
    }

    public void setOnStaffClickListener(OnStaffClickListener onChannelListener) {
        listener = onChannelListener;
    }

    class StaffViewHolder extends RecyclerView.ViewHolder {

        ItemStaffBinding mBinding;

        public StaffViewHolder(ItemStaffBinding binding) {
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

    public interface OnStaffClickListener {
        void onItemClick(KeyValue item);
    }
}
