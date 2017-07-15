package com.viettel.mbccs.screen.warehousecommon.basecreatecmdnote;

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
import com.viettel.mbccs.constance.RejectWareHouseVisible;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.Apis;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.StockStatus;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.CreateExpCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpNoteNoCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpNoteRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.DestroyStockTransRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.response.BaseCreateCmdNoteResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelAllResponse;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.screen.nhanvientrahang.createNote.StockLapPhieuAdapter;
import com.viettel.mbccs.screen.warehousecommon.exportwarehouse.StockTransDetailAdapter;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.utils.StockTotalCompare;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.CustomDialog;
import java.util.ArrayList;
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
    public List<T> mListReceiveWareHouse = new ArrayList<>();
    public SpinnerAdapter<T> spinnerReceiveWareHouse;
    public SpinnerAdapter<StockStatus> spinnerAdapterStatus;
    protected List<StockStatus> listProductState = new ArrayList<>();
    private int positionReceiveWareHouse = 0;
    private int positionStatus = 0;
    public ObservableField<Boolean> showReject = new ObservableField<>(false);
    public ObservableField<Boolean> showClose = new ObservableField<>(false);
    public ObservableField<String> titleButtonCreate = new ObservableField<>();
    public ObservableField<String> cmdCode = new ObservableField<>();
    public ObservableField<String> receiveWarehouse = new ObservableField<>();
    public ObservableField<String> dayCreated = new ObservableField<>();

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
                    String.format(mContext.getString(R.string.common_export_label_create_cmd_from),
                            mUserRepository.getUserInfo().getShop().getShopName()));
        } else {
            titleOrder.set(
                    String.format(mContext.getString(R.string.common_export_label_create_note_from),
                            mUserRepository.getUserInfo().getShop().getShopName()));
        }

        if (mStockTrans != null) {
            cmdCode.set(String.format(
                    mContext.getString(R.string.common_cmd_prepare_export_detail_label_cmd_code),
                    String.valueOf(mStockTrans.getStockTransId())));

            receiveWarehouse.set(String.format(mContext.getString(
                    R.string.common_cmd_prepare_export_detail_label_receive_warehouse),
                    String.valueOf(mStockTrans.getToOwnerId())));

            dayCreated.set(String.format(mContext.getString(
                    R.string.common_cmd_prepare_export_detail_label_day_cmd_created),
                    DateUtils.convertStringToStringFormat(mStockTrans.getCreateDatetime(),
                            DateUtils.CALENDAR_DATE_FORMAT_DD_MM_YY)));
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

        if (mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_CMD) {
            titleButtonCreate.set(mContext.getString(R.string.common_export_label_create_cmd));
        } else {
            titleButtonCreate.set(mContext.getString(R.string.common_export_label_create_note));
        }

        //define showbutton
        if ((mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_CMD) || (
                mViewModel.getStep() == BaseCreateCommandNoteActivity.STEP_2
                        && mViewModel.getAction()
                        == BaseCreateCommandNoteActivity.ACTION_CREATE_NOTE)) {
            showAddButton.set(true);
            loadStocks();
        } else {
            showAddButton.set(false);
            loadStockTransDetail();
        }

        //check show close button for create cmd
        if (mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_CMD) {
            showClose.set(true);
        }

        //check to show reject button
        boolean checkRole = false;
        for (Apis apis : mUserRepository.getUser().getApi()) {
            if (apis.getApiCode().equals(RejectWareHouseVisible.REJECT_EXPORT)) {
                checkRole = true;
                break;
            }
        }

        if (mViewModel.getAction() == BaseCreateCommandNoteActivity.ACTION_CREATE_NOTE
                && checkRole) {
            showReject.set(true);
        }
    }

    private void createSpinnerAdapter() {
        listProductState = StockStatus.statusList();
        spinnerAdapterStatus = new SpinnerAdapter(mContext, listProductState);
        spinnerAdapterStatus.setOnItemSelectedListener(getStatusItemSelectedListener());

        spinnerReceiveWareHouse = new SpinnerAdapter<T>(mContext, mListReceiveWareHouse);
        spinnerReceiveWareHouse.setOnItemSelectedListener(getWareHouseItemSelectedListener());
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
        if (mStockTrans == null) {
            return;
        }
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
                            }
                        });
        mCompositeSubscription.add(subscription);
    }

    private void bindData(ListStockTransDetailsReponse object) {
        mStockTransDetails.clear();
        mStockTransDetails.addAll(object.getStockTransDetails());
        mStockTransDetailAdapter.notifyDataSetChanged();
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

    public void showDialogReject() {
        new CustomDialog(mContext, R.string.confirm,
                R.string.commmon_warehouse_msg_reject_create_note, true,
                R.string.common_label_close, R.string.activity_create_order_success_tu_choi, null,
                new CustomDialog.OnInputDialogListener() {
                    @Override
                    public void onClick(DialogInterface var1, int var2, String input) {
                        reject(input);
                    }
                }, null, false, true).show();
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
        request.setReasonId(mViewModel.getReasonId());
        request.setDiscountPolicy(
                Long.valueOf(mUserRepository.getUserInfo().getStaffInfo().getDiscountPolicy()));
        List<CreateExpCmdRequest.CmdStock> cmdStocks = new ArrayList<>();
        for (StockTotal stockTotal : mStockTotals) {
            CreateExpCmdRequest.CmdStock cmdStock = new CreateExpCmdRequest.CmdStock();
            cmdStock.cloneFromStockTotal(stockTotal);
            if (cmdStock.getQuantity() > 0) {
                cmdStock.setStateId(listProductState.get(positionStatus).getId());
                cmdStocks.add(cmdStock);
            }
        }
        request.setCmdStocks(cmdStocks);
        mDataRequest.setWsRequest(request);
        Subscription subscription = mBanHangKhoTaiChinhRepository.createExpCmd(mDataRequest)
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
        request.setReasonId(mViewModel.getReasonId());
        request.setDiscountPolicy(
                Long.valueOf(mUserRepository.getUserInfo().getStaffInfo().getDiscountPolicy()));
        List<CreateExpCmdRequest.CmdStock> cmdStocks = new ArrayList<>();
        for (StockTotal stockTotal : mStockTotals) {
            CreateExpCmdRequest.CmdStock cmdStock = new CreateExpCmdRequest.CmdStock();
            cmdStock.cloneFromStockTotal(stockTotal);
            if (cmdStock.getQuantity() > 0) {
                cmdStock.setStateId(listProductState.get(positionStatus).getId());
                cmdStocks.add(cmdStock);
            }
        }
        request.setCmdStocks(cmdStocks);
        mDataRequest.setWsRequest(request);
        Subscription subscription = mBanHangKhoTaiChinhRepository.createExpCmd(mDataRequest)
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

    public void reject(String input) {
        mViewModel.showLoading();
        DataRequest<DestroyStockTransRequest> dataRequest = new DataRequest<>();
        DestroyStockTransRequest request = new DestroyStockTransRequest();
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        request.setStockTransId(mStockTrans.getStockTransId());
        request.setNote(input);
        dataRequest.setWsCode(WsCode.DestroyStockTrans);
        dataRequest.setWsRequest(request);
        mBanHangKhoTaiChinhRepository.destroyStockTrans(dataRequest)
                .subscribe(new MBCCSSubscribe<EmptyObject>() {

                    @Override
                    public void onSuccess(EmptyObject object) {
                        mViewModel.onRejectExportSuccess();
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialogError(mContext, error);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });
    }

    private void onCreateCmdNoteSuccess(BaseCreateCmdNoteResponse obj) {

        StockTrans stockTrans = obj.getStockTrans();

        //TODO show chi tiet lenh xuat

        //TODO show lap phieu thanh cong

        mViewModel.onCreateExportSuccess(stockTrans);
        Toast.makeText(mContext, "show dialog success", Toast.LENGTH_SHORT).show();
    }

    private void loadStocks() {
        mViewModel.showLoading();
        DataRequest<GetListStockModelRequest> mGetListStockModelRequestBaseRequest =
                new DataRequest<>();
        mGetListStockModelRequestBaseRequest.setWsCode(WsCode.GetListStockModelAll);
        GetListStockModelRequest request = new GetListStockModelRequest();
        //request.setStockTypeId(StockTotalType.TYPE_NEW);
        //request.setStateId(StockTotalType.TYPE_NEW);
        request.setOwnerType(mViewModel.getOwnerStockTypeCreate());
        request.setSaleTransType(SaleTranType.SALE_RETAIL);
        request.setOwnerId(mViewModel.getOwnerStockIdCreate());
        mGetListStockModelRequestBaseRequest.setWsRequest(request);
        Subscription subscription = mBanHangKhoTaiChinhRepository.getListStockModelAll(
                mGetListStockModelRequestBaseRequest)
                .subscribe(new MBCCSSubscribe<GetListStockModelAllResponse>() {
                    @Override
                    public void onSuccess(GetListStockModelAllResponse object) {
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
        spinnerReceiveWareHouse.notifyDataSetChanged();
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
