package com.viettel.mbccs.screen.hotnewscskpp;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;

/**
 * Created by minhnx on 5/19/17.
 */

public class HotNewsCSKPPPresenter implements HotNewsCSKPPContract.Presenter {

    private Context context;
    private HotNewsCSKPPContract.ViewModel viewModel;
    public ObservableField<String> title;

    public HotNewsCSKPPPresenter(Context context, HotNewsCSKPPContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData() {
        try {
            title = new ObservableField<>(context.getString(R.string.hot_news_cs_kpp_title));
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
}
