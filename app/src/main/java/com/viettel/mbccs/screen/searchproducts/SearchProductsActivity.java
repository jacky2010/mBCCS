package com.viettel.mbccs.screen.searchproducts;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivitySearchProductsBinding;
import com.viettel.mbccs.screen.searchproducts.fragments.SearchProductsDetailFragment;

/**
 * Created by minhnx on 5/20/17.
 */

public class SearchProductsActivity extends BaseDataBindActivity<ActivitySearchProductsBinding, SearchProductsPresenter>
        implements SearchProductsContract.ViewModel {

    private boolean isShownLoading = false;

    @Override
    public void showLoading() {
        if (isShownLoading)
            return;

        showLoadingDialog();
        isShownLoading = true;
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
        isShownLoading = false;
    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_search_products;
    }

    @Override
    protected void initData() {
        mPresenter = new SearchProductsPresenter(this, this);
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
                .replace(R.id.frame_main, SearchProductsDetailFragment.newInstance())
//                .addToBackStack(null)
                .commit();
    }
}
