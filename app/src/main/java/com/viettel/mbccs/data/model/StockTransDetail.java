package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.utils.Common;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 6/21/17.
 */

public class StockTransDetail implements Parcelable {
    @SerializedName("stockTransId")
    @Expose
    private long stockTransId;

    @SerializedName("stockTransDetailId")
    @Expose
    private long stockTransDetailId;

    @SerializedName("stockModelId")
    @Expose
    private long stockModelId;

    @SerializedName("stockModelCode")
    @Expose
    private String stockModelCode;

    @SerializedName("stockModelName")
    @Expose
    private String stockModelName;

    @SerializedName("quantity")
    @Expose
    private long quantity;

    @SerializedName("stateId")
    @Expose
    private long stateId;

    @SerializedName("stateName")
    @Expose
    private String stateName;

    @Expose
    private StockSerial mStockSerial;

    @Expose
    private List<String> mSerials = new ArrayList<>();

    private List<SerialBO> mSerialBlocks = new ArrayList<>();

    public List<SerialBO> getSerialBlocks() {
        if (mSerialBlocks.size() == 0) {
            if (mSerials.size() > 0) {
                return Common.getSerialBlockBySerials(mSerials, stockModelId);
            }
        }
        return mSerialBlocks;
    }

    public int getSerialCount() {
        if (getSerialBlocks() == null) {
            return 0;
        }
        return Common.getSerialCountByListSerialBlock(getSerialBlocks());
    }

    public boolean isPickSerialOk() {
        return getSerialCount() == quantity;
    }

    public List<String> getSerials() {
        return mSerials;
    }

    public void setSerials(List<String> serials) {
        mSerials = serials;
    }

    public StockSerial getStockSerial() {
        if (mStockSerial == null) {
            mStockSerial = new StockSerial();
            mStockSerial.setQuantity(quantity);
            mStockSerial.setSerialBOs(getSerialBlocks());
            mStockSerial.setStockModelId(stockModelId);
            mStockSerial.setStockModelCode(stockModelCode);
            mStockSerial.setStockMoldeName(stockModelName);
        }
        return mStockSerial;
    }

    public void setStockSerial(StockSerial stockSerial) {
        mStockSerial = stockSerial;
    }

    public long getStockTransId() {
        return stockTransId;
    }

    public void setStockTransId(long stockTransId) {
        this.stockTransId = stockTransId;
    }

    public long getStockTransDetailId() {
        return stockTransDetailId;
    }

    public void setStockTransDetailId(long stockTransDetailId) {
        this.stockTransDetailId = stockTransDetailId;
    }

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

    public String getStockModelName() {
        return stockModelName;
    }

    public void setStockModelName(String stockModelName) {
        this.stockModelName = stockModelName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public StockTransDetail() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.stockTransId);
        dest.writeLong(this.stockTransDetailId);
        dest.writeLong(this.stockModelId);
        dest.writeString(this.stockModelCode);
        dest.writeString(this.stockModelName);
        dest.writeLong(this.quantity);
        dest.writeLong(this.stateId);
        dest.writeString(this.stateName);
        dest.writeSerializable(this.mStockSerial);
        dest.writeStringList(this.mSerials);
        dest.writeTypedList(this.mSerialBlocks);
    }

    protected StockTransDetail(Parcel in) {
        this.stockTransId = in.readLong();
        this.stockTransDetailId = in.readLong();
        this.stockModelId = in.readLong();
        this.stockModelCode = in.readString();
        this.stockModelName = in.readString();
        this.quantity = in.readLong();
        this.stateId = in.readLong();
        this.stateName = in.readString();
        this.mStockSerial = (StockSerial) in.readSerializable();
        this.mSerials = in.createStringArrayList();
        this.mSerialBlocks = in.createTypedArrayList(SerialBO.CREATOR);
    }

    public static final Creator<StockTransDetail> CREATOR = new Creator<StockTransDetail>() {
        @Override
        public StockTransDetail createFromParcel(Parcel source) {
            return new StockTransDetail(source);
        }

        @Override
        public StockTransDetail[] newArray(int size) {
            return new StockTransDetail[size];
        }
    };
}
