package com.viettel.mbccs.screen.changesim;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityChangeSimBinding;
import com.viettel.mbccs.screen.changesim.fragments.SearchChangeSimFragment;

/**
 * Created by minhnx on 5/20/17.
 */

public class ChangeSimActivity extends BaseDataBindActivity<ActivityChangeSimBinding, ChangeSimPresenter>
        implements ChangeSimContract.ViewModel{

    @Override
    public void setPresenter(ChangeSimContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_change_sim;
    }

    @Override
    protected void initData() {
        mPresenter = new ChangeSimPresenter(this, this);
        mBinding.setPresenter(mPresenter);

        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, SearchChangeSimFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCancel() {
        onBackPressed();
    }
}
