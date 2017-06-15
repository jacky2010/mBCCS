package com.viettel.mbccs.screen.sellanypay.fragments;

import android.app.Activity;
import android.content.Intent;
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
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.databinding.FragmentCreateTransAnyPayBinding;
import com.viettel.mbccs.screen.common.picker.KeyValuePickerActivity;
import com.viettel.mbccs.screen.sellanypay.dialogs.DialogConfirmSellAnyPayFragment;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by minhnx on 5/20/17.
 */

public class CreateTransAnyPayFragment extends BaseDataBindFragment<FragmentCreateTransAnyPayBinding, CreateTransAnyPayPresenter>
        implements CreateTransAnyPayContract.ViewModel {

    private static final int GET_MANAGER = 1001;
    private static final int GET_BRANCH = GET_MANAGER + 1;
    private static final int GET_CHANNEL = GET_MANAGER + 2;

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
        try {
//            mBinding.spDefaultAmountList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        try {

            mBinding.spCustType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(view == null)
                        return;
                    if (!Constants.View.HINT.equals(view.getTag()))
                        mPresenter.onCustomerTypeChanged(i);
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
                    mPresenter.onOtherAmountChanged();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void onCustomerTypeChanged(CreateTransAnyPayContract.CustomerType type) {

    }

    @Override
    public void goToDialogFragment(Bundle args) {
        getBaseActivity().goToDialogFragment(new DialogConfirmSellAnyPayFragment(), args);
    }

    @Override
    public void onChooseBranch(List<KeyValue> items) {
        Intent intent = new Intent(getActivity(), KeyValuePickerActivity.class);
        intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                GsonUtils.Object2String(items));
        intent.putExtra(Constants.BundleConstant.FORM_TYPE, getString(R.string.sell_anypay_title_branch));
        startActivityForResult(intent, GET_BRANCH);
    }

    @Override
    public void onChooseManager(List<KeyValue> items) {
        Intent intent = new Intent(getActivity(), KeyValuePickerActivity.class);
        intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                GsonUtils.Object2String(items));
        intent.putExtra(Constants.BundleConstant.FORM_TYPE, getString(R.string.sell_anypay_title_manager));
        startActivityForResult(intent, GET_MANAGER);
    }

    @Override
    public void onChooseChannel(List<KeyValue> items) {
        Intent intent = new Intent(getActivity(), KeyValuePickerActivity.class);
        intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                GsonUtils.Object2String(items));
        intent.putExtra(Constants.BundleConstant.FORM_TYPE, getString(R.string.sell_anypay_title_channel));
        startActivityForResult(intent, GET_CHANNEL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == GET_MANAGER && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetManagerSuccess(item);
            } else if (requestCode == GET_BRANCH && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetBranchSuccess(item);
            } else if (requestCode == GET_CHANNEL && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetChannelSuccess(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
