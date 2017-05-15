package com.viettel.mbccs.screen.sellretail.sellprogrampicker.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.SellProgram;
import com.viettel.mbccs.databinding.ItemTextSearchBinding;
import java.util.List;

/**
 * Created by eo_cuong on 5/16/17.
 */

public class SellProgramAdapter
        extends RecyclerView.Adapter<SellProgramAdapter.SellProgramViewHolder> {

    private List<SellProgram> mSellPrograms;

    private OnSellProgramListener mOnSellProgramListener;

    public SellProgramAdapter(List<SellProgram> sellPrograms) {
        mSellPrograms = sellPrograms;
    }

    @Override
    public SellProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SellProgramViewHolder((ItemTextSearchBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_text_search, parent,
                false));
    }

    @Override
    public void onBindViewHolder(SellProgramViewHolder holder, int position) {
        holder.bind(mSellPrograms.get(position));
    }

    @Override
    public int getItemCount() {
        return mSellPrograms.size();
    }

    public void setOnSellProgramListener(OnSellProgramListener onSellProgramListener) {
        mOnSellProgramListener = onSellProgramListener;
    }

    class SellProgramViewHolder extends RecyclerView.ViewHolder {

        ItemTextSearchBinding mBinding;

        public SellProgramViewHolder(ItemTextSearchBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnSellProgramListener != null) {
                        mOnSellProgramListener.onItemClick(mSellPrograms.get(getAdapterPosition()));
                    }
                }
            });
        }

        public void bind(SellProgram sellProgram) {
            mBinding.setItem(sellProgram);
        }
    }

    public interface OnSellProgramListener {
        void onItemClick(SellProgram sellProgram);
    }
}
