package com.viettel.mbccs.screen.kppfeedback.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.KPPFeedback;
import com.viettel.mbccs.databinding.FragmentRespondKppFeedbackBinding;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by minhnx on 5/20/17.
 */

public class RespondKPPFeedbackFragment extends BaseDataBindFragment<FragmentRespondKppFeedbackBinding, RespondKPPFeedbackPresenter>
        implements RespondKPPFeedbackContract.ViewModel {

    private AppCompatActivity mActivity;

    public static RespondKPPFeedbackFragment newInstance() {
        return new RespondKPPFeedbackFragment();
    }

    @Override
    public void setPresenter(RespondKPPFeedbackContract.Presenter presenter) {

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
    protected void initData() {
        mPresenter = new RespondKPPFeedbackPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();

        try{
            Bundle args = getArguments();

            if(args != null){
                KPPFeedback item = GsonUtils.String2Object(args.getString(Constants.BundleConstant.ITEM_LIST), KPPFeedback.class);
                mPresenter.fillFeedbackContent(item);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_respond_kpp_feedback;
    }

    @Override
    protected void initView() {
    }

    private void initListeners() {

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

    @Override
    public void onBackPressed() {
        getActivity().onBackPressed();
    }

    @Override
    public void goToSuccessDialog(Bundle args) {

        com.viettel.mbccs.screen.common.dialog.DialogSuccessFragment fragment = new com.viettel.mbccs.screen.common.dialog.DialogSuccessFragment();
        fragment.setOnCloseListener(new com.viettel.mbccs.screen.common.dialog.DialogSuccessFragment.OnCloseListener() {
            @Override
            public void onClose() {
                onBackPressed();
            }
        });

        getBaseActivity().goToDialogFragment(fragment, args);
    }
}
