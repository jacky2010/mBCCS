package com.viettel.mbccs.screen.information.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseFragment;
import com.viettel.mbccs.data.model.ApDomain;
import com.viettel.mbccs.data.model.District;
import com.viettel.mbccs.data.model.Precinct;
import com.viettel.mbccs.data.model.Province;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetRegiterSubInfoResponse;
import com.viettel.mbccs.databinding.FragmentCreateUpdateInformationBinding;
import com.viettel.mbccs.screen.common.success.DialogFullScreen;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.PermissionUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/29/17.
 */

public class CreateUpdateInformationFragment extends BaseFragment
        implements CreateUpdateInformationFragmentContract.View {
    public static final String STRING_NAME = "CreateUpdateInformationFragment";

    @IntDef({ Type.CREATE_INFORMATION, Type.CREATE_INFORMATION_CLONE })
    public @interface Type {
        int CREATE_INFORMATION = 1;
        int CREATE_INFORMATION_CLONE = 2;
    }

    private static final String ARG_TYPE_FRAGMENT = "TYPE_FRAGMENT";
    private static final String ARG_DATA = "DATA";
    private static final int REQUEST_CAMERA = 101;
    private static final int SELECT_FILE = 102;

    private static final int IMAGE_FRONT = 1;
    private static final int IMAGE_BACKSIDE = 2;
    private static final int IMAGE_PORTRAIT = 3;

    private FragmentCreateUpdateInformationBinding binding;
    private CreateUpdateInformationFragmentPresenter presenter;
    @Type
    private int typeFragment;
    private int imageSelect;
    private GetRegiterSubInfoResponse data;

    private List<String> dataPassportType;
    private List<ApDomain> apDomainList;
    private ArrayAdapter<String> adapterPassportType;

    public static CreateUpdateInformationFragment newInstance(@Type int typeFragment,
            @Nullable GetRegiterSubInfoResponse data) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TYPE_FRAGMENT, typeFragment);
        bundle.putParcelable(ARG_DATA, data);
        CreateUpdateInformationFragment fragment = new CreateUpdateInformationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeFragment = getArguments().getInt(ARG_TYPE_FRAGMENT);
        data = getArguments().getParcelable(ARG_DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateUpdateInformationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CreateUpdateInformationFragmentPresenter(getActivity(), this);

        dataPassportType = new ArrayList<>();
        adapterPassportType =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                        dataPassportType);
        adapterPassportType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        presenter.setAdapterPassportType(adapterPassportType);
        binding.spinnerSelectTypePassport.setOnItemSelectedListener(presenter);

        presenter.setTypeFragment(typeFragment, data);
        presenter.getDataSpinnerPassport();
        binding.setPresenter(presenter);
    }

    @Override
    public void onStop() {
        presenter.unSubscribe();
        super.onStop();
    }

    @Override
    public void setPresenter(CreateUpdateInformationFragmentContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void onCancel() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onSelectImage(View v) {
        switch (v.getId()) {
            case R.id.image_front:
                imageSelect = IMAGE_FRONT;
                break;
            case R.id.image_backside:
                imageSelect = IMAGE_BACKSIDE;
                break;
            case R.id.image_portrait:
                imageSelect = IMAGE_PORTRAIT;
                break;
        }

        final CharSequence[] items = {
                getString(R.string.picker_photo_take_photo),
                getString(R.string.picker_photo_chooose_library),
                getString(R.string.picker_photo_cancel)
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.picker_photo_add_photo));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getString(R.string.picker_photo_take_photo))) {
                    if (PermissionUtils.checkPermissions(CreateUpdateInformationFragment.this,
                            PermissionUtils.REQUEST_CODE_CAMERA_PERMISSIONS,
                            Manifest.permission.CAMERA)) {
                        cameraIntent();
                    }
                    dialog.dismiss();
                } else if (items[item].equals(getString(R.string.picker_photo_chooose_library))) {
                    if (PermissionUtils.checkPermissions(CreateUpdateInformationFragment.this,
                            PermissionUtils.REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSIONS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        galleryIntent();
                    }
                    dialog.dismiss();
                } else if (items[item].equals(getString(R.string.picker_photo_cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public Province getProvince() {
        return binding.customSelectAddressCreateInformation.getProvince();
    }

    @Override
    public District getDistrict() {
        return binding.customSelectAddressCreateInformation.getDistrict();
    }

    @Override
    public Precinct getPrecinct() {
        return binding.customSelectAddressCreateInformation.getPrecinct();
    }

    @Override
    public String getAddress() {
        return binding.customSelectAddressCreateInformation.getAddress();
    }

    @Override
    public String getBirthDate() {
        return binding.dateBirthday.getStringDate();
    }

    @Override
    public void setBirthDate(String birthDate) {
        binding.dateBirthday.setDateString(birthDate);
    }
    
    @Override
    public void registerCustomerInfoError(BaseException error) {
        DialogUtils.showDialogError(getActivity(), error);
    }

    @Override
    public void registerCustomerInfoSuccess(String result) {
        Dialog dia = new DialogFullScreen.Builder(getActivity()).setCenterContent(true)
                .setTitle(
                        getString(R.string.fragment_create_update_information_create_dk_thanh_cong))
                .setContent(result)
                .setPositiveButton(getString(R.string.OK))
                .setListener(new DialogFullScreen.SuccessDialogListener() {
                    @Override
                    public void onPositiveButtonClick(Dialog dialog) {
                        dialog.dismiss();
                        getActivity().getSupportFragmentManager().popBackStack();
                    }

                    @Override
                    public void onNegativeButtonClick(Dialog dialog) {

                    }

                    @Override
                    public void onDialogClose() {

                    }
                })
                .build();
        dia.setCancelable(false);
        dia.setCanceledOnTouchOutside(false);
        dia.show();
    }

    @Override
    public void getDataSpinnerPassportSuccess(List<ApDomain> type) {
        apDomainList = type;
        for (ApDomain a : apDomainList) {
            dataPassportType.add(a.getName());
        }
        adapterPassportType.notifyDataSetChanged();
    }

    @Override
    public void getDataSpinnerPassportError(BaseException error) {
        // TODO: 6/1/17 handler error
        DialogUtils.showDialogError(getActivity(), error);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        switch (requestCode) {
            case PermissionUtils.REQUEST_CODE_CAMERA_PERMISSIONS:
                PermissionUtils.onRequestResult(getActivity(), permissions, grantResults,
                        new PermissionUtils.OnPermissionGranted() {
                            @Override
                            public void onGranted() {
                                cameraIntent();
                            }
                        }, null, true);
                break;
            case PermissionUtils.REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSIONS:
                PermissionUtils.onRequestResult(getActivity(), permissions, grantResults,
                        new PermissionUtils.OnPermissionGranted() {
                            @Override
                            public void onGranted() {
                                galleryIntent();
                            }
                        }, null, true);
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SELECT_FILE:
                    onSelectFromGalleryResult(data);
                    break;
                case REQUEST_CAMERA:
                    onCaptureImageResult(data);
                    break;
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),
                        data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setImageView(bm);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        //        data.getData();
        //        try {
        //            Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),
        //                    data.getData());
        //            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //            bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        //            File destination = new File(Environment.getExternalStorageDirectory(),
        //                    System.currentTimeMillis() + ".jpg");
        //            FileOutputStream fo;
        //            destination.createNewFile();
        //            fo = new FileOutputStream(destination);
        //            fo.write(bytes.toByteArray());
        //            fo.close();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        setImageView(thumbnail);
    }

    private void setImageView(Bitmap bitmap) {
        switch (imageSelect) {
            case IMAGE_FRONT:
                presenter.setImageFront(bitmap);
                break;
            case IMAGE_BACKSIDE:
                presenter.setImageBackside(bitmap);
                break;
            case IMAGE_PORTRAIT:
                presenter.setImagePortrait(bitmap);
                break;
        }
    }
}
