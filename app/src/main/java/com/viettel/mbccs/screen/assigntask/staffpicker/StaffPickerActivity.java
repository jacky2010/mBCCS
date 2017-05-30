package com.viettel.mbccs.screen.assigntask.staffpicker;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.viettel.mbccs.base.BaseSearchListPickerActivity;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.variable.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/27/2017.
 */

public class StaffPickerActivity extends BaseSearchListPickerActivity
        implements StaffPickerContract.ViewModel {

    private List<StaffInfo> mStaffInfo;

    @Override
    protected void initData() {
        mStaffInfo = new ArrayList<>();
        mStaffInfo.add(new StaffInfo(123, "155", "Nguyen Van A", 122, "154", "Shop", 0, "0125846",
                "Nhan vien ban hang", "Ha Noi"));

        mPresenter = new StaffPickerPresenter(this, this, mStaffInfo);
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onStaffPicked(StaffInfo staffInfo) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STAFF_INFO, staffInfo);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
