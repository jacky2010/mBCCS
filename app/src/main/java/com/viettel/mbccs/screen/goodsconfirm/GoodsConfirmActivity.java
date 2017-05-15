package com.viettel.mbccs.screen.goodsconfirm;

import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.GoodItem;
import com.viettel.mbccs.databinding.ActivityConfirmGoodsBinding;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class GoodsConfirmActivity extends BaseDataBindActivity<ActivityConfirmGoodsBinding,GoodsConfirmPresenter> implements GoodsActivityContract.ViewModel {

    private List<GoodItem> mGoodItems;

    @Override
    protected ActivityConfirmGoodsBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_confirm_goods);
    }

    @Override
    protected void initData() {
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
