package com.viettel.mbccs.screen.kpp.order.findstock.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.databinding.ItemStocktotalPickerBinding;
import java.util.List;

public class StockTotalPickerAdapter
        extends RecyclerView.Adapter<StockTotalPickerAdapter.StockTotalPickerViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<StockTotal> mStockTotals;

    private OnStockTotalPickListener mOnStockTotalPickListener;

    public StockTotalPickerAdapter(Context context, List<StockTotal> stockTotals) {
        mContext = context;
        mStockTotals = stockTotals;
    }

    @Override
    public StockTotalPickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StockTotalPickerViewHolder(
                (ItemStocktotalPickerBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_stocktotal_picker, parent, false));
    }

    @Override
    public void onBindViewHolder(StockTotalPickerViewHolder holder, int position) {
        holder.bind(mStockTotals.get(position));
    }

    public void setOnStockTotalPickListener(OnStockTotalPickListener onStockTotalPickListener) {
        mOnStockTotalPickListener = onStockTotalPickListener;
    }

    @Override
    public int getItemCount() {
        return mStockTotals.size();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            if (mOnStockTotalPickListener != null) {
                mOnStockTotalPickListener.onEdittextFocus();
            }
        }
    }

    class StockTotalPickerViewHolder extends RecyclerView.ViewHolder {

        ItemStocktotalPickerBinding mBinding;

        StockTotal mStockTotal;

        public StockTotalPickerViewHolder(ItemStocktotalPickerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.inputQuantityChoice.setOnFocusChangeListener(StockTotalPickerAdapter.this);
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
        }

        public void bind(StockTotal stockTotal) {
            mStockTotal = stockTotal;
            ItemStockTotalPresenter itemStockTotalPresenter =
                    new ItemStockTotalPresenter(stockTotal);
            mBinding.setData(itemStockTotalPresenter);
        }
    }

    public interface OnStockTotalPickListener {
        void onEdittextFocus();
    }
}
