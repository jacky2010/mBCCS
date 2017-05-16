package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/17/17.
 */

public class SaleOrdersDetail implements Parcelable {

    @SerializedName("SaleOrdersId")
    @Expose
    private long saleOrdersId;

    @SerializedName("SaleOrdersDetailId")
    @Expose
    private long saleOrdersDetailId;

    @SerializedName("StockModelId")
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

    @SerializedName("Price")
    @Expose
    private double price;

    @SerializedName("VAT")
    @Expose
    private double vat;

    @SerializedName("lstSerial")
    @Expose
    private List<StockSerial> lstSerial;

    public SaleOrdersDetail() {
    }

    protected SaleOrdersDetail(Parcel in) {
        lstSerial = new ArrayList<>();

        saleOrdersId = in.readLong();
        saleOrdersDetailId = in.readLong();
        stockModelId = in.readLong();
        stockModelCode = in.readString();
        stockMoldeName = in.readString();
        quantity = in.readLong();
        price = in.readDouble();
        vat = in.readDouble();
        in.readList(lstSerial, SaleOrdersDetail.class.getClassLoader());
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
        dest.writeString(stockMoldeName);
        dest.writeLong(quantity);
        dest.writeDouble(price);
        dest.writeDouble(vat);
        dest.writeList(lstSerial);
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public List<StockSerial> getLstSerial() {
        return lstSerial;
    }

    public void setLstSerial(List<StockSerial> lstSerial) {
        this.lstSerial = lstSerial;
    }
}
