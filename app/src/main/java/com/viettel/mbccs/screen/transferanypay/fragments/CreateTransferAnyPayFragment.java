package com.viettel.mbccs.screen.transferanypay.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.databinding.FragmentCreateTransferAnyPayBinding;
import com.viettel.mbccs.screen.transferanypay.DialogConfirmRefillFragment;
import com.viettel.mbccs.screen.transferanypay.DialogConfirmTransferFragment;
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
    public void setPresenter(CreateTransferAnyPayContract.Presenter presenter) {

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

            mBinding.spTransType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    mPresenter.onTransTypeChanged(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            mBinding.spDefaultAmountList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    mPresenter.onDefaultAmountChanged(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

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
    public void onTransCreatedSuccessful(ChangeSimItem item) {

    }

    @Override
    public void onTransFailed() {

    }

    @Override
    public void onDefaultAmountChanged(boolean selectedDefault) {
        try{
            if(selectedDefault){
                mBinding.spDefaultAmountList.setEnabled(true);
                mBinding.txtOtherAmount.setEnabled(false);
            }else{
                mBinding.spDefaultAmountList.setEnabled(false);
                mBinding.txtOtherAmount.setEnabled(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTransferTypeChanged(CreateTransferAnyPayContract.TransferType method) {
        try{
            if(method == CreateTransferAnyPayContract.TransferType.TRANSFER){
                mBinding.btnExecute.setText(getString(R.string.transfer_anypay_transfer));
            }else if(method == CreateTransferAnyPayContract.TransferType.REFILL){
                mBinding.btnExecute.setText(getString(R.string.transfer_anypay_refill));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void goToDialogFragment(boolean isRefill, Bundle args) {
        getBaseActivity().goToDialogFragment(isRefill ? new DialogConfirmRefillFragment() : new DialogConfirmTransferFragment(), args);
    }
}
