package com.viettel.mbccs.screen.goodsconfirm;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockSerial;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public interface SaleReviewContract {

    interface Presenter extends BasePresenter {

    }

    interface ViewModel extends BaseView<Presenter> {

        void onConfrirm(List<StockSerial> stockSerials);
    }
}
