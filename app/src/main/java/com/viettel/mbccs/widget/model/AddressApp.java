package com.viettel.mbccs.widget.model;

import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.utils.StringUtils;

/**
 * Created by HuyQuyet on 7/7/17.
 */

public class AddressApp {
    private Area areaProvince;
    private Area areaDistrict;
    private Area areaPrecinct;
    private String address;

    private String idProvince;
    private String idDistrict;
    private String idPrecinct;
    private boolean status;

    public AddressApp() {
    }

    public AddressApp(Area areaProvince, Area areaDistrict, Area areaPrecinct, String address,
            String idProvince, String idDistrict, String idPrecinct, boolean status) {
        this.areaProvince = areaProvince;
        this.areaDistrict = areaDistrict;
        this.areaPrecinct = areaPrecinct;
        this.address = address;
        this.idProvince = idProvince;
        this.idDistrict = idDistrict;
        this.idPrecinct = idPrecinct;
        this.status = status;
    }

    public AddressApp(Area areaProvince, Area areaDistrict, Area areaPrecinct, String address) {
        this.areaProvince = areaProvince;
        this.areaDistrict = areaDistrict;
        this.areaPrecinct = areaPrecinct;
        this.address = address;
    }

    public AddressApp(String idProvince, String idDistrict, String idPrecinct, String address) {
        this.address = address;
        this.idProvince = idProvince;
        this.idDistrict = idDistrict;
        this.idPrecinct = idPrecinct;
    }

    public Area getAreaProvince() {
        return areaProvince;
    }

    public void setAreaProvince(Area areaProvince) {
        this.areaProvince = areaProvince;
    }

    public Area getAreaDistrict() {
        return areaDistrict;
    }

    public void setAreaDistrict(Area areaDistrict) {
        this.areaDistrict = areaDistrict;
    }

    public Area getAreaPrecinct() {
        return areaPrecinct;
    }

    public void setAreaPrecinct(Area areaPrecinct) {
        this.areaPrecinct = areaPrecinct;
    }

    public String getAddress() {
        return StringUtils.isEmpty(address) ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdProvince() {
        return StringUtils.isEmpty(idProvince) ? "" : idProvince;
    }

    public void setIdProvince(String idProvince) {
        this.idProvince = idProvince;
    }

    public String getIdDistrict() {
        return StringUtils.isEmpty(idDistrict) ? "" : idDistrict;
    }

    public void setIdDistrict(String idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getIdPrecinct() {
        return StringUtils.isEmpty(idPrecinct) ? "" : idPrecinct;
    }

    public void setIdPrecinct(String idPrecinct) {
        this.idPrecinct = idPrecinct;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
