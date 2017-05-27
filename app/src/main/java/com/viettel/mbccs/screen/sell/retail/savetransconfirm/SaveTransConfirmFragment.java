package com.viettel.mbccs.screen.sell.retail.savetransconfirm;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.databinding.FragmentTransactionRetailConfirmBinding;
import com.viettel.mbccs.variable.Constants;

public class SaveTransConfirmFragment extends BaseFragment
        implements SaveTransConfirmContract.ViewModel {

    private FragmentTransactionRetailConfirmBinding mBinding;
    private BaseRequest<GetInfoSaleTranRequest> mGetInfoSaleTranRequest;
    private SaleTrans mSaleTrans;
    private ChannelInfo mChannelInfo;
    private SaveTransConfirmPresenter mPresenter;
    private AppCompatActivity mActivity;

    public static SaveTransConfirmFragment newInstance(
            BaseRequest<GetInfoSaleTranRequest> getInfoSaleTranRequest, SaleTrans saleTrans,ChannelInfo channelInfo) {
        Bundle bundle = new Bundle();
//        bundle.putSerializable(Constants.BundleConstant.INFOR_SALE_REQUEST, getInfoSaleTranRequest);
        bundle.putSerializable(Constants.BundleConstant.SALE_TRANS, saleTrans);
        bundle.putSerializable(Constants.BundleConstant.CHANNEL, channelInfo);
        SaveTransConfirmFragment fragment = new SaveTransConfirmFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_retail_confirm,
                container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String s1 = bundle.getString(Constants.BundleConstant.INFOR_SALE_REQUEST);
        mGetInfoSaleTranRequest = (BaseRequest<GetInfoSaleTranRequest>) bundle.getSerializable(
                Constants.BundleConstant.INFOR_SALE_REQUEST);
        mSaleTrans = (SaleTrans) bundle.getSerializable(Constants.BundleConstant.SALE_TRANS);
        mChannelInfo = (ChannelInfo) bundle.getSerializable(Constants.BundleConstant.CHANNEL);
        if (mGetInfoSaleTranRequest == null || mSaleTrans == null) {
            return;
        }
        mPresenter = new SaveTransConfirmPresenter(getActivity(), this, mSaleTrans,
                mGetInfoSaleTranRequest,mChannelInfo);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(SaveTransConfirmContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoading();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
    }

    @Override
    public void close() {
        mActivity.getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onSaveTranSuccess() {
        mActivity.setResult(Activity.RESULT_OK);
        mActivity.finish();
    }
}
