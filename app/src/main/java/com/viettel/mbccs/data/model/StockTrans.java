package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.constance.ActionType;
import com.viettel.mbccs.constance.StockTransStatus;

/**
 * Created by eo_cuong on 6/4/17.
 */

public class StockTrans implements Parcelable {

    @SerializedName("createDatetime")
    @Expose
    private String createDatetime;
    @SerializedName("stockTransStatus")
    @Expose
    private long stockTransStatus;
    @SerializedName("stockTransId")
    @Expose
    private long stockTransId;
    @SerializedName("toOwnerType")
    @Expose
    private long toOwnerType;
    @SerializedName("toOwnerId")
    @Expose
    private long toOwnerId;
    @SerializedName("fromOwnerType")
    @Expose
    private long fromOwnerType;
    @SerializedName("fromOwnerId")
    @Expose
    private long fromOwnerId;
    @SerializedName("stockTransType")
    @Expose
    private long stockTransType;

    @SerializedName("stockTransStatusName")
    @Expose
    private String stockTransStatusName;

    private String actionName;

    public String getStockTransStatusName() {
        return stockTransStatusName;
    }

    public void setStockTransStatusName(String stockTransStatusName) {
        this.stockTransStatusName = stockTransStatusName;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public long getStockTransStatus() {
        return stockTransStatus;
    }

    public void setStockTransStatus(long stockTransStatus) {
        this.stockTransStatus = stockTransStatus;
    }

    public long getStockTransId() {
        return stockTransId;
    }

    public void setStockTransId(long stockTransId) {
        this.stockTransId = stockTransId;
    }

    public long getToOwnerType() {
        return toOwnerType;
    }

    public void setToOwnerType(long toOwnerType) {
        this.toOwnerType = toOwnerType;
    }

    public long getToOwnerId() {
        return toOwnerId;
    }

    public void setToOwnerId(long toOwnerId) {
        this.toOwnerId = toOwnerId;
    }

    public long getFromOwnerType() {
        return fromOwnerType;
    }

    public void setFromOwnerType(long fromOwnerType) {
        this.fromOwnerType = fromOwnerType;
    }

    public long getFromOwnerId() {
        return fromOwnerId;
    }

    public void setFromOwnerId(long fromOwnerId) {
        this.fromOwnerId = fromOwnerId;
    }

    public long getStockTransType() {
        return stockTransType;
    }

    public void setStockTransType(long stockTransType) {
        this.stockTransType = stockTransType;
    }

    public int getActionType() {
        switch ((int) stockTransStatus) {
            case (int) StockTransStatus.TRANS_CANCEL:
                return ActionType.ACTION_CANCEL;
            case (int) StockTransStatus.TRANS_REJECT:
                return ActionType.ACTION_CANCEL;
            default:
                return ActionType.ACTION_OTHER;
        }
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return this.actionName;
    }

    public StockTrans() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createDatetime);
        dest.writeLong(this.stockTransStatus);
        dest.writeLong(this.stockTransId);
        dest.writeLong(this.toOwnerType);
        dest.writeLong(this.toOwnerId);
        dest.writeLong(this.fromOwnerType);
        dest.writeLong(this.fromOwnerId);
        dest.writeLong(this.stockTransType);
        dest.writeString(this.stockTransStatusName);
    }

    protected StockTrans(Parcel in) {
        this.createDatetime = in.readString();
        this.stockTransStatus = in.readLong();
        this.stockTransId = in.readLong();
        this.toOwnerType = in.readLong();
        this.toOwnerId = in.readLong();
        this.fromOwnerType = in.readLong();
        this.fromOwnerId = in.readLong();
        this.stockTransType = in.readLong();
        this.stockTransStatusName = in.readString();
    }

    public static final Creator<StockTrans> CREATOR = new Creator<StockTrans>() {
        @Override
        public StockTrans createFromParcel(Parcel source) {
            return new StockTrans(source);
        }

        @Override
        public StockTrans[] newArray(int size) {
            return new StockTrans[size];
        }
    };
}
