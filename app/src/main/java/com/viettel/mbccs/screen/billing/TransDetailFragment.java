package com.viettel.mbccs.screen.billing;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataFragment;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.BillingRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetSaleTransDetailRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetSaleTransDetailResponse;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jacky on 7/4/17.
 */

public class TransDetailFragment extends BaseDataFragment {

    private BillingRepository mBillingRepository;
    private CompositeSubscription mCompositeSubscription;
    private SaleTrans mSaleTrans;

    @Override
    protected void initData() {
        if (getArguments() != null) {
            mSaleTrans = getArguments().getParcelable(SaleTrans.class.getName());
        }
        mBillingRepository = BillingRepository.getInstance();
        mCompositeSubscription = new CompositeSubscription();
        callApiGetTransDetail();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_trans_detail;
    }

    @Override
    protected void initView() {

    }

    private void callApiGetTransDetail() {
        getBaseActivity().showLoadingDialog();
        final DataRequest<GetSaleTransDetailRequest> request = new DataRequest<>();
        GetSaleTransDetailRequest mGetSaleTransDetailRequest = new GetSaleTransDetailRequest();
        mGetSaleTransDetailRequest.mSaleTransId = mSaleTrans.getSaleTransId();//mUserRepository.getUserInfo().getShop().getShopId()

        request.setWsRequest(mGetSaleTransDetailRequest);
        request.setWsCode(WsCode.GetSaleTransDetail);

        Subscription subscription = mBillingRepository.getSaleTransDetail(request)
                .subscribe(new MBCCSSubscribe<GetSaleTransDetailResponse>() {
                    @Override
                    public void onSuccess(GetSaleTransDetailResponse response) {
                        getBaseActivity().hideLoadingDialog();
                    }

                    @Override
                    public void onError(BaseException error) {
                        getBaseActivity().hideLoadingDialog();
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
