package com.viettel.mbccs.screen.common.picker;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.KeyValue;

/**
 * Created by eo_cuong on 5/20/17.
 */

public interface KeyValuePickerContract {

    interface Presenter extends BasePresenter {
        void init();

        void onTextChange(String s);
        void setTitle(String s);
    }

    interface ViewModel extends BaseView<Presenter> {

        void onPickItem(KeyValue item);
    }
}
