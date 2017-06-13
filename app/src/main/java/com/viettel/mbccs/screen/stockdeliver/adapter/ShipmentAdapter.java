package com.viettel.mbccs.screen.stockdeliver.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.Shipment;
import com.viettel.mbccs.databinding.ItemShipmentLayoutBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by buidinhviet on 6/13/17.
 */

public class ShipmentAdapter extends RecyclerView.Adapter<ShipmentAdapter.ShipmentHolder> {

    private List<Shipment> mShipmentList;

    public void setShipmentList(List<Shipment> list) {
        if (mShipmentList == null) {
            mShipmentList = new ArrayList<>();
        }
        int size = mShipmentList.size();
        mShipmentList.addAll(list);
        notifyItemInserted(size);
    }

    @Override
    public ShipmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemShipmentLayoutBinding mBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_shipment_layout, parent, false);

        return new ShipmentHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ShipmentHolder holder, int position) {
        holder.bindData(mShipmentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mShipmentList == null ? 0 : mShipmentList.size();
    }

    public static class ShipmentHolder extends RecyclerView.ViewHolder {

        private ItemShipmentLayoutBinding mBinding;

        public ShipmentHolder(ItemShipmentLayoutBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(Shipment shipment) {
            mBinding.setItem(shipment);
        }
    }
}
