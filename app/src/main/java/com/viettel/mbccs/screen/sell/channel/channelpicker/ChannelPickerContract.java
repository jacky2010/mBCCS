package com.viettel.mbccs.screen.sell.channel.channelpicker;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ChannelInfo;

/**
 * Created by eo_cuong on 5/20/17.
 */

public interface ChannelPickerContract {

    interface Presenter extends BasePresenter {
        void init();

        void onTextChange(String s);
    }

    interface ViewModel extends BaseView<Presenter> {

        void onPickChannelPick(ChannelInfo channelInfo);
    }
}
