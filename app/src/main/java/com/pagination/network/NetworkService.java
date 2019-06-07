package com.pagination.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
 * Created by Rajkumar.
 */
public interface NetworkService {
    String WALMARTPRODUCTS = "walmartproducts";

    @GET(WALMARTPRODUCTS + "/{pageNumber}/{pageSize}")
    Call<ResponseBody> getProductsData(@Path("pageNumber") int pageNumber,
                                       @Path("pageSize") int pageSize);

}
