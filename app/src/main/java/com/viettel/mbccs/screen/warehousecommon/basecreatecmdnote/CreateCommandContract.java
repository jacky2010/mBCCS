package com.viettel.mbccs.screen.warehousecommon.basecreatecmdnote;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTrans;
import java.util.ArrayList;
import java.util.List;

public interface CreateCommandContract {

    interface Presenter<T> extends BasePresenter {

        void pickStockTotalListSuccess(List<StockTotal> stockTotals);

        String getActivityTitle();

        void setListReceiverWareHouser(List<T> listReceiverWareHouser);

        int getPositionReceicerWareHouse();
    }

    interface ViewModel<T> extends BaseView<Presenter> {

        boolean isViewOnly();

        String getScreenTitle();

        void goGoStockPicker(ArrayList<StockTotal> stockTotals);

        void finishScreen();

        int getAction();

        int getStep();

        void setListReceiverWareHouser(List<T> listReceiverWareHouser);

        int getPositionReceicerWareHouse();

        long getOwnerStockIdCreate();

        long getOwnerStockTypeCreate();

        long getFromOwnerIdCreate();

        long getFromOwnerTypeCreate();

        long getToOwnerIdCreate();

        long getToOwnerTypeCreate();

        void onRejectExportSuccess();

        void onCreateExportSuccess(StockTrans stockTrans);

        long getReasonId();
    }
}
