package com.viettel.mbccs.screen.splash;

import android.content.Context;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.utils.StringUtils;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HuyQuyet on 6/1/17.
 */

public class SplashPresenter implements SplashContract.Presenter {
    private Context context;
    private SplashContract.View splashView;
    private UserRepository userRepository;
    private BanHangKhoTaiChinhRepository b;
    private CompositeSubscription subscriptions;

    public SplashPresenter(Context context, SplashContract.View splashView) {
        this.context = context;
        this.splashView = splashView;
    }

    @Override
    public void subscribe() {
        userRepository = UserRepository.getInstance();
        b = BanHangKhoTaiChinhRepository.getInstance();
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    public void checkDataProvince() {
        checkLogin();
        //        if (userRepository.getListProvince().size() != 0) {
        //            checkLogin();
        //        } else {
        //            getDataProvinceFromServer();
        //        }
    }

    private void checkLogin() {
        if (userRepository.getUser() == null || StringUtils.isEmpty(
                userRepository.getUser().getToken()) || userRepository.getUserInfo() == null) {
            splashView.gotoLogin();
        } else {
            splashView.gotoMain();
        }
    }
}
