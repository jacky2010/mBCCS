package com.viettel.mbccs.screen.warehousecommon.exportwarehouse;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 6/20/17.
 */

interface BaseExportWareHouseContract {

    interface Presenter extends BasePresenter {
        void onSerialPickerSuccess(List<String> serials);
    }

    interface ViewModel extends BaseView<Presenter> {
        String getToolBarTitle();

        void onSerialPicker(StockTransDetail stockTransDetail);

        void onCreateExpStockSuccess(StockTrans stockTrans);

        void rejectFinish();
    }
}
