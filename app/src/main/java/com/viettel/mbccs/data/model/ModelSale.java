package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.utils.Common;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eo_cuong on 4/14/17.
 */

public class ModelSale implements Serializable, Parcelable {
    @SerializedName("checkSerial")
    @Expose
    private int checkSerial;

    @SerializedName("stockModelId")
    @Expose
    private long stockModelId;

    @SerializedName("stockModelCode")
    @Expose
    private String stockModelCode;

    @SerializedName("stockMoldeName")
    @Expose
    private String stockMoldeName;

    @SerializedName("quantity")
    @Expose
    private long quantity;

    @SerializedName("quantityIssue")
    @Expose
    private long quantityIssue;

    @SerializedName("pathImage1")
    @Expose
    private String pathImage1;

    @SerializedName("pathImage2")
    @Expose
    private String pathImage2;

    @SerializedName("pathImage3")
    @Expose
    private String pathImage3;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("vat")
    @Expose
    private double vat;

    @Expose
    private int choiceCount;
    @Expose
    private int serialCount;
    @Expose
    private List<SerialBO> mSerialBlocks = new ArrayList<>();
    @Expose
    private List<String> mSerials = new ArrayList<>();

    public ModelSale() {
    }

    protected ModelSale(Parcel in) {
        stockModelId = in.readLong();
        stockModelCode = in.readString();
        stockMoldeName = in.readString();
        quantity = in.readLong();
        quantityIssue = in.readLong();
        pathImage1 = in.readString();
        pathImage2 = in.readString();
        pathImage3 = in.readString();
        price = in.readDouble();
        vat = in.readDouble();
        choiceCount = in.readInt();
        serialCount = in.readInt();
        mSerialBlocks = in.createTypedArrayList(SerialBO.CREATOR);
        mSerials = in.createStringArrayList();
    }

    public static final Creator<ModelSale> CREATOR = new Creator<ModelSale>() {
        @Override
        public ModelSale createFromParcel(Parcel in) {
            return new ModelSale(in);
        }

        @Override
        public ModelSale[] newArray(int size) {
            return new ModelSale[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(stockModelId);
        dest.writeString(stockModelCode);
        dest.writeString(stockMoldeName);
        dest.writeLong(quantity);
        dest.writeLong(quantityIssue);
        dest.writeString(pathImage1);
        dest.writeString(pathImage2);
        dest.writeString(pathImage3);
        dest.writeDouble(price);
        dest.writeDouble(vat);
        dest.writeInt(choiceCount);
        dest.writeInt(serialCount);
        dest.writeTypedList(mSerialBlocks);
        dest.writeStringList(mSerials);
    }

    public List<SerialBO> getSerialBlocks() {
        if (mSerialBlocks.size() == 0) {
            if (mSerials.size() > 0) {
                return Common.getSerialBlockBySerials(mSerials, this);
            }
        }
        return mSerialBlocks;
    }

    public void setSerialBlocks(List<SerialBO> serialBlocks) {

        mSerialBlocks = serialBlocks;
    }

    public void decreaseSerial() {

        if (mSerials != null && mSerials.size() > 0) {
            List<String> fake = new ArrayList<>();
            for (int i = 0; i < mSerials.size() - 1; i++) {
                fake.add(mSerials.get(i));
            }
            mSerials.clear();
            mSerials.addAll(fake);
        }
    }

    public int getSerialCount() {
        if (getSerialBlocks() == null) {
            return 0;
        }
        return Common.getSerialCountByListSerialBlock(getSerialBlocks());
    }

    public void setSerialCount(int serialCount) {
        this.serialCount = serialCount;
    }

    public int getChoiceCount() {
        return choiceCount;
    }

    public void setChoiceCount(int choiceCount) {
        this.choiceCount = choiceCount;
    }

    public String getPathImage1() {
        return pathImage1;
    }

    public void setPathImage1(String pathImage1) {
        this.pathImage1 = pathImage1;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceString() {
        return price + " VNĐ";
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getSerials() {
        return mSerials;
    }

    public void setSerials(List<String> serials) {
        mSerials = serials;
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

    public String getPathImage2() {
        return pathImage2;
    }

    public void setPathImage2(String pathImage2) {
        this.pathImage2 = pathImage2;
    }

    public String getPathImage3() {
        return pathImage3;
    }

    public void setPathImage3(String pathImage3) {
        this.pathImage3 = pathImage3;
    }

    public long getQuantityIssue() {
        return quantityIssue;
    }

    public void setQuantityIssue(long quantityIssue) {
        this.quantityIssue = quantityIssue;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public int getCheckSerial() {
        return checkSerial;
    }

    public void setCheckSerial(int checkSerial) {
        this.checkSerial = checkSerial;
    }
}
