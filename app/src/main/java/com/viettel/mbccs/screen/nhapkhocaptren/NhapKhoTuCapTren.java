package com.viettel.mbccs.screen.nhapkhocaptren;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity;

/**
 * Created by FRAMGIA\hoang.van.cuong on 07/07/2017.
 */

public class NhapKhoTuCapTren extends BaseCreateImportWareHouseActivity {

    @Override
    protected String getTitleToolbar() {
        return getString(R.string.import_high_store_note_detail);
    }

    @Override
    public int getActionTypeCreate() {
        return BaseCreateImportWareHouseActivity.ACTION_CREATE_IMPORT;
    }

    @Override
    public void onImportSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public int getStepType() {
        return BaseCreateImportWareHouseActivity.STEP_2;
    }
}
