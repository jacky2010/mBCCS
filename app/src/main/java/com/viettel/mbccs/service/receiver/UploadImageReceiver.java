package com.viettel.mbccs.service.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.service.service.UploadImageService;
import com.viettel.mbccs.utils.Connectivity;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 6/8/17.
 */

public class UploadImageReceiver extends BroadcastReceiver {

    private UserRepository userRepository;
    private CompositeSubscription subscriptions;


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (!Connectivity.isConnected(context)) {

                Intent intentService = new Intent(context, UploadImageService.class);
                context.startService(intentService);
            }
        }
    }
}
