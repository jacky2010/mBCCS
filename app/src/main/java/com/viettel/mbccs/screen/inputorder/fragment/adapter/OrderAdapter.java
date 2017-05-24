package com.viettel.mbccs.screen.inputorder.fragment.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.BillRange;
import com.viettel.mbccs.databinding.ItemBillRangeBinding;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<BillRange> mBillRangeList;
    private boolean isLastItems;

    public OrderAdapter() {
    }

    public void setBillRangeList(List<BillRange> list) {
        if (null == list || list.isEmpty()) {
            reachLastItems();
            return;
        }
        if (mBillRangeList == null) {
            mBillRangeList = new ArrayList<>();
        }
        int sizeList = mBillRangeList.size();
        mBillRangeList.addAll(list);
        notifyItemInserted(sizeList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBillRangeBinding mBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_bill_range, parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mBillRangeList.get(position));
        holder.setIsReachLastItems(isLastItems);
    }

    @Override
    public int getItemCount() {
        return mBillRangeList == null ? 0 : mBillRangeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemBillRangeBinding mBinding;
        private ObservableBoolean isReachLastItems;

        public ViewHolder(ItemBillRangeBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            isReachLastItems = new ObservableBoolean();
        }

        public void bindData(BillRange billRange) {
            if (mBinding.getPresenter() == null) {
                mBinding.setPresenter(new BillRangePresenter(billRange));
            }
        }

        public void setIsReachLastItems(ObservableBoolean isReachLastItems) {
            this.isReachLastItems = isReachLastItems;
        }

        public void setIsReachLastItems(boolean isReachLastItems) {
            this.isReachLastItems.set(isReachLastItems);
        }
    }

    public void reachLastItems() {
        isLastItems = true;
    }

    public boolean isReachLastItems() {
        return isLastItems;
    }
}
