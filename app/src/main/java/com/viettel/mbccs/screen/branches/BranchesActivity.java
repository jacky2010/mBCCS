package com.viettel.mbccs.screen.branches;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityBranchesBinding;
import com.viettel.mbccs.screen.branches.fragments.SearchBranchFragment;

/**
 * Created by minhnx on 5/20/17.
 */

public class BranchesActivity extends BaseDataBindActivity<ActivityBranchesBinding, BranchesPresenter>
        implements BranchesContract.ViewModel{

    @Override
    public void setPresenter(BranchesContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_branches;
    }

    @Override
    protected void initData() {
        mPresenter = new BranchesPresenter(this, this);
        mBinding.setPresenter(mPresenter);

        initView();
    }

    @Override
    public void onCancel() {
//        getSupportFragmentManager().popBackStackImmediate();
        onBackPressed();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, SearchBranchFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
