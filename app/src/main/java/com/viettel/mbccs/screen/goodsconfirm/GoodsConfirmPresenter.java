package com.viettel.mbccs.screen.goodsconfirm;

import android.app.Activity;
import android.content.Context;
import com.viettel.mbccs.data.model.StockItem;
import com.viettel.mbccs.screen.goodsconfirm.adapter.GoodsConfirmAdapter;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class GoodsConfirmPresenter implements GoodsActivityContract.Presenter {

    private Context mContext;
    private GoodsActivityContract.ViewModel mViewModel;
    private List<StockItem> mGoodItems;
    private GoodsConfirmAdapter mAdapter;

    public GoodsConfirmPresenter(Context context, GoodsActivityContract.ViewModel viewModel,
            List<StockItem> goodItems) {
        mContext = context;
        mViewModel = viewModel;
        mGoodItems = goodItems;
        init();
    }

    private void init() {
        mAdapter = new GoodsConfirmAdapter(mContext, mGoodItems);
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

    public GoodsConfirmAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(GoodsConfirmAdapter adapter) {
        mAdapter = adapter;
    }
}
