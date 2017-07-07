package com.viettel.mbccs.base.createorder;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.viettel.mbccs.R;

import com.viettel.mbccs.base.BasePresenter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\vu.viet.anh on 14/06/2017.
 */

public abstract class BaseCreateOrderSuccessPresenter<T>
        implements BaseCreateOrderSuccessContract.Presenter {

    protected Context mContext;

    protected BaseCreateOrderSuccessContract.ViewModel mViewModel;

    protected List<T> mList = new ArrayList<>();

    public ObservableInt itemCount = new ObservableInt();

    public ObservableField<RecyclerView.Adapter> adapter = new ObservableField<>();


    public BaseCreateOrderSuccessPresenter(Context context,
            BaseCreateOrderSuccessContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        adapter.set(getListAdapter());
        adapter.get().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                itemCount.set(mList.size());
            }
        });
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

    public String getItemCountString() {
        return mContext.getString(R.string.activity_create_order_success_danh_sach_mat_hang, itemCount.get());
    }

    public abstract RecyclerView.Adapter getListAdapter();

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
    }

    public void onBackPressed() {
        mViewModel.onBackPressed();
    }
}
