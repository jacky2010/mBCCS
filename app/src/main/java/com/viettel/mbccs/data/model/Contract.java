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

    // Id hợp đồng
    @SerializedName("contractId")
    @Expose
    private String contractId;

    // Tên hợp đồng
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

    // Ngày hiệu lực
    @SerializedName("effectDate")
    @Expose
    private String effectDate;

    // Ngày ký hợp đồng
    @SerializedName("signDate")
    @Expose
    private String signDate;

    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;

    @SerializedName("noticeCharge")
    @Expose
    private List<String> noticeCharge;

    // Hình thức thanh toán
    @SerializedName("payMethodCode")
    @Expose
    private String payMethodCode;

    // Chi tiết in
    @SerializedName("printMethodCode")
    @Expose
    private String printMethodCode;

    @SerializedName("payAreaCode")
    @Expose
    private String payAreaCode;

    // Người thanh toán
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

    // Chu kỳ cước
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

    @SerializedName("billCycleFromCharging")
    @Expose
    private String billCycleFromCharging;

    @SerializedName("contactName")
    @Expose
    private String contactName;

    @SerializedName("contactTitle")
    @Expose
    private String contactTitle;

    // Loại hợp đồng
    @SerializedName("contractTypeCode")
    @Expose
    private String contractTypeCode;

    @SerializedName("deporsit")
    @Expose
    private Long deporsit;

    @SerializedName("email")
    @Expose
    private String email;

    // Ngày hết hạn
    @SerializedName("endDatetime")
    @Expose
    private String endDatetime;

    @SerializedName("fileBusinessLic")
    @Expose
    private String fileBusinessLic;

    @SerializedName("fileCertificate")
    @Expose
    private String fileCertificate;

    @SerializedName("fileContract")
    @Expose
    private String fileContract;

    @SerializedName("fileIdRepre")
    @Expose
    private String fileIdRepre;

    @SerializedName("fileTin")
    @Expose
    private String fileTin;

    @SerializedName("fileVas")
    @Expose
    private String fileVas;

    @SerializedName("fromPrice")
    @Expose
    private Long fromPrice;

    @SerializedName("ftpHost")
    @Expose
    private String ftpHost;

    @SerializedName("ftpHostBusiness")
    @Expose
    private String ftpHostBusiness;

    @SerializedName("ftpPass")
    @Expose
    private String ftpPass;

    @SerializedName("ftpPassBusiness")
    @Expose
    private String ftpPassBusiness;

    @SerializedName("stftpUseratus")
    @Expose
    private String ftpUser;

    @SerializedName("ftpUserBusiness")
    @Expose
    private String ftpUserBusiness;

    @SerializedName("idNo")
    @Expose
    private String idNo;

    @SerializedName("imageName")
    @Expose
    private String imageName;

    @SerializedName("imageNameNo1")
    @Expose
    private String imageNameNo1;

    @SerializedName("imageNameNo2")
    @Expose
    private String imageNameNo2;

    @SerializedName("imageNameNo3")
    @Expose
    private String imageNameNo3;

    @SerializedName("lastUpdateTime")
    @Expose
    private String lastUpdateTime;

    @SerializedName("lastUpdateUser")
    @Expose
    private String lastUpdateUser;

    @SerializedName("oldCustId")
    @Expose
    private Long oldCustId;

    @SerializedName("pathFileBusiness")
    @Expose
    private String pathFileBusiness;

    @SerializedName("pathName")
    @Expose
    private String pathName;

    @SerializedName("reason")
    @Expose
    private String reason;

    @SerializedName("regType")
    @Expose
    private Long regType;

    @SerializedName("street")
    @Expose
    private String street;

    @SerializedName("streetBlock")
    @Expose
    private String streetBlock;

    @SerializedName("telFax")
    @Expose
    private String telFax;

    @SerializedName("toPrice")
    @Expose
    private Long toPrice;

    @SerializedName("contractBank")
    @Expose
    private ContractBank contractBank;

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
        billCycleFromCharging = in.readString();
        contactName = in.readString();
        contactTitle = in.readString();
        contractTypeCode = in.readString();
        email = in.readString();
        endDatetime = in.readString();
        fileBusinessLic = in.readString();
        fileCertificate = in.readString();
        fileContract = in.readString();
        fileIdRepre = in.readString();
        fileTin = in.readString();
        fileVas = in.readString();
        ftpHost = in.readString();
        ftpHostBusiness = in.readString();
        ftpPass = in.readString();
        ftpPassBusiness = in.readString();
        ftpUser = in.readString();
        ftpUserBusiness = in.readString();
        idNo = in.readString();
        imageName = in.readString();
        imageNameNo1 = in.readString();
        imageNameNo2 = in.readString();
        imageNameNo3 = in.readString();
        lastUpdateTime = in.readString();
        lastUpdateUser = in.readString();
        pathFileBusiness = in.readString();
        pathName = in.readString();
        reason = in.readString();
        street = in.readString();
        streetBlock = in.readString();
        telFax = in.readString();
        contractBank = in.readParcelable(ContractBank.class.getClassLoader());
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
        dest.writeString(billCycleFromCharging);
        dest.writeString(contactName);
        dest.writeString(contactTitle);
        dest.writeString(contractTypeCode);
        dest.writeString(email);
        dest.writeString(endDatetime);
        dest.writeString(fileBusinessLic);
        dest.writeString(fileCertificate);
        dest.writeString(fileContract);
        dest.writeString(fileIdRepre);
        dest.writeString(fileTin);
        dest.writeString(fileVas);
        dest.writeString(ftpHost);
        dest.writeString(ftpHostBusiness);
        dest.writeString(ftpPass);
        dest.writeString(ftpPassBusiness);
        dest.writeString(ftpUser);
        dest.writeString(ftpUserBusiness);
        dest.writeString(idNo);
        dest.writeString(imageName);
        dest.writeString(imageNameNo1);
        dest.writeString(imageNameNo2);
        dest.writeString(imageNameNo3);
        dest.writeString(lastUpdateTime);
        dest.writeString(lastUpdateUser);
        dest.writeString(pathFileBusiness);
        dest.writeString(pathName);
        dest.writeString(reason);
        dest.writeString(street);
        dest.writeString(streetBlock);
        dest.writeString(telFax);
        dest.writeParcelable(contractBank, flags);
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

    public String getBillCycleFromCharging() {
        return billCycleFromCharging;
    }

    public void setBillCycleFromCharging(String billCycleFromCharging) {
        this.billCycleFromCharging = billCycleFromCharging;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getContractTypeCode() {
        return contractTypeCode;
    }

    public void setContractTypeCode(String contractTypeCode) {
        this.contractTypeCode = contractTypeCode;
    }

    public Long getDeporsit() {
        return deporsit;
    }

    public void setDeporsit(Long deporsit) {
        this.deporsit = deporsit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getFileBusinessLic() {
        return fileBusinessLic;
    }

    public void setFileBusinessLic(String fileBusinessLic) {
        this.fileBusinessLic = fileBusinessLic;
    }

    public String getFileCertificate() {
        return fileCertificate;
    }

    public void setFileCertificate(String fileCertificate) {
        this.fileCertificate = fileCertificate;
    }

    public String getFileContract() {
        return fileContract;
    }

    public void setFileContract(String fileContract) {
        this.fileContract = fileContract;
    }

    public String getFileIdRepre() {
        return fileIdRepre;
    }

    public void setFileIdRepre(String fileIdRepre) {
        this.fileIdRepre = fileIdRepre;
    }

    public String getFileTin() {
        return fileTin;
    }

    public void setFileTin(String fileTin) {
        this.fileTin = fileTin;
    }

    public String getFileVas() {
        return fileVas;
    }

    public void setFileVas(String fileVas) {
        this.fileVas = fileVas;
    }

    public Long getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(Long fromPrice) {
        this.fromPrice = fromPrice;
    }

    public String getFtpHost() {
        return ftpHost;
    }

    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }

    public String getFtpHostBusiness() {
        return ftpHostBusiness;
    }

    public void setFtpHostBusiness(String ftpHostBusiness) {
        this.ftpHostBusiness = ftpHostBusiness;
    }

    public String getFtpPass() {
        return ftpPass;
    }

    public void setFtpPass(String ftpPass) {
        this.ftpPass = ftpPass;
    }

    public String getFtpPassBusiness() {
        return ftpPassBusiness;
    }

    public void setFtpPassBusiness(String ftpPassBusiness) {
        this.ftpPassBusiness = ftpPassBusiness;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getFtpUserBusiness() {
        return ftpUserBusiness;
    }

    public void setFtpUserBusiness(String ftpUserBusiness) {
        this.ftpUserBusiness = ftpUserBusiness;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageNameNo1() {
        return imageNameNo1;
    }

    public void setImageNameNo1(String imageNameNo1) {
        this.imageNameNo1 = imageNameNo1;
    }

    public String getImageNameNo2() {
        return imageNameNo2;
    }

    public void setImageNameNo2(String imageNameNo2) {
        this.imageNameNo2 = imageNameNo2;
    }

    public String getImageNameNo3() {
        return imageNameNo3;
    }

    public void setImageNameNo3(String imageNameNo3) {
        this.imageNameNo3 = imageNameNo3;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Long getOldCustId() {
        return oldCustId;
    }

    public void setOldCustId(Long oldCustId) {
        this.oldCustId = oldCustId;
    }

    public String getPathFileBusiness() {
        return pathFileBusiness;
    }

    public void setPathFileBusiness(String pathFileBusiness) {
        this.pathFileBusiness = pathFileBusiness;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getRegType() {
        return regType;
    }

    public void setRegType(Long regType) {
        this.regType = regType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetBlock() {
        return streetBlock;
    }

    public void setStreetBlock(String streetBlock) {
        this.streetBlock = streetBlock;
    }

    public String getTelFax() {
        return telFax;
    }

    public void setTelFax(String telFax) {
        this.telFax = telFax;
    }

    public Long getToPrice() {
        return toPrice;
    }

    public void setToPrice(Long toPrice) {
        this.toPrice = toPrice;
    }

    public ContractBank getContractBank() {
        return contractBank;
    }

    public void setContractBank(ContractBank contractBank) {
        this.contractBank = contractBank;
    }

    @Override
    public String toString() {
        return getContactName();
    }
}
