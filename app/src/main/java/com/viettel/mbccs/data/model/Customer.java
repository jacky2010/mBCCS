package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/19/17.
 */

public class Customer implements Parcelable {

    @SerializedName("customerId")
    @Expose
    private String customerId;

    @SerializedName("customerName")
    @Expose
    private String customerName;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("tin")
    @Expose
    private String tin;

    // Id khách hàng
    @SerializedName("custId")
    @Expose
    private Integer custId;

    // Loại khách hàng
    @SerializedName("busType")
    @Expose
    private String busType;

    // ID loại khách hàng
    @SerializedName("custTypeId")
    @Expose
    private int custTypeId;

    // Loại giấy tờ
    @SerializedName("idType")
    @Expose
    private String idType;

    // Số giấy tờ
    @SerializedName("idNo")
    @Expose
    private String idNo;

    // Nơi câp giấy tờ
    @SerializedName("idIssuePlace")
    @Expose
    private String idIssuePlace;

    // Ngày cấp giấy tờ
    @SerializedName("idIssueDate")
    @Expose
    private String idIssueDate;

    // Ngày hết hiệu lực giấy tờ
    @SerializedName("idExpireDate")
    @Expose
    private String idExpireDate;

    // Số sổ hộ khẩu
    @SerializedName("popNo")
    @Expose
    private String popNo;

    // Giấy phép kinh doanh
    @SerializedName("busPermitNo")
    @Expose
    private int busPermitNo;

    // Nơi cấp sổ hộ khẩu
    @SerializedName("popIssuePlace")
    @Expose
    private String popIssuePlace;

    // Ngày cấp sổ hộ khẩu
    @SerializedName("popIssueDate")
    @Expose
    private String popIssueDate;

    // Tên liên hệ
    @SerializedName("contactName")
    @Expose
    private String contactName;

    // chức danh
    @SerializedName("contactTitle")
    @Expose
    private String contactTitle;

    @SerializedName("name")
    @Expose
    private String name;

    // Ngày sinh
    @SerializedName("birthDate")
    @Expose
    private String birthDate;

    // giới tính
    @SerializedName("sex")
    @Expose
    private String sex;

    // Quốc tịch
    @SerializedName("nationality")
    @Expose
    private String nationality;

    // Số điện thoại,fax
    @SerializedName("telFax")
    @Expose
    private String telFax;

    // email
    @SerializedName("email")
    @Expose
    private String email;

    // Mã địa chỉ
    @SerializedName("areaCode")
    @Expose
    private String areaCode;

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

    // Đường phố
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
    private int streetBlock;

    // Tên tổ cụm
    @SerializedName("streetBlockName")
    @Expose
    private String streetBlockName;

    // Số nhà
    @SerializedName("home")
    @Expose
    private String home;

    // vip
    @SerializedName("vip")
    @Expose
    private String vip;

    // Trạng thái
    @SerializedName("status")
    @Expose
    private Integer status;

    // Người thêm mới bản ghi
    @SerializedName("addedUser")
    @Expose
    private String addedUser;

    // Ngày thêm mới
    @SerializedName("addedDate")
    @Expose
    private String addedDate;

    // Người cập nhật
    @SerializedName("updUser")
    @Expose
    private String updUser;

    // Thời gian cập nhật
    @SerializedName("updTime")
    @Expose
    private String updTime;

    // ghi chú
    @SerializedName("notes")
    @Expose
    private int notes;

    @SerializedName("birthPlace")
    @Expose
    private String birthPlace;

    @SerializedName("certificate")
    @Expose
    private String certificate;

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

    @SerializedName("ftpUser")
    @Expose
    private String ftpUser;

    @SerializedName("nftpUserBusinessotes")
    @Expose
    private String ftpUserBusiness;

    @SerializedName("imageName")
    @Expose
    private String imageName;

    @SerializedName("imageNameNo1")
    @Expose
    private String imageNameNo1;

    @SerializedName("imageNameNo2")
    @Expose
    private String imageNameNo2;

    @SerializedName("imageData")
    @Expose
    private String imageData;

    @SerializedName("imageDataNo1")
    @Expose
    private String imageDataNo1;

    @SerializedName("imageDataNo2")
    @Expose
    private String imageDataNo2;

    @SerializedName("noimageSignaltes")
    @Expose
    private String imageSignal;

    @SerializedName("pathFileBusiness")
    @Expose
    private String pathFileBusiness;

    @SerializedName("pathName")
    @Expose
    private String pathName;

    @SerializedName("repreCustBirthDate")
    @Expose
    private String repreCustBirthDate;

    @SerializedName("repreCustIdExpireDate")
    @Expose
    private String repreCustIdExpireDate;

    @SerializedName("repreCustIdIssuePlace")
    @Expose
    private String repreCustIdIssuePlace;

    @SerializedName("repreCustIdNo")
    @Expose
    private String repreCustIdNo;

    @SerializedName("repreCustIdType")
    @Expose
    private Long repreCustIdType;

    @SerializedName("repreCustName")
    @Expose
    private String repreCustName;

    @SerializedName("repreCustTelFax")
    @Expose
    private String repreCustTelFax;

    @SerializedName("updatedTime")
    @Expose
    private String updatedTime;

    @SerializedName("updatedUser")
    @Expose
    private String updatedUser;

    @SerializedName("vasRegistration")
    @Expose
    private String vasRegistration;

    public Customer() {
    }

    protected Customer(Parcel in) {
        customerId = in.readString();
        customerName = in.readString();
        address = in.readString();
        tin = in.readString();
        busType = in.readString();
        custTypeId = in.readInt();
        idType = in.readString();
        idNo = in.readString();
        idIssuePlace = in.readString();
        idIssueDate = in.readString();
        idExpireDate = in.readString();
        popNo = in.readString();
        busPermitNo = in.readInt();
        popIssuePlace = in.readString();
        popIssueDate = in.readString();
        contactName = in.readString();
        contactTitle = in.readString();
        name = in.readString();
        birthDate = in.readString();
        sex = in.readString();
        nationality = in.readString();
        telFax = in.readString();
        email = in.readString();
        areaCode = in.readString();
        province = in.readString();
        district = in.readString();
        precinct = in.readString();
        street = in.readString();
        streetName = in.readString();
        streetBlock = in.readInt();
        streetBlockName = in.readString();
        home = in.readString();
        vip = in.readString();
        addedUser = in.readString();
        addedDate = in.readString();
        updUser = in.readString();
        updTime = in.readString();
        notes = in.readInt();
        birthPlace = in.readString();
        certificate = in.readString();
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
        imageName = in.readString();
        imageNameNo1 = in.readString();
        imageNameNo2 = in.readString();
        imageData = in.readString();
        imageDataNo1 = in.readString();
        imageDataNo2 = in.readString();
        imageSignal = in.readString();
        pathFileBusiness = in.readString();
        pathName = in.readString();
        repreCustBirthDate = in.readString();
        repreCustIdExpireDate = in.readString();
        repreCustIdIssuePlace = in.readString();
        repreCustIdNo = in.readString();
        repreCustName = in.readString();
        repreCustTelFax = in.readString();
        updatedTime = in.readString();
        updatedUser = in.readString();
        vasRegistration = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(customerId);
        dest.writeString(customerName);
        dest.writeString(address);
        dest.writeString(tin);
        dest.writeString(busType);
        dest.writeInt(custTypeId);
        dest.writeString(idType);
        dest.writeString(idNo);
        dest.writeString(idIssuePlace);
        dest.writeString(idIssueDate);
        dest.writeString(idExpireDate);
        dest.writeString(popNo);
        dest.writeInt(busPermitNo);
        dest.writeString(popIssuePlace);
        dest.writeString(popIssueDate);
        dest.writeString(contactName);
        dest.writeString(contactTitle);
        dest.writeString(name);
        dest.writeString(birthDate);
        dest.writeString(sex);
        dest.writeString(nationality);
        dest.writeString(telFax);
        dest.writeString(email);
        dest.writeString(areaCode);
        dest.writeString(province);
        dest.writeString(district);
        dest.writeString(precinct);
        dest.writeString(street);
        dest.writeString(streetName);
        dest.writeInt(streetBlock);
        dest.writeString(streetBlockName);
        dest.writeString(home);
        dest.writeString(vip);
        dest.writeString(addedUser);
        dest.writeString(addedDate);
        dest.writeString(updUser);
        dest.writeString(updTime);
        dest.writeInt(notes);
        dest.writeString(birthPlace);
        dest.writeString(certificate);
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
        dest.writeString(imageName);
        dest.writeString(imageNameNo1);
        dest.writeString(imageNameNo2);
        dest.writeString(imageData);
        dest.writeString(imageDataNo1);
        dest.writeString(imageDataNo2);
        dest.writeString(imageSignal);
        dest.writeString(pathFileBusiness);
        dest.writeString(pathName);
        dest.writeString(repreCustBirthDate);
        dest.writeString(repreCustIdExpireDate);
        dest.writeString(repreCustIdIssuePlace);
        dest.writeString(repreCustIdNo);
        dest.writeString(repreCustName);
        dest.writeString(repreCustTelFax);
        dest.writeString(updatedTime);
        dest.writeString(updatedUser);
        dest.writeString(vasRegistration);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public int getCustTypeId() {
        return custTypeId;
    }

    public void setCustTypeId(int custTypeId) {
        this.custTypeId = custTypeId;
    }

    /**
     *
     * @return id loại giấy tờ
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType Loại giấy tờ
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return số giấy tờ "cmt or passport"
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * @param idNo Số giấy tờ
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdIssuePlace() {
        return idIssuePlace;
    }

    public void setIdIssuePlace(String idIssuePlace) {
        this.idIssuePlace = idIssuePlace;
    }

    /**
     * @return Ngày cấp giấy tờ
     */
    public String getIdIssueDate() {
        return idIssueDate;
    }

    public void setIdIssueDate(String idIssueDate) {
        this.idIssueDate = idIssueDate;
    }

    /**
     * @return Ngày hêt hạn giấy tờ
     */
    public String getIdExpireDate() {
        return idExpireDate;
    }

    public void setIdExpireDate(String idExpireDate) {
        this.idExpireDate = idExpireDate;
    }

    public String getPopNo() {
        return popNo;
    }

    public void setPopNo(String popNo) {
        this.popNo = popNo;
    }

    public int getBusPermitNo() {
        return busPermitNo;
    }

    public void setBusPermitNo(int busPermitNo) {
        this.busPermitNo = busPermitNo;
    }

    public String getPopIssuePlace() {
        return popIssuePlace;
    }

    public void setPopIssuePlace(String popIssuePlace) {
        this.popIssuePlace = popIssuePlace;
    }

    public String getPopIssueDate() {
        return popIssueDate;
    }

    public void setPopIssueDate(String popIssueDate) {
        this.popIssueDate = popIssueDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTelFax() {
        return telFax;
    }

    public void setTelFax(String telFax) {
        this.telFax = telFax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return {@link Province#provinceId}
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province Mã tỉnh
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return {@link District#districtId}
     */
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return {@link Precinct#precinctId}
     */
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

    public int getStreetBlock() {
        return streetBlock;
    }

    public void setStreetBlock(int streetBlock) {
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

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddedUser() {
        return addedUser;
    }

    public void setAddedUser(String addedUser) {
        this.addedUser = addedUser;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getUpdUser() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public int getNotes() {
        return notes;
    }

    public void setNotes(int notes) {
        this.notes = notes;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
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

    public String getImageSignal() {
        return imageSignal;
    }

    public void setImageSignal(String imageSignal) {
        this.imageSignal = imageSignal;
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

    public String getRepreCustBirthDate() {
        return repreCustBirthDate;
    }

    public void setRepreCustBirthDate(String repreCustBirthDate) {
        this.repreCustBirthDate = repreCustBirthDate;
    }

    public String getRepreCustIdExpireDate() {
        return repreCustIdExpireDate;
    }

    public void setRepreCustIdExpireDate(String repreCustIdExpireDate) {
        this.repreCustIdExpireDate = repreCustIdExpireDate;
    }

    public String getRepreCustIdIssuePlace() {
        return repreCustIdIssuePlace;
    }

    public void setRepreCustIdIssuePlace(String repreCustIdIssuePlace) {
        this.repreCustIdIssuePlace = repreCustIdIssuePlace;
    }

    public String getRepreCustIdNo() {
        return repreCustIdNo;
    }

    public void setRepreCustIdNo(String repreCustIdNo) {
        this.repreCustIdNo = repreCustIdNo;
    }

    public Long getRepreCustIdType() {
        return repreCustIdType;
    }

    public void setRepreCustIdType(Long repreCustIdType) {
        this.repreCustIdType = repreCustIdType;
    }

    public String getRepreCustName() {
        return repreCustName;
    }

    public void setRepreCustName(String repreCustName) {
        this.repreCustName = repreCustName;
    }

    public String getRepreCustTelFax() {
        return repreCustTelFax;
    }

    public void setRepreCustTelFax(String repreCustTelFax) {
        this.repreCustTelFax = repreCustTelFax;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getVasRegistration() {
        return vasRegistration;
    }

    public void setVasRegistration(String vasRegistration) {
        this.vasRegistration = vasRegistration;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getImageDataNo1() {
        return imageDataNo1;
    }

    public void setImageDataNo1(String imageDataNo1) {
        this.imageDataNo1 = imageDataNo1;
    }

    public String getImageDataNo2() {
        return imageDataNo2;
    }

    public void setImageDataNo2(String imageDataNo2) {
        this.imageDataNo2 = imageDataNo2;
    }
}
