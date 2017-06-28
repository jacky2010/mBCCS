package com.viettel.mbccs.screen.transferanypay.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.databinding.FragmentCreateTransferAnyPayBinding;
import com.viettel.mbccs.screen.transferanypay.dialogs.DialogConfirmRefillAnyPayFragment;
import com.viettel.mbccs.screen.transferanypay.dialogs.DialogConfirmTransferAnyPayFragment;
import com.viettel.mbccs.utils.ActivityUtils;

/**
 * Created by minhnx on 5/20/17.
 */

public class CreateTransferAnyPayFragment extends BaseDataBindFragment<FragmentCreateTransferAnyPayBinding, CreateTransferAnyPayPresenter>
        implements CreateTransferAnyPayContract.ViewModel{

    private AppCompatActivity mActivity;

    public static CreateTransferAnyPayFragment newInstance() {
        return new CreateTransferAnyPayFragment();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new CreateTransferAnyPayPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_create_transfer_any_pay;
    }

    @Override
    protected void initView() {

    }

    private void initListeners(){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTransTypeChanged(String transType) {
        try{
            switch (transType){
                case CreateTransferAnyPayPresenter.PAY_METHOD_REFILL:
                    mBinding.btnExecute.setText(R.string.transfer_anypay_label_refill);
                    break;
                case CreateTransferAnyPayPresenter.PAY_METHOD_TRANSFER:
                    mBinding.btnExecute.setText(R.string.transfer_anypay_label_transfer);
                    break;
            }
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
    public void onTransCreatedSuccessful(ChangeSimItem item) {

    }

    @Override
    public void onTransFailed() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToDialogFragment(boolean isRefill, Bundle args) {
        getBaseActivity().goToDialogFragment(isRefill ? new DialogConfirmRefillAnyPayFragment() : new DialogConfirmTransferAnyPayFragment(), args);
    }
}
