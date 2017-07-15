package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.Image;
import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.Precinct;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.model.database.ImageDataBase;
import com.viettel.mbccs.data.model.database.UploadImage;
import com.viettel.mbccs.data.source.local.IUserLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.UserLocalDataSource;
import com.viettel.mbccs.data.source.remote.IUserRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.UserRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.DownloadImageRequest;
import com.viettel.mbccs.data.source.remote.request.GetDistrictRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetListIdImageRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOwnerCodeRequest;
import com.viettel.mbccs.data.source.remote.request.GetPrecinctRequest;
import com.viettel.mbccs.data.source.remote.request.GetProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.GetUserInfoRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.request.PassResetRequest;
import com.viettel.mbccs.data.source.remote.request.UploadImageRequest;
import com.viettel.mbccs.data.source.remote.response.DownloadImageResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistrictResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetListIdImageResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOwneCodeReponse;
import com.viettel.mbccs.data.source.remote.response.GetPrecinctResponse;
import com.viettel.mbccs.data.source.remote.response.GetProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.SendCodeChangePassResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.response.UploadImageResponse;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class UserRepository implements IUserLocalDataSource, IUserRemoteDataSource {

    private volatile static UserRepository instance;
    private IUserLocalDataSource mUserLocalDataSource;
    private IUserRemoteDataSource mUserRemoteDataSource;

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
    public void saveUser(LoginInfo loginResult) {
        mUserLocalDataSource.saveUser(loginResult);
    }

    @Override
    public LoginInfo getUser() {
        return mUserLocalDataSource.getUser();
    }

    @Override
    public List<String> getFunctionsCodes() {
        return mUserLocalDataSource.getFunctionsCodes();
    }

    @Override
    public void saveLoginUserName(String name) {
        mUserLocalDataSource.saveLoginUserName(name);
    }

    @Override
    public String getLoginUserName() {
        return mUserLocalDataSource.getLoginUserName();
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        mUserLocalDataSource.saveUserInfo(userInfo);
    }

    @Override
    public UserInfo getUserInfo() {
        return mUserLocalDataSource.getUserInfo();
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
    public void saveApiKey(String apiKey) {
        mUserLocalDataSource.saveApiKey(apiKey);
    }

    @Override
    public String getApiKey() {
        return mUserLocalDataSource.getApiKey();
    }

    @Override
    public List<Area> getListAreaProvince() {
        return mUserLocalDataSource.getListAreaProvince();
    }

    @Override
    public List<Area> getListDistrictByProvinceId(String provinceId) {
        return mUserLocalDataSource.getListDistrictByProvinceId(provinceId);
    }

    @Override
    public List<Area> getListPrecinctByDistrictId(String districtId) {
        return mUserLocalDataSource.getListPrecinctByDistrictId(districtId);
    }

    @Override
    public List<Precinct> getListPrecinctByProvinceAndDistrictId(String provinceId,
            String districtId) {
        List<Precinct> list =
                mUserLocalDataSource.getListPrecinctByProvinceAndDistrictId(provinceId, districtId);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<UploadImage> getUploadImage() {
        return mUserLocalDataSource.getUploadImage();
    }

    @Override
    public List<UploadImage> getUploadImage(@UploadImage.StatusUpload String status) {
        return mUserLocalDataSource.getUploadImage(status);
    }

    @Override
    public UploadImage getUploadImageByName(String name) {
        return mUserLocalDataSource.getUploadImageByName(name);
    }

    @Override
    public void setUploadImage(List<UploadImage> data) {
        mUserLocalDataSource.setUploadImage(data);
    }

    @Override
    public boolean isCreateDataBaseArea() {
        return mUserLocalDataSource.isCreateDataBaseArea();
    }

    @Override
    public void setCreateDataBaseArea(boolean status) {
        mUserLocalDataSource.setCreateDataBaseArea(status);
    }

    @Override
    public boolean isDownloadImage() {
        return mUserLocalDataSource.isDownloadImage();
    }

    @Override
    public void setDownloadImage(boolean status) {
        mUserLocalDataSource.setDownloadImage(status);
    }

    @Override
    public boolean isSaveIdImage() {
        return mUserLocalDataSource.isSaveIdImage();
    }

    @Override
    public void setSaveIdImage(boolean status) {
        mUserLocalDataSource.setSaveIdImage(status);
    }

    @Override
    public List<Image> getImageFromDatabase() {
        return mUserLocalDataSource.getImageFromDatabase();
    }

    @Override
    public List<Image> getImageFromDatabase(int status) {
        return mUserLocalDataSource.getImageFromDatabase(status);
    }

    @Override
    public Image getImageFromDatabase(String id) {
        return mUserLocalDataSource.getImageFromDatabase(id);
    }

    @Override
    public ImageDataBase getDataImageFromDatabase(String id) {
        return mUserLocalDataSource.getDataImageFromDatabase(id);
    }

    @Override
    public Observable<LoginInfo> login(LoginRequest loginRequest) {
        return mUserRemoteDataSource.login(loginRequest);
    }

    @Override
    public Observable<UserInfo> getUserInfo(DataRequest<GetUserInfoRequest> request) {
        return mUserRemoteDataSource.getUserInfo(request);
    }

    @Override
    public Observable<SendCodeChangePassResponse> sendCodeChangePass(String phone) {
        return mUserRemoteDataSource.sendCodeChangePass(phone);
    }

    @Override
    public Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            DataRequest<GetTelecomServiceAndSaleProgramRequest> request) {
        return mUserRemoteDataSource.getTelecomserviceAndSaleProgram(request);
    }

    @Override
    public Observable<GetSerialsResponse> getSerial(DataRequest<GetSerialRequest> request) {
        return mUserRemoteDataSource.getSerial(request);
    }

    @Override
    public Observable<GetTotalStockResponse> getModelSales(
            DataRequest<GetTotalStockRequest> request) {
        return mUserRemoteDataSource.getModelSales(request);
    }

    @Override
    public Observable<GetInfoSaleTranResponse> getSaleTransInfo(
            DataRequest<GetInfoSaleTranRequest> request) {
        return mUserRemoteDataSource.getSaleTransInfo(request);
    }

    @Override
    public Observable<GetInfoSaleTranResponse> createSaleTransRetail(
            DataRequest<GetInfoSaleTranRequest> request) {
        return mUserRemoteDataSource.createSaleTransRetail(request);
    }

    @Override
    public Observable<GetProvinceResponse> getProvince(DataRequest<GetProvinceRequest> request) {
        return mUserRemoteDataSource.getProvince(request);
    }

    @Override
    public Observable<GetDistrictResponse> getDistrict(DataRequest<GetDistrictRequest> request) {
        return mUserRemoteDataSource.getDistrict(request);
    }

    @Override
    public Observable<GetPrecinctResponse> getPrecinct(DataRequest<GetPrecinctRequest> request) {
        return mUserRemoteDataSource.getPrecinct(request);
    }

    @Override
    public Observable<UploadImageResponse> uploadImage(DataRequest<UploadImageRequest> request) {
        return mUserRemoteDataSource.uploadImage(request);
    }

    @Override
    public Observable<GetListIdImageResponse> getListIdImage(
            DataRequest<GetListIdImageRequest> request) {
        return mUserRemoteDataSource.getListIdImage(request);
    }

    @Override
    public Observable<DownloadImageResponse> downloadImage(
            DataRequest<DownloadImageRequest> request) {
        return mUserRemoteDataSource.downloadImage(request);
    }

    @Override
    public Observable<EmptyObject> resetPassword(PassResetRequest request) {
        return mUserRemoteDataSource.resetPassword(request);
    }

    @Override
    public Observable<GetListOwneCodeReponse> getListOwnerCode(
            DataRequest<GetListOwnerCodeRequest> dataRequest) {
        return mUserRemoteDataSource.getListOwnerCode(dataRequest);
    }
}
