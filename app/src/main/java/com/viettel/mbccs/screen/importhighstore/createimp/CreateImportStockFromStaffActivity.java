package com.viettel.mbccs.screen.importhighstore.createimp;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity;

/**
 * Created by FRAMGIA\hoang.van.cuong on 07/07/2017.
 */

public class CreateImportStockFromStaffActivity extends BaseCreateImportWareHouseActivity {

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
}
