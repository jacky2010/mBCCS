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
import com.viettel.mbccs.data.model.StockTransDetail;
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
    private List<StockTransDetail> mStockTransDetails;
    private String cmdCodeTitle;
    private String cmdReceiveTitle;
    DialogExportSuccessBinding mBinding;
    private AppCompatActivity mAppCompatActivity;
    private List<StockSerial> mStockSerials = new ArrayList<>();
    private static boolean isFromBundle = false;
    private OnDialogDismissListener mOnDialogDismissListener;

    public OnDialogDismissListener getOnDialogDismissListener() {
        return mOnDialogDismissListener;
    }

    public void setOnDialogDismissListener(OnDialogDismissListener onDialogDismissListener) {
        mOnDialogDismissListener = onDialogDismissListener;
    }

    public static ExportSuccessDialog newInstance(ArrayList<StockTransDetail> stockTransDetail,
            String cmdCodeTitle, String cmdNameTitle) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.BundleConstant.STOCK_TRANS_DETAIL_LIST,
                stockTransDetail);
        bundle.putString(Constants.BundleConstant.CMD_CODE_TITLE, cmdCodeTitle);
        bundle.putString(Constants.BundleConstant.CMD_NAME_TITLE, cmdNameTitle);
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
            cmdCodeTitle = bundle.getString(Constants.BundleConstant.CMD_CODE_TITLE);
            cmdReceiveTitle = bundle.getString(Constants.BundleConstant.CMD_NAME_TITLE);
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
        cmdCode.set(cmdCodeTitle);
        cmdReceive.set(cmdReceiveTitle);
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
        StockSerial stockSerial = new StockSerial();
        stockSerial.setStockModelId(stockTransDetail.getStockModelId());
        stockSerial.setStockModelName(stockTransDetail.getStockModelName());
        stockSerial.setStockModelCode(stockTransDetail.getStockModelCode());
        stockSerial.setQuantity(stockTransDetail.getSerialCount());
        stockSerial.setSerialBOs(stockTransDetail.getSerialBlocks());
        DialogViewSerial dialogViewSerial = DialogViewSerial.newInstance();
        dialogViewSerial.setStockSerial(stockSerial);
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
