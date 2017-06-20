package com.viettel.mbccs.screen.splash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.constance.ApiCode;
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
    private boolean checkFinish = false;

    public ObservableInt progressValue;
    public ObservableInt progressValueArea;

    private BroadcastReceiver broadcastReceiverDownloadImage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadImageService.ACTION_DOWNLOAD_COMPLETE)) {
                userRepository.setDownloadImage(true);
                progressValue.set(100);
                gotoMain();
            } else if (intent.getAction().equals(DownloadImageService.ACTION_DOWNLOAD_SUCCESS)) {
                progressValue.set(
                        intent.getIntExtra(DownloadImageService.DATA_DOWNLOAD_SUCCESS, 0));
            } else {
                DialogUtils.showDialogError(DownloadDataActivity.this, null,
                        "Download image error!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hideLoadingDialog();
                                Common.logout(DownloadDataActivity.this);
                            }
                        }, false);
            }
        }
    };

    private BroadcastReceiver broadcastReceiverCreateArea = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(CreateDataBaseService.ACTION_CREATE_AREA_COMPLETED)) {
                progressValueArea.set(
                        intent.getIntExtra(CreateDataBaseService.DATA_CREATE_AREA_COMPLETED, 0));
                gotoMain();
            } else if (intent.getAction()
                    .equals(CreateDataBaseService.ACTION_CREATE_AREA_SUCCESS)) {
                progressValueArea.set(
                        intent.getIntExtra(CreateDataBaseService.DATA_CREATE_AREA_SUCCESS, 0));
            } else {
                // TODO: 6/19/17 handler error create data area
            }
        }
    };

    @Override
    protected int getIdLayout() {
        return R.layout.activity_download_data;
    }

    @Override
    protected void initData() {
        binding = DataBindingUtil.setContentView(this, getIdLayout());
        binding.setPresenter(this);
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();
        progressValue = new ObservableInt();
        progressValueArea = new ObservableInt();
        getData();
    }

    @Override
    protected void onStop() {
        if (!isFinishing() && intentCreateDataBaseAreaService != null) {
            stopService(intentCreateDataBaseAreaService);
        }

        if (!isFinishing() && intentDownloadImageService != null) {
            stopService(intentDownloadImageService);
        }

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
            saveDataArea();
            downloadImageService();
            return;
        }

        showLoadingDialog();

        DataRequest<GetListIdImageRequest> request = new DataRequest<>();
        request.setParameterApi(new GetListIdImageRequest());
        request.setApiCode(ApiCode.GetListImageOfProductOnServer);

        Subscription subscription = userRepository.getListIdImage(request)
                .subscribe(new MBCCSSubscribe<GetListIdImageResponse>() {
                    @Override
                    public void onSuccess(GetListIdImageResponse object) {
                        listIdImage = object.getIdImage();
                        saveIdImageToDatabase();
                        saveDataArea();
                        hideLoadingDialog();
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialogError(DownloadDataActivity.this, error,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        hideLoadingDialog();
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

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CreateDataBaseService.ACTION_CREATE_AREA_COMPLETED);
        intentFilter.addAction(CreateDataBaseService.ACTION_CREATE_AREA_SUCCESS);
        intentFilter.addAction(CreateDataBaseService.ACTION_CREATE_AREA_FAIL);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadcastReceiverCreateArea, intentFilter);
    }

    private void downloadImageService() {
        intentDownloadImageService = new Intent(this, DownloadImageService.class);
        startService(intentDownloadImageService);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadImageService.ACTION_DOWNLOAD_COMPLETE);
        intentFilter.addAction(DownloadImageService.ACTION_DOWNLOAD_SUCCESS);
        intentFilter.addAction(DownloadImageService.ACTION_DOWNLOAD_FAIL);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadcastReceiverDownloadImage, intentFilter);
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
        if (!checkFinish) {
            checkFinish = true;
            return;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(DownloadDataActivity.this, MainActivity.class));
                finish();
            }
        }, 1500);
    }
}
