package com.viettel.mbccs.screen.warehousecommon.exportsuccess;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.ViewInforSerialRequest;
import com.viettel.mbccs.databinding.DialogExportSuccessBinding;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;
import com.viettel.mbccs.screen.warehousecommon.cmdprepareexportdetail.StockTransDetailAdapter;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 6/21/17.
 */

public class ExportSuccessDialog extends DialogFragment
        implements StockTransDetailAdapter.OnStockTransDetailAdapterListener {

    private StockTransDetailAdapter mAdapter;

    public ObservableField<String> cmdCode;
    public ObservableField<String> cmdReceive;
    public ObservableField<String> cmdSender;
    private List<StockTransDetail> mStockTransDetails;
    private String cmdCodeTitle;
    private String cmdReceiveTitle;
    private String cmdSenderTitle;
    DialogExportSuccessBinding mBinding;
    private AppCompatActivity mAppCompatActivity;
    private List<StockSerial> mStockSerials = new ArrayList<>();
    private static boolean isFromBundle = false;
    private OnDialogDismissListener mOnDialogDismissListener;
    private StockTrans mStockTrans;

    public OnDialogDismissListener getOnDialogDismissListener() {
        return mOnDialogDismissListener;
    }

    public void setOnDialogDismissListener(OnDialogDismissListener onDialogDismissListener) {
        mOnDialogDismissListener = onDialogDismissListener;
    }

    public static ExportSuccessDialog newInstance(ArrayList<StockTransDetail> stockTransDetail,
            StockTrans stockTrans, String cmdCodeTitle, String cmdNameTitle) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.BundleConstant.STOCK_TRANS_DETAIL_LIST,
                stockTransDetail);
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        bundle.putString(Constants.BundleConstant.CMD_CODE_TITLE, cmdCodeTitle);
        bundle.putString(Constants.BundleConstant.CMD_RECEIVE_TITLE, cmdNameTitle);
        ExportSuccessDialog fragment = new ExportSuccessDialog();
        fragment.setArguments(bundle);
        isFromBundle = true;
        return fragment;
    }

    public static ExportSuccessDialog newInstance(ArrayList<StockTransDetail> stockTransDetail,
            StockTrans stockTrans, String cmdCodeTitle, String cmdNameTitle,
            String cmdSenderTitle) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.BundleConstant.STOCK_TRANS_DETAIL_LIST,
                stockTransDetail);
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        bundle.putString(Constants.BundleConstant.CMD_CODE_TITLE, cmdCodeTitle);
        bundle.putString(Constants.BundleConstant.CMD_RECEIVE_TITLE, cmdNameTitle);
        bundle.putString(Constants.BundleConstant.CMD_SENDER_TITLE, cmdSenderTitle);
        ExportSuccessDialog fragment = new ExportSuccessDialog();
        fragment.setArguments(bundle);
        isFromBundle = true;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow()
                    .setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }
    }

    public static ExportSuccessDialog newInstance() {
        isFromBundle = false;
        return new ExportSuccessDialog();
    }

    public void setCmdCodeTitle(String codeTitle) {
        cmdCodeTitle = codeTitle;
    }

    public void setCmdNameTitle(String receivetitle) {
        cmdReceiveTitle = receivetitle;
    }

    public void setStockSerials(ArrayList<StockSerial> stockSerials) {
        mStockSerials.addAll(stockSerials);
    }

    public void setCmdSender(ObservableField<String> cmdSender) {
        this.cmdSender = cmdSender;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DialogExportSuccessBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    public void onClose() {
        dismiss();
    }

    protected void initData() {

        if (isFromBundle) {
            Bundle bundle = getArguments();
            if (bundle == null) {
                return;
            }
            mStockTransDetails =
                    bundle.getParcelableArrayList(Constants.BundleConstant.STOCK_TRANS_DETAIL_LIST);
            mStockTrans = bundle.getParcelable(Constants.BundleConstant.STOCK_TRANS);
            cmdCodeTitle = bundle.getString(Constants.BundleConstant.CMD_CODE_TITLE);
            cmdReceiveTitle = bundle.getString(Constants.BundleConstant.CMD_RECEIVE_TITLE);
            cmdSenderTitle = bundle.getString(Constants.BundleConstant.CMD_SENDER_TITLE);
        } else {
            if (mStockTransDetails == null && mStockSerials != null) {
                mStockTransDetails = new ArrayList<>();
                for (StockSerial stockSerial : mStockSerials) {
                    StockTransDetail stockTransDetail = new StockTransDetail();
                    stockTransDetail.setStockModelId(stockSerial.getStockModelId());
                    stockTransDetail.setQuantity(stockSerial.getQuantity());
                    stockTransDetail.setSerialBlocks(stockSerial.getSerialBOs());
                    stockTransDetail.setStockModelCode(stockSerial.getStockModelCode());
                    stockTransDetail.setStockModelName(stockSerial.getStockModelName());
                    mStockTransDetails.add(stockTransDetail);
                }
            }
        }
        mAdapter = new StockTransDetailAdapter(getActivity(), mStockTransDetails,
                getActivity().getString(R.string.item_nhap_kho_cap_duoi_mat_hang_xem_serial));
        mAdapter.setOnStockTransAdapterListerner(this);
        init();
        mBinding.setDialog(this);
    }

    private void init() {
        cmdCode = new ObservableField<>();
        cmdReceive = new ObservableField<>();
        cmdSender = new ObservableField<>();
        cmdCode.set(cmdCodeTitle);
        cmdReceive.set(cmdReceiveTitle);
        cmdSender.set(cmdSenderTitle);

        //        cmdCode.set(String.format(getString(R.string
        // .warehouse_label_export_success_code), ""));
        //        cmdReceive.set(String.format(getString(R.string.warehouse_label_receive), ""));
    }

    public StockTransDetailAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(StockTransDetailAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mAppCompatActivity = (AppCompatActivity) activity;
    }

    @Override
    public void onSerialPickerClick(int position, StockTransDetail stockTransDetail) {
        if (stockTransDetail == null || mStockTrans == null) {
            return;
        }
        StockTotal stockTotal = new StockTotal();
        stockTotal.setStateId(stockTransDetail.getStateId());
        stockTotal.setOwnerId(mStockTrans.getFromOwnerId());
        stockTotal.setOwnerType(mStockTrans.getFromOwnerType());
        stockTotal.setStockModelId(stockTransDetail.getStockModelId());
        DialogViewSerial dialogViewSerial = DialogViewSerial.newInstance();
        dialogViewSerial.setStockTotal(stockTotal);
        dialogViewSerial.show(mAppCompatActivity.getSupportFragmentManager(), "");
    }

    @Override
    public void onItemClick(int position, StockTransDetail data) {

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDialogDismissListener != null) {
            mOnDialogDismissListener.onDialogDissmis();
        }
    }

    public interface OnDialogDismissListener {
        void onDialogDissmis();
    }
}
