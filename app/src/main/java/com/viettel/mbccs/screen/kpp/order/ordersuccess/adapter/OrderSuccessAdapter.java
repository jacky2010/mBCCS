package com.viettel.mbccs.screen.kpp.order.ordersuccess.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.databinding.ItemFooterKppOrderSuccessBinding;
import com.viettel.mbccs.databinding.ItemHeaderKppOrderSuccessBinding;
import com.viettel.mbccs.databinding.ItemKppOrderSuccessBinding;
import java.util.List;

public class OrderSuccessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ROW = 1;
    public static final int TYPE_FOOTER = 2;
    private Context mContext;
    private List<StockTotal> mList;

    public OrderSuccessAdapter(Context context, List<StockTotal> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:

                return new OrderSuccessHeaderViewHolder(
                        (ItemHeaderKppOrderSuccessBinding) DataBindingUtil.inflate(
                                LayoutInflater.from(mContext),
                                R.layout.item_header_kpp_order_success, parent, false));
            case TYPE_FOOTER:
                return new OrderSuccessFooterViewHolder(
                        (ItemFooterKppOrderSuccessBinding) DataBindingUtil.inflate(
                                LayoutInflater.from(mContext),
                                R.layout.item_footer_kpp_order_success, parent, false));
            default:
                return new OrderSuccessViewHolder(
                        (ItemKppOrderSuccessBinding) DataBindingUtil.inflate(
                                LayoutInflater.from(mContext), R.layout.item_kpp_order_success,
                                parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof OrderSuccessViewHolder) {
            ((OrderSuccessViewHolder) holder).bind(mList.get(position - 1), position - 1);
        }
        if (holder instanceof OrderSuccessFooterViewHolder) {
            ((OrderSuccessFooterViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        if (position == mList.size() + 1) {
            return TYPE_FOOTER;
        }

        return TYPE_ROW;
    }

    @Override
    public int getItemCount() {
        if (mList.size() == 0) {
            return 0;
        }

        return mList.size() + 2;
    }

    class OrderSuccessFooterViewHolder extends RecyclerView.ViewHolder {

        String data;

        ItemFooterKppOrderSuccessBinding mBinding;

        public OrderSuccessFooterViewHolder(ItemFooterKppOrderSuccessBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind() {
            data = "Tổng tiền : 3460000 VND";
            mBinding.setData(data);
        }
    }

    class OrderSuccessHeaderViewHolder extends RecyclerView.ViewHolder {

        public OrderSuccessHeaderViewHolder(ItemHeaderKppOrderSuccessBinding binding) {
            super(binding.getRoot());
        }
    }

    class OrderSuccessViewHolder extends RecyclerView.ViewHolder {
        ItemKppOrderSuccessBinding mBinding;

        public OrderSuccessViewHolder(ItemKppOrderSuccessBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(StockTotal stockTotal, int position) {
            mBinding.setItem(stockTotal);
            if (position % 2 == 0) {
                mBinding.layoutItem.setBackgroundColor(
                        mContext.getResources().getColor(R.color.white));
            } else {
                mBinding.layoutItem.setBackgroundColor(
                        mContext.getResources().getColor(R.color.default_bg_disabled));
            }
        }
    }
}
