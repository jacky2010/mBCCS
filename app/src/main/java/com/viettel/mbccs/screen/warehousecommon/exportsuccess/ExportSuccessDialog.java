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
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.request.GetStockTransSerialDetailRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetStockTransSerialDetailResponse;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.databinding.DialogExportSuccessBinding;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;
import com.viettel.mbccs.screen.warehousecommon.exportwarehouse.StockTransDetailAdapter;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.StringUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 6/21/17.
 */

public class ExportSuccessDialog extends DialogFragment
        implements StockTransDetailAdapter.OnStockTransDetailAdapterListener {

    private StockTransDetailAdapter mAdapter;
    public ObservableField<String> cmdCode;
    public ObservableField<String> cmdReceive;
    public ObservableField<String> cmdSender;
    public ObservableField<String> cmdTitle;
    private List<StockTransDetail> mStockTransDetails = new ArrayList<>();
    private String cmdCodeTitle;
    private String cmdReceiveTitle;
    private String cmdSenderTitle;
    private String title;
    DialogExportSuccessBinding mBinding;
    private AppCompatActivity mAppCompatActivity;
    private List<StockSerial> mStockSerials = new ArrayList<>();
    private OnDialogDismissListener mOnDialogDismissListener;
    private StockTrans mStockTrans;
    private UserRepository mUserRepository;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private CompositeSubscription mSubscription;
    private boolean isShowSerial = true;

    public OnDialogDismissListener getOnDialogDismissListener() {
        return mOnDialogDismissListener;
    }

    public void setOnDialogDismissListener(OnDialogDismissListener onDialogDismissListener) {
        mOnDialogDismissListener = onDialogDismissListener;
    }

    public static ExportSuccessDialog newInstance(StockTrans stockTrans, String title,
            String cmdCodeTitle,
            String cmdNameTitle) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        bundle.putString(Constants.BundleConstant.CMD_CODE_TITLE, cmdCodeTitle);
        bundle.putString(Constants.BundleConstant.CMD_RECEIVE_TITLE, cmdNameTitle);
        bundle.putString(Constants.BundleConstant.CMD_TITLE, title);
        ExportSuccessDialog fragment = new ExportSuccessDialog();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ExportSuccessDialog newInstance(StockTrans stockTrans, String title,
            String cmdCodeTitle,
            String cmdNameTitle, boolean isShowSerial) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        bundle.putString(Constants.BundleConstant.CMD_CODE_TITLE, cmdCodeTitle);
        bundle.putString(Constants.BundleConstant.CMD_RECEIVE_TITLE, cmdNameTitle);
        bundle.putBoolean(Constants.BundleConstant.SHOW_SERIAL, isShowSerial);
        bundle.putString(Constants.BundleConstant.CMD_TITLE, title);
        ExportSuccessDialog fragment = new ExportSuccessDialog();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ExportSuccessDialog newInstance(StockTrans stockTrans, String title,
            String cmdCodeTitle,
            String cmdNameTitle, String cmdSenderTitle) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        bundle.putString(Constants.BundleConstant.CMD_CODE_TITLE, cmdCodeTitle);
        bundle.putString(Constants.BundleConstant.CMD_RECEIVE_TITLE, cmdNameTitle);
        bundle.putString(Constants.BundleConstant.CMD_SENDER_TITLE, cmdSenderTitle);
        bundle.putString(Constants.BundleConstant.CMD_TITLE, title);
        ExportSuccessDialog fragment = new ExportSuccessDialog();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ExportSuccessDialog newInstance(StockTrans stockTrans, String title,
            String cmdCodeTitle,
            String cmdNameTitle, String cmdSenderTitle, boolean isShowSerial) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        bundle.putString(Constants.BundleConstant.CMD_CODE_TITLE, cmdCodeTitle);
        bundle.putString(Constants.BundleConstant.CMD_RECEIVE_TITLE, cmdNameTitle);
        bundle.putString(Constants.BundleConstant.CMD_SENDER_TITLE, cmdSenderTitle);
        bundle.putBoolean(Constants.BundleConstant.SHOW_SERIAL, isShowSerial);
        bundle.putString(Constants.BundleConstant.CMD_TITLE, title);
        ExportSuccessDialog fragment = new ExportSuccessDialog();
        fragment.setArguments(bundle);
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

        mUserRepository = UserRepository.getInstance();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mSubscription = new CompositeSubscription();
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        mStockTrans = bundle.getParcelable(Constants.BundleConstant.STOCK_TRANS);
        cmdCodeTitle = bundle.getString(Constants.BundleConstant.CMD_CODE_TITLE);
        cmdReceiveTitle = bundle.getString(Constants.BundleConstant.CMD_RECEIVE_TITLE);
        cmdSenderTitle = bundle.getString(Constants.BundleConstant.CMD_SENDER_TITLE);
        title = bundle.getString(Constants.BundleConstant.CMD_TITLE);
        isShowSerial = bundle.getBoolean(Constants.BundleConstant.SHOW_SERIAL, true);
        mAdapter = new StockTransDetailAdapter(getActivity(), mStockTransDetails,
                getActivity().getString(R.string.item_nhap_kho_cap_duoi_mat_hang_xem_serial),
                isShowSerial);
        mAdapter.setOnStockTransAdapterListerner(this);
        init();
        mBinding.setDialog(this);
    }

    private void init() {
        cmdCode = new ObservableField<>();
        cmdReceive = new ObservableField<>();
        cmdSender = new ObservableField<>();
        cmdTitle = new ObservableField<>();
        cmdCode.set(cmdCodeTitle);
        cmdReceive.set(cmdReceiveTitle);
        cmdSender.set(cmdSenderTitle);
        cmdTitle.set(StringUtils.isEmpty(title) ? getString(R.string.common_label_notice) : title);

        getStockTransDetail();
    }

    private void getStockTransDetail() {
        DataRequest mDataRequest = new DataRequest<>();
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
                                    mStockTransDetails.clear();
                                    mStockTransDetails.addAll(object.getStockTransDetails());
                                    mAdapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onError(BaseException error) {
                                DialogUtils.showDialogError(mAppCompatActivity, error);
                            }
                        });
        mSubscription.add(subscription);
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
    public void onSerialPickerClick(int position, final StockTransDetail stockTransDetail) {
        if (stockTransDetail == null || mStockTrans == null) {
            return;
        }
        DataRequest<GetStockTransSerialDetailRequest> dataRequest = new DataRequest<>();
        GetStockTransSerialDetailRequest request = new GetStockTransSerialDetailRequest();
        request.setStockTransId(mStockTrans.getStockTransId());
        request.setStockModelId(stockTransDetail.getStockModelId());
        dataRequest.setWsCode(WsCode.GetStockTransSerialDetail);
        dataRequest.setWsRequest(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.getStockTransDetailSerial(dataRequest).subscribe(

                        new MBCCSSubscribe<GetStockTransSerialDetailResponse>() {
                            @Override
                            public void onSuccess(GetStockTransSerialDetailResponse object) {
                                if (object != null && object.getSerialBOList() != null) {
                                    StockSerial stockSerial = new StockSerial();
                                    stockSerial.setSerialBOs(object.getSerialBOList());
                                    stockSerial.setStockModelId(stockTransDetail.getStockModelId());
                                    stockSerial.setStockModelName(
                                            stockTransDetail.getStockModelName());
                                    stockSerial.setQuantity(Common.getSerialCountByListSerialBlock(
                                            stockSerial.getSerialBOs()));
                                    DialogViewSerial dialogViewSerial =
                                            DialogViewSerial.newInstance();
                                    dialogViewSerial.setStockSerial(stockSerial);
                                    dialogViewSerial.show(
                                            mAppCompatActivity.getSupportFragmentManager(), "");
                                }
                            }

                            @Override
                            public void onError(BaseException error) {

                                DialogUtils.showDialogError(mAppCompatActivity, error);
                            }
                        });
        mSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        super.onStop();
        mSubscription.clear();
    }

    @Override
    public void onItemClick(int position, StockTransDetail data) {

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDialogDismissListener != null) {
            mOnDialogDismissListener.onDialogDismiss();
        }
    }

    public interface OnDialogDismissListener {
        void onDialogDismiss();
    }
}
