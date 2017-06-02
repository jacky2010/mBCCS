package com.viettel.mbccs.screen.sell.channel.channelpicker;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import com.viettel.mbccs.base.BaseSearchListPickerActivity;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class ChannelPickerActivity extends BaseSearchListPickerActivity
        implements ChannelPickerContract.ViewModel {

    private List<ChannelInfo> mChannelInfos;

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mChannelInfos =
                GsonUtils.String2ListObject(bundle.getString(Constants.BundleConstant.CHANNEL_LIST),
                        ChannelInfo[].class);

        if (mChannelInfos == null) {
            return;
        }
        mPresenter = new ChannelPickerPresenter(this, this, mChannelInfos);
        mBinding.setPresenter(mPresenter);
        mBinding.searchInput.addOnTextChange(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((ChannelPickerPresenter) mPresenter).onTextChange(s.toString());
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
    public void onPickChannelPick(ChannelInfo channelInfo) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleConstant.CHANNEL, channelInfo);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
