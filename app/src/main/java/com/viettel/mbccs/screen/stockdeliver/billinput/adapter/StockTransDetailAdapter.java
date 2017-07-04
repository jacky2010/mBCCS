package com.viettel.mbccs.screen.stockdeliver.billinput.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.databinding.ItemBaseInputBillDetailBinding;
import com.viettel.mbccs.widget.viewholderbinding.BaseRecyclerViewAdapterBinding;
import com.viettel.mbccs.widget.viewholderbinding.BaseViewHolderBinding;
import java.util.List;

public class StockTransDetailAdapter extends
        BaseRecyclerViewAdapterBinding<StockTransDetailAdapter.StockTransDetailViewHolder, StockTransDetail> {

    private OnSerialDetailAdapterListener mOnStockTransAdapterListerner;
    private String action;

    public void setOnSerialDetailAdapterListener(
            OnSerialDetailAdapterListener onSerialDetailAdapterListener) {
        mOnStockTransAdapterListerner = onSerialDetailAdapterListener;
    }

    @Override
    protected StockTransDetailViewHolder getViewHolder(ViewGroup parent) {
        return new StockTransDetailViewHolder(
                ItemBaseInputBillDetailBinding.inflate(LayoutInflater.from(mContext), parent,
                        false));
    }

    public StockTransDetailAdapter(Context context, List<StockTransDetail> list) {
        super(context, list);
    }

    public StockTransDetailAdapter(Context context, List<StockTransDetail> list, String action) {
        super(context, list);
        this.action = action;
    }

    class StockTransDetailViewHolder
            extends BaseViewHolderBinding<ItemBaseInputBillDetailBinding, StockTransDetail> {

        public StockTransDetailViewHolder(ItemBaseInputBillDetailBinding binding) {
            super(binding);
            mBinding.buttonChooseSerial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnStockTransAdapterListerner != null) {
                        mOnStockTransAdapterListerner.onViewSerialDetail(
                                mList.get(getAdapterPosition()).getStockSerial());
                    }
                }
            });
            mBinding.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnStockTransAdapterListerner != null) {
                        mOnStockTransAdapterListerner.onItemClick(getAdapterPosition(),
                                mList.get(getAdapterPosition()).getStockSerial());
                    }
                }
            });
        }

        @Override
        public void bindData(StockTransDetail stockTransDetail) {
            super.bindData(stockTransDetail);
            ItemStockTransDetailPresenter itemStockTransDetailPresenter =
                    new ItemStockTransDetailPresenter(mContext, stockTransDetail);
            if (!TextUtils.isEmpty(action)) {
                itemStockTransDetailPresenter.setAction(action);
            }
            mBinding.setPresenter(itemStockTransDetailPresenter);
        }
    }

    public interface OnSerialDetailAdapterListener extends OnRecyclerItemListener<StockSerial> {
        void onViewSerialDetail(StockSerial stockSerial);
    }
}
