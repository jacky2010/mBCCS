package com.viettel.mbccs.screen.viewwarehouse.adapter;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
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
    private List<SerialBO> serialBOList;

    public ViewWarehouseViewSerialAdapter(List<SerialBO> serialBOList) {
        this.serialBOList = serialBOList;
    }

    @Override
    public ViewWarehouseViewSerialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewWarehouseViewSerialAdapter.ViewHolder holder, int position) {

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

            background = new ObservableInt();
            fromSerial = new ObservableField<>();
            toSerial = new ObservableField<>();
            quantity = new ObservableField<>();
        }

        public void bind(SerialBO serialBO, int position){
            if (view.getPresenter() ==null){
                view.setPresenter(this);
            }
            fromSerial.set(serialBO.getFromSerial());
            toSerial.set(serialBO.getToSerial());
            quantity.set(serialBO.getQuantityString());
            if (position % 2 == 0){
                background.set(R.color.white);
            }else {
                background.set(R.color.grey);
            }
        }
    }
}
