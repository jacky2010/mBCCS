package com.viettel.mbccs.screen.sell.retail;

import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.TelecomService;
import com.viettel.mbccs.screen.sell.channel.payment.PaymentInforChannelFragment;
import com.viettel.mbccs.screen.sell.retail.payment.PaymentInforRetailFragment;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

public class CreateSaleActivity extends BaseActivity {

    private List<StockSerial> mStockSerials;

    private TelecomService mTeleComService;

    private SaleProgram mSaleProgram;

    private ChannelInfo mChannelInfo;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_sale_retail;
    }

    @Override
    protected void initData() {
        initDataBinder();
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mStockSerials = GsonUtils.String2ListObject(
                bundle.getString(Constants.BundleConstant.STOCK_SERIAL_LIST), StockSerial[].class);

        mTeleComService =
                (TelecomService) bundle.getSerializable(Constants.BundleConstant.TELECOMSERIVE);

        mSaleProgram = (SaleProgram) bundle.getSerializable(Constants.BundleConstant.SALE_PROGRAM);
        mChannelInfo = (ChannelInfo) bundle.getSerializable(Constants.BundleConstant.CHANNEL);

        if (mStockSerials == null || mTeleComService == null || mSaleProgram == null) {
            return;
        }
        if (mChannelInfo == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,
                            PaymentInforRetailFragment.newInstance(mStockSerials, mTeleComService,
                                    mSaleProgram))
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,
                            PaymentInforChannelFragment.newInstance(mStockSerials, mTeleComService,
                                    mSaleProgram, mChannelInfo))
                    .commit();
        }
    }
}
