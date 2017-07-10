package com.viettel.mbccs.screen.assigntask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.TaskShopManagement;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetTaskPrepareAssignStaffRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetTaskPrepareAssignStaffResponse;
import com.viettel.mbccs.screen.assigntask.adapters.AssignTaskAdapter;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;

import rx.Subscription;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public class ListAssignTaskPresenter extends BaseListTaskPresenter<TaskShopManagement>
        implements ListAssignTaskContract.Presenter {

    public ListAssignTaskPresenter(Context context, ListAssignTaskContract.ViewModel viewModel) {
        super(context, viewModel);
    }

    @Override
    public void doSearch() {
        long fromDate = ((ListAssignTaskContract.ViewModel) mViewModel).getFromDate();
        long toDate = ((ListAssignTaskContract.ViewModel) mViewModel).getToDate();
        if (fromDate > toDate){
            // TODO: 7/2/2017 Show error
            return;
        }
        mViewModel.showLoading();

        GetTaskPrepareAssignStaffRequest request = new GetTaskPrepareAssignStaffRequest();
        request.setShopCode(mUserRepository.getUserInfo().getShop().getShopCode());
        request.setType(String.valueOf(taskType.get()));
        request.setFromDate(DateUtils.convertDateToString(fromDate,
                DateUtils.TIMEZONE_FORMAT_SERVER));
        request.setToDate(DateUtils.convertDateToString(toDate,
                DateUtils.TIMEZONE_FORMAT_SERVER));
        request.setStaffCode(mUserRepository.getUserInfo().getStaffInfo().getStaffCode());
        request.setStatus(String.valueOf(taskStatus.get()));

        DataRequest<GetTaskPrepareAssignStaffRequest> dataRequest = new DataRequest<>();
        dataRequest.setWsCode(WsCode.GetTaskPrepareAssignStaff);
        dataRequest.setWsRequest(request);

        Subscription subscription = mRepository.getTaskPrepareAssignStaff(dataRequest)
                .subscribe(new MBCCSSubscribe<GetTaskPrepareAssignStaffResponse>() {
                    @Override
                    public void onSuccess(GetTaskPrepareAssignStaffResponse object) {
                        mViewModel.hideLoading();
                        if (object == null) return;

                        listData.addAll(object.getLstTaskShopManagement());
                    }

                    @Override
                    public void onError(BaseException error) {
                        mViewModel.hideLoading();
                        mViewModel.showErrorDialog(error);
                    }
                });
        mSubscription.add(subscription);
    }

    @Override
    protected RecyclerView.Adapter getListAdapter() {
        AssignTaskAdapter adapter = new AssignTaskAdapter(mContext, listData);
        adapter.setOnTaskClickListener(this);
        return adapter;
    }
}
