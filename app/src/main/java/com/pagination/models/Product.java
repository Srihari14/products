package com.pagination.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Product {

    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("longDescription")
    @Expose
    private String longDescription;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("reviewRating")
    @Expose
    private String reviewRating;
    @SerializedName("reviewCount")
    @Expose
    private String reviewCount;
    @SerializedName("inStock")
    @Expose
    private Boolean inStock;
}