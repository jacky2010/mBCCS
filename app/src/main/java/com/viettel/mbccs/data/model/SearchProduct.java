package com.viettel.mbccs.data.model;


import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by minhnx on 7/13/17.
 */

public class SearchProduct {
    @Expose
    private Long productId;
    @Expose
    private Long manufacturerId;
    @Expose
    private String screen;
    @Expose
    private String camera;
    @Expose
    private String cpu;
    @Expose
    private String ram;
    @Expose
    private String colour;
    @Expose
    private Double price;
    @Expose
    private String battery;
    @Expose
    private String description;
    @Expose
    private String promotion;
    @Expose
    private Long functionProductId;
    @Expose
    private Long stockModelTypeId;
    @Expose
    private String status;
    @Expose
    private String name;
    @Expose
    private String manufacturerName;
    @Expose
    private String features;
    @Expose
    private List<KeyValue> productImages;
    @Expose
    private List<KeyValue> comments;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Long getFunctionProductId() {
        return functionProductId;
    }

    public void setFunctionProductId(Long functionProductId) {
        this.functionProductId = functionProductId;
    }

    public Long getStockModelTypeId() {
        return stockModelTypeId;
    }

    public void setStockModelTypeId(Long stockModelTypeId) {
        this.stockModelTypeId = stockModelTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public List<KeyValue> getComments() {
        return comments;
    }

    public void setComments(List<KeyValue> comments) {
        this.comments = comments;
    }

    public List<KeyValue> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<KeyValue> productImages) {
        this.productImages = productImages;
    }
}
