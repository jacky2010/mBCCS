package com.viettel.mbccs.base.createordersuccess;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.data.model.StockTotal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\vu.viet.anh on 14/06/2017.
 */

public abstract class BaseCreateOrderSuccessPresenter
        implements BaseCreateOrderSuccessContract.Presenter {

    protected Context mContext;

    protected BaseCreateOrderSuccessContract.ViewModel mViewModel;

    protected List<StockTotal> mList = new ArrayList<>();

    public ObservableInt itemCount = new ObservableInt();

    public BaseCreateOrderSuccessPresenter(Context context,
            BaseCreateOrderSuccessContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public abstract String getToolbarTitle();

    public abstract String getOrderCode();

    public abstract String getImportWarehouseCode();

    public abstract String getExportWarehouseCode();

    public abstract String getCreatedDate();

    public abstract RecyclerView.Adapter getAdapter();

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
    }

    public void onBackPressed() {
        mViewModel.onBackPressed();
    }
}
