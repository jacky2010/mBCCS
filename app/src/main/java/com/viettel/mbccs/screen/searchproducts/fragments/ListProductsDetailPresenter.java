package com.viettel.mbccs.screen.searchproducts.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.model.SearchProduct;
import com.viettel.mbccs.data.source.SearchProductRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetProductSearchRequest;
import com.viettel.mbccs.data.source.remote.request.SearchAdvancedProductRequest;
import com.viettel.mbccs.data.source.remote.request.SearchProductRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetProductSearchResponse;
import com.viettel.mbccs.data.source.remote.response.SearchAdvancedProductResponse;
import com.viettel.mbccs.data.source.remote.response.SearchProductResponse;
import com.viettel.mbccs.screen.searchproducts.adapters.ProductsAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minhnx on 5/19/17.
 */

public class ListProductsDetailPresenter implements ListProductsDetailContract.Presenter {

    private DecimalFormat nf = new DecimalFormat("###");

    public ObservableField<String> manufacturer;
    public ObservableField<String> screenSize;
    public ObservableField<String> camera;
    public ObservableField<String> priceRange;
    public ObservableField<String> model;
    public ObservableField<String> feature;
    public ObservableField<String> filterText;
    public ObservableBoolean searchFound;

    public ObservableField<Boolean> isCollapse;

    private KeyValue manufacturerObj;
    private KeyValue screenSizeObj;
    private KeyValue cameraObj;
    private KeyValue priceRangeObj;
    private KeyValue modelObj;
    private KeyValue featureObj;

    private List<KeyValue> manufacturerList;
    private List<KeyValue> screenSizeList;
    private List<KeyValue> cameraList;
    private List<KeyValue> priceRangeList;
    private List<KeyValue> modelList;
    private List<KeyValue> featureList;
    private List<SearchProduct> productList;

    private ProductsAdapter productAdapter;

    private Context context;
    private ListProductsDetailContract.ViewModel viewModel;

    private SearchProductRepository searchProductRepository;
    private UserRepository userRepository;
    private CompositeSubscription mSubscriptions;

    public ListProductsDetailPresenter(Context context, final ListProductsDetailContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initListeners();
        initData();
    }

    private void initData() {
        try {
            searchProductRepository = SearchProductRepository.getInstance();
            userRepository = UserRepository.getInstance();
            mSubscriptions = new CompositeSubscription();

            manufacturer = new ObservableField<>();
            screenSize = new ObservableField<>();
            camera = new ObservableField<>();
            priceRange = new ObservableField<>();
            model = new ObservableField<>();
            feature = new ObservableField<>();
            filterText = new ObservableField<>();

            isCollapse = new ObservableField<>(true);
            searchFound = new ObservableBoolean(true);

            manufacturerList = new ArrayList<>();
            screenSizeList = new ArrayList<>();
            cameraList = new ArrayList<>();
            priceRangeList = new ArrayList<>();
            modelList = new ArrayList<>();
            featureList = new ArrayList<>();
            productList = new ArrayList<>();

            loadDefaultData();

//            //TODO minhnx test
//            SearchProduct item = new SearchProduct();
//            item.setName("Demo 1");
//            item.setPrice(3000d);
//            productList.add(item);
//
//            item = new SearchProduct();
//            item.setName("Demo 2");
//            item.setPrice(3000d);
//            productList.add(item);
//
//            item = new SearchProduct();
//            item.setName("Demo 3");
//            item.setPrice(3000d);
//            productList.add(item);
//
//            item = new SearchProduct();
//            item.setName("Demo 4");
//            item.setPrice(3000d);
//            productList.add(item);
//
//            item = new SearchProduct();
//            item.setName("Demo 5");
//            item.setPrice(3000d);
//            productList.add(item);
//
//            item = new SearchProduct();
//            item.setName("Demo 6");
//            item.setPrice(3000d);
//            productList.add(item);

            productAdapter = new ProductsAdapter(context, productList);
            productAdapter.notifyDataSetChanged();

            productAdapter.setOnItemClickListener(new ProductsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(SearchProduct item) {
                    showProductDetail(item);
                }

                @Override
                public void onItemFocus() {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProductDetail(SearchProduct item) {
        try {
            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.ITEM_LIST, GsonUtils.Object2String(item));

            viewModel.changeToDetailScreen(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDefaultData() {
        try {
            viewModel.showLoading();

            DataRequest<GetProductSearchRequest> baseRequest = new DataRequest<>();
            baseRequest.setWsCode(WsCode.GetProductSearch);
            GetProductSearchRequest request = new GetProductSearchRequest();
            baseRequest.setWsRequest(request);

            Subscription subscription =
                    searchProductRepository.getProductSearch(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetProductSearchResponse>() {
                                @Override
                                public void onSuccess(GetProductSearchResponse object) {
                                    try {
                                        fillData(object);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
                                    DialogUtils.showDialog(context, null, error.getMessage(),
                                            null);

                                }

                                @Override
                                public void onRequestFinish() {
                                    super.onRequestFinish();
                                    viewModel.hideLoading();
                                }
                            });

            mSubscriptions.add(subscription);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillData(GetProductSearchResponse response) throws Exception {
        manufacturerList = response.getListManufacturers();
        screenSizeList = response.getListScreenSizes();
        cameraList = response.getListCameras();
        priceRangeList = response.getListPriceRanges();
        modelList = response.getListModels();
        featureList = response.getListFeatures();
    }

    private void initListeners() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void basicSearch() {
        try {

            if (filterText.get() == null || TextUtils.isEmpty(filterText.get()))
                return;

            viewModel.showLoading();

            DataRequest<SearchProductRequest> baseRequest = new DataRequest<>();
            baseRequest.setWsCode(WsCode.SearchProduct);
            SearchProductRequest request = new SearchProductRequest();
            request.setLanguage(userRepository.getLanguageFromSharePrefs());
            request.setProduct(filterText.get().trim());
            baseRequest.setWsRequest(request);

            Subscription subscription =
                    searchProductRepository.searchProduct(baseRequest)
                            .subscribe(new MBCCSSubscribe<SearchProductResponse>() {
                                @Override
                                public void onSuccess(SearchProductResponse object) {
                                    try {

                                        productList.clear();

                                        if (object.getListProducts() != null) {
                                            for (SearchProduct item : object.getListProducts()) {

                                                productList.add(item);
                                            }
                                        }

                                        productAdapter.setItems(productList);
                                        productAdapter.notifyDataSetChanged();

                                        if (productList.size() > 0)
                                            searchFound.set(true);
                                        else
                                            searchFound.set(false);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
                                    DialogUtils.showDialog(context, null, error.getMessage(),

                                            null);
                                }

                                @Override
                                public void onRequestFinish() {
                                    super.onRequestFinish();
                                    viewModel.hideLoading();
                                }
                            });

            mSubscriptions.add(subscription);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void advancedSearch() {
        try {

            if ((manufacturer.get() == null || TextUtils.isEmpty(manufacturer.get()))
                    && (screenSize.get() == null || TextUtils.isEmpty(screenSize.get()))
                    && (camera.get() == null || TextUtils.isEmpty(camera.get()))
                    && (priceRange.get() == null || TextUtils.isEmpty(priceRange.get()))
                    && (model.get() == null || TextUtils.isEmpty(model.get()))
                    && (feature.get() == null || TextUtils.isEmpty(feature.get()))) {
                viewModel.showError(context.getString(R.string.search_products_error_msg_empty_search_advanced));
                return;
            }

            isCollapse.set(true);

            viewModel.showLoading();

            DataRequest<SearchAdvancedProductRequest> baseRequest = new DataRequest<>();
            baseRequest.setWsCode(WsCode.SearchAdvancedProduct);
            SearchAdvancedProductRequest request = new SearchAdvancedProductRequest();
            request.setCamera(cameraObj != null ? cameraObj.getKey() : null);
            request.setFeature(featureObj != null ? featureObj.getKey() : null);
            request.setManufacturer(manufacturerObj != null ? manufacturerObj.getKey() : null);
            request.setModel(modelObj != null ? modelObj.getKey() : null);
            request.setPriceRange(priceRangeObj != null ? priceRangeObj.getKey() : null);
            request.setScreenSize(screenSizeObj != null ? screenSizeObj.getKey() : null);
            request.setLanguage(userRepository.getLanguageFromSharePrefs());

            baseRequest.setWsRequest(request);

            Subscription subscription =
                    searchProductRepository.searchAdvancedProduct(baseRequest)
                            .subscribe(new MBCCSSubscribe<SearchAdvancedProductResponse>() {
                                @Override
                                public void onSuccess(SearchAdvancedProductResponse object) {
                                    try {

                                        productList.clear();

                                        if (object.getListProducts() != null) {
                                            for (SearchProduct item : object.getListProducts()) {

                                                productList.add(item);
                                            }
                                        }

                                        productAdapter.setItems(productList);
                                        productAdapter.notifyDataSetChanged();

                                        if (productList.size() > 0)
                                            searchFound.set(true);
                                        else
                                            searchFound.set(false);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
                                    DialogUtils.showDialog(context, null, error.getMessage(),

                                            null);
                                }

                                @Override
                                public void onRequestFinish() {
                                    super.onRequestFinish();
                                    viewModel.hideLoading();
                                }
                            });

            mSubscriptions.add(subscription);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseManufacturer() {
        try {
            viewModel.onChooseManufacturer(manufacturerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseScreenSize() {
        try {
            viewModel.onChooseScreenSize(screenSizeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseCamera() {
        try {
            viewModel.onChooseCamera(cameraList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void choosePriceRange() {
        try {
            viewModel.onChoosePriceRange(priceRangeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseModel() {
        try {
            viewModel.onChooseModel(modelList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseFeature() {
        try {
            viewModel.onChooseFeature(featureList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGetManufacturerSuccess(KeyValue item) {
        if (item == null) {
            return;
        }
        manufacturerObj = item;
        manufacturer.set(item.getValue());
    }

    @Override
    public void onGetScreenSizeSuccess(KeyValue item) {
        if (item == null) {
            return;
        }
        screenSizeObj = item;
        screenSize.set(item.getValue());
    }

    @Override
    public void onGetCameraSuccess(KeyValue item) {
        if (item == null) {
            return;
        }
        cameraObj = item;
        camera.set(item.getValue());
    }

    @Override
    public void onGetPriceRangeSuccess(KeyValue item) {
        if (item == null) {
            return;
        }
        priceRangeObj = item;
        priceRange.set(item.getValue());
    }

    @Override
    public void onGetModelSuccess(KeyValue item) {
        if (item == null) {
            return;
        }
        modelObj = item;
        model.set(item.getValue());
    }

    @Override
    public void onGetFeatureSuccess(KeyValue item) {
        if (item == null) {
            return;
        }
        featureObj = item;
        feature.set(item.getValue());
    }

    public ProductsAdapter getProductAdapter() {
        return productAdapter;
    }

    public void toogleExpand() {
        isCollapse.set(!isCollapse.get());
        changeSearchFilter();
    }

    private void changeSearchFilter() {
    }
}
