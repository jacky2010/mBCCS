package com.viettel.mbccs.screen.branches.fragments;

import android.content.Context;
import android.databinding.ObservableField;
import android.widget.ArrayAdapter;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.BranchesRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class AddBranchPresenter implements AddBranchContract.Presenter {

    private Context context;
    private AddBranchContract.ViewModel viewModel;

    public ObservableField<String> channelType;
    public ObservableField<String> phoneNo;
    public ObservableField<String> documentType;
    public ObservableField<String> documentNo;
    public ObservableField<String> address;
    public ObservableField<String> location;
    public ObservableField<String> manager;
    public ObservableField<String> bts;

    private ArrayAdapter<String> channelTypeAdapter;
    private ArrayAdapter<String> documentTypeAdapter;

    private List<String> channelTypesList;
    private List<String> documentTypesList;
    private List<KeyValue> managersList;
    private List<KeyValue> btsesList;

    private KeyValue managerObj;
    private KeyValue btsObj;

    private BranchesRepository repository;

    public AddBranchPresenter(Context context, AddBranchContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        channelType = new ObservableField<>();
        phoneNo = new ObservableField<>();
        documentNo = new ObservableField<>();
        documentType = new ObservableField<>();
        address = new ObservableField<>();
        location = new ObservableField<>();
        manager = new ObservableField<>();
        bts = new ObservableField<>();

        channelTypesList = new ArrayList<>();
        documentTypesList = new ArrayList<>();

        channelTypeAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                channelTypesList);
        documentTypeAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                documentTypesList);

        initListeners();
        initData();
    }

    private void initListeners() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {
            repository = BranchesRepository.getInstance();

//            channelTypesList.clear();
//            documentTypesList.clear();
//            managersList.clear();
//            btsesList.clear();

            for (KeyValue item : repository.getChannelTypes()) {
                channelTypesList.add(item.getValue());
            }

            for (KeyValue item : repository.getDocumentTypes()) {
                documentTypesList.add(item.getValue());
            }

            managersList = repository.getStaffs();
            btsesList = repository.getBtses();

            channelTypeAdapter.notifyDataSetChanged();
            documentTypeAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {

    }

    public void addNewDocument() {
        try {

            BranchItem item = new BranchItem();
            item.setChannelType(channelType.get());
            item.setPhoneNo(phoneNo.get());
            item.setDocumentType(documentType.get());
            item.setDocumentNo(documentNo.get());
            item.setImagePath("");
            item.setAddress(address.get());
            item.setLatitude(0);
            item.setLongitude(0);
            item.setStaffCode(managerObj.getKey());
            item.setBtsCode(btsObj.getKey());
            item.setDocumentImagePath("");

            viewModel.onBranchAdded(item);

        } catch (Exception e) {
            e.printStackTrace();

            viewModel.onBranchAddFailed();
        }
    }

    public void chooseManager(){
        try{
            viewModel.onChooseManager(managersList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void chooseBTS(){
        try{
            viewModel.onChooseBTS(btsesList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onGetManagerSuccess(KeyValue item) {
        if (item == null) {
            return;
        }
        managerObj = item;
        manager.set(item.getValue());
    }

    @Override
    public void onGetBTSSuccess(KeyValue item) {
        if (item == null) {
            return;
        }
        btsObj = item;
        bts.set(item.getValue());
    }

    public ArrayAdapter<String> getChannelTypeAdapter() {
        return channelTypeAdapter;
    }

    public ArrayAdapter<String> getDocumentTypeAdapter() {
        return documentTypeAdapter;
    }
}
