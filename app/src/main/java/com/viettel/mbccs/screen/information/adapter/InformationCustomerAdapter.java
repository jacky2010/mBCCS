package com.viettel.mbccs.screen.information.adapter;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.Subscriber;
import com.viettel.mbccs.databinding.ItemInformationCustomerBinding;
import java.util.List;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class InformationCustomerAdapter
        extends RecyclerView.Adapter<InformationCustomerAdapter.ViewHolder> {
    private ItemInformationCustomerBinding binding;
    private ItemClick listener;
    private Context context;
    private List<DataInformationCustomerAdapter> dataList;
    private boolean isCreate;

    public InformationCustomerAdapter(Context context, List<DataInformationCustomerAdapter> data,
            boolean isCreate) {
        this.context = context;
        this.dataList = data;
        this.isCreate = isCreate;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemInformationCustomerBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(dataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemInformationCustomerBinding viewBinding;
        private int position;

        public ObservableField<String> name;
        public ObservableField<String> phone;
        public ObservableField<String> type;
        public ObservableField<Drawable> icon;

        public ViewHolder(ItemInformationCustomerBinding itemView) {
            super(itemView.getRoot());
            viewBinding = itemView;

            name = new ObservableField<>();
            phone = new ObservableField<>();
            type = new ObservableField<>();
            icon = new ObservableField<>();
        }

        public void bind(DataInformationCustomerAdapter data, int position) {
            if (viewBinding.getPresenter() == null) {
                viewBinding.setPresenter(this);
            }
            name.set(data.getCustomer().getName());
            if (data.getSubscriber() != null) {
                phone.set(data.getSubscriber().getIsdn());
                type.set(data.getSubscriber().getSubType());
            }

            icon.set(isCreate ? context.getResources().getDrawable(R.drawable.ic_file_black_24dp)
                    : context.getResources().getDrawable(R.drawable.ic_edit_black));

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

    public static class DataInformationCustomerAdapter {
        private Customer customer;
        private Subscriber subscriber;

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public Subscriber getSubscriber() {
            return subscriber;
        }

        public void setSubscriber(Subscriber subscriber) {
            this.subscriber = subscriber;
        }
    }
}
