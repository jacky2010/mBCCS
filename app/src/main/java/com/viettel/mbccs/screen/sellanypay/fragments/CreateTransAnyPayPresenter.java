package com.viettel.mbccs.screen.sellanypay.fragments;

import android.content.Context;
import android.databinding.ObservableField;
import android.widget.ArrayAdapter;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.SellAnyPayRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class CreateTransAnyPayPresenter implements CreateTransAnyPayContract.Presenter {

    private Context context;
    private CreateTransAnyPayContract.ViewModel viewModel;

    private ArrayAdapter<String> custTypeAdapter;
    private ArrayAdapter<String> payMethodAdapter;
    private ArrayAdapter<String> payAmountAdapter;

    public ObservableField<String> channelCode;
    public ObservableField<String> isdn;
    public ObservableField<String> walletIsdn;

    private List<String> custTypesList;
    private List<String> payMethodsList;
    private List<String> payAmountList;

    private SellAnyPayRepository repository;

    public CreateTransAnyPayPresenter(Context context, final CreateTransAnyPayContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        channelCode = new ObservableField<>();
        isdn = new ObservableField<>();
        walletIsdn = new ObservableField<>();

        custTypesList = new ArrayList<>();
        payMethodsList = new ArrayList<>();
        payAmountList = new ArrayList<>();

        custTypeAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                custTypesList);
        payMethodAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                payMethodsList);
        payAmountAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                payAmountList);

        initListeners();
        initData();
    }

    private void initData() {
        try {
            repository = SellAnyPayRepository.getInstance();

            for (KeyValue item : repository.getCustTypes()) {
                custTypesList.add(item.getValue());
            }

            for (KeyValue item : repository.getPayMethods()) {
                payMethodsList.add(item.getValue());
            }

            for (KeyValue item : repository.getBankPlusAmounts()) {
                payAmountList.add(item.getValue());
            }

            custTypeAdapter.notifyDataSetChanged();
            payMethodAdapter.notifyDataSetChanged();
            payAmountAdapter.notifyDataSetChanged();

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

    }

    public ArrayAdapter<String> getCustTypeAdapter() {
        return custTypeAdapter;
    }

    public ArrayAdapter<String> getPayMethodAdapter() {
        return payMethodAdapter;
    }

    public ArrayAdapter<String> getPayAmountAdapter() {
        return payAmountAdapter;
    }
}
