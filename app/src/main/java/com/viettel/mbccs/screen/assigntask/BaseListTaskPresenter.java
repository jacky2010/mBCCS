package com.viettel.mbccs.screen.assigntask;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewPresenter;
import com.viettel.mbccs.data.model.TaskShopManagement;
import com.viettel.mbccs.data.source.CongViecRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.screen.assigntask.adapters.AssignTaskAdapter;
import com.viettel.mbccs.widget.SpinnerWithBorder.HintAdapter;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public abstract class BaseListTaskPresenter<T>
        extends BaseSearchListViewPresenter<T, BaseSearchListViewContract.ViewModel>
        implements AssignTaskAdapter.OnTaskClickListener {

    public ObservableInt taskTypePosition = new ObservableInt();

    public ObservableInt taskStatusPosition = new ObservableInt();

    public ObservableInt taskType = new ObservableInt();

    public ObservableInt taskStatus = new ObservableInt();

    public ObservableField<HintAdapter<String>> taskTypeAdapter =
            new ObservableField<HintAdapter<String>>() {
                @Override
                public void set(HintAdapter<String> value) {
                    super.set(value);
                    taskTypePosition.set(value.getCount());
                }
            };

    public ObservableField<HintAdapter<String>> taskStatusAdapter =
            new ObservableField<HintAdapter<String>>() {
                @Override
                public void set(HintAdapter<String> value) {
                    super.set(value);
                    taskStatusPosition.set(value.getCount());
                }
            };

    protected CongViecRepository mRepository;

    protected CompositeSubscription mSubscription = new CompositeSubscription();

    protected UserRepository mUserRepository;

    public BaseListTaskPresenter(Context context, BaseSearchListViewContract.ViewModel viewModel) {
        super(context, viewModel);

        mUserRepository = UserRepository.getInstance();
        taskType.set(TaskShopManagement.TaskType.TYPE_CSKPP);

        HintAdapter<String> adapter = new HintAdapter<>(mContext,
                mContext.getResources().getStringArray(R.array.task_type));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        taskType.set(TaskShopManagement.TaskType.TYPE_CSKPP);
                        break;
                    case 1:
                        taskType.set(TaskShopManagement.TaskType.TYPE_PHAT_SINH);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        taskTypeAdapter.set(adapter);
        taskTypePosition.set(0);

        HintAdapter<String> adapter1 = new HintAdapter<>(mContext,
                mContext.getResources().getStringArray(R.array.task_status));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        taskStatus.set(TaskShopManagement.TaskStaffStatus.STATUS_NEW);
                        break;
                    case 1:
                        taskStatus.set(TaskShopManagement.TaskStaffStatus.STATUS_INPROGRESS);
                        break;
                    case 2:
                        taskStatus.set(TaskShopManagement.TaskStaffStatus.STATUS_CLOSE);
                        break;
                    case 3:
                        taskStatus.set(TaskShopManagement.TaskStaffStatus.STATUS_REJECTED);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        taskStatusAdapter.set(adapter1);
        mRepository = CongViecRepository.getInstance();
    }

    @Override
    public void onSearchSuccess() {

    }

    @Override
    public void onSearchFail() {

    }

    @Override
    public String getSearchHint() {
        return mContext.getString(R.string.search_task_hint);
    }

    @Override
    public String getToolbarTitle() {
        return mContext.getString(R.string.quan_ly_giao_viec);
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }

    @Override
    public String getItemCountString() {
        return null;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
    }

    @Override
    public void onAddClick() {
        mViewModel.onAddClick();
    }

    @Override
    public void onTaskClick(TaskShopManagement task) {
        mViewModel.onItemClicked(task);
    }
}
