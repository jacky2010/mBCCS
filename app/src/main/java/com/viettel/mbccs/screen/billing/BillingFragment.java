package com.viettel.mbccs.screen.billing;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataFragment;
import com.viettel.mbccs.callback.OnListenerItemRecyclerView;
import com.viettel.mbccs.data.model.BillingModel;
import com.viettel.mbccs.screen.billing.adapter.BillingAdapter;
import com.viettel.mbccs.widget.CustomDatePicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BillingFragment extends BaseDataFragment {

    @BindView(R.id.lyHeader) LinearLayout mHeader;
    @BindView(R.id.search_text) LinearLayout mSearchText;
    @BindView(R.id.list) RecyclerView mRecyclerView;
    @BindView(R.id.dp_to_date) CustomDatePicker mToDate;
    @BindView(R.id.dp_from_date) CustomDatePicker mFormDate;
    @BindView(R.id.tv_list_trans) TextView mListTrans;

    private BillingAdapter mBillingAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @OnClick({R.id.bt_search, R.id.bt_billing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_search:
                showViewSearchDetail(true);
                break;
            case R.id.bt_billing:
                getBaseActivity().goToDialogFragment(new DialogConfirmBillingFragment(), null);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {
        mListTrans.setText(String.format(getString(R.string.msg_list_trans),listFake().size()));
        mBillingAdapter = new BillingAdapter(listFake(), getBaseActivity());
        mBillingAdapter.setOnClickItemRecycleView(new OnListenerItemRecyclerView<BillingModel>() {
            @Override
            public void onClickItem(BillingModel model, int position) {

            }
        });
        mRecyclerView.setAdapter(mBillingAdapter);
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_billing;
    }

    @Override
    protected void initView() {
        mLinearLayoutManager = new LinearLayoutManager(getBaseActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL);

        mRecyclerView.addItemDecoration(dividerItemDecoration);
        showViewSearchDetail(false);
    }

    private void showViewSearchDetail(boolean isActive) {
        mHeader.setVisibility(!isActive ? View.VISIBLE : View.GONE);
        mSearchText.setVisibility(isActive ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().setTitleToolbar(R.string.billing_title);
    }

    private List<BillingModel> listFake() {
        List<BillingModel> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BillingModel model = new BillingModel();
            mList.add(model);
        }
        return mList;
    }
}
