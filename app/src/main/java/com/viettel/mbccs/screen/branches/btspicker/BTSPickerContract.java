package com.viettel.mbccs.screen.branches.btspicker;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.KeyValue;

/**
 * Created by eo_cuong on 5/20/17.
 */

public interface BTSPickerContract {

    interface Presenter extends BasePresenter {
        void init();

        void onTextChange(String s);
    }

    interface ViewModel extends BaseView<Presenter> {

        void onPickBTS(KeyValue item);
    }
}
