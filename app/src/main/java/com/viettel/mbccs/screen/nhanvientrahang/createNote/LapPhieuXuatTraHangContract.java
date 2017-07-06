package com.viettel.mbccs.screen.nhanvientrahang.createNote;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockTotal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 28/06/2017.
 */

public interface LapPhieuXuatTraHangContract {

    interface Presenter extends BasePresenter {

        void onSerialPickerSuccess(List<String> serials);

        void pickStockTotalListSuccess(List<StockTotal> stockTotals);
    }

    interface ViewModel extends BaseView {
        void onSerialPicker(StockTotal stockItem);

        void onCreateExpSuccess(ArrayList<StockTotal> stockTotals);

        void goGoStockPicker(ArrayList<StockTotal> stockTotals);
    }
}
