package com.viettel.mbccs.service.service;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.google.common.collect.Lists;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.Image;
import com.viettel.mbccs.data.model.database.ImageDataBase;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.DownloadImageRequest;
import com.viettel.mbccs.data.source.remote.response.DownloadImageResponse;
import com.viettel.mbccs.utils.ImageUtils;
import com.viettel.mbccs.variable.Constants;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 6/16/17.
 */

public class DownloadImageService extends IntentService {

    public static final String ACTION_DOWNLOAD_START = "action_download_start";
    public static final String ACTION_DOWNLOAD_COMPLETE = "action_download_complete";
    public static final String ACTION_DOWNLOAD_SUCCESS = "action_download_success";
    public static final String ACTION_DOWNLOAD_FAIL = "action_download_fail";
    public static final String DATA_DOWNLOAD_SUCCESS = "data_download_success";
    public static final String DATA_DOWNLOAD_FAIL = "data_download_fail";

    public static final String DATA_ID_IMAGE = "data_id_iamge";
    private UserRepository userRepository;
    private CompositeSubscription subscriptions;
    private List<Observable<DownloadImageResponse>> observableList;
    private int countImageDownload;
    private int currentImageDownload;
    private int currentProcess;
    //    private Intent currentIntent;

    public DownloadImageService() {
        super(UploadImageService.class.getName());
        setIntentRedelivery(true);
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();
        observableList = new ArrayList<>();
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadImageService(String name) {
        super(UploadImageService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        List<Image> imageList = userRepository.getImageFromDatabase(ImageDataBase.Status.NO_DATA);
        if (imageList == null || imageList.size() == 0) {
            LocalBroadcastManager.getInstance(DownloadImageService.this)
                    .sendBroadcast(new Intent(ACTION_DOWNLOAD_COMPLETE));
            return;
        }
        countImageDownload = imageList.size();
        List<List<Image>> smallerList = Lists.partition(imageList, Constants.NUM_IMAGE_DOWNLOAD);
        for (int i = 0; i < smallerList.size(); i++) {
            observableList.add(createDownloadImage(smallerList.get(i)));
        }
        downloadImage();
    }

    @Override
    public void onDestroy() {
        subscriptions.clear();
        super.onDestroy();
    }

    private void downloadImage() {

        LocalBroadcastManager.getInstance(DownloadImageService.this)
                .sendBroadcast(new Intent(ACTION_DOWNLOAD_START));

        Subscription s = Observable.create(new Observable.OnSubscribe<DownloadImageResponse>() {
            @Override
            public void call(final Subscriber<? super DownloadImageResponse> subscriber) {
                Observable.mergeDelayError(observableList)
                        .subscribe(new Subscriber<DownloadImageResponse>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(DownloadImageResponse response) {
                                subscriber.onNext(response);
                            }
                        });
            }
        }).subscribe(new Subscriber<DownloadImageResponse>() {
            @Override
            public void onCompleted() {
                userRepository.setDownloadImage(true);
                LocalBroadcastManager.getInstance(DownloadImageService.this)
                        .sendBroadcast(new Intent(ACTION_DOWNLOAD_COMPLETE));
            }

            @Override
            public void onError(Throwable e) {
                LocalBroadcastManager.getInstance(DownloadImageService.this)
                        .sendBroadcast(new Intent(ACTION_DOWNLOAD_FAIL));
            }

            @Override
            public void onNext(DownloadImageResponse response) {
                try {
                    saveImageToDatabase(response.getDataImageList());
                } catch (IOException e) {
                    e.printStackTrace();
                    onError(new Exception());
                }
            }
        });

        subscriptions.add(s);
    }

    private void saveImageToDatabase(List<DownloadImageResponse.DataImage> dataImage)
            throws IOException {
        Intent intentSuccess = new Intent(ACTION_DOWNLOAD_SUCCESS);
        for (DownloadImageResponse.DataImage image : dataImage) {
            File file = ImageUtils.saveBase64ToFile(this, image.getData(), image.getId());
            ImageDataBase imageDataBase = userRepository.getDataImageFromDatabase(image.getId());
            imageDataBase.setImageName(image.getId() + ".jpg");
            imageDataBase.setImageVersion(image.getVersion());
            imageDataBase.setImageUri(Uri.fromFile(file).toString());
            imageDataBase.setImagePath(file.getAbsolutePath());
            imageDataBase.setImageStatus(ImageDataBase.Status.DATA);
            imageDataBase.save();

            currentImageDownload += 1;
            intentSuccess.putExtra(DATA_DOWNLOAD_SUCCESS,
                    (int) (((float) currentImageDownload / countImageDownload) * 100));
            LocalBroadcastManager.getInstance(this).sendBroadcast(intentSuccess);
        }
    }

    private Observable<DownloadImageResponse> createDownloadImage(List<Image> data) {
        DownloadImageRequest downloadImageRequest = new DownloadImageRequest();
        List<String> dataList = new ArrayList<>();
        for (Image image : data) {
            dataList.add(image.getImageID());
        }
        downloadImageRequest.setListIdImage(dataList);

        DataRequest<DownloadImageRequest> request = new DataRequest<>();
        request.setWsRequest(downloadImageRequest);
        request.setWsCode(WsCode.GetDetailImageOfProduct);
        return userRepository.downloadImage(request);
    }
}
