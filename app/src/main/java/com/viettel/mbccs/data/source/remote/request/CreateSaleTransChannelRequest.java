package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.StockSerial;
import java.util.List;

/**
 * Created by eo_cuong on 5/28/17.
 */

public class CreateSaleTransChannelRequest extends DataRequest {

    @SerializedName("shopId")
    @Expose
    private long shopId;

    @SerializedName("staffId")
    @Expose
    private long staffId;

    @SerializedName("lstSerialSale")
    @Expose
    private List<StockSerial> lstSerialSale;

    @SerializedName("customer")
    @Expose
    private Customer mCustomer;

    @SerializedName("payMethod")
    @Expose
    private String paymentMethod;

    @SerializedName("isdnPay")
    @Expose
    private String IsdnPay;

    @SerializedName("telecomserviceId")
    @Expose
    private Long telecomserviceId;

    @SerializedName("saleProgrameCode")
    @Expose
    private String saleProgrameCode;

    @SerializedName("saleTransType")
    @Expose
    private String saleTransType;

    @SerializedName("channelId")
    @Expose
    private long chanelId;

    @SerializedName("channelType")
    @Expose
    private long channelType;

    @SerializedName("priceType")
    @Expose
    private String priceType;

    @SerializedName("pricePolicy")
    @Expose
    private String pricePolicy;

    @SerializedName("discountPolicy")
    @Expose
    private String discountPolicy;

    @SerializedName("couponCode")
    @Expose
    private String couponCode;

    @SerializedName("language")
    @Expose
    private String language;

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public List<StockSerial> getLstSerialSale() {
        return lstSerialSale;
    }

    public void setLstSerialSale(List<StockSerial> lstSerialSale) {
        this.lstSerialSale = lstSerialSale;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getIsdnPay() {
        return IsdnPay;
    }

    public void setIsdnPay(String isdnPay) {
        IsdnPay = isdnPay;
    }

    public long getTelecomserviceId() {
        return telecomserviceId;
    }

    public void setTelecomserviceId(long telecomserviceId) {
        this.telecomserviceId = telecomserviceId;
    }

    public String getSaleProgrameCode() {
        return saleProgrameCode;
    }

    public void setSaleProgrameCode(String saleProgrameCode) {
        this.saleProgrameCode = saleProgrameCode;
    }

    public String getSaleTransType() {
        return saleTransType;
    }

    public void setSaleTransType(String saleTransType) {
        this.saleTransType = saleTransType;
    }

    public long getChanelId() {
        return chanelId;
    }

    public void setChanelId(long chanelId) {
        this.chanelId = chanelId;
    }

    public long getChannelType() {
        return channelType;
    }

    public void setChannelType(long channelType) {
        this.channelType = channelType;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPricePolicy() {
        return pricePolicy;
    }

    public void setPricePolicy(String pricePolicy) {
        this.pricePolicy = pricePolicy;
    }

    public String getDiscountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(String discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Customer getCustomer() {
        return mCustomer;
    }

    public void setCustomer(Customer customer) {
        mCustomer = customer;
    }

}
