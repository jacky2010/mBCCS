package com.viettel.mbccs.screen.viewwarehouse.adapter;

import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.databinding.ItemViewWareHouseOrderBinding;
import java.util.List;

/**
 * Created by HuyQuyet on 5/21/17.
 */

public class ViewWarehouseListOrderAdapter
        extends RecyclerView.Adapter<ViewWarehouseListOrderAdapter.ViewHolder> {
    private ItemViewWareHouseOrderBinding binding;
    private ViewWarehouseListOrderAdapterCallback listener;

    private List<StockTotal> stockTotalList;

    public ViewWarehouseListOrderAdapter(List<StockTotal> stockTotalList) {
        this.stockTotalList = stockTotalList;
    }

    @Override
    public ViewWarehouseListOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        binding = ItemViewWareHouseOrderBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewWarehouseListOrderAdapter.ViewHolder holder, int position) {
        holder.bind(stockTotalList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return stockTotalList == null ? 0 : stockTotalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemViewWareHouseOrderBinding view;
        private int position;

        public ObservableField<StockTotal> stockTotal;

        public ViewHolder(ItemViewWareHouseOrderBinding itemView) {
            super(itemView.getRoot());
            view = itemView;
            stockTotal = new ObservableField<>();
        }

        public void bind(StockTotal stockTotal, int position) {
            if (view.getPresenter() == null) {
                view.setPresenter(this);
            }
            this.stockTotal.set(stockTotal);
            this.position = position;
        }

        public void onClickViewSerial() {
            if (listener != null) {
                listener.onClickViewSerial(position);
            }
        }
    }

    public interface ViewWarehouseListOrderAdapterCallback {
        void onClickViewSerial(int position);
    }

    public void setViewWarehouseListOrderAdapterCallback(
            ViewWarehouseListOrderAdapterCallback listener) {
        this.listener = listener;
    }
}
