package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.District;
import com.viettel.mbccs.data.model.DistrictResponse;
import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.LoginResult;
import com.viettel.mbccs.data.model.Precinct;
import com.viettel.mbccs.data.model.PrecinctResponse;
import com.viettel.mbccs.data.model.Province;
import com.viettel.mbccs.data.model.ProvinceResponse;
import com.viettel.mbccs.data.model.Session;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.source.local.IUserLocalDataSource;
import com.viettel.mbccs.data.source.local.UserLocalDataSource;
import com.viettel.mbccs.data.source.remote.IUserRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.UserRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetDistrictRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetPrecinctRequest;
import com.viettel.mbccs.data.source.remote.request.GetProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.GetDistrictResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetPrecinctResponse;
import com.viettel.mbccs.data.source.remote.response.GetProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.SendCodeChangePassResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
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
    public void saveSession(Session session) {
        mUserLocalDataSource.saveSession(session);
    }

    @Override
    public Session getSession() {
        return mUserLocalDataSource.getSession();
    }

    @Override
    public void saveapiKey(String apikey) {

        mUserLocalDataSource.saveapiKey(apikey);
    }

    @Override
    public String getApiKey() {
        return mUserLocalDataSource.getApiKey();
    }

    @Override
    public List<Province> getListProvince() {
        List<Province> list = mUserLocalDataSource.getListProvince();
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<District> getListDistrictByProvinceId(String provinceId) {
        List<District> list = mUserLocalDataSource.getListDistrictByProvinceId(provinceId);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<Precinct> getListPrecinctByDistrictId(String districtId) {
        List<Precinct> list = mUserLocalDataSource.getListPrecinctByDistrictId(districtId);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
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
    public void setListProvince(List<ProvinceResponse> data) {
        if (data == null || data.size() == 0) return;
        mUserLocalDataSource.setListProvince(data);
    }

    @Override
    public void setListDistrict(List<DistrictResponse> data) {
        if (data == null || data.size() == 0) return;
        mUserLocalDataSource.setListDistrict(data);
    }

    @Override
    public void setListPrecinct(List<PrecinctResponse> data) {
        if (data == null || data.size() == 0) return;
        mUserLocalDataSource.setListPrecinct(data);
    }

    @Override
    public Observable<LoginInfo> login(LoginRequest loginRequest) {
        return mUserRemoteDataSource.login(loginRequest);
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
}
