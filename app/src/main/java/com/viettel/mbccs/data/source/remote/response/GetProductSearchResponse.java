package com.viettel.mbccs.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.viettel.mbccs.data.model.KeyValue;

import java.util.List;

/**
 * Created by minhnx on 6/7/17.
 */

public class GetProductSearchResponse extends DataResponse {
    @Expose
    private List<KeyValue> listCameras;
    @Expose
    private List<KeyValue> listManufacturers;
    @Expose
    private List<KeyValue> listScreenSizes;
    @Expose
    private List<KeyValue> listModels;
    @Expose
    private List<KeyValue> listFeatures;
    @Expose
    private List<KeyValue> listPriceRanges;

    public List<KeyValue> getListCameras() {
        return listCameras;
    }

    public void setListCameras(List<KeyValue> listCameras) {
        this.listCameras = listCameras;
    }

    public List<KeyValue> getListManufacturers() {
        return listManufacturers;
    }

    public void setListManufacturers(List<KeyValue> listManufacturers) {
        this.listManufacturers = listManufacturers;
    }

    public List<KeyValue> getListScreenSizes() {
        return listScreenSizes;
    }

    public void setListScreenSizes(List<KeyValue> listScreenSizes) {
        this.listScreenSizes = listScreenSizes;
    }

    public List<KeyValue> getListModels() {
        return listModels;
    }

    public void setListModels(List<KeyValue> listModels) {
        this.listModels = listModels;
    }

    public List<KeyValue> getListFeatures() {
        return listFeatures;
    }

    public void setListFeatures(List<KeyValue> listFeatures) {
        this.listFeatures = listFeatures;
    }

    public List<KeyValue> getListPriceRanges() {
        return listPriceRanges;
    }

    public void setListPriceRanges(List<KeyValue> listPriceRanges) {
        this.listPriceRanges = listPriceRanges;
    }
}
