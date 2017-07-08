package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 7/6/17.
 */

public class RegType {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("reasonId")
    @Expose
    private int reasonId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("regSpecial")
    @Expose
    private String regSpecial;

    @SerializedName("reqProfile")
    @Expose
    private String reqProfile;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("changeDatetime")
    @Expose
    private String changeDatetime;

    @SerializedName("status")
    @Expose
    private int status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getReasonId() {
        return reasonId;
    }

    public void setReasonId(int reasonId) {
        this.reasonId = reasonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegSpecial() {
        return regSpecial;
    }

    public void setRegSpecial(String regSpecial) {
        this.regSpecial = regSpecial;
    }

    public String getReqProfile() {
        return reqProfile;
    }

    public void setReqProfile(String reqProfile) {
        this.reqProfile = reqProfile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChangeDatetime() {
        return changeDatetime;
    }

    public void setChangeDatetime(String changeDatetime) {
        this.changeDatetime = changeDatetime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getName();
    }
}
