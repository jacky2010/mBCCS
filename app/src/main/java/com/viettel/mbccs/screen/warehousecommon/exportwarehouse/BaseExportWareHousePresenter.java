package com.viettel.mbccs.screen.warehousecommon.exportwarehouse;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.DestroyStockTransRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.response.BaseCreateCmdNoteResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.widget.CustomDialog;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 6/20/17.
 */

public class BaseExportWareHousePresenter implements BaseExportWareHouseContract.Presenter,
        StockTransDetailAdapter.OnStockTransDetailAdapterListener {
    public ObservableField<String> title;
    private Context mContext;
    private BaseExportWareHouseContract.ViewModel mViewModel;
    private StockTrans mStockTrans;
    public ObservableField<String> cmdCode;
    public ObservableField<String> receiveWarehouse;
    public ObservableField<String> dayCreated;
    public ObservableField<String> status;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private UserRepository mUserRepository;
    private DataRequest<GetListStockTransDetailRequest> mDataRequest;
    private DataRequest<CreateExpStockRequest> mCreateExpStockRequest;
    private ArrayList<StockTransDetail> mStockTransDetails = new ArrayList<>();
    private StockTransDetailAdapter mAdapter;
    private CompositeSubscription mSubscription;
    private int currentPosition = -1;

    public BaseExportWareHousePresenter(Context context,
            BaseExportWareHouseContract.ViewModel viewModel, StockTrans stockTrans) {
        mContext = context;
        mViewModel = viewModel;
        this.mStockTrans = stockTrans;
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        mSubscription = new CompositeSubscription();
        initData();
        loadData();
    }

    private void initData() {
        title = new ObservableField<>();
        title.set(mViewModel.getToolBarTitle());
        cmdCode = new ObservableField<>();
        receiveWarehouse = new ObservableField<>();
        dayCreated = new ObservableField<>();
        status = new ObservableField<>();

        cmdCode.set(String.format(
                mContext.getString(R.string.common_cmd_prepare_export_detail_label_cmd_code),
                String.valueOf(mStockTrans.getStockTransId())));

        receiveWarehouse.set(String.format(mContext.getString(
                R.string.common_cmd_prepare_export_detail_label_receive_warehouse),
                String.valueOf(mStockTrans.getToOwnerId())));

        dayCreated.set(String.format(
                mContext.getString(R.string.common_cmd_prepare_export_detail_label_day_cmd_created),
                mStockTrans.getCreateDatetime()));

        status.set(String.format(
                mContext.getString(R.string.common_cmd_prepare_export_detail_label_status),
                mStockTrans.getStockTransStatusName()));

        mAdapter = new StockTransDetailAdapter(mContext, mStockTransDetails);
        mAdapter.setOnStockTransAdapterListerner(this);
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
                                DialogUtils.showDialog(mContext, error.getMessage());
                            }
                        });
        mSubscription.add(subscription);
    }

    private void bindData(ListStockTransDetailsReponse object) {
        mStockTransDetails.clear();
        mStockTransDetails.addAll(object.getStockTransDetails());
        mAdapter.notifyDataSetChanged();
    }


    public StockTransDetailAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {
        mSubscription.clear();
    }

    public void export() {
        new CustomDialog(mContext, R.string.confirm,
                R.string.common_cmd_prepare_export_msg_detail_accept_export, false,
                R.string.common_label_close, R.string.common_label_export, null,
                new CustomDialog.OnInputDialogListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int var2, String input) {
                        dialogInterface.dismiss();
                        createExpStock();
                    }
                }, null, false, false).show();
    }

    private void createExpStock() {
        if (!validate()) {
            return;
        }
        mViewModel.showLoading();
        mCreateExpStockRequest = new DataRequest<>();
        mCreateExpStockRequest.setWsCode(WsCode.CreateExpStock);
        CreateExpStockRequest request = new CreateExpStockRequest();
        request.setStockTransId(mStockTrans.getStockTransId());
        List<StockSerial> stockSerials = new ArrayList<>();
        for (StockTransDetail stockTransDetail : mStockTransDetails) {
            stockSerials.add(stockTransDetail.getStockSerial());
        }
        request.setStockSerials(stockSerials);
        mCreateExpStockRequest.setWsRequest(request);
        Subscription subcription =
                mBanHangKhoTaiChinhRepository.createExpStock(mCreateExpStockRequest).subscribe(

                        new MBCCSSubscribe<BaseCreateCmdNoteResponse>() {
                            @Override
                            public void onSuccess(BaseCreateCmdNoteResponse object) {
                                mViewModel.onCreateExpStockSuccess(mStockTrans);
                            }

                            @Override
                            public void onError(BaseException error) {
                                DialogUtils.showDialog(mContext, null, error.getMessage(), null);
                            }

                            @Override
                            public void onRequestFinish() {
                                super.onRequestFinish();
                                mViewModel.hideLoading();
                            }
                        });
        mSubscription.add(subcription);
    }

    public boolean validate() {
        for (StockTransDetail stockTransDetail : mStockTransDetails) {
            if (!stockTransDetail.isPickSerialOk()) {
                DialogUtils.showDialog(mContext, String.format(mContext.getString(
                        R.string.common_cmd_prepare_export_detail_msg_error_choose_serial),
                        stockTransDetail.getStockModelName()));
                return false;
            }
        }

        return true;
    }

    public void reject() {
        new CustomDialog(mContext, R.string.common_cmd_prepare_export_msg_detail_reject_export,
                R.string.activity_create_order_success_ly_do_tu_choi, true,
                R.string.common_label_close, R.string.activity_create_order_success_tu_choi, null,
                new CustomDialog.OnInputDialogListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int var2, String input) {
                        dialogInterface.dismiss();
                        rejectExport(input);
                    }
                }, null, false, true).show();
    }

    private void rejectExport(String input) {
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
                        ((BaseCreateImportWareHouseActivity) mContext).finish();
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

    public void onCancel() {

    }

    @Override
    public void onItemClick(int position, StockTransDetail data) {

    }

    @Override
    public void onSerialPickerClick(int position, StockTransDetail stockTransDetail) {
        currentPosition = position;
        mViewModel.onSerialPicker(stockTransDetail);
    }

    @Override
    public void onSerialPickerSuccess(List<String> serials) {
        mStockTransDetails.get(currentPosition).setSerials(serials);
        mAdapter.notifyItemChanged(currentPosition);
    }
}
