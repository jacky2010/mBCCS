package com.viettel.mbccs.screen.inputorder.fragment;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.databinding.FragmentInputOrderBinding;
import com.viettel.mbccs.screen.common.success.DialogFullScreen;
import com.viettel.mbccs.screen.inputorder.InputOrderContract;
import com.viettel.mbccs.screen.inputorder.fragment.adapter.OrderAdapter;

public class OrderFragment extends BaseFragment implements OrderContract.ViewModel {

    public static final int INDEX_TAB_ORDER_HIGH = 0;
    public static final int INDEX_TAB_ORDER_SLOW = 1;
    private static final String ARG_INDEX_TAB = "index_order";
    private FragmentInputOrderBinding mBinding;
    private OrderPresenter mPresenter;
    private OrderAdapter mAdapter;

    public static OrderFragment newInstance(int index) {
        OrderFragment orderFragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_INDEX_TAB, index);
        orderFragment.setArguments(bundle);
        return orderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_input_order, container, false);
        mAdapter = new OrderAdapter();
        mPresenter = new OrderPresenter(this, getIndexTab());
        mBinding.setPresenter(mPresenter);
        mPresenter.subscribe();
        return mBinding.getRoot();
    }

    private int getIndexTab() {
        return getArguments().getInt(ARG_INDEX_TAB);
    }

    @Override
    public void setPresenter(InputOrderContract.Presenter presenter) {

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
    public void onStop() {
        mPresenter.unSubscribe();
        super.onStop();
    }

    @Override
    public void inputOrderSuccess() {
        //TODO: input order success
        Dialog dialog = new DialogFullScreen.Builder(getContext()).setCenterContent(true)
                .setAutoClose(true)
                .setTitle(getContext().getResources()
                        .getString(R.string.input_order_dialog_success_title))
                .setContent("")
                .build();

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }
}
