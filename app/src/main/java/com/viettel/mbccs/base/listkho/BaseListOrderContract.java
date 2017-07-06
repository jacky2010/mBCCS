package com.viettel.mbccs.base.listkho;

import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public interface BaseListOrderContract {

    interface ViewModel extends BaseSearchListViewContract.ViewModel<Presenter> {

        void doSearch();

        void onSearchSuccess();

        void setWareHouseData(List<String> wareHouseData);

        List<String> getWareHouseData();

        void setStatus(List<String> status);

        List<String> getStatus();

        void onAddClick();

        void onItemStockTransClick(StockTrans stockTrans);

        void setDataSearch(List<StockTrans> stockTranses);

        void setItemCountStringFormat(String format);

        String getItemCountStringFormat();

        String getToolbarTitle();

        boolean isShowAddButton();

        int getPositionStatus();

        int getPositionWareHouser();
    }

    interface Presenter extends BaseSearchListViewContract.Presenter {

        List<String> getWareHouseData();

        List<String> getStatus();

        void doSearch();

        void setWareHouseData(List<String> wareHouseData);

        void setStatus(List<String> status);

        void onAddClick();

        void onItemStockTransClick(StockTrans stockTrans);

        void setDataSearch(List<StockTrans> stockTranses);

        boolean isShowAddButton();

        void setItemCountStringFormat(String format);

        String getItemCountStringFormat();
    }
}
