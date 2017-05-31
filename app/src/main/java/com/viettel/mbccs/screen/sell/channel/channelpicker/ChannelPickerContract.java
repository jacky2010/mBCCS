package com.viettel.mbccs.screen.sell.channel.channelpicker;

import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;

/**
 * Created by eo_cuong on 5/20/17.
 */

public interface ChannelPickerContract {

    interface Presenter extends BaseSearchListViewContract.Presenter {

        void onTextChange(String s);
    }

    interface ViewModel extends BaseSearchListViewContract.ViewModel {

    }
}
