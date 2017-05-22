package com.viettel.mbccs.screen.changesim.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.viettel.mbccs.data.model.ChangeSimItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class UpdateSimPresenter implements UpdateSimContract.Presenter {

    private Context context;
    private UpdateSimContract.ViewModel viewModel;

    public ChangeSimItem changeSimItem;
    public ObservableField<String> documentId;
    public ObservableField<String> newSerial;
    public ObservableField<String> contact1;
    public ObservableField<String> contact2;
    public ObservableField<String> contact3;
    public ObservableField<String> contact4;
    public ObservableField<String> contact5;
    public ObservableBoolean isPrepaid;

    public UpdateSimPresenter(Context context, final UpdateSimContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        changeSimItem = new ChangeSimItem();
        documentId = new ObservableField<>();
        newSerial = new ObservableField<>();
        contact1 = new ObservableField<>();
        contact2 = new ObservableField<>();
        contact3 = new ObservableField<>();
        contact4 = new ObservableField<>();
        contact5 = new ObservableField<>();
        isPrepaid = new ObservableBoolean();

        initListeners();
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
    public void changeSim() {

    }
}
