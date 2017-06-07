package com.viettel.mbccs.screen.sellanypay.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.databinding.FragmentCreateTransAnyPayBinding;
import com.viettel.mbccs.screen.sellanypay.dialogs.DialogConfirmSellAnyPayFragment;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by minhnx on 5/20/17.
 */

public class CreateTransAnyPayFragment extends BaseDataBindFragment<FragmentCreateTransAnyPayBinding, CreateTransAnyPayPresenter>
        implements CreateTransAnyPayContract.ViewModel{

    private AppCompatActivity mActivity;

    public static CreateTransAnyPayFragment newInstance() {
        return new CreateTransAnyPayFragment();
    }

    @Override
    public void setPresenter(CreateTransAnyPayContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new CreateTransAnyPayPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_create_trans_any_pay;
    }

    @Override
    protected void initView() {

    }

    private void initListeners(){
        try{
//            mBinding.txtDocumentId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    if(!b)
//                        hideSoftInput();
//                }
//            });

            mBinding.spCustType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!Constants.View.HINT.equals(view.getTag()))
                        mPresenter.onCustomerTypeChanged(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            mBinding.spPayMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!Constants.View.HINT.equals(view.getTag()))
                        mPresenter.onPaymentMethodChanged(i);
                    mPresenter.onAmountChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            mBinding.spBankPlusAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!Constants.View.HINT.equals(view.getTag()))
                        mPresenter.onBankPlusAmountChanged(i);
                    mPresenter.onAmountChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            mBinding.spDefaultAmountList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!Constants.View.HINT.equals(view.getTag()))
                        mPresenter.onDefaultAmountChanged(i);
                    mPresenter.onAmountChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            mBinding.txtOtherAmount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    mPresenter.onAmountChanged();
                }
            });

            mBinding.txtEWalletAmount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    mPresenter.onAmountChanged();
                }
            });

//            mBinding.txtOtherAmount.setOnKeyListener(new View.OnKeyListener() {
//                @Override
//                public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
//                    if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
//                        if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                            mPresenter.onAmountChanged();
//                            return true;
//                        }
//                        mPresenter.onAmountChanged();
//                    }
//                    return false;
//                }
//            });

//            mBinding.txtEWalletAmount.setOnKeyListener(new View.OnKeyListener() {
//                @Override
//                public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
//                    if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
//                        if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                            mPresenter.onAmountChanged();
//                            return true;
//                        }
//                        mPresenter.onAmountChanged();
//                    }
//                    return false;
//                }
//            });

            mBinding.rbPayByCashDefaultAmount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.onAmountChanged();
                }
            });

            mBinding.rbPayByCashOtherAmount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.onAmountChanged();
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
    public void onCustomerTypeChanged(CreateTransAnyPayContract.CustomerType type) {

    }

    @Override
    public void onPayMethodChanged(CreateTransAnyPayContract.PayMethod method) {

    }

    @Override
    public void goToDialogFragment(Bundle args) {
        getBaseActivity().goToDialogFragment(new DialogConfirmSellAnyPayFragment(), args);
    }
}
