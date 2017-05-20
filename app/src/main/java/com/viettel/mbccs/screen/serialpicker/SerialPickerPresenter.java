package com.viettel.mbccs.screen.serialpicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.SerialPickerModel;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetSerialsReponse;
import com.viettel.mbccs.screen.serialpicker.adapter.SerialAdapter;
import com.viettel.mbccs.screen.serialpicker.adapter.SerialSelectedAdapter;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.Arrays;
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
    private List<SerialBO> mSerialSelected = new ArrayList<>();
    private SerialBO currentSerialBlock = new SerialBO();
    private BaseRequest<GetSerialRequest> mBaseRequest;
    private UserRepository mUserRepository;
    private CompositeSubscription mSubscription;

    private SerialPickerModel mSerialPickerModel;

    public SerialPickerPresenter(Context context, SerialPickerContract.ViewModel viewModel,
            SerialPickerModel serialPickerModel) {
        mContext = context;
        mViewModel = viewModel;
        mSerialPickerModel = serialPickerModel;
        mSubscription = new CompositeSubscription();
        mUserRepository = UserRepository.getInstance();
        init();
        loadSerial();
    }

    private void fakeData() {
        final SerialBO serialBO = new SerialBO();
        serialBO.setFromSerial("111111");
        serialBO.setToSerial("111114");

        final SerialBO serialBO1 = new SerialBO();
        serialBO1.setFromSerial("111116");
        serialBO1.setToSerial("111116");

        final SerialBO serialBO2 = new SerialBO();
        serialBO2.setFromSerial("111118");
        serialBO2.setToSerial("111119");
        List<SerialBO> serialBOs = new ArrayList<SerialBO>();
        serialBOs.add(serialBO);
        serialBOs.add(serialBO1);
        serialBOs.add(serialBO2);
        mSerials.addAll(Common.getSerialsByListSerialBlock(serialBOs));
        reCaculateSerial();
    }

    private void loadSerial() {
        mViewModel.showLoading();
        GetSerialRequest mSerialRequest = new GetSerialRequest();
        mBaseRequest = new BaseRequest<>();
        mBaseRequest.setRequest(mSerialRequest);

        Subscription subscription = mUserRepository.getSerial(mBaseRequest)
                .subscribe(new MBCCSSubscribe<GetSerialsReponse>() {
                    @Override
                    public void onSuccess(GetSerialsReponse object) {
                        List<SerialBO> serialBOs = object.getLstSerialInStock();
                        mSerials.addAll(Common.getSerialsByListSerialBlock(serialBOs));
                        reCaculateSerial();
                    }

                    @Override
                    public void onError(BaseException error) {
                        fakeData();
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
        mSerialSelected.addAll(mSerialPickerModel.getLstSerial());
        mSerials.removeAll(Common.getSerialsByListSerialBlock(mSerialPickerModel.getLstSerial()));
        mSerialAdapter.refresh();
        mSerialSelectedAdapter.notifyDataSetChanged();
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

        if (!chooseAble.get()) {
            return;
        }

        if (validate()) {
            SerialBO serialBlock = new SerialBO();
            serialBlock.setFromSerial((serialFrom.get()));
            int remain = (int) (mSerialPickerModel.getQuantity() - getSerialCountByListSerialBlock(
                    mSerialSelected));
            if (remain >= currentSerialBlock.toSerialList().size()) {
                serialBlock.setToSerial((serialTo.get()));
            } else {
                serialBlock.setToSerial(
                        String.valueOf(Long.parseLong(serialFrom.get()) + remain - 1));
            }

            mSerialSelected.add(serialBlock);
            mSerialAdapter.removeSerial(serialBlock.toSerialList());
            mSerialAdapter.clearSelectedPosition();
            mSerialSelectedAdapter.notifyDataSetChanged();
            serialFrom.set("");
            serialTo.set("");
            refreshProgressSerial();
        }
    }

    private boolean validate() {
        currentSerialBlock.setFromSerial((serialFrom.get()));
        currentSerialBlock.setToSerial((serialTo.get()));
        if (currentSerialBlock.getQuantity() < 1) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.invalid_serial),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mSerials.size() < currentSerialBlock.getQuantity()) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.invalid_serial),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (!mSerials.containsAll(currentSerialBlock.toSerialList())) {
                Toast.makeText(mContext, mContext.getResources().getString(R.string.invalid_serial),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        int remain = (int) (mSerialPickerModel.getQuantity() - getSerialCountByListSerialBlock(
                mSerialSelected));
        if (remain == 0) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.full_serial),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public int getSerialCountByListSerialBlock(List<SerialBO> serialBlock) {
        Set<String> sets = new HashSet<>();
        for (SerialBO s : serialBlock) {
            sets.addAll(s.toSerialList());
        }
        return sets.size();
    }

    private void refreshProgressSerial() {
        summary.set(String.format(mContext.getResources().getString(R.string.count_serial_selected),
                Common.getSerialCountByListSerialBlock(mSerialSelected),
                mSerialPickerModel.getQuantity(), mSerialPickerModel.getStockMoldeName()));
        quantity.set(String.valueOf(getSerialCountByListSerialBlock(mSerialSelected)));
    }

    @Override
    public void onDeleteSerial(SerialBO serialBlock) {
        mSerialSelected.remove(serialBlock);
        mSerialSelectedAdapter.notifyDataSetChanged();
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
        String json = GsonUtils.Object2String(Common.getSerialsByListSerialBlock(mSerialSelected));
        Intent intent = new Intent();
        intent.putExtra(Constants.BundleConstant.LIST_SERIAL, json);
        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }
}
