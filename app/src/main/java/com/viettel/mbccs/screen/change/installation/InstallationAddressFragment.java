package com.viettel.mbccs.screen.change.installation;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataFragment;
import com.viettel.mbccs.callback.OnListenerItemRecyclerView;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.screen.change.installation.adapter.InstallationAddressAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jacky on 5/22/17.
 */

public class InstallationAddressFragment extends BaseDataFragment {

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private InstallationAddressAdapter mAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_install_address;
    }

    @Override
    protected void initView() {
        mLinearLayoutManager = new LinearLayoutManager(getBaseActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().setTitleToolbar(R.string.title_install_address);
    }

    private void showSearchInfoCustomer() {
        mAdapter = new InstallationAddressAdapter(listCustomer(), getBaseActivity());
        mAdapter.setOnClickItemRecycleView(new OnListenerItemRecyclerView<Customer>() {
            @Override
            public void onClickItem(Customer mCustomer, int position) {
                if (mCustomer != null) {
                    getBaseActivity().openActivity(InstallationAddressDetailActivity.class);
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Customer> listCustomer() {
        List<Customer> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Customer model = new Customer();
            mList.add(model);
        }
        return mList;
    }

    @OnClick({R.id.bt_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_search:
                showSearchInfoCustomer();
                break;
            default:
                break;
        }
    }
}
