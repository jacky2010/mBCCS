package com.viettel.mbccs.screen.sell.orders.fragment;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.Reason;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.source.SellOrdersRepository;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.FragmentConfirmTransactionSellCancelBinding;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/18/17.
 */

public class ConfirmTransactionSellCancelFragment extends BaseFragment {
    private static final String ARG_IS_SELL = "IS_SELL";
    private static final String ARG_SALE_TRANS = "SALE_TRANS";
    private static final String ARG_CHANGE_INFO = "CHANGE_INFO";
    private FragmentConfirmTransactionSellCancelBinding binding;
    private SellOrdersRepository sellOrdersRepository;
    private List<Reason> reasonList;
    private Reason reason;
    private List<String> dataSpinnerReason;

    /**
     * isSell -> status of fragment
     */
    private boolean isSell;
    private ChannelInfo channelInfoSell;
    private SaleTrans saleTrans;

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
            SaleTrans saleTrans, ChannelInfo channelInfoSell) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_IS_SELL, isSell);
        bundle.putParcelable(ARG_SALE_TRANS, saleTrans);
        bundle.putParcelable(ARG_CHANGE_INFO, channelInfoSell);
        ConfirmTransactionSellCancelFragment fragment = new ConfirmTransactionSellCancelFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sellOrdersRepository = SellOrdersRepository.getInstance();
        isSell = getArguments().getBoolean(ARG_IS_SELL);
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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
        if (!isSell) getListReason();
    }

    private void getListReason() {
        showLoadingDialog();
        sellOrdersRepository.getListReason(new BaseRequest<Object>())
                .subscribe(new MBCCSSubscribe<List<Reason>>() {
                    @Override
                    public void onSuccess(List<Reason> object) {
                        reasonList = object;
                        setUpSpinner();
                    }

                    @Override
                    public void onError(BaseException error) {
                        // TODO: 5/19/17 show error
                        // get again getListReason
                        hideLoadingDialog();
                    }
                });
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
        sumMoneyTransaction.set(String.valueOf(saleTrans.getAmountTax()));
    }

    public void onClose() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    public void onSellOrCancel() {
        if (isSell) {
            // TODO: 5/19/17 Sell order
        } else {
            // TODO: 5/19/17 Cancel order
        }
    }
}
