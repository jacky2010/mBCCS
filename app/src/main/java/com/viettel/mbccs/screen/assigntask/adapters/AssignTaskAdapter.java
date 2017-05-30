package com.viettel.mbccs.screen.assigntask.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.TaskModel;
import com.viettel.mbccs.databinding.ItemAssignTaskBinding;
import com.viettel.mbccs.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/20/2017.
 */

public class AssignTaskAdapter extends RecyclerView.Adapter<AssignTaskAdapter.AssignTaskViewHolder> {

    private List<TaskModel> mList = new ArrayList<>();

    private Context mContext;

    private OnTaskClickListener mOnTaskClickListener;

    public AssignTaskAdapter(Context context, List<TaskModel> list) {
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

        public void bind(final TaskModel item) {
            mBinding.setTaskTitle(item.getTitle());
            mBinding.setCreatedDate(
                    DateUtils.timestampToString(item.getCreatedDate(), DateUtils.DATE_PICKER_FORMAT,
                            null));
            switch (item.getType()) {
                case TaskModel.TaskType.TYPE_CSKPP:
                    mBinding.setTaskType(itemView.getResources().getString(R.string.task_cskpp));
                    break;
                case TaskModel.TaskType.TYPE_ARISING:
                    mBinding.setTaskType(itemView.getResources().getString(R.string.task_arising));
                    break;
                case TaskModel.TaskType.TYPE_TEAM:
                    mBinding.setTaskType(itemView.getResources().getString(R.string.task_team));
                    break;
            }
            switch (item.getStatus()) {
                case TaskModel.TaskStatus.REJECTED:
                    mBinding.setTaskStatus(itemView.getResources().getString(R.string.reject));
                    mBinding.setStatusBackground(Color.RED);
                    mBinding.setStatusBackgroundLight(false);
                    break;
                case TaskModel.TaskStatus.NOT_ACCEPTED:
                    mBinding.setTaskStatus(itemView.getResources().getString(R.string.not_accepted));
                    mBinding.setStatusBackground(Color.GRAY);
                    mBinding.setStatusBackgroundLight(false);
                    break;
                case TaskModel.TaskStatus.INPROGRESS:
                    mBinding.setTaskStatus(itemView.getResources().getString(R.string.inprogress));
                    mBinding.setStatusBackground(Color.YELLOW);
                    mBinding.setStatusBackgroundLight(true);
                    break;
                case TaskModel.TaskStatus.DONE:
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
        void onTaskClick(TaskModel task);
    }
}
