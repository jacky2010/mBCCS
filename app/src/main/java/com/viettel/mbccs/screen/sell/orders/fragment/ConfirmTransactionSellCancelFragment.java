package com.viettel.mbccs.screen.sell.orders.fragment;

import android.app.Dialog;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.Reason;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.model.Session;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransFromOrderRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetReasonRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateSaleOrderRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransFromOrderResponse;
import com.viettel.mbccs.data.source.remote.response.GetReasonResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateSaleOrderResponse;
import com.viettel.mbccs.databinding.FragmentConfirmTransactionSellCancelBinding;
import com.viettel.mbccs.screen.common.success.DialogFullScreen;
import com.viettel.mbccs.screen.sell.orders.fragment.orderdetail.OrderDetailFragment;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class ConfirmTransactionSellCancelFragment extends BaseFragment {
    public static final String STRING_NAME = "ConfirmTransactionSellCancelFragment";
    private static final String ARG_IS_SELL = "IS_SELL";
    private static final String ARG_SALE_TRANS = "SALE_TRANS";
    private static final String ARG_CHANGE_INFO = "CHANGE_INFO";
    private static final String ARG_LIST_DATA = "LIST_DATA";
    private FragmentConfirmTransactionSellCancelBinding binding;
    private BanHangKhoTaiChinhRepository banHangKhoTaiChinhRepository;
    private List<Reason> reasonList;
    private Reason reason;
    private List<String> dataSpinnerReason;
    private CompositeSubscription subscriptions;

    /**
     * isSell -> status of fragment
     */
    private boolean isSell;
    private ChannelInfo channelInfoSell;
    private SaleTrans saleTrans;
    private List<SaleOrdersDetail> saleOrdersDetailList;

    public ObservableField<ArrayAdapter<String>> spinnerReasonCancelAdapter;
    public ObservableField<String> title;
    public ObservableField<String> textButton;
    public ObservableInt colorButton;
    public ObservableBoolean isShow;
    public ObservableField<String> idSaleTrans;
    public ObservableField<String> phoneNumberChange;
    public ObservableField<String> nameChange;
    public ObservableField<String> sumMoneyTransaction;

    public static ConfirmTransactionSellCancelFragment newInstance(boolean isSell,
            List<SaleOrdersDetail> saleOrdersDetailList, SaleTrans saleTrans,
            ChannelInfo channelInfoSell) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_IS_SELL, isSell);
        bundle.putParcelableArrayList(ARG_LIST_DATA,
                (ArrayList<? extends Parcelable>) saleOrdersDetailList);
        bundle.putParcelable(ARG_SALE_TRANS, saleTrans);
        bundle.putParcelable(ARG_CHANGE_INFO, channelInfoSell);
        ConfirmTransactionSellCancelFragment fragment = new ConfirmTransactionSellCancelFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        banHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        subscriptions = new CompositeSubscription();

        isSell = getArguments().getBoolean(ARG_IS_SELL);
        saleOrdersDetailList = getArguments().getParcelableArrayList(ARG_LIST_DATA);
        saleTrans = getArguments().getParcelable(ARG_SALE_TRANS);
        channelInfoSell = getArguments().getParcelable(ARG_CHANGE_INFO);

        if (!isSell) {
            dataSpinnerReason = new ArrayList<>();
            spinnerReasonCancelAdapter = new ObservableField<>();
        }
        idSaleTrans = new ObservableField<>();
        phoneNumberChange = new ObservableField<>();
        nameChange = new ObservableField<>();
        sumMoneyTransaction = new ObservableField<>();
        title = new ObservableField<>();
        textButton = new ObservableField<>();
        colorButton = new ObservableInt();
        isShow = new ObservableBoolean();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentConfirmTransactionSellCancelBinding.inflate(inflater, container, false);
        binding.setPresenter(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
        if (!isSell) getListReason();
    }

    @Override
    public void onStop() {
        subscriptions.clear();
        super.onStop();
    }

    private void getListReason() {
        showLoadingDialog();
        GetReasonRequest reason = new GetReasonRequest();
        reason.setOwnerId("5435");
        reason.setOwnerType("2");
        reason.setReasonType("2");

        DataRequest<GetReasonRequest> request = new DataRequest<>();
        Session session = new Session();
        session.setSessionId("54578345638");

        request.setSession(session);
        request.setUserName("smac");
        request.setApiCode(ApiCode.GetReason);
        request.setApiKey("123456");
        request.setToken("54353-543346-65464564-6546");
        request.setParameterApi(reason);

        Subscription subscription = banHangKhoTaiChinhRepository.getReason(request)
                .subscribe(new MBCCSSubscribe<GetReasonResponse>() {

                    @Override
                    public void onSuccess(GetReasonResponse object) {
                        reasonList = object.getReasonList();
                        setUpSpinner();
                    }

                    @Override
                    public void onError(BaseException error) {
                        // TODO: 5/31/17  get again getReason
                        hideLoadingDialog();
                    }
                });
        subscriptions.add(subscription);
    }

    private void setUpSpinner() {
        for (Reason r : reasonList) {
            dataSpinnerReason.add(r.getReasonName());
        }

        spinnerReasonCancelAdapter.set(
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                        dataSpinnerReason));
        spinnerReasonCancelAdapter.get()
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerReasonCancel.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        reason = reasonList.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        hideLoadingDialog();
    }

    private void setData() {
        isShow.set(!isSell);
        title.set(isSell ? getString(R.string.confirm_transaction_sell_title)
                : getString(R.string.confirm_transaction_cancel_title));
        textButton.set(isSell ? getString(R.string.confirm_transaction_btn_sell)
                : getString(R.string.confirm_transaction_btn_cancel));
        colorButton.set(isSell ? R.color.green : R.color.red_button);
        idSaleTrans.set("Mã đơn hàng: " + saleTrans.getSaleTransId());
        phoneNumberChange.set(channelInfoSell.getTel());
        nameChange.set(channelInfoSell.getChannelName());
        sumMoneyTransaction.set(Common.formatDouble(saleTrans.getAmountTax()));
    }

    public void onClose() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    public void onSellOrCancel() {
        if (isSell) {
            sellOrder();
        } else {
            cancelSell();
        }
    }

    private void sellOrder() {
        CreateSaleTransFromOrderRequest c = new CreateSaleTransFromOrderRequest();
        c.setShopId(5235);
        c.setStaffId(534534);

        Customer customer = new Customer();
        customer.setCustomerName(saleTrans.getCustName());

        c.setCustomer(customer);
        c.setLstSerialSale(saleOrdersDetailList);
        c.setPayMethod("1");
        c.setPriceType("3");

        DataRequest<CreateSaleTransFromOrderRequest> request = new DataRequest<>();
        Session session = new Session();
        session.setSessionId("54578345638");

        request.setSession(session);
        request.setUserName("smac");
        request.setApiCode(ApiCode.CreateSaleTransFromOrder);
        request.setApiKey("123456");
        request.setToken("54353-543346-65464564-6546");
        request.setParameterApi(c);

        Subscription subscription = banHangKhoTaiChinhRepository.createSaleTransFromOrder(request)
                .subscribe(new MBCCSSubscribe<CreateSaleTransFromOrderResponse>() {
                    @Override
                    public void onSuccess(CreateSaleTransFromOrderResponse object) {
                        sellOrderSuccess(true);
                    }

                    @Override
                    public void onError(BaseException error) {
                        sellOrderError(error);
                    }
                });
        subscriptions.add(subscription);
    }

    private void cancelSell() {
        UpdateSaleOrderRequest u = new UpdateSaleOrderRequest();
        u.setSaleOrderId("54353");
        u.setNewStatus("2");
        u.setReasonId("2");

        DataRequest<UpdateSaleOrderRequest> request = new DataRequest<>();
        Session session = new Session();
        session.setSessionId("54578345638");

        request.setSession(session);
        request.setUserName("smac");
        request.setApiCode(ApiCode.UpdateSaleOrder);
        request.setApiKey("123456");
        request.setToken("54353-543346-65464564-6546");
        request.setParameterApi(u);

        Subscription subscription = banHangKhoTaiChinhRepository.updateSaleOrder(request)
                .subscribe(new MBCCSSubscribe<UpdateSaleOrderResponse>() {
                    @Override
                    public void onSuccess(UpdateSaleOrderResponse object) {
                        sellOrderSuccess(false);
                    }

                    @Override
                    public void onError(BaseException error) {
                        sellOrderError(error);
                    }
                });
        subscriptions.add(subscription);
    }

    private void sellOrderError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error.getMessage());
    }

    private void sellOrderSuccess(boolean type) {
        Dialog dia = new DialogFullScreen.Builder(getActivity()).setCenterContent(true)
                .setTitle(type ? getString(
                        R.string.confirm_transaction_sell_cancel_ban_giao_dich_thanh_cong)
                        : getString(
                                R.string.confirm_transaction_sell_cancel_huy_giao_dich_thanh_cong))
                .setContent(getString(R.string.confirm_transaction_sell_cancel_gui_tin_nhan))
                .setPositiveButton(getString(R.string.OK))
                .setListener(new DialogFullScreen.SuccessDialogListener() {
                    @Override
                    public void onPositiveButtonClick(Dialog dialog) {
                        dialog.dismiss();
                        getActivity().getSupportFragmentManager()
                                .popBackStack(OrderDetailFragment.STRING_NAME,
                                        FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }

                    @Override
                    public void onNegativeButtonClick(Dialog dialog) {

                    }

                    @Override
                    public void onDialogClose() {

                    }
                })
                .build();
        dia.setCancelable(false);
        dia.setCanceledOnTouchOutside(false);
        dia.show();
    }
}
