package com.viettel.mbccs.screen.sell.retail.sellprogrampicker;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseSearchListPickerPresenter;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.screen.sell.retail.sellprogrampicker.adapter.SellProgramAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/16/17.
 */

public class SaleProgramPresenter extends BaseSearchListPickerPresenter<SaleProgram>
        implements SaleProgramContract.Presenter, SellProgramAdapter.OnSellProgramListener {

    private List<SaleProgram> mSaleProgramFilter;
    private Handler handler = new Handler();

    public SaleProgramPresenter(Context context, SaleProgramContract.ViewModel viewModel,
                                List<SaleProgram> salePrograms) {
        super(context, viewModel);
        listData.addAll(salePrograms);
        mSaleProgramFilter.addAll(listData);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

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
        return mContext.getString(R.string.sale_program);
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }

    @Override
    protected RecyclerView.Adapter getListAdapter() {
        if (mSaleProgramFilter == null)
            mSaleProgramFilter = new ArrayList<>();
        SellProgramAdapter adapter = new SellProgramAdapter(mSaleProgramFilter);
        adapter.setOnSellProgramListener(this);
        return adapter;
    }

    @Override
    public String getListCount() {
        return null;
    }

    Runnable filter = new Runnable() {
        @Override
        public void run() {
            mSaleProgramFilter.clear();
            String searchKey = text.get();
            for (SaleProgram item : listData) {
                if (item.getName().toLowerCase().contains(searchKey.toLowerCase())) {
                    mSaleProgramFilter.add(item);
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
    public void onItemClick(SaleProgram sellProgram) {
        ((SaleProgramContract.ViewModel) mViewModel).onPickSellProgram(sellProgram);
    }
}
