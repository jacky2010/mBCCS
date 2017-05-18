package com.viettel.mbccs.screen.sell.orders.fragment.orderdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.model.GoodItem;
import com.viettel.mbccs.databinding.FragmentOrderDetailBinding;
import com.viettel.mbccs.screen.sell.orders.adapter.OrderDetailAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrderDetailFragment extends BaseFragment implements OrderDetailFragmentContract.View {
    private static final String ARG_ID_ORDER = "ID_ORDER";
    private FragmentOrderDetailBinding binding;
    private OrderDetailFragmentPresenter presenter;
    private List<GoodItem> goodItemList;
    private OrderDetailAdapter orderDetailAdapter;

    public static OrderDetailFragment newInstance(String idOrder) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ID_ORDER, idOrder);
        OrderDetailFragment fragment = new OrderDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goodItemList = new ArrayList<>();
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
        presenter.getDetailOrder((String) getArguments().get(ARG_ID_ORDER));
    }

    @Override
    public void setPresenter(OrderDetailFragmentContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(List<GoodItem> items) {
        goodItemList = items;
        orderDetailAdapter = new OrderDetailAdapter(goodItemList);
        presenter.setAdapterOrderDetail(orderDetailAdapter);
        orderDetailAdapter.setOrderDetailAdapterCallback(presenter);
    }
}
