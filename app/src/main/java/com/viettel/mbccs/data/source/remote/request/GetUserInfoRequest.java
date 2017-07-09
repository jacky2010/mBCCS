package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by FRAMGIA\vu.viet.anh on 12/06/2017.
 */

public class GetUserInfoRequest extends BaseRequest  {
    @Expose
    @SerializedName("staffCode")
    private String staffCode;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public GetUserInfoRequest() {
        super();
    }
}
