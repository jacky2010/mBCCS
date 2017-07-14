package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetProductInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetProductSearchRequest;
import com.viettel.mbccs.data.source.remote.request.SearchAdvancedProductRequest;
import com.viettel.mbccs.data.source.remote.request.SearchProductRequest;
import com.viettel.mbccs.data.source.remote.response.GetProductInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetProductSearchResponse;
import com.viettel.mbccs.data.source.remote.response.SearchAdvancedProductResponse;
import com.viettel.mbccs.data.source.remote.response.SearchProductResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface ISearchProductRemoteDataSource {
    Observable<SearchProductResponse> searchProduct(DataRequest<SearchProductRequest> request);
    Observable<SearchAdvancedProductResponse> searchAdvancedProduct(DataRequest<SearchAdvancedProductRequest> request);
    Observable<GetProductSearchResponse> getProductSearch(DataRequest<GetProductSearchRequest> request);
    Observable<GetProductInfoResponse> getProductInfo(DataRequest<GetProductInfoRequest> request);
}
