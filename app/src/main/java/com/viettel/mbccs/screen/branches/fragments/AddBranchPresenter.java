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

    public ObservableField<ArrayAdapter<String>> channelTypeAdapter;
    public ObservableField<ArrayAdapter<String>> documentTypeAdapter;
    public ObservableField<ArrayAdapter<String>> managerAdapter;
    public ObservableField<ArrayAdapter<String>> btsAdapter;

    private List<String> channelTypesList;
    private List<String> documentTypesList;
    private List<String> managersList;
    private List<String> btsesList;

    private BranchesRepository repository;

    public AddBranchPresenter(Context context, AddBranchContract.ViewModel viewModel){
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

        channelTypeAdapter = new ObservableField<>();
        documentTypeAdapter = new ObservableField<>();
        managerAdapter = new ObservableField<>();
        btsAdapter = new ObservableField<>();

        channelTypesList = new ArrayList<>();
        documentTypesList = new ArrayList<>();
        managersList = new ArrayList<>();
        btsesList = new ArrayList<>();

        initListeners();
    }

    private void initListeners(){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initData(){
        try{
            repository = BranchesRepository.getInstance();

            channelTypesList.clear();
            documentTypesList.clear();
            managersList.clear();
            btsesList.clear();

            for(KeyValue item : repository.getChannelTypes()){
                channelTypesList.add(item.getValue());
            }

            for(KeyValue item : repository.getDocumentTypes()){
                documentTypesList.add(item.getValue());
            }

            for(KeyValue item : repository.getStaffs()){
                managersList.add(item.getValue());
            }

            for(KeyValue item : repository.getBtses()){
                btsesList.add(item.getValue());
            }

            channelTypeAdapter.set(new ArrayAdapter<>(context, R.layout.item_spinner,
                    channelTypesList));
            documentTypeAdapter.set(new ArrayAdapter<>(context, R.layout.item_spinner,
                    documentTypesList));
            managerAdapter.set(new ArrayAdapter<>(context, R.layout.item_spinner,
                    managersList));
            btsAdapter.set(new ArrayAdapter<>(context, R.layout.item_spinner,
                    btsesList));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe() {
        initData();
    }

    @Override
    public void unSubscribe() {

    }

    public void addNewDocument(){
        try{

            BranchItem item = new BranchItem();
            item.setChannelType(channelType.get());
            item.setPhoneNo(phoneNo.get());
            item.setDocumentType(documentType.get());
            item.setDocumentNo(documentNo.get());
            item.setImagePath("");
            item.setAddress(address.get());
            item.setLatitude(0);
            item.setLongitude(0);
            item.setStaffCode(manager.get());
            item.setBtsCode(bts.get());
            item.setDocumentImagePath("");

            viewModel.onBranchAdded(item);

        }catch (Exception e){
            e.printStackTrace();

            viewModel.onBranchAddFailed();
        }
    }
}
