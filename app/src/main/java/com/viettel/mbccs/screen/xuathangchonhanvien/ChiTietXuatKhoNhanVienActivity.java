package com.viettel.mbccs.screen.xuathangchonhanvien;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.warehousecommon.exportwarehouse.BaseExportWareHouseActivity;

/**
 * Created by FRAMGIA\hoang.van.cuong on 21/06/2017.
 */

public class ChiTietXuatKhoNhanVienActivity extends BaseExportWareHouseActivity {

    @Override
    public void showLoading() {

        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public String getHeaderTitle() {
        return getString(R.string.xuatkhochonhanvien_lablel_chi_tiet_xuat_kho_title);
    }

    @Override
    public void onExportSuccess() {

    }
}
