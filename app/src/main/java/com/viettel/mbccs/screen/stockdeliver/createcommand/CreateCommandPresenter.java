package com.viettel.mbccs.screen.stockdeliver.createcommand;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.CreateOrderResponse;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.screen.stockdeliver.createcommand.adapter.CreateCommandAdapter;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.StockTotalCompare;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class CreateCommandPresenter implements CreateCommandContract.Presenter {

    public ObservableField<String> titleOrder;
    public ObservableField<String> amount;
    private Context mContext;
    private CreateCommandContract.ViewModel mViewModel;
    private CreateCommandAdapter mAdapter;
    private CompositeSubscription mCompositeSubscription;
    private ArrayList<StockTotal> mStockTotals = new ArrayList<>();
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private UserRepository mUserRepository;
    private DataRequest<KPPOrderRequest> mKPPOrderRequestBaseRequest;
    private CompositeSubscription mSubscription;
    private ArrayList<StockTransDetail> mStockTransDetails = new ArrayList<>();
    public ObservableInt countProducts;
    protected List<String> listProductState = new ArrayList<>();
    private StockTrans mStockTrans;
    private DataRequest<GetListStockTransDetailRequest> mDataRequest;
    public ObservableField<SpinnerAdapter<String>> adapterProductState = new ObservableField<>();

    public CreateCommandPresenter(Context context, CreateCommandContract.ViewModel viewModel,
            StockTrans stockTrans) {
        mContext = context;
        mViewModel = viewModel;
        mStockTrans = stockTrans;
        mCompositeSubscription = new CompositeSubscription();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        mSubscription = new CompositeSubscription();
        init();
        loadData();
    }

    private void init() {
        createAdapterProductState();
        countProducts = new ObservableInt();
        titleOrder = new ObservableField<>();
        titleOrder.set(
                String.format(mContext.getString(R.string.xuatkhocapduoi_create_command_from),
                        mUserRepository.getUserInfo().getShop().getShopName()));
        amount = new ObservableField<>();
        caculatePrice();
        mAdapter = new CreateCommandAdapter(mContext, mStockTransDetails);
        mAdapter.setStockTotalListener(new CreateCommandAdapter.StockTotalListener() {
            @Override
            public void onStockQuantityChange() {
                caculatePrice();
            }
        });
    }

    private void createAdapterProductState() {
        fakeData();
        SpinnerAdapter<String> adapter = new SpinnerAdapter<>(mContext, listProductState);
        adapter.setOnItemSelectedListener(getWareHouseItemSelectedListener());
        adapterProductState.set(adapter);
    }

    private void fakeData() {
        String[] array = new String[] { "NEW", "OLD" };
        Collections.addAll(listProductState, array);
    }

    protected AdapterView.OnItemSelectedListener getWareHouseItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private void caculatePrice() {
        float totalMoney = 0;
        for (StockTotal stockTotal : mStockTotals) {
            totalMoney += stockTotal.getCountChoice() * stockTotal.getPrice();
        }
        amount.set(String.format(mContext.getString(R.string.kpp_order_label_amount),
                Common.formatDouble(totalMoney)));
    }

    private void loadData() {
        mDataRequest = new DataRequest<>();
        mDataRequest.setWsCode(WsCode.GetListStockTransDetail);
        GetListStockTransDetailRequest request = new GetListStockTransDetailRequest();
        request.setStockTransId(mStockTrans.getStockTransId());
        mDataRequest.setWsRequest(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.getListStockTransDetail(mDataRequest).subscribe(

                        new MBCCSSubscribe<ListStockTransDetailsReponse>() {
                            @Override
                            public void onSuccess(ListStockTransDetailsReponse object) {
                                if (object != null && object.getStockTransDetails() != null) {
                                    bindData(object);
                                }
                            }

                            @Override
                            public void onError(BaseException error) {
                                /*DialogUtils.showDialog(mContext, String.format(mContext.getString(
                                        R.string.xuatkhocapduoi_error_connect_api),
                                        error.getMessage()), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mViewModel.finishScreen();
                                    }
                                });*/
                                bindData(fakeDataListStockTransDetailsReponse());
                            }
                        });
        mSubscription.add(subscription);
    }

    private void bindData(ListStockTransDetailsReponse object) {
        mStockTransDetails.clear();
        mStockTransDetails.addAll(object.getStockTransDetails());
        mAdapter.notifyDataSetChanged();
    }

    public ListStockTransDetailsReponse fakeDataListStockTransDetailsReponse() {

        StockSerial stockSerial = new StockSerial();
        List<SerialBO> serialBOs = new ArrayList<>();
        serialBOs.add(new SerialBO("111111", "111115"));
        serialBOs.add(new SerialBO("111117", "111119"));
        stockSerial.setSerialBOs(serialBOs);

        ListStockTransDetailsReponse reponse = new ListStockTransDetailsReponse();

        List<StockTransDetail> listFake = new ArrayList<>();

        StockTransDetail stockTransDetail1 = new StockTransDetail();
        stockTransDetail1.setQuantity(12);
        stockTransDetail1.setStockModelCode("AA-SS");
        stockTransDetail1.setStockModelId(1000554);
        stockTransDetail1.setStockModelName("SP1");
        stockTransDetail1.setStockSerial(stockSerial);

        StockTransDetail stockTransDetail2 = new StockTransDetail();
        stockTransDetail2.setQuantity(12);
        stockTransDetail2.setStockModelCode("FF-SS");
        stockTransDetail2.setStockModelId(1000554);
        stockTransDetail2.setStockModelName("SP1");
        stockTransDetail2.setStockSerial(stockSerial);

        StockTransDetail stockTransDetail3 = new StockTransDetail();
        stockTransDetail3.setQuantity(18);
        stockTransDetail3.setStockModelCode("CCC-SS");
        stockTransDetail3.setStockModelId(1000554);
        stockTransDetail3.setStockModelName("SP1");
        stockTransDetail3.setStockSerial(stockSerial);

        listFake.add(stockTransDetail1);
        listFake.add(stockTransDetail2);
        listFake.add(stockTransDetail3);
        mStockTransDetails.clear();
        mStockTransDetails.addAll(listFake);
        reponse.setStockTransDetails(listFake);
        return reponse;
    }

    public void cancelClick() {
        ((Activity) mContext).finish();
    }

    public void orderClick() {
        if (!isValidate()) {
            return;
        }
        DialogUtils.showDialogStyle(mContext, R.string.confirm, R.string.confirm_kpp_order,
                R.string.order2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        createOrder();
                    }
                }, R.string.common_label_close, null);
    }

    private void createOrder() {

        mViewModel.showLoading();
        mKPPOrderRequestBaseRequest = new DataRequest<>();
        mKPPOrderRequestBaseRequest.setWsCode(WsCode.CreateSaleOrders);
        final KPPOrderRequest request = new KPPOrderRequest();
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        request.setChannelStaffId(mUserRepository.getUserInfo().getChannelInfo().getChannelId());
        request.setListStockModel(Common.convertStockTotalsToStockModels(mStockTotals));
        mKPPOrderRequestBaseRequest.setWsRequest(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.createSaleOrders(mKPPOrderRequestBaseRequest)
                        .subscribe(new MBCCSSubscribe<CreateOrderResponse>() {
                            @Override
                            public void onSuccess(CreateOrderResponse object) {
                                if (object != null) {
                                    mViewModel.gotoSuccessScreen(mStockTotals, object.getOrderId(),
                                            mUserRepository.getUserInfo()
                                                    .getChannelInfo()
                                                    .getManagementName());
                                    return;
                                }

                                onError(new Throwable());
                            }

                            @Override
                            public void onError(BaseException error) {
                                DialogUtils.showDialog(mContext, null, error.getMessage(), null);
                                //mViewModel.gotoSuccessScreen(mStockTotals);
                            }

                            @Override
                            public void onRequestFinish() {
                                super.onRequestFinish();
                                mViewModel.hideLoading();
                            }
                        });
        mCompositeSubscription.add(subscription);
    }

    public void addNewStock() {
        mViewModel.goGoStockPicker(mStockTotals);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mCompositeSubscription.clear();
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }

    public CreateCommandAdapter getAdapter() {
        return mAdapter;
    }

    public void mergeStockTotalList(StockTotal stockTotal) {
        for (int i = 0; i < mStockTotals.size(); i++) {
            if (mStockTotals.get(i).equals(stockTotal)) {
                mStockTotals.get(i).setCountChoice(stockTotal.getCountChoice());
                return;
            }
        }
        mStockTotals.add(stockTotal);
    }

    @Override
    public void pickStockTotalListSuccess(List<StockTotal> stockTotals) {
        for (StockTotal stockTotal : stockTotals) {
            mergeStockTotalList(stockTotal);
        }
        Collections.sort(mStockTotals, new StockTotalCompare());
        mAdapter.notifyDataSetChanged();
        caculatePrice();
        //TODO add list stock total
    }

    @Override
    public String getActivityTitle() {
        return mViewModel.getScreenTitle();
    }

    public boolean isValidate() {

        int count = 0;
        for (StockTotal item : mStockTotals) {
            if (item.getCountChoice() > 0) {
                count++;
                break;
            }
        }
        if (count == 0) {
            DialogUtils.showDialog(mContext, R.string.no_item_order);
            return false;
        }

        return true;
    }
}
