package com.viettel.mbccs.screen.sell.retail.savetransconfirm;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;

public interface SaveTransConfirmContract {

    interface Presenter extends BasePresenter {

        void saveTransaction();
    }

    interface ViewModel extends BaseView<Presenter> {

        void close();

        void onSaveTranSuccess();
    }
}
