package com.viettel.mbccs.screen.goodsconfirm;

import android.text.TextUtils;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.databinding.ActivityConfirmGoodsBinding;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class SaleConfirmActivity extends BaseDataBindActivity<ActivityConfirmGoodsBinding,SaleConfirmPresenter> implements SaleActivityContract.ViewModel {


    private List<ModelSale> mGoodItems;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_confirm_goods;
    }

    @Override
    protected void initData() {
        String json = getIntent().getExtras().getString(Constants.BundleConstant.GOODS_LIST);
        if (!TextUtils.isEmpty(json)) {
            mGoodItems = GsonUtils.String2ListObject(json, ModelSale[].class);
        }

        mPresenter = new SaleConfirmPresenter(this, this, mGoodItems);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(SaleActivityContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
