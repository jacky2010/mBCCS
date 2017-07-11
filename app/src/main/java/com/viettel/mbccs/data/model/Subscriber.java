package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/31/17.
 */

public class Subscriber implements Parcelable {

    // Id thuê bao
    @SerializedName("subID")
    @Expose
    private Long subId;

    // custReqId
    @SerializedName("custReqId")
    @Expose
    private Integer custReqId;

    // Id hợp đồng
    @SerializedName("contractId")
    @Expose
    private Integer contractId;

    // loại thuê bao
    @SerializedName("subType")
    @Expose
    private String subType;

    // Id lí do hòa mạng
    @SerializedName("regReasonId")
    @Expose
    private Integer regReasonId;

    // Id lí do kết thúc
    @SerializedName("finishReasonId")
    @Expose
    private Integer finishReasonId;

    // mã cửa hàng
    @SerializedName("shopCode")
    @Expose
    private String shopCode;

    // thời gian kết thúc
    @SerializedName("endDatetime")
    @Expose
    private String endDatetime;

    // thời gian bắt đầu
    @SerializedName("staDatetime")
    @Expose
    private String staDatetime;

    // trạng thái
    @SerializedName("actStatus")
    @Expose
    private String actStatus;

    // vip
    @SerializedName("vip")
    @Expose
    private String vip;

    // Ngày tạo
    @SerializedName("createDate")
    @Expose
    private String createDate;

    // Ngày user tạo
    @SerializedName("userCreated")
    @Expose
    private String userCreated;

    // trạng thái
    @SerializedName("status")
    @Expose
    private Integer status;

    // tiền cọc
    @SerializedName("deposit")
    @Expose
    private String deposit;

    // Địa chỉ
    @SerializedName("address")
    @Expose
    private String address;

    // thuê bao
    @SerializedName("isdn")
    @Expose
    private String isdn;

    // số sim
    @SerializedName("imsi")
    @Expose
    private String imsi;

    // serial
    @SerializedName("serial")
    @Expose
    private String serial;

    // data
    @SerializedName("data")
    @Expose
    private String data;

    // địa chỉ cửa hàng
    @SerializedName("addressCode")
    @Expose
    private String addressCode;

    // Hạn mức
    @SerializedName("quota")
    @Expose
    private Long quota;

    // Mã chương trình khuyến mãi
    @SerializedName("promotionCode")
    @Expose
    private String promotionCode;

    // loại hòa mạng
    @SerializedName("regType")
    @Expose
    private String regType;

    // id lí do đặt cọc
    @SerializedName("reasonDepositId")
    @Expose
    private Integer reasonDepositId;

    // userUsing
    @SerializedName("userUsing")
    @Expose
    private String userUsing;

    // userTitle
    @SerializedName("userTitle")
    @Expose
    private String userTitle;

    // Id cán bộ
    @SerializedName("staffId")
    @Expose
    private Integer staffId;

    // password
    @SerializedName("password")
    @Expose
    private String password;

    // pass aTM
    @SerializedName("passwordAtm")
    @Expose
    private String passwordAtm;

    // Ngày sinh
    @SerializedName("birthDate")
    @Expose
    private String birthDate;

    // quốc gia
    @SerializedName("nationality")
    @Expose
    private String nationality;

    // giới tính
    @SerializedName("sex")
    @Expose
    private String sex;

    // Mã tỉnh
    @SerializedName("province")
    @Expose
    private String province;

    // Mã Quận/Huyện
    @SerializedName("district")
    @Expose
    private String district;

    // Mã phường xã
    @SerializedName("precinct")
    @Expose
    private String precinct;

    //  Đường phố
    @SerializedName("street")
    @Expose
    private String street;

    // Tên Đường phố
    @SerializedName("streetName")
    @Expose
    private String streetName;

    // Tổ, cụm
    @SerializedName("streetBlock")
    @Expose
    private String streetBlock;

    // Tên tổ cụm
    @SerializedName("streetBlockName")
    @Expose
    private String streetBlockName;

    // Số nhà
    @SerializedName("home")
    @Expose
    private String home;

    // mã sản phẩm
    @SerializedName("productCode")
    @Expose
    private String productCode;

    // dịch vụ
    @SerializedName("telecomServiceId")
    @Expose
    private Integer telecomServiceId;

    // dịch vụ
    @SerializedName("serviceType")
    @Expose
    private String serviceType;

    // paymentOrder
    @SerializedName("paymentOrder")
    @Expose
    private Integer paymentOrder;

    // mã địa chỉ
    @SerializedName("locationCode")
    @Expose
    private String locationCode;

    // tên địa chỉ
    @SerializedName("locationName")
    @Expose
    private String locationName;

    // loại kênh
    @SerializedName("channelTypeId")
    @Expose
    private Integer channelTypeId;

    // hoàn thành
    @SerializedName("isInfoCompleted")
    @Expose
    private Integer isInfoCompleted;

    // adslIsdn
    @SerializedName("adslIsdn")
    @Expose
    private String adslIsdn;

    // ngày đầu kết nối
    @SerializedName("firstConnect")
    @Expose
    private String firstConnect;

    // mã quốc qia lắp đặt
    @SerializedName("deployAreaCode")
    @Expose
    private String deployAreaCode;

    // adslSubId
    @SerializedName("adslSubId")
    @Expose
    private Integer adslSubId;

    // địa chỉ lắp đặt
    @SerializedName("deployAddress")
    @Expose
    private String deployAddress;

    // địa chỉ lắp đặt
    @SerializedName("deployAddressEnd")
    @Expose
    private String deployAddressEnd;

    // loại
    @SerializedName("type")
    @Expose
    private Integer type;

    // operation
    @SerializedName("operation")
    @Expose
    private Integer operation;

    // syncStatus
    @SerializedName("syncStatus")
    @Expose
    private Integer syncStatus;

    // syncLastUpdateTime
    @SerializedName("syncLastUpdateTime")
    @Expose
    private String syncLastUpdateTime;

    @SerializedName("package_name")
    @Expose
    private String packageName;

    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("numResetZone")
    @Expose
    private Integer numResetZone;

    @SerializedName("payType")
    @Expose
    private String payType;

    @SerializedName("cardType")
    @Expose
    private String cardType;

    @SerializedName("changeDatetime")
    @Expose
    private String  changeDatetime;

    @SerializedName("isTransfer")
    @Expose
    private String isTransfer;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("manageInvoiceId")
    @Expose
    private Long manageInvoiceId;

    @SerializedName("payIsdn")
    @Expose
    private String payIsdn;

    @SerializedName("payMethod")
    @Expose
    private String payMethod;

    @SerializedName("payTransCode")
    @Expose
    private String payTransCode;

    @SerializedName("shopInvoiceId")
    @Expose
    private Long shopInvoiceId;

    public Subscriber() {

    }

    protected Subscriber(Parcel in) {
        subType = in.readString();
        shopCode = in.readString();
        endDatetime = in.readString();
        staDatetime = in.readString();
        actStatus = in.readString();
        vip = in.readString();
        createDate = in.readString();
        userCreated = in.readString();
        deposit = in.readString();
        address = in.readString();
        isdn = in.readString();
        imsi = in.readString();
        serial = in.readString();
        data = in.readString();
        addressCode = in.readString();
        promotionCode = in.readString();
        regType = in.readString();
        userUsing = in.readString();
        userTitle = in.readString();
        password = in.readString();
        passwordAtm = in.readString();
        birthDate = in.readString();
        nationality = in.readString();
        sex = in.readString();
        province = in.readString();
        district = in.readString();
        precinct = in.readString();
        street = in.readString();
        streetName = in.readString();
        streetBlock = in.readString();
        streetBlockName = in.readString();
        home = in.readString();
        productCode = in.readString();
        serviceType = in.readString();
        locationCode = in.readString();
        locationName = in.readString();
        adslIsdn = in.readString();
        firstConnect = in.readString();
        deployAreaCode = in.readString();
        deployAddress = in.readString();
        deployAddressEnd = in.readString();
        syncLastUpdateTime = in.readString();
        packageName = in.readString();
        customer = in.readParcelable(Customer.class.getClassLoader());
        payType = in.readString();
        cardType = in.readString();
        changeDatetime = in.readString();
        isTransfer = in.readString();
        language = in.readString();
        payIsdn = in.readString();
        payMethod = in.readString();
        payTransCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subType);
        dest.writeString(shopCode);
        dest.writeString(endDatetime);
        dest.writeString(staDatetime);
        dest.writeString(actStatus);
        dest.writeString(vip);
        dest.writeString(createDate);
        dest.writeString(userCreated);
        dest.writeString(deposit);
        dest.writeString(address);
        dest.writeString(isdn);
        dest.writeString(imsi);
        dest.writeString(serial);
        dest.writeString(data);
        dest.writeString(addressCode);
        dest.writeString(promotionCode);
        dest.writeString(regType);
        dest.writeString(userUsing);
        dest.writeString(userTitle);
        dest.writeString(password);
        dest.writeString(passwordAtm);
        dest.writeString(birthDate);
        dest.writeString(nationality);
        dest.writeString(sex);
        dest.writeString(province);
        dest.writeString(district);
        dest.writeString(precinct);
        dest.writeString(street);
        dest.writeString(streetName);
        dest.writeString(streetBlock);
        dest.writeString(streetBlockName);
        dest.writeString(home);
        dest.writeString(productCode);
        dest.writeString(serviceType);
        dest.writeString(locationCode);
        dest.writeString(locationName);
        dest.writeString(adslIsdn);
        dest.writeString(firstConnect);
        dest.writeString(deployAreaCode);
        dest.writeString(deployAddress);
        dest.writeString(deployAddressEnd);
        dest.writeString(syncLastUpdateTime);
        dest.writeString(packageName);
        dest.writeParcelable(customer, flags);
        dest.writeString(payType);
        dest.writeString(cardType);
        dest.writeString(changeDatetime);
        dest.writeString(isTransfer);
        dest.writeString(language);
        dest.writeString(payIsdn);
        dest.writeString(payMethod);
        dest.writeString(payTransCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Subscriber> CREATOR = new Creator<Subscriber>() {
        @Override
        public Subscriber createFromParcel(Parcel in) {
            return new Subscriber(in);
        }

        @Override
        public Subscriber[] newArray(int size) {
            return new Subscriber[size];
        }
    };

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Integer getCustReqId() {
        return custReqId;
    }

    public void setCustReqId(Integer custReqId) {
        this.custReqId = custReqId;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getRegReasonId() {
        return regReasonId;
    }

    public void setRegReasonId(int regReasonId) {
        this.regReasonId = regReasonId;
    }

    public int getFinishReasonId() {
        return finishReasonId;
    }

    public void setFinishReasonId(int finishReasonId) {
        this.finishReasonId = finishReasonId;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getStaDatetime() {
        return staDatetime;
    }

    public void setStaDatetime(String staDatetime) {
        this.staDatetime = staDatetime;
    }

    public String getActStatus() {
        return actStatus;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public Long getQuota() {
        return quota;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public Integer getReasonDepositId() {
        return reasonDepositId;
    }

    public void setReasonDepositId(Integer reasonDepositId) {
        this.reasonDepositId = reasonDepositId;
    }

    public String getUserUsing() {
        return userUsing;
    }

    public void setUserUsing(String userUsing) {
        this.userUsing = userUsing;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordAtm() {
        return passwordAtm;
    }

    public void setPasswordAtm(String passwordAtm) {
        this.passwordAtm = passwordAtm;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetBlock() {
        return streetBlock;
    }

    public void setStreetBlock(String streetBlock) {
        this.streetBlock = streetBlock;
    }

    public String getStreetBlockName() {
        return streetBlockName;
    }

    public void setStreetBlockName(String streetBlockName) {
        this.streetBlockName = streetBlockName;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getTelecomServiceId() {
        return telecomServiceId;
    }

    public void setTelecomServiceId(Integer telecomServiceId) {
        this.telecomServiceId = telecomServiceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(int paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(int channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public int getIsInfoCompleted() {
        return isInfoCompleted;
    }

    public void setIsInfoCompleted(int isInfoCompleted) {
        this.isInfoCompleted = isInfoCompleted;
    }

    public String getAdslIsdn() {
        return adslIsdn;
    }

    public void setAdslIsdn(String adslIsdn) {
        this.adslIsdn = adslIsdn;
    }

    public String getFirstConnect() {
        return firstConnect;
    }

    public void setFirstConnect(String firstConnect) {
        this.firstConnect = firstConnect;
    }

    public String getDeployAreaCode() {
        return deployAreaCode;
    }

    public void setDeployAreaCode(String deployAreaCode) {
        this.deployAreaCode = deployAreaCode;
    }

    public int getAdslSubId() {
        return adslSubId;
    }

    public void setAdslSubId(int adslSubId) {
        this.adslSubId = adslSubId;
    }

    public String getDeployAddress() {
        return deployAddress;
    }

    public void setDeployAddress(String deployAddress) {
        this.deployAddress = deployAddress;
    }

    public String getDeployAddressEnd() {
        return deployAddressEnd;
    }

    public void setDeployAddressEnd(String deployAddressEnd) {
        this.deployAddressEnd = deployAddressEnd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getSyncLastUpdateTime() {
        return syncLastUpdateTime;
    }

    public void setSyncLastUpdateTime(String syncLastUpdateTime) {
        this.syncLastUpdateTime = syncLastUpdateTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getNumResetZone() {
        return numResetZone;
    }

    public void setNumResetZone(Integer numResetZone) {
        this.numResetZone = numResetZone;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setRegReasonId(Integer regReasonId) {
        this.regReasonId = regReasonId;
    }

    public void setFinishReasonId(Integer finishReasonId) {
        this.finishReasonId = finishReasonId;
    }

    public void setPaymentOrder(Integer paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public void setChannelTypeId(Integer channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public void setIsInfoCompleted(Integer isInfoCompleted) {
        this.isInfoCompleted = isInfoCompleted;
    }

    public void setAdslSubId(Integer adslSubId) {
        this.adslSubId = adslSubId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getChangeDatetime() {
        return changeDatetime;
    }

    public void setChangeDatetime(String changeDatetime) {
        this.changeDatetime = changeDatetime;
    }

    public String getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getManageInvoiceId() {
        return manageInvoiceId;
    }

    public void setManageInvoiceId(Long manageInvoiceId) {
        this.manageInvoiceId = manageInvoiceId;
    }

    public String getPayIsdn() {
        return payIsdn;
    }

    public void setPayIsdn(String payIsdn) {
        this.payIsdn = payIsdn;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayTransCode() {
        return payTransCode;
    }

    public void setPayTransCode(String payTransCode) {
        this.payTransCode = payTransCode;
    }

    public Long getShopInvoiceId() {
        return shopInvoiceId;
    }

    public void setShopInvoiceId(Long shopInvoiceId) {
        this.shopInvoiceId = shopInvoiceId;
    }
}
