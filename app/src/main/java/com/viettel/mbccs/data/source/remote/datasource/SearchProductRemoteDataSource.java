package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.ISearchProductRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetProductInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetProductSearchRequest;
import com.viettel.mbccs.data.source.remote.request.SearchAdvancedProductRequest;
import com.viettel.mbccs.data.source.remote.request.SearchProductRequest;
import com.viettel.mbccs.data.source.remote.response.GetProductInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetProductSearchResponse;
import com.viettel.mbccs.data.source.remote.response.SearchAdvancedProductResponse;
import com.viettel.mbccs.data.source.remote.response.SearchProductResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class SearchProductRemoteDataSource implements ISearchProductRemoteDataSource {

    public volatile static SearchProductRemoteDataSource instance;

    public SearchProductRemoteDataSource() {

    }

    @Override
    public Observable<SearchProductResponse> searchProduct(DataRequest<SearchProductRequest> request) {
        return RequestHelper.getRequest()
                .searchProduct(request)
                .flatMap(SchedulerUtils.<SearchProductResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<SearchProductResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<SearchAdvancedProductResponse> searchAdvancedProduct(DataRequest<SearchAdvancedProductRequest> request) {
        return RequestHelper.getRequest()
                .searchAdvancedProduct(request)
                .flatMap(SchedulerUtils.<SearchAdvancedProductResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<SearchAdvancedProductResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetProductSearchResponse> getProductSearch(DataRequest<GetProductSearchRequest> request) {
        return RequestHelper.getRequest()
                .getProductSearch(request)
                .flatMap(SchedulerUtils.<GetProductSearchResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetProductSearchResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetProductInfoResponse> getProductInfo(DataRequest<GetProductInfoRequest> request) {
        return RequestHelper.getRequest()
                .getProductInfo(request)
                .flatMap(SchedulerUtils.<GetProductInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetProductInfoResponse>applyAsyncSchedulers());
    }

    public static SearchProductRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new SearchProductRemoteDataSource();
        }
        return instance;
    }
}
