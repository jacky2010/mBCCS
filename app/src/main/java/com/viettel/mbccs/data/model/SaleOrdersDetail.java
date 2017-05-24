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
    private List<SerialBO> lstSerial;

    // TODO: 5/24/17 data fake

    private int count;
    private String imageUrl;
    private int select;

    public SaleOrdersDetail() {
    }

    protected SaleOrdersDetail(Parcel in) {
        saleOrdersId = in.readLong();
        saleOrdersDetailId = in.readLong();
        stockModelId = in.readLong();
        stockModelCode = in.readString();
        stockMoldeName = in.readString();
        quantity = in.readLong();
        price = in.readDouble();
        vat = in.readDouble();
        lstSerial = in.createTypedArrayList(SerialBO.CREATOR);
        count = in.readInt();
        imageUrl = in.readString();
        select = in.readInt();
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
        dest.writeTypedList(lstSerial);
        dest.writeInt(count);
        dest.writeString(imageUrl);
        dest.writeInt(select);
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

    public List<SerialBO> getLstSerial() {
        return lstSerial;
    }

    public void setLstSerial(List<SerialBO> lstSerial) {
        this.lstSerial = lstSerial;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }
}
