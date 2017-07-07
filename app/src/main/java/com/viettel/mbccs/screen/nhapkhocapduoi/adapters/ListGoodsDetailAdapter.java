package com.viettel.mbccs.screen.nhapkhocapduoi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.databinding.ItemNhapKhoCapDuoiMatHangBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\vu.viet.anh on 02/06/2017.
 */

public class ListGoodsDetailAdapter
        extends RecyclerView.Adapter<ListGoodsDetailAdapter.ViewHolder> {

    private List<StockTransDetail> mList = new ArrayList<>();

    private Context mContext;

    private OnViewSerialClickListener mOnViewSerialClickListener;

    public ListGoodsDetailAdapter(Context context, List<StockTransDetail> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ListGoodsDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListGoodsDetailAdapter.ViewHolder(
                ItemNhapKhoCapDuoiMatHangBinding.inflate(LayoutInflater.from(mContext), parent,
                        false));
    }

    @Override
    public void onBindViewHolder(ListGoodsDetailAdapter.ViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public OnViewSerialClickListener getOnViewSerialClickListener() {
        return mOnViewSerialClickListener;
    }

    public void setOnViewSerialClickListener(OnViewSerialClickListener onViewSerialClickListener) {
        mOnViewSerialClickListener = onViewSerialClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNhapKhoCapDuoiMatHangBinding mBinding;

        public ViewHolder(ItemNhapKhoCapDuoiMatHangBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final StockTransDetail item) {
            mBinding.setItem(item);
            mBinding.text3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnViewSerialClickListener != null) {
                        mOnViewSerialClickListener.onViewSerialClickListener(item);
                    }
                }
            });
        }
    }

    public interface OnViewSerialClickListener {
        void onViewSerialClickListener(StockTransDetail item);
    }
}
