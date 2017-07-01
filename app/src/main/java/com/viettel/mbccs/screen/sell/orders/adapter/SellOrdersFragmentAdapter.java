package com.viettel.mbccs.screen.sell.orders.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.screen.sell.orders.fragment.orders.OrdersFragment;
import com.viettel.mbccs.screen.sell.orders.listener.ChangeStatusOrderCallback;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class SellOrdersFragmentAdapter extends FragmentStatePagerAdapter
        implements ChangeStatusOrderCallback {
    private static int NUM_ITEMS = 3;
    private List<SaleOrders> orderListApprovals;
    private List<SaleOrders> orderListPending;
    private List<SaleOrders> orderListReject;
    private List<String> listTitle;
    private ChannelInfo channelInfoSell;
    private ChangeStatusOrderCallback callback;

    public SellOrdersFragmentAdapter(FragmentManager fm) {
        super(fm);
        orderListApprovals = new ArrayList<>();
        orderListPending = new ArrayList<>();
        orderListReject = new ArrayList<>();
    }

    public SellOrdersFragmentAdapter(FragmentManager fm, List<SaleOrders> saleOrderses,
            ChannelInfo channelInfoSell, List<String> listTitle) {
        super(fm);
        orderListApprovals = new ArrayList<>();
        orderListPending = new ArrayList<>();
        orderListReject = new ArrayList<>();
        this.channelInfoSell = channelInfoSell;
        this.listTitle = listTitle;
        for (SaleOrders o : saleOrderses) {
            if (o.getOrderStatus().equals(OrderStatus.APPROVALS)) {
                orderListApprovals.add(o);
            } else if (o.getOrderStatus().equals(OrderStatus.PENDING)) {
                orderListPending.add(o);
            } else {
                orderListReject.add(o);
            }
        }
    }

    @Override
    public int getItemPosition(Object object){
        return PagerAdapter.POSITION_NONE;
    }

    public void setData(List<SaleOrders> saleOrderses, ChannelInfo channelInfoSell,
            List<String> listTitle) {
        this.channelInfoSell = channelInfoSell;
        this.listTitle = listTitle;
        orderListApprovals.clear();
        orderListPending.clear();
        orderListReject.clear();
        for (SaleOrders o : saleOrderses) {
            if (o.getOrderStatus().equals(OrderStatus.APPROVALS)) {
                orderListApprovals.add(o);
            } else if (o.getOrderStatus().equals(OrderStatus.PENDING)) {
                orderListPending.add(o);
            } else {
                orderListReject.add(o);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OrdersFragment fragmentPending =
                        OrdersFragment.newInstance(orderListPending, channelInfoSell);
                fragmentPending.setConfirmTransactionSellCancelCallback(this);
                return fragmentPending;
            case 1:
                OrdersFragment fragmentApprovals =
                        OrdersFragment.newInstance(orderListApprovals, channelInfoSell);
                fragmentApprovals.setConfirmTransactionSellCancelCallback(this);
                return fragmentApprovals;
            case 2:
                OrdersFragment fragmentReject =
                        OrdersFragment.newInstance(orderListReject, channelInfoSell);
                fragmentReject.setConfirmTransactionSellCancelCallback(this);
                return fragmentReject;
            //            default:
            //                return null;
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
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
