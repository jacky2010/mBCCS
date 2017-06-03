package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.ChecOTPRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeIdRequireRequest;
import com.viettel.mbccs.data.source.remote.request.GetOTPRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegiterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.RegisterCustomerInfoRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.ChecOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeIdRequireResponse;
import com.viettel.mbccs.data.source.remote.response.GetOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.RegisterCustomerInfoResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateAllSubInfoResponse;
import rx.Observable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public interface IQLKhachHangRemoteDataSource {

    Observable<GetRegiterSubInfoResponse> getRegiterSubInfo(
            DataRequest<GetRegiterSubInfoRequest> request);

    Observable<RegisterCustomerInfoResponse> registerCustomerInfo(
            DataRequest<RegisterCustomerInfoRequest> request);

    Observable<GetListBusTypeIdRequireResponse> getListBusTypeIdRequire(
            DataRequest<GetListBusTypeIdRequireRequest> request);

    Observable<GetApDomainResponse> getApDomain(DataRequest<GetApDomainRequest> request);

    Observable<GetOTPResponse> getOTP(DataRequest<GetOTPRequest> request);

    Observable<ChecOTPResponse> checOTP(DataRequest<ChecOTPRequest> request);

    Observable<UpdateAllSubInfoResponse> updateAllSubInfo(
            DataRequest<UpdateAllSubInfoRequest> request);
}
