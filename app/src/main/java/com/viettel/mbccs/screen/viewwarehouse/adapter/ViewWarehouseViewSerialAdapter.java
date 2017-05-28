package com.viettel.mbccs.screen.viewwarehouse.adapter;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.databinding.ItemViewWareHouseViewSerialBinding;
import java.util.List;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class ViewWarehouseViewSerialAdapter
        extends RecyclerView.Adapter<ViewWarehouseViewSerialAdapter.ViewHolder> {
    private ItemViewWareHouseViewSerialBinding binding;
    private List<SerialBO> serialBOList;
    private Context context;

    public ViewWarehouseViewSerialAdapter(List<SerialBO> serialBOList, Context context) {
        this.serialBOList = serialBOList;
        this.context = context;
    }

    @Override
    public ViewWarehouseViewSerialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        binding =
                ItemViewWareHouseViewSerialBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewWarehouseViewSerialAdapter.ViewHolder holder, int position) {
        holder.bind(serialBOList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return serialBOList == null ? 0 : serialBOList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemViewWareHouseViewSerialBinding view;
        public ObservableInt background;
        public ObservableField<String> fromSerial;
        public ObservableField<String> toSerial;
        public ObservableField<String> quantity;

        public ViewHolder(ItemViewWareHouseViewSerialBinding itemView) {
            super(itemView.getRoot());
            view = itemView;
            background = new ObservableInt();
            fromSerial = new ObservableField<>();
            toSerial = new ObservableField<>();
            quantity = new ObservableField<>();
        }

        public void bind(SerialBO serialBO, int position) {
            if (view.getPresenter() == null) {
                view.setPresenter(this);
            }
            fromSerial.set(serialBO.getFromSerial());
            toSerial.set(serialBO.getToSerial());
            quantity.set(serialBO.getQuantityString());
            if (position % 2 == 0) {
                background.set(context.getResources().getColor(R.color.white));
            } else {
                background.set(context.getResources().getColor(R.color.grey_two));
            }
        }
    }
}
