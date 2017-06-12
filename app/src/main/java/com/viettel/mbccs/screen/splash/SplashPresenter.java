package com.viettel.mbccs.screen.splash;

import android.content.Context;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.model.DistrictResponse;
import com.viettel.mbccs.data.model.PrecinctResponse;
import com.viettel.mbccs.data.model.ProvinceResponse;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetDistrictRequest;
import com.viettel.mbccs.data.source.remote.request.GetPrecinctRequest;
import com.viettel.mbccs.data.source.remote.request.GetProvinceRequest;
import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetDistrictResponse;
import com.viettel.mbccs.data.source.remote.response.GetPrecinctResponse;
import com.viettel.mbccs.data.source.remote.response.GetProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.ProvinceDistrictPrecinctResponse;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func3;
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
        if (userRepository.getUser() == null) {
            splashView.gotoLogin();
        } else {
            splashView.gotoMain();
        }
    }

    private void getDataProvinceFromServer() {
        Subscription subscription =
                Observable.zip(getListProvince(), getListDistrict(), getListPrecinct(),
                        new Func3<GetProvinceResponse, GetDistrictResponse, GetPrecinctResponse, ProvinceDistrictPrecinctResponse>() {
                            @Override
                            public ProvinceDistrictPrecinctResponse call(
                                    GetProvinceResponse response, GetDistrictResponse response2,
                                    GetPrecinctResponse response3) {
                                return new ProvinceDistrictPrecinctResponse(response, response2,
                                        response3);
                            }
                        })
                        .flatMap(
                                new Func1<ProvinceDistrictPrecinctResponse, Observable<ProvinceDistrictPrecinctResponse>>() {
                                    @Override
                                    public Observable<ProvinceDistrictPrecinctResponse> call(
                                            ProvinceDistrictPrecinctResponse response) {
                                        List<DistrictResponse> districtList =
                                                response.getGetDistrictResponse().getDistrictList();
                                        List<ProvinceResponse> provinceList =
                                                response.getGetProvinceResponse().getProvinceList();
                                        List<PrecinctResponse> precinctList =
                                                response.getGetPrecinctResponse().getPrecinctList();
                                        if (districtList == null
                                                || districtList.size() == 0
                                                || provinceList == null
                                                || provinceList.size() == 0
                                                || precinctList == null
                                                || precinctList.size() == 0) {
                                            BaseErrorResponse baseErrorResponse =
                                                    new BaseErrorResponse();
                                            baseErrorResponse.setError(201, "");
                                            return Observable.error(
                                                    BaseException.toServerError(baseErrorResponse));
                                        } else {
                                            return Observable.just(response);
                                        }
                                    }
                                })
                        .subscribe(new MBCCSSubscribe<ProvinceDistrictPrecinctResponse>() {
                            @Override
                            public void onSuccess(ProvinceDistrictPrecinctResponse object) {
                                userRepository.setListProvince(
                                        object.getGetProvinceResponse().getProvinceList());
                                userRepository.setListDistrict(
                                        object.getGetDistrictResponse().getDistrictList());
                                userRepository.setListPrecinct(
                                        object.getGetPrecinctResponse().getPrecinctList());

                                checkLogin();
                            }

                            @Override
                            public void onError(BaseException error) {
                                // TODO: 6/1/17 show error
                            }
                        });
        subscriptions.add(subscription);
    }

    private Observable<GetProvinceResponse> getListProvince() {
        DataRequest<GetProvinceRequest> request = new DataRequest<>();

        request.setParameterApi(new GetProvinceRequest());
        request.setApiCode(ApiCode.GetProvince);

        return userRepository.getProvince(request);
    }

    private Observable<GetDistrictResponse> getListDistrict() {
        DataRequest<GetDistrictRequest> request = new DataRequest<>();
        request.setParameterApi(new GetDistrictRequest());
        request.setApiCode(ApiCode.GetProvince);
        return userRepository.getDistrict(request);
    }

    private Observable<GetPrecinctResponse> getListPrecinct() {
        DataRequest<GetPrecinctRequest> request = new DataRequest<>();
        request.setParameterApi(new GetPrecinctRequest());
        request.setApiCode(ApiCode.GetProvince);
        return userRepository.getPrecinct(request);
    }
}
