package com.viettel.mbccs.permission;

import android.support.annotation.NonNull;

public interface PermissionListener {

    void permissionGranted(@NonNull String[] permissions);

    void permissionDenied(@NonNull String[] permissions);
}
