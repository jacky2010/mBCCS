package com.viettel.mbccs.screen.sellretail.sellprogrampicker;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import com.viettel.mbccs.data.model.SellProgram;
import com.viettel.mbccs.screen.sellretail.sellprogrampicker.adapter.SellProgramAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/16/17.
 */

public class SellProgramPresenter implements SellProgramContract.Presenter ,SellProgramAdapter.OnSellProgramListener {

    private Context mContext;
    private SellProgramContract.ViewModel mViewModel;
    public ObservableField<String> text;
    private SellProgramAdapter mAdapter;
    private List<SellProgram> mSellPrograms = new ArrayList<>();

    public SellProgramPresenter(Context context, SellProgramContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        fakeData();
        init();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void init() {
        text = new ObservableField<>();
        mAdapter = new SellProgramAdapter(mSellPrograms);


    }

    private void fakeData() {
        SellProgram sell = new SellProgram(1, "khuyen mai");
        SellProgram sell1 = new SellProgram(1, "khuyen mai 1");
        SellProgram sell2 = new SellProgram(1, "khuyen mai 2");
        SellProgram sell3 = new SellProgram(1, "khuyen mai 3");
        mSellPrograms.add(sell);
        mSellPrograms.add(sell1);
        mSellPrograms.add(sell2);
        mSellPrograms.add(sell3);
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }

    public SellProgramAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(SellProgramAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onItemClick(SellProgram sellProgram) {

    }
}
