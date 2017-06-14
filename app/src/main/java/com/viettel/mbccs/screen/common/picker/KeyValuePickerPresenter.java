package com.viettel.mbccs.screen.common.picker;

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

public class KeyValuePickerPresenter
        implements KeyValuePickerContract.Presenter, KeyValuePickerAdaper.OnItemClickListener {

    private Context mContext;
    private KeyValuePickerContract.ViewModel mViewModel;

    public ObservableField<String> text;
    public ObservableField<String> title;

    private KeyValuePickerAdaper mAdapter;
    private Handler handler = new Handler();
    private List<KeyValue> items = new ArrayList<>();
    private List<KeyValue> filters = new ArrayList<>();

    public KeyValuePickerPresenter(Context context, KeyValuePickerContract.ViewModel viewModel,
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
        text = new ObservableField<>();
        mAdapter = new KeyValuePickerAdaper(filters);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onTextChange(String s) {
        handler.removeCallbacks(filter);
        handler.postDelayed(filter, 500);
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }

    public KeyValuePickerAdaper getAdapter() {
        return mAdapter;
    }

    public void setAdapter(KeyValuePickerAdaper adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onItemClick(KeyValue item) {
        mViewModel.onPickItem(item);
    }

    @Override
    public void setTitle(String s) {
        title = new ObservableField<>(s);
    }
}
