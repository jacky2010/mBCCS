package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.utils.Common;

/**
 * Created by HuyQuyet on 7/10/17.
 */

public class Quota {
    @SerializedName("quotaId")
    @Expose
    private long quotaId;

    @SerializedName("quotaValue")
    @Expose
    private long quotaValue;

    public long getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(long quotaId) {
        this.quotaId = quotaId;
    }

    public long getQuotaValue() {
        return quotaValue;
    }

    public void setQuotaValue(long quotaValue) {
        this.quotaValue = quotaValue;
    }

    @Override
    public String toString() {
        return Common.formatDouble(getQuotaValue());
    }
}
