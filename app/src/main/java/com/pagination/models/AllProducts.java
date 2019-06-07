package com.pagination.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/*
 * Created by developer on 6/6/19.
 */
@Data
public class AllProducts {
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("totalProducts")
    @Expose
    private Integer totalProducts;
    @SerializedName("pageNumber")
    @Expose
    private Integer pageNumber;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
}
