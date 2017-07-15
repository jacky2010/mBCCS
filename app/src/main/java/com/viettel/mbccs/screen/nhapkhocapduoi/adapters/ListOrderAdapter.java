package com.viettel.mbccs.screen.nhapkhocapduoi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.StockTransType;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.WarehouseOrder;
import com.viettel.mbccs.databinding.ItemWarehouseOrderBinding;
import com.viettel.mbccs.utils.DateUtils;

import com.viettel.mbccs.widget.viewholderbinding.BaseRecyclerViewAdapterBinding;
import com.viettel.mbccs.widget.viewholderbinding.BaseViewHolderBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/20/2017.
 */

public class ListOrderAdapter
        extends BaseRecyclerViewAdapterBinding<ListOrderAdapter.OrderViewHolder, StockTrans> {

    private OnOrderClickListener mOnOrderClickListener;

    public ListOrderAdapter(Context context, List<StockTrans> list) {
        super(context, list);
    }

    public OnOrderClickListener getOnOrderClickListener() {
        return mOnOrderClickListener;
    }

    public void setOnOrderClickListener(OnOrderClickListener onOrderClickListener) {
        mOnOrderClickListener = onOrderClickListener;
    }

    @Override
    protected OrderViewHolder getViewHolder(ViewGroup parent) {
        return new OrderViewHolder(
                ItemWarehouseOrderBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    class OrderViewHolder extends BaseViewHolderBinding<ItemWarehouseOrderBinding, StockTrans> {

        public OrderViewHolder(ItemWarehouseOrderBinding binding) {
            super(binding);
        }

        @Override
        public void bindData(final StockTrans item) {
            super.bindData(item);
            mBinding.setTitle(itemView.getContext()
                    .getString(R.string.item_orders_change_code_name,
                            String.valueOf(item.getStockTransId()),
                            String.valueOf(item.getToOwnerId())));
            if (item.getActionType()== StockTransType.TRANS_EXPORT){
                mBinding.setChannelName(String.valueOf(item.getToOwnerType()));
            }else{
                mBinding.setChannelName(String.valueOf(item.getFromOwnerId()));
            }

            mBinding.setCreatedDate(DateUtils.convertStringToStringFormat(item.getCreateDatetime(),
                    DateUtils.DATE_FORMAT1));
            mBinding.setOnClicked(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnOrderClickListener != null) mOnOrderClickListener.onOrderClick(item);
                }
            });
            mBinding.setActionType(item.getActionType());
            mBinding.setAction(item.getActionName());
        }
    }

    public interface OnOrderClickListener {
        void onOrderClick(StockTrans item);
    }
}
