package com.viettel.mbccs.screen.trahangcaptren.create;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.data.model.Shop;
import com.viettel.mbccs.screen.warehousecommon.basecreatecmdnote.BaseCreateCommandNoteActivity;

/**
 * Created by FRAMGIA\vu.viet.anh on 13/07/2017.
 */

public class LapLenhTraHangCapTrenActivity extends BaseCreateCommandNoteActivity<Shop> {

    @Override
    public void init() {
    }

    @Override
    public int getActionType() {
        return BaseCreateCommandNoteActivity.ACTION_CREATE_CMD;
    }

    @Override
    public String getTitleName() {
        return getString(R.string.xuatkhocapduoi_title_create_command);
    }

    @Override
    public long getOwnerIdStock() {
        return mUserRepository.getUserInfo().getShop().getShopId();
    }

    @Override
    public long getOwnerTypeStock() {
        return OwnerType.SHOP;
    }

    @Override
    public long getFromOwnerId() {
        return mUserRepository.getUserInfo().getShop().getShopId();
    }

    @Override
    public long getFromOwnerType() {
        return OwnerType.SHOP;
    }

    @Override
    public long getToOwnerId() {
        return mUserRepository.getUserInfo().getShop().getParentShop().getShopId();
    }

    @Override
    public long getToOwnerType() {
        return OwnerType.SHOP;
    }

    @Override
    public int getStepType() {
        return BaseCreateCommandNoteActivity.STEP_3;
    }

    @Override
    public void onRejectSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onCreateSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public long getReasonId() {
        return 0;
    }
}
