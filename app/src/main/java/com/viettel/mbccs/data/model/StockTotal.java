package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.utils.Common;
import java.util.ArrayList;
import java.util.List;

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
    @SerializedName("pathImage1")
    private String pathImage1;

    @Expose
    @SerializedName("pathImage2")
    private String pathImage2;

    @SerializedName("checkSerial")
    @Expose
    private double checkSerial;

    @Expose
    @SerializedName("pathImage3")
    private String pathImage3;

    @Expose
    private StockSerial mStockSerial;

    @Expose
    private List<String> mSerials = new ArrayList<>();

    private List<SerialBO> mSerialBlocks = new ArrayList<>();

    private int countChoice;

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
            if (checkSerial==1){
                mStockSerial.setQuantity(Common.getSerialCountByListSerialBlock(getSerialBlocks()));
                mStockSerial.setSerialBOs(getSerialBlocks());
            }else{
                mStockSerial.setQuantity(countChoice);
            }
            mStockSerial.setStockModelId(stockModelId);
            mStockSerial.setStockModelCode(stockModelCode);
            mStockSerial.setStockModelName(stockModelName);
        }
        return mStockSerial;
    }

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

    public StockTotal(long ownerId, long ownerType, long stockModelId, String stockModelCode,
                      String stockModelName, long stockTypeId, String stockTypeName, long quantity,
                      long quantityIssue, long stateId, String stateName, int countChoice) {
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.stockModelId = stockModelId;
        this.stockModelCode = stockModelCode;
        this.stockModelName = stockModelName;
        this.stockTypeId = stockTypeId;
        this.stockTypeName = stockTypeName;
        this.quantity = quantity;
        this.quantityIssue = quantityIssue;
        this.stateId = stateId;
        this.stateName = stateName;
        this.countChoice = countChoice;
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

    public String getPathImage1() {
        return pathImage1;
    }

    public void setPathImage1(String pathImage1) {
        this.pathImage1 = pathImage1;
    }

    public String getPathImage3() {
        return pathImage3;
    }

    public void setPathImage3(String pathImage3) {
        this.pathImage3 = pathImage3;
    }

    public String getPathImage2() {
        return pathImage2;
    }

    public void setPathImage2(String pathImage2) {
        this.pathImage2 = pathImage2;
    }

    public double getCheckSerial() {
        return checkSerial;
    }

    public void setCheckSerial(double checkSerial) {
        this.checkSerial = checkSerial;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.stockModelId == ((StockTotal) obj).getStockModelId()
                && this.stockModelCode.equals(((StockTotal) obj).getStockModelCode()));
    }

    public String getTotalMoney() {
        return Common.formatDouble(getCountChoice() * price);
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
        dest.writeString(this.pathImage1);
        dest.writeString(this.pathImage2);
        dest.writeDouble(this.checkSerial);
        dest.writeString(this.pathImage3);
        dest.writeParcelable(this.mStockSerial, flags);
        dest.writeStringList(this.mSerials);
        dest.writeTypedList(this.mSerialBlocks);
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
        this.pathImage1 = in.readString();
        this.pathImage2 = in.readString();
        this.checkSerial = in.readDouble();
        this.pathImage3 = in.readString();
        this.mStockSerial = in.readParcelable(StockSerial.class.getClassLoader());
        this.mSerials = in.createStringArrayList();
        this.mSerialBlocks = in.createTypedArrayList(SerialBO.CREATOR);
        this.countChoice = in.readInt();
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
