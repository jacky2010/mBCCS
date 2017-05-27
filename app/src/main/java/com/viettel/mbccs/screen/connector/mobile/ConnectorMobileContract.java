package com.viettel.mbccs.screen.connector.mobile;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import java.util.List;

/**
 * Created by HuyQuyet on 5/27/17.
 */

public class ConnectorMobileContract {

    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {

        void onCancel();

        void txtPassportEmpty();

        void searchError(BaseException error);

        void checkIdNoSuccess(Customer customer, List<Contract> contractList);

        void onItemClick(int position);
    }
}
