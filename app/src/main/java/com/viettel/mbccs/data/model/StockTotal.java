package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.utils.Common;

/**
 * Created by HuyQuyet on 5/21/17.
 */

public class StockTotal implements Parcelable {
    @Expose
    @SerializedName("ownerId")
    private long ownerId;

    @Expose
    @SerializedName("ownerType")
    private long ownerType;

    @Expose
    @SerializedName("stockModelId")
    private long stockModelId;

    @Expose
    @SerializedName("stockModelCode")
    private String stockModelCode;

    @Expose
    @SerializedName("stockModelName")
    private String stockModelName;

    @Expose
    @SerializedName("stockTypeId")
    private long stockTypeId;

    @Expose
    @SerializedName("stockTypeName")
    private String stockTypeName;

    @Expose
    @SerializedName("quantity")
    private long quantity;

    @Expose
    @SerializedName("quantityIssue")
    private long quantityIssue;

    @Expose
    @SerializedName("stateId")
    private long stateId;

    @Expose
    @SerializedName("stateName")
    private String stateName;

    @Expose
    @SerializedName("price")
    private float price;

    @Expose
    @SerializedName("image")
    private String image;

    private int countChoice;

    public void addChoice() {
        if (countChoice < quantity) {
            countChoice++;
        }
    }

    public void subtract() {
        if (countChoice > 0) {
            countChoice--;
        }
    }

    public int getCountChoice() {
        return countChoice;
    }

    public void setCountChoice(int countChoice) {
        this.countChoice = countChoice;
    }

    public StockTotal() {
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(long ownerType) {
        this.ownerType = ownerType;
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

    public long getStockTypeId() {
        return stockTypeId;
    }

    public void setStockTypeId(long stockTypeId) {
        this.stockTypeId = stockTypeId;
    }

    public String getStockTypeName() {
        return stockTypeName;
    }

    public void setStockTypeName(String stockTypeName) {
        this.stockTypeName = stockTypeName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getQuantityIssue() {
        return quantityIssue;
    }

    public void setQuantityIssue(long quantityIssue) {
        this.quantityIssue = quantityIssue;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.stockModelId == ((StockTotal) obj).getStockModelId()
                && this.stockModelCode.equals(((StockTotal) obj).getStockModelCode()));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.ownerId);
        dest.writeLong(this.ownerType);
        dest.writeLong(this.stockModelId);
        dest.writeString(this.stockModelCode);
        dest.writeString(this.stockModelName);
        dest.writeLong(this.stockTypeId);
        dest.writeString(this.stockTypeName);
        dest.writeLong(this.quantity);
        dest.writeLong(this.quantityIssue);
        dest.writeLong(this.stateId);
        dest.writeString(this.stateName);
        dest.writeFloat(this.price);
        dest.writeString(this.image);
        dest.writeInt(this.countChoice);
    }

    protected StockTotal(Parcel in) {
        this.ownerId = in.readLong();
        this.ownerType = in.readLong();
        this.stockModelId = in.readLong();
        this.stockModelCode = in.readString();
        this.stockModelName = in.readString();
        this.stockTypeId = in.readLong();
        this.stockTypeName = in.readString();
        this.quantity = in.readLong();
        this.quantityIssue = in.readLong();
        this.stateId = in.readLong();
        this.stateName = in.readString();
        this.price = in.readFloat();
        this.image = in.readString();
        this.countChoice = in.readInt();
    }

    public String getTotalMoney() {
        return Common.formatDouble(getCountChoice() * price);
    }

    public static final Creator<StockTotal> CREATOR = new Creator<StockTotal>() {
        @Override
        public StockTotal createFromParcel(Parcel source) {
            return new StockTotal(source);
        }

        @Override
        public StockTotal[] newArray(int size) {
            return new StockTotal[size];
        }
    };
}
