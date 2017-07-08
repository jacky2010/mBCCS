package com.viettel.mbccs.screen.connector.fragment.postpaid;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.viettel.mbccs.BR;
import com.viettel.mbccs.constance.Data;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.model.BusType;
import com.viettel.mbccs.data.model.Product;
import com.viettel.mbccs.data.model.RegType;
import com.viettel.mbccs.data.model.SubType;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.widget.model.AddressApp;
import java.util.Date;

/**
 * Created by HuyQuyet on 7/7/17.
 */

public class CreateNewConnectorInformationPostpaidViewModel extends BaseObservable {
    private int contractLoaiKhachHang;
    private boolean isViewThongTinNganHang;

    //region Fragment 1
    /**
     * Fragment 1
     */
    // Customer
    private String customerNameCustomer;
    private String customerPlaceCreatePassport;
    private String customerNumberGPKH;
    private String customerNumberMaSoThue;

    private String customerNameCustomerError;
    private String customerPlaceCreatePassportError;
    private String customerNumberGPKHError;
    private String customerNumberMaSoThueError;

    private String customerDateCreate;
    private String customerDateBirthday;

    private Date customerMaxDateBirthday;
    private Date customerMinDateCreate;

    private SpinnerAdapter<BusType> customerAdapterSpinnerCustomerType;

    private AddressApp customerArea1;

    // Register
    private String registerNameRegister;
    private String registerIdNo;
    private String registerPlaceCreatePassport;

    private String registerNameRegisterError;
    private String registerIdNoError;
    private String registerPlaceCreatePassportError;

    private String registerDateBirthday;
    private String registerDateCreate;

    private Date registerMaxDateBirthday;
    private Date registerMaxDateCreate;

    private boolean isRegisterCheckMale = true;
    private boolean isRegisterCheckFemale;

    private SpinnerAdapter<ApDomainByType> registerAdapterSpinnerPassportType;

    private String imageUrlFront;
    private String imageUrlBackside;
    private String imageUrlPortrait;

    //endregion Fragment 1

    //region Fragment 2
    /**
     * Fragment 2
     */
    private String subscriberIsdn;
    private String subscriberSerial;
    private String subscriberInformationStock;
    private String subscriberInformationContract;

    private String subscriberIsdnError;
    private String subscriberSerialError;
    private String subscriberInformationStockError;
    private String subscriberInformationContractError;

    private SpinnerAdapter<Data.DataField> subscriberAdapterSpinner2DichVu;
    private SpinnerAdapter<Product> subscriberAdapterSpinner2GoiCuoc;
    private SpinnerAdapter<SubType> subscriberAdapterSpinner2LoaiThueBao;
    private SpinnerAdapter<RegType> subscriberAdapterSpinner2HTHoaMang;
    private SpinnerAdapter<RegType> subscriberAdapterSpinner2KhuyenMai;
    private SpinnerAdapter<RegType> subscriberAdapterSpinner2DatCoc;
    private SpinnerAdapter<RegType> subscriberAdapterSpinner2HanMuc;

    private AddressApp subscriberArea2;
    //endregion Fragment 2

    //region Fragment 3
    /**
     * Fragment 3
     */
    private String contractHopDongThu;
    private String contractSoTaiKhoan;
    private String contractTenTaiKhoan;
    private String contractTenNguoiDaiDien;
    private String contractChucVu;
    private String contractCMTNDHoCHieu;
    private String contractDienThoai;
    private String contractDiaChiThongBaoCuoc;
    private String contractPhuLucHopDong;

    private String contractHopDongThuError;
    private String contractSoTaiKhoanError;
    private String contractTenTaiKhoanError;
    private String contractTenNguoiDaiDienError;
    private String contractChucVuError;
    private String contractCMTNDHoCHieuError;
    private String contractDienThoaiError;
    private String contractDiaChiThongBaoCuocError;
    private String contractPhuLucHopDongError;

    private SpinnerAdapter<RegType> contractAdapterSpinner3TypeContract;
    private SpinnerAdapter<RegType> contractAdapterSpinner3Payer;
    private SpinnerAdapter<RegType> contractAdapterSpinner3HinhThucThanhToan;
    private SpinnerAdapter<RegType> contractAdapterSpinner3ChuKyCuoc;
    private SpinnerAdapter<RegType> contractAdapterSpinner3ChiTietIn;
    private SpinnerAdapter<RegType> contractAdapterSpinner3TenNganHang;
    private SpinnerAdapter<RegType> contractAdapterSpinner3HinhThucThongBaoCuoc;

    private Date contractMaxDateNgayKy;
    private Date contractMaxDateFromNgayHieuLuc;
    private Date contractMaxDateToNgayHieuLuc;

    private String contractDateNgayKy;
    private String contractDateFromNgayHieuLuc;
    private String contractDateToNgayHieuLuc;

    private AddressApp area3;
    //endregion Fragment 3

    public CreateNewConnectorInformationPostpaidViewModel() {

    }

    //region Set - Get

    @Bindable
    public int getContractLoaiKhachHang() {
        return contractLoaiKhachHang;
    }

    public void setContractLoaiKhachHang(int contractLoaiKhachHang) {
        this.contractLoaiKhachHang = contractLoaiKhachHang;
        notifyPropertyChanged(BR.contractLoaiKhachHang);
    }

    public boolean isViewThongTinNganHang() {
        return isViewThongTinNganHang;
    }

    public void setViewThongTinNganHang(boolean viewThongTinNganHang) {
        isViewThongTinNganHang = viewThongTinNganHang;
    }

    //region Set - Get Fragment 1
    @Bindable
    public String getCustomerNameCustomer() {
        return customerNameCustomer;
    }

    public void setCustomerNameCustomer(String customerNameCustomer) {
        this.customerNameCustomer = customerNameCustomer;
        notifyPropertyChanged(BR.customerNameCustomer);
    }

    @Bindable
    public String getCustomerPlaceCreatePassport() {
        return customerPlaceCreatePassport;
    }

    public void setCustomerPlaceCreatePassport(String customerPlaceCreatePassport) {
        this.customerPlaceCreatePassport = customerPlaceCreatePassport;
        notifyPropertyChanged(BR.customerPlaceCreatePassport);
    }

    @Bindable
    public String getCustomerNumberGPKH() {
        return customerNumberGPKH;
    }

    public void setCustomerNumberGPKH(String customerNumberGPKH) {
        this.customerNumberGPKH = customerNumberGPKH;
        notifyPropertyChanged(BR.customerNumberGPKH);
    }

    @Bindable
    public String getCustomerNumberMaSoThue() {
        return customerNumberMaSoThue;
    }

    public void setCustomerNumberMaSoThue(String customerNumberMaSoThue) {
        this.customerNumberMaSoThue = customerNumberMaSoThue;
        notifyPropertyChanged(BR.customerNumberMaSoThue);
    }

    @Bindable
    public String getCustomerNameCustomerError() {
        return customerNameCustomerError;
    }

    public void setCustomerNameCustomerError(String customerNameCustomerError) {
        this.customerNameCustomerError = customerNameCustomerError;
        notifyPropertyChanged(BR.customerNameCustomerError);
    }

    @Bindable
    public String getCustomerPlaceCreatePassportError() {
        return customerPlaceCreatePassportError;
    }

    public void setCustomerPlaceCreatePassportError(String customerPlaceCreatePassportError) {
        this.customerPlaceCreatePassportError = customerPlaceCreatePassportError;
        notifyPropertyChanged(BR.customerPlaceCreatePassportError);
    }

    @Bindable
    public String getCustomerNumberGPKHError() {
        return customerNumberGPKHError;
    }

    public void setCustomerNumberGPKHError(String customerNumberGPKHError) {
        this.customerNumberGPKHError = customerNumberGPKHError;
        notifyPropertyChanged(BR.customerNumberGPKHError);
    }

    @Bindable
    public String getCustomerNumberMaSoThueError() {
        return customerNumberMaSoThueError;
    }

    public void setCustomerNumberMaSoThueError(String customerNumberMaSoThueError) {
        this.customerNumberMaSoThueError = customerNumberMaSoThueError;
        notifyPropertyChanged(BR.customerNumberMaSoThueError);
    }

    @Bindable
    public String getCustomerDateCreate() {
        return customerDateCreate;
    }

    public void setCustomerDateCreate(String customerDateCreate) {
        this.customerDateCreate = customerDateCreate;
        notifyPropertyChanged(BR.customerDateCreate);
    }

    @Bindable
    public String getCustomerDateBirthday() {
        return customerDateBirthday;
    }

    public void setCustomerDateBirthday(String customerDateBirthday) {
        this.customerDateBirthday = customerDateBirthday;
        notifyPropertyChanged(BR.customerDateBirthday);
    }

    @Bindable
    public Date getCustomerMaxDateBirthday() {
        return customerMaxDateBirthday;
    }

    public void setCustomerMaxDateBirthday(Date customerMaxDateBirthday) {
        this.customerMaxDateBirthday = customerMaxDateBirthday;
        notifyPropertyChanged(BR.customerMaxDateBirthday);
    }

    @Bindable
    public Date getCustomerMinDateCreate() {
        return customerMinDateCreate;
    }

    public void setCustomerMinDateCreate(Date customerMinDateCreate) {
        this.customerMinDateCreate = customerMinDateCreate;
        notifyPropertyChanged(BR.customerMinDateCreate);
    }

    @Bindable
    public SpinnerAdapter<BusType> getCustomerAdapterSpinnerCustomerType() {
        return customerAdapterSpinnerCustomerType;
    }

    public void setCustomerAdapterSpinnerCustomerType(
            SpinnerAdapter<BusType> customerAdapterSpinnerCustomerType) {
        this.customerAdapterSpinnerCustomerType = customerAdapterSpinnerCustomerType;
        notifyPropertyChanged(BR.customerAdapterSpinnerCustomerType);
    }

    public AddressApp getCustomerArea1() {
        return customerArea1;
    }

    @Bindable
    public void setCustomerArea1(AddressApp customerArea1) {
        this.customerArea1 = customerArea1;
        notifyPropertyChanged(BR.customerArea1);
    }

    @Bindable
    public String getRegisterNameRegister() {
        return registerNameRegister;
    }

    public void setRegisterNameRegister(String registerNameRegister) {
        this.registerNameRegister = registerNameRegister;
        notifyPropertyChanged(BR.registerNameRegister);
    }

    @Bindable
    public String getRegisterIdNo() {
        return registerIdNo;
    }

    public void setRegisterIdNo(String registerIdNo) {
        this.registerIdNo = registerIdNo;
        notifyPropertyChanged(BR.registerIdNo);
    }

    @Bindable
    public String getRegisterPlaceCreatePassport() {
        return registerPlaceCreatePassport;
    }

    public void setRegisterPlaceCreatePassport(String registerPlaceCreatePassport) {
        this.registerPlaceCreatePassport = registerPlaceCreatePassport;
        notifyPropertyChanged(BR.registerPlaceCreatePassport);
    }

    @Bindable
    public String getRegisterNameRegisterError() {
        return registerNameRegisterError;
    }

    public void setRegisterNameRegisterError(String registerNameRegisterError) {
        this.registerNameRegisterError = registerNameRegisterError;
        notifyPropertyChanged(BR.registerNameRegisterError);
    }

    @Bindable
    public String getRegisterIdNoError() {
        return registerIdNoError;
    }

    public void setRegisterIdNoError(String registerIdNoError) {
        this.registerIdNoError = registerIdNoError;
        notifyPropertyChanged(BR.registerIdNoError);
    }

    @Bindable
    public String getRegisterPlaceCreatePassportError() {
        return registerPlaceCreatePassportError;
    }

    public void setRegisterPlaceCreatePassportError(String registerPlaceCreatePassportError) {
        this.registerPlaceCreatePassportError = registerPlaceCreatePassportError;
        notifyPropertyChanged(BR.registerPlaceCreatePassportError);
    }

    @Bindable
    public String getRegisterDateBirthday() {
        return registerDateBirthday;
    }

    public void setRegisterDateBirthday(String registerDateBirthday) {
        this.registerDateBirthday = registerDateBirthday;
        notifyPropertyChanged(BR.registerDateBirthday);
    }

    @Bindable
    public String getRegisterDateCreate() {
        return registerDateCreate;
    }

    public void setRegisterDateCreate(String registerDateCreate) {
        this.registerDateCreate = registerDateCreate;
        notifyPropertyChanged(BR.registerDateCreate);
    }

    @Bindable
    public Date getRegisterMaxDateBirthday() {
        return registerMaxDateBirthday;
    }

    public void setRegisterMaxDateBirthday(Date registerMaxDateBirthday) {
        this.registerMaxDateBirthday = registerMaxDateBirthday;
        notifyPropertyChanged(BR.registerMaxDateBirthday);
    }

    @Bindable
    public Date getRegisterMaxDateCreate() {
        return registerMaxDateCreate;
    }

    public void setRegisterMaxDateCreate(Date registerMaxDateCreate) {
        this.registerMaxDateCreate = registerMaxDateCreate;
        notifyPropertyChanged(BR.registerMaxDateCreate);
    }

    @Bindable
    public boolean isRegisterCheckMale() {
        return isRegisterCheckMale;
    }

    public void setRegisterCheckMale(boolean registerCheckMale) {
        isRegisterCheckMale = registerCheckMale;
        notifyPropertyChanged(BR.registerCheckMale);
    }

    @Bindable
    public boolean isRegisterCheckFemale() {
        return isRegisterCheckFemale;
    }

    public void setRegisterCheckFemale(boolean registerCheckFemale) {
        isRegisterCheckFemale = registerCheckFemale;
        notifyPropertyChanged(BR.registerCheckFemale);
    }

    @Bindable
    public SpinnerAdapter<ApDomainByType> getRegisterAdapterSpinnerPassportType() {
        return registerAdapterSpinnerPassportType;
    }

    public void setRegisterAdapterSpinnerPassportType(
            SpinnerAdapter<ApDomainByType> registerAdapterSpinnerPassportType) {
        this.registerAdapterSpinnerPassportType = registerAdapterSpinnerPassportType;
        notifyPropertyChanged(BR.registerAdapterSpinnerPassportType);
    }

    @Bindable
    public String getImageUrlFront() {
        return imageUrlFront;
    }

    public void setImageUrlFront(String imageUrlFront) {
        this.imageUrlFront = imageUrlFront;
        notifyPropertyChanged(BR.imageUrlFront);
    }

    @Bindable
    public String getImageUrlBackside() {
        return imageUrlBackside;
    }

    public void setImageUrlBackside(String imageUrlBackside) {
        this.imageUrlBackside = imageUrlBackside;
        notifyPropertyChanged(BR.imageUrlBackside);
    }

    @Bindable
    public String getImageUrlPortrait() {
        return imageUrlPortrait;
    }

    public void setImageUrlPortrait(String imageUrlPortrait) {
        this.imageUrlPortrait = imageUrlPortrait;
        notifyPropertyChanged(BR.imageUrlPortrait);
    }

    //endregion Set - Get Fragment 1

    //region Set - Get Fragment 2
    @Bindable
    public String getSubscriberIsdn() {
        return subscriberIsdn;
    }

    public void setSubscriberIsdn(String subscriberIsdn) {
        this.subscriberIsdn = subscriberIsdn;
        notifyPropertyChanged(BR.subscriberIsdn);
    }

    @Bindable
    public String getSubscriberSerial() {
        return subscriberSerial;
    }

    public void setSubscriberSerial(String subscriberSerial) {
        this.subscriberSerial = subscriberSerial;
        notifyPropertyChanged(BR.subscriberSerial);
    }

    @Bindable
    public String getSubscriberInformationStock() {
        return subscriberInformationStock;
    }

    public void setSubscriberInformationStock(String subscriberInformationStock) {
        this.subscriberInformationStock = subscriberInformationStock;
        notifyPropertyChanged(BR.subscriberInformationStock);
    }

    @Bindable
    public String getSubscriberInformationContract() {
        return subscriberInformationContract;
    }

    public void setSubscriberInformationContract(String subscriberInformationContract) {
        this.subscriberInformationContract = subscriberInformationContract;
        notifyPropertyChanged(BR.subscriberInformationContract);
    }

    @Bindable
    public String getSubscriberIsdnError() {
        return subscriberIsdnError;
    }

    public void setSubscriberIsdnError(String subscriberIsdnError) {
        this.subscriberIsdnError = subscriberIsdnError;
        notifyPropertyChanged(BR.subscriberIsdnError);
    }

    @Bindable
    public String getSubscriberSerialError() {
        return subscriberSerialError;
    }

    public void setSubscriberSerialError(String subscriberSerialError) {
        this.subscriberSerialError = subscriberSerialError;
        notifyPropertyChanged(BR.subscriberSerialError);
    }

    @Bindable
    public String getSubscriberInformationStockError() {
        return subscriberInformationStockError;
    }

    public void setSubscriberInformationStockError(String subscriberInformationStockError) {
        this.subscriberInformationStockError = subscriberInformationStockError;
        notifyPropertyChanged(BR.subscriberInformationStockError);
    }

    @Bindable
    public String getSubscriberInformationContractError() {
        return subscriberInformationContractError;
    }

    public void setSubscriberInformationContractError(String subscriberInformationContractError) {
        this.subscriberInformationContractError = subscriberInformationContractError;
        notifyPropertyChanged(BR.subscriberInformationContractError);
    }

    @Bindable
    public SpinnerAdapter<Data.DataField> getSubscriberAdapterSpinner2DichVu() {
        return subscriberAdapterSpinner2DichVu;
    }

    public void setSubscriberAdapterSpinner2DichVu(
            SpinnerAdapter<Data.DataField> subscriberAdapterSpinner2DichVu) {
        this.subscriberAdapterSpinner2DichVu = subscriberAdapterSpinner2DichVu;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2DichVu);
    }

    @Bindable
    public SpinnerAdapter<Product> getSubscriberAdapterSpinner2GoiCuoc() {
        return subscriberAdapterSpinner2GoiCuoc;
    }

    public void setSubscriberAdapterSpinner2GoiCuoc(
            SpinnerAdapter<Product> subscriberAdapterSpinner2GoiCuoc) {
        this.subscriberAdapterSpinner2GoiCuoc = subscriberAdapterSpinner2GoiCuoc;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2GoiCuoc);
    }

    @Bindable
    public SpinnerAdapter<SubType> getSubscriberAdapterSpinner2LoaiThueBao() {
        return subscriberAdapterSpinner2LoaiThueBao;
    }

    public void setSubscriberAdapterSpinner2LoaiThueBao(
            SpinnerAdapter<SubType> subscriberAdapterSpinner2LoaiThueBao) {
        this.subscriberAdapterSpinner2LoaiThueBao = subscriberAdapterSpinner2LoaiThueBao;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2LoaiThueBao);
    }

    @Bindable
    public SpinnerAdapter<RegType> getSubscriberAdapterSpinner2HTHoaMang() {
        return subscriberAdapterSpinner2HTHoaMang;
    }

    public void setSubscriberAdapterSpinner2HTHoaMang(
            SpinnerAdapter<RegType> subscriberAdapterSpinner2HTHoaMang) {
        this.subscriberAdapterSpinner2HTHoaMang = subscriberAdapterSpinner2HTHoaMang;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2HTHoaMang);
    }

    @Bindable
    public SpinnerAdapter<RegType> getSubscriberAdapterSpinner2KhuyenMai() {
        return subscriberAdapterSpinner2KhuyenMai;
    }

    public void setSubscriberAdapterSpinner2KhuyenMai(
            SpinnerAdapter<RegType> subscriberAdapterSpinner2KhuyenMai) {
        this.subscriberAdapterSpinner2KhuyenMai = subscriberAdapterSpinner2KhuyenMai;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2KhuyenMai);
    }

    @Bindable
    public SpinnerAdapter<RegType> getSubscriberAdapterSpinner2DatCoc() {
        return subscriberAdapterSpinner2DatCoc;
    }

    public void setSubscriberAdapterSpinner2DatCoc(
            SpinnerAdapter<RegType> subscriberAdapterSpinner2DatCoc) {
        this.subscriberAdapterSpinner2DatCoc = subscriberAdapterSpinner2DatCoc;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2DatCoc);
    }

    @Bindable
    public SpinnerAdapter<RegType> getSubscriberAdapterSpinner2HanMuc() {
        return subscriberAdapterSpinner2HanMuc;
    }

    public void setSubscriberAdapterSpinner2HanMuc(
            SpinnerAdapter<RegType> subscriberAdapterSpinner2HanMuc) {
        this.subscriberAdapterSpinner2HanMuc = subscriberAdapterSpinner2HanMuc;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2HanMuc);
    }

    @Bindable
    public AddressApp getSubscriberArea2() {
        return subscriberArea2;
    }

    public void setSubscriberArea2(AddressApp subscriberArea2) {
        this.subscriberArea2 = subscriberArea2;
        notifyPropertyChanged(BR.subscriberArea2);
    }

    //endregion Set - Get Fragment 2

    //region Set - Get Fragment 3
    @Bindable
    public String getContractHopDongThu() {
        return contractHopDongThu;
    }

    public void setContractHopDongThu(String contractHopDongThu) {
        this.contractHopDongThu = contractHopDongThu;
        notifyPropertyChanged(BR.contractHopDongThu);
    }

    @Bindable
    public String getContractSoTaiKhoan() {
        return contractSoTaiKhoan;
    }

    public void setContractSoTaiKhoan(String contractSoTaiKhoan) {
        this.contractSoTaiKhoan = contractSoTaiKhoan;
        notifyPropertyChanged(BR.contractSoTaiKhoan);
    }

    @Bindable
    public String getContractTenTaiKhoan() {
        return contractTenTaiKhoan;
    }

    public void setContractTenTaiKhoan(String contractTenTaiKhoan) {
        this.contractTenTaiKhoan = contractTenTaiKhoan;
        notifyPropertyChanged(BR.contractTenTaiKhoan);
    }

    @Bindable
    public String getContractTenNguoiDaiDien() {
        return contractTenNguoiDaiDien;
    }

    public void setContractTenNguoiDaiDien(String contractTenNguoiDaiDien) {
        this.contractTenNguoiDaiDien = contractTenNguoiDaiDien;
        notifyPropertyChanged(BR.contractTenNguoiDaiDien);
    }

    @Bindable
    public String getContractChucVu() {
        return contractChucVu;
    }

    public void setContractChucVu(String contractChucVu) {
        this.contractChucVu = contractChucVu;
        notifyPropertyChanged(BR.contractChucVu);
    }

    @Bindable
    public String getContractCMTNDHoCHieu() {
        return contractCMTNDHoCHieu;
    }

    public void setContractCMTNDHoCHieu(String contractCMTNDHoCHieu) {
        this.contractCMTNDHoCHieu = contractCMTNDHoCHieu;
        notifyPropertyChanged(BR.contractCMTNDHoCHieu);
    }

    @Bindable
    public String getContractDienThoai() {
        return contractDienThoai;
    }

    public void setContractDienThoai(String contractDienThoai) {
        this.contractDienThoai = contractDienThoai;
        notifyPropertyChanged(BR.contractDienThoai);
    }

    @Bindable
    public String getContractDiaChiThongBaoCuoc() {
        return contractDiaChiThongBaoCuoc;
    }

    public void setContractDiaChiThongBaoCuoc(String contractDiaChiThongBaoCuoc) {
        this.contractDiaChiThongBaoCuoc = contractDiaChiThongBaoCuoc;
        notifyPropertyChanged(BR.contractDiaChiThongBaoCuoc);
    }

    @Bindable
    public String getContractPhuLucHopDong() {
        return contractPhuLucHopDong;
    }

    public void setContractPhuLucHopDong(String contractPhuLucHopDong) {
        this.contractPhuLucHopDong = contractPhuLucHopDong;
        notifyPropertyChanged(BR.contractPhuLucHopDong);
    }

    @Bindable
    public String getContractHopDongThuError() {
        return contractHopDongThuError;
    }

    public void setContractHopDongThuError(String contractHopDongThuError) {
        this.contractHopDongThuError = contractHopDongThuError;
        notifyPropertyChanged(BR.contractHopDongThuError);
    }

    @Bindable
    public String getContractSoTaiKhoanError() {
        return contractSoTaiKhoanError;
    }

    public void setContractSoTaiKhoanError(String contractSoTaiKhoanError) {
        this.contractSoTaiKhoanError = contractSoTaiKhoanError;
        notifyPropertyChanged(BR.contractSoTaiKhoanError);
    }

    @Bindable
    public String getContractTenTaiKhoanError() {
        return contractTenTaiKhoanError;
    }

    public void setContractTenTaiKhoanError(String contractTenTaiKhoanError) {
        this.contractTenTaiKhoanError = contractTenTaiKhoanError;
        notifyPropertyChanged(BR.contractTenTaiKhoanError);
    }

    @Bindable
    public String getContractTenNguoiDaiDienError() {
        return contractTenNguoiDaiDienError;
    }

    public void setContractTenNguoiDaiDienError(String contractTenNguoiDaiDienError) {
        this.contractTenNguoiDaiDienError = contractTenNguoiDaiDienError;
        notifyPropertyChanged(BR.contractTenNguoiDaiDienError);
    }

    @Bindable
    public String getContractChucVuError() {
        return contractChucVuError;
    }

    public void setContractChucVuError(String contractChucVuError) {
        this.contractChucVuError = contractChucVuError;
        notifyPropertyChanged(BR.contractChucVuError);
    }

    @Bindable
    public String getContractCMTNDHoCHieuError() {
        return contractCMTNDHoCHieuError;
    }

    public void setContractCMTNDHoCHieuError(String contractCMTNDHoCHieuError) {
        this.contractCMTNDHoCHieuError = contractCMTNDHoCHieuError;
        notifyPropertyChanged(BR.contractCMTNDHoCHieuError);
    }

    @Bindable
    public String getContractDienThoaiError() {
        return contractDienThoaiError;
    }

    public void setContractDienThoaiError(String contractDienThoaiError) {
        this.contractDienThoaiError = contractDienThoaiError;
        notifyPropertyChanged(BR.contractDienThoaiError);
    }

    @Bindable
    public String getContractDiaChiThongBaoCuocError() {
        return contractDiaChiThongBaoCuocError;
    }

    public void setContractDiaChiThongBaoCuocError(String contractDiaChiThongBaoCuocError) {
        this.contractDiaChiThongBaoCuocError = contractDiaChiThongBaoCuocError;
        notifyPropertyChanged(BR.contractDiaChiThongBaoCuocError);
    }

    @Bindable
    public String getContractPhuLucHopDongError() {
        return contractPhuLucHopDongError;
    }

    public void setContractPhuLucHopDongError(String contractPhuLucHopDongError) {
        this.contractPhuLucHopDongError = contractPhuLucHopDongError;
        notifyPropertyChanged(BR.contractPhuLucHopDongError);
    }

    @Bindable
    public SpinnerAdapter<RegType> getContractAdapterSpinner3TypeContract() {
        return contractAdapterSpinner3TypeContract;
    }

    public void setContractAdapterSpinner3TypeContract(
            SpinnerAdapter<RegType> contractAdapterSpinner3TypeContract) {
        this.contractAdapterSpinner3TypeContract = contractAdapterSpinner3TypeContract;
        notifyPropertyChanged(BR.contractAdapterSpinner3TypeContract);
    }

    @Bindable
    public SpinnerAdapter<RegType> getContractAdapterSpinner3Payer() {
        return contractAdapterSpinner3Payer;
    }

    public void setContractAdapterSpinner3Payer(
            SpinnerAdapter<RegType> contractAdapterSpinner3Payer) {
        this.contractAdapterSpinner3Payer = contractAdapterSpinner3Payer;
        notifyPropertyChanged(BR.contractAdapterSpinner3Payer);
    }

    @Bindable
    public SpinnerAdapter<RegType> getContractAdapterSpinner3HinhThucThanhToan() {
        return contractAdapterSpinner3HinhThucThanhToan;
    }

    public void setContractAdapterSpinner3HinhThucThanhToan(
            SpinnerAdapter<RegType> contractAdapterSpinner3HinhThucThanhToan) {
        this.contractAdapterSpinner3HinhThucThanhToan = contractAdapterSpinner3HinhThucThanhToan;
        notifyPropertyChanged(BR.contractAdapterSpinner3HinhThucThanhToan);
    }

    @Bindable
    public SpinnerAdapter<RegType> getContractAdapterSpinner3ChuKyCuoc() {
        return contractAdapterSpinner3ChuKyCuoc;
    }

    public void setContractAdapterSpinner3ChuKyCuoc(
            SpinnerAdapter<RegType> contractAdapterSpinner3ChuKyCuoc) {
        this.contractAdapterSpinner3ChuKyCuoc = contractAdapterSpinner3ChuKyCuoc;
        notifyPropertyChanged(BR.contractAdapterSpinner3ChuKyCuoc);
    }

    @Bindable
    public SpinnerAdapter<RegType> getContractAdapterSpinner3ChiTietIn() {
        return contractAdapterSpinner3ChiTietIn;
    }

    public void setContractAdapterSpinner3ChiTietIn(
            SpinnerAdapter<RegType> contractAdapterSpinner3ChiTietIn) {
        this.contractAdapterSpinner3ChiTietIn = contractAdapterSpinner3ChiTietIn;
        notifyPropertyChanged(BR.contractAdapterSpinner3ChiTietIn);
    }

    @Bindable
    public SpinnerAdapter<RegType> getContractAdapterSpinner3TenNganHang() {
        return contractAdapterSpinner3TenNganHang;
    }

    public void setContractAdapterSpinner3TenNganHang(
            SpinnerAdapter<RegType> contractAdapterSpinner3TenNganHang) {
        this.contractAdapterSpinner3TenNganHang = contractAdapterSpinner3TenNganHang;
        notifyPropertyChanged(BR.contractAdapterSpinner3TenNganHang);
    }

    @Bindable
    public SpinnerAdapter<RegType> getContractAdapterSpinner3HinhThucThongBaoCuoc() {
        return contractAdapterSpinner3HinhThucThongBaoCuoc;
    }

    public void setContractAdapterSpinner3HinhThucThongBaoCuoc(
            SpinnerAdapter<RegType> contractAdapterSpinner3HinhThucThongBaoCuoc) {
        this.contractAdapterSpinner3HinhThucThongBaoCuoc =
                contractAdapterSpinner3HinhThucThongBaoCuoc;
        notifyPropertyChanged(BR.contractAdapterSpinner3HinhThucThongBaoCuoc);
    }

    @Bindable
    public Date getContractMaxDateNgayKy() {
        return contractMaxDateNgayKy;
    }

    public void setContractMaxDateNgayKy(Date contractMaxDateNgayKy) {
        this.contractMaxDateNgayKy = contractMaxDateNgayKy;
        notifyPropertyChanged(BR.contractMaxDateNgayKy);
    }

    @Bindable
    public Date getContractMaxDateFromNgayHieuLuc() {
        return contractMaxDateFromNgayHieuLuc;
    }

    public void setContractMaxDateFromNgayHieuLuc(Date contractMaxDateFromNgayHieuLuc) {
        this.contractMaxDateFromNgayHieuLuc = contractMaxDateFromNgayHieuLuc;
        notifyPropertyChanged(BR.contractMaxDateFromNgayHieuLuc);
    }

    @Bindable
    public Date getContractMaxDateToNgayHieuLuc() {
        return contractMaxDateToNgayHieuLuc;
    }

    public void setContractMaxDateToNgayHieuLuc(Date contractMaxDateToNgayHieuLuc) {
        this.contractMaxDateToNgayHieuLuc = contractMaxDateToNgayHieuLuc;
        notifyPropertyChanged(BR.contractMaxDateToNgayHieuLuc);
    }

    @Bindable
    public String getContractDateNgayKy() {
        return contractDateNgayKy;
    }

    public void setContractDateNgayKy(String contractDateNgayKy) {
        this.contractDateNgayKy = contractDateNgayKy;
        notifyPropertyChanged(BR.contractDateNgayKy);
    }

    @Bindable
    public String getContractDateFromNgayHieuLuc() {
        return contractDateFromNgayHieuLuc;
    }

    public void setContractDateFromNgayHieuLuc(String contractDateFromNgayHieuLuc) {
        this.contractDateFromNgayHieuLuc = contractDateFromNgayHieuLuc;
        notifyPropertyChanged(BR.contractDateFromNgayHieuLuc);
    }

    @Bindable
    public String getContractDateToNgayHieuLuc() {
        return contractDateToNgayHieuLuc;
    }

    public void setContractDateToNgayHieuLuc(String contractDateToNgayHieuLuc) {
        this.contractDateToNgayHieuLuc = contractDateToNgayHieuLuc;
        notifyPropertyChanged(BR.contractDateToNgayHieuLuc);
    }

    @Bindable
    public AddressApp getArea3() {
        return area3;
    }

    public void setArea3(AddressApp area3) {
        this.area3 = area3;
        notifyPropertyChanged(BR.area3);
    }
    //endregion Set - Get Fragment 3

    //endregion Set - Get
}
