package com.viettel.mbccs.screen.trahangcaptren.create;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.exportwarehouse.BaseExportWareHouseActivity;

/**
 * Created by FRAMGIA\vu.viet.anh on 13/07/2017.
 */

public class TraHangCapTrenActivity extends BaseExportWareHouseActivity {

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
