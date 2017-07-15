package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.ChecOTPRequest;
import com.viettel.mbccs.data.source.remote.request.CheckIdNoRequest;
import com.viettel.mbccs.data.source.remote.request.ConnectSubscriberRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetApDomainByTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetBankInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetCurrBillCycleRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeIdRequireRequest;
import com.viettel.mbccs.data.source.remote.request.GetListBusTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProductRequest;
import com.viettel.mbccs.data.source.remote.request.GetListRegTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSubTypeRequest;
import com.viettel.mbccs.data.source.remote.request.GetOTPRequest;
import com.viettel.mbccs.data.source.remote.request.GetQuotaByProductCodeRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubRequest;
import com.viettel.mbccs.data.source.remote.request.RegisterCustomerInfoRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateAllSubInfoRequest;
import com.viettel.mbccs.data.source.remote.response.CheckIdNoResponse;
import com.viettel.mbccs.data.source.remote.response.CheckOTPResponse;
import com.viettel.mbccs.data.source.remote.response.ConnectSubscriberResponse;
import com.viettel.mbccs.data.source.remote.response.GetAllSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetApDomainByTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetBankInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetCurrBillCycleResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeIdRequireResponse;
import com.viettel.mbccs.data.source.remote.response.GetListBusTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProductResponse;
import com.viettel.mbccs.data.source.remote.response.GetListRegTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSubTypeResponse;
import com.viettel.mbccs.data.source.remote.response.GetOTPResponse;
import com.viettel.mbccs.data.source.remote.response.GetQuotaByProductCodeResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubResponse;
import com.viettel.mbccs.data.source.remote.response.RegisterCustomerInfoResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateAllSubInfoResponse;
import rx.Observable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public interface IQLKhachHangRemoteDataSource {

    Observable<GetRegisterSubInfoResponse> getRegiterSubInfo(
            DataRequest<GetRegisterSubInfoRequest> request);

    Observable<RegisterCustomerInfoResponse> registerCustomerInfo(
            DataRequest<RegisterCustomerInfoRequest> request);

    Observable<GetListBusTypeIdRequireResponse> getListBusTypeIdRequire(
            DataRequest<GetListBusTypeIdRequireRequest> request);

    Observable<GetApDomainByTypeResponse> getApDomainByType(
            DataRequest<GetApDomainByTypeRequest> request);

    Observable<GetOTPResponse> getOTP(DataRequest<GetOTPRequest> request);

    Observable<CheckOTPResponse> checOTP(DataRequest<ChecOTPRequest> request);

    Observable<GetAllSubInfoResponse> getAllSubInfo(
            DataRequest<GetAllSubInfoRequest> request);

    Observable<UpdateAllSubInfoResponse> updateAllSubInfo(
            DataRequest<UpdateAllSubInfoRequest> request);

    Observable<CheckIdNoResponse> checkIdNo(DataRequest<CheckIdNoRequest> request);

    Observable<GetListProductResponse> getListProduct(DataRequest<GetListProductRequest> request);

    Observable<GetListRegTypeResponse> getListRegType(DataRequest<GetListRegTypeRequest> request);

    Observable<GetListSubTypeResponse> getListSubType(DataRequest<GetListSubTypeRequest> request);

    Observable<ConnectSubscriberResponse> connectSubscriber(DataRequest<ConnectSubscriberRequest> request);

    Observable<GetListBusTypeResponse> getListBusType(DataRequest<GetListBusTypeRequest> request);

    Observable<GetQuotaByProductCodeResponse> getQuotaByProductCode(
            DataRequest<GetQuotaByProductCodeRequest> request);

    Observable<GetBankInfoResponse> getBankInfo(DataRequest<GetBankInfoRequest> request);

    Observable<GetCurrBillCycleResponse> getCurrBillCycle(
            DataRequest<GetCurrBillCycleRequest> request);

    Observable<GetRegisterSubResponse> getRegisterSub(DataRequest<GetRegisterSubRequest> request);
}
