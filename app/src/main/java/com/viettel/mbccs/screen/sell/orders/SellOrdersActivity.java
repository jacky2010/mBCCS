package com.viettel.mbccs.screen.sell.orders;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.constance.OrderStatus;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.ActivitySellOrdersBinding;
import com.viettel.mbccs.screen.sell.orders.adapter.SellOrdersFragmentAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/15/17.
 */

public class SellOrdersActivity
        extends BaseDataBindActivity<ActivitySellOrdersBinding, SellOrdersPresenter>
        implements SellOrdersContract.View {

    private SellOrdersPresenter presenter;
    private SellOrdersFragmentAdapter sellOrdersFragmentAdapter;
    private List<SaleOrders> saleOrdersList;
    private List<String> titleList;

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
        titleList = new ArrayList<>();
        initView();
    }

    @Override
    protected void onDestroy() {
        presenter.unSubscribe();
        super.onDestroy();
    }

    private void initView() {
        mBinding.spinnerChannelSellOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setPositionSelectChange(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        sellOrdersFragmentAdapter = new SellOrdersFragmentAdapter(getSupportFragmentManager());

        titleList.add(getString(R.string.sell_orders_title_pending, 0));
        titleList.add(getString(R.string.sell_orders_title_approvals, 0));
        titleList.add(getString(R.string.sell_orders_title_reject, 0));

        sellOrdersFragmentAdapter.setData(saleOrdersList, new ChannelInfo(), titleList);
        presenter.setSellOrdersFragmentAdapter(sellOrdersFragmentAdapter);
        mBinding.tabLayout.setupWithViewPager(mBinding.vpPager);
    }

    @Override
    public void setPresenter(SellOrdersContract.Presenter presenter) {

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
    public void setDataResult(List<SaleOrders> saleOrdersList, ChannelInfo channelInfoSelect) {
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

        titleList.clear();
        titleList.add(getString(R.string.sell_orders_title_pending, countPen));
        titleList.add(getString(R.string.sell_orders_title_approvals, countApp));
        titleList.add(getString(R.string.sell_orders_title_reject, countRe));

        sellOrdersFragmentAdapter.setData(saleOrdersList, channelInfoSelect, titleList);
    }

    @Override
    public void getDataError(BaseException error) {
        DialogUtils.showDialogError(this, error.getMessage());
    }

    @Override
    public void getListChannelByOwnerTypeIdError(BaseException error) {
        DialogUtils.showDialogError(this, error.getMessage());
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
        DialogUtils.showDialogError(this, "Thoi gian toi da 1 thang ");
    }
}
