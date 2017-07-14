package com.viettel.mbccs.screen.viewproduct;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.model.SearchProduct;
import com.viettel.mbccs.data.source.SearchProductRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetProductInfoRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetProductInfoResponse;
import com.viettel.mbccs.screen.searchproducts.adapters.AvailableColorsListAdapter;
import com.viettel.mbccs.screen.viewproduct.adapter.ProductImagePagerAdapter;
import com.viettel.mbccs.screen.viewproduct.adapter.ViewProductDetailFragmentAdapter;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minhnx on 7/13/17.
 */

public class ViewProductDetailPresenter implements ViewProductDetailContract.Presenter {

    private Context context;
    private ViewProductDetailContract.ViewModel viewModel;

    public ObservableField<String> title;
    public ObservableField<String> productName;
    public ObservableField<String> price;
    public ObservableField<ViewProductDetailFragmentAdapter> fragmentAdapter;
    public ObservableField<AvailableColorsListAdapter> availableColorsListAdapter;
    public ObservableField<ProductImagePagerAdapter> imageListAdapter;

    private ViewProductDetailFragmentAdapter viewProductDetailAdapter;
    private AvailableColorsListAdapter availableColorsAdapter;
    private ProductImagePagerAdapter imageAdapter;

    private List<KeyValue> features;
    private List<KeyValue> comments;
    private List<String> colorIds;
    private List<KeyValue> productImages;

    private SearchProductRepository searchProductRepository;
    private CompositeSubscription mSubscriptions;

    public ViewProductDetailPresenter(Context context, ViewProductDetailContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData() {
        try {
            searchProductRepository = SearchProductRepository.getInstance();
            mSubscriptions = new CompositeSubscription();

            title = new ObservableField<>(context.getString(R.string.view_product_detail_title));
            fragmentAdapter = new ObservableField<>();
            imageListAdapter = new ObservableField<>();
            productName = new ObservableField<>();
            price = new ObservableField<>();

            features = new ArrayList<>();
            comments = new ArrayList<>();
            productImages = new ArrayList<>();
            colorIds = new ArrayList<>();

            availableColorsAdapter = new AvailableColorsListAdapter(context, colorIds);
            availableColorsListAdapter = new ObservableField<>(availableColorsAdapter);

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

    public void onCancel() {
        viewModel.onCancel();
    }

    @Override
    public void displayProductDetail(SearchProduct item) {
        try {
            viewModel.showLoading();

            DataRequest<GetProductInfoRequest> baseRequest = new DataRequest<>();
            baseRequest.setWsCode(WsCode.GetProductInfo);
            GetProductInfoRequest request = new GetProductInfoRequest();
            request.setProductId(item.getProductId());
            baseRequest.setWsRequest(request);

            Subscription subscription =
                    searchProductRepository.getProductInfo(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetProductInfoResponse>() {
                                @Override
                                public void onSuccess(GetProductInfoResponse object) {
                                    try {
                                        fillData(object.getProduct());
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

    private void fillData(SearchProduct item) throws Exception {
        productName.set(item.getName());

        features.add(new KeyValue(context.getString(R.string.search_products_detail_label_cpu), item.getCpu()));
        features.add(new KeyValue(context.getString(R.string.search_products_detail_label_ram), item.getRam()));
        features.add(new KeyValue(context.getString(R.string.search_products_detail_label_camera), item.getCamera()));
        features.add(new KeyValue(context.getString(R.string.search_products_detail_label_screen), item.getScreen()));
        features.add(new KeyValue(context.getString(R.string.search_products_detail_label_battery), item.getBattery()));
        features.add(new KeyValue(context.getString(R.string.search_products_detail_label_manufacturer), item.getManufacturerName()));
        features.add(new KeyValue(context.getString(R.string.search_products_detail_label_features), item.getFeatures()));

        if (item.getComments() != null) {
            comments.addAll(item.getComments());
        }

        viewProductDetailAdapter.setData(item.getDescription(), features, comments);

        if (item.getProductImages() != null && item.getProductImages().size() > 0) {
            for (KeyValue image : item.getProductImages()) {
                productImages.add(new KeyValue(image.getValue(), image.getValue()));
            }
        } else {
            productImages.add(new KeyValue("-1", "-1"));
        }

//            if (item.getColour() != null) {
//                String[] colours = item.getColour().split(";");
//                if (colours != null) {
//                    for (int i = 0; i < colours.length; i++) {
//                        colorIds.add(colours[i]);
//                    }
//                }
//            }
        //based on product images
        if (item.getProductImages() != null) {
            for (KeyValue color : item.getProductImages()) {
                colorIds.add(color.getKey());//key = color (hex)
            }
        }

        imageAdapter.setItems(productImages);
        availableColorsAdapter.setItems(colorIds);

        price.set(String.format(context.getResources().getString(R.string.price),
                Common.formatDouble(item.getPrice())));
        availableColorsListAdapter.set(availableColorsAdapter);

        onImagePageChanged(0);//first item

        viewProductDetailAdapter.notifyDataSetChanged();
    }

    public void setViewProductDetailFragmentAdapter(ViewProductDetailFragmentAdapter adapter) {
        fragmentAdapter.set(adapter);
        viewProductDetailAdapter = adapter;
    }

    public void setProductImagePagerAdapter(ProductImagePagerAdapter adapter) {
        imageListAdapter.set(adapter);
        imageAdapter = adapter;
    }

    @Override
    public void onImagePageChanged(int index) {
        try {

            if (index > -1)
                availableColorsAdapter.setSelectedIndex(index);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
