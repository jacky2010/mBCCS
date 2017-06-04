package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eo_cuong on 6/4/17.
 */

public class StockTrans implements Parcelable {

    private long stockTransId;
    private long stockTransType;
    private long fromOwnerId;
    private long fromOwnerType;
    private long toOwnerId;
    private long toOwnerType;
    private long stockTransStatus;
    private String stockTransStatusName;
    private String createDateTime;
    private String realTransDate;

    public long getStockTransId() {
        return stockTransId;
    }

    public void setStockTransId(long stockTransId) {
        this.stockTransId = stockTransId;
    }

    public long getStockTransType() {
        return stockTransType;
    }

    public void setStockTransType(long stockTransType) {
        this.stockTransType = stockTransType;
    }

    public long getFromOwnerId() {
        return fromOwnerId;
    }

    public void setFromOwnerId(long fromOwnerId) {
        this.fromOwnerId = fromOwnerId;
    }

    public long getFromOwnerType() {
        return fromOwnerType;
    }

    public void setFromOwnerType(long fromOwnerType) {
        this.fromOwnerType = fromOwnerType;
    }

    public long getToOwnerId() {
        return toOwnerId;
    }

    public void setToOwnerId(long toOwnerId) {
        this.toOwnerId = toOwnerId;
    }

    public long getToOwnerType() {
        return toOwnerType;
    }

    public void setToOwnerType(long toOwnerType) {
        this.toOwnerType = toOwnerType;
    }

    public long getStockTransStatus() {
        return stockTransStatus;
    }

    public void setStockTransStatus(long stockTransStatus) {
        this.stockTransStatus = stockTransStatus;
    }

    public String getStockTransStatusName() {
        return stockTransStatusName;
    }

    public void setStockTransStatusName(String stockTransStatusName) {
        this.stockTransStatusName = stockTransStatusName;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getRealTransDate() {
        return realTransDate;
    }

    public void setRealTransDate(String realTransDate) {
        this.realTransDate = realTransDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.stockTransId);
        dest.writeLong(this.stockTransType);
        dest.writeLong(this.fromOwnerId);
        dest.writeLong(this.fromOwnerType);
        dest.writeLong(this.toOwnerId);
        dest.writeLong(this.toOwnerType);
        dest.writeLong(this.stockTransStatus);
        dest.writeString(this.stockTransStatusName);
        dest.writeString(this.createDateTime);
        dest.writeString(this.realTransDate);
    }

    public StockTrans() {
    }

    protected StockTrans(Parcel in) {
        this.stockTransId = in.readLong();
        this.stockTransType = in.readLong();
        this.fromOwnerId = in.readLong();
        this.fromOwnerType = in.readLong();
        this.toOwnerId = in.readLong();
        this.toOwnerType = in.readLong();
        this.stockTransStatus = in.readLong();
        this.stockTransStatusName = in.readString();
        this.createDateTime = in.readString();
        this.realTransDate = in.readString();
    }

    public static final Parcelable.Creator<StockTrans> CREATOR =
            new Parcelable.Creator<StockTrans>() {
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
