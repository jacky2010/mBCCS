package com.viettel.mbccs.service.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.DataImage;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.DownloadImageRequest;
import com.viettel.mbccs.data.source.remote.response.DownloadImageResponse;
import java.util.List;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 6/16/17.
 */

public class DownloadImageService extends IntentService {

    private UserRepository userRepository;
    private CompositeSubscription subscriptions;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadImageService(String name) {
        super(UploadImageService.class.getName());
        setIntentRedelivery(true);
        userRepository = UserRepository.getInstance();
        subscriptions = new CompositeSubscription();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    private Observable<DownloadImageResponse> createDownloadloadImage(List<DataImage> data) {
        DownloadImageRequest downloadImageRequest = new DownloadImageRequest();

        DataRequest<DownloadImageRequest> request = new DataRequest<>();
        request.setParameterApi(downloadImageRequest);
        request.setApiCode(ApiCode.UpLoadImage);
        return userRepository.downloadImage(request);
    }
}
