package com.viettel.mbccs.screen.hotnewscskpp.fragments;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.data.model.HotNewsCSKPPItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class ViewHotNewsCSKPPPresenter implements ViewHotNewsCSKPPContract.Presenter {

    private Context context;
    private ViewHotNewsCSKPPContract.ViewModel viewModel;

    public ObservableField<String> content;

    public ViewHotNewsCSKPPPresenter(Context context, final ViewHotNewsCSKPPContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initListeners();
        initData();
    }

    private void initListeners() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {

            content = new ObservableField<>();

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
    public void onNewsContentLoaded(HotNewsCSKPPItem item) {
        try {

            String content = "<h4>" + item.getTitle() + "</h4>" + item.getContent();

            viewModel.showNewsContent(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
