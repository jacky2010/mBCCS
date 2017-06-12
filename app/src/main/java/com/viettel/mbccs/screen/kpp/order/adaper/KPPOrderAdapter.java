package com.viettel.mbccs.screen.kpp.order.adaper;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.databinding.ItemKppOrderBinding;
import java.util.List;

/**
 * Created by eo_cuong on 5/21/17.
 */

public class KPPOrderAdapter extends RecyclerView.Adapter<KPPOrderAdapter.KPPOrderViewHolder> {

    private Context mContext;
    private List<SaleOrders> mSaleOrderses;
    private KPPOrderListener mKPPOrderListener;

    public KPPOrderAdapter(Context context, List<SaleOrders> saleOrderses) {
        mContext = context;
        mSaleOrderses = saleOrderses;
    }

    @Override
    public KPPOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KPPOrderViewHolder(
                (ItemKppOrderBinding) DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.item_kpp_order, parent, false));
    }

    @Override
    public void onBindViewHolder(KPPOrderViewHolder holder, int position) {
        holder.bind(mSaleOrderses.get(position));
    }

    @Override
    public int getItemCount() {
        return mSaleOrderses.size();
    }

    public void setKPPOrderListener(KPPOrderListener KPPOrderListener) {
        mKPPOrderListener = KPPOrderListener;
    }

    class KPPOrderViewHolder extends RecyclerView.ViewHolder {

        ItemKppOrderBinding mBinding;

        KPPOrderViewHolder(ItemKppOrderBinding binding) {
            super(binding.getRoot());
            this.mBinding=binding;
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
