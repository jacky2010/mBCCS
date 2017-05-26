package com.viettel.mbccs.screen.transferanypay.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.TransferAnyPayRepository;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.variable.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class CreateTransferAnyPayPresenter implements CreateTransferAnyPayContract.Presenter {

    public static final String PAY_METHOD_REFILL = "0";
    public static final String PAY_METHOD_TRANSFER = "1";

    private Context context;
    private CreateTransferAnyPayContract.ViewModel viewModel;

    private ArrayAdapter<String> transTypesAdapter;
    private ArrayAdapter<String> payAmountAdapter;

    public ObservableField<String> transferType;
    public ObservableField<String> isdn;
    public ObservableField<String> refillAmount;
    public ObservableField<String> transferAmount;

    public ObservableBoolean defaultAmountChecked;

    private List<String> transTypesList;
    private List<String> payAmountList;
    private List<KeyValue> transTypes;
    private List<KeyValue> payAmounts;

    private TransferAnyPayRepository repository;
    private String selectedDefaultAmount;

    public CreateTransferAnyPayPresenter(Context context, final CreateTransferAnyPayContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        isdn = new ObservableField<>();
        transferType = new ObservableField<>();
        transferAmount = new ObservableField<>();
        refillAmount = new ObservableField<>();
        defaultAmountChecked = new ObservableBoolean(true);

        transTypesList = new ArrayList<>();
        payAmountList = new ArrayList<>();

        transTypesAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                transTypesList);
        payAmountAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                payAmountList);

        initListeners();
        initData();
    }

    private void initData() {
        try {
            repository = TransferAnyPayRepository.getInstance();

//            custTypes = repository.getCustTypes();
            transTypes = repository.getTransferTypes();
            payAmounts = repository.getDefaultAmounts();
//
//            for (KeyValue item : custTypes) {
//                custTypesList.add(item.getValue());
//            }
//
            for (KeyValue item : transTypes) {
                transTypesList.add(item.getValue());
            }
//
            for (KeyValue item : payAmounts) {
                payAmountList.add(item.getValue());
            }
//
//            for (KeyValue item : repository.getBankPlusAmounts()) {
//                bankPlusAmountList.add(item.getValue());
//            }
//
//            custTypeAdapter.notifyDataSetChanged();
            transTypesAdapter.notifyDataSetChanged();
            payAmountAdapter.notifyDataSetChanged();
//            bankPlusAmountAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListeners(){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void createTransaction() {
        try{

            double preTax = 0;
            double tax = 0;
            double discount = 0;
            double total = 0;

            if(isdn.get() == null || "".equals(isdn.get().trim())){
                viewModel.showError("So isdn khong duoc de trong");
                return;
            }

            if(PAY_METHOD_REFILL.equals(transferType.get())){
                if(defaultAmountChecked.get()){

                    if(selectedDefaultAmount == null || "".equals(selectedDefaultAmount.trim())){
                        viewModel.showError("So tien khong duoc de trong");

                        return;
                    }

                    total = Double.parseDouble(selectedDefaultAmount.trim());

                }else {
                    if(refillAmount.get() == null || "".equals(refillAmount.get().trim())){
                        viewModel.showError("So tien khong duoc de trong");

                        return;
                    }

                    total = Double.parseDouble(refillAmount.get().trim());
                }
            }else if(PAY_METHOD_TRANSFER.equals(transferType.get())){

                if(transferAmount.get() == null || "".equals(transferAmount.get().trim())){
                    viewModel.showError("So tien khong duoc de trong");

                    return;
                }

                total = Double.parseDouble(transferAmount.get().trim());
            }

            if(total <= 0){
                viewModel.showError("So tien phai lon hon 0");

                return;
            }

            tax = Math.floor(total / Constants.Tax.DEFAULT_TAX_CALC_BACK_RATE);
            preTax = total - tax;

            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.CUSTOMER_ITEM, isdn.get().trim());
            args.putString(Constants.BundleConstant.PRE_TAX, Common.formatDouble(preTax));
            args.putString(Constants.BundleConstant.TAX, Common.formatDouble(tax));
            args.putString(Constants.BundleConstant.DISCOUNT, Common.formatDouble(discount));
            args.putString(Constants.BundleConstant.TOTAL, Common.formatDouble(total));

            viewModel.goToDialogFragment(PAY_METHOD_REFILL.equals(transferType.get()), args);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDefaultAmountChanged(int index) {
        try{
            selectedDefaultAmount = payAmounts.get(index).getKey();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTransTypeChanged(int index) {
        try{
            KeyValue item = transTypes.get(index);

            if(item != null){
                if(PAY_METHOD_REFILL.equals(item.getKey())) {
                    viewModel.onTransferTypeChanged(CreateTransferAnyPayContract.TransferType.REFILL);

                    transferType.set(PAY_METHOD_REFILL);
                }else if(PAY_METHOD_TRANSFER.equals(item.getKey())) {
                    viewModel.onTransferTypeChanged(CreateTransferAnyPayContract.TransferType.TRANSFER);

                    transferType.set(PAY_METHOD_TRANSFER);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onDefaultAmountCheckChanged(CompoundButton button, boolean checked){
        if(checked)
            defaultAmountChecked.set(true);
        else
            defaultAmountChecked.set(false);

        viewModel.onDefaultAmountChanged(defaultAmountChecked.get());
    }

    public void onOtherAmountCheckChanged(CompoundButton button, boolean checked){
        if(checked)
            defaultAmountChecked.set(false);
        else
            defaultAmountChecked.set(true);

        viewModel.onDefaultAmountChanged(defaultAmountChecked.get());
    }

    public ArrayAdapter<String> getTransTypesAdapter() {
        return transTypesAdapter;
    }

    public ArrayAdapter<String> getPayAmountAdapter() {
        return payAmountAdapter;
    }

}
