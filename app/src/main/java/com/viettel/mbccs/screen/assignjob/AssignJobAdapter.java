package com.viettel.mbccs.screen.assignjob;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.JobModel;
import com.viettel.mbccs.databinding.ItemAssignJobBinding;
import com.viettel.mbccs.utils.DateUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/20/2017.
 */

public class AssignJobAdapter extends RecyclerView.Adapter<AssignJobAdapter.AssignJobViewHolder> {

    private List<JobModel> mList = new ArrayList<>();

    private Context mContext;

    public AssignJobAdapter(Context context, List<JobModel> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public AssignJobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AssignJobViewHolder(
                ItemAssignJobBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(AssignJobViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class AssignJobViewHolder extends RecyclerView.ViewHolder {

        private ItemAssignJobBinding mBinding;

        public AssignJobViewHolder(ItemAssignJobBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(JobModel item) {
            mBinding.setJobTitle(item.getTitle());
            mBinding.setCreatedDate(
                    DateUtils.timestampToString(item.getCreatedDate(), DateUtils.DATE_PICKER_FORMAT,
                            null));
            switch (item.getType()) {
                case JobModel.JobType.TYPE_CSKPP:
                    mBinding.setJobType(itemView.getResources().getString(R.string.job_cskpp));
                    break;
                case JobModel.JobType.TYPE_ARISING:
                    mBinding.setJobType(itemView.getResources().getString(R.string.job_arising));
                    break;
                case JobModel.JobType.TYPE_TEAM:
                    mBinding.setJobType(itemView.getResources().getString(R.string.job_team));
                    break;
            }
            switch (item.getStatus()) {
                case JobModel.JobStatus.REJECTED:
                    mBinding.setJobStatus(itemView.getResources().getString(R.string.reject));
                    mBinding.setStatusBackground(Color.RED);
                    mBinding.setStatusBackgroundLight(false);
                    break;
                case JobModel.JobStatus.NOT_ACCEPTED:
                    mBinding.setJobStatus(itemView.getResources().getString(R.string.not_accepted));
                    mBinding.setStatusBackground(Color.GRAY);
                    mBinding.setStatusBackgroundLight(false);
                    break;
                case JobModel.JobStatus.INPROGRESS:
                    mBinding.setJobStatus(itemView.getResources().getString(R.string.inprogress));
                    mBinding.setStatusBackground(Color.YELLOW);
                    mBinding.setStatusBackgroundLight(true);
                    break;
                case JobModel.JobStatus.DONE:
                    mBinding.setJobStatus(itemView.getResources().getString(R.string.finished));
                    mBinding.setStatusBackground(Color.GREEN);
                    mBinding.setStatusBackgroundLight(true);
                    break;
            }
        }
    }
}
