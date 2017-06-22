package com.viettel.mbccs.data.model;

import android.support.annotation.StringDef;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 6/1/17.
 */

public class ApDomain {
    @StringDef({ Type.LOAI_GIAY_TO, Type.HINH_THUC_THANH_TOAN, Type.HINH_THUC_NHAN_THONG_BAO_CUOC })
    public @interface Type {
        String LOAI_GIAY_TO = "301";
        String HINH_THUC_THANH_TOAN = "303";
        String HINH_THUC_NHAN_THONG_BAO_CUOC = "308";
        String LOAI_KHACH_HANG = "310";
    }

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("name")
    @Expose
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
