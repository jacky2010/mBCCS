package com.viettel.mbccs.screen.assigntask.cskpp.update;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.TaskModel;
import com.viettel.mbccs.databinding.ItemTaskKppUpdateBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public class TaskCSKPPUpdatePresenter implements TaskCSKPPUpdateContract.Presenter {

    private Context mContext;

    private TaskCSKPPUpdateContract.ViewModel mViewModel;

    private TaskModel mTaskModel;

    public TaskCSKPPUpdatePresenter(Context context, TaskCSKPPUpdateContract.ViewModel viewModel,
            TaskModel taskModel) {
        mContext = context;
        mViewModel = viewModel;
        mTaskModel = taskModel;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public RecyclerView.Adapter getAdapter() {
        // TODO: 29/05/2017 Fake empty data
        List<ChannelInfo> list = new ArrayList<>();
        list.add(new ChannelInfo(123, "channelCode", "channelType", "Kenh A", "Ha Noi", "012145253",
                2424, "managementCode", "managementType", "managementName", 1241, "shopCode",
                "shopName", "pricePolicy", "discountPolicy"));
        return new TaskCSKPPUpdateAdapter(list);
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new android.support.v7.widget.DividerItemDecoration(mContext,
                android.support.v7.widget.DividerItemDecoration.VERTICAL);
    }

    public void onAccept() {
        mViewModel.onAccept();
    }

    public void onBackPressed() {
        mViewModel.onBackPressed();
    }

    public class TaskCSKPPUpdateAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<ChannelInfo> mList = new ArrayList<>();

        public TaskCSKPPUpdateAdapter(List<ChannelInfo> list) {
            mList = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder((ItemTaskKppUpdateBinding) DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.item_task_kpp_update, parent,
                    false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemTaskKppUpdateBinding mBinding;

        public ViewHolder(ItemTaskKppUpdateBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(ChannelInfo channelInfo) {
            mBinding.setItem(channelInfo);
        }
    }
}
