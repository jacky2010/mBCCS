package com.viettel.mbccs.data.model;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class WarehouseOrder {

    private String orderId;

    private String warehouseId;

    private String warehouseName;

    private String channelId;

    private String channelName;

    private long createdDate;


    public WarehouseOrder(String orderId, String warehouseId, String warehouseName, String channelId, String channelName, long createdDate) {
        this.orderId = orderId;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.channelId = channelId;
        this.channelName = channelName;
        this.createdDate = createdDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }
}
