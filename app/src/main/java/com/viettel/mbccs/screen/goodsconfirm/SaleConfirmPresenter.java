package com.viettel.mbccs.screen.goodsconfirm;

import android.app.Activity;
import android.content.Context;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.screen.goodsconfirm.adapter.StockConfirmAdapter;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class StockConfirmPresenter implements StockActivityContract.Presenter {

    private Context mContext;
    private StockActivityContract.ViewModel mViewModel;
    private List<ModelSale> mGoodItems;
    private StockConfirmAdapter mAdapter;

    public StockConfirmPresenter(Context context, StockActivityContract.ViewModel viewModel,
            List<ModelSale> goodItems) {
        mContext = context;
        mViewModel = viewModel;
        mGoodItems = goodItems;
        init();
    }

    private void init() {
        mAdapter = new StockConfirmAdapter(mContext, mGoodItems);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }

    public StockConfirmAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(StockConfirmAdapter adapter) {
        mAdapter = adapter;
    }
}
