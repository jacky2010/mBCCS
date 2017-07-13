package com.viettel.mbccs.screen.viewproduct;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.model.SearchProduct;
import com.viettel.mbccs.screen.searchproducts.adapters.AvailableColorsListAdapter;
import com.viettel.mbccs.screen.viewproduct.adapter.ProductImagePagerAdapter;
import com.viettel.mbccs.screen.viewproduct.adapter.ViewProductDetailFragmentAdapter;
import com.viettel.mbccs.utils.Common;

import java.util.ArrayList;
import java.util.List;

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

    private String description;
    private List<KeyValue> features;
    private List<KeyValue> comments;
    private List<String> colorIds;
    private List<KeyValue> productImages;

    public ViewProductDetailPresenter(Context context, ViewProductDetailContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData() {
        try {
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
            productName.set(item.getName());

            description = "It means people without close family or business relationships in the US could be denied visas and barred entry.\n" +
                    "Grandparents, aunts, uncles, nephews and nieces are not considered to be \"bona fide\" relations.\n" +
                    "The rules apply to people in Iran, Libya, Syria, Somalia, Sudan and Yemen, as well as all refugees.\n" +
                    "Moments before the ban began at 20:00 Washington time (00:00 GMT), it emerged that the state of Hawaii had asked a federal judge for clarification.";

            features.add(new KeyValue("CPU", "800hz"));
            features.add(new KeyValue("RAM", "3GB"));
            features.add(new KeyValue("Camera", "10MP"));

            comments.add(new KeyValue("1", "It has in the past accused the US government of violating the Supreme Court's instructions by improperly excluding people."));
            comments.add(new KeyValue("2", "Earlier this week, the Supreme Court partially upheld the ban, lifting injunctions that had halted one of the president's key policies."));

            viewProductDetailAdapter.setData(description, features, comments);

            //TODO minhnx modelId?
            productImages.add(new KeyValue("1", "image id 1"));
            productImages.add(new KeyValue("2", ""));
            productImages.add(new KeyValue("3", ""));

            if (item.getColour() != null) {
                String[] colours = item.getColour().split(";");
                if (colours != null) {
                    for (int i = 0; i < colours.length; i++) {
                        colorIds.add(colours[i]);
                    }
                }
            }

            imageAdapter.setItems(productImages);
            availableColorsAdapter.setItems(colorIds);

            price.set(String.format(context.getResources().getString(R.string.price),
                    Common.formatDouble(item.getPrice())));
            availableColorsListAdapter.set(availableColorsAdapter);

            onImagePageChanged(0);//first item
        } catch (Exception e) {
            e.printStackTrace();
        }
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
