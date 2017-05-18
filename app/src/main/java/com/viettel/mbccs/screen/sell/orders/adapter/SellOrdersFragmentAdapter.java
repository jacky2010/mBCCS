package com.viettel.mbccs.screen.sell.orders.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.screen.sell.orders.fragment.orders.OrdersFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class SellOrdersFragmentAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    private List<SaleOrders> orderListApprovals;
    private List<SaleOrders> orderListPending;
    private List<SaleOrders> orderListReject;

    public SellOrdersFragmentAdapter(FragmentManager fm, List<SaleOrders> saleOrderses) {
        super(fm);
        orderListApprovals = new ArrayList<>();
        orderListPending = new ArrayList<>();
        orderListReject = new ArrayList<>();
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
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OrdersFragment.newInstance(orderListPending);
            case 1:
                return OrdersFragment.newInstance(orderListApprovals);
            case 2:
                return OrdersFragment.newInstance(orderListReject);
            //            default:
            //                return null;
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the page idOrder for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
