package com.viettel.mbccs.screen.main.fragments.main;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface MainFragmentContract {

    interface ViewModel extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
    }
}
