package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HuyQuyet on 5/31/17.
 */

public class Subscriber implements Parcelable {

    // Id thuê bao
    @SerializedName("subID")
    @Expose
    private int subID;

    // custReqId
    @SerializedName("custReqId")
    @Expose
    private int custReqId;

    // Id hợp đồng
    @SerializedName("contractId")
    @Expose
    private int contractId;

    // loại thuê bao
    @SerializedName("subType")
    @Expose
    private String subType;

    // Id lí do hòa mạng
    @SerializedName("regReasonId")
    @Expose
    private int regReasonId;

    // Id lí do kết thúc
    @SerializedName("finishReasonId")
    @Expose
    private int finishReasonId;

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
    private int status;

    // tiền cọc
    @SerializedName("deposit")
    @Expose
    private int deposit;

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

    // giới hạn
    @SerializedName("quota")
    @Expose
    private int quota;

    // Mã chương trình khuyênn
    @SerializedName("promotionCode")
    @Expose
    private int promotionCode;

    // loại hòa mạng
    @SerializedName("regType")
    @Expose
    private String regType;

    // id lí do đặt cọc
    @SerializedName("reasonDepositId")
    @Expose
    private int reasonDepositId;

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
    private int staffId;

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
    private int telecomServiceId;

    // paymentOrder
    @SerializedName("paymentOrder")
    @Expose
    private int paymentOrder;

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
    private int channelTypeId;

    // hoàn thành
    @SerializedName("isInfoCompleted")
    @Expose
    private int isInfoCompleted;

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
    private int adslSubId;

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
    private int type;

    // operation
    @SerializedName("operation")
    @Expose
    private int operation;

    // syncStatus
    @SerializedName("syncStatus")
    @Expose
    private int syncStatus;

    // syncLastUpdateTime
    @SerializedName("syncLastUpdateTime")
    @Expose
    private String syncLastUpdateTime;

    @SerializedName("package_name")
    @Expose
    private String packageName;

    public Subscriber() {

    }

    protected Subscriber(Parcel in) {
        subID = in.readInt();
        custReqId = in.readInt();
        contractId = in.readInt();
        subType = in.readString();
        regReasonId = in.readInt();
        finishReasonId = in.readInt();
        shopCode = in.readString();
        endDatetime = in.readString();
        staDatetime = in.readString();
        actStatus = in.readString();
        vip = in.readString();
        createDate = in.readString();
        userCreated = in.readString();
        status = in.readInt();
        deposit = in.readInt();
        address = in.readString();
        isdn = in.readString();
        imsi = in.readString();
        serial = in.readString();
        data = in.readString();
        addressCode = in.readString();
        quota = in.readInt();
        promotionCode = in.readInt();
        regType = in.readString();
        reasonDepositId = in.readInt();
        userUsing = in.readString();
        userTitle = in.readString();
        staffId = in.readInt();
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
        telecomServiceId = in.readInt();
        paymentOrder = in.readInt();
        locationCode = in.readString();
        locationName = in.readString();
        channelTypeId = in.readInt();
        isInfoCompleted = in.readInt();
        adslIsdn = in.readString();
        firstConnect = in.readString();
        deployAreaCode = in.readString();
        adslSubId = in.readInt();
        deployAddress = in.readString();
        deployAddressEnd = in.readString();
        type = in.readInt();
        operation = in.readInt();
        syncStatus = in.readInt();
        syncLastUpdateTime = in.readString();
        packageName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(subID);
        dest.writeInt(custReqId);
        dest.writeInt(contractId);
        dest.writeString(subType);
        dest.writeInt(regReasonId);
        dest.writeInt(finishReasonId);
        dest.writeString(shopCode);
        dest.writeString(endDatetime);
        dest.writeString(staDatetime);
        dest.writeString(actStatus);
        dest.writeString(vip);
        dest.writeString(createDate);
        dest.writeString(userCreated);
        dest.writeInt(status);
        dest.writeInt(deposit);
        dest.writeString(address);
        dest.writeString(isdn);
        dest.writeString(imsi);
        dest.writeString(serial);
        dest.writeString(data);
        dest.writeString(addressCode);
        dest.writeInt(quota);
        dest.writeInt(promotionCode);
        dest.writeString(regType);
        dest.writeInt(reasonDepositId);
        dest.writeString(userUsing);
        dest.writeString(userTitle);
        dest.writeInt(staffId);
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
        dest.writeInt(telecomServiceId);
        dest.writeInt(paymentOrder);
        dest.writeString(locationCode);
        dest.writeString(locationName);
        dest.writeInt(channelTypeId);
        dest.writeInt(isInfoCompleted);
        dest.writeString(adslIsdn);
        dest.writeString(firstConnect);
        dest.writeString(deployAreaCode);
        dest.writeInt(adslSubId);
        dest.writeString(deployAddress);
        dest.writeString(deployAddressEnd);
        dest.writeInt(type);
        dest.writeInt(operation);
        dest.writeInt(syncStatus);
        dest.writeString(syncLastUpdateTime);
        dest.writeString(packageName);
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

    public int getSubID() {
        return subID;
    }

    public void setSubID(int subID) {
        this.subID = subID;
    }

    public int getCustReqId() {
        return custReqId;
    }

    public void setCustReqId(int custReqId) {
        this.custReqId = custReqId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
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

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(int promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public int getReasonDepositId() {
        return reasonDepositId;
    }

    public void setReasonDepositId(int reasonDepositId) {
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

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
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

    public int getTelecomServiceId() {
        return telecomServiceId;
    }

    public void setTelecomServiceId(int telecomServiceId) {
        this.telecomServiceId = telecomServiceId;
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
}
