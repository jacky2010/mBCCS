package com.viettel.mbccs.screen.sell.orders.adapter;

import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import com.viettel.mbccs.databinding.ItemOrderDetailBinding;
import com.viettel.mbccs.utils.Common;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrderDetailAdapter
        extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {
    private ItemOrderDetailBinding binding;
    private List<SaleOrdersDetail> saleOrdersDetailList;
    private OrderDetailAdapterCallback callback;

    public OrderDetailAdapter(List<SaleOrdersDetail> saleOrdersDetailList) {
        this.saleOrdersDetailList = saleOrdersDetailList;
    }

    @Override
    public OrderDetailAdapter.OrderDetailViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        binding = ItemOrderDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                false);
        return new OrderDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(OrderDetailAdapter.OrderDetailViewHolder holder, int position) {
        holder.bind(saleOrdersDetailList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return saleOrdersDetailList == null ? 0 : saleOrdersDetailList.size();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        private ItemOrderDetailBinding itemBinding;
        private int position;

        private SaleOrdersDetail saleOrdersDetail;
        public ObservableField<String> imageUrl;
        public ObservableField<String> nameStock;
        public ObservableField<String> priceStock;
        public ObservableField<String> quantityStock;
        public ObservableField<String> countStock;
        public ObservableField<String> selectStock;

        public OrderDetailViewHolder(ItemOrderDetailBinding itemView) {
            super(itemView.getRoot());
            itemBinding = itemView;
            imageUrl = new ObservableField<>();
            nameStock = new ObservableField<>();
            priceStock = new ObservableField<>();
            quantityStock = new ObservableField<>();
            countStock = new ObservableField<>();
            selectStock = new ObservableField<>();
        }

        public void bind(SaleOrdersDetail item, int pos) {
            if (itemBinding.getPresenter() == null) {
                itemBinding.setPresenter(this);
                itemBinding.executePendingBindings();
            }
            position = pos;
            saleOrdersDetail = item;
            imageUrl.set(saleOrdersDetail.getImageUrl());
            nameStock.set(saleOrdersDetail.getStockMoldeName());
            priceStock.set(Common.formatDouble(saleOrdersDetail.getPrice()));
            quantityStock.set(String.valueOf(saleOrdersDetail.getQuantity()));
            countStock.set(String.valueOf(saleOrdersDetail.getCount()));
            selectStock.set(String.valueOf(saleOrdersDetail.getSelect()));
        }

        public void selectSerial() {
            if (callback != null) {
                callback.selectSerialClick(position);
            }
        }
    }

    public interface OrderDetailAdapterCallback {
        void itemOrderDetailClick(int position);

        void selectSerialClick(int position);
    }

    public void setOrderDetailAdapterCallback(OrderDetailAdapterCallback callback) {
        this.callback = callback;
    }
}
