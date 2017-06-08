package com.viettel.mbccs.screen.changesim.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.source.ChangeSimRepository;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.ValidateUtils;

/**
 * Created by minhnx on 5/19/17.
 */

public class UpdateSimPresenter implements UpdateSimContract.Presenter {

    private Context context;
    private UpdateSimContract.ViewModel viewModel;

    public ObservableField<String> customerName;
    public ObservableField<String> customerNameError;
    public ObservableField<String> customerAddress;
    public ObservableField<String> customerAddressError;
    public ObservableField<String> oldSimSerial;
    public ObservableField<String> oldSimSerialError;
    public ObservableField<String> documentId;
    public ObservableField<String> documentIdError;
    public ObservableField<String> newSimSerial;
    public ObservableField<String> newSimSerialError;
    public ObservableField<String> contact1;
    public ObservableField<String> contact1Error;
    public ObservableField<String> contact2;
    public ObservableField<String> contact2Error;
    public ObservableField<String> contact3;
    public ObservableField<String> contact3Error;
    public ObservableField<String> contact4;
    public ObservableField<String> contact4Error;
    public ObservableField<String> contact5;
    public ObservableField<String> contact5Error;
    public ObservableField<String> serviceFee;
    public ObservableField<String> changeSimFee;
    public ObservableField<String> totalFee;
    public ObservableBoolean isPrepaid;

    private ChangeSimRepository repository;

    public UpdateSimPresenter(Context context, final UpdateSimContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        customerName = new ObservableField<>();
        customerNameError = new ObservableField<>();
        customerAddress = new ObservableField<>();
        customerAddressError = new ObservableField<>();
        documentId = new ObservableField<>();
        documentIdError = new ObservableField<>();
        oldSimSerial = new ObservableField<>();
        oldSimSerialError = new ObservableField<>();
        newSimSerial = new ObservableField<>();
        newSimSerialError = new ObservableField<>();
        contact1 = new ObservableField<>();
        contact1Error = new ObservableField<>();
        contact2 = new ObservableField<>();
        contact2Error = new ObservableField<>();
        contact3 = new ObservableField<>();
        contact3Error = new ObservableField<>();
        contact4 = new ObservableField<>();
        contact4Error = new ObservableField<>();
        contact5 = new ObservableField<>();
        contact5Error = new ObservableField<>();
        serviceFee = new ObservableField<>();
        changeSimFee = new ObservableField<>();
        totalFee = new ObservableField<>();
        isPrepaid = new ObservableBoolean();

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
            repository = ChangeSimRepository.getInstance();

            double changeSimPrice = repository.getChangeSimPrice();
            double servicePrice = repository.getChangeSimServiceFee();

            serviceFee.set(Common.formatDouble(servicePrice) + " " + context.getString(R.string.common_label_currency_suffix));
            changeSimFee.set(Common.formatDouble(changeSimPrice) + " " + context.getString(R.string.common_label_currency_suffix));
            totalFee.set(Common.formatDouble(servicePrice + changeSimPrice) + " " + context.getString(R.string.common_label_currency_suffix));

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

    @Override
    public void changeSim() {
        try {

            customerNameError.set(null);
            documentIdError.set(null);
            customerAddressError.set(null);
            oldSimSerialError.set(null);
            newSimSerialError.set(null);
            contact1Error.set(null);
            contact2Error.set(null);
            contact3Error.set(null);
            contact4Error.set(null);
            contact5Error.set(null);

            boolean isValid = true;

            if (TextUtils.isEmpty(customerName.get())) {
                customerNameError.set(context.getString(R.string.input_empty));
                isValid = false;
            }

            if (TextUtils.isEmpty(documentId.get())) {
                documentIdError.set(context.getString(R.string.input_empty));
                isValid = false;
            } else if (!TextUtils.isEmpty(documentId.get()) && !ValidateUtils.isDocumentIdValid(documentId.get())) {
                documentIdError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.change_sim_label_document_id)));
                isValid = false;
            }

            if (TextUtils.isEmpty(customerAddress.get())) {
                customerAddressError.set(context.getString(R.string.input_empty));
                isValid = false;
            }

            if (TextUtils.isEmpty(oldSimSerial.get())) {
                oldSimSerialError.set(context.getString(R.string.input_empty));
                isValid = false;
            } else if (!TextUtils.isEmpty(oldSimSerial.get()) && !ValidateUtils.isSimSerialValid(oldSimSerial.get())) {
                oldSimSerialError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.change_sim_label_old_serial)));
                isValid = false;
            }

            if (TextUtils.isEmpty(newSimSerial.get())) {
                newSimSerialError.set(context.getString(R.string.input_empty));
                isValid = false;
            } else if (!TextUtils.isEmpty(newSimSerial.get()) && !ValidateUtils.isSimSerialValid(newSimSerial.get())) {
                newSimSerialError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.change_sim_label_new_serial)));
                isValid = false;
            }

            if (!isPrepaid.get()) {
                if (TextUtils.isEmpty(contact1.get())) {
                    contact1Error.set(context.getString(R.string.input_empty));
                    isValid = false;
                } else if (!TextUtils.isEmpty(contact1.get()) && !ValidateUtils.isPhoneNumberValid(contact1.get())) {
                    contact1Error.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.change_sim_label_contact_1)));
                    isValid = false;
                }

                if (TextUtils.isEmpty(contact2.get())) {
                    contact2Error.set(context.getString(R.string.input_empty));
                    isValid = false;
                } else if (!TextUtils.isEmpty(contact2.get()) && !ValidateUtils.isPhoneNumberValid(contact2.get())) {
                    contact2Error.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.change_sim_label_contact_2)));
                    isValid = false;
                }

                if (TextUtils.isEmpty(contact3.get())) {
                    contact3Error.set(context.getString(R.string.input_empty));
                    isValid = false;
                } else if (!TextUtils.isEmpty(contact3.get()) && !ValidateUtils.isPhoneNumberValid(contact3.get())) {
                    contact3Error.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.change_sim_label_contact_3)));
                    isValid = false;
                }

                if (TextUtils.isEmpty(contact4.get())) {
                    contact4Error.set(context.getString(R.string.input_empty));
                    isValid = false;
                } else if (!TextUtils.isEmpty(contact4.get()) && !ValidateUtils.isPhoneNumberValid(contact4.get())) {
                    contact4Error.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.change_sim_label_contact_4)));
                    isValid = false;
                }

                if (TextUtils.isEmpty(contact5.get())) {
                    contact5Error.set(context.getString(R.string.input_empty));
                    isValid = false;
                } else if (!TextUtils.isEmpty(contact5.get()) && !ValidateUtils.isPhoneNumberValid(contact5.get())) {
                    contact5Error.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.change_sim_label_contact_5)));
                    isValid = false;
                }
            }

            if(!isValid)
                return;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
