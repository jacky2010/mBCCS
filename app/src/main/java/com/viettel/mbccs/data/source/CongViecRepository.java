package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.remote.ICongViecRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.CongViecRemoteDataSource;
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

import rx.Observable;

/**
 * Created by Anh Vu Viet on 6/23/2017.
 */

public class CongViecRepository implements ICongViecRemoteDataSource {

    private volatile static CongViecRepository instance;
    private ICongViecRemoteDataSource mCongViecRemoteDataSource;

    public CongViecRepository(CongViecRemoteDataSource congViecRemoteDataSource) {
        mCongViecRemoteDataSource = congViecRemoteDataSource;
    }

    public static CongViecRepository getInstance() {
        if (instance == null) {
            instance = new CongViecRepository(CongViecRemoteDataSource.getInstance());
        }
        return instance;
    }

    @Override
    public Observable<GetTaskPrepareAssignStaffResponse> getTaskPrepareAssignStaff(DataRequest<GetTaskPrepareAssignStaffRequest> request) {
        return mCongViecRemoteDataSource.getTaskPrepareAssignStaff(request);
    }

    @Override
    public Observable<AssignTaskForStaffResponse> assignTaskForStaff(DataRequest<AssignTaskForStaffRequest> request) {
        return mCongViecRemoteDataSource.assignTaskForStaff(request);
    }

    @Override
    public Observable<GetInfoTaskForUpdateResponse> getInfoTaskForUpdate(DataRequest<GetInfoTaskForUpdateRequest> request) {
        return mCongViecRemoteDataSource.getInfoTaskForUpdate(request);
    }

    @Override
    public Observable<GetAccessoryForUpdateTaskResponse> getAccessoryForUpdateTask(DataRequest<GetAccessoryForUpdateTaskRequest> request) {
        return mCongViecRemoteDataSource.getAccessoryForUpdateTask(request);
    }

    @Override
    public Observable<GetStockModelForUpdateTaskResponse> getStockModelForUpdateTask(DataRequest<GetStockModelForUpdateTaskRequest> request) {
        return mCongViecRemoteDataSource.getStockModelForUpdateTask(request);
    }

    @Override
    public Observable<UpdateTaskResponse> updateTask(DataRequest<UpdateTaskRequest> request) {
        return mCongViecRemoteDataSource.updateTask(request);
    }

    @Override
    public Observable<CloseTaskResponse> closeTask(DataRequest<CloseTaskRequest> request) {
        return mCongViecRemoteDataSource.closeTask(request);
    }

    @Override
    public Observable<GetStaffToAssignTaskResponse> getStaffToAssignTask(DataRequest<GetStaffToAssignTaskRequest> request) {
        return mCongViecRemoteDataSource.getStaffToAssignTask(request);
    }

    @Override
    public Observable<GetChannelRouterResponse> getChannelRouter(DataRequest<GetChannelRouterRequest> request) {
        return mCongViecRemoteDataSource.getChannelRouter(request);
    }

    @Override
    public Observable<GetChannelWorkTypeResponse> getChannelWorkType(DataRequest<GetChannelWorkTypeRequest> request) {
        return mCongViecRemoteDataSource.getChannelWorkType(request);
    }

    @Override
    public Observable<GetListChannelResponse> getListChannel(DataRequest<GetListChannelRequest> request) {
        return mCongViecRemoteDataSource.getListChannel(request);
    }

    @Override
    public Observable<CreateTaskExtendResponse> createTaskExtend(DataRequest<CreateTaskExtendRequest> request) {
        return mCongViecRemoteDataSource.createTaskExtend(request);
    }
}
