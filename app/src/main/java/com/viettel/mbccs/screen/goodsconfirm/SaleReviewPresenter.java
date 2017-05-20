package com.viettel.mbccs.screen.goodsconfirm;

import android.app.Activity;
import android.content.Context;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.screen.goodsconfirm.adapter.ModelSaleConfirmAdapter;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class SaleReviewPresenter implements SaleReviewContract.Presenter {

    private Context mContext;
    private SaleReviewContract.ViewModel mViewModel;
    private List<StockSerial> mStockSerials;
    private ModelSaleConfirmAdapter mAdapter;

    public SaleReviewPresenter(Context context, SaleReviewContract.ViewModel viewModel,
            List<StockSerial> stockSerials) {
        mContext = context;
        mViewModel = viewModel;
        mStockSerials = stockSerials;
        init();
    }

    private void init() {
        mAdapter = new ModelSaleConfirmAdapter(mContext, mStockSerials);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public void onConfirm() {
        mViewModel.onConfrirm(mStockSerials);
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
