package com.viettel.mbccs.screen.serialpicker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.SerialPickerModel;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.databinding.ActivitySerialPickerBinding;
import com.viettel.mbccs.screen.common.success.ScanbarCodeActivity;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class SerialPickerActivity
        extends BaseDataBindActivity<ActivitySerialPickerBinding, SerialPickerPresenter>
        implements SerialPickerContract.ViewModel {

    public static final int SCANBARCODE_REQUEST_CODE = 125;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 126;
    private SerialPickerModel mSerialPickerModel;
    private GetSerialRequest mRequest;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_serial_picker;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mSerialPickerModel = (SerialPickerModel) bundle.getSerializable(
                    Constants.BundleConstant.SERIAL_PICKER_MODEL);
        }
        mPresenter = new SerialPickerPresenter(this, this, mSerialPickerModel);
        mBinding.setPresenter(mPresenter);

        getSerials();
    }

    private void getSerials() {
        if (mSerialPickerModel == null) {
            return;
        }
        mRequest = new GetSerialRequest();
    }

    @Override
    public void setPresenter(SerialPickerContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void openQRcodeScanner() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(SerialPickerActivity.this,
                    Manifest.permission.CAMERA);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(
                        new Intent(SerialPickerActivity.this, ScanbarCodeActivity.class),
                        SCANBARCODE_REQUEST_CODE);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(SerialPickerActivity.this,
                        Manifest.permission.READ_CONTACTS)) {
                    Toast.makeText(this,
                            "Hãy cho phép truy cập camera để thực hiện chức năng scan barcode",
                            Toast.LENGTH_SHORT).show();
                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(SerialPickerActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
        } else {
            startActivityForResult(new Intent(SerialPickerActivity.this, ScanbarCodeActivity.class),
                    SCANBARCODE_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SCANBARCODE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String serial = data.getStringExtra(Constants.BundleConstant.SCAN_SERIAL);
                try {
                    Integer number = Integer.parseInt(serial);
                    mPresenter.onPickSerialByQrCodeSuccess(String.valueOf(number));
                } catch (Exception e) {
                    Toast.makeText(this, "Serial không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    startActivityForResult(
                            new Intent(SerialPickerActivity.this, ScanbarCodeActivity.class),
                            SCANBARCODE_REQUEST_CODE);
                } else {

                    Toast.makeText(this,
                            "Không dc phép truy câp camera, không thể thực hiện chức năng scan barcode",
                            Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
