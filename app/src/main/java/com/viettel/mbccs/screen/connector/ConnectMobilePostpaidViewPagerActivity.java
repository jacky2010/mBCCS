package com.viettel.mbccs.screen.connector;

import android.content.Intent;
import android.databinding.ObservableField;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.source.remote.request.CheckIdNoRequest;
import com.viettel.mbccs.databinding.ActivityConnectMobilePostpaidViewPagerBinding;
import com.viettel.mbccs.screen.connector.adapter.ConnectMobileStatePagerAdapter;
import com.viettel.mbccs.screen.connector.fragment.postpaid.CreateNewConnectorInformationPostpaid1Fragment;
import com.viettel.mbccs.screen.connector.fragment.postpaid.CreateNewConnectorInformationPostpaid2Fragment;
import com.viettel.mbccs.screen.connector.fragment.postpaid.CreateNewConnectorInformationPostpaid3Fragment;
import com.viettel.mbccs.screen.connector.fragment.postpaid.CreateNewConnectorInformationPostpaidFragmentPresenter;
import com.viettel.mbccs.screen.connector.fragment.postpaid.CreateNewConnectorInformationPostpaidViewModel;
import com.viettel.mbccs.screen.connector.listener.OnPageChange;
import java.util.ArrayList;
import java.util.List;

public class ConnectMobilePostpaidViewPagerActivity extends ConnectMobileViewPagerBaseActivity<ActivityConnectMobilePostpaidViewPagerBinding, ConnectMobilePostpaidViewPagerActivity>
        implements OnPageChange {
    public static final String ARG_CUSTOMER = "CUSTOMER";
    public static final String ARG_CONTRACT = "CONTRACT";
    public static final String ARG_CHECK_ID_NO_REQUEST = "CHECK_ID_NO_REQUEST";

    private CreateNewConnectorInformationPostpaid1Fragment postpaid1Fragment;
    private CreateNewConnectorInformationPostpaid2Fragment postpaid2Fragment;
    private CreateNewConnectorInformationPostpaid3Fragment postpaid3Fragment;
    private CreateNewConnectorInformationPostpaidFragmentPresenter presenter;
    private CreateNewConnectorInformationPostpaidViewModel viewModel;
    private List<String> titleList;
    private List<Fragment> fragmentList;

    private Customer customer;
    private Contract contract;
    private CheckIdNoRequest checkIdNoRequest;

    public ObservableField<ConnectMobileStatePagerAdapter> connectMobilePostpaidStatePagerAdapter;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_connect_mobile_postpaid_view_pager;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        customer = intent.getParcelableExtra(ARG_CUSTOMER);
        contract = intent.getParcelableExtra(ARG_CONTRACT);
        checkIdNoRequest = intent.getParcelableExtra(ARG_CHECK_ID_NO_REQUEST);

        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        connectMobilePostpaidStatePagerAdapter = new ObservableField<>();
        postpaid1Fragment = CreateNewConnectorInformationPostpaid1Fragment.newInstance();
        postpaid2Fragment = CreateNewConnectorInformationPostpaid2Fragment.newInstance();
        postpaid3Fragment = CreateNewConnectorInformationPostpaid3Fragment.newInstance();

        viewModel = new CreateNewConnectorInformationPostpaidViewModel();
        presenter =
                new CreateNewConnectorInformationPostpaidFragmentPresenter(this, viewModel, customer, contract,
                        checkIdNoRequest);
        presenter.setView1(postpaid1Fragment);
        presenter.setView2(postpaid2Fragment);
        presenter.setView3(postpaid3Fragment);
        presenter.setOnPageChange(this);

        titleList.add(getString(R.string.create_new_connector_information_khach_hang));
        titleList.add(getString(R.string.create_new_connector_information_thue_bao));
        titleList.add(getString(R.string.create_new_connector_information_hop_dong));

        fragmentList = new ArrayList<>();
        fragmentList.add(postpaid1Fragment);
        fragmentList.add(postpaid2Fragment);
        fragmentList.add(postpaid3Fragment);

        mBinding.vpPager.setOffscreenPageLimit(3);
        connectMobilePostpaidStatePagerAdapter.set(
                new ConnectMobileStatePagerAdapter(getSupportFragmentManager(), fragmentList,
                        titleList));

        mBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mBinding.tabLayout.setupWithViewPager(mBinding.vpPager);
        mBinding.setPresenter(this);
    }

    @Override
    public void onPageChange(int position) {
        mBinding.vpPager.setCurrentItem(position);
    }

    public void onCancel() {
        int currentPosition = mBinding.vpPager.getCurrentItem();
        if (currentPosition == 0) {
            onBackPressed();
        } else {
            onPageChange(currentPosition - 1);
        }
    }
}
