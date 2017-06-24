package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.ICongViecRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.AssignTaskForStaffRequest;
import com.viettel.mbccs.data.source.remote.request.CloseTaskRequest;
import com.viettel.mbccs.data.source.remote.request.CreateTaskExtendRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAccessoryForUpdateTaskRequest;
import com.viettel.mbccs.data.source.remote.request.GetChannelRouterRequest;
import com.viettel.mbccs.data.source.remote.request.GetChannelWorkTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoTaskForUpdateRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelRequest;
import com.viettel.mbccs.data.source.remote.request.GetStaffToAssignTaskRequest;
import com.viettel.mbccs.data.source.remote.request.GetStockModelForUpdateTaskRequest;
import com.viettel.mbccs.data.source.remote.request.GetTaskPrepareAssignStaffRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateTaskRequest;
import com.viettel.mbccs.data.source.remote.response.AssignTaskForStaffResponse;
import com.viettel.mbccs.data.source.remote.response.CloseTaskResponse;
import com.viettel.mbccs.data.source.remote.response.CreateTaskExtendResponse;
import com.viettel.mbccs.data.source.remote.response.GetAccessoryForUpdateTaskResponse;
import com.viettel.mbccs.data.source.remote.response.GetChannelRouterResponse;
import com.viettel.mbccs.data.source.remote.response.GetChannelWorkTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoTaskForUpdateResponse;
import com.viettel.mbccs.data.source.remote.response.GetListChannelResponse;
import com.viettel.mbccs.data.source.remote.response.GetStaffToAssignTaskResponse;
import com.viettel.mbccs.data.source.remote.response.GetStockModelForUpdateTaskResponse;
import com.viettel.mbccs.data.source.remote.response.GetTaskPrepareAssignStaffResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateTaskResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by Anh Vu Viet on 6/24/2017.
 */

public class CongViecRemoteDataSource implements ICongViecRemoteDataSource {

    public volatile static CongViecRemoteDataSource instance;

    public static CongViecRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new CongViecRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<GetTaskPrepareAssignStaffResponse> getTaskPrepareAssignStaff(DataRequest<GetTaskPrepareAssignStaffRequest> request) {
        return RequestHelper.getRequest()
                .getTaskPrepareAssignStaff(request)
                .flatMap(SchedulerUtils.<GetTaskPrepareAssignStaffResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetTaskPrepareAssignStaffResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<AssignTaskForStaffResponse> assignTaskForStaff(DataRequest<AssignTaskForStaffRequest> request) {
        return RequestHelper.getRequest()
                .assignTaskForStaff(request)
                .flatMap(SchedulerUtils.<AssignTaskForStaffResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<AssignTaskForStaffResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetInfoTaskForUpdateResponse> getInfoTaskForUpdate(DataRequest<GetInfoTaskForUpdateRequest> request) {
        return RequestHelper.getRequest()
                .getInfoTaskForUpdate(request)
                .flatMap(SchedulerUtils.<GetInfoTaskForUpdateResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetInfoTaskForUpdateResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetAccessoryForUpdateTaskResponse> getAccessoryForUpdateTask(DataRequest<GetAccessoryForUpdateTaskRequest> request) {
        return RequestHelper.getRequest()
                .getAccessoryForUpdateTask(request)
                .flatMap(SchedulerUtils.<GetAccessoryForUpdateTaskResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetAccessoryForUpdateTaskResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetStockModelForUpdateTaskResponse> getStockModelForUpdateTask(DataRequest<GetStockModelForUpdateTaskRequest> request) {
        return RequestHelper.getRequest()
                .getStockModelForUpdateTask(request)
                .flatMap(SchedulerUtils.<GetStockModelForUpdateTaskResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetStockModelForUpdateTaskResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<UpdateTaskResponse> updateTask(DataRequest<UpdateTaskRequest> request) {
        return RequestHelper.getRequest()
                .updateTask(request)
                .flatMap(SchedulerUtils.<UpdateTaskResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<UpdateTaskResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<CloseTaskResponse> closeTask(DataRequest<CloseTaskRequest> request) {
        return RequestHelper.getRequest()
                .closeTask(request)
                .flatMap(SchedulerUtils.<CloseTaskResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<CloseTaskResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetStaffToAssignTaskResponse> getStaffToAssignTask(DataRequest<GetStaffToAssignTaskRequest> request) {
        return RequestHelper.getRequest()
                .getStaffToAssignTask(request)
                .flatMap(SchedulerUtils.<GetStaffToAssignTaskResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetStaffToAssignTaskResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetChannelRouterResponse> getChannelRouter(DataRequest<GetChannelRouterRequest> request) {
        return RequestHelper.getRequest()
                .getChannelRouter(request)
                .flatMap(SchedulerUtils.<GetChannelRouterResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetChannelRouterResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetChannelWorkTypeResponse> getChannelWorkType(DataRequest<GetChannelWorkTypeRequest> request) {
        return RequestHelper.getRequest()
                .getChannelWorkType(request)
                .flatMap(SchedulerUtils.<GetChannelWorkTypeResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetChannelWorkTypeResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListChannelResponse> getListChannel(DataRequest<GetListChannelRequest> request) {
        return RequestHelper.getRequest()
                .getListChannel(request)
                .flatMap(SchedulerUtils.<GetListChannelResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListChannelResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<CreateTaskExtendResponse> createTaskExtend(DataRequest<CreateTaskExtendRequest> request) {
        return RequestHelper.getRequest()
                .createTaskExtend(request)
                .flatMap(SchedulerUtils.<CreateTaskExtendResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<CreateTaskExtendResponse>applyAsyncSchedulers());
    }
}
