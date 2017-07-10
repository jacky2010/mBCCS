package com.viettel.mbccs.screen.assigntask.cskpp.detail;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.TaskShopManagement;
import com.viettel.mbccs.screen.assigntask.adapters.TaskCSKPPDetailAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public class TaskCSKPPDetailPresenter implements TaskCSKPPDetailContract.Presenter {

    public ObservableField<String> title = new ObservableField<>();

    public ObservableField<String> duration = new ObservableField<>();

    public ObservableField<String> status = new ObservableField<>();

    public ObservableField<String> staffName = new ObservableField<>();

    public ObservableField<String> assignDate = new ObservableField<>();

    public ObservableField<String> taskType = new ObservableField<>();

    public ObservableField<String> taskName = new ObservableField<>();

    public ObservableField<String> channelType = new ObservableField<>();

    public ObservableField<String> channelCount = new ObservableField<>();

    public ObservableBoolean update = new ObservableBoolean();

    private Context mContext;

    private TaskCSKPPDetailContract.ViewModel mViewModel;

    private TaskShopManagement mTaskShopManagement;

    public TaskCSKPPDetailPresenter(Context context, TaskCSKPPDetailContract.ViewModel viewModel,
                                    TaskShopManagement taskShopManagement, boolean update) {
        mContext = context;
        mViewModel = viewModel;
        mTaskShopManagement = taskShopManagement;
        this.update.set(update);
        title.set(mContext.getString(R.string.chi_tiet_cong_viec, mTaskShopManagement.getDescription()));
        switch (mTaskShopManagement.getStatus()) {
            case TaskShopManagement.TaskStaffStatus.STATUS_INPROGRESS:
                status.set(mContext.getString(R.string.inprogress));
                break;
            case TaskShopManagement.TaskStaffStatus.STATUS_NEW:
                status.set(mContext.getString(R.string.not_accepted));
            default:
                break;
        }
//        taskName.set(mTaskShopManagement.getTitle());
//        switch (mTaskShopManagement.getType()) {
//            case TaskShopManagement.TaskType.TYPE_PHAT_SINH:
//                taskType.set(mContext.getString(R.string.task_arising));
//                break;
//            case TaskShopManagement.TaskType.TYPE_CSKPP:
//                taskType.set(mContext.getString(R.string.task_cskpp));
//                break;
//            case TaskShopManagement.TaskType.TYPE_TEAM:
//            default:
//                taskType.set(mContext.getString(R.string.task_team));
//                break;
//        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public int getStatusBg() {
        switch (mTaskShopManagement.getStatus()) {
            case TaskShopManagement.TaskStaffStatus.STATUS_INPROGRESS:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return mContext.getResources()
                            .getColor(android.R.color.holo_orange_light, null);
                } else {
                    return mContext.getResources().getColor(android.R.color.holo_orange_light);
                }
            case TaskShopManagement.TaskStaffStatus.STATUS_NEW:
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return mContext.getResources().getColor(android.R.color.darker_gray, null);
                } else {
                    return mContext.getResources().getColor(android.R.color.darker_gray);
                }
        }
    }

    public RecyclerView.Adapter getAdapter() {
        // TODO: 29/05/2017 Fake empty data
        List<ChannelInfo> list = new ArrayList<>();
        list.add(new ChannelInfo(123, "channelCode",1, "Kenh A",
                "Ha Noi", "012145253", 2424, "managementCode",
                "managementType", "managementName", 1241, "shopCode",
                "shopName", "pricePolicy", "discountPolicy"));
        return new TaskCSKPPDetailAdapter(list);
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new android.support.v7.widget.DividerItemDecoration(mContext,
                android.support.v7.widget.DividerItemDecoration.VERTICAL);
    }

    public void onReject() {
        mViewModel.onReject();
    }

    public void onAccept() {
        mViewModel.onAccept();
    }

    public void onUpdate() {
        mViewModel.onUpdate();
    }

    public void onRadioButtonClicked(View v) {
    }

    public void onBackPressed() {
        mViewModel.onBackPressed();
    }
}
