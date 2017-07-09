package com.viettel.mbccs.screen.stockdeliver.createcommand;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.CreateExpCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpNoteNoCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpNoteRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.response.BaseCreateCmdNoteResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.CreateOrderResponse;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.screen.nhanvientrahang.createNote.StockLapPhieuAdapter;
import com.viettel.mbccs.screen.warehousecommon.exportwarehouse.StockTransDetailAdapter;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.StockTotalCompare;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.CustomDialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class CreateCommandPresenter<T> implements CreateCommandContract.Presenter<T> {

    public ObservableBoolean showAddButton = new ObservableBoolean(false);
    public ObservableField<String> titleOrder;
    private Context mContext;
    private CreateCommandContract.ViewModel mViewModel;
    private StockLapPhieuAdapter mStockLapPhieuAdapter;
    private StockTransDetailAdapter mStockTransDetailAdapter;
    private CompositeSubscription mCompositeSubscription;
    private ArrayList<StockTotal> mStockTotals = new ArrayList<>();
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private UserRepository mUserRepository;
    private ArrayList<StockTransDetail> mStockTransDetails = new ArrayList<>();
    public ObservableInt countProducts;
    private StockTrans mStockTrans;
    private DataRequest mDataRequest;
    public ObservableField<SpinnerAdapter<String>> adapterProductState = new ObservableField<>();
    public List<T> mListReceiveWareHouse = new ArrayList<>();
    public SpinnerAdapter<T> mSpinnerAdapterReceiveHouser;
    public SpinnerAdapter mSpinnerAdapterStatus;
    protected List<String> listProductState = new ArrayList<>();
    private int positionReceiveWareHouse = 0;
    private int positionStatus = 0;

    public CreateCommandPresenter(Context context, CreateCommandContract.ViewModel viewModel,
            StockTrans stockTrans) {
        mContext = context;
        mViewModel = viewModel;
        mStockTrans = stockTrans;
        mCompositeSubscription = new CompositeSubscription();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        init();
    }

    private void init() {
        createSpinnerAdapter();
        countProducts = new ObservableInt();
        titleOrder = new ObservableField<>();

        if (mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_CMD) {
            titleOrder.set(
                    String.format(mContext.getString(R.string.xuatkhocapduoi_create_command_from),
                            mUserRepository.getUserInfo().getShop().getShopName()));
        } else {
            titleOrder.set(
                    String.format(mContext.getString(R.string.xuatkhocapduoi_create_note_from),
                            mUserRepository.getUserInfo().getShop().getShopName()));
        }

        mStockLapPhieuAdapter = new StockLapPhieuAdapter(mContext, mStockTotals, false);
        mStockLapPhieuAdapter.setOnStockLapPhieuListener(
                new StockLapPhieuAdapter.OnStockLapPhieuListener() {
                    @Override
                    public void onSerialPickerClick(int position, StockTotal stockTotal) {

                    }

                    @Override
                    public void onRemoveStock() {

                    }

                    @Override
                    public void onClickItem(StockTotal stockTotal, int position) {

                    }
                });

        mStockTransDetailAdapter = new StockTransDetailAdapter(mContext, mStockTransDetails);

        //define showbutton
        if ((mViewModel.getStep() == BaseCreateCommandNoteActivity.STEP_3
                && mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_CMD) || (
                mViewModel.getStep() == BaseCreateCommandNoteActivity.STEP_2
                        && mViewModel.getAction()
                        == BaseCreateCommandNoteActivity.ACTION_CREATE_NOTE)) {
            showAddButton.set(true);
            loadStocks();
        } else {
            showAddButton.set(false);
            loadStockTransDetail();
        }
    }

    private void createSpinnerAdapter() {
        listProductState =
                Arrays.asList(mContext.getResources().getStringArray(R.array.stock_status));
        mSpinnerAdapterStatus = new SpinnerAdapter(mContext, listProductState);
        mSpinnerAdapterStatus.setOnItemSelectedListener(getStatusItemSelectedListener());

        mSpinnerAdapterReceiveHouser = new SpinnerAdapter<T>(mContext, mListReceiveWareHouse);
        mSpinnerAdapterReceiveHouser.setOnItemSelectedListener(getWareHouseItemSelectedListener());
    }

    protected AdapterView.OnItemSelectedListener getWareHouseItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                positionReceiveWareHouse = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    protected AdapterView.OnItemSelectedListener getStatusItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                positionStatus = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private void loadStockTransDetail() {
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
                                showError(error);

                                //fake
                                bindData(fakeDataListStockTransDetailsReponse());
                            }
                        });
        mCompositeSubscription.add(subscription);
    }

    private void bindData(ListStockTransDetailsReponse object) {
        mStockTransDetails.clear();
        mStockTransDetails.addAll(object.getStockTransDetails());
        mStockTransDetailAdapter.notifyDataSetChanged();
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

        if (mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_CMD) {

            if (!isValidate()) {
                return;
            }

            showDialogConfirm(new CustomDialog.OnInputDialogListener() {
                @Override
                public void onClick(DialogInterface var1, int var2, String input) {
                    createCmd();
                }
            });

            return;
        }

        if (mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_NOTE
                && mViewModel.getStep() == BaseCreateCommandNoteActivity.STEP_2) {
            if (!isValidate()) {
                return;
            }

            showDialogConfirm(new CustomDialog.OnInputDialogListener() {
                @Override
                public void onClick(DialogInterface var1, int var2, String input) {
                    createNoteNoCmd();
                }
            });

            return;
        }

        if (mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_NOTE
                && mViewModel.getStep() == BaseCreateCommandNoteActivity.STEP_3) {
            showDialogConfirm(new CustomDialog.OnInputDialogListener() {
                @Override
                public void onClick(DialogInterface var1, int var2, String input) {
                    createNote();
                }
            });
        }
    }

    public void showDialogConfirm(CustomDialog.OnInputDialogListener listener) {

        int title;
        int titleButton;
        if (mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_CMD) {
            title = (R.string.common_export_msg_create_cmd);
            titleButton = (R.string.common_export_label_create_cmd);
        } else {
            title = (R.string.common_export_msg_create_note);
            titleButton = (R.string.common_export_label_create_note);
        }

        new CustomDialog(mContext, R.string.confirm, title, false, R.string.common_label_close,
                titleButton, null, listener, null, false, false).show();
    }

    private void createCmd() {
        mDataRequest = new DataRequest();
        CreateExpCmdRequest request = new CreateExpCmdRequest();
        mDataRequest.setWsCode(WsCode.CreateExpCmd);
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        request.setFromOwnerId(mViewModel.getFromOwnerIdCreate());
        request.setFromOwnerType(mViewModel.getFromOwnerTypeCreate());
        request.setToOwnerId(mViewModel.getToOwnerIdCreate());
        request.setToOwnerType(mViewModel.getToOwnerTypeCreate());
        request.setDiscountPolicy(
                Long.valueOf(mUserRepository.getUserInfo().getStaffInfo().getDiscountPolicy()));
        List<CreateExpCmdRequest.CmdStock> cmdStocks = new ArrayList<>();
        for (StockTotal stockTotal : mStockTotals) {
            CreateExpCmdRequest.CmdStock cmdStock = new CreateExpCmdRequest.CmdStock();
            cmdStock.cloneFromStockTotal(stockTotal);
            cmdStocks.add(cmdStock);
        }
        request.setCmdStocks(cmdStocks);
        mDataRequest.setWsRequest(request);
        Subscription subscription = mBanHangKhoTaiChinhRepository.createExpCmd(mDataRequest)
                .subscribe(new MBCCSSubscribe<BaseCreateCmdNoteResponse>() {
                    @Override
                    public void onSuccess(BaseCreateCmdNoteResponse object) {
                        onSuccess(object);
                    }

                    @Override
                    public void onError(BaseException error) {
                        showError(error);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });

        mCompositeSubscription.add(subscription);
    }

    private void createNoteNoCmd() {
        mDataRequest = new DataRequest();
        CreateExpNoteNoCmdRequest request = new CreateExpNoteNoCmdRequest();
        mDataRequest.setWsCode(WsCode.CreateExpNoteNoCMD);
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        request.setFromOwnerId(mViewModel.getFromOwnerIdCreate());
        request.setFromOwnerType(mViewModel.getFromOwnerTypeCreate());
        request.setToOwnerId(mViewModel.getToOwnerIdCreate());
        request.setToOwnerType(mViewModel.getToOwnerTypeCreate());
        request.setDiscountPolicy(
                Long.valueOf(mUserRepository.getUserInfo().getStaffInfo().getDiscountPolicy()));
        List<CreateExpCmdRequest.CmdStock> cmdStocks = new ArrayList<>();
        for (StockTotal stockTotal : mStockTotals) {
            CreateExpCmdRequest.CmdStock cmdStock = new CreateExpCmdRequest.CmdStock();
            cmdStock.cloneFromStockTotal(stockTotal);
            cmdStocks.add(cmdStock);
        }
        request.setCmdStocks(cmdStocks);
        mDataRequest.setWsRequest(request);
        Subscription subscription = mBanHangKhoTaiChinhRepository.createExpCmd(mDataRequest)
                .subscribe(new MBCCSSubscribe<BaseCreateCmdNoteResponse>() {
                    @Override
                    public void onSuccess(BaseCreateCmdNoteResponse object) {
                        onSuccess(object);
                    }

                    @Override
                    public void onError(BaseException error) {
                        showError(error);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });

        mCompositeSubscription.add(subscription);
    }

    private void createNote() {
        mViewModel.showLoading();
        mDataRequest = new DataRequest();
        CreateExpNoteRequest request = new CreateExpNoteRequest();
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        request.setStockTransId(mStockTrans.getStockTransId());
        mDataRequest.setWsCode(WsCode.CreateExpNote);
        mDataRequest.setWsRequest(request);
        Subscription subscription = mBanHangKhoTaiChinhRepository.createExpNote(mDataRequest)
                .subscribe(new MBCCSSubscribe<BaseCreateCmdNoteResponse>() {
                    @Override
                    public void onSuccess(BaseCreateCmdNoteResponse object) {
                        onCreateCmdNoteSuccess(object);
                    }

                    @Override
                    public void onError(BaseException error) {
                        showError(error);
                    }

                    @Override
                    public void onRequestFinish() {

                        mViewModel.hideLoading();
                    }
                });

        mCompositeSubscription.add(subscription);
    }

    private void onCreateCmdNoteSuccess(BaseCreateCmdNoteResponse obj) {

        StockTrans stockTrans = obj.getStockTrans();

        //TODO show chi tiet lenh xuat

        //TODO show lap phieu thanh cong
        Toast.makeText(mContext, "show dialog success", Toast.LENGTH_SHORT).show();
    }

    private void loadStocks() {
        mViewModel.showLoading();
        DataRequest<GetListStockModelRequest> mGetListStockModelRequestBaseRequest =
                new DataRequest<>();
        mGetListStockModelRequestBaseRequest.setWsCode(WsCode.GetListStockModel);
        GetListStockModelRequest request = new GetListStockModelRequest();
        //request.setStockTypeId(StockTotalType.TYPE_NEW);
        //request.setStateId(StockTotalType.TYPE_NEW);
        request.setOwnerType(mViewModel.getOwnerStockList());
        request.setSaleTransType(SaleTranType.SALE_RETAIL);
        request.setOwnerId(
                Long.valueOf(mUserRepository.getUserInfo().getStaffInfo().getStaffOwnerId()));
        mGetListStockModelRequestBaseRequest.setWsRequest(request);
        Subscription subscription = mBanHangKhoTaiChinhRepository.getListStockModel(
                mGetListStockModelRequestBaseRequest)
                .subscribe(new MBCCSSubscribe<GetListStockModelResponse>() {
                    @Override
                    public void onSuccess(GetListStockModelResponse object) {
                        if (object != null && object.getStockTotalList() != null) {
                            if (object.getStockTotalList().size() == 0) {
                                DialogUtils.showDialog(mContext, R.string.common_msg_no_data);
                            }
                            mStockTotals.clear();
                            mStockTotals.addAll(object.getStockTotalList());
                            mStockLapPhieuAdapter.notifyDataSetChanged();
                            return;
                        }
                        DialogUtils.showDialog(mContext, R.string.common_msg_no_data,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mViewModel.finishScreen();
                                    }
                                });
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialog(mContext, null, error.getMessage(), null);
                        //fake();
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                        ActivityUtils.hideKeyboard((Activity) mContext);
                    }
                });

        mCompositeSubscription.add(subscription);
    }

    private void showError(BaseException e) {
        DialogUtils.showDialogError(mContext, e);
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

    public RecyclerView.Adapter getAdapter() {
        if (mViewModel.getStep() == BaseCreateCommandNoteActivity.STEP_3
                && mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_CMD) {
            return mStockLapPhieuAdapter;
        }

        if (mViewModel.getStep() == BaseCreateCommandNoteActivity.STEP_2
                && mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_NOTE) {
            return mStockLapPhieuAdapter;
        }

        return mStockTransDetailAdapter;
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
        mStockLapPhieuAdapter.notifyDataSetChanged();
        //TODO add list stock total
    }

    @Override
    public String getActivityTitle() {
        return mViewModel.getScreenTitle();
    }

    @Override
    public void setListReceiverWareHouser(List<T> listReceiverWareHouser) {
        mListReceiveWareHouse.clear();
        mListReceiveWareHouse.addAll(listReceiverWareHouser);
        mSpinnerAdapterReceiveHouser.notifyDataSetChanged();
    }

    @Override
    public int getPositionReceicerWareHouse() {
        return positionReceiveWareHouse;
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
