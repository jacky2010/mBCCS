package com.viettel.mbccs.screen.branches.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.databinding.FragmentAddBranchBinding;
import com.viettel.mbccs.screen.branches.btspicker.BTSPickerActivity;
import com.viettel.mbccs.screen.branches.staffpicker.StaffPickerActivity;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by minhnx on 5/20/17.
 */

public class AddBranchFragment extends BaseDataBindFragment<FragmentAddBranchBinding, AddBranchPresenter>
        implements AddBranchContract.ViewModel{

    private static final int GET_MANAGER = 1001;
    private static final int GET_BTS = GET_MANAGER + 1;

    public static final int FORM_ADD = 1;
    public static final int FORM_EDIT = 2;

    private AppCompatActivity mActivity;
    private int formType;

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

        Bundle args = getArguments();
        formType = args.getInt(Constants.BundleConstant.FORM_TYPE);
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
    public void onBranchAddFailed() {

    }

    @Override
    public void onChooseManager(List<KeyValue> items) {
        Intent intent = new Intent(getActivity(), StaffPickerActivity.class);
        intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                GsonUtils.Object2String(items));
        startActivityForResult(intent, GET_MANAGER);
    }


    @Override
    public void onChooseBTS(List<KeyValue> items) {
        Intent intent = new Intent(getActivity(), BTSPickerActivity.class);
        intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                GsonUtils.Object2String(items));
        startActivityForResult(intent, GET_BTS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try{
            if (requestCode == GET_MANAGER && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetManagerSuccess(item);
            }
            if (requestCode == GET_BTS && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetBTSSuccess(item);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
    }
}
