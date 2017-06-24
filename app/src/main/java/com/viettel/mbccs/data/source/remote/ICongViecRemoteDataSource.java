package com.viettel.mbccs.data.source.remote;

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

public interface ICongViecRemoteDataSource {

    // Assign - Update - Close
    Observable<GetTaskPrepareAssignStaffResponse> getTaskPrepareAssignStaff(DataRequest<GetTaskPrepareAssignStaffRequest> request);

    Observable<AssignTaskForStaffResponse> assignTaskForStaff(DataRequest<AssignTaskForStaffRequest> request);

    Observable<GetInfoTaskForUpdateResponse> getInfoTaskForUpdate(DataRequest<GetInfoTaskForUpdateRequest> request);

    Observable<GetAccessoryForUpdateTaskResponse> getAccessoryForUpdateTask(DataRequest<GetAccessoryForUpdateTaskRequest> request);

    Observable<GetStockModelForUpdateTaskResponse> getStockModelForUpdateTask(DataRequest<GetStockModelForUpdateTaskRequest> request);

    Observable<UpdateTaskResponse> updateTask(DataRequest<UpdateTaskRequest> request);

    Observable<CloseTaskResponse> closeTask(DataRequest<CloseTaskRequest> request);

    // Assign - Create
    Observable<GetStaffToAssignTaskResponse> getStaffToAssignTask(DataRequest<GetStaffToAssignTaskRequest> request);

    Observable<GetChannelRouterResponse> getChannelRouter(DataRequest<GetChannelRouterRequest> request);

    Observable<GetChannelWorkTypeResponse> getChannelWorkType(DataRequest<GetChannelWorkTypeRequest> request);

    Observable<GetListChannelResponse> getListChannel(DataRequest<GetListChannelRequest> request);

    Observable<CreateTaskExtendResponse> createTaskExtend(DataRequest<CreateTaskExtendRequest> request);
}
