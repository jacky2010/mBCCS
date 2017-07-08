package com.viettel.mbccs.screen.connector;

import android.databinding.ViewDataBinding;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.screen.connector.listener.OnBackPressActivity;

/**
 * Created by HuyQuyet on 7/7/17.
 */

public abstract class ConnectMobileViewPagerBaseActivity<T extends ViewDataBinding, K>
        extends BaseDataBindActivity<T, K> {

    private OnBackPressActivity onBackpressActivity;

    @Override
    public void onBackPressed() {
        if (onBackpressActivity != null) {
            onBackpressActivity.onBackPressActivity();
        } else {
            super.onBackPressed();
        }
    }

    public void setOnBackPressActivity(OnBackPressActivity onBackpressActivity) {
        this.onBackpressActivity = onBackpressActivity;
    }

    public void removeOnBackPressActivity() {
        onBackpressActivity = null;
    }
}
