package com.viettel.mbccs.data.model;

import android.support.annotation.StringDef;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 6/1/17.
 */

public class ApDomainByType {
    @StringDef({ Type.LOAI_GIAY_TO, Type.HINH_THUC_THANH_TOAN, Type.HINH_THUC_NHAN_THONG_BAO_CUOC })
    public @interface Type {
        String LOAI_GIAY_TO = "301";
        String HINH_THUC_THANH_TOAN = "303";
        String HINH_THUC_NHAN_THONG_BAO_CUOC = "308";
    }

    public @interface SubType {
        String PREPAID = "1";
        String POSTPAID = "3";
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
