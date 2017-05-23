package com.viettel.mbccs.screen.branches.btspicker;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.os.Handler;

import com.viettel.mbccs.data.model.KeyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class BTSPickerPresenter
        implements BTSPickerContract.Presenter, BTSPickerAdaper.OnBTSClickListener {
    private Context mContext;
    private BTSPickerContract.ViewModel mViewModel;
    public ObservableField<String> text;
    private BTSPickerAdaper mAdapter;
    private List<KeyValue> items = new ArrayList<>();
    private List<KeyValue> filters = new ArrayList<>();
    private Handler handler = new Handler();

    public BTSPickerPresenter(Context context, BTSPickerContract.ViewModel viewModel,
                              List<KeyValue> items) {
        mContext = context;
        mViewModel = viewModel;
        this.items.addAll(items);
        filters.addAll(this.items);
        init();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    Runnable filter = new Runnable() {
        @Override
        public void run() {
            filters.clear();
            String searchKey = text.get();
            for (KeyValue item : items) {
                if (item.getValue().toLowerCase().contains(searchKey.toLowerCase())) {
                    filters.add(item);
                }
            }
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void init() {
        text = new ObservableField<String>();
        mAdapter = new BTSPickerAdaper(filters);
        mAdapter.setOnBTSClickListener(this);
    }

    @Override
    public void onTextChange(String s) {
        handler.removeCallbacks(filter);
        handler.postDelayed(filter, 500);
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }

    public BTSPickerAdaper getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BTSPickerAdaper adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onItemClick(KeyValue item) {
        mViewModel.onPickBTS(item);
    }
}
