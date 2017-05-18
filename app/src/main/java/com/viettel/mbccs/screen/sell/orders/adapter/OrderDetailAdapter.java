package com.viettel.mbccs.screen.sell.orders.adapter;

import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.GoodItem;
import com.viettel.mbccs.databinding.ItemOrderDetailBinding;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrderDetailAdapter
        extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {
    private ItemOrderDetailBinding binding;
    private List<GoodItem> goodItemList;
    private OrderDetailAdapterCallback callback;

    public OrderDetailAdapter(List<GoodItem> goodItemList) {
        this.goodItemList = goodItemList;
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
        holder.bind(goodItemList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return goodItemList == null ? 0 : goodItemList.size();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        private ItemOrderDetailBinding itemBinding;
        private int position;

        public ObservableField<GoodItem> goodItem;

        public OrderDetailViewHolder(ItemOrderDetailBinding itemView) {
            super(itemView.getRoot());
            itemBinding = itemView;
            goodItem = new ObservableField<>();
        }

        public void bind(GoodItem goodItem, int position) {
            if (itemBinding.getPresenter() == null) {
                itemBinding.setPresenter(this);
                itemBinding.executePendingBindings();
            }
            this.position = position;
            this.goodItem.set(goodItem);
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
