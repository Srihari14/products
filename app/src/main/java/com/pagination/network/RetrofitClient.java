package com.pagination.network;


import com.pagination.BuildConfig;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by Rajkumar.
 */
public class RetrofitClient {

    private static String SEVER_URL = BuildConfig.IP_ADDRESS;
    static NetworkService locationService;
    static Retrofit retrofit;

    public static NetworkService create() {
        if (locationService == null)
            locationService = getClient().create(NetworkService.class);
        return locationService;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder builder = original.newBuilder();
                    builder.method(original.method(), original.body());
                    Request request = builder.build();
                    return chain.proceed(request);
                }
            })


                    .connectTimeout(60, TimeUnit.SECONDS).
                            readTimeout(60, TimeUnit.SECONDS).
                            addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(SEVER_URL)
                    .client(client)

                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RequestBody getRequestBody(JSONObject request) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request.toString());
    }


}
