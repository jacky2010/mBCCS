package com.viettel.mbccs.screen.serialpicker.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.SerialBlock;
import com.viettel.mbccs.databinding.ItemSerialAdapterBinding;
import com.viettel.mbccs.utils.Common;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class SerialAdapter extends RecyclerView.Adapter<SerialAdapter.SerialViewHolder> {
    private Context mContext;
    private List<Integer> mSerials;
    private List<SerialBlock> mSerialBlocks = new ArrayList<>();
    private int currentSelect = -1;
    private SerialChooseListener mSerialChooseListener;

    public SerialAdapter(Context context, List<Integer> serials) {
        mContext = context;
        mSerials = serials;
        formatData();
    }

    @Override
    public SerialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SerialViewHolder(
                (ItemSerialAdapterBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_serial_adapter, parent, false));
    }

    public void clearSelectedPosition() {
        currentSelect = -1;
    }

    public void setSerialChooseListener(SerialChooseListener serialChooseListener) {
        mSerialChooseListener = serialChooseListener;
    }

    @Override
    public void onBindViewHolder(SerialViewHolder holder, int position) {
        holder.binData(mSerialBlocks.get(position), position == currentSelect);
    }

    private void formatData() {
        mSerialBlocks.clear();
        mSerialBlocks.addAll(Common.getSerialBlockBySerials(mSerials));
        notifyDataSetChanged();
    }

    public void removeSerial(List<Integer> serials) {
        mSerials.removeAll(serials);
        formatData();
    }

    public void refresh() {
        formatData();
    }

    @Override
    public int getItemCount() {
        return mSerialBlocks.size();
    }

    class SerialViewHolder extends RecyclerView.ViewHolder {

        ItemSerialAdapterBinding mBinding;

        public SerialViewHolder(ItemSerialAdapterBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.layoutSerial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentSelect = getAdapterPosition();
                    if (mSerialChooseListener != null) {
                        mSerialChooseListener.onSerialSelect(
                                mSerialBlocks.get(getAdapterPosition()));
                    }
                    notifyDataSetChanged();
                }
            });
        }

        public void binData(SerialBlock serialBlock, boolean isSelected) {
            ItemSerialPresenter itemSerialPresenter = new ItemSerialPresenter();
            itemSerialPresenter.setSerialBlock(serialBlock);
            itemSerialPresenter.setSelected(isSelected);
            mBinding.setItem(itemSerialPresenter);
        }
    }

    public interface SerialChooseListener {
        void onSerialSelect(SerialBlock serialBlock);
    }
}
