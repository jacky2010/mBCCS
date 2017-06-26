package com.viettel.mbccs.service.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.UploadImage;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.UploadImageRequest;
import com.viettel.mbccs.data.source.remote.response.UploadImageResponse;
import com.viettel.mbccs.utils.DateUtils;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 6/8/17.
 */

public class UploadImageService extends IntentService {
    public static final String ACTION_UPLOAD_SUCCESS = "upload_success";
    public static final String ACTION_UPLOAD_FAIL = "upload_fail";
    public static final String ACTION_UPLOAD_COMPLETED = "upload_completed";
    public static final String DATA_UPLOAD_SUCCESS = "data_upload_success";
    public static final String DATA_UPLOAD_ERROR = "data_upload_error";
    public static final String DATA_UPLOAD_WAITING = "data_upload_waiting";
    public static final String ARG_DATA_INTENT = "data_intent";
    private UserRepository userRepository;
    private CompositeSubscription subscriptions;

    private List<Observable<UploadImageResponse>> observableList;
    private List<UploadImage> uploadImageList;
    private int progressValue;
    private int total;
    private int curre;
    private Integer notificationID = 100;
    private Notification notification;
    private NotificationManager notificationManager;
    private Notification.Builder notificationBuilder;

    public UploadImageService() {
        super(UploadImageService.class.getName());
        Log.i("UploadImageService", " -> UploadImageService: ----------------: UploadImageService");
        setIntentRedelivery(true);
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public UploadImageService(String name) {
        super(UploadImageService.class.getName());
        Log.i("UploadImageService",
                " -> UploadImageService: ----------------: UploadImageService(String name)");
        setIntentRedelivery(true);
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        observableList = new ArrayList<>();
        if (intent.getParcelableExtra(ARG_DATA_INTENT) != null) {
            uploadImageList = userRepository.getUploadImage();
        } else {
            uploadImageList = intent.getParcelableArrayListExtra(ARG_DATA_INTENT);
        }

        if (uploadImageList.size() == 0) return;
        for (UploadImage uploadImage : uploadImageList) {
            observableList.add(createUploadImage(uploadImage));
        }
        startUploadImage(intent);
    }

    public void startUploadImage(final Intent intent) {
        createNotification();
        updateNotification(getString(R.string.upload_service_image_front), 0);
        Subscription subscription =
                Observable.create(new Observable.OnSubscribe<UploadImageResponse>() {
                    int process = 0;

                    @Override
                    public void call(final Subscriber<? super UploadImageResponse> subscriber) {
                        Observable.mergeDelayError(observableList)
                                .subscribe(new Subscriber<UploadImageResponse>() {
                                    @Override
                                    public void onCompleted() {
                                        subscriber.onCompleted();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        subscriber.onError(e);
                                    }

                                    @Override
                                    public void onNext(UploadImageResponse response) {
                                        if (++process <= observableList.size()) {
                                            String name = "";
                                            if (process == observableList.size()) {
                                                name = getString(R.string.upload_service_finish);
                                            } else {
                                                switch (uploadImageList.get(process).getOrder()) {
                                                    case UploadImage.ImageType.FRONT:
                                                        name = getString(
                                                                R.string.upload_service_image_front);
                                                        break;
                                                    case UploadImage.ImageType.BACKSIDE:
                                                        name = getString(
                                                                R.string.upload_service_image_backside);
                                                        break;
                                                    case UploadImage.ImageType.PORTRAIT:
                                                        name = getString(
                                                                R.string.upload_service_image_portrait);
                                                        break;
                                                }
                                            }
                                            updateNotification(name,
                                                    (process / observableList.size()) * 100);

                                            subscriber.onNext(response);
                                            if (process == observableList.size()) {
                                                subscriber.onCompleted();
                                                cancelNotification();
                                            }
                                        } else {
                                            subscriber.onCompleted();
                                        }
                                    }
                                });
                    }
                }).subscribe(new Subscriber<UploadImageResponse>() {
                    int process = 0;

                    @Override
                    public void onCompleted() {
                        intent.setAction(ACTION_UPLOAD_COMPLETED);
                        LocalBroadcastManager.getInstance(UploadImageService.this)
                                .sendBroadcast(intent);
                        Toast.makeText(UploadImageService.this, "Upload thanh cong",
                                Toast.LENGTH_SHORT).show();
                        stopSelf();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //                        stopSelf();
                        intent.setAction(ACTION_UPLOAD_FAIL);
                        LocalBroadcastManager.getInstance(UploadImageService.this)
                                .sendBroadcast(intent);
                    }

                    @Override
                    public void onNext(UploadImageResponse response) {
                        intent.setAction(ACTION_UPLOAD_SUCCESS);
                        intent.putExtra(DATA_UPLOAD_SUCCESS, response);
                        LocalBroadcastManager.getInstance(UploadImageService.this)
                                .sendBroadcast(intent);
                    }
                });
        subscriptions.add(subscription);
    }

    private void createNotification() {
        //Set notification information:
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder = new Notification.Builder(getApplicationContext());
        notificationBuilder.setOngoing(true)
                .setContentTitle(getString(R.string.upload_service_title))
                .setProgress(100, 0, false);

        //Send the notification:
        notification = notificationBuilder.getNotification();
        notificationManager.notify(notificationID, notification);
    }

    private void updateNotification(String content, int process) {
        //Update notification information:
        notificationBuilder.setContentText(content).setProgress(100, process, false);

        //Send the notification:
        notification = notificationBuilder.getNotification();
        notificationManager.notify(notificationID, notification);
    }

    private void cancelNotification() {
        notificationManager.cancel(notificationID);
    }

    private Observable<UploadImageResponse> createUploadImage(UploadImage data) {
        UploadImageRequest uploadImageRequest = new UploadImageRequest();
        uploadImageRequest.setActionBusiness(data.getActionBusiness());
        uploadImageRequest.setObjectId(data.getObjectId());
        uploadImageRequest.setOrder(data.getOrder());
        uploadImageRequest.setDocType(data.getDocType());
        uploadImageRequest.setFileName(data.getFileName());
        uploadImageRequest.setImageData(data.getImageData());
        uploadImageRequest.setTransDate(DateUtils.convertDateToString(System.currentTimeMillis(),
                DateUtils.CALENDAR_DATE_FORMAT_DD_MM_YY_HH));

        DataRequest<UploadImageRequest> request = new DataRequest<>();
        request.setWsRequest(uploadImageRequest);
        request.setWsCode(WsCode.UpLoadImage);
        return userRepository.uploadImage(request);
    }
}
