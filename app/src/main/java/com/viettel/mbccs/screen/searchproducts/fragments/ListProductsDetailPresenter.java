package com.viettel.mbccs.screen.searchproducts.fragments;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.source.SellAnyPayRepository;
import com.viettel.mbccs.screen.searchproducts.adapters.ProductsAdapter;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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

    private ProductsAdapter productAdapter;

    private Context context;
    private ListProductsDetailContract.ViewModel viewModel;

    private SellAnyPayRepository anyPayRepository;

    public ListProductsDetailPresenter(Context context, final ListProductsDetailContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initListeners();
        initData();
    }

    private void initData() {
        try {
            anyPayRepository = SellAnyPayRepository.getInstance();

            manufacturer = new ObservableField<>();
            screenSize = new ObservableField<>();
            camera = new ObservableField<>();
            priceRange = new ObservableField<>();
            model = new ObservableField<>();
            feature = new ObservableField<>();
            filterText = new ObservableField<>();

            isCollapse = new ObservableField<>(true);

            manufacturerList = new ArrayList<>();
            screenSizeList = new ArrayList<>();
            cameraList = new ArrayList<>();
            priceRangeList = new ArrayList<>();
            modelList = new ArrayList<>();
            featureList = new ArrayList<>();

            //TODO minhnx test
            List<ModelSale> items = new ArrayList<>();
            ModelSale item = new ModelSale();
            item.setStockModelName("Demo 1");
            item.setPrice(3000);
            items.add(item);

            item = new ModelSale();
            item.setStockModelName("Demo 2");
            item.setPrice(3000);
            items.add(item);

            item = new ModelSale();
            item.setStockModelName("Demo 3");
            item.setPrice(3000);
            items.add(item);

            item = new ModelSale();
            item.setStockModelName("Demo 4");
            item.setPrice(3000);
            items.add(item);

            item = new ModelSale();
            item.setStockModelName("Demo 5");
            item.setPrice(3000);
            items.add(item);

            item = new ModelSale();
            item.setStockModelName("Demo 6");
            item.setPrice(3000);
            items.add(item);

            productAdapter = new ProductsAdapter(context, items);
            productAdapter.notifyDataSetChanged();
            productAdapter.setOnItemClickListener(new ProductsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ModelSale item) {
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

    private void showProductDetail(ModelSale item) {
        try {
            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.ITEM_LIST, GsonUtils.Object2String(item));

            viewModel.changeToDetailScreen(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void search() {

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
