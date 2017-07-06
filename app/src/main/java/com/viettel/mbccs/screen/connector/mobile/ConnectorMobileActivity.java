package com.viettel.mbccs.screen.connector.mobile;

import android.content.Intent;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.constance.MobileType;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.source.remote.request.CheckIdNoRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.ActivityConnectorMobileBinding;
import com.viettel.mbccs.screen.connector.ConnectMobilePostpaidViewPagerActivity;
import com.viettel.mbccs.screen.connector.ConnectMobilePrepaidViewPagerActivity;
import com.viettel.mbccs.screen.connector.adapter.ConnectorMobileAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.widget.MultiDirectionSlidingDrawer;
import java.util.List;

public class ConnectorMobileActivity
        extends BaseDataBindActivity<ActivityConnectorMobileBinding, ConnectorMobilePresenter>
        implements ConnectorMobileContract.View {

    private ConnectorMobileAdapter connectorMobileAdapter;
    private Customer customer;
    private List<Contract> contractList;
    private CheckIdNoRequest checkIdNoRequest;


    @Override
    protected int getIdLayout() {
        return R.layout.activity_connector_mobile;
    }

    @Override
    protected void initData() {
        mPresenter = new ConnectorMobilePresenter(this, this);
        mPresenter.subscribe();
        mBinding.setPresenter(mPresenter);
        mBinding.drawer.setOnDrawerCloseListener(
                new MultiDirectionSlidingDrawer.OnDrawerCloseListener() {
                    @Override
                    public void onDrawerClosed() {
                        mPresenter.setTextHideSearch();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.unSubscribe();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.drawer.open();
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
    public void onCancel() {
        onBackPressed();
    }

    @Override
    public void searchError(BaseException error) {
        DialogUtils.showDialogError(this, error);
    }

    @Override
    public void checkIdNoSuccess(Customer customer, List<Contract> contractList) {
        this.customer = customer;
        this.contractList = contractList;
        connectorMobileAdapter = new ConnectorMobileAdapter(contractList);
        connectorMobileAdapter.setConnectorMobileAdapterCallback(mPresenter);
        mPresenter.setConnectorMobileAdapter(connectorMobileAdapter);
    }

    @Override
    public void onItemClick(int position) {
        if (checkIdNoRequest.getSubType().equals(MobileType.TRA_TRUOC)) {
            Intent intent =
                    new Intent(ConnectorMobileActivity.this, ConnectMobilePrepaidViewPagerActivity.class);
            intent.putExtra(ConnectMobilePrepaidViewPagerActivity.ARG_CONTRACT,
                    contractList != null && contractList.size() != 0 ? contractList.get(position)
                            : null);
            intent.putExtra(ConnectMobilePrepaidViewPagerActivity.ARG_CUSTOMER, customer);
            intent.putExtra(ConnectMobilePrepaidViewPagerActivity.ARG_CHECK_ID_NO_REQUEST,
                    checkIdNoRequest);
            startActivity(intent);
        } else {
            Intent intent =
                    new Intent(ConnectorMobileActivity.this, ConnectMobilePostpaidViewPagerActivity.class);
            intent.putExtra(ConnectMobilePostpaidViewPagerActivity.ARG_CONTRACT,
                    contractList != null && contractList.size() != 0 ? contractList.get(position)
                            : null);
            intent.putExtra(ConnectMobilePostpaidViewPagerActivity.ARG_CUSTOMER, customer);
            intent.putExtra(ConnectMobilePostpaidViewPagerActivity.ARG_CHECK_ID_NO_REQUEST,
                    checkIdNoRequest);
            startActivity(intent);
        }
    }

    @Override
    public void closeFormSearch() {
        mBinding.drawer.animateClose();
    }

    @Override
    public void getCustomerSuccess(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void setDataFormSearch(CheckIdNoRequest checkIdNoRequest) {
        this.checkIdNoRequest = checkIdNoRequest;
    }
}
