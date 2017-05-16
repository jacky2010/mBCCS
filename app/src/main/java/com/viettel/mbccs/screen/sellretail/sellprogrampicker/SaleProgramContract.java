package com.viettel.mbccs.screen.sellretail.sellprogrampicker;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.SaleProgram;

/**
 * Created by eo_cuong on 5/16/17.
 */

public interface SaleProgramContract {

    interface Presenter extends BasePresenter {
        void init();

        void onTextChange(String s);
    }

    interface ViewModel extends BaseView<Presenter> {

        void onPickSellProgram(SaleProgram sellProgram);
    }
}
