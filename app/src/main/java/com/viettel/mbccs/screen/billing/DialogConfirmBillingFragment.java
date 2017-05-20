package com.viettel.mbccs.screen.billing;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.callback.OnListenerItemRecyclerView;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.data.model.BillingModel;
import com.viettel.mbccs.screen.billing.adapter.BillingConfirmAdapter;
import com.viettel.mbccs.widget.ToolBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jacky on 5/20/17.
 */

public class DialogConfirmBillingFragment extends BaseDialog {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    private BillingConfirmAdapter mBillingConfirmAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void initView() {
        mToolBar.setOnClickIconListener(new ToolBarView.OnClickIconListener() {
            @Override
            public void onClickIconLeft(int mIconType) {
                switch (mIconType) {
                    case IconType.LEFT:
                        dismiss();
                        break;
                    case IconType.RIGHT:
                        break;
                }
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(getBaseActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL);

        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected int idLayoutRes() {
        return R.layout.dialog_confirm_billing_fragment;
    }

    @Override
    protected void initData() {
        mBillingConfirmAdapter = new BillingConfirmAdapter(listFake(), getBaseActivity());
        mBillingConfirmAdapter.setOnClickItemRecycleView(new OnListenerItemRecyclerView<BillingModel>() {
            @Override
            public void onClickItem(BillingModel model, int position) {

            }
        });
        mRecyclerView.setAdapter(mBillingConfirmAdapter);
    }

    @Override
    protected int getStyleDialog() {
        return 0;
    }

    @OnClick({R.id.biv_close, R.id.biv_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.biv_close:
                dismiss();
                break;
            case R.id.biv_done:
                getBaseActivity().goToDialogFragment(new DialogSuccessFragment(), null);
                dismiss();
                break;
            default:
                break;
        }
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
