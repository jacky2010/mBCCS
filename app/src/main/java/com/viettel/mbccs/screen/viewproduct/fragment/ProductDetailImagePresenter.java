package com.viettel.mbccs.screen.viewproduct.fragment;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.utils.FileUtils;

import java.io.File;

/**
 * Created by minhnx on 5/19/17.
 */

public class ProductDetailImagePresenter implements ProductDetailImageContract.Presenter {

    private Context mContext;

    public ObservableField<String> urlImage;

    public ProductDetailImagePresenter(Context context, final ProductDetailImageContract.ViewModel viewModel) {
        mContext = context;

        initListeners();
        initData();
    }

    private void initData() {
        try {

            urlImage = new ObservableField<>();

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
    public void showImage(KeyValue item) {
        File file = FileUtils.getImageFileByIdName(mContext, item.getKey());
        if (file != null && file.exists()) {
            urlImage.set(file.getAbsolutePath());
        } else
            urlImage.set(null);
    }
}
