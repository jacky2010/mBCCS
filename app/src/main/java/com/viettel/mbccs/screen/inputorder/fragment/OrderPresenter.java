package com.viettel.mbccs.screen.inputorder.fragment;

import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.InvoiceList;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.InputOrderRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.InputOrderResponse;
import com.viettel.mbccs.screen.inputorder.fragment.adapter.OrderAdapter;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class OrderPresenter implements OrderContract.Presenter {
    private OrderContract.ViewModel mViewModel;
    private int mIndexTab;
    public OrderAdapter adapter;
    private DataRequest<InputOrderRequest> mDataRequest;
    private List<InvoiceList> mInvoiceListList = new ArrayList<>();
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private CompositeSubscription mSubscription;
    private InputOrderRequest request;
    private UserRepository mUserRepository;
    private UserInfo mUserInfo;

    public OrderPresenter(OrderContract.ViewModel viewModel, int indexTab) {
        mViewModel = viewModel;
        mIndexTab = indexTab;
        mSubscription = new CompositeSubscription();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        mUserInfo = mUserRepository.getUserInfo();
    }

    public OrderAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void subscribe() {
        getInvoiceList(mIndexTab);
    }

    @Override
    public void unSubscribe() {

    }

    private void getInvoiceList(int indexTab) {
        mDataRequest = new DataRequest<>();
        if (indexTab == OrderFragment.INDEX_TAB_ORDER_HIGH) {
            mDataRequest.setWsCode(WsCode.GetListInvoiceImport);
        } else {
            mDataRequest.setWsCode(WsCode.GetListInvoiceExport);
        }
        request = new InputOrderRequest();
        request.setShopId(mUserInfo.getShop().getShopId());
        request.setStaffId(mUserInfo.getStaffInfo().getStaffId());
        mDataRequest.setWsRequest(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.getListInvoice(mDataRequest).subscribe(

                        new MBCCSSubscribe<InputOrderResponse>() {
                            @Override
                            public void onSuccess(InputOrderResponse object) {
                                if (object != null && object.getInvoiceLists() != null) {
                                    mInvoiceListList = object.getInvoiceLists();
                                    adapter.setInvoiceListList(mInvoiceListList);
                                }
                            }

                            @Override
                            public void onError(BaseException error) {
                                //TODO: handle error response
                                adapter.setInvoiceListList(mInvoiceListList);
                            }
                        });
        mSubscription.add(subscription);
    }

    private void importInvoiceList() {
        request.setInvoiceListId(mInvoiceListList);
        mDataRequest.setWsCode(WsCode.ImportInvoiceList);
        mDataRequest.setWsRequest(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.importInvoiceList(mDataRequest).subscribe(

                        new MBCCSSubscribe<InputOrderResponse>() {
                            @Override
                            public void onSuccess(InputOrderResponse object) {
                                if (object.getErrorMessage().equals("successful")) {
                                    getInvoiceList(mIndexTab);
                                    mViewModel.inputOrderSuccess();
                                }
                            }

                            @Override
                            public void onError(BaseException error) {
                                //TODO: handle error response
                            }
                        });
        mSubscription.add(subscription);
    }

    @Override
    public void onInputOrderClick() {
        importInvoiceList();
    }

    private void dumyData() {
        mInvoiceListList.add(new InvoiceList("123345", "123346", 40, "25/05/2017", "Viettel"));
        mInvoiceListList.add(new InvoiceList("123345", "123346", 40, "25/05/2017", "Viettel"));
        mInvoiceListList.add(new InvoiceList("123345", "123346", 40, "25/05/2017", "Viettel"));
        mInvoiceListList.add(new InvoiceList("123345", "123346", 40, "25/05/2017", "Viettel"));
        mInvoiceListList.add(new InvoiceList("123345", "123346", 40, "25/05/2017", "Viettel"));
        mInvoiceListList.add(new InvoiceList("123345", "123346", 40, "25/05/2017", "Viettel"));
        mInvoiceListList.add(new InvoiceList("123345", "123346", 40, "25/05/2017", "Viettel"));
        mInvoiceListList.add(new InvoiceList("123345", "123346", 40, "25/05/2017", "Viettel"));
        mInvoiceListList.add(new InvoiceList("123345", "123346", 40, "25/05/2017", "Viettel"));
        mInvoiceListList.add(new InvoiceList("123345", "123346", 40, "25/05/2017", "Viettel"));
    }
}
