package com.viettel.mbccs.screen.sell.channel.channelpicker;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.os.Handler;
import com.viettel.mbccs.data.model.ChannelInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class ChannelPickerPresenter
        implements ChannelPickerContract.Presenter, ChannelAdaper.onChannelListener {
    private Context mContext;
    private ChannelPickerContract.ViewModel mViewModel;
    public ObservableField<String> text;
    private ChannelAdaper mAdapter;
    private List<ChannelInfo> mChannelInfos = new ArrayList<>();
    private List<ChannelInfo> mChannelFilter = new ArrayList<>();
    private Handler handler = new Handler();

    public ChannelPickerPresenter(Context context, ChannelPickerContract.ViewModel viewModel,
            List<ChannelInfo> salePrograms) {
        mContext = context;
        mViewModel = viewModel;
        mChannelInfos.addAll(salePrograms);
        mChannelFilter.addAll(mChannelInfos);
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
            mChannelFilter.clear();
            String searchKey = text.get();
            for (ChannelInfo item : mChannelInfos) {
                if (item.getChannelName().toLowerCase().contains(searchKey.toLowerCase())) {
                    mChannelFilter.add(item);
                }
            }
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void init() {
        text = new ObservableField<String>();
        mAdapter = new ChannelAdaper(mChannelFilter);
        mAdapter.setOnChannelListener(this);
    }

    @Override
    public void onTextChange(String s) {
        handler.removeCallbacks(filter);
        handler.postDelayed(filter, 500);
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }

    public ChannelAdaper getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ChannelAdaper adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onItemClick(ChannelInfo channelInfo) {
        mViewModel.onPickChannelPick(channelInfo);
    }
}
