package com.viettel.mbccs.screen.importwarehousefromstaff.importnote;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity;

/**
 * Created by eo_cuong on 7/7/17.
 */

public class CreateNoteFromStaffActivity extends BaseCreateImportWareHouseActivity {

    @Override
    protected String getTitleToolbar() {
        return getString(R.string.nhapkhonhanvien_cmd_detail);
    }

    @Override
    public int getActionTypeCreate() {
        return BaseCreateImportWareHouseActivity.ACTION_CREATE_NOTE;
    }

    @Override
    public int getStepCreate() {
        return BaseCreateImportWareHouseActivity.STEP_3;
    }
}
