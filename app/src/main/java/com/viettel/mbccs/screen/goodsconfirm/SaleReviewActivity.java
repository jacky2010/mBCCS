package com.viettel.mbccs.screen.goodsconfirm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.TelecomService;
import com.viettel.mbccs.databinding.ActivityConfirmGoodsBinding;
import com.viettel.mbccs.screen.sell.retail.CreateSaleActivity;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class SaleReviewActivity
        extends BaseDataBindActivity<ActivityConfirmGoodsBinding, SaleReviewPresenter>
        implements SaleReviewContract.ViewModel {
    private static final int REQUEST_TRANS_RETAIL = 125;
    private List<StockSerial> mStockSerials;
    private TelecomService mTeleComService;
    private SaleProgram mSaleProgram;
    private ChannelInfo mChannelInfo;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_confirm_goods;
    }

    @Override
    protected void initData() {
        String json = getIntent().getExtras().getString(Constants.BundleConstant.STOCK_SERIAL_LIST);
        if (!TextUtils.isEmpty(json)) {
            mStockSerials = GsonUtils.String2ListObject(json, StockSerial[].class);
        }

        mTeleComService = (TelecomService) getIntent().getExtras()
                .getSerializable(Constants.BundleConstant.TELECOMSERIVE);

        mSaleProgram = (SaleProgram) getIntent().getExtras()
                .getSerializable(Constants.BundleConstant.SALE_PROGRAM);

        mChannelInfo = (ChannelInfo) getIntent().getExtras()
                .getSerializable(Constants.BundleConstant.CHANNEL);

        if (mStockSerials == null || mTeleComService == null || mSaleProgram == null) {
            return;
        }

        mPresenter = new SaleReviewPresenter(this, this, mStockSerials);
        mBinding.setPresenter(mPresenter);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onConfrirm(List<StockSerial> stockSerials) {
        Intent intent1 = new Intent(this, CreateSaleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BundleConstant.STOCK_SERIAL_LIST,
                GsonUtils.Object2String(stockSerials));
        bundle.putSerializable(Constants.BundleConstant.TELECOMSERIVE, mTeleComService);
        bundle.putSerializable(Constants.BundleConstant.SALE_PROGRAM, mSaleProgram);
        bundle.putSerializable(Constants.BundleConstant.CHANNEL, mChannelInfo);
        intent1.putExtras(bundle);
        startActivityForResult(intent1, REQUEST_TRANS_RETAIL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TRANS_RETAIL && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
