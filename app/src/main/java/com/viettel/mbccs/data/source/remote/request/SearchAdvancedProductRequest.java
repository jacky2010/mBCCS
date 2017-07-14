package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;

/**
 * Created by minhnx on 6/7/17.
 */

public class SearchAdvancedProductRequest extends BaseRequest {

    public SearchAdvancedProductRequest() {
        super();
    }

    @Expose
    private String manufacturer;
    @Expose
    private String screenSize;
    @Expose
    private String camera;
    @Expose
    private String priceRange;
    @Expose
    private String model;
    @Expose
    private String feature;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
