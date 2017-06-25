package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Created by jacky on 6/23/17.
 */

public class LamModel implements Serializable{

    @SerializedName("reservedPort")
    @Expose
    public int mReservedPort;

    @SerializedName("address")
    @Expose
    public String mAddress;

    @SerializedName("code")
    @Expose
    public String mCode;

    @SerializedName("productId")
    @Expose
    public int mProductId;

    @SerializedName("province")
    @Expose
    public String mProvince;

    @SerializedName("dslamId")
    @Expose
    public long mDslamId;

    @SerializedName("name")
    @Expose
    public String mName;

    @SerializedName("usedPort")
    @Expose
    public int mUsedPort;

    @SerializedName("maxPort")
    @Expose
    public int mMaxPort;

    @SerializedName("shopId")
    @Expose
    public int mShopId;

    @SerializedName("dslamIp")
    @Expose
    public String mDslamIp;

    @SerializedName("status")
    @Expose
    public int mStatus;
}
