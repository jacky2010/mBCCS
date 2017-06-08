package com.viettel.mbccs.screen.goodsconfirm.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.databinding.ItemFooterSerialConfirmBinding;
import com.viettel.mbccs.databinding.ItemSerialConfirmBinding;
import com.viettel.mbccs.utils.Common;
import java.util.List;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class SerialConfirmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_ROW = 0;
    public static final int TYPE_FOOTER = 1;

    private List<SerialBO> mSerialBlocks;

    public SerialConfirmAdapter(List<SerialBO> serialBlocks) {
        mSerialBlocks = serialBlocks;
    }

    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ROW) {
            return new SerialConfirmViewHolder((ItemSerialConfirmBinding) DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.item_serial_confirm, parent,
                    false));
        } else {
            return new FooterSeletedViewHolder(
                    (ItemFooterSerialConfirmBinding) DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_footer_serial_confirm, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SerialConfirmViewHolder) {
            ((SerialConfirmViewHolder) holder).bind(mSerialBlocks.get(position));
        }

        if (holder instanceof FooterSeletedViewHolder) {
            ((FooterSeletedViewHolder) holder).bindData();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mSerialBlocks.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_ROW;
    }

    @Override
    public int getItemCount() {
        return mSerialBlocks.size() + 1;
    }

    class FooterSeletedViewHolder extends RecyclerView.ViewHolder {

        ItemFooterSerialConfirmBinding mBinding;

        public FooterSeletedViewHolder(ItemFooterSerialConfirmBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData() {
            mBinding.setTotal(Common.getSerialCountByListSerialBlock(mSerialBlocks));
        }
    }

    class SerialConfirmViewHolder extends RecyclerView.ViewHolder {

        ItemSerialConfirmBinding mBinding;

        public SerialConfirmViewHolder(ItemSerialConfirmBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            if (getAdapterPosition() % 2 == 0) {
                mBinding.layoutSerial.setBackgroundResource(R.color.white);
            } else {
                mBinding.layoutSerial.setBackgroundResource(R.color.white_1);
            }
        }

        public void bind(SerialBO serialBlock) {
            mBinding.setSerial(serialBlock);
        }
    }
}
