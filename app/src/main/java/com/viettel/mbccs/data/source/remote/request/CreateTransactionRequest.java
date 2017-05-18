package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by FRAMGIA\hoang.van.cuong on 17/05/2017.
 */

public class CreateTransactionRequest extends BaseRequest<CreateTransactionRequest.Request> {

    class Request {

        @SerializedName(" ShopId ")
        @Expose
        private String shopId;
        @SerializedName(" StaffId ")
        @Expose
        private String staffId;
        @SerializedName(" List<StockSerial>")
        @Expose
        private String listStockSerial;
        @SerializedName(" Customer ")
        @Expose
        private String customer;
        @SerializedName(" PayMethod ")
        @Expose
        private String payMethod;
        @SerializedName(" IsdnPay ")
        @Expose
        private String isdnPay;
        @SerializedName(" TelecomserviceId ")
        @Expose
        private String telecomserviceId;
        @SerializedName(" SaleProgrameCode ")
        @Expose
        private String saleProgrameCode;
        @SerializedName(" SaleTransType ")
        @Expose
        private String saleTransType;
        @SerializedName(" ChannelId ")
        @Expose
        private String channelId;
        @SerializedName(" ChannelType ")
        @Expose
        private String channelType;
        @SerializedName(" PriceType ")
        @Expose
        private String priceType;
        @SerializedName(" PricePolicy ")
        @Expose
        private String pricePolicy;
        @SerializedName(" DiscountPolicy ")
        @Expose
        private String discountPolicy;

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getStaffId() {
            return staffId;
        }

        public void setStaffId(String staffId) {
            this.staffId = staffId;
        }

        public String getListStockSerial() {
            return listStockSerial;
        }

        public void setListStockSerial(String listStockSerial) {
            this.listStockSerial = listStockSerial;
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
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

        public String getTelecomserviceId() {
            return telecomserviceId;
        }

        public void setTelecomserviceId(String telecomserviceId) {
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

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getChannelType() {
            return channelType;
        }

        public void setChannelType(String channelType) {
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
    }
}
