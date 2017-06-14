package com.viettel.mbccs.screen.common.picker;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.databinding.ItemKeyValueBinding;

import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class KeyValuePickerAdaper extends RecyclerView.Adapter<KeyValuePickerAdaper.KeyValueViewHolder> {
    private List<KeyValue> items;

    private OnItemClickListener listener;

    public KeyValuePickerAdaper(List<KeyValue> sellPrograms) {
        items = sellPrograms;
    }

    @Override
    public KeyValueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KeyValueViewHolder((ItemKeyValueBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_key_value, parent, false));
    }

    @Override
    public void onBindViewHolder(KeyValueViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        listener = itemClickListener;
    }

    class KeyValueViewHolder extends RecyclerView.ViewHolder {

        ItemKeyValueBinding mBinding;

        public KeyValueViewHolder(ItemKeyValueBinding binding) {
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

    public interface OnItemClickListener {
        void onItemClick(KeyValue item);
    }
}
