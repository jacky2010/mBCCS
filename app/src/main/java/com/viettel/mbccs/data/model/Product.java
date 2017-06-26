package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 6/24/17.
 */

public class Product implements Parcelable {
    @Expose
    @SerializedName("offerName")
    private String offerName;

    @Expose
    @SerializedName("offerTypeId")
    private int offerTypeId;

    @Expose
    @SerializedName("productId")
    private int productId;

    @Expose
    @SerializedName("offerCode")
    private String offerCode;

    @Expose
    @SerializedName("offerId")
    private String offerId;

    @Expose
    @SerializedName("startDate")
    private String startDate;

    @Expose
    @SerializedName("applyCondition")
    private String applyCondition;

    @Expose
    @SerializedName("createDate")
    private String createDate;

    @Expose
    @SerializedName("status")
    private int status;

    public Product() {
    }

    public Product(String offerName, int offerTypeId, int productId, String offerCode,
            String offerId, String startDate, String applyCondition, String createDate,
            int status) {
        this.offerName = offerName;
        this.offerTypeId = offerTypeId;
        this.productId = productId;
        this.offerCode = offerCode;
        this.offerId = offerId;
        this.startDate = startDate;
        this.applyCondition = applyCondition;
        this.createDate = createDate;
        this.status = status;
    }

    protected Product(Parcel in) {
        offerName = in.readString();
        offerTypeId = in.readInt();
        productId = in.readInt();
        offerCode = in.readString();
        offerId = in.readString();
        startDate = in.readString();
        applyCondition = in.readString();
        createDate = in.readString();
        status = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(offerName);
        dest.writeInt(offerTypeId);
        dest.writeInt(productId);
        dest.writeString(offerCode);
        dest.writeString(offerId);
        dest.writeString(startDate);
        dest.writeString(applyCondition);
        dest.writeString(createDate);
        dest.writeInt(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public int getOfferTypeId() {
        return offerTypeId;
    }

    public void setOfferTypeId(int offerTypeId) {
        this.offerTypeId = offerTypeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return offerName;
    }
}
