package com.viettel.mbccs.screen.changesim.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.ChangeSimInfo;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.Subscriber;
import com.viettel.mbccs.data.source.ChangeSimRepository;
import com.viettel.mbccs.data.source.remote.request.CheckCalledIsdnsRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.utils.ValidateUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

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
    public ObservableField<Bitmap> image1;
    public ObservableField<Bitmap> image2;
    public ObservableField<Bitmap> image3;
    public ObservableBoolean isPrepaid;

    private Bitmap image1Obj;
    private Bitmap image2Obj;
    private Bitmap image3Obj;

    private ChangeSimRepository changeSimRepository;
    private CompositeSubscription mSubscriptions;
    private Subscriber sub;
    private Customer cus;
    private double changeSimPrice;
    private double servicePrice;

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
        image1 = new ObservableField<>(
                BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_select_image));
        image2 = new ObservableField<>(
                BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_select_image));
        image3 = new ObservableField<>(
                BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_select_image));
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
            changeSimRepository = ChangeSimRepository.getInstance();
            mSubscriptions = new CompositeSubscription();

            changeSimPrice = changeSimRepository.getChangeSimPrice();
            servicePrice = changeSimRepository.getChangeSimServiceFee();

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
    public void onPrepareChangeSim(ChangeSimItem item) {
        try {

            if (item == null)
                return;

            sub = item.getSubscriber();
            cus = item.getCustomer();

            if (cus == null || sub == null) {
                viewModel.showError(context.getString(R.string.common_msg_error_no_data));
                return;
            }

            customerName.set(cus.getName());
            documentId.set(cus.getIdNo());
            customerAddress.set(cus.getAddress());
            oldSimSerial.set(sub.getSerial());
            isPrepaid.set("1".equals(sub.getSubType()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeSim() {
        try {

            if (cus == null || sub == null) {
                viewModel.showError(context.getString(R.string.common_msg_error_no_data));
                return;
            }

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

//            if (isPrepaid.get()) {
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
//            }

            if (!isValid)
                return;

//            if (isPrepaid.get()) {

            final List<String> recentContacts = new ArrayList<>();
            recentContacts.add(contact1.get().trim());
            recentContacts.add(contact2.get().trim());
            recentContacts.add(contact3.get().trim());
            recentContacts.add(contact4.get().trim());
            recentContacts.add(contact5.get().trim());

            //validate recent contacts
            viewModel.showLoading();

            DataRequest<CheckCalledIsdnsRequest> baseRequest = new DataRequest<>();
            baseRequest.setWsCode(WsCode.CheckCalledIsdn);
            CheckCalledIsdnsRequest request = new CheckCalledIsdnsRequest();
            request.setListIsdn(recentContacts);
            request.setIsdn(sub.getIsdn());
            request.setSubType(sub.getSubType());
            baseRequest.setWsRequest(request);

            Subscription subscription =
                    changeSimRepository.checkCalledIsdn(baseRequest)
                            .subscribe(new MBCCSSubscribe<DataResponse>() {
                                @Override
                                public void onSuccess(DataResponse object) {
                                    try {
//                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {

                                        goToConfirmDialog(recentContacts);

//                                        } else {
//                                        DialogUtils.showDialog(context, null, context.getString(R.string.change_sim_error_recent_calls_not_valid),
//                                                null);
//                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
                                    if (error.getMessage().contains("Called isdns are not match")) {
                                        DialogUtils.showDialog(context, null, context.getString(R.string.change_sim_error_recent_calls_not_valid),
                                                null);
                                    } else
                                        DialogUtils.showDialog(context, null, context.getString(R.string.common_msg_error_general),
                                                null);
                                }

                                @Override
                                public void onRequestFinish() {
                                    super.onRequestFinish();
                                    viewModel.hideLoading();
                                }
                            });

            mSubscriptions.add(subscription);
            //validate recent contacts
//            } else {
//                goToConfirmDialog(null);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToConfirmDialog(List<String> recentContacts) {
        ChangeSimInfo changeSimInfo = new ChangeSimInfo();
        changeSimInfo.setOldSerial(oldSimSerial.get());
        changeSimInfo.setNewSerial(newSimSerial.get());
        changeSimInfo.setRecentContacts(recentContacts);

        ChangeSimItem item = new ChangeSimItem();
        item.setCustomer(cus);
        item.setSubscriber(sub);
        item.setChangeSimInfo(changeSimInfo);

        Bundle args = new Bundle();
        args.putString(Constants.BundleConstant.CUSTOMER_ITEM, GsonUtils.Object2String(item));
        args.putDouble(Constants.BundleConstant.SERVICE_FEE, servicePrice);
        args.putDouble(Constants.BundleConstant.SIM_FEE, changeSimPrice);
        args.putDouble(Constants.BundleConstant.TOTAL, (servicePrice + changeSimPrice));

        viewModel.goToDialogFragment(args);
    }

    public void onSelectImage(View v) {
        viewModel.onSelectImage(v);
    }

    @Override
    public void setImage1(Bitmap bitmap) {
        image1Obj = bitmap;
        image1.set(bitmap);
    }

    @Override
    public void setImage2(Bitmap bitmap) {
        image2Obj = bitmap;
        image2.set(bitmap);
    }

    @Override
    public void setImage3(Bitmap bitmap) {
        image3Obj = bitmap;
        image3.set(bitmap);
    }
}
