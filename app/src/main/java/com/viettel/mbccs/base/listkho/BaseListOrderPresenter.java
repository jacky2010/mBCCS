package com.viettel.mbccs.base.listkho;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewPresenter;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.screen.nhapkhocapduoi.adapters.ListOrderAdapter;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.SpinnerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class BaseListOrderPresenter
        extends BaseSearchListViewPresenter<StockTrans, BaseListOrderContract.ViewModel>
        implements BaseListOrderContract.Presenter {

    public ObservableField<SpinnerAdapter<String>> warehouseAdapter = new ObservableField<>();

    public ObservableField<SpinnerAdapter<String>> statusAdapter = new ObservableField<>();

    public ObservableField<String> filterText = new ObservableField<>();

    public ObservableField<String> itemCountStringText = new ObservableField<>();

    public ObservableField<String> wareHouseTitle = new ObservableField<>();

    public ListOrderAdapter mAdaper;

    protected List<String> mWareHouseData = new ArrayList<>();
    protected List<String> mStatusList = new ArrayList<>();
    protected int positionWareHouse = 0;
    protected int positionStatus = 0;

    public BaseListOrderPresenter(Context context, BaseListOrderContract.ViewModel viewModel) {
        super(context, viewModel);
        // TODO: 06/06/2017 Phân quyền

        SpinnerAdapter<String> adapter = new SpinnerAdapter<>(mContext, mWareHouseData);
        adapter.setOnItemSelectedListener(getWareHouseItemSelectedListener());
        warehouseAdapter.set(adapter);

        SpinnerAdapter<String> adapter2 = new SpinnerAdapter<>(mContext, mStatusList);
        adapter2.setOnItemSelectedListener(getStatusItemSelectedListener());
        statusAdapter.set(adapter2);
        wareHouseTitle.set(((BaseListOrderActivity) mContext).getWareHouseTitle());
        updateTextFilter();
    }

    @Override
    public void onAdapterChangeSize(int size) {
        super.onAdapterChangeSize(size);
    }


    @Override
    public List<String> getWareHouseData() {
        return mWareHouseData;
    }

    @Override
    public List<String> getStatus() {
        return mStatusList;
    }

    @Override
    public void doSearch() {
        mViewModel.doSearch();
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
        return mViewModel.getToolbarTitle();
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }

    @Override
    protected RecyclerView.Adapter getListAdapter() {
        // TODO: 06/06/2017 Phân quyền
        mAdaper = new ListOrderAdapter(mContext, listData);
        mAdaper.setOnOrderClickListener(new ListOrderAdapter.OnOrderClickListener() {
            @Override
            public void onOrderClick(StockTrans item) {
                onItemStockTransClick(item);
            }
        });
        return mAdaper;
    }

    @Override
    public String getItemCountString() {
        return null;
    }

    @Override
    public void onAddClick() {
        mViewModel.onAddClick();
    }

    protected AdapterView.OnItemSelectedListener getWareHouseItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionWareHouse = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    protected AdapterView.OnItemSelectedListener getStatusItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionStatus = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    @Override
    public void setWareHouseData(List<String> mList) {
        mWareHouseData.clear();
        mWareHouseData.addAll(mList);
        warehouseAdapter.notifyChange();
    }

    @Override
    public void setStatus(List<String> mList) {
        mStatusList.clear();
        mStatusList.addAll(mList);
        statusAdapter.notifyChange();
    }

    @Override
    public void onItemStockTransClick(StockTrans stockTrans) {
        mViewModel.onItemStockTransClick(stockTrans);
    }

    @Override
    public void setDataSearch(List<StockTrans> stockTranses) {
        listData.clear();
        listData.addAll(stockTranses);
        mAdaper.notifyDataSetChanged();
    }

    @Override
    public boolean isShowAddButton() {
        return mViewModel.isShowAddButton();
    }


    public void updateTextFilter() {
        String text = Common.getDayByLong(
                ((BaseListOrderActivity) mViewModel).getFromDate().getDateInMilis())
                + mContext.getString(R.string.common_label_dot)
                + Common.getDayByLong(
                ((BaseListOrderActivity) mViewModel).getToDate().getDateInMilis());
        if (getWareHouseData().size() > 0) {
            text += mContext.getString(R.string.common_label_dot) + getWareHouseData().get(
                    positionWareHouse);
        }

        if (getStatus().size() > 0) {
            text += mContext.getString(R.string.common_label_dot) + getStatus().get(positionStatus);
        }

        filterText.set(text);
    }
}
