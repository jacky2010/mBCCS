package com.viettel.mbccs.screen.sell.channel;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.SerialPickerModel;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.TelecomService;
import com.viettel.mbccs.databinding.ActivitySaleChannelBinding;
import com.viettel.mbccs.screen.goodsconfirm.SaleReviewActivity;
import com.viettel.mbccs.screen.sell.channel.channelpicker.ChannelPickerActivity;
import com.viettel.mbccs.screen.sell.retail.SaleRetailContract;
import com.viettel.mbccs.screen.sell.retail.sellprogrampicker.SaleProgramPickerActivity;
import com.viettel.mbccs.screen.serialpicker.SerialPickerActivity;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.MultiDirectionSlidingDrawer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class SaleChannelActivity
        extends BaseDataBindActivity<ActivitySaleChannelBinding, SaleChannelPresenter>
        implements SaleChannelContract.ViewModel {
    private static final int REQUEST_TRANS_RETAIL = 125;
    public static final int GET_SALE_PROGRAM = 123;
    public static final int GET_CHANNEL = 126;
    public static final int GET_SERIAL = 124;

    private MultiDirectionSlidingDrawer mDrawer;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_sale_channel;
    }

    @Override
    protected void initData() {
        mPresenter = new SaleChannelPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mDrawer = mBinding.drawer;
        mDrawer.setOnDrawerCloseListener(new MultiDirectionSlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                mPresenter.changeSearchFilter();
            }
        });
        mDrawer.open();
    }


    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void onChooseSaleProgram(List<SaleProgram> salePrograms) {
        Intent intent = new Intent(this, SaleProgramPickerActivity.class);
        intent.putExtra(Constants.BundleConstant.SALE_PROGRAM_LIST,
                GsonUtils.Object2String(salePrograms));
        startActivityForResult(intent, GET_SALE_PROGRAM);
    }

    @Override
    public void onChooseChannel(List<ChannelInfo> channelInfos) {
        Intent intent = new Intent(this, ChannelPickerActivity.class);
        intent.putExtra(Constants.BundleConstant.CHANNEL_LIST,
                GsonUtils.Object2String(channelInfos));
        startActivityForResult(intent, GET_CHANNEL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_SALE_PROGRAM && resultCode == RESULT_OK) {
            SaleProgram saleProgram = (SaleProgram) data.getExtras()
                    .getSerializable(Constants.BundleConstant.SALE_PROGRAM);
            mPresenter.onGetSaleProgramSuccess(saleProgram);
        }
        if (requestCode == GET_SERIAL && resultCode == RESULT_OK) {
            List<String> serials = GsonUtils.String2ListObject(
                    data.getExtras().getString(Constants.BundleConstant.LIST_SERIAL),
                    String[].class);
            mPresenter.onSerialPickerSuccess(serials);
        }

        if (requestCode == GET_CHANNEL && resultCode == RESULT_OK) {
            ChannelInfo channelInfo = (ChannelInfo) data.getExtras()
                    .getSerializable(Constants.BundleConstant.CHANNEL);
            mPresenter.onGetChannelSuccess(channelInfo);
        }

        if (requestCode == REQUEST_TRANS_RETAIL && resultCode == RESULT_OK) {
            mPresenter.refresh();
        }
    }

    @Override
    public void onSerialPicker(ModelSale stockItem) {
        Intent intent = new Intent(this, SerialPickerActivity.class);
        SerialPickerModel serialPickerModel = new SerialPickerModel();
        serialPickerModel.setStockModelId(stockItem.getStockModelId());
        serialPickerModel.setStockMoldeName(stockItem.getStockModelName());
        serialPickerModel.setQuantity(stockItem.getChoiceCount());
        serialPickerModel.setLstSerial(stockItem.getSerialBlocks());
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleConstant.SERIAL_PICKER_MODEL, serialPickerModel);
        intent.putExtras(bundle);
        startActivityForResult(intent, GET_SERIAL);
    }

    @Override
    public void onNext(List<ModelSale> stockItems, TelecomService teleComService,
            SaleProgram saleProgram, ChannelInfo currentChannel) {

        List<StockSerial> stockSerials = new ArrayList<>();

        for (ModelSale modelSale : stockItems) {
            StockSerial stockSerial = new StockSerial();
            stockSerial.setStockModelId(modelSale.getStockModelId());
            stockSerial.setStockModelName(modelSale.getStockModelName());
            stockSerial.setStockModelCode(modelSale.getStockModelCode());
            if (modelSale.getCheckSerial() == 1) {
                stockSerial.setSerialBOs(modelSale.getSerialBlocks());
                stockSerial.setQuantity(
                        Common.getSerialCountByListSerialBlock(modelSale.getSerialBlocks()));
            } else {
                if (modelSale.getChoiceCount() > 0) {
                    stockSerial.setQuantity(modelSale.getChoiceCount());
                }
            }

            if (stockSerial.getQuantity() > 0) {
                stockSerials.add(stockSerial);
            }
        }
        int countSerial = 0;
        for (StockSerial serial : stockSerials) {
            if (serial.getSerialBOs() != null) {
                countSerial += Common.getSerialCountByListSerialBlock(serial.getSerialBOs());
            } else {
                countSerial += serial.getQuantity();
            }
        }
        if (countSerial == 0) {
            DialogUtils.showDialog(SaleChannelActivity.this, null,
                    getResources().getString(R.string.no_serial), null);
            return;
        }

        Intent intent1 = new Intent(this, SaleReviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BundleConstant.STOCK_SERIAL_LIST,
                GsonUtils.Object2String(stockSerials));
        bundle.putSerializable(Constants.BundleConstant.TELECOMSERIVE, teleComService);
        bundle.putSerializable(Constants.BundleConstant.SALE_PROGRAM, saleProgram);
        bundle.putSerializable(Constants.BundleConstant.CHANNEL, currentChannel);
        intent1.putExtras(bundle);
        startActivityForResult(intent1, REQUEST_TRANS_RETAIL);
    }

    @Override
    public void refresh() {

    }
}
