package com.viettel.mbccs.screen.assigntask.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.databinding.ItemTaskKppBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/28/2017.
 */

public class TaskCSKPPDetailAdapter extends RecyclerView.Adapter<TaskCSKPPDetailAdapter.ViewHolder> {

    private List<ChannelInfo> mList = new ArrayList<>();

    public TaskCSKPPDetailAdapter(List<ChannelInfo> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemTaskKppBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_task_kpp, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemTaskKppBinding mBinding;

        public ViewHolder(ItemTaskKppBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (mOnStaffPickListener != null) {
//                        mOnStaffPickListener.onItemPicked(mList.get(getAdapterPosition()));
//                    }
//                }
//            });
        }

        public void bind(ChannelInfo channelInfo) {
            mBinding.setItem(channelInfo);
        }
    }
}
