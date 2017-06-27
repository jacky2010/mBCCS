package com.viettel.mbccs.screen.sell.channel;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.TelecomService;
import com.viettel.mbccs.screen.sell.retail.SaleRetailContract;
import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class SaleChannelContract {

    interface Presenter extends BasePresenter {

        void onGetSaleProgramSuccess(SaleProgram saleProgram);

        void onGetChannelSuccess(ChannelInfo channelInfo);

        void onItemServiceClick(int position);

        void onSerialPickerSuccess(List<String> serials);

        void refresh();
    }

    interface ViewModel extends BaseView<SaleRetailContract.Presenter> {

        void onChooseSaleProgram(List<SaleProgram> salePrograms);

        void onChooseChannel(List<ChannelInfo> channelInfos);

        void onSerialPicker(ModelSale stockItem);

        void onNext(List<ModelSale> stockItems, TelecomService teleComService,
                SaleProgram saleProgram, ChannelInfo currentChannel);

        void refresh();
    }
}
