package com.viettel.mbccs.screen.changesim.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.data.source.ChangeSimRepository;
import com.viettel.mbccs.screen.common.Validator;
import com.viettel.mbccs.utils.Common;

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
    public ObservableField<String> serviceFee;
    public ObservableField<String> changeSimFee;
    public ObservableField<String> totalFee;
    public ObservableBoolean isPrepaid;

    private ChangeSimRepository repository;

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
        serviceFee = new ObservableField<>();
        changeSimFee = new ObservableField<>();
        totalFee = new ObservableField<>();
        isPrepaid = new ObservableBoolean();

        initListeners();
        initData();
    }

    private void initListeners(){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initData(){
        try{
            repository = ChangeSimRepository.getInstance();

            double changeSimPrice = repository.getChangeSimPrice();
            double servicePrice = repository.getChangeSimServiceFee();

            serviceFee.set(Common.formatDouble(servicePrice) + " " + context.getString(R.string.common_label_currency_suffix));
            changeSimFee.set(Common.formatDouble(changeSimPrice) + " " + context.getString(R.string.common_label_currency_suffix));
            totalFee.set(Common.formatDouble(servicePrice + changeSimPrice)+ " " + context.getString(R.string.common_label_currency_suffix));

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
        try{

            if(newSerial.get() == null || "".equals(newSerial.get())){
                viewModel.showError(context.getString(R.string.change_sim_error_serial_required));
                return;
            }

            if(!Validator.isSerialValid(newSerial.get())){
                viewModel.showError(context.getString(R.string.change_sim_error_serial_not_valid));
                return;
            }

            if(!isPrepaid.get()){
                if(contact1.get() == null || "".equals(contact1.get())){
                    viewModel.showError(context.getString(R.string.change_sim_error_recent_call_required, "1"));
                    return;
                }
                if(Validator.isPhoneNumberValid(contact1.get())){
                    viewModel.showError(context.getString(R.string.change_sim_error_recent_call_not_valid, "1"));
                    return;
                }

                if(contact2.get() == null || "".equals(contact2.get())){
                    viewModel.showError(context.getString(R.string.change_sim_error_recent_call_required, "2"));
                    return;
                }
                if(Validator.isPhoneNumberValid(contact2.get())){
                    viewModel.showError(context.getString(R.string.change_sim_error_recent_call_not_valid, "2"));
                    return;
                }

                if(contact3.get() == null || "".equals(contact3.get())){
                    viewModel.showError(context.getString(R.string.change_sim_error_recent_call_required, "3"));
                    return;
                }
                if(Validator.isPhoneNumberValid(contact3.get())){
                    viewModel.showError(context.getString(R.string.change_sim_error_recent_call_not_valid, "3"));
                    return;
                }

                if(contact4.get() == null || "".equals(contact4.get())){
                    viewModel.showError(context.getString(R.string.change_sim_error_recent_call_required, "4"));
                    return;
                }
                if(Validator.isPhoneNumberValid(contact4.get())){
                    viewModel.showError(context.getString(R.string.change_sim_error_recent_call_not_valid, "4"));
                    return;
                }

                if(contact5.get() == null || "".equals(contact5.get())){
                    viewModel.showError(context.getString(R.string.change_sim_error_recent_call_required, "5"));
                    return;
                }
                if(Validator.isPhoneNumberValid(contact5.get())){
                    viewModel.showError(context.getString(R.string.change_sim_error_recent_call_not_valid, "5"));
                    return;
                }
            }

            //TODO create trans

        }catch (Exception e){

        }
    }
}
