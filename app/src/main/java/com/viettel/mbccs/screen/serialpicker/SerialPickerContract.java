package com.viettel.mbccs.screen.serialpicker;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

/**
 * Created by eo_cuong on 5/14/17.
 */

public interface SerialPickerContract {

    interface Presenter extends BasePresenter {

        void pickSerialByScanQrcode();

        void onPickSerialByQrCodeSuccess(String serial);

        void chooseSerial();
    }

    interface ViewModel extends BaseView<Presenter> {
        void openQRcodeScanner();
    }
}
