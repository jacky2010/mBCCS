package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.source.local.ISearchProductLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.SearchProductLocalDataSource;
import com.viettel.mbccs.data.source.remote.ISearchProductRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.SearchProductRemoteDataSource;
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
 * Created by eo_cuong on 5/10/17.
 */

public class SearchProductRepository implements ISearchProductLocalDataSource, ISearchProductRemoteDataSource {

    private volatile static SearchProductRepository instance;
    private SearchProductLocalDataSource localDataSource;
    private SearchProductRemoteDataSource remoteDataSource;

    public SearchProductRepository(SearchProductLocalDataSource localDataSource,
                                   SearchProductRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static SearchProductRepository getInstance() {
        if (instance == null) {
            instance = new SearchProductRepository(SearchProductLocalDataSource.getInstance(),
                    SearchProductRemoteDataSource.getInstance());
        }
        return instance;
    }


    @Override
    public Observable<SearchProductResponse> searchProduct(DataRequest<SearchProductRequest> request) {
        return remoteDataSource.searchProduct(request);
    }

    @Override
    public Observable<SearchAdvancedProductResponse> searchAdvancedProduct(DataRequest<SearchAdvancedProductRequest> request) {
        return remoteDataSource.searchAdvancedProduct(request);
    }

    @Override
    public Observable<GetProductSearchResponse> getProductSearch(DataRequest<GetProductSearchRequest> request) {
        return remoteDataSource.getProductSearch(request);
    }

    @Override
    public Observable<GetProductInfoResponse> getProductInfo(DataRequest<GetProductInfoRequest> request) {
        return remoteDataSource.getProductInfo(request);
    }
}
