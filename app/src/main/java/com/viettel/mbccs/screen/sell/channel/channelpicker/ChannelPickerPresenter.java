package com.viettel.mbccs.screen.sell.channel.channelpicker;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseSearchListPickerPresenter;
import com.viettel.mbccs.data.model.ChannelInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class ChannelPickerPresenter extends BaseSearchListPickerPresenter<ChannelInfo>
        implements ChannelPickerContract.Presenter, ChannelAdapter.onChannelListener {

    private List<ChannelInfo> mChannelFilter;
    private Handler handler = new Handler();

    public ChannelPickerPresenter(Context context, ChannelPickerContract.ViewModel viewModel,
            List<ChannelInfo> salePrograms) {
        super(context, viewModel);
        listData.addAll(salePrograms);
        listData.remove(0);
        mChannelFilter.addAll(listData);
    }

    @Override
    public void doSearch() {

    }

    @Override
    public void onSearchSuccess() {

    }

    @Override
    public void onSearchFail() {

    }

    @Override
    public String getSearchHint() {
        return null;
    }

    @Override
    public String getToolbarTitle() {
        return mContext.getString(R.string.choose_channel);
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }

    @Override
    protected RecyclerView.Adapter getListAdapter() {
        if (mChannelFilter == null)
            mChannelFilter = new ArrayList<>();
        ChannelAdapter adapter = new ChannelAdapter(mChannelFilter);
        adapter.setOnChannelListener(this);
        return adapter;
    }

    @Override
    public String getItemCountString() {
        return null;
    }

    Runnable filter = new Runnable() {
        @Override
        public void run() {
            mChannelFilter.clear();
            String searchKey = text.get();
            for (ChannelInfo item : listData) {
                if (item.getChannelName().toLowerCase().contains(searchKey.toLowerCase())) {
                    mChannelFilter.add(item);
                }
            }
            adapter.get().notifyDataSetChanged();
        }
    };

    @Override
    public void onTextChange(String s) {
        handler.removeCallbacks(filter);
        handler.postDelayed(filter, 500);
    }

    @Override
    public void onItemClick(ChannelInfo channelInfo) {
        mViewModel.onItemClicked(channelInfo);
    }
}
