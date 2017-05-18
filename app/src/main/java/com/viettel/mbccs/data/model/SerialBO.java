package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class SerialBO implements Parcelable {

    @SerializedName("stockModelId")
    @Expose
    private long stockModelId;

    @SerializedName("Quantity")
    @Expose
    private long quantity;

    @SerializedName("FromSerial")
    @Expose
    private String fromSerial;

    @SerializedName("ToSerial")
    @Expose
    private String toSerial;

    public SerialBO() {
    }

    protected SerialBO(Parcel in) {
        stockModelId = in.readLong();
        quantity = in.readLong();
        fromSerial = in.readString();
        toSerial = in.readString();
    }

    public static final Creator<SerialBO> CREATOR = new Creator<SerialBO>() {
        @Override
        public SerialBO createFromParcel(Parcel in) {
            return new SerialBO(in);
        }

        @Override
        public SerialBO[] newArray(int size) {
            return new SerialBO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(stockModelId);
        dest.writeLong(quantity);
        dest.writeString(fromSerial);
        dest.writeString(toSerial);
    }

    public long getStockModelId() {
        return stockModelId;
    }

    public void setStockModelId(long stockModelId) {
        this.stockModelId = stockModelId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getFromSerial() {
        return fromSerial;
    }

    public void setFromSerial(String fromSerial) {
        this.fromSerial = fromSerial;
    }

    public String getToSerial() {
        return toSerial;
    }

    public void setToSerial(String toSerial) {
        this.toSerial = toSerial;
    }
}
