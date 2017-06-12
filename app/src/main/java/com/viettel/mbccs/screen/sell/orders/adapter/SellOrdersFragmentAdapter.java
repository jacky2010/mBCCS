package com.viettel.mbccs.screen.sell.orders.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.screen.sell.orders.fragment.orders.OrdersFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class SellOrdersFragmentAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 3;
    private List<SaleOrders> orderListApprovals;
    private List<SaleOrders> orderListPending;
    private List<SaleOrders> orderListReject;
    private List<String> listTitle;
    private ChannelInfo channelInfoSell;

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
            switch ((int) o.getOrderStatus()) {
                case OrderStatus.APPROVALS:
                    orderListApprovals.add(o);
                    break;
                case OrderStatus.PENDING:
                    orderListPending.add(o);
                    break;
                case OrderStatus.REJECT:
                    orderListReject.add(o);
                    break;
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
            switch ((int) o.getOrderStatus()) {
                case OrderStatus.APPROVALS:
                    orderListApprovals.add(o);
                    break;
                case OrderStatus.PENDING:
                    orderListPending.add(o);
                    break;
                case OrderStatus.REJECT:
                    orderListReject.add(o);
                    break;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OrdersFragment.newInstance(orderListPending, channelInfoSell);
            case 1:
                return OrdersFragment.newInstance(orderListApprovals, channelInfoSell);
            case 2:
                return OrdersFragment.newInstance(orderListReject, channelInfoSell);
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
}
