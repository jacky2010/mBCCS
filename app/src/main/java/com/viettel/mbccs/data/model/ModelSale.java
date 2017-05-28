package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.utils.Common;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 4/14/17.
 */

public class ModelSale implements Serializable {

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

    @SerializedName("path_image1")
    @Expose
    private String pathImage1;

    @SerializedName("path_image2")
    @Expose
    private String pathImage2;

    @SerializedName("path_image3")
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

    public List<SerialBO> getSerialBlocks() {
        if (mSerialBlocks.size() == 0) {
            if (mSerials.size() > 0) {
                return Common.getSerialBlockBySerials(mSerials);
            }
        }
        return mSerialBlocks;
    }

    public void setSerialBlocks(List<SerialBO> serialBlocks) {

        mSerialBlocks = serialBlocks;
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
}
