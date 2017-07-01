package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class SaleOrdersDetail implements Parcelable {

    @SerializedName("saleOrdersId")
    @Expose
    private long saleOrdersId;

    @SerializedName("saleOrdersDetailId")
    @Expose
    private long saleOrdersDetailId;

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

    @SerializedName("quantityIssue")
    @Expose
    private long quantityIssue;

    @SerializedName("checkSerial")
    @Expose
    private long checkSerial;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("lstSerial")
    @Expose
    private List<SerialBO> lstSerial;

    private int select;

    public SaleOrdersDetail() {
    }

    protected SaleOrdersDetail(Parcel in) {
        this();
        saleOrdersId = in.readLong();
        saleOrdersDetailId = in.readLong();
        stockModelId = in.readLong();
        stockModelCode = in.readString();
        stockModelName = in.readString();
        quantity = in.readLong();
        lstSerial = in.createTypedArrayList(SerialBO.CREATOR);
        select = in.readInt();
        price = in.readDouble();
    }

    public static final Creator<SaleOrdersDetail> CREATOR = new Creator<SaleOrdersDetail>() {
        @Override
        public SaleOrdersDetail createFromParcel(Parcel in) {
            return new SaleOrdersDetail(in);
        }

        @Override
        public SaleOrdersDetail[] newArray(int size) {
            return new SaleOrdersDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(saleOrdersId);
        dest.writeLong(saleOrdersDetailId);
        dest.writeLong(stockModelId);
        dest.writeString(stockModelCode);
        dest.writeString(stockModelName);
        dest.writeLong(quantity);
        dest.writeTypedList(lstSerial);
        dest.writeInt(select);
        dest.writeDouble(price);
    }

    public long getSaleOrdersId() {
        return saleOrdersId;
    }

    public void setSaleOrdersId(long saleOrdersId) {
        this.saleOrdersId = saleOrdersId;
    }

    public long getSaleOrdersDetailId() {
        return saleOrdersDetailId;
    }

    public void setSaleOrdersDetailId(long saleOrdersDetailId) {
        this.saleOrdersDetailId = saleOrdersDetailId;
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

    public long getQuantityIssue() {
        return quantityIssue;
    }

    public void setQuantityIssue(long quantityIssue) {
        this.quantityIssue = quantityIssue;
    }

    public long getCheckSerial() {
        return checkSerial;
    }

    public void setCheckSerial(long checkSerial) {
        this.checkSerial = checkSerial;
    }

    public List<SerialBO> getLstSerial() {
        return lstSerial;
    }

    public void setLstSerial(List<SerialBO> lstSerial) {
        this.lstSerial = lstSerial;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
