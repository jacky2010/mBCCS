package com.viettel.mbccs.screen.sell.orders.fragment.orders;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.databinding.FragmentOrdersBinding;
import com.viettel.mbccs.screen.sell.orders.adapter.OrdersAdapter;
import com.viettel.mbccs.screen.sell.orders.fragment.orderdetail.OrderDetailFragment;
import com.viettel.mbccs.screen.sell.orders.listener.ChangeStatusOrderCallback;
import com.viettel.mbccs.widget.DividerItemDecoration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrdersFragment extends BaseFragment
        implements OrdersAdapter.OrdersAdapterCallback, ChangeStatusOrderCallback {
    public static final String STRING_NAME = "OrdersFragment";
    private static final String ARG_DATA = "DATA";
    private static final String ARG_CHANGE_INFO = "CHANGE_INFO";
    private FragmentOrdersBinding binding;
    private List<SaleOrders> saleOrdersList;
    private ChannelInfo channelInfoSale;
    private ChangeStatusOrderCallback callback;

    public ObservableField<OrdersAdapter> adapterOrders;
    public ObservableField<RecyclerView.ItemDecoration> itemDecoration;

    public static OrdersFragment newInstance(List<SaleOrders> saleOrdersList,
            ChannelInfo channelInfoSale) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARG_DATA, (ArrayList<? extends Parcelable>) saleOrdersList);
        bundle.putParcelable(ARG_CHANGE_INFO, channelInfoSale);
        OrdersFragment fragment = new OrdersFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        saleOrdersList = getArguments().getParcelableArrayList(ARG_DATA);
        channelInfoSale = getArguments().getParcelable(ARG_CHANGE_INFO);
        adapterOrders = new ObservableField<>();
        itemDecoration = new ObservableField<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        binding.setPresenter(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterOrders.set(new OrdersAdapter(getActivity(), saleOrdersList));
        adapterOrders.get().setOrdersAdapterCallback(this);
        itemDecoration.set(new DividerItemDecoration(getActivity(), R.drawable.divider));
    }

    @Override
    public void itemOrderClick(int position) {
        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(OrderDetailFragment.STRING_NAME);
        OrderDetailFragment orderDetailFragment =
                OrderDetailFragment.newInstance(saleOrdersList.get(position), channelInfoSale);
        orderDetailFragment.setConfirmTransactionSellCancelCallback(this);
        fragmentTransaction.replace(R.id.frame_sell_orders, orderDetailFragment).commit();
    }

    public void setConfirmTransactionSellCancelCallback(ChangeStatusOrderCallback callback) {
        this.callback = callback;
    }

    @Override
    public void callback(long saleOrdersId, @OrderStatus String orderStatus) {
        if (this.callback != null) {
            this.callback.callback(saleOrdersId, orderStatus);
        }
    }
}
