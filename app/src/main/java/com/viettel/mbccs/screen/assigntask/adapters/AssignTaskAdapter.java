package com.viettel.mbccs.screen.assigntask.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.TaskShopManagement;
import com.viettel.mbccs.databinding.ItemAssignTaskBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/20/2017.
 */

public class AssignTaskAdapter extends RecyclerView.Adapter<AssignTaskAdapter.AssignTaskViewHolder> {

    private List<TaskShopManagement> mList = new ArrayList<>();

    private Context mContext;

    private OnTaskClickListener mOnTaskClickListener;

    public AssignTaskAdapter(Context context, List<TaskShopManagement> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public AssignTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AssignTaskViewHolder(
                ItemAssignTaskBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(AssignTaskViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public OnTaskClickListener getOnTaskClickListener() {
        return mOnTaskClickListener;
    }

    public void setOnTaskClickListener(OnTaskClickListener onTaskClickListener) {
        mOnTaskClickListener = onTaskClickListener;
    }

    class AssignTaskViewHolder extends RecyclerView.ViewHolder {

        private ItemAssignTaskBinding mBinding;

        public AssignTaskViewHolder(ItemAssignTaskBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final TaskShopManagement item) {
            mBinding.setTaskTitle(item.getName());
            mBinding.setCreatedDate(item.getCreateDate());
            switch (item.getStatus()) {
                case TaskShopManagement.TaskStatus.STATUS_NEW:
                    mBinding.setTaskStatus(itemView.getResources().getString(R.string.not_accepted));
                    mBinding.setStatusBackground(Color.GRAY);
                    mBinding.setStatusBackgroundLight(false);
                    break;
                case TaskShopManagement.TaskStatus.STATUS_ASSIGNED:
                    mBinding.setTaskStatus(itemView.getResources().getString(R.string.inprogress));
                    mBinding.setStatusBackground(Color.YELLOW);
                    mBinding.setStatusBackgroundLight(true);
                    break;
                case TaskShopManagement.TaskStatus.STATUS_CLOSE:
                    mBinding.setTaskStatus(itemView.getResources().getString(R.string.finished));
                    mBinding.setStatusBackground(Color.GREEN);
                    mBinding.setStatusBackgroundLight(true);
                    break;
            }
            mBinding.setOnClicked(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnTaskClickListener != null) mOnTaskClickListener.onTaskClick(item);
                }
            });
        }
    }

    public interface OnTaskClickListener {
        void onTaskClick(TaskShopManagement task);
    }
}
