package com.viettel.mbccs.screen.viewwarehouse.fragment;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class ViewWarehouseSearchContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
        void onCancel();
    }
}
