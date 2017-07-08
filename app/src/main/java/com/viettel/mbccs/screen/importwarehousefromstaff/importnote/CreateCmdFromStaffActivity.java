package com.viettel.mbccs.screen.importwarehousefromstaff.importnote;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.importcmdnotestock
        .BaseCreateImportWareHouseActivity;

/**
 * Created by FRAMGIA\hoang.van.cuong on 07/07/2017.
 */

public class CreateCmdFromStaffActivity extends BaseCreateImportWareHouseActivity {

    @Override
    protected String getTitleToolbar() {
        return getString(R.string.nhapkhonhanvien_cmd_detail);
    }

    @Override
    public int getActionTypeCreate() {
        return BaseCreateImportWareHouseActivity.ACTION_CREATE_CMD;
    }

    @Override
    public int getStepCreate() {
        return BaseCreateImportWareHouseActivity.STEP_3;
    }
}
