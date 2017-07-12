package com.viettel.mbccs.screen.importhighstore.createimp;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity;

public class ImpHighStoreCreateNoteFromStaffActivity extends BaseCreateImportWareHouseActivity {

    @Override
    protected String getTitleToolbar() {
        return getString(R.string.nhap_kho_cap_duoi_chi_tiet_phieu_nhap);
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
}
