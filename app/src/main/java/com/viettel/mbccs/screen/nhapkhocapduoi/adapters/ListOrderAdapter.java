package com.viettel.mbccs.screen.nhapkhocapduoi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.WarehouseOrder;
import com.viettel.mbccs.databinding.ItemWarehouseOrderBinding;
import com.viettel.mbccs.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/20/2017.
 */

public class ListOrderAdapter extends RecyclerView.Adapter<ListOrderAdapter.OrderViewHolder> {

    private List<StockTrans> mList = new ArrayList<>();

    private Context mContext;

    private OnOrderClickListener mOnOrderClickListener;

    public ListOrderAdapter(Context context, List<StockTrans> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(
                ItemWarehouseOrderBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public OnOrderClickListener getOnOrderClickListener() {
        return mOnOrderClickListener;
    }

    public void setOnOrderClickListener(OnOrderClickListener onOrderClickListener) {
        mOnOrderClickListener = onOrderClickListener;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        private ItemWarehouseOrderBinding mBinding;

        public OrderViewHolder(ItemWarehouseOrderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final StockTrans item) {
            mBinding.setTitle(itemView.getContext()
                    .getString(R.string.item_orders_change_code_name,
                            String.valueOf(item.getStockTransId()),
                            String.valueOf(item.getToOwnerId())));
            mBinding.setChannelName(String.valueOf(item.getToOwnerType()));
            mBinding.setCreatedDate(DateUtils.convertStringToStringFormat(item.getCreateDateTime(),
                    DateUtils.DATE_TIME_FORMAT));
            mBinding.setOnClicked(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnOrderClickListener != null) mOnOrderClickListener.onOrderClick(item);
                }
            });
        }
    }

    public interface OnOrderClickListener {
        void onOrderClick(StockTrans item);
    }
}
