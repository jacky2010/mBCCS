package com.viettel.mbccs.screen.serialpicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.constance.SerialStateType;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.SerialPickerModel;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.screen.serialpicker.adapter.SerialAdapter;
import com.viettel.mbccs.screen.serialpicker.adapter.SerialSelectedAdapter;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 5/14/17.
 */

public class SerialPickerPresenter
        implements SerialPickerContract.Presenter, SerialAdapter.SerialChooseListener,
        SerialSelectedAdapter.SerialSelectedListener {

    public ObservableField<String> serialFrom;
    public ObservableField<String> serialTo;
    public ObservableField<String> summary;
    public ObservableField<Boolean> chooseAble;
    public ObservableField<String> quantity;
    private SerialAdapter mSerialAdapter;
    private SerialSelectedAdapter mSerialSelectedAdapter;
    private List<String> mSerials = new ArrayList<>();
    private Context mContext;
    private SerialPickerContract.ViewModel mViewModel;
    private Set<String> mSerialSelected = new HashSet<>();
    private SerialBO currentSerialBlock = new SerialBO();
    private DataRequest<GetSerialRequest> mBaseRequest;
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private UserRepository mUserRepository;
    private CompositeSubscription mSubscription;

    private SerialPickerModel mSerialPickerModel;

    public SerialPickerPresenter(Context context, SerialPickerContract.ViewModel viewModel,
            SerialPickerModel serialPickerModel) {
        mContext = context;
        mViewModel = viewModel;
        mSerialPickerModel = serialPickerModel;
        mSubscription = new CompositeSubscription();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        init();
        loadSerial();
    }

    private void loadSerial() {
        mViewModel.showLoading();
        GetSerialRequest mSerialRequest = new GetSerialRequest();
        mSerialRequest.setOwnerType(2);
        mSerialRequest.setOwnerId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());//TODO
        mSerialRequest.setStateId(SerialStateType.TYPE_NEW);
        mSerialRequest.setQuantity(mSerialPickerModel.getQuantity());
        mSerialRequest.setStockModelId(mSerialPickerModel.getStockModelId());
        mBaseRequest = new DataRequest<>();
        mBaseRequest.setApiCode(ApiCode.GetListSerial);
        mBaseRequest.setParameterApi(mSerialRequest);

        Subscription subscription = mBanHangKhoTaiChinhRepository.getSerial(mBaseRequest)
                .subscribe(new MBCCSSubscribe<GetSerialsResponse>() {
                    @Override
                    public void onSuccess(GetSerialsResponse object) {
                        if (object != null
                                && object.getSerialSale() != null
                                && object.getSerialSale().getSerialBOs() != null
                                && object.getSerialSale().getSerialBOs().size() > 0) {
                            List<SerialBO> serialBOs = object.getSerialSale().getSerialBOs();
                            mSerials.addAll(Common.getSerialsByListSerialBlock(serialBOs));
                            reCaculateSerial();
                            return;
                        }
                        onError(new Throwable());
                    }

                    @Override
                    public void onError(BaseException error) {
                        //fakeData();
                        DialogUtils.showDialogError(mContext, null, error.getMessage(), null);
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });
        mSubscription.add(subscription);
    }

    private void reCaculateSerial() {
        if (mSerialPickerModel == null) {
            return;
        }
        mSerialSelected.addAll(
                Common.getSerialsByListSerialBlock(mSerialPickerModel.getLstSerial()));
        mSerials.removeAll(Common.getSerialsByListSerialBlock(mSerialPickerModel.getLstSerial()));
        mSerialAdapter.refresh();
        mSerialSelectedAdapter.refreshData();
        refreshProgressSerial();
    }

    private void init() {

        chooseAble = new ObservableField<>();
        serialFrom = new ObservableField<String>() {
            @Override
            public void set(String value) {
                super.set(value);
                chooseAble.set(
                        !TextUtils.isEmpty(serialFrom.get()) && !TextUtils.isEmpty(serialTo.get()));
            }
        };
        serialTo = new ObservableField<String>() {
            @Override
            public void set(String value) {
                super.set(value);
                chooseAble.set(
                        !TextUtils.isEmpty(serialFrom.get()) && !TextUtils.isEmpty(serialTo.get()));
            }
        };
        summary = new ObservableField<>();

        quantity = new ObservableField<>();

        mSerialAdapter = new SerialAdapter(mContext, mSerials);
        mSerialAdapter.setSerialChooseListener(this);

        mSerialSelectedAdapter = new SerialSelectedAdapter(mContext, mSerialSelected);
        mSerialSelectedAdapter.setSerialChooseListener(this);
        refreshProgressSerial();
    }

    @Override
    public void subscribe() {

    }

    public SerialAdapter getSerialAdapter() {
        return mSerialAdapter;
    }

    public SerialSelectedAdapter getSerialSelectedAdapter() {
        return mSerialSelectedAdapter;
    }

    @Override
    public void unSubscribe() {

        mSubscription.clear();
    }

    @Override
    public void onSerialSelect(SerialBO serialBlock) {
        serialFrom.set(serialBlock.getFromSerial());
        serialTo.set(serialBlock.getToSerial());
    }

    @Override
    public void pickSerialByScanQrcode() {
        mViewModel.openQRcodeScanner();
    }

    @Override
    public void onPickSerialByQrCodeSuccess(String serial) {
        serialFrom.set(serial);
        serialTo.set(serial);
    }

    @Override
    public void chooseSerial() {

        if (chooseAble == null || !chooseAble.get()) {
            return;
        }

        if (validate()) {
            //SerialBO serialBlock = new SerialBO();
            //serialBlock.setFromSerial((serialFrom.get()));
            List<String> result = new ArrayList<>();
            int remain = (int) (mSerialPickerModel.getQuantity() - mSerialSelected.size());
            List<String> commonSerial = new ArrayList<>(mSerials);
            commonSerial.retainAll(currentSerialBlock.toSerialList());

            if (remain >= commonSerial.size()) {
                result.addAll(commonSerial);
            } else {
                for (int i = 0; i < remain; i++) {
                    result.add(commonSerial.get(i));
                }
            }

            //if (remain >= currentSerialBlock.toSerialList().size()) {
            //    serialBlock.setToSerial((serialTo.get()));
            //} else {
            //    serialBlock.setToSerial(
            //            String.valueOf(Long.parseLong(serialFrom.get()) + remain - 1));
            //}
            //
            //mSerialSelected.add(serialBlock);
            //mSerialAdapter.removeSerial(serialBlock.toSerialList());
            mSerialSelected.addAll(result);
            mSerialAdapter.removeSerial(result);
            mSerialAdapter.clearSelectedPosition();
            mSerialSelectedAdapter.refreshData();
            serialFrom.set("");
            serialTo.set("");
            refreshProgressSerial();
        }
    }

    private boolean validate() {
        try {
            currentSerialBlock.setFromSerial((serialFrom.get()));
            currentSerialBlock.setToSerial((serialTo.get()));
            if (currentSerialBlock.getQuantity() < 1) {
                DialogUtils.showDialogError(mContext, R.string.invalid_serial);
                return false;
            }

            if (mSerials.size() == 0) {
                DialogUtils.showDialogError(mContext, R.string.not_enought_serial);
                return false;
            }
            if (Long.parseLong(currentSerialBlock.getFromSerial()) < Long.valueOf(
                    mSerials.get(0))) {
                currentSerialBlock.setFromSerial(mSerials.get(0));
            }

            if (Long.parseLong(currentSerialBlock.getToSerial()) > Long.valueOf(
                    mSerials.get(mSerials.size() - 1))) {
                currentSerialBlock.setToSerial(mSerials.get(mSerials.size() - 1));
            }

            List<String> commonSerial = new ArrayList<>(mSerials);
            commonSerial.retainAll(currentSerialBlock.toSerialList());
            if (commonSerial.size() <= 0) {
                DialogUtils.showDialogError(mContext, R.string.invalid_serial);
                return false;
            }

            int remain = (int) (mSerialPickerModel.getQuantity() - mSerialSelected.size());
            if (remain == 0) {
                DialogUtils.showDialogError(mContext, R.string.full_serial);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void refreshProgressSerial() {
        summary.set(String.format(mContext.getResources().getString(R.string.count_serial_selected),
                mSerialSelected.size(), mSerialPickerModel.getQuantity(),
                mSerialPickerModel.getStockMoldeName()));
        quantity.set(String.valueOf(mSerialSelected.size()));
    }

    @Override
    public void onDeleteSerial(SerialBO serialBlock) {
        mSerialSelected.removeAll(serialBlock.toSerialList());
        mSerialSelectedAdapter.refreshData();
        mSerials.addAll(serialBlock.toSerialList());
        mSerialAdapter.refresh();
        refreshProgressSerial();
    }

    public void onCancel() {
        Activity activity = (Activity) mContext;
        activity.setResult(Activity.RESULT_CANCELED);
        activity.finish();
    }

    public void onSubmit() {
        Activity activity = (Activity) mContext;
        String json = GsonUtils.Object2String((mSerialSelected));
        Intent intent = new Intent();
        intent.putExtra(Constants.BundleConstant.LIST_SERIAL, json);
        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }
}
