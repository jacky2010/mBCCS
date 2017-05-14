package com.viettel.mbccs.screen.goodsconfirm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.data.model.GoodItem;
import com.viettel.mbccs.databinding.ActivityConfirmGoodsBinding;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class GoodsConfirmActivity extends BaseActivity implements GoodsActivityContract.ViewModel {

    private ActivityConfirmGoodsBinding mBinding;
    private GoodsConfirmPresenter mPresenter;
    private List<GoodItem> mGoodItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_confirm_goods);

        String json = getIntent().getExtras().getString(Constants.BundleConstant.GOODS_LIST);
        if (!TextUtils.isEmpty(json)) {
            mGoodItems = GsonUtils.String2ListObject(json, GoodItem[].class);
        }

        mPresenter = new GoodsConfirmPresenter(this, this, mGoodItems);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(GoodsActivityContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
