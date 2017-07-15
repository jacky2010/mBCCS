package com.viettel.mbccs.screen.nvxacnhanhang;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity;

/**
 * Created by HuyQuyet on 7/14/17.
 */

public class NvXacNhanHangNhapKhoActivity extends BaseCreateImportWareHouseActivity {

    @Override
    protected String getTitleToolbar() {
        return getString(R.string.nv_xac_nhan_hang_chi_tiet_phieu_nhap_kho);
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
        return 0;
    }
}
