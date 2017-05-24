package com.viettel.mbccs.screen.help;

import android.databinding.ObservableBoolean;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityHelpBinding;
import com.viettel.mbccs.screen.help.fragment.HelpDetailFragment;

public class HelpActivity extends BaseDataBindActivity<ActivityHelpBinding, HelpActivity> {

    public ObservableBoolean showRightIcon;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_help;
    }

    @Override
    protected void initData() {
        mBinding.setPresenter(this);
        showRightIcon = new ObservableBoolean();
    }

    public void onCancel() {
        finish();
    }

    public void onClick(View v) {
        HelpDetailFragment fragment = null;
        switch (v.getId()) {
            case R.id.text_help_qlbh:
                fragment = HelpDetailFragment.newInstance(HelpDetailFragment.HelpType.QLBH);
                break;
            case R.id.text_help_dntbm:
                fragment = HelpDetailFragment.newInstance(HelpDetailFragment.HelpType.DNTBM);
                break;
            case R.id.text_help_khycbh:
                fragment = HelpDetailFragment.newInstance(HelpDetailFragment.HelpType.KHYCBH);
                break;
            case R.id.text_help_csm:
                fragment = HelpDetailFragment.newInstance(HelpDetailFragment.HelpType.CSM);
                break;
            case R.id.text_help_cvdg:
                fragment = HelpDetailFragment.newInstance(HelpDetailFragment.HelpType.CVDG);
                break;
            case R.id.text_help_tdkpibh:
                fragment = HelpDetailFragment.newInstance(HelpDetailFragment.HelpType.TDKPIBH);
                break;
            default:
                fragment = HelpDetailFragment.newInstance(HelpDetailFragment.HelpType.QLBH);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.frame_help, fragment);
        transaction.commitAllowingStateLoss();
    }
}
