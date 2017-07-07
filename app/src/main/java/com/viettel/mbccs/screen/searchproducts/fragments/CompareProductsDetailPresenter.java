package com.viettel.mbccs.screen.searchproducts.fragments;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.data.model.CompareProducts;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.source.SellAnyPayRepository;
import com.viettel.mbccs.screen.searchproducts.adapters.ItemProductFeaturePresenter;
import com.viettel.mbccs.screen.searchproducts.adapters.ProductFeaturesAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class CompareProductsDetailPresenter implements CompareProductsDetailContract.Presenter {

    private DecimalFormat nf = new DecimalFormat("###");

    public ObservableField<ProductFeaturesAdapter> productFeatureAdapter;

    private Context context;
    private CompareProductsDetailContract.ViewModel viewModel;
    private SellAnyPayRepository anyPayRepository;
    private ProductFeaturesAdapter productAdapter;

    private List<CompareProducts> features;

    public CompareProductsDetailPresenter(Context context, final CompareProductsDetailContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initListeners();
        initData();
    }

    private void initData() {
        try {
            anyPayRepository = SellAnyPayRepository.getInstance();

            ModelSale product1 = new ModelSale();
            product1.setPrice(300000);
            product1.setStockModelId(1);
            product1.setStockModelName("Product 1");

            ModelSale product2 = new ModelSale();
            product2.setPrice(400000);
            product2.setStockModelId(1);
            product2.setStockModelName("Product 1");

            features = new ArrayList<>();
            CompareProducts item = new CompareProducts();
            item.setFeature(null);
            item.setProduct1(product1);
            item.setProduct2(product2);
            item.setType(ItemProductFeaturePresenter.ITEM_TYPE_IMAGE);
            features.add(item);

            item = new CompareProducts();
            item.setFeature("Feature 1");
            item.setValue1("3GB");
            item.setValue2("4GB");
            item.setType(ItemProductFeaturePresenter.ITEM_TYPE_TEXT);
            features.add(item);

            item = new CompareProducts();
            item.setFeature("Feature 2");
            item.setValue1("5Hz");
            item.setValue2("4.5Hz");
            item.setType(ItemProductFeaturePresenter.ITEM_TYPE_TEXT);
            features.add(item);

            item = new CompareProducts();
            item.setFeature("Feature 3");
            item.setType(ItemProductFeaturePresenter.ITEM_TYPE_COLOR_LIST);
            features.add(item);

            productAdapter = new ProductFeaturesAdapter(context, features);

            productFeatureAdapter = new ObservableField<>(productAdapter);
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
}
