package com.viettel.mbccs.screen.warehousecommon.importcmdnotestock;

import com.viettel.mbccs.base.createorder.BaseCreateOrderContract;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;

/**
 * Created by eo_cuong on 7/7/17.
 */

public interface BaseCreateImportWareHouseContract {

    interface ViewModel extends BaseCreateOrderContract.ViewModel {

        void onViewSerialClickListener(StockTransDetail item, StockTrans stockTrans);

        String getTitleToolbarHeader();

        void createCmdNoteStockSuccess(StockTrans stockTrans);

        void rejectNoteSuccess();

        int getActionType();

        int getStep();
    }

    interface Presenter extends BaseCreateOrderContract.Presenter {

    }
}
