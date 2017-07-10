package com.viettel.mbccs.screen.assigntask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.viettel.mbccs.data.model.TaskShopManagement;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoTaskForUpdateRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetInfoTaskForUpdateResponse;
import com.viettel.mbccs.screen.assigntask.adapters.AssignTaskAdapter;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;

import rx.Subscription;

/**
 * Created by Anh Vu Viet on 7/2/2017.
 */

public class ListUpdateTaskPresenter extends BaseListTaskPresenter<TaskShopManagement>
        implements ListAssignTaskContract.Presenter {

    public ListUpdateTaskPresenter(Context context, ListAssignTaskContract.ViewModel viewModel) {
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

        GetInfoTaskForUpdateRequest request = new GetInfoTaskForUpdateRequest();
        request.setServiceType(null);
        request.setShopCode(mUserRepository.getUserInfo().getShop().getShopCode());
        request.setType(taskType.get());
        request.setAccount(null);
        request.setFromDate(DateUtils.convertDateToString(fromDate,
                DateUtils.TIMEZONE_FORMAT_SERVER));
        request.setToDate(DateUtils.convertDateToString(toDate,
                DateUtils.TIMEZONE_FORMAT_SERVER));
        request.setStaffCode(mUserRepository.getUserInfo().getStaffInfo().getStaffCode());
        request.setStatus(taskStatus.get());

        DataRequest<GetInfoTaskForUpdateRequest> dataRequest = new DataRequest<>();
        dataRequest.setWsCode(ApiCode.GetInfoTaskForUpdate);
        dataRequest.setWsRequest(request);

        Subscription subscription = mRepository.getInfoTaskForUpdate(dataRequest)
                .subscribe(new MBCCSSubscribe<GetInfoTaskForUpdateResponse>() {
                    @Override
                    public void onSuccess(GetInfoTaskForUpdateResponse object) {
                        mViewModel.hideLoading();
                        if (object == null) return;

//                        listData.addAll(object.getLstTaskStaffManagement());
                    }

                    @Override
                    public void onError(BaseException error) {
                        mViewModel.hideLoading();
                        // TODO: 7/2/2017 Show error
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
