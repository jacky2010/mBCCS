package com.viettel.mbccs.screen.assigntask.staffpicker;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import com.viettel.mbccs.base.BaseSearchListPickerActivity;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by Anh Vu Viet on 5/27/2017.
 */

public class StaffPickerActivity extends BaseSearchListPickerActivity
        implements StaffPickerContract.ViewModel {

    @Override
    protected void initData() {
        mPresenter = new StaffPickerPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mBinding.searchInput.addOnTextChange(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((StaffPickerPresenter) mPresenter).onTextChange(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
    public void onItemClicked(Object object) {
        Intent intent = new Intent();
        intent.putExtra(Constants.BundleConstant.STAFF_INFO, (StaffInfo) object);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showErrorDialog(BaseException e) {
        DialogUtils.showDialogError(this, e);
    }
}
