package com.viettel.mbccs.screen.change.installation;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataFragment;
import com.viettel.mbccs.permission.PermissionListener;
import com.viettel.mbccs.permission.PermissionsUtil;
import com.viettel.mbccs.screen.map.DialogMapStationFragment;

import butterknife.OnClick;

public class ChangeSurveyFragment extends BaseDataFragment {

    @Override
    protected void initData() {

    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_change_survey;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.bt_select_station})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_select_station:
                PermissionsUtil.requestPermission(getActivity(), new PermissionListener() {
                    @Override
                    public void permissionGranted(@NonNull String[] permissions) {
                        getBaseActivity().goToDialogFragment(new DialogMapStationFragment(), null);
                    }

                    @Override
                    public void permissionDenied(@NonNull String[] permissions) {

                    }
                }, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION});
                break;

            default:
                break;
        }

    }
}