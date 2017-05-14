package com.viettel.mbccs.base.searchtableview;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

public interface BaseSearchTableViewContract {

    interface ViewModel extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
