package com.viettel.mbccs.screen.sell.orders;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import java.util.List;

/**
 * Created by HuyQuyet on 5/15/17.
 */

public class SellOrdersContract {
    interface Presenter extends BasePresenter, AdapterView.OnItemSelectedListener {
        void setSpinnerAdapterUnit(ArrayAdapter adapterUnit);
    }

    interface View extends BaseView<Presenter> {

        void setDataResult(List<SaleOrders> orders, ChannelInfo channelInfoSelect);

        void getDataError(BaseException error);

        void getListChannelByOwnerTypeIdError(BaseException error);

        void getListChannelByOwnerTypeIdError(String error);

        long getDateTo();

        long getDateFrom();

        String getStringDateTo();

        String getStringDateFrom();

        void showErrorDate();

        void closeFormSearch();
    }
}
