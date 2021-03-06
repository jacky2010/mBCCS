package com.viettel.mbccs.screen.branches.fragments;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.BranchesRepository;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.variable.Constants;

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
    public ObservableField<String> phoneNoError;
    public ObservableField<String> documentType;
    public ObservableField<String> documentNo;
    public ObservableField<String> documentNoError;
    public ObservableField<String> address;
    public ObservableField<String> addressError;
    public ObservableField<String> location;
    public ObservableField<String> locationError;
    public ObservableField<String> manager;
    public ObservableField<String> bts;
    public ObservableField<Bitmap> imageDocument;
    public ObservableField<Bitmap> imageContract;

    private SpinnerAdapter<String> channelTypeAdapter;
    private SpinnerAdapter<String> documentTypeAdapter;

    private List<String> channelTypesList;
    private List<String> documentTypesList;
    private List<KeyValue> managersList;
    private List<KeyValue> btsesList;
    private List<KeyValue> channelTypes;
    private List<KeyValue> documentTypes;

    private KeyValue managerObj;
    private KeyValue btsObj;
    private KeyValue channelTypeObj;
    private KeyValue documentTypeObj;
    private Bitmap documentImage;
    private Bitmap contractImage;
    private LatLng locationObj;

    private BranchesRepository repository;

    public AddBranchPresenter(Context context, AddBranchContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        channelType = new ObservableField<>();
        phoneNo = new ObservableField<>();
        phoneNoError = new ObservableField<>();
        documentNo = new ObservableField<>();
        documentNoError = new ObservableField<>();
        documentType = new ObservableField<>();
        address = new ObservableField<>();
        addressError = new ObservableField<>();
        location = new ObservableField<>();
        locationError = new ObservableField<>();
        manager = new ObservableField<>();
        bts = new ObservableField<>();
        imageContract = new ObservableField<>(
                BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_select_image));
        imageDocument = new ObservableField<>(
                BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_select_image));

        channelTypesList = new ArrayList<>();
        documentTypesList = new ArrayList<>();

        channelTypeAdapter = new SpinnerAdapter<>(context, channelTypesList);
        channelTypeAdapter.setTextHint(
                context.getString(R.string.branches_add_hint_channel_type));
        channelTypeAdapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onChannelTypeChanged(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        documentTypeAdapter = new SpinnerAdapter<>(context, documentTypesList);
        documentTypeAdapter.setTextHint(
                context.getString(R.string.branches_add_hint_document_type));
        documentTypeAdapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onDocumentTypeChanged(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

            channelTypes = repository.getChannelTypes();
            documentTypes = repository.getDocumentTypes();

            //            channelTypes.add(0,
            //                    new KeyValue(null, context.getString(R.string
            // .branches_add_hint_channel_type)));
            for (KeyValue item : channelTypes) {
                channelTypesList.add(item.getValue());
            }

            //            documentTypes.add(0, new KeyValue(null,
            //                    context.getString(R.string.branches_add_hint_document_type)));
            for (KeyValue item : documentTypes) {
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

            boolean isValid = true;

            phoneNoError.set(null);
            documentNoError.set(null);
            addressError.set(null);
            locationError.set(null);

            if (TextUtils.isEmpty(channelType.get())) {
                viewModel.showError(context.getString(R.string.common_msg_error_required_field,
                        context.getString(R.string.branches_add_label_channel_type)));
                isValid = false;
            }

            if (TextUtils.isEmpty(phoneNo.get())) {
                phoneNoError.set(context.getString(R.string.input_empty));
                isValid = false;
            }

            if (TextUtils.isEmpty(documentType.get())) {
                viewModel.showError(context.getString(R.string.common_msg_error_required_field,
                        context.getString(R.string.branches_add_label_document_type)));
                isValid = false;
            }

            if (TextUtils.isEmpty(documentNo.get())) {
                documentNoError.set(context.getString(R.string.input_empty));
                isValid = false;
            }

            if (TextUtils.isEmpty(address.get())) {
                addressError.set(context.getString(R.string.input_empty));
                isValid = false;
            }

            if (TextUtils.isEmpty(location.get())) {
                locationError.set(context.getString(R.string.input_empty));
                isValid = false;
            }

            if (TextUtils.isEmpty(manager.get())) {
                viewModel.showError(context.getString(R.string.common_msg_error_required_field,
                        context.getString(R.string.branches_add_label_manager)));
                isValid = false;
            }

            if (TextUtils.isEmpty(bts.get())) {
                viewModel.showError(context.getString(R.string.common_msg_error_required_field,
                        context.getString(R.string.branches_add_label_bts)));
                isValid = false;
            }

            if (!isValid) return;

            BranchItem item = new BranchItem();
            item.setChannelType(channelType.get());
            item.setPhoneNo(phoneNo.get());
            item.setDocumentType(documentType.get());
            item.setDocumentNo(documentNo.get());
            item.setImagePath("");
            item.setAddress(address.get());

            if (locationObj != null) {
                item.setLatitude(locationObj.latitude);
                item.setLongitude(locationObj.longitude);
            }

            item.setStaffCode(managerObj.getKey());
            item.setBtsCode(btsObj.getKey());
            item.setDocumentImagePath("");
            item.setBtsName(btsObj.getValue());
            item.setStaffName(managerObj.getValue());
            item.setChannelTypeName(channelTypeObj.getValue());
            item.setDocumentTypeName(documentTypeObj.getValue());

            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.ITEM_LIST, GsonUtils.Object2String(item));

            viewModel.goToDialogFragment(args);
        } catch (Exception e)

        {
            e.printStackTrace();
        }
    }

    public void onSelectImage(View v) {
        viewModel.onSelectImage(v);
    }

    public void chooseManager() {
        try {
            viewModel.onChooseManager(managersList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseBTS() {
        try {
            viewModel.onChooseBTS(btsesList);
        } catch (Exception e) {
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

    @Override
    public void onChannelTypeChanged(int index) {
        try {
            channelType.set(channelTypes.get(index).getKey());
            channelTypeObj = channelTypes.get(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDocumentTypeChanged(int index) {
        try {
            documentType.set(documentTypes.get(index).getKey());
            documentTypeObj = documentTypes.get(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDocumentImage(Bitmap bitmap) {
        documentImage = bitmap;
        imageDocument.set(bitmap);
    }

    @Override
    public void setContractImage(Bitmap bitmap) {
        contractImage = bitmap;
        imageContract.set(bitmap);
    }

    @Override
    public void onPlaceSelected(Place place) {
        try {
            if (place != null) {
                location.set(place.getLatLng().latitude + ", " + place.getLatLng().longitude);
                locationObj = place.getLatLng();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayAdapter<String> getChannelTypeAdapter() {
        return channelTypeAdapter;
    }

    public ArrayAdapter<String> getDocumentTypeAdapter() {
        return documentTypeAdapter;
    }
}
