package com.viettel.mbccs.base.searchlistview;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import com.viettel.mbccs.R;
import com.viettel.mbccs.widget.SpacesItemDecoration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public abstract class BaseSearchListViewPresenter<T>
        implements BaseSearchListViewContract.Presenter {

    protected Context mContext;

    protected BaseSearchListViewContract.ViewModel mViewModel;

    protected List<T> listData;

    public ObservableField<RecyclerView.Adapter> adapter = new ObservableField<>();

    public ObservableBoolean isEmpty = new ObservableBoolean();

    public ObservableBoolean isExpand = new ObservableBoolean();

    public ObservableInt itemCount = new ObservableInt();

    public BaseSearchListViewPresenter(Context context,
            BaseSearchListViewContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        listData = new ArrayList<>();
        adapter.set(getListAdapter());
        adapter.get().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                itemCount.set(listData.size());
                isEmpty.set(listData.isEmpty());
            }
        });
        isExpand.set(true);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public abstract void doSearch();

    public abstract void onSearchSuccess();

    public abstract void onSearchFail();

    public abstract String getSearchHint();

    public abstract String getToolbarTitle();

    public abstract void onBackPressed();

    public int getSearchTextColor() {
        return 0;
    }

    public float getSearchTextSize() {
        return 0;
    }

    protected abstract RecyclerView.Adapter getListAdapter();

    public abstract void onAddClick();

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new SpacesItemDecoration((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                mContext.getResources().getDimension(R.dimen.dp_6),
                mContext.getResources().getDisplayMetrics()));
    }

    public void toggleSearchForm() {
        isExpand.set(!isExpand.get());
    }
}
