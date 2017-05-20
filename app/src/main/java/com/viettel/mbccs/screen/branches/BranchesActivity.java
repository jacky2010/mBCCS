package com.viettel.mbccs.screen.branches;

import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityBranchesBinding;
import com.viettel.mbccs.utils.ActivityUtils;

/**
 * Created by minhnx on 5/19/17.
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
    public void onDocumentFound(String documentId) {

    }

    @Override
    public void onDocumentNotFound(String documentId) {

    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_branches;
    }

    @Override
    protected void initData() {
        mPresenter = new BranchesPresenter(this, this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    private void initListeners(){
        try{
            mBinding.txtSearchKey.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b)
                        hideSoftInput();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void hideSoftInput(){
        ActivityUtils.hideKeyboard(this);
    }
}
