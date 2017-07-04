package com.viettel.mbccs.screen.stockdeliver.billinput;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTransDetail;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 6/20/17.
 */

interface BaseBillInputDetailContract {

    interface Presenter extends BasePresenter {
        void onSerialPickerSuccess(List<String> serials);
    }

    interface ViewModel extends BaseView<Presenter> {
        String getToolBarTitle();

        String getActionMode();

        void viewSerialDetail(StockSerial stockSerial);

        void onSerialPicker(StockTransDetail stockTransDetail);

        void onCreateExpStockSuccess(ArrayList<StockTransDetail> stockTransDetails);
    }
}
