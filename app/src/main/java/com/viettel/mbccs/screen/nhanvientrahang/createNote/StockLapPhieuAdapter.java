package com.viettel.mbccs.screen.nhanvientrahang.createNote;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.callback.OnListenerItemRecyclerView;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.databinding.ItemStockLapPhieuBinding;
import com.viettel.mbccs.screen.kpp.order.findstock.adapter.ItemStockTotalPresenter;
import com.viettel.mbccs.widget.viewholderbinding.BaseRecyclerViewAdapterBinding;
import com.viettel.mbccs.widget.viewholderbinding.BaseViewHolderBinding;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 28/06/2017.
 */

public class StockLapPhieuAdapter extends
        BaseRecyclerViewAdapterBinding<StockLapPhieuAdapter.StockLapPhieuViewHolder, StockTotal> {
    private OnStockLapPhieuListener mOnStockLapPhieuListener;

    public void setOnStockLapPhieuListener(OnStockLapPhieuListener onStockLapPhieuListener) {
        mOnStockLapPhieuListener = onStockLapPhieuListener;
    }

    public StockLapPhieuAdapter(Context context, List<StockTotal> list) {
        super(context, list);
    }

    @Override
    protected StockLapPhieuViewHolder getViewHolder(ViewGroup parent) {
        return new StockLapPhieuViewHolder(
                ItemStockLapPhieuBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    class StockLapPhieuViewHolder
            extends BaseViewHolderBinding<ItemStockLapPhieuBinding, StockTotal> {

        StockTotal mStockTotal;

        public StockLapPhieuViewHolder(ItemStockLapPhieuBinding binding) {
            super(binding);
            mBinding.inputQuantityChoice.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    try {
                        if (charSequence.toString().equals("")) {
                            mStockTotal.setCountChoice(0);
                        } else {
                            int quantity = Integer.parseInt(charSequence.toString());
                            if (quantity < 0) {
                                mBinding.inputQuantityChoice.setText(
                                        mStockTotal.getCountChoice() + "");
                                mBinding.inputQuantityChoice.setSelection(0,
                                        charSequence.toString().length());
                            } else {
                                if (quantity <= mStockTotal.getQuantity()) {
                                    mStockTotal.setCountChoice(quantity);
                                } else {
                                    mBinding.inputQuantityChoice.setText(
                                            mStockTotal.getCountChoice() + "");
                                    mBinding.inputQuantityChoice.setSelection(0,
                                            charSequence.toString().length());
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            mBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mList.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    if (mOnStockLapPhieuListener != null) {
                        mOnStockLapPhieuListener.onRemoveStock();
                    }
                }
            });

            mBinding.buttonChooseSerial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnStockLapPhieuListener != null) {
                        mOnStockLapPhieuListener.onSerialPickerClick(getAdapterPosition(),
                                mList.get(getAdapterPosition()));
                    }
                }
            });
        }

        @Override
        public void bindData(StockTotal stockTotal) {
            super.bindData(stockTotal);
            mStockTotal = stockTotal;
            ItemStockTotalPresenter itemStockTotalPresenter =
                    new ItemStockTotalPresenter(mContext, stockTotal);
            mBinding.setData(itemStockTotalPresenter);
        }
    }

    public interface OnStockLapPhieuListener extends OnListenerItemRecyclerView {
        void onSerialPickerClick(int position, StockTotal stockTotal);

        void onRemoveStock();
    }
}
