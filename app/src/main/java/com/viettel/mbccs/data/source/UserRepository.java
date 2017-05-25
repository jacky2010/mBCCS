package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.LoginResult;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleTrans;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.source.local.IUserLocalDataSource;
import com.viettel.mbccs.data.source.local.UserLocalDataSource;
import com.viettel.mbccs.data.source.remote.IUserRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.UserRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.GetSerialsReponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class UserRepository implements IUserLocalDataSource, IUserRemoteDataSource {

    private volatile static UserRepository instance;
    private UserLocalDataSource mUserLocalDataSource;
    private UserRemoteDataSource mUserRemoteDataSource;

    public UserRepository(UserLocalDataSource userLocalDataSource,
                          UserRemoteDataSource userRemoteDataSource) {
        this.mUserLocalDataSource = userLocalDataSource;
        mUserRemoteDataSource = userRemoteDataSource;
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository(UserLocalDataSource.getInstance(),
                    UserRemoteDataSource.getInstance());
        }
        return instance;
    }

    @Override
    public String getAccessToken() {
        return mUserLocalDataSource.getAccessToken();
    }

    @Override
    public void saveUser(LoginResult loginResult) {
        mUserLocalDataSource.saveUser(loginResult);
    }

    @Override
    public void saveStaffInfoToSharePrefs(StaffInfo staffInfo) {
        mUserLocalDataSource.saveStaffInfoToSharePrefs(staffInfo);
    }

    @Override
    public StaffInfo getStaffInfoFromSharePrefs() {
        return mUserLocalDataSource.getStaffInfoFromSharePrefs();
    }

    @Override
    public void saveLanguageToSharePrefs(String language) {
        mUserLocalDataSource.saveLanguageToSharePrefs(language);
    }

    @Override
    public String getLanguageFromSharePrefs() {
        return mUserLocalDataSource.getLanguageFromSharePrefs();
    }

    @Override
    public void saveCountryToSharePrefs(String code) {
        mUserLocalDataSource.saveCountryToSharePrefs(code);
    }

    @Override
    public String getCountryFromSharePrefs() {
        return mUserLocalDataSource.getCountryFromSharePrefs();
    }

    @Override
    public void saveStatusNotification(boolean status) {
        mUserLocalDataSource.saveStatusNotification(status);
    }

    @Override
    public boolean getStatusNotification() {
        return mUserLocalDataSource.getStatusNotification();
    }

    @Override
    public void saveDisplayDashBoard(boolean status) {
        mUserLocalDataSource.saveDisplayDashBoard(status);
    }

    @Override
    public boolean getDisplayDashBoard() {
        return mUserLocalDataSource.getDisplayDashBoard();
    }

    @Override
    public void saveSyncBCCS(boolean status) {
        mUserLocalDataSource.saveSyncBCCS(status);
    }

    @Override
    public boolean getSyncBCCS() {
        return mUserLocalDataSource.getSyncBCCS();
    }

    @Override
    public void saveTimeSyncBCCS(int time) {
        mUserLocalDataSource.saveTimeSyncBCCS(time);
    }

    @Override
    public int getTimeSyncBCCS() {
        return mUserLocalDataSource.getTimeSyncBCCS();
    }

    @Override
    public Observable<LoginInfo> login(LoginRequest loginRequest) {
        return mUserRemoteDataSource.login(loginRequest);
    }

    @Override
    public Observable<Object> sendCodeChangePass(String phone) {
        return mUserRemoteDataSource.sendCodeChangePass(phone);
    }

    @Override
    public Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            BaseRequest<GetTelecomServiceAndSaleProgramRequest> request) {
        return mUserRemoteDataSource.getTelecomserviceAndSaleProgram(request);
    }

    @Override
    public Observable<GetSerialsReponse> getSerial(BaseRequest<GetSerialRequest> request) {
        return mUserRemoteDataSource.getSerial(request);
    }

    @Override
    public Observable<List<ModelSale>> getModelSales(BaseRequest<GetTotalStockRequest> request) {
        return mUserRemoteDataSource.getModelSales(request);
    }

    @Override
    public Observable<SaleTrans> getSaleTransInfo(BaseRequest<GetInfoSaleTranRequest> request) {
        return mUserRemoteDataSource.getSaleTransInfo(request);
    }

    @Override
    public Observable<SaleTrans> createSaleTransRetail(
            BaseRequest<GetInfoSaleTranRequest> request) {
        return mUserRemoteDataSource.createSaleTransRetail(request);
    }
}
