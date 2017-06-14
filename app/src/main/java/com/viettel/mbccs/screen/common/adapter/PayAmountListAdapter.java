package com.viettel.mbccs.screen.common.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.DefaultPaymentAmount;
import com.viettel.mbccs.databinding.ItemDefaultAmountBinding;
import com.viettel.mbccs.screen.common.payamount.ItemDefaultAmountPresenter;

import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class PayAmountListAdapter extends RecyclerView.Adapter<PayAmountListAdapter.PayAmountViewHolder>
        implements View.OnFocusChangeListener {

    private Context mContext;
    private List<DefaultPaymentAmount> mItems;
    private OnItemClickListener listener;

    public PayAmountListAdapter(Context context, List<DefaultPaymentAmount> items) {
        mContext = context;
        mItems = items;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<DefaultPaymentAmount> getItems() {
        return mItems;
    }

    public void setItems(List<DefaultPaymentAmount> items) {
        mItems = items;
    }

    public DefaultPaymentAmount getItem(int index) {
        return mItems.get(index);
    }

    @Override
    public PayAmountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PayAmountViewHolder(
                (ItemDefaultAmountBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_default_amount, parent, false));
    }

    @Override
    public void onBindViewHolder(PayAmountViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
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

    class PayAmountViewHolder extends RecyclerView.ViewHolder {

        ItemDefaultAmountBinding mBinding;
        DefaultPaymentAmount item;

        public PayAmountViewHolder(final ItemDefaultAmountBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.rbDefaultAmount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        for (int i = 0; i < getItemCount(); i++) {
                            DefaultPaymentAmount rowItem = getItem(i);

                            if (rowItem.equals(item)) {
                                rowItem.setSelected(true);
                            } else
                                rowItem.setSelected(false);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (listener != null)
                        listener.onClick(view, item);

                    notifyDataSetChanged();
                }
            });
        }

        public void bind(DefaultPaymentAmount item) {
            this.item = item;
            ItemDefaultAmountPresenter itemPresenter = new ItemDefaultAmountPresenter(mContext, item);
            mBinding.setPresenter(itemPresenter);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onClick(View view, DefaultPaymentAmount item);
    }
}