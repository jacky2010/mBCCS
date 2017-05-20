package com.viettel.mbccs.screen.sellretail;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.TeleComService;
import com.viettel.mbccs.screen.sellretail.payment.PaymentInforRetailFragment;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

public class CreateSaleRetailActivity extends BaseActivity {

    private List<StockSerial> mStockSerials;

    private TeleComService mTeleComService;

    private SaleProgram mSaleProgram;

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
                (TeleComService) bundle.getSerializable(Constants.BundleConstant.TELECOMSERIVE);

        mSaleProgram = (SaleProgram) bundle.getSerializable(Constants.BundleConstant.SALE_PROGRAM);

        if (mStockSerials == null || mTeleComService == null || mSaleProgram == null) {
            return;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,
                        PaymentInforRetailFragment.newInstance(mStockSerials, mTeleComService,
                                mSaleProgram))
                .commit();
    }
}
