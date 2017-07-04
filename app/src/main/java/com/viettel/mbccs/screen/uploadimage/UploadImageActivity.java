package com.viettel.mbccs.screen.uploadimage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.UploadImage;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.UploadImageResponse;
import com.viettel.mbccs.databinding.ActivityUploadImageBinding;
import com.viettel.mbccs.screen.uploadimage.adapter.AdapterUploadImage;
import com.viettel.mbccs.service.service.UploadImageService;
import com.viettel.mbccs.utils.DialogUtils;
import java.util.List;

public class UploadImageActivity
        extends BaseDataBindActivity<ActivityUploadImageBinding, UploadImagePresenter>
        implements UploadImageContract.View {

    private AdapterUploadImage recyclerAdapter;
    private List<UploadImage> uploadImageList;
    private int process;
    private int total;
    private Intent intentService;
    private boolean isStartService;
    private BroadcastReceiver uploadImageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(UploadImageService.ACTION_UPLOAD_SUCCESS) && total != 0) {
                process++;
                mPresenter.setCurrent(process);
                mPresenter.setProgressValue((process / total) * 100);
                UploadImageResponse response =
                        intent.getParcelableExtra(UploadImageService.DATA_UPLOAD_SUCCESS);
                for (int i = 0; i < uploadImageList.size(); i++) {
                    if (uploadImageList.get(i).getFileName().equals(response.getFileName())) {
                        uploadImageList.get(i).setStatus(UploadImage.StatusUpload.SUCCESS);
                        recyclerAdapter.notifyItemInserted(i);
                        break;
                    }
                }
            }

            if (intent.getAction().equals(UploadImageService.ACTION_UPLOAD_FAIL)) {
                //                uploadError();
            }

            if (intent.getAction().equals(UploadImageService.ACTION_UPLOAD_COMPLETED)) {
                uploadCompleted();
            }
        }
    };

    @Override
    protected void onStop() {
        if (isStartService) {
            unregisterReceiver(uploadImageReceiver);
            stopService(intentService);
        }
        mPresenter.unSubscribe();
        super.onStop();
    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_upload_image;
    }

    @Override
    protected void initData() {
        mPresenter = new UploadImagePresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mPresenter.subscribe();
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
    public void setData(List<UploadImage> list) {
        this.uploadImageList = list;
        recyclerAdapter = new AdapterUploadImage(uploadImageList);
        total = uploadImageList.size();
        mPresenter.setRecyclerAdapter(recyclerAdapter);
    }

    @Override
    public void uploadCompleted() {
        Log.i("UploadImageActivity", " -> uploadCompleted: ----------------: toast");
        Toast.makeText(this, "Upload success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadError(BaseException error) {
        DialogUtils.showDialogError(this, error);
    }

    @Override
    public void onCancel() {
        DialogUtils.showDialog(this, "Cancel upload image", "Cancel upload image", "OK", yesClick,
                "Cancel", cancelClick);
    }

    @Override
    public void onStartUpload() {
        isStartService = true;
        intentService = new Intent(this, UploadImageService.class);
        startService(intentService);

        IntentFilter in = new IntentFilter();
        in.addAction(UploadImageService.ACTION_UPLOAD_SUCCESS);
        in.addAction(UploadImageService.ACTION_UPLOAD_FAIL);
        LocalBroadcastManager.getInstance(this).registerReceiver(uploadImageReceiver, in);
    }

    private DialogInterface.OnClickListener cancelClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };

    private DialogInterface.OnClickListener yesClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            mPresenter.onCancelUpload();
        }
    };
}
