package com.viettel.mbccs.screen.main;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.Function;

import java.util.List;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface MainContract {

    interface ViewModel extends BaseView<Presenter> {

        void gotoMenu();

        void backToMain();
    }

    interface Presenter extends BasePresenter {

        List<Function> getFunctionList();
    }
}
