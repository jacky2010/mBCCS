package com.viettel.mbccs.screen.trahangcaptren.create;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.basecreatecmdnote.BaseCreateCommandNoteActivity;

/**
 * Created by FRAMGIA\vu.viet.anh on 06/06/2017.
 */

public class CreateTicketActivity extends BaseCreateCommandNoteActivity {

    @Override
    public void init() {

    }

    @Override
    public int getActionType() {
        return BaseCreateCommandNoteActivity.ACTION_CREATE_NOTE;
    }

    @Override
    public String getTitleName() {
        return getString(R.string.common_export_label_cmd_detail);
    }

    @Override
    public long getOwnerIdStock() {
        return 0;
    }

    @Override
    public long getOwnerTypeStock() {
        return 0;
    }

    @Override
    public long getFromOwnerId() {
        return 0;
    }

    @Override
    public long getFromOwnerType() {
        return 0;
    }

    @Override
    public long getToOwnerId() {
        return 0;
    }

    @Override
    public long getToOwnerType() {
        return 0;
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
