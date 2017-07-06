package com.viettel.mbccs.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jacky on 7/4/17.
 */

public class SaleTransDetail {

    @SerializedName("amountBeforeTax")
    @Expose
    private int amountBeforeTax;

    @SerializedName("amount")
    @Expose
    private int amount;

    @SerializedName("quantity")
    @Expose
    private int quantity;


    @SerializedName("stateId")
    @Expose
    private int stateId;


    @SerializedName("amountAfterTax")
    @Expose
    private int amountAfterTax;


    @SerializedName("discountAmount")
    @Expose
    private int discountAmount;

    @SerializedName("saleTransDate")
    @Expose
    private String saleTransDate;

    @SerializedName("stockTypeCode")
    @Expose
    private String stockTypeCode;

    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("stockTypeName")
    @Expose
    private String stockTypeName;

    @SerializedName("saleTransId")
    @Expose
    private int saleTransId;

    @SerializedName("stockTypeId")
    @Expose
    private int stockTypeId;

    @SerializedName("priceVat")
    @Expose
    private int priceVat;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("stockModelId")
    @Expose
    private long stockModelId;

    @SerializedName("priceId")
    @Expose
    private long priceId;

    @SerializedName("stockModelCode")
    @Expose
    private String stockModelCode;

    @SerializedName("amountTax")
    @Expose
    private int amountTax;

    @SerializedName("saleTransDetailId")
    @Expose
    private long saleTransDetailId;
}
