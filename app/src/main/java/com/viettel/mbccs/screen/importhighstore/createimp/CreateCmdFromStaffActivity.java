package com.viettel.mbccs.screen.importhighstore.createimp;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity;

public class CreateCmdFromStaffActivity extends BaseCreateImportWareHouseActivity {

    @Override
    protected String getTitleToolbar() {
        return getString(R.string.nhap_kho_cap_duoi_chi_tiet_lenh_nhap);
    }

    @Override
    public int getActionTypeCreate() {
        return BaseCreateImportWareHouseActivity.ACTION_CREATE_CMD;
    }

    @Override
    public void onImportSuccess() {
        setResult(RESULT_OK);
        finish();
    }
}
