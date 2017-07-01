package com.viettel.mbccs.screen.viewproduct;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.databinding.ActivityViewProductDetailBinding;
import com.viettel.mbccs.screen.viewproduct.adapter.ProductImagePagerAdapter;
import com.viettel.mbccs.screen.viewproduct.adapter.ViewProductDetailFragmentAdapter;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/20/17.
 */

public class ViewProductDetailActivity extends BaseDataBindActivity<ActivityViewProductDetailBinding, ViewProductDetailPresenter>
        implements ViewProductDetailContract.ViewModel {

    private ViewProductDetailFragmentAdapter viewProductDetailAdapter;
    private ProductImagePagerAdapter productImagePagerAdapter;

    private List<String> tabTitles;

    @Override
    public void setPresenter(ViewProductDetailContract.Presenter presenter) {

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
        return R.layout.activity_view_product_detail;
    }

    @Override
    protected void initData() {
        mPresenter = new ViewProductDetailPresenter(this, this);
        mBinding.setPresenter(mPresenter);

        try {

            tabTitles = new ArrayList<>();
            String[] titles = getResources().getStringArray(R.array.search_products_detail_title_tabs_desc);

            if (titles != null) {
                for (int i = 0; i < titles.length; i++) {
                    tabTitles.add(titles[i]);
                }
            }

            viewProductDetailAdapter = new ViewProductDetailFragmentAdapter(getSupportFragmentManager(), tabTitles);
            mPresenter.setViewProductDetailFragmentAdapter(viewProductDetailAdapter);
            mBinding.tabLayout.setupWithViewPager(mBinding.vpPager);

            productImagePagerAdapter = new ProductImagePagerAdapter(getSupportFragmentManager());
            mPresenter.setProductImagePagerAdapter(productImagePagerAdapter);

            Bundle args = getIntent().getExtras();

            if (args != null && args.getString(Constants.BundleConstant.ITEM_LIST) != null) {
                ModelSale item = GsonUtils.String2Object(args.getString(Constants.BundleConstant.ITEM_LIST), ModelSale.class);

                if (item != null) {
                    mPresenter.displayProductDetail(item);
                }
            }

            mBinding.vpImagesList.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    mPresenter.onImagePageChanged(i);
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCancel() {
        onBackPressed();
    }

}
