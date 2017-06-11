package com.viettel.mbccs.screen.hotnewscskpp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.HotNewsCSKPPItem;
import com.viettel.mbccs.databinding.FragmentViewHotNewsCsKppBinding;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by minhnx on 5/20/17.
 */

public class ViewHotNewsCSKPPFragment extends BaseDataBindFragment<FragmentViewHotNewsCsKppBinding, ViewHotNewsCSKPPPresenter>
        implements ViewHotNewsCSKPPContract.ViewModel {

    private static final int SURVEY_REQUEST = 1001;

    private AppCompatActivity mActivity;

    public static ViewHotNewsCSKPPFragment newInstance() {
        return new ViewHotNewsCSKPPFragment();
    }

    @Override
    public void setPresenter(ViewHotNewsCSKPPContract.Presenter presenter) {

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
    protected void initData() {
        mPresenter = new ViewHotNewsCSKPPPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
        loadNewsItem();
    }

    private void loadNewsItem() {
        try {

            Bundle args = getArguments();

            if (args != null) {

                HotNewsCSKPPItem newsItem = GsonUtils.String2Object(args.getString(Constants.BundleConstant.ITEM_LIST), HotNewsCSKPPItem.class);
                if (newsItem != null) {
                    mPresenter.onNewsContentLoaded(newsItem);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_view_hot_news_cs_kpp;
    }

    @Override
    protected void initView() {
    }

    private void initListeners() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        getActivity().onBackPressed();
    }

    @Override
    public void showNewsContent(String content) {
        try {
            mBinding.wvNewsContent.loadData(content, "text/html; charset=utf-8", "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
