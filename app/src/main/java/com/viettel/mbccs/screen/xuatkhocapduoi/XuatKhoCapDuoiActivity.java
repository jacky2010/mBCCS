package com.viettel.mbccs.screen.xuatkhocapduoi;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.exportwarehouse.BaseExportWareHouseActivity;

/**
 * Created by eo_cuong on 7/12/17.
 */

public class XuatKhoCapDuoiActivity extends BaseExportWareHouseActivity {

    @Override
    public void rejectFinish() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public String getHeaderTitle() {
        return getString(R.string.common_export_label_note_detail);
    }

    @Override
    public void onExportSuccess() {
        setResult(RESULT_OK);
        finish();
    }
}
