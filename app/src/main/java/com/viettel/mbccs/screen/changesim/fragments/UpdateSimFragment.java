package com.viettel.mbccs.screen.changesim.fragments;

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

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.databinding.FragmentUpdateSimBinding;
import com.viettel.mbccs.screen.changesim.dialogs.DialogConfirmUpdateSimFragment;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.utils.PermissionUtils;
import com.viettel.mbccs.variable.Constants;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by minhnx on 5/20/17.
 */

public class UpdateSimFragment extends BaseDataBindFragment<FragmentUpdateSimBinding, UpdateSimPresenter>
        implements UpdateSimContract.ViewModel {

    private static final int REQUEST_CAMERA = 101;
    private static final int SELECT_FILE = 102;

    private static final int IMAGE1 = 1;
    private static final int IMAGE2 = 2;
    private static final int IMAGE3 = 3;

    private int imageSelect;
    private AppCompatActivity mActivity;

    public static UpdateSimFragment newInstance() {
        return new UpdateSimFragment();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new UpdateSimPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
        initWindow();
    }

    private void initWindow() {
        try {
            ChangeSimItem item;

            Bundle args = getArguments();

            if(args != null){
                item = GsonUtils.String2Object(args.getString(Constants.BundleConstant.CHANGE_SIM_ITEM), ChangeSimItem.class);

                if(item != null)
                    mPresenter.onPrepareChangeSim(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_update_sim;
    }

    @Override
    protected void initView() {

    }

    private void initListeners() {
        try {
//            mBinding.txtDocumentId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View view, boolean b) {
//                    if(!b)
//                        hideSoftInput();
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideSoftInput() {
        ActivityUtils.hideKeyboard(getBaseActivity());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
    }

    @Override
    public void onChangeSimSuccessful(ChangeSimItem item) {

    }

    @Override
    public void onChangeSimFailed() {

    }

    @Override
    public void goToDialogFragment(Bundle args) {
        getBaseActivity().goToDialogFragment(new DialogConfirmUpdateSimFragment(), args);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == RESULT_OK && (requestCode == SELECT_FILE || requestCode == REQUEST_CAMERA)) {
                switch (requestCode) {
                    case SELECT_FILE:
                        onSelectFromGalleryResult(data);
                        break;
                    case REQUEST_CAMERA:
                        onCaptureImageResult(data);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSelectImage(View v) {
        switch (v.getId()) {
            case R.id.image_doc1:
                imageSelect = IMAGE1;
                break;
            case R.id.image_doc2:
                imageSelect = IMAGE2;
                break;
            case R.id.image_doc3:
                imageSelect = IMAGE3;
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
                    if (PermissionUtils.checkPermissions(UpdateSimFragment.this,
                            PermissionUtils.REQUEST_CODE_CAMERA_PERMISSIONS,
                            Manifest.permission.CAMERA)) {
                        cameraIntent();
                    }
                    dialog.dismiss();
                } else if (items[item].equals(getString(R.string.picker_photo_chooose_library))) {
                    if (PermissionUtils.checkPermissions(UpdateSimFragment.this,
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
        setImageView(thumbnail);
    }

    private void setImageView(Bitmap bitmap) {
        switch (imageSelect) {
            case IMAGE1:
                mPresenter.setImage1(bitmap);
                break;
            case IMAGE2:
                mPresenter.setImage2(bitmap);
                break;
            case IMAGE3:
                mPresenter.setImage3(bitmap);
                break;
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
}
