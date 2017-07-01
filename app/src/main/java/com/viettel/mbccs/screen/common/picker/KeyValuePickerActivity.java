package com.viettel.mbccs.screen.common.picker;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.databinding.ActivityKeyValuePickerBinding;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class KeyValuePickerActivity
        extends BaseDataBindActivity<ActivityKeyValuePickerBinding, KeyValuePickerPresenter>
        implements KeyValuePickerContract.ViewModel {

    private List<KeyValue> items;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_key_value_picker;
    }

    @Override
    protected void initData() {

        Bundle bundle = getIntent().getExtras();
        if (bundle == null || bundle.getString(Constants.BundleConstant.ITEM_LIST) == null) {
            return;
        }
        items = GsonUtils.String2ListObject(
                bundle.getString(Constants.BundleConstant.ITEM_LIST), KeyValue[].class);

        if (items == null) {
            return;
        }

        mPresenter = new KeyValuePickerPresenter(this, this, items);
        mBinding.setPresenter(mPresenter);
        mBinding.searchInput.addOnTextChange(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.onTextChange(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPresenter.setTitle(bundle.getString(Constants.BundleConstant.FORM_TYPE, ""));
    }

    @Override
    public void setPresenter(KeyValuePickerContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onPickItem(KeyValue item) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleConstant.RESULT, item);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
