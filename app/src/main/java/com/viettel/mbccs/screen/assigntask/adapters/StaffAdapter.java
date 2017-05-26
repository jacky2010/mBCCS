package com.viettel.mbccs.screen.assigntask.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.databinding.ItemStaffPickerBinding;

import java.util.List;

/**
 * Created by Anh Vu Viet on 5/27/2017.
 */

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {

    private List<StaffInfo> mList;

    private OnStaffPickListener mOnStaffPickListener;

    public StaffAdapter(List<StaffInfo> staffs) {
        mList = staffs;
    }

    @Override
    public StaffAdapter.StaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StaffAdapter.StaffViewHolder((ItemStaffPickerBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_staff_picker, parent, false));
    }

    @Override
    public void onBindViewHolder(StaffAdapter.StaffViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public OnStaffPickListener getOnStaffPickListener() {
        return mOnStaffPickListener;
    }

    public void setOnStaffPickListener(OnStaffPickListener onStaffPickListener) {
        mOnStaffPickListener = onStaffPickListener;
    }

    class StaffViewHolder extends RecyclerView.ViewHolder {

        ItemStaffPickerBinding mBinding;

        public StaffViewHolder(ItemStaffPickerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnStaffPickListener != null) {
                        mOnStaffPickListener.onItemPicked(mList.get(getAdapterPosition()));
                    }
                }
            });
        }

        public void bind(StaffInfo staffInfo) {
            mBinding.setTitle(staffInfo.getStaffCode() + " - " + staffInfo.getStaffName());
            mBinding.setTel(staffInfo.getTel());
            mBinding.setTaskTitle(staffInfo.getStaffJobTitle() + " - " + staffInfo.getStaffAddress());
        }
    }

    public interface OnStaffPickListener {
        void onItemPicked(StaffInfo staffInfo);
    }
}
