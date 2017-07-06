package com.viettel.mbccs.screen.connector;

import android.content.Intent;
import android.databinding.ObservableField;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.Contract;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.source.remote.request.CheckIdNoRequest;
import com.viettel.mbccs.databinding.ActivityConnectMobilePrepaidViewPagerBinding;
import com.viettel.mbccs.screen.connector.adapter.ConnectMobileStatePagerAdapter;
import com.viettel.mbccs.screen.connector.fragment.prepaid.CreateNewConnectorInformationPrepaid1Fragment;
import com.viettel.mbccs.screen.connector.fragment.prepaid.CreateNewConnectorInformationPrepaid2Fragment;
import com.viettel.mbccs.screen.connector.fragment.prepaid.CreateNewConnectorInformationPrepaidFragmentPresenter;
import com.viettel.mbccs.screen.connector.listener.OnPageChange;
import java.util.ArrayList;
import java.util.List;

public class ConnectMobilePrepaidViewPagerActivity extends ConnectMobileViewPagerBaseActivity<ActivityConnectMobilePrepaidViewPagerBinding, ConnectMobilePrepaidViewPagerActivity>
        implements OnPageChange {
    public static final String ARG_CUSTOMER = "CUSTOMER";
    public static final String ARG_CONTRACT = "CONTRACT";
    public static final String ARG_CHECK_ID_NO_REQUEST = "CHECK_ID_NO_REQUEST";

    private CreateNewConnectorInformationPrepaid1Fragment postpaid1Fragment;
    private CreateNewConnectorInformationPrepaid2Fragment postpaid2Fragment;
    private CreateNewConnectorInformationPrepaidFragmentPresenter presenter;
    private List<String> titleList;
    private List<Fragment> fragmentList;

    private Customer customer;
    private Contract contract;
    private CheckIdNoRequest checkIdNoRequest;

    public ObservableField<ConnectMobileStatePagerAdapter> connectMobilePrepaidStatePagerAdapter;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_connect_mobile_prepaid_view_pager;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        customer = intent.getParcelableExtra(ARG_CUSTOMER);
        contract = intent.getParcelableExtra(ARG_CONTRACT);
        checkIdNoRequest = intent.getParcelableExtra(ARG_CHECK_ID_NO_REQUEST);

        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        connectMobilePrepaidStatePagerAdapter = new ObservableField<>();
        postpaid1Fragment = CreateNewConnectorInformationPrepaid1Fragment.newInstance();
        postpaid2Fragment = CreateNewConnectorInformationPrepaid2Fragment.newInstance();
        presenter =
                new CreateNewConnectorInformationPrepaidFragmentPresenter(this, customer, contract,
                        checkIdNoRequest);
        presenter.setView1(postpaid1Fragment);
        presenter.setView2(postpaid2Fragment);
        presenter.setOnPageChange(this);

        titleList.add(getString(R.string.create_new_connector_information_khach_hang));
        titleList.add(getString(R.string.create_new_connector_information_thue_bao));

        fragmentList = new ArrayList<>();
        fragmentList.add(postpaid1Fragment);
        fragmentList.add(postpaid2Fragment);

        connectMobilePrepaidStatePagerAdapter.set(
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
