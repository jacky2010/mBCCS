package com.viettel.mbccs.screen.connector.adapter;

import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.databinding.ItemConnectorMobileBinding;
import java.util.List;

/**
 * Created by HuyQuyet on 6/4/17.
 */

public class ConnectorMobileAdapter
        extends RecyclerView.Adapter<ConnectorMobileAdapter.ViewHolder> {
    private ItemConnectorMobileBinding binding;
    private ConnectorMobileAdapterCallback callback;
    private List<Contract> contractList;

    public ConnectorMobileAdapter(List<Contract> contracts) {
        contractList = contracts;
    }

    @Override
    public ConnectorMobileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding =
                ItemConnectorMobileBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ConnectorMobileAdapter.ViewHolder holder, int position) {
        holder.bind(contractList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return contractList == null ? 0 : contractList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemConnectorMobileBinding viewBinding;
        Contract contract;
        int position;

        public ObservableField<String> contractNo;
        public ObservableField<String> service;
        public ObservableField<String> isdn;
        public ObservableField<String> date;
        public ObservableField<String> status;

        public ViewHolder(ItemConnectorMobileBinding itemView) {
            super(itemView.getRoot());
            viewBinding = itemView;
            contractNo = new ObservableField<>();
            service = new ObservableField<>();
            isdn = new ObservableField<>();
            date = new ObservableField<>();
            status = new ObservableField<>();
        }

        public void bind(Contract data, int pos) {
            if (viewBinding.getPresenter() == null) {
                viewBinding.setPresenter(this);
            }
            contract = data;
            position = pos;

            contractNo.set("lkdjflajlf");
            service.set("lkdjflajlf");
            isdn.set("lkdjflajlf");
            date.set("lkdjflajlf");
            status.set("lkdjflajlf");
        }

        public void onItemClick() {
            if (callback != null) {
                callback.onItemClick(position);
            }
        }
    }

    public interface ConnectorMobileAdapterCallback {
        void onItemClick(int position);
    }

    public void setConnectorMobileAdapterCallback(ConnectorMobileAdapterCallback callback) {
        this.callback = callback;
    }
}
