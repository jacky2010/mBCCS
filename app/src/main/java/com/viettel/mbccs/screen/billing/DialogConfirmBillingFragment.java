package com.viettel.mbccs.screen.billing;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.callback.OnListenerItemRecyclerView;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.BillingModel;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.BillingRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetCreateInvoiceBillRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.screen.billing.adapter.BillingConfirmAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.ToolBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jacky on 5/20/17.
 */

public class DialogConfirmBillingFragment extends BaseDialog {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    private ArrayList<SaleTrans> mListSaleTransChoose = new ArrayList<>();

    private BillingRepository mBillingRepository;
    private CompositeSubscription mCompositeSubscription;
    private BillingConfirmAdapter mBillingConfirmAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private UserRepository mUserRepository;

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

        mLinearLayoutManager =
                new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);

        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected int idLayoutRes() {
        return R.layout.dialog_confirm_billing_fragment;
    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            mListSaleTransChoose = getArguments().getParcelableArrayList(SaleTrans.class.getName());
        }
        mUserRepository = UserRepository.getInstance();

        mBillingRepository = BillingRepository.getInstance();
        mCompositeSubscription = new CompositeSubscription();
        mBillingConfirmAdapter = new BillingConfirmAdapter(mListSaleTransChoose, getBaseActivity());
        mBillingConfirmAdapter.setOnClickItemRecycleView(
                new OnListenerItemRecyclerView<SaleTrans>() {
                    @Override
                    public void onClickItem(SaleTrans model, int position) {

                    }
                });
        mRecyclerView.setAdapter(mBillingConfirmAdapter);
    }

    @Override
    protected int getStyleDialog() {
        return 0;
    }

    @OnClick({ R.id.biv_close, R.id.biv_done })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.biv_close:
                dismiss();
                break;
            case R.id.biv_done:
                createInvoiceBill();
                break;
            default:
                break;
        }
    }

    private void createInvoiceBill() {
        getBaseActivity().showLoadingDialog();
        final DataRequest<GetCreateInvoiceBillRequest> request = new DataRequest<>();
        GetCreateInvoiceBillRequest mGetCreateInvoiceBillRequest =
                new GetCreateInvoiceBillRequest();
        mGetCreateInvoiceBillRequest.mShopId = Integer.valueOf(
                String.valueOf(mUserRepository.getUserInfo().getShop().getShopId()));
        mGetCreateInvoiceBillRequest.mStaffId =
                mUserRepository.getUserInfo().getStaffInfo().getStaffId();
        List<Long> mList = new ArrayList<>();
        for (SaleTrans saleTrans : mListSaleTransChoose) {
            mList.add(saleTrans.getSaleTransId());
        }
        mGetCreateInvoiceBillRequest.mListSaleTrans =
                mGetCreateInvoiceBillRequest.getListSaleTransFromChoose(mList);

        request.setWsRequest(mGetCreateInvoiceBillRequest);
        request.setWsCode(WsCode.CreateInvoice);

        Subscription subscription = mBillingRepository.createInvoiceBill(request)
                .subscribe(new MBCCSSubscribe<EmptyObject>() {
                    @Override
                    public void onSuccess(EmptyObject response) {
                        getBaseActivity().goToDialogFragment(new DialogSuccessFragment(), null);
                        dismiss();
                        getBaseActivity().hideLoadingDialog();
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialog(getBaseActivity(), null, error.getMessage(), null);
                        getBaseActivity().hideLoadingDialog();
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
