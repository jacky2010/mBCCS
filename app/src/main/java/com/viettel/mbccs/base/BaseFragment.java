package com.viettel.mbccs.base;

import android.support.v4.app.Fragment;

/**
 * Created by TienDQ on 4/27/17.
 */

public abstract class BaseFragment extends Fragment {

    public void showLoadingDialog() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showLoadingDialog();
        }
    }

    public void hideLoadingDialog() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideLoadingDialog();
        }
    }
}
