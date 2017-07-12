package com.viettel.mbccs.screen.nhapkhotunhanvien.importnote;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.importcmdnotestock
        .BaseCreateImportWareHouseActivity;

public class CreateImportStockFromStaffActivity extends BaseCreateImportWareHouseActivity {

    @Override
    protected String getTitleToolbar() {
        return getString(R.string.nhapkhonhanvien_note_detail);
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
        return STEP_3;
    }
}
