package com.viettel.mbccs.screen.sell.orders;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.ActivitySellOrdersBinding;
import com.viettel.mbccs.screen.sell.orders.adapter.SellOrdersFragmentAdapter;
import com.viettel.mbccs.screen.sell.orders.listener.ChangeStatusOrderCallback;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.widget.MultiDirectionSlidingDrawer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/15/17.
 */

public class SellOrdersActivity
        extends BaseDataBindActivity<ActivitySellOrdersBinding, SellOrdersPresenter>
        implements SellOrdersContract.View, ChangeStatusOrderCallback {

    private SellOrdersPresenter presenter;
    private SellOrdersFragmentAdapter sellOrdersFragmentAdapter;
    private List<SaleOrders> saleOrdersList;
    private ChannelInfo channelInfo;
    private List<String> titleList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding.drawer.setOnDrawerCloseListener(
                new MultiDirectionSlidingDrawer.OnDrawerCloseListener() {
                    @Override
                    public void onDrawerClosed() {
                        presenter.setTextHideSearch();
                    }
                });
    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_sell_orders;
    }

    @Override
    protected void initData() {
        presenter = new SellOrdersPresenter(this, this);
        mBinding.setPresenter(presenter);
        presenter.subscribe();
        saleOrdersList = new ArrayList<>();
        channelInfo = new ChannelInfo();
        titleList = new ArrayList<>();

        mBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        sellOrdersFragmentAdapter = new SellOrdersFragmentAdapter(getSupportFragmentManager());
        sellOrdersFragmentAdapter.setConfirmTransactionSellCancelCallback(this);
        presenter.setSellOrdersFragmentAdapter(sellOrdersFragmentAdapter);
        mBinding.tabLayout.setupWithViewPager(mBinding.vpPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    @Override
    protected void onDestroy() {
        presenter.unSubscribe();
        super.onDestroy();
    }

    private void initView() {
        setTitleList(0, 0, 0);
        sellOrdersFragmentAdapter.setData(saleOrdersList, channelInfo, titleList);
    }

    @Override
    public void setPresenter(SellOrdersContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        showLoadingDialog(false);
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void setDataResult(List<SaleOrders> saleOrders, ChannelInfo channelInfoSelect) {
        if (saleOrdersList != null && saleOrdersList.size() != 0) {
            saleOrdersList.clear();
        }
        this.saleOrdersList = saleOrders;
        this.channelInfo = channelInfoSelect;
        int countApp = 0, countPen = 0, countRe = 0;
        if (saleOrdersList == null || saleOrdersList.size() == 0) {
            DialogUtils.showDialog(this, null, "Không tìm thấy dữ liệu", "OK", null, null, null);
            setTitleList(countPen, countApp, countRe);

            sellOrdersFragmentAdapter.setData(
                    saleOrdersList == null ? new ArrayList<SaleOrders>() : saleOrdersList,
                    channelInfoSelect, titleList);
            return;
        }

        for (SaleOrders saleOrder : this.saleOrdersList) {
            if (saleOrder.getOrderStatus().equals(OrderStatus.APPROVALS)) {
                countApp++;
            } else if (saleOrder.getOrderStatus().equals(OrderStatus.PENDING)) {
                countPen++;
            } else {
                countRe++;
            }
        }

        setTitleList(countPen, countApp, countRe);

        sellOrdersFragmentAdapter.setData(this.saleOrdersList, channelInfo, titleList);
    }

    private void setTitleList(int countPen, int countApp, int countRe) {
        titleList.clear();
        titleList.add(getString(R.string.sell_orders_title_pending, countPen));
        titleList.add(getString(R.string.sell_orders_title_approvals, countApp));
        titleList.add(getString(R.string.sell_orders_title_reject, countRe));
    }
    @Override
    public void getDataError(BaseException error) {
        DialogUtils.showDialog(this, error.getMessage());
    }

    @Override
    public void getListChannelByOwnerTypeIdError(BaseException error) {
        DialogUtils.showDialogError(this, error, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        }, false);
    }

    @Override
    public void getListChannelByOwnerTypeIdError(String error) {
        DialogUtils.showDialog(this, null, error, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        }, false);
    }

    @Override
    public long  getDateTo() {
        return mBinding.dateTo.getDateInMilis();
    }

    @Override
    public long getDateFrom() {
        return mBinding.dateFrom.getDateInMilis();
    }

    @Override
    public String getStringDateTo() {
        return mBinding.dateTo.getStringDate();
    }

    @Override
    public String getStringDateFrom() {
        return mBinding.dateFrom.getStringDate();
    }

    @Override
    public void showErrorDate() {
        DialogUtils.showDialog(this, "Thoi gian toi da 1 thang ");
    }

    @Override
    public void callback(long saleOrdersId, @OrderStatus String orderStatus) {
        presenter.clickSearch();
    }
}
