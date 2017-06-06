package com.viettel.mbccs.screen.manager.area.dialog;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.callback.OnListenerItemRecyclerView;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.model.BillingModel;
import com.viettel.mbccs.screen.manager.area.adapter.BTSAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jacky on 6/6/17.
 */

public class DialogSearchBTSFragment extends BaseDialog {

    @BindView(R.id.list) RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;

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
    protected int idLayoutRes() {
        return R.layout.dialog_bts_search_fragment;
    }

    @Override
    protected void initData() {
        BTSAdapter mBtsAdapter = new BTSAdapter(null, getBaseActivity());
        mBtsAdapter.setOnClickItemRecycleView(new OnListenerItemRecyclerView<Area>() {
            @Override
            public void onClickItem(Area mArea, int position) {

            }
        });
        mRecyclerView.setAdapter(mBtsAdapter);
    }

    @Override
    protected int getStyleDialog() {
        return 0;
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
