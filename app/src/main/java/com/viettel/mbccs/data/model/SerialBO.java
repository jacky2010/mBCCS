package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/14/17.
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

    public SerialBO(String fromSerial, String toSerial) {
        this.fromSerial = fromSerial;
        this.toSerial = toSerial;
    }

    public SerialBO() {
        fromSerial = "-1";
        toSerial = "-1";
    }

    public long getStockModelId() {
        return stockModelId;
    }

    public void setStockModelId(long stockModelId) {
        this.stockModelId = stockModelId;
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

    public long getQuantity() {
        if (quantity == 0) {
            if (TextUtils.isEmpty(getFromSerial()) || TextUtils.isEmpty(getToSerial())) {
                return 0;
            }
        }
        return Long.parseLong(getToSerial()) - Long.parseLong(getFromSerial()) + 1;
    }

    public String getQuantityString() {
        return String.valueOf(getQuantity());
    }

    public List<String> toSerialList() {
        List<String> serials = new ArrayList<>();
        for (long i = Long.parseLong(fromSerial); i <= Long.parseLong(toSerial); i++) {
            serials.add(String.valueOf(i));
        }
        return serials;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.stockModelId);
        dest.writeLong(this.quantity);
        dest.writeString(this.fromSerial);
        dest.writeString(this.toSerial);
    }

    protected SerialBO(Parcel in) {
        this.stockModelId = in.readLong();
        this.quantity = in.readLong();
        this.fromSerial = in.readString();
        this.toSerial = in.readString();
    }

    public static final Creator<SerialBO> CREATOR = new Creator<SerialBO>() {
        @Override
        public SerialBO createFromParcel(Parcel source) {
            return new SerialBO(source);
        }

        @Override
        public SerialBO[] newArray(int size) {
            return new SerialBO[size];
        }
    };
}
