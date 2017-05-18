package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class StockSerial implements Parcelable {

    @SerializedName("stockModelId")
    @Expose
    private long stockModelId;

    @SerializedName("StockModelCode")
    @Expose
    private String stockModelCode;

    @SerializedName("StockMoldeName")
    @Expose
    private String stockMoldeName;

    @SerializedName("Quantity")
    @Expose
    private long quantity;

    @SerializedName("lstSerial")
    @Expose
    private List<SerialBO> mSerialBOs;

    public long getStockModelId() {
        return stockModelId;
    }

    public void setStockModelId(long stockModelId) {
        this.stockModelId = stockModelId;
    }

    public String getStockModelCode() {
        return stockModelCode;
    }

    public void setStockModelCode(String stockModelCode) {
        this.stockModelCode = stockModelCode;
    }

    public String getStockMoldeName() {
        return stockMoldeName;
    }

    public void setStockMoldeName(String stockMoldeName) {
        this.stockMoldeName = stockMoldeName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public List<SerialBO> getSerialBOs() {
        return mSerialBOs;
    }

    public void setSerialBOs(List<SerialBO> serialBOs) {
        mSerialBOs = serialBOs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.stockModelId);
        dest.writeString(this.stockModelCode);
        dest.writeString(this.stockMoldeName);
        dest.writeLong(this.quantity);
        dest.writeTypedList(this.mSerialBOs);
    }

    public StockSerial() {
    }

    protected StockSerial(Parcel in) {
        this.stockModelId = in.readLong();
        this.stockModelCode = in.readString();
        this.stockMoldeName = in.readString();
        this.quantity = in.readLong();
        this.mSerialBOs = in.createTypedArrayList(SerialBO.CREATOR);
    }

    public static final Creator<StockSerial> CREATOR = new Creator<StockSerial>() {
        @Override
        public StockSerial createFromParcel(Parcel source) {
            return new StockSerial(source);
        }

        @Override
        public StockSerial[] newArray(int size) {
            return new StockSerial[size];
        }
    };
}
