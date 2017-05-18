package com.viettel.mbccs.screen.sellretail;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleProgram;
import java.util.List;

/**
 * Created by eo_cuong on 5/15/17.
 */

public interface SaleRetailContract {

    interface Presenter extends BasePresenter {

        void onGetSaleProgramSuccess(SaleProgram saleProgram);

        void onItemServiceClick(int position);



        void onSerialPickerSuccess(List<String> serials);
    }

    interface ViewModel extends BaseView<Presenter> {

        void onChooseSaleProgram(List<SaleProgram> salePrograms);
        void onSerialPicker(ModelSale stockItem);

        void onNext(List<ModelSale> stockItems);
    }
}
