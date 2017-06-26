package com.viettel.mbccs.screen.warehousecommon.cmdprepareexportdetail;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.databinding.ItemStockTransDetailBinding;
import com.viettel.mbccs.widget.viewholderbinding.BaseRecyclerViewAdapterBinding;
import com.viettel.mbccs.widget.viewholderbinding.BaseViewHolderBinding;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 21/06/2017.
 */

public class StockTransDetailAdapter extends
        BaseRecyclerViewAdapterBinding<StockTransDetailAdapter.StockTransDetailViewHolder, StockTransDetail> {

    private OnStockTransDetailAdapterListener mOnStockTransAdapterListerner;
    private String action;

    public void setOnStockTransAdapterListerner(
            OnStockTransDetailAdapterListener onStockTransAdapterListerner) {
        mOnStockTransAdapterListerner = onStockTransAdapterListerner;
    }

    @Override
    protected StockTransDetailViewHolder getViewHolder(ViewGroup parent) {
        return new StockTransDetailViewHolder(
                ItemStockTransDetailBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    public StockTransDetailAdapter(Context context, List<StockTransDetail> list) {
        super(context, list);
    }

    public StockTransDetailAdapter(Context context, List<StockTransDetail> list, String action) {
        super(context, list);
        this.action = action;
    }



    class StockTransDetailViewHolder
            extends BaseViewHolderBinding<ItemStockTransDetailBinding, StockTransDetail> {

        public StockTransDetailViewHolder(ItemStockTransDetailBinding binding) {
            super(binding);
            mBinding.buttonChooseSerial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnStockTransAdapterListerner != null) {
                        mOnStockTransAdapterListerner.onSerialPickerClick(getAdapterPosition(),
                                mList.get(getAdapterPosition()));
                    }
                }
            });
            mBinding.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnStockTransAdapterListerner != null) {
                        mOnStockTransAdapterListerner.onItemClick(getAdapterPosition(),
                                mList.get(getAdapterPosition()));
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

    public interface OnStockTransDetailAdapterListener
            extends OnRecyclerItemListener<StockTransDetail> {
        void onSerialPickerClick(int position, StockTransDetail stockTransDetail);
    }
}
