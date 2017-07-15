package com.viettel.mbccs.screen.billing;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataFragment;
import com.viettel.mbccs.callback.OnListenerItemRecyclerView;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.BillingModel;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleChannelInitData;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListApParamsRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSearchTransRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.BillingRepository;
import com.viettel.mbccs.data.source.remote.response.GetListApParamsResponse;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSearchTransResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.screen.billing.adapter.BillingAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.EditTextUtil;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.CustomDatePicker;
import com.viettel.mbccs.widget.CustomEditText;
import com.viettel.mbccs.widget.ItemSpinText;
import com.viettel.mbccs.widget.MultiDirectionSlidingDrawer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;

public class BillingFragment extends BaseDataFragment {

    @BindView(R.id.lyHeader)
    LinearLayout mHeader;
    @BindView(R.id.view_close)
    LinearLayout mSearchText;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.dp_to_date)
    CustomDatePicker mToDate;
    @BindView(R.id.dp_from_date)
    CustomDatePicker mFormDate;
    @BindView(R.id.tv_list_trans)
    TextView mListTrans;
    @BindView(R.id.id_ist_channel)
    ItemSpinText mItemChannel;
    @BindView(R.id.id_ist_trans)
    ItemSpinText mItemTrans;
    @BindView(R.id.drawer)
    MultiDirectionSlidingDrawer mWrappingSlidingDrawer;
    @BindView(R.id.ed_date_search)
    CustomEditText mDateSearch;

    private BillingAdapter mBillingAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private UserRepository mUserRepository;
    private DataRequest<GetTelecomServiceAndSaleProgramRequest>
            mGetTelecomServiceAndSaleProgramRequest;
    private DataRequest<GetListChannelByOwnerTypeIdRequest> mGetListChannelByOwnerTypeIdRequest;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private BillingRepository mBillingRepository;
    private CompositeSubscription mCompositeSubscription;
    private List<SaleTrans> mListSaleTrans;
    private List<ChannelInfo> mChannelInfoList = new ArrayList<>();

    @OnClick({R.id.bt_search, R.id.bt_billing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_search:
                if (isActiveSearch()) {
                    getListSearchTrans(mFormDate.getStringDate(), mToDate.getStringDate());
                }
                break;
            case R.id.bt_billing:
                if (mListSaleTrans != null) {
                    ArrayList<SaleTrans> mListSaleTransChoose = new ArrayList<>();
                    for (SaleTrans saleTrans : mListSaleTrans) {
                        if (saleTrans.isCheck()) {
                            mListSaleTransChoose.add(saleTrans);
                        }
                    }
                    if (mListSaleTransChoose.size() > 0) {
                        Bundle mBundle = new Bundle();
                        mBundle.putParcelableArrayList(SaleTrans.class.getName(),
                                mListSaleTransChoose);
                        System.out.println("mListSaleTransChoose" + mListSaleTransChoose.size());
                        getBaseActivity().goToDialogFragment(new DialogConfirmBillingFragment(),
                                mBundle);
                    } else {
                        DialogUtils.showDialog(getBaseActivity(), null,
                                "Bạn chưa chọn giao dịch lập hóa đơn nào?", null);
                    }
                }

                break;
            default:
                break;
        }
    }

    private boolean isActiveSearch() {
//        if (TextUtils.isEmpty(mItemChannel.getStringSpinner())) {
//            DialogUtils.showDialog(getBaseActivity(), null, "Bạn chưa chọn kênh nào?", null);
//            return false;
//        } else if (TextUtils.isEmpty(mItemTrans.getStringSpinner())) {
//            DialogUtils.showDialog(getBaseActivity(), null, "Bạn chưa chọn giao dịch nào?", null);
//            return false;
//        }
        return true;
    }

    @Override
    protected void initData() {
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        mBillingRepository = BillingRepository.getInstance();
        mCompositeSubscription = new CompositeSubscription();
        getListChannelFromServer();
        //getListSearchTrans("22/06/2017 00:00:00", "22/07/2017 00:00:00");
        EditTextUtil.hideSoftKeyboard(getBaseActivity());
        EditTextUtil.hideKeyboard(getBaseActivity());
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_billing;
    }

    @Override
    protected void initView() {
        mLinearLayoutManager =
                new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);

        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mWrappingSlidingDrawer.setOnDrawerCloseListener(
                new MultiDirectionSlidingDrawer.OnDrawerCloseListener() {
                    @Override
                    public void onDrawerClosed() {
                    }
                });

        mWrappingSlidingDrawer.setOnDrawerOpenListener(
                new MultiDirectionSlidingDrawer.OnDrawerOpenListener() {
                    @Override
                    public void onDrawerOpened() {
                    }
                });

        mWrappingSlidingDrawer.open();

        //RelativeLayout.LayoutParams item_title_param = new RelativeLayout.LayoutParams
        // (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //item_title_param.setMargins(0,0,0,800);
        //mWrappingSlidingDrawer.setLayoutParams(item_title_param );
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().setTitleToolbar(R.string.billing_title);
    }

    private void setAdapterSearch(List<SaleTrans> mListSaleTrans) {
        mListTrans.setText(
                String.format(getString(R.string.msg_list_trans), mListSaleTrans.size()));
        mBillingAdapter = new BillingAdapter(mListSaleTrans, getBaseActivity());
        mBillingAdapter.setOnClickItemRecycleView(new OnListenerItemRecyclerView<SaleTrans>() {
            @Override
            public void onClickItem(SaleTrans model, int position) {
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(SaleTrans.class.getName(), model);
                //getBaseActivity().goToFragment(new TransDetailFragment(), mBundle);
            }
        });
        mRecyclerView.setAdapter(mBillingAdapter);
    }

    private Observable<TelecomServiceAndSaleProgramResponse>
    getObservableTeleComserviceAndSaleProgram() {
        mGetTelecomServiceAndSaleProgramRequest = new DataRequest<>();
        mGetTelecomServiceAndSaleProgramRequest.setWsCode(WsCode.GetTelecomServiceAndSaleProgram);
        GetTelecomServiceAndSaleProgramRequest request =
                new GetTelecomServiceAndSaleProgramRequest();
        request.setShopId(mUserRepository.getUserInfo().getShop().getShopId());
        mGetTelecomServiceAndSaleProgramRequest.setWsRequest(request);
        return mBanHangKhoTaiChinhRepository.getTelecomserviceAndSaleProgram(
                mGetTelecomServiceAndSaleProgramRequest);
    }

    private Observable<GetListChannelByOwnerTypeIdResponse> getObservaleChannelInfors() {
        mGetListChannelByOwnerTypeIdRequest = new DataRequest<>();
        mGetListChannelByOwnerTypeIdRequest.setWsCode(WsCode.GetListChannelByOwnerTypeId);
        GetListChannelByOwnerTypeIdRequest request = new GetListChannelByOwnerTypeIdRequest();
        request.setStaffId(Long.valueOf(
                String.valueOf(mUserRepository.getUserInfo().getStaffInfo().getStaffId())));
        //request.setChannelTypeId(mUserRepository.getUserInfo().getStaffInfo().getChannelTypeId());
        request.setLanguage("en");
        mGetListChannelByOwnerTypeIdRequest.setWsRequest(request);
        return mBanHangKhoTaiChinhRepository.getListChannelByOwnerTypeId(
                mGetListChannelByOwnerTypeIdRequest);
    }

    private void getListSearchTrans(final String fromDate, String toDate) {
        getBaseActivity().showLoadingDialog();
        final DataRequest<GetListSearchTransRequest> request = new DataRequest<>();
        GetListSearchTransRequest mGetListSearchTransRequest = new GetListSearchTransRequest();
        mGetListSearchTransRequest.mShopId = (mUserRepository.getUserInfo().getShop().getShopId());
        mGetListSearchTransRequest.mFromDate = fromDate;
        mGetListSearchTransRequest.mToDate = toDate;
        if (!TextUtils.isEmpty(mItemChannel.getStringSpinner())) {
            mGetListSearchTransRequest.mSaleTransType = 1;
        }
        if (TextUtils.isEmpty(mItemTrans.getStringSpinner())) {
            mGetListSearchTransRequest.mStaffId = getIdChanelId(
                    TextUtils.isEmpty(mItemChannel.getStringSpinner()) ? "0"
                            : mItemChannel.getStringSpinner());
        }

        request.setWsRequest(mGetListSearchTransRequest);
        request.setWsCode(WsCode.GetListSaleTrans);

        Subscription subscription = mBillingRepository.getListSearchTrans(request)
                .subscribe(new MBCCSSubscribe<GetListSearchTransResponse>() {
                    @Override
                    public void onSuccess(GetListSearchTransResponse response) {
                        if (response != null && response.mListSaleTrans != null) {
                            if (mListSaleTrans != null) mListSaleTrans.clear();
                            mListSaleTrans = response.mListSaleTrans;
                            setAdapterSearch(mListSaleTrans);
                            mDateSearch.setText(mFormDate.getStringFormatDDMMYY() + "->" + mToDate.getStringFormatDDMMYY());
                            mWrappingSlidingDrawer.close();
                        }
                        getBaseActivity().hideLoadingDialog();
                    }

                    @Override
                    public void onError(BaseException error) {
                        getBaseActivity().hideLoadingDialog();
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    private void getListChannelFromServer() {
        getBaseActivity().showLoadingDialog();
        Observable.zip(getObservableTeleComserviceAndSaleProgram(), getObservaleChannelInfors(),
                new Func2<TelecomServiceAndSaleProgramResponse,
                        GetListChannelByOwnerTypeIdResponse, SaleChannelInitData>() {
                    @Override
                    public SaleChannelInitData call(
                            TelecomServiceAndSaleProgramResponse
                                    telecomServiceAndSaleProgramResponse,
                            GetListChannelByOwnerTypeIdResponse
                                    getListChannelByOwnerTypeIdResponse) {
                        if (telecomServiceAndSaleProgramResponse == null
                                || getListChannelByOwnerTypeIdResponse == null) {
                            return null;
                        }
                        return new SaleChannelInitData(telecomServiceAndSaleProgramResponse,
                                getListChannelByOwnerTypeIdResponse);
                    }
                }).subscribe(new MBCCSSubscribe<SaleChannelInitData>() {
            @Override
            public void onSuccess(SaleChannelInitData object) {
                if (object != null
                        && object.getmGetListChannelByOwnerTypeIdResponse() != null
                        && object.getTelecomServiceAndSaleProgramResponse() != null) {
                    mChannelInfoList =
                            object.getmGetListChannelByOwnerTypeIdResponse().getChannelInfoList();
                    mItemChannel.setListSpinner(
                            object.getmGetListChannelByOwnerTypeIdResponse().getListDataChannel());

                    getExchange();
                }
            }

            @Override
            public void onError(BaseException error) {
                DialogUtils.showDialog(getBaseActivity(), null, error.getMessage(), null);
            }

            @Override
            public void onRequestFinish() {
                super.onRequestFinish();
                getBaseActivity().hideLoadingDialog();
            }
        });
    }

    private void getExchange() {
        getBaseActivity().showLoadingDialog();
        final DataRequest<GetListApParamsRequest> request = new DataRequest<>();
        GetListApParamsRequest mListApParamsRequest = new GetListApParamsRequest();
        mListApParamsRequest.mType = "SALE_TRANS_TYPE";
        request.setWsRequest(mListApParamsRequest);
        request.setWsCode(WsCode.GetApParam);

        Subscription subscription = mBillingRepository.getApParam(request)
                .subscribe(new MBCCSSubscribe<GetListApParamsResponse>() {
                    @Override
                    public void onSuccess(GetListApParamsResponse response) {
                        if (response != null && response.getApParamsModelList() != null) {
                            mItemTrans.setListSpinner(response.getListApParams());
                        }
                        getBaseActivity().hideLoadingDialog();
                    }

                    @Override
                    public void onError(BaseException error) {
                        getBaseActivity().hideLoadingDialog();
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    private long getIdChanelId(String mData) {
        if (TextUtils.isEmpty(mData) || mChannelInfoList == null) return 0;
        for (ChannelInfo channelInfo : mChannelInfoList) {
            if (mData.equals(channelInfo.getChannelName())) {
                return channelInfo.getChannelId();
            }
        }
        return 0;
    }
}
