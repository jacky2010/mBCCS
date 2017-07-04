package com.viettel.mbccs.screen.warehousecommon.cmdprepareimportdetail;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 04/07/2017.
 */

public interface CmdPrepareImportDetailContract {
    interface Presenter extends BasePresenter {
        void onSerialPickerSuccess(List<String> serials);
    }

    interface ViewModel extends BaseView<Presenter> {

        String getToolBarTitle();

        void onSerialPicker(StockTransDetail stockTransDetail);

        void onCreateImportStockSuccess(ArrayList<StockTransDetail> stockTransDetails,
                StockTrans stockTrans);
    }
}
