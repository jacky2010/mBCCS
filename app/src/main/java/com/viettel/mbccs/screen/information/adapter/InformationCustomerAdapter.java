package com.viettel.mbccs.screen.information.adapter;

import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.databinding.ItemInformationCustomerBinding;
import java.util.List;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class InformationCustomerAdapter
        extends RecyclerView.Adapter<InformationCustomerAdapter.ViewHolder> {
    private ItemInformationCustomerBinding binding;
    private ItemClick listener;
    private List<Customer> dataList;

    public InformationCustomerAdapter(List<Customer> data) {
        this.dataList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemInformationCustomerBinding view;
        private int position;

        public ObservableField<String> name;
        public ObservableField<String> phone;
        public ObservableField<String> type;

        public ViewHolder(ItemInformationCustomerBinding itemView) {
            super(itemView.getRoot());
            view = itemView;

            name = new ObservableField<>();
            phone = new ObservableField<>();
            type = new ObservableField<>();
        }

        public void bind(Customer customer, int position) {
            if (view.getPresenter() == null){
                view.setPresenter(this);
            }
            name.set(customer.getCustomerName());
            phone.set(customer.getTin());
            this.position = position;
        }

        public void onClickEdit() {
            if (listener != null) {
                listener.onItemClick(position);
            }
        }
    }

    public interface ItemClick {
        void onItemClick(int position);
    }

    public void setItemClick(ItemClick itemClick) {
        listener = itemClick;
    }
}
