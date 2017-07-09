package com.viettel.mbccs.data.source.remote.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.constance.PaymentMethod;
import com.viettel.mbccs.data.model.Customer;
import com.viettel.mbccs.data.model.StockSerial;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/19/17.
 */

public class GetInfoSaleTranRequest extends BaseRequest implements Parcelable {

    @SerializedName("shopId")
    @Expose
    private Long shopId;

    @SerializedName("staffId")
    @Expose
    private Long staffId;

    @SerializedName("lstSerialSale")
    @Expose
    private List<StockSerial> lstSerialSale;

    @SerializedName("customer")
    @Expose
    private Customer mCustomer;

    @PaymentMethod
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
    private Long chanelId;

    @SerializedName("channelType")
    @Expose
    private Long channelType;

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

    @SerializedName("orderId")
    @Expose
    private long orderId;


    public Long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public Long getStaffId() {
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

    public Customer getCustomer() {
        return mCustomer;
    }

    public void setCustomer(Customer customer) {
        mCustomer = customer;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.shopId);
        dest.writeLong(this.staffId);
        dest.writeList(this.lstSerialSale);
        dest.writeParcelable(this.mCustomer, flags);
        dest.writeString(this.paymentMethod);
        dest.writeString(this.IsdnPay);
        dest.writeLong(this.telecomserviceId);
        dest.writeString(this.saleProgrameCode);
        dest.writeString(this.saleTransType);
        dest.writeLong(this.chanelId);
        dest.writeLong(this.channelType);
        dest.writeString(this.priceType);
        dest.writeString(this.pricePolicy);
        dest.writeString(this.discountPolicy);
        dest.writeString(this.couponCode);
        dest.writeLong(this.orderId);
    }

    public GetInfoSaleTranRequest() {
    }

    protected GetInfoSaleTranRequest(Parcel in) {
        this.shopId = in.readLong();
        this.staffId = in.readLong();
        this.lstSerialSale = new ArrayList<StockSerial>();
        in.readList(this.lstSerialSale, StockSerial.class.getClassLoader());
        this.mCustomer = in.readParcelable(Customer.class.getClassLoader());
        this.paymentMethod = in.readString();
        this.IsdnPay = in.readString();
        this.telecomserviceId = in.readLong();
        this.saleProgrameCode = in.readString();
        this.saleTransType = in.readString();
        this.chanelId = in.readLong();
        this.channelType = in.readLong();
        this.priceType = in.readString();
        this.pricePolicy = in.readString();
        this.discountPolicy = in.readString();
        this.couponCode = in.readString();
        this.orderId = in.readLong();
    }

    public static final Creator<GetInfoSaleTranRequest> CREATOR =
            new Creator<GetInfoSaleTranRequest>() {
                @Override
                public GetInfoSaleTranRequest createFromParcel(Parcel source) {
                    return new GetInfoSaleTranRequest(source);
                }

                @Override
                public GetInfoSaleTranRequest[] newArray(int size) {
                    return new GetInfoSaleTranRequest[size];
                }
            };

    public CreateSaleTransChannelRequest clone() {
        CreateSaleTransChannelRequest request = new CreateSaleTransChannelRequest();
        request.setChanelId(chanelId);
        request.setChannelType(channelType);
        request.setCouponCode(couponCode);
        request.setCustomer(mCustomer);
        request.setDiscountPolicy(discountPolicy);
        request.setIsdnPay(IsdnPay);
        request.setLstSerialSale(lstSerialSale);
        request.setPaymentMethod(paymentMethod);
        request.setPricePolicy(pricePolicy);
        request.setPriceType(priceType);
        request.setStaffId(staffId);
        request.setShopId(shopId);
        request.setSaleProgrameCode(saleProgrameCode);
        request.setSaleTransType(saleTransType);
        if (telecomserviceId != null) {
            request.setTelecomserviceId(telecomserviceId);
        }
        request.setOrderId(orderId);
        return request;
    }
}
