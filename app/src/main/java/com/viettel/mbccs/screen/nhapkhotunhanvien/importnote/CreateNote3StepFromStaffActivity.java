package com.viettel.mbccs.screen.nhapkhotunhanvien.importnote;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity;

/**
 * Created by eo_cuong on 7/7/17.
 */

public class CreateNote3StepFromStaffActivity extends BaseCreateImportWareHouseActivity {

    @Override
    protected String getTitleToolbar() {
        return getString(R.string.nhapkhonhanvien_cmd_detail);
    }

    @Override
    public int getActionTypeCreate() {
        return BaseCreateImportWareHouseActivity.ACTION_CREATE_NOTE;
    }

    @Override
    public void onImportSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public int getStepType() {
        return STEP_3;
    }
}
