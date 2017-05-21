package com.viettel.mbccs.screen.sell.retail.sellprogrampicker;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.os.Handler;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.screen.sell.retail.sellprogrampicker.adapter.SellProgramAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/16/17.
 */

public class SaleProgramPresenter
        implements SaleProgramContract.Presenter, SellProgramAdapter.OnSellProgramListener {

    private Context mContext;
    private SaleProgramContract.ViewModel mViewModel;
    public ObservableField<String> text;
    private SellProgramAdapter mAdapter;
    private List<SaleProgram> mSalePrograms = new ArrayList<>();
    private List<SaleProgram> mSaleProgramFilter = new ArrayList<>();
    private Handler handler = new Handler();

    public SaleProgramPresenter(Context context, SaleProgramContract.ViewModel viewModel,
            List<SaleProgram> salePrograms) {
        mContext = context;
        mViewModel = viewModel;
        mSalePrograms.addAll(salePrograms);
        mSaleProgramFilter.addAll(mSalePrograms);
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
            mSaleProgramFilter.clear();
            String searchKey = text.get();
            for (SaleProgram item : mSalePrograms) {
                if (item.getName().toLowerCase().contains(searchKey.toLowerCase())) {
                    mSaleProgramFilter.add(item);
                }
            }
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void init() {
        text = new ObservableField<String>() ;
        mAdapter = new SellProgramAdapter(mSaleProgramFilter);
        mAdapter.setOnSellProgramListener(this);
    }

    @Override
    public void onTextChange(String s) {
        handler.removeCallbacks(filter);
        handler.postDelayed(filter, 500);
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
    public void onItemClick(SaleProgram sellProgram) {
        mViewModel.onPickSellProgram(sellProgram);
    }
}
