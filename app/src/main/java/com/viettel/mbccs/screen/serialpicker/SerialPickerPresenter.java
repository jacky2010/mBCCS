package com.viettel.mbccs.screen.serialpicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockItem;
import com.viettel.mbccs.data.model.SerialBlock;
import com.viettel.mbccs.screen.serialpicker.adapter.SerialAdapter;
import com.viettel.mbccs.screen.serialpicker.adapter.SerialSelectedAdapter;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private List<Integer> mSerials = new ArrayList<>();
    private Context mContext;
    private SerialPickerContract.ViewModel mViewModel;
    private List<SerialBlock> mSerialSelected = new ArrayList<>();
    private SerialBlock currentSerialBlock = new SerialBlock();

    private StockItem mGoodItem;

    public SerialPickerPresenter(Context context, SerialPickerContract.ViewModel viewModel,
            StockItem goodItem) {
        mContext = context;
        mViewModel = viewModel;
        mGoodItem = goodItem;
        loadSerial();
    }

    private void loadSerial() {
        mSerials.addAll(Arrays.asList(new Integer[] {
                11111, 11112, 11113, 11114, 11116, 11117, 11119, 11121, 11122, 11123, 11124, 11125,
                11126, 11127, 11130, 11131, 11133, 11135, 11136, 11137, 11138,
        }));

        init();
    }

    private void init() {
        if (mGoodItem == null) {
            return;
        }
        mSerialSelected.addAll(Common.getSerialBlockBySerials(mGoodItem.getSerials()));
        mSerials.removeAll(mGoodItem.getSerials());

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
        chooseAble = new ObservableField<>();
        quantity = new ObservableField<>();

        mSerialAdapter = new SerialAdapter(mContext, mSerials);
        mSerialAdapter.setSerialChooseListener(this);

        mSerialSelectedAdapter = new SerialSelectedAdapter(mContext, mSerialSelected);
        mSerialSelectedAdapter.setSerialChooseListener(this);
        refreshProgressSerial();
        quantity.set(String.valueOf(getSerialCountByListSerialBlock(mSerialSelected)));
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

    }

    @Override
    public void onSerialSelect(SerialBlock serialBlock) {
        serialFrom.set(serialBlock.getFromString());
        serialTo.set(serialBlock.getToString());
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
            SerialBlock serialBlock = new SerialBlock();
            serialBlock.setFrom(Integer.parseInt(serialFrom.get()));
            int remain =
                    mGoodItem.getChoiceCount() - getSerialCountByListSerialBlock(mSerialSelected);
            if (remain >= currentSerialBlock.toSerialList().size()) {
                serialBlock.setTo(Integer.parseInt(serialTo.get()));
            } else {
                serialBlock.setTo(Integer.parseInt(serialFrom.get()) + remain - 1);
            }

            mSerialSelected.add(serialBlock);
            mSerialAdapter.removeSerial(serialBlock.toSerialList());
            mSerialAdapter.clearSelectedPosition();
            mSerialSelectedAdapter.notifyDataSetChanged();
            serialFrom.set("");
            serialTo.set("");

            refreshProgressSerial();
            quantity.set(String.valueOf(getSerialCountByListSerialBlock(mSerialSelected)));
        }
    }

    private boolean validate() {
        currentSerialBlock.setFrom(Integer.parseInt(serialFrom.get()));
        currentSerialBlock.setTo(Integer.parseInt(serialTo.get()));
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

        int remain = mGoodItem.getChoiceCount() - getSerialCountByListSerialBlock(mSerialSelected);
        if (remain == 0) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.full_serial),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public int getSerialCountByListSerialBlock(List<SerialBlock> serialBlock) {
        Set<Integer> sets = new HashSet<>();
        for (SerialBlock s : serialBlock) {
            sets.addAll(s.toSerialList());
        }
        return sets.size();
    }

    private void refreshProgressSerial() {
        summary.set(String.format(mContext.getResources().getString(R.string.count_serial_selected),
                Common.getSerialCountByListSerialBlock(mSerialSelected), mGoodItem.getChoiceCount(),
                mGoodItem.getName()));
    }

    @Override
    public void onDeleteSerial(SerialBlock serialBlock) {
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
