package com.viettel.mbccs.screen.xuatkhochonhanvien.threestep;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.exportwarehouse.BaseExportWareHouseActivity;

/**
 * Created by HuyQuyet on 7/13/17.
 */

public class XuatKho3XuatKhoChoNhanVienActivity extends BaseExportWareHouseActivity {
    @Override
    public void rejectFinish() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public String getHeaderTitle() {
        return getString(R.string.xuat_kho_cho_nhan_vien_chi_tiet_phieu_xuat);
    }

    @Override
    public void onExportSuccess() {
        setResult(RESULT_OK);
        finish();
    }
}
