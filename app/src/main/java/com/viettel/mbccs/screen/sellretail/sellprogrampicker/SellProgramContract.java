package com.viettel.mbccs.screen.sellretail.sellprogrampicker;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by eo_cuong on 5/16/17.
 */

public interface SellProgramContract {

    interface Presenter extends BasePresenter {
        void init();
    }

    interface ViewModel extends BaseView<Presenter> {

    }
}
