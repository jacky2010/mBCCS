package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.UserInfo;

/**
 * Created by FRAMGIA\vu.viet.anh on 12/06/2017.
 */

public class GetUserInfoResponse extends DataResponse {

    @Expose
    @SerializedName("object")
    private UserInfo mUserInfo;

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
    }
}
