package com.viettel.mbccs.screen.sell.orders.fragment.orderdetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.reflect.TypeToken;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.FragmentOrderDetailBinding;
import com.viettel.mbccs.screen.sell.orders.adapter.OrderDetailAdapter;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrderDetailFragment extends BaseFragment implements OrderDetailFragmentContract.View {
    private static final String ARG_ID_ORDER = "ID_ORDER";
    private FragmentOrderDetailBinding binding;
    private OrderDetailFragmentPresenter presenter;
    private List<ModelSale> goodItemList;
    private List<SaleOrdersDetail> saleOrdersDetailList;
    private OrderDetailAdapter orderDetailAdapter;

    public static OrderDetailFragment newInstance(long idOrder) {
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_ID_ORDER, idOrder);
        OrderDetailFragment fragment = new OrderDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderDetailBinding.inflate(inflater, container, false);
        presenter = new OrderDetailFragmentPresenter(getActivity(), this);
        binding.setPresenter(presenter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getDetailOrder((long) getArguments().get(ARG_ID_ORDER));
    }

    @Override
    public void setPresenter(OrderDetailFragmentContract.Presenter presenter) {

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
    public void setData(List<SaleOrdersDetail> items) {
        saleOrdersDetailList = items;
        orderDetailAdapter = new OrderDetailAdapter(saleOrdersDetailList);
        presenter.setAdapterOrderDetail(orderDetailAdapter);
        orderDetailAdapter.setOrderDetailAdapterCallback(presenter);
    }

    @Override
    public void getOrderInfoError(BaseException error) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            String json = data.getStringExtra(Constants.BundleConstant.LIST_SERIAL);
            Type collectionType = new TypeToken<List<Integer>>() {
            }.getType();
            List<Integer> serialBlockList = GsonUtils.String2Object(json, collectionType);
            presenter.setSerialBlockList(serialBlockList);
        }
    }
}
