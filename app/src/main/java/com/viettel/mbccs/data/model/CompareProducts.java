package com.viettel.mbccs.data.model;

import java.util.List;

/**
 * Created by minhnx on 7/1/17.
 */

public class CompareProducts {
    private String feature;
    private String value1;
    private String value2;
    private SearchProduct product1;
    private SearchProduct product2;
    private List<String> colorList1;
    private List<String> colorList2;
    private int type;

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public SearchProduct getProduct1() {
        return product1;
    }

    public void setProduct1(SearchProduct product1) {
        this.product1 = product1;
    }

    public SearchProduct getProduct2() {
        return product2;
    }

    public void setProduct2(SearchProduct product2) {
        this.product2 = product2;
    }

    public List<String> getColorList1() {
        return colorList1;
    }

    public void setColorList1(List<String> colorList1) {
        this.colorList1 = colorList1;
    }

    public List<String> getColorList2() {
        return colorList2;
    }

    public void setColorList2(List<String> colorList2) {
        this.colorList2 = colorList2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
