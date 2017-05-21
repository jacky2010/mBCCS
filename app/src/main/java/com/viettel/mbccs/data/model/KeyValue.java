package com.viettel.mbccs.data.model;

/**
 * Created by minhnx on 5/21/17.
 */

public class KeyValue {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValue() {
    }
}
