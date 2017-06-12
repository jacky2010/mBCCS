package com.viettel.mbccs.screen.sell.orders.adapter;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.databinding.ItemOrdersBinding;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {
    private Context context;
    private ItemOrdersBinding binding;
    private List<SaleOrders> saleOrdersList;
    private OrdersAdapterCallback callback;

    public OrdersAdapter(Context context, List<SaleOrders> saleOrdersList) {
        this.saleOrdersList = saleOrdersList;
        this.context = context;
    }

    @Override
    public OrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding =
                ItemOrdersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrdersViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(OrdersViewHolder holder, int position) {
        holder.bind(saleOrdersList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return saleOrdersList == null ? 0 : saleOrdersList.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {
        private ItemOrdersBinding itemBinding;
        private SaleOrders saleOrders;
        private int position;

        public ObservableField<String> idOrder;
        public ObservableField<String> changeCodeName;
        public ObservableField<String> dateOrder;
        //        public ObservableField<String> statusOrder;
        //        public ObservableInt colorStatus;

        public OrdersViewHolder(ItemOrdersBinding itemView) {
            super(itemView.getRoot());
            itemBinding = itemView;
            idOrder = new ObservableField<>();
            changeCodeName = new ObservableField<>();
            dateOrder = new ObservableField<>();
            //            statusOrder = new ObservableField<>();
            //            colorStatus = new ObservableInt();
        }

        public void bind(SaleOrders saleOrders, int position) {
            //            if (itemBinding.getPresenter() == null) {
            itemBinding.setPresenter(this);
            //            }
            this.position = position;
            this.saleOrders = saleOrders;

            idOrder.set(context.getString(R.string.item_orders_id,
                    String.valueOf(saleOrders.getSaleOrdersId())));
            dateOrder.set(context.getString(R.string.item_orders_date, saleOrders.getOrderDate()));
            changeCodeName.set(context.getString(R.string.item_orders_change_code_name,
                    saleOrders.getChannelCode(), saleOrders.getChannelName()));

            //            switch ((int) saleOrders.getOrderStatus()) {
            //                case (OrderStatus.APPROVALS):
            //                    statusOrder.set(saleOrders.getOderName());
            //                    colorStatus.set(context.getResources().getColor(R.color.green));
            //                    break;
            //                case (OrderStatus.PENDING):
            //                    statusOrder.set(saleOrders.getOderName());
            //                    colorStatus.set(context.getResources().getColor(R.color.blue));
            //                    break;
            //                case (OrderStatus.REJECT):
            //                    statusOrder.set(saleOrders.getOderName());
            //                    colorStatus.set(context.getResources().getColor(R.color.red_button));
            //                    break;
            //                default:
            //                    statusOrder.set(saleOrders.getOderName());
            //                    colorStatus.set(context.getResources().getColor(R.color.green));
            //                    break;
            //            }
        }

        public void onClickItem() {
            if (saleOrders.getOrderStatus().equals(OrderStatus.PENDING) && callback != null) {
                callback.itemOrderClick(position);
            }
        }
    }

    public interface OrdersAdapterCallback {
        void itemOrderClick(int position);
    }

    public void setOrdersAdapterCallback(OrdersAdapterCallback callback) {
        this.callback = callback;
    }
}
