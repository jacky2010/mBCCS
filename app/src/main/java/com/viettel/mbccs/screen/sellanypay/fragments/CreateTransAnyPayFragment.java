package com.viettel.mbccs.screen.sellanypay.fragments;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.databinding.FragmentCreateTransAnyPayBinding;
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
    public void onCustomerTypeChanged(CreateTransAnyPayContract.CustomerType type) {

    }

    @Override
    public void onPayMethodChanged(CreateTransAnyPayContract.PayMethod method) {

    }
}
