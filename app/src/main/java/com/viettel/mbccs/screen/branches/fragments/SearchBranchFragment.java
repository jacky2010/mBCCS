package com.viettel.mbccs.screen.branches.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.databinding.FragmentSearchBranchBinding;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by minhnx on 5/20/17.
 */

public class SearchBranchFragment extends BaseDataBindFragment<FragmentSearchBranchBinding, SearchBranchPresenter>
        implements SearchBranchContract.ViewModel{

    private AppCompatActivity mActivity;

    public static SearchBranchFragment newInstance() {
        return new SearchBranchFragment();
    }

    @Override
    public void setPresenter(SearchBranchContract.Presenter presenter) {

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
    public void onAddNewDocument() {
        try{
            AddBranchFragment fragment = AddBranchFragment.newInstance();

            Bundle args = new Bundle();
            args.putInt(Constants.BundleConstant.FORM_TYPE, AddBranchFragment.FORM_ADD);

            fragment.setArguments(args);

            mActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_main, fragment)
                    .addToBackStack(null)
                    .commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepareUpdateBranch(BranchItem item) {

    }

    @Override
    protected void initData() {
        mPresenter = new SearchBranchPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_search_branch;
    }

    @Override
    protected void initView() {

    }

    private void initListeners(){
        try{
            mBinding.txtDocumentId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        ActivityUtils.hideKeyboard(getBaseActivity());
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
}
