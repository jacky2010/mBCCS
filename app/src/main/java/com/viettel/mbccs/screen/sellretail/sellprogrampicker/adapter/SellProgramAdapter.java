package com.viettel.mbccs.screen.sellretail.sellprogrampicker.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.databinding.ItemSaleProgramBinding;
import java.util.List;

/**
 * Created by eo_cuong on 5/16/17.
 */

public class SellProgramAdapter
        extends RecyclerView.Adapter<SellProgramAdapter.SellProgramViewHolder> {

    private List<SaleProgram> mSellPrograms;

    private OnSellProgramListener mOnSellProgramListener;

    public SellProgramAdapter(List<SaleProgram> sellPrograms) {
        mSellPrograms = sellPrograms;
    }

    @Override
    public SellProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SellProgramViewHolder((ItemSaleProgramBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_sale_program, parent,
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

        ItemSaleProgramBinding mBinding;

        public SellProgramViewHolder(ItemSaleProgramBinding binding) {
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

        public void bind(SaleProgram sellProgram) {
            mBinding.setItem(sellProgram);
        }
    }

    public interface OnSellProgramListener {
        void onItemClick(SaleProgram sellProgram);
    }
}
