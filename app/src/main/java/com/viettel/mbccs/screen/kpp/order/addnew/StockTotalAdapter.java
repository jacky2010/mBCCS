package com.viettel.mbccs.screen.kpp.order.addnew;

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
import com.viettel.mbccs.databinding.ItemAddNewOrderBinding;
import com.viettel.mbccs.screen.kpp.order.findstock.adapter.ItemStockTotalPresenter;
import com.viettel.mbccs.screen.kpp.order.findstock.adapter.StockTotalPickerAdapter;
import java.util.List;

/**
 * Created by eo_cuong on 5/22/17.
 */

public class StockTotalAdapter
        extends RecyclerView.Adapter<StockTotalAdapter.StockTotalViewHolder> {

    private Context mContext;
    private List<StockTotal> mStockTotals;

    public StockTotalAdapter(Context context, List<StockTotal> stockTotals) {
        mContext = context;
        mStockTotals = stockTotals;
    }

    @Override
    public StockTotalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StockTotalViewHolder(
                (ItemAddNewOrderBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_add_new_order, parent, false));
    }

    @Override
    public void onBindViewHolder(StockTotalViewHolder holder, int position) {
        holder.bind(mStockTotals.get(position));
    }

    @Override
    public int getItemCount() {
        return mStockTotals.size();
    }

    class StockTotalViewHolder extends RecyclerView.ViewHolder {

        ItemAddNewOrderBinding mBinding;
        StockTotal mStockTotal;

        public StockTotalViewHolder(ItemAddNewOrderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mStockTotals.remove(mStockTotal);
                    notifyDataSetChanged();
                }
            });
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
            mBinding.setItem(itemStockTotalPresenter);
        }
    }
}
