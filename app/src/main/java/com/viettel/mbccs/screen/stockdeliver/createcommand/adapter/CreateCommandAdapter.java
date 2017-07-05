package com.viettel.mbccs.screen.stockdeliver.createcommand.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
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
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.databinding.ItemCreateCommandBinding;
import java.util.List;

/**
 * Created by eo_cuong on 5/22/17.
 */

public class CreateCommandAdapter
        extends RecyclerView.Adapter<CreateCommandAdapter.StockTotalViewHolder> {

    private Context mContext;
    private List<StockTransDetail> mStockTransDetails;
    private StockTotalListener mStockTotalListener;

    public CreateCommandAdapter(Context context, List<StockTransDetail> stockTransDetails) {
        mContext = context;
        mStockTransDetails = stockTransDetails;
    }

    @Override
    public StockTotalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StockTotalViewHolder(
                (ItemCreateCommandBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_create_command, parent, false));
    }

    @Override
    public void onBindViewHolder(StockTotalViewHolder holder, int position) {
        holder.bind(mStockTransDetails.get(position));
    }

    public void setStockTotalListener(StockTotalListener stockTotalListener) {
        mStockTotalListener = stockTotalListener;
    }

    @Override
    public int getItemCount() {
        return mStockTransDetails.size();
    }

    class StockTotalViewHolder extends RecyclerView.ViewHolder {

        ItemCreateCommandBinding mBinding;
        StockTransDetail mStockTransDetail;

        public StockTotalViewHolder(ItemCreateCommandBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.inputQuantityChoice.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    try {
                        if (charSequence.toString().equals("")) {
                            mStockTransDetail.setCountChoice(0);
                        } else {
                            int quantity = Integer.parseInt(charSequence.toString());
                            if (quantity < 0) {
                                mBinding.inputQuantityChoice.setText(
                                        mStockTransDetail.getCountChoice() + "");
                                mBinding.inputQuantityChoice.setSelection(0,
                                        charSequence.toString().length());
                            } else {
                                if (quantity <= mStockTransDetail.getQuantity()) {
                                    mStockTransDetail.setCountChoice(quantity);
                                } else {
                                    mBinding.inputQuantityChoice.setText(
                                            mStockTransDetail.getCountChoice() + "");
                                    mBinding.inputQuantityChoice.setSelection(0,
                                            charSequence.toString().length());
                                }
                            }
                        }
                        if (mStockTotalListener != null) {
                            mStockTotalListener.onStockQuantityChange();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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
                                mBinding.inputQuantityChoice.clearFocus();
                                InputMethodManager imm =
                                        (InputMethodManager) mContext.getSystemService(
                                                Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(
                                        mBinding.inputQuantityChoice.getWindowToken(), 0);
                                return true;
                            }
                            return false;
                        }
                    });

            mBinding.inputQuantityChoice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b) {
                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });
        }

        public void bind(StockTransDetail stockTransDetail) {
            mStockTransDetail = stockTransDetail;
            ItemCreateCommandPresenter itemCreateCommandPresenter =
                    new ItemCreateCommandPresenter(mContext, stockTransDetail);
            mBinding.setData(itemCreateCommandPresenter);
        }
    }

    public interface StockTotalListener {
        void onStockQuantityChange();
    }
}
