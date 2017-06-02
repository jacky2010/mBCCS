package com.viettel.mbccs.screen.sell.channel.channelpicker;

import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.data.model.ChannelInfo;

/**
 * Created by eo_cuong on 5/20/17.
 */

public interface ChannelPickerContract {

    interface Presenter extends BaseSearchListViewContract.Presenter {

        void onTextChange(String s);
    }

    interface ViewModel extends BaseSearchListViewContract.ViewModel {

        void onPickChannelPick(ChannelInfo channelInfo);
    }
}
