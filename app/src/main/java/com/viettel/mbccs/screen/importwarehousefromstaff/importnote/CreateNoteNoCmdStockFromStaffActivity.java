package com.viettel.mbccs.screen.importwarehousefromstaff.importnote;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.importcmdnotestock
        .BaseCreateImportWareHouseActivity;

/**
 * Created by FRAMGIA\hoang.van.cuong on 10/07/2017.
 */

public class CreateNoteNoCmdStockFromStaffActivity extends BaseCreateImportWareHouseActivity {

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
        return BaseCreateImportWareHouseActivity.STEP_2;
    }

    @Override
    public void onImportSuccess() {
        setResult(RESULT_OK);
        finish();
    }
}
