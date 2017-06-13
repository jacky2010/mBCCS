package com.viettel.mbccs.screen.sell.orders.fragment.orderdetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import com.viettel.mbccs.data.model.SerialPickerModel;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetOrderInfoResponse;
import com.viettel.mbccs.databinding.FragmentOrderDetailBinding;
import com.viettel.mbccs.screen.sell.orders.adapter.OrderDetailAdapter;
import com.viettel.mbccs.screen.sell.orders.fragment.ConfirmTransactionSellCancelFragment;
import com.viettel.mbccs.screen.serialpicker.SerialPickerActivity;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

/**
 * Created by HuyQuyet on 5/16/17.
 */

public class OrderDetailFragment extends BaseFragment implements OrderDetailFragmentContract.View {
    public static final String STRING_NAME = "OrderDetailFragment";
    private static final String ARG_ID_ORDER = "ID_ORDER";
    private static final String ARG_CHANGE_INFO = "CHANGE_INFO";
    private static final int GET_SERIAL = 12345;
    private FragmentOrderDetailBinding binding;
    private OrderDetailFragmentPresenter presenter;
    private OrderDetailAdapter orderDetailAdapter;
    private SaleOrdersDetail saleOrdersDetailSelect;
    private ChannelInfo channelInfo;
    private GetOrderInfoResponse getOrderInfoResponse;

    public static OrderDetailFragment newInstance(long idOrder, ChannelInfo channelInfo) {
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_ID_ORDER, idOrder);
        bundle.putParcelable(ARG_CHANGE_INFO, channelInfo);
        OrderDetailFragment fragment = new OrderDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderDetailBinding.inflate(inflater, container, false);
        presenter = new OrderDetailFragmentPresenter(getActivity(), this);
        binding.setPresenter(presenter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        channelInfo = getArguments().getParcelable(ARG_CHANGE_INFO);
        if(getOrderInfoResponse != null){
            presenter.setData(getOrderInfoResponse);
            setData(getOrderInfoResponse);
            return;
        }
        presenter.getDetailOrder((long) getArguments().get(ARG_ID_ORDER));
    }

    @Override
    public void setPresenter(OrderDetailFragmentContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void setData(GetOrderInfoResponse data) {
        getOrderInfoResponse = data;
        orderDetailAdapter = new OrderDetailAdapter(data.getSaleOrdersDetailList());
        presenter.setAdapterOrderDetail(orderDetailAdapter);
        orderDetailAdapter.setOrderDetailAdapterCallback(presenter);
    }

    @Override
    public void getOrderInfoError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error.getMessage());
    }

    @Override
    public void pickSerial(SaleOrdersDetail saleOrdersDetail) {
        saleOrdersDetailSelect = saleOrdersDetail;
        Intent intent = new Intent(getActivity(), SerialPickerActivity.class);
        SerialPickerModel serialPickerModel = new SerialPickerModel();
        serialPickerModel.setStockModelId(saleOrdersDetail.getStockModelId());
        serialPickerModel.setStockMoldeName(saleOrdersDetail.getStockMoldeName());
        serialPickerModel.setQuantity(saleOrdersDetail.getQuantity());
        serialPickerModel.setLstSerial(saleOrdersDetail.getLstSerial());

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleConstant.SERIAL_PICKER_MODEL, serialPickerModel);
        intent.putExtras(bundle);
        startActivityForResult(intent, GET_SERIAL);
    }

    @Override
    public void clickCancelSell() {
        ConfirmTransactionSellCancelFragment fragment =
                ConfirmTransactionSellCancelFragment.newInstance(false,
                        getOrderInfoResponse.getSaleOrdersDetailList(),
                        getOrderInfoResponse.getSaleTrans(), channelInfo);
        FragmentTransaction transaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_sell_orders, fragment);
        transaction.addToBackStack(ConfirmTransactionSellCancelFragment.STRING_NAME);
        transaction.commit();
    }

    @Override
    public void onClickSell() {
        ConfirmTransactionSellCancelFragment fragment =
                ConfirmTransactionSellCancelFragment.newInstance(true,
                        getOrderInfoResponse.getSaleOrdersDetailList(),
                        getOrderInfoResponse.getSaleTrans(), channelInfo);
        FragmentTransaction transaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_sell_orders, fragment);
        transaction.addToBackStack(ConfirmTransactionSellCancelFragment.STRING_NAME);
        transaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_SERIAL && resultCode == Activity.RESULT_OK) {
            List<String> serials = GsonUtils.String2ListObject(
                    data.getExtras().getString(Constants.BundleConstant.LIST_SERIAL),
                    String[].class);
            if (serials.size() < saleOrdersDetailSelect.getQuantity()) {
                // TODO: 5/24/17 show error select serial
                Toast.makeText(getActivity(), "Bạn chưa chọn đủ số lượn serial", Toast.LENGTH_SHORT)
                        .show();
            }
            saleOrdersDetailSelect.setLstSerial(Common.getSerialBlockBySerials(serials));
            saleOrdersDetailSelect.setSelect(serials.size());
            orderDetailAdapter.notifyDataSetChanged();
        }
    }
}
