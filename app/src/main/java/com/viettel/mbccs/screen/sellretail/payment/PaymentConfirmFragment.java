package com.viettel.mbccs.screen.sellretail.payment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.databinding.FragmentConfirmPaymentBinding;
import com.viettel.mbccs.utils.BindingUtils;

/**
 * Created by FRAMGIA\hoang.van.cuong on 17/05/2017.
 */

public class PaymentConfirmFragment extends BaseFragment
        implements PaymentConfirmContract.ViewModel {

    private FragmentConfirmPaymentBinding mBinding;
    private PaymentConfirmContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()),
                R.layout.fragment_confirm_payment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new PaymentConfirmPresenter(getActivity(), this);
        mBinding.setPresenter((PaymentConfirmPresenter) mPresenter);
    }

    @Override
    public void setPresenter(PaymentConfirmContract.Presenter presenter) {

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
    public void onClose() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
