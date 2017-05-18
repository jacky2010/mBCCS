package com.viettel.mbccs.screen.sellretail.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.databinding.ItemStockBinding;
import java.util.List;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<ModelSale> mStockItems;
    private OnStockListener mOnStockListener;

    public StockAdapter(Context context, List<ModelSale> stockItems) {
        mContext = context;
        mStockItems = stockItems;
    }

    public void setOnStockListener(OnStockListener onStockListener) {
        mOnStockListener = onStockListener;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<ModelSale> getStockItems() {
        return mStockItems;
    }

    public void setStockItems(List<ModelSale> stockItems) {
        mStockItems = stockItems;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StockViewHolder(
                (ItemStockBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_stock, parent, false));
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        holder.bind(mStockItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mStockItems.size();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (!b) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();
                }
            });
        }
    }

    class StockViewHolder extends RecyclerView.ViewHolder {

        ItemStockBinding mBinding;
        ModelSale mStockItem;

        public StockViewHolder(final ItemStockBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.btnSubtracs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentChoice = mStockItem.getChoiceCount();
                    if (currentChoice > 0) {
                        currentChoice--;
                        mStockItem.setChoiceCount(currentChoice);
                        notifyDataSetChanged();
                    }
                }
            });

            mBinding.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentChoice = mStockItem.getChoiceCount();
                    if (currentChoice < mStockItem.getRemainGoodCount()) {
                        currentChoice++;
                        mStockItem.setChoiceCount(currentChoice);
                        notifyDataSetChanged();
                    }
                }
            });

            binding.inputQuantityChoice.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    try {
                        if (charSequence.toString().equals("")) {
                            mStockItem.setChoiceCount(0);
                        } else {
                            int quantity = Integer.parseInt(charSequence.toString());
                            if (quantity <= mStockItem.getRemainGoodCount()) {
                                mStockItem.setChoiceCount(quantity);
                            } else {
                                binding.inputQuantityChoice.setText(
                                        mStockItem.getChoiceCount() + "");
                                binding.inputQuantityChoice.setSelection(0,
                                        charSequence.toString().length());
                            }
                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            mBinding.inputQuantityChoice.setOnEditorActionListener(
                    new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                            if (i == EditorInfo.IME_ACTION_DONE) {
                                binding.inputQuantityChoice.clearFocus();
                                InputMethodManager imm =
                                        (InputMethodManager) mContext.getSystemService(
                                                Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(
                                        binding.inputQuantityChoice.getWindowToken(), 0);
                                return true;
                            }
                            return false;
                        }
                    });

            mBinding.inputQuantityChoice.setOnFocusChangeListener(StockAdapter.this);

            binding.textSerial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnStockListener != null) {
                        mOnStockListener.onSerialClick(mStockItem,getAdapterPosition());
                    }
                }
            });
        }

        public void bind(ModelSale item) {
            mStockItem = item;
            ItemStockPresenter itemStockPresenter = new ItemStockPresenter(mContext, item);
            mBinding.setItem(itemStockPresenter);
        }
    }

    public interface OnStockListener {
        void onSerialClick(ModelSale item, int position);
    }
}
