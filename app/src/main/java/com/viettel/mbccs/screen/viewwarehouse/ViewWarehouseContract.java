package com.viettel.mbccs.screen.viewwarehouse;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import java.util.List;

/**
 * Created by HuyQuyet on 5/21/17.
 */

public class ViewWarehouseContract {

    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
        void setData(List<StockTotal> stockTotalList);

        void onError(BaseException error);

        void onCancel();
    }
}
