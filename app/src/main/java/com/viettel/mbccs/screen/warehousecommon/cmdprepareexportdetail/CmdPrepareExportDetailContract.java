package com.viettel.mbccs.screen.warehousecommon.cmdprepareexportdetail;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockTransDetail;
import java.util.List;

/**
 * Created by eo_cuong on 6/20/17.
 */

interface CmdPrepareExportDetailContract {

    interface Presenter extends BasePresenter {
        void onSerialPickerSuccess(List<String> serials);
    }

    interface ViewModel extends BaseView<Presenter> {
        String getToolBarTitle();

        void onSerialPicker(StockTransDetail stockTransDetail);

        void onCreateExpStockSuccess(List<StockTransDetail> stockTransDetails);
    }
}
