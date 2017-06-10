package com.viettel.mbccs.screen.hotnewscskpp;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityHotNewsCsKppBinding;
import com.viettel.mbccs.screen.hotnewscskpp.fragments.SearchHotNewsCSKPPFragment;

/**
 * Created by minhnx on 5/20/17.
 */

public class HotNewsCSKPPActivity extends BaseDataBindActivity<ActivityHotNewsCsKppBinding, HotNewsCSKPPPresenter>
        implements HotNewsCSKPPContract.ViewModel {

    @Override
    public void setPresenter(HotNewsCSKPPContract.Presenter presenter) {

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
        return R.layout.activity_hot_news_cs_kpp;
    }

    @Override
    protected void initData() {
        mPresenter = new HotNewsCSKPPPresenter(this, this);
        mBinding.setPresenter(mPresenter);

        changeToSearchTab();
    }

    @Override
    public void onCancel() {
        onBackPressed();
    }

    @Override
    public void changeToSearchTab() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, SearchHotNewsCSKPPFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
