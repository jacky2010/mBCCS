package com.viettel.mbccs.screen.splash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.view.WindowManager;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.Image;
import com.viettel.mbccs.data.model.database.ImageDataBase;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListIdImageRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetListIdImageResponse;
import com.viettel.mbccs.databinding.ActivityDownloadDataBinding;
import com.viettel.mbccs.screen.main.MainActivity;
import com.viettel.mbccs.service.service.CreateDataBaseService;
import com.viettel.mbccs.service.service.DownloadImageService;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.viettel.mbccs.service.service.CreateDataBaseService.ACTION_CREATE_AREA_COMPLETED;
import static com.viettel.mbccs.service.service.CreateDataBaseService.DATA_CREATE_AREA_COMPLETED;

/**
 * Created by HuyQuyet on 6/18/17.
 */

public class DownloadDataActivity extends BaseActivity {
    private ActivityDownloadDataBinding binding;
    private UserRepository userRepository;
    private CompositeSubscription subscriptions;
    private Intent intentCreateDataBaseAreaService;
    private Intent intentDownloadImageService;
    private List<String> listIdImage;

    public ObservableInt progressValue;
    public ObservableField<String> textProcess;

    private BroadcastReceiver broadcastReceiverCreateArea = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_CREATE_AREA_COMPLETED)) {
                progressValue.set(intent.getIntExtra(DATA_CREATE_AREA_COMPLETED, 0));
                textProcess.set(
                        getString(R.string.create_data_area_text, progressValue.get()) + "%");
                getData();
            } else if (intent.getAction()
                    .equals(CreateDataBaseService.ACTION_CREATE_AREA_SUCCESS)) {
                progressValue.set(
                        intent.getIntExtra(CreateDataBaseService.DATA_CREATE_AREA_SUCCESS, 0));
                textProcess.set(
                        getString(R.string.create_data_area_text, progressValue.get()) + "%");
            } else if (intent.getAction().equals(CreateDataBaseService.ACTION_CREATE_AREA_FAIL)) {
                // TODO: 6/19/17 handler error create data area
            }
        }
    };

    private BroadcastReceiver broadcastReceiverDownloadImage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadImageService.ACTION_DOWNLOAD_START)) {
                progressValue.set(0);
                textProcess.set(getString(R.string.download_image_text, progressValue.get()) + "%");
            }
            if (intent.getAction().equals(DownloadImageService.ACTION_DOWNLOAD_COMPLETE)) {
                progressValue.set(100);
                textProcess.set(getString(R.string.download_image_text, progressValue.get()) + "%");
                gotoMain();
            } else if (intent.getAction().equals(DownloadImageService.ACTION_DOWNLOAD_SUCCESS)) {
                progressValue.set(
                        intent.getIntExtra(DownloadImageService.DATA_DOWNLOAD_SUCCESS, 0));
                textProcess.set(getString(R.string.download_image_text, progressValue.get()) + "%");
            } else if (intent.getAction().equals(DownloadImageService.ACTION_DOWNLOAD_FAIL)) {
                DialogUtils.showDialog(DownloadDataActivity.this, null,
                        "Download image error!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //                                hideLoadingDialog();
                                Common.logout(DownloadDataActivity.this);
                            }
                        }, false);
            }
        }
    };

    @Override
    protected int getIdLayout() {
        return R.layout.activity_download_data;
    }

    @Override
    protected void initData() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        binding = DataBindingUtil.setContentView(this, getIdLayout());
        binding.setPresenter(this);
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();
        progressValue = new ObservableInt();
        textProcess = new ObservableField<>();

        textProcess.set(getString(R.string.create_data_area_text, progressValue.get()) + "%");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                saveDataArea();
            }
        }, 500);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userRepository.isCreateDataBaseArea()) {
            progressValue.set(100);
            textProcess.set(getString(R.string.create_data_area_text, progressValue.get()) + "%");
            getData();
        }
        List<Image> imageList = userRepository.getImageFromDatabase();
        List<Image> imageListNoDownload =
                userRepository.getImageFromDatabase(ImageDataBase.Status.NO_DATA);
        if (imageList != null && imageList.size() != 0 && (imageListNoDownload == null
                || imageListNoDownload.size() == 0)) {
            progressValue.set(100);
            textProcess.set(getString(R.string.download_image_text, progressValue.get()) + "%");
            gotoMain();
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadImageService.ACTION_DOWNLOAD_START);
        intentFilter.addAction(DownloadImageService.ACTION_DOWNLOAD_COMPLETE);
        intentFilter.addAction(DownloadImageService.ACTION_DOWNLOAD_SUCCESS);
        intentFilter.addAction(DownloadImageService.ACTION_DOWNLOAD_FAIL);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadcastReceiverDownloadImage, intentFilter);

        IntentFilter intentFilterCreateArea = new IntentFilter();
        intentFilterCreateArea.addAction(ACTION_CREATE_AREA_COMPLETED);
        intentFilterCreateArea.addAction(CreateDataBaseService.ACTION_CREATE_AREA_SUCCESS);
        intentFilterCreateArea.addAction(CreateDataBaseService.ACTION_CREATE_AREA_FAIL);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadcastReceiverCreateArea, intentFilterCreateArea);
    }

    @Override
    protected void onStop() {
        //        if (!isFinishing() && intentCreateDataBaseAreaService != null) {
        //            stopService(intentCreateDataBaseAreaService);
        //        }
        //
        //        if (!isFinishing() && intentDownloadImageService != null) {
        //            stopService(intentDownloadImageService);
        //        }

        if (!isFinishing() && broadcastReceiverDownloadImage != null) {
            LocalBroadcastManager.getInstance(this)
                    .unregisterReceiver(broadcastReceiverDownloadImage);
        }
        if (!isFinishing() && broadcastReceiverCreateArea != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiverCreateArea);
        }
        subscriptions.clear();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
    }

    private void getData() {
        if (userRepository.isSaveIdImage()) {
            downloadImageService();
            return;
        }
        progressValue.set(0);
        textProcess.set(getString(R.string.download_image_text, progressValue.get()) + "%");

        DataRequest<GetListIdImageRequest> request = new DataRequest<>();
        request.setWsRequest(new GetListIdImageRequest());
        request.setWsCode(WsCode.GetListImageOfProductOnServer);

        Subscription subscription = userRepository.getListIdImage(request)
                .subscribe(new MBCCSSubscribe<GetListIdImageResponse>() {
                    @Override
                    public void onSuccess(GetListIdImageResponse object) {
                        listIdImage = object.getIdImage();
                        saveIdImageToDatabase();
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialogError(DownloadDataActivity.this, error,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Common.logout(DownloadDataActivity.this);
                                    }
                                }, false);
                    }
                });
        subscriptions.add(subscription);
    }

    private void saveDataArea() {
        intentCreateDataBaseAreaService = new Intent(this, CreateDataBaseService.class);
        startService(intentCreateDataBaseAreaService);
    }

    private void downloadImageService() {
        intentDownloadImageService = new Intent(this, DownloadImageService.class);
        startService(intentDownloadImageService);
    }

    private void saveIdImageToDatabase() {
        for (String data : listIdImage) {
            ImageDataBase imageDatabase = new ImageDataBase();
            imageDatabase.setImageID(data);
            imageDatabase.setImageName(data + ".jpg");
            imageDatabase.setImageStatus(ImageDataBase.Status.NO_DATA);
            imageDatabase.save();
        }
        userRepository.setSaveIdImage(true);
        downloadImageService();
    }

    private void gotoMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(DownloadDataActivity.this, MainActivity.class));
                finish();
            }
        }, 1500);
    }
}
