package com.viettel.mbccs.screen.branches.fragments;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.databinding.FragmentAddBranchBinding;
import com.viettel.mbccs.utils.ActivityUtils;

/**
 * Created by minhnx on 5/20/17.
 */

public class AddBranchFragment extends BaseDataBindFragment<FragmentAddBranchBinding, AddBranchPresenter>
        implements AddBranchContract.ViewModel{

    private AppCompatActivity mActivity;

    public static AddBranchFragment newInstance() {
        return new AddBranchFragment();
    }

    @Override
    public void setPresenter(AddBranchContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new AddBranchPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_add_branch;
    }

    @Override
    protected void initView() {

    }

    private void initListeners(){

    }

    private void hideSoftInput(){
        ActivityUtils.hideKeyboard(getBaseActivity());
    }

    @Override
    public void onBranchAdded(BranchItem branchItem) {

    }

    @Override
    public void onBranchAddFailed(BranchItem branchItem) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
    }
}
