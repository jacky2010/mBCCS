package com.viettel.mbccs.screen.branches.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.places.ui.PlacePicker;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.databinding.FragmentAddBranchBinding;
import com.viettel.mbccs.screen.branches.btspicker.BTSPickerActivity;
import com.viettel.mbccs.screen.branches.dialogs.DialogConfirmAddBranchFragment;
import com.viettel.mbccs.screen.branches.staffpicker.StaffPickerActivity;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.utils.PermissionUtils;
import com.viettel.mbccs.variable.Constants;

import java.io.IOException;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by minhnx on 5/20/17.
 */

public class AddBranchFragment extends BaseDataBindFragment<FragmentAddBranchBinding, AddBranchPresenter>
        implements AddBranchContract.ViewModel {

    private static final int GET_MANAGER = 1001;
    private static final int GET_BTS = GET_MANAGER + 1;
    private static final int REQUEST_CAMERA = 101;
    private static final int SELECT_FILE = 102;
    private static final int PLACE_PICKER_REQUEST = 103;

    public static final int FORM_ADD = 1;
    public static final int FORM_EDIT = 2;
    private static final int IMAGE_DOCUMENT = 1;
    private static final int IMAGE_CONTRACT = 2;

    private AppCompatActivity mActivity;
    private int formType;
    private int imageSelect;

    public static AddBranchFragment newInstance() {
        return new AddBranchFragment();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new AddBranchPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();

        Bundle args = getArguments();
        formType = args.getInt(Constants.BundleConstant.FORM_TYPE);
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_add_branch;
    }

    @Override
    protected void initView() {

    }

    private void initListeners() {
        try{
//            mBinding.spChannelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    if(view == null)
//                        return;
//                    if (!Constants.View.HINT.equals(view.getTag()))
//                        mPresenter.onChannelTypeChanged(i - 1);
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//
//                }
//            });

//            mBinding.spDocumentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    if(view == null)
//                        return;
//                    if (!Constants.View.HINT.equals(view.getTag()))
//                        mPresenter.onDocumentTypeChanged(i - 1);
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//
//                }
//            });

            mBinding.ivLocationPicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                        startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void hideSoftInput() {
        ActivityUtils.hideKeyboard(getBaseActivity());
    }

    @Override
    public void onChooseManager(List<KeyValue> items) {
        Intent intent = new Intent(getActivity(), StaffPickerActivity.class);
        intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                GsonUtils.Object2String(items));
        startActivityForResult(intent, GET_MANAGER);
    }


    @Override
    public void onChooseBTS(List<KeyValue> items) {
        Intent intent = new Intent(getActivity(), BTSPickerActivity.class);
        intent.putExtra(Constants.BundleConstant.ITEM_LIST,
                GsonUtils.Object2String(items));
        startActivityForResult(intent, GET_BTS);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSelectImage(View v) {
        switch (v.getId()) {
            case R.id.image_document:
                imageSelect = IMAGE_DOCUMENT;
                break;
            case R.id.image_contract:
                imageSelect = IMAGE_CONTRACT;
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
                    if (PermissionUtils.checkPermissions(AddBranchFragment.this,
                            PermissionUtils.REQUEST_CODE_CAMERA_PERMISSIONS,
                            Manifest.permission.CAMERA)) {
                        cameraIntent();
                    }
                    dialog.dismiss();
                } else if (items[item].equals(getString(R.string.picker_photo_chooose_library))) {
                    if (PermissionUtils.checkPermissions(AddBranchFragment.this,
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == GET_MANAGER && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetManagerSuccess(item);
            }
            else if (requestCode == GET_BTS && resultCode == RESULT_OK) {
                KeyValue item = (KeyValue) data.getExtras()
                        .getSerializable(Constants.BundleConstant.RESULT);
                mPresenter.onGetBTSSuccess(item);
            }
            else if (resultCode == Activity.RESULT_OK && (requestCode == SELECT_FILE || requestCode == REQUEST_CAMERA)) {
                switch (requestCode) {
                    case SELECT_FILE:
                        onSelectFromGalleryResult(data);
                        break;
                    case REQUEST_CAMERA:
                        onCaptureImageResult(data);
                        break;
                }
            } else if(requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK){
                mPresenter.onPlaceSelected(PlacePicker.getPlace(getContext(), data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
    }

    @Override
    public void goToDialogFragment(Bundle args) {
        getBaseActivity().goToDialogFragment(new DialogConfirmAddBranchFragment(), args);
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
            case IMAGE_DOCUMENT:
                mPresenter.setDocumentImage(bitmap);
                break;
            case IMAGE_CONTRACT:
                mPresenter.setContractImage(bitmap);
                break;
        }
    }
}
