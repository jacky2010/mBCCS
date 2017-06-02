package com.viettel.mbccs.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.viettel.mbccs.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class PermissionUtils {

    public static final int REQUEST_CODE_BASE_PERMISSIONS = 101;

    public static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSIONS = 102;

    public static final int REQUEST_CODE_CAMERA_PERMISSIONS = 103;

    public static boolean checkPermissions(AppCompatActivity activity, int requestCode,
            String... permissions) {
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            int per = ActivityCompat.checkSelfPermission(activity, permission);

            if (per != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    requestCode);
            return false;
        }
        return true;
    }

    public static boolean checkPermissions(Fragment fragment, int requestCode,
            String... permissions) {
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            int per = ActivityCompat.checkSelfPermission(fragment.getActivity(), permission);

            if (per != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            fragment.requestPermissions(
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    requestCode);
            return false;
        }
        return true;
    }

    public static void onRequestResult(Activity activity, @NonNull final String permissions[],
            @NonNull int[] grantResults, @Nullable OnPermissionGranted granted,
            @Nullable OnPermissionDenied denied, boolean shouldDeniedRationale) {
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[i])) {
                    if (denied != null) denied.onDenied();
                } else {
                    if (shouldDeniedRationale && denied != null) {
                        denied.onDenied();
                    }
                    Toast.makeText(activity, R.string.permission_guide, Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
        if (granted != null) granted.onGranted();
    }

    public interface OnPermissionDenied {
        void onDenied();
    }

    public interface OnPermissionGranted {
        void onGranted();
    }
}
