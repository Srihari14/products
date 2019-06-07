package com.pagination.common;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pagination.listners.OnResultListner;
import com.pagination.models.AllProducts;
import com.pagination.network.Listener;
import com.pagination.network.RetrofitClient;
import com.pagination.network.RetrofitService;
import com.pagination.utils.CommonUtils;
import com.pagination.utils.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;

/*
 * Created by Rajkumar.
 */
public class NetworkCallOperations {
    private static final NetworkCallOperations ourInstance = new NetworkCallOperations();

    public static NetworkCallOperations getInstance() {
        return ourInstance;
    }

    private NetworkCallOperations() {
    }

    public void getLoanApplications(final Context context, final OnResultListner onResultListner,int curentpage) {
        if (CommonUtils.checkNetworkConnection(context)) {
            Call<ResponseBody> responseBodyCall = RetrofitClient.create().
                    getProductsData(curentpage, 30);
            responseBodyCall.enqueue(new Listener(new RetrofitService() {
                @Override
                public void onSuccess(String result, int pos, Throwable t) {
                    if (pos == 0) {
                        parseLoanApplications(result, context, onResultListner);

                    } else {
                        CommonUtils.showToastMessage(context, Constants.NETWORK_NOT_AVAILABLE);
                    }

                }
            }, null, false, context));
        } else {
            CommonUtils.showToastMessage(context, Constants.NETWORK_NOT_AVAILABLE);
        }
    }

    private void parseLoanApplications(String result, final Context context,
                                       final OnResultListner onResultListner) {
        Gson gson = new GsonBuilder().create();
        final AllProducts allProducts = gson.fromJson(result, AllProducts.class);
        onResultListner.getResult(allProducts, true);

    }


}
