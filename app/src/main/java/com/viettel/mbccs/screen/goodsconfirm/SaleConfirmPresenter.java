package com.viettel.mbccs.screen.goodsconfirm;

import android.app.Activity;
import android.content.Context;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.screen.goodsconfirm.adapter.ModelSaleConfirmAdapter;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class SaleConfirmPresenter implements SaleActivityContract.Presenter {

    private Context mContext;
    private SaleActivityContract.ViewModel mViewModel;
    private List<ModelSale> mGoodItems;
    private ModelSaleConfirmAdapter mAdapter;

    public SaleConfirmPresenter(Context context, SaleActivityContract.ViewModel viewModel,
            List<ModelSale> goodItems) {
        mContext = context;
        mViewModel = viewModel;
        mGoodItems = goodItems;
        init();
    }

    private void init() {
        mAdapter = new ModelSaleConfirmAdapter(mContext, mGoodItems);
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

    public ModelSaleConfirmAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ModelSaleConfirmAdapter adapter) {
        mAdapter = adapter;
    }
}
