package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.SaleOrdersDetail;
import java.util.List;

/**
 * Created by HuyQuyet on 5/31/17.
 */

public class CreateSaleTransFromOrderRequest extends BaseRequest {

    @SerializedName("shopId")
    @Expose
    private long shopId;

    @SerializedName("staffId")
    @Expose
    private long staffId;

    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("lstSerialSale")
    @Expose
    private List<SaleOrdersDetail> lstSerialSale;

    @SerializedName("payMethod")
    @Expose
    private String payMethod;

    @SerializedName("isdnPay")
    @Expose
    private String isdnPay;

    @SerializedName("telecomserviceId")
    @Expose
    private int telecomserviceId;

    @SerializedName("saleProgrameCode")
    @Expose
    private String saleProgrameCode;

    @SerializedName("saleTransType")
    @Expose
    private String saleTransType;

    @SerializedName("channelId")
    @Expose
    private int channelId;

    @SerializedName("channelType")
    @Expose
    private int channelType;

    @SerializedName("priceType")
    @Expose
    private String priceType;

    @SerializedName("pricePolicy")
    @Expose
    private String pricePolicy;

    @SerializedName("discountPolicy")
    @Expose
    private String discountPolicy;

    @SerializedName("saleOrderId")
    @Expose
    private String saleOrderId;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SaleOrdersDetail> getLstSerialSale() {
        return lstSerialSale;
    }

    public void setLstSerialSale(List<SaleOrdersDetail> lstSerialSale) {
        this.lstSerialSale = lstSerialSale;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getIsdnPay() {
        return isdnPay;
    }

    public void setIsdnPay(String isdnPay) {
        this.isdnPay = isdnPay;
    }

    public int getTelecomserviceId() {
        return telecomserviceId;
    }

    public void setTelecomserviceId(int telecomserviceId) {
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

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
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

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public CreateSaleTransFromOrderRequest() {
        super();
    }
}
