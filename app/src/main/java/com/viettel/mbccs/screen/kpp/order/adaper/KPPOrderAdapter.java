package com.viettel.mbccs.screen.kpp.order.adaper;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.databinding.ItemKppOrderBinding;
import com.viettel.mbccs.databinding.ItemTotalAmoutKppBinding;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.widget.viewholderbinding.BaseViewHolderBinding;
import java.util.List;

/**
 * Created by eo_cuong on 5/21/17.
 */

public class KPPOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_TOTAL = 0;
    public static final int TYPE_NORMAL = 1;
    private Context mContext;
    private List<SaleOrders> mSaleOrderses;
    private KPPOrderListener mKPPOrderListener;

    public KPPOrderAdapter(Context context, List<SaleOrders> saleOrderses) {
        mContext = context;
        mSaleOrderses = saleOrderses;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TOTAL) {
            return new TotalViewHolder(
                    ItemTotalAmoutKppBinding.inflate(LayoutInflater.from(mContext), parent, false));
        }
        return new KPPOrderViewHolder(
                (ItemKppOrderBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_kpp_order, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mSaleOrderses.size() > 0) {
            if (holder instanceof KPPOrderViewHolder) {
                ((KPPOrderViewHolder) holder).bind(mSaleOrderses.get(position - 1));
            }

            if (holder instanceof TotalViewHolder) {
                ((TotalViewHolder) holder).bindData(null);
            }
        } else {
            ((KPPOrderViewHolder) holder).bind(mSaleOrderses.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mSaleOrderses.size() > 0) {
            if (position == 0) {
                return TYPE_TOTAL;
            }
            return TYPE_NORMAL;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mSaleOrderses.size() > 0) {
            return mSaleOrderses.size() + 1;
        }
        return mSaleOrderses.size();
    }

    public void setKPPOrderListener(KPPOrderListener KPPOrderListener) {
        mKPPOrderListener = KPPOrderListener;
    }

    class TotalViewHolder extends BaseViewHolderBinding<ItemTotalAmoutKppBinding, EmptyObject> {

        public TotalViewHolder(ItemTotalAmoutKppBinding binding) {
            super(binding);

        }

        @Override
        public void bindData(EmptyObject emptyObject) {
            super.bindData(emptyObject);
            double total = 0;
            for (SaleOrders saleOrders : mSaleOrderses) {
                total += saleOrders.getAmount();
            }
            if (total != 0) {
                mBinding.textTotalAmount.setText(
                        String.format(mContext.getString(R.string.kpp_order_label_amount),
                                Common.formatDouble(total)));
                mBinding.textTotalAmount.setVisibility(View.VISIBLE);
            } else {
                mBinding.textTotalAmount.setVisibility(View.GONE);
            }
        }
    }

    class KPPOrderViewHolder extends RecyclerView.ViewHolder {

        ItemKppOrderBinding mBinding;

        KPPOrderViewHolder(ItemKppOrderBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mKPPOrderListener != null) {
                        mKPPOrderListener.onOrderItemClick(mSaleOrderses.get(getAdapterPosition()),
                                mSaleOrderses.get(getAdapterPosition()).getOrderStatus());
                    }
                }
            });
        }

        public void bind(SaleOrders saleOrders) {
            ItemOrderSalePresenter itemOrderSalePresenter =
                    new ItemOrderSalePresenter(mContext, saleOrders);
            mBinding.setOrder(itemOrderSalePresenter);
        }
    }

    public interface KPPOrderListener {
        void onOrderItemClick(SaleOrders saleOrder, String status);
    }
}
