package com.viettel.mbccs.screen.nvtrahangcaptren.list;

import android.content.Context;
import android.databinding.ObservableField;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;

/**
 * Created by eo_cuong on 6/4/17.
 */

public class NVTraHangCapTrenPresenter implements NVTraHangCapTrenContract.Presenter {

    private Context mContext;
    private NVTraHangCapTrenContract.ViewModel mViewModel;
    public ObservableField<String> textSummany;
    private ArrayAdapter<String> mAdapter;

    public NVTraHangCapTrenPresenter(Context context,
            NVTraHangCapTrenContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        init();
    }

    private void init() {
        mAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public void onCancel() {

    }

    public ArrayAdapter<String> getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ArrayAdapter<String> adapter) {
        mAdapter = adapter;
    }
}
