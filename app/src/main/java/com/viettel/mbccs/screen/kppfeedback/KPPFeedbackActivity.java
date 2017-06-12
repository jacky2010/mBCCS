package com.viettel.mbccs.screen.kppfeedback;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityKppFeedbackBinding;
import com.viettel.mbccs.screen.kppfeedback.fragments.SearchKPPFeedbackFragment;
import com.viettel.mbccs.screen.kppfeedback.fragments.SendKPPFeedbackFragment;

/**
 * Created by minhnx on 5/20/17.
 */

public class KPPFeedbackActivity extends BaseDataBindActivity<ActivityKppFeedbackBinding, KPPFeedbackPresenter>
        implements KPPFeedbackContract.ViewModel {

    @Override
    public void setPresenter(KPPFeedbackContract.Presenter presenter) {

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
    protected int getIdLayout() {
        return R.layout.activity_kpp_feedback;
    }

    @Override
    protected void initData() {
        mPresenter = new KPPFeedbackPresenter(this, this);
        mBinding.setPresenter(mPresenter);

        if (isManager())
            changeToSearchTab();
        else
            changeToSendTab();
    }

    @Override
    public void onCancel() {
        onBackPressed();
    }

    @Override
    public void changeToSearchTab() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, SearchKPPFeedbackFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void changeToSendTab() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, SendKPPFeedbackFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    private boolean isManager(){
        return true;
    }
}
