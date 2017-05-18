package com.viettel.mbccs.screen.sell.orders;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.databinding.ActivitySellOrdersBinding;
import com.viettel.mbccs.screen.sell.orders.adapter.SellOrdersFragmentAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/15/17.
 */

public class SellOrdersActivity
        extends BaseDataBindActivity<ActivitySellOrdersBinding, SellOrdersPresenter>
        implements SellOrdersContract.View, TabLayout.OnTabSelectedListener {

    private SellOrdersPresenter presenter;
    private SellOrdersFragmentAdapter sellOrdersFragmentAdapter;

    @Override
    protected ActivitySellOrdersBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_sell_orders);
    }

    @Override
    protected void initData() {
        presenter = new SellOrdersPresenter(this, this);
        presenter.subscribe();
        mBinding.setPresenter(presenter);
        initView();
    }

    @Override
    protected void onDestroy() {
        presenter.unSubscribe();
        super.onDestroy();
    }

    private void initView() {
        mBinding.spinnerChannel.setOnItemSelectedListener(presenter);

        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab()
                .setText(getString(R.string.sell_orders_title_pending, 0)));
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab()
                .setText(getString(R.string.sell_orders_title_approvals, 0)));
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab()
                .setText(getString(R.string.sell_orders_title_reject, 0)));
        mBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mBinding.tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void setPresenter(SellOrdersContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mBinding.vpPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void setDataResult(List<SaleOrders> saleOrdersList) {
        int countApp = 0, countPen = 0, countRe = 0;
        for (SaleOrders saleOrders : saleOrdersList) {
            switch ((int) saleOrders.getOrderStatus()) {
                case OrderStatus.APPROVALS:
                    countApp++;
                    break;
                case OrderStatus.PENDING:
                    countPen++;
                    break;
                case OrderStatus.REJECT:
                    countRe++;
                    break;
            }
        }
        mBinding.tabLayout.getTabAt(0)
                .setText(getString(R.string.sell_orders_title_pending, countApp));
        mBinding.tabLayout.getTabAt(1)
                .setText(getString(R.string.sell_orders_title_approvals, countPen));
        mBinding.tabLayout.getTabAt(2)
                .setText(getString(R.string.sell_orders_title_reject, countRe));
        sellOrdersFragmentAdapter =
                new SellOrdersFragmentAdapter(getSupportFragmentManager(), saleOrdersList);
        presenter.setSellOrdersFragmentAdapter(sellOrdersFragmentAdapter);
        presenter.setTotalOrders(saleOrdersList.size());
    }

    @Override
    public void getDataError() {
        // Fake data
        List<SaleOrders> saleOrdersList = new ArrayList<>();
        mBinding.tabLayout.getTabAt(0).setText(getString(R.string.sell_orders_title_pending, 10));
        mBinding.tabLayout.getTabAt(1).setText(getString(R.string.sell_orders_title_approvals, 10));
        mBinding.tabLayout.getTabAt(2).setText(getString(R.string.sell_orders_title_reject, 10));
        presenter.setTotalOrders(30);
        for (int i = 0; i < 30; i++) {
            SaleOrders order = new SaleOrders();
            order.setOrderDate("17/05/2017");
            order.setSaleOrdersId(10000000);
            order.setChannelCode("10000000");
            order.setChannelName("kenh cua bo may");
            if (i < 10) {
                order.setOrderStatus(OrderStatus.APPROVALS);
                order.setOderName("APPROVALS");
            } else if (10 < i && i < 20) {
                order.setOrderStatus(OrderStatus.PENDING);
                order.setOderName("PENDING");
            } else {
                order.setOrderStatus(OrderStatus.REJECT);
                order.setOderName("REJECT");
            }
            saleOrdersList.add(order);
        }
        sellOrdersFragmentAdapter =
                new SellOrdersFragmentAdapter(getSupportFragmentManager(), saleOrdersList);
        presenter.setSellOrdersFragmentAdapter(sellOrdersFragmentAdapter);
    }
}
