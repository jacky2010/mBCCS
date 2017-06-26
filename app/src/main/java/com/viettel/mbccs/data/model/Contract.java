package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringDef;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by HuyQuyet on 6/2/17.
 */

public class Contract implements Parcelable {
    @StringDef({ TypeNoticeChange.AT_HOME, TypeNoticeChange.SMS, TypeNoticeChange.EMAIL })
    public @interface TypeNoticeChange {
        String SMS = "2";
        String EMAIL = "3";
        String AT_HOME = "1";
    }

    @SerializedName("contractId")
    @Expose
    private String contractId;

    @SerializedName("contractNo")
    @Expose
    private String contractNo;

    @SerializedName("custId")
    @Expose
    private String custId;

    @SerializedName("subId")
    @Expose
    private String subId;

    @SerializedName("isdn")
    @Expose
    private String isdn;

    @SerializedName("numOfSubscribers")
    @Expose
    private String numOfSubscribers;

    @SerializedName("dateCreate")
    @Expose
    private String dateCreate;

    @SerializedName("effectDate")
    @Expose
    private String effectDate;

    @SerializedName("signDate")
    @Expose
    private String signDate;

    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;

    @SerializedName("noticeCharge")
    @Expose
    private List<String> noticeCharge;

    @SerializedName("payMethodCode")
    @Expose
    private String payMethodCode;

    @SerializedName("printMethodCode")
    @Expose
    private String printMethodCode;

    @SerializedName("payAreaCode")
    @Expose
    private String payAreaCode;

    @SerializedName("payer")
    @Expose
    private String payer;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("home")
    @Expose
    private String home;

    @SerializedName("streetBlockName")
    @Expose
    private String streetBlockName;

    @SerializedName("streetName")
    @Expose
    private String streetName;

    @SerializedName("province")
    @Expose
    private String province;

    @SerializedName("district")
    @Expose
    private String district;

    @SerializedName("precinct")
    @Expose
    private String precinct;

    @SerializedName("billCycleFrom")
    @Expose
    private String billCycleFrom;

    @SerializedName("serviceTypes")
    @Expose
    private String serviceTypes;

    @SerializedName("userCreate")
    @Expose
    private String userCreate;

    @SerializedName("status")
    @Expose
    private String status;

    public Contract() {
    }
    protected Contract(Parcel in) {
        contractId = in.readString();
        contractNo = in.readString();
        custId = in.readString();
        subId = in.readString();
        isdn = in.readString();
        numOfSubscribers = in.readString();
        dateCreate = in.readString();
        effectDate = in.readString();
        signDate = in.readString();
        accountNumber = in.readString();
        noticeCharge = in.createStringArrayList();
        payMethodCode = in.readString();
        printMethodCode = in.readString();
        payAreaCode = in.readString();
        payer = in.readString();
        address = in.readString();
        home = in.readString();
        streetBlockName = in.readString();
        streetName = in.readString();
        province = in.readString();
        district = in.readString();
        precinct = in.readString();
        billCycleFrom = in.readString();
        serviceTypes = in.readString();
        userCreate = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contractId);
        dest.writeString(contractNo);
        dest.writeString(custId);
        dest.writeString(subId);
        dest.writeString(isdn);
        dest.writeString(numOfSubscribers);
        dest.writeString(dateCreate);
        dest.writeString(effectDate);
        dest.writeString(signDate);
        dest.writeString(accountNumber);
        dest.writeStringList(noticeCharge);
        dest.writeString(payMethodCode);
        dest.writeString(printMethodCode);
        dest.writeString(payAreaCode);
        dest.writeString(payer);
        dest.writeString(address);
        dest.writeString(home);
        dest.writeString(streetBlockName);
        dest.writeString(streetName);
        dest.writeString(province);
        dest.writeString(district);
        dest.writeString(precinct);
        dest.writeString(billCycleFrom);
        dest.writeString(serviceTypes);
        dest.writeString(userCreate);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contract> CREATOR = new Creator<Contract>() {
        @Override
        public Contract createFromParcel(Parcel in) {
            return new Contract(in);
        }

        @Override
        public Contract[] newArray(int size) {
            return new Contract[size];
        }
    };

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getNumOfSubscribers() {
        return numOfSubscribers;
    }

    public void setNumOfSubscribers(String numOfSubscribers) {
        this.numOfSubscribers = numOfSubscribers;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<String> getNoticeCharge() {
        return noticeCharge;
    }

    public void setNoticeCharge(List<String> noticeCharge) {
        this.noticeCharge = noticeCharge;
    }

    public String getPayMethodCode() {
        return payMethodCode;
    }

    public void setPayMethodCode(String payMethodCode) {
        this.payMethodCode = payMethodCode;
    }

    public String getPrintMethodCode() {
        return printMethodCode;
    }

    public void setPrintMethodCode(String printMethodCode) {
        this.printMethodCode = printMethodCode;
    }

    public String getPayAreaCode() {
        return payAreaCode;
    }

    public void setPayAreaCode(String payAreaCode) {
        this.payAreaCode = payAreaCode;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getStreetBlockName() {
        return streetBlockName;
    }

    public void setStreetBlockName(String streetBlockName) {
        this.streetBlockName = streetBlockName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPrecinct() {
        return precinct;
    }

    public void setPrecinct(String precinct) {
        this.precinct = precinct;
    }

    public String getBillCycleFrom() {
        return billCycleFrom;
    }

    public void setBillCycleFrom(String billCycleFrom) {
        this.billCycleFrom = billCycleFrom;
    }

    public String getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(String serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
