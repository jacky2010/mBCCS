package com.viettel.mbccs.screen.connector.mobile;

import android.support.v4.app.FragmentTransaction;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.ActivityConnectorMobileBinding;
import com.viettel.mbccs.screen.connector.adapter.ConnectorMobileAdapter;
import com.viettel.mbccs.screen.connector.fragment.CreateNewConnectorInformation1Fragment;
import com.viettel.mbccs.utils.DialogUtils;
import java.util.List;

public class ConnectorMobileActivity
        extends BaseDataBindActivity<ActivityConnectorMobileBinding, ConnectorMobilePresenter>
        implements ConnectorMobileContract.View {

    private ConnectorMobileAdapter connectorMobileAdapter;
    private Customer customer;
    private List<Contract> contractList;


    @Override
    protected int getIdLayout() {
        return R.layout.activity_connector_mobile;
    }

    @Override
    protected void initData() {
        mPresenter = new ConnectorMobilePresenter(this, this);
        mPresenter.subscribe();
        mBinding.setPresenter(mPresenter);
    }

    @Override
    protected void onStart() {
        mPresenter.unSubscribe();
        super.onStart();
    }

    @Override
    public void setPresenter(ConnectorMobileContract.Presenter presenter) {

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
    public void txtPassportEmpty() {
        DialogUtils.showDialogError(this, "Số giấy tờ trống!");
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
        CreateNewConnectorInformation1Fragment fragment;
        if (position == -1) {
            fragment = CreateNewConnectorInformation1Fragment.newInstance(null, null);
        } else {
            fragment = CreateNewConnectorInformation1Fragment.newInstance(customer,
                    contractList.get(position));
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_connector_mobile, fragment);
        transaction.addToBackStack(CreateNewConnectorInformation1Fragment.STRING_NAME);
        transaction.commit();
    }
}
