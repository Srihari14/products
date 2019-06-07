package com.pagination.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pagination.utils.CommonUtils;
import com.pagination.utils.Logger;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Rajkumar.
 */
public class Listener implements Callback<ResponseBody> {

    Type tt;
    private RetrofitService listner;
    private Context activity;

    public Listener(RetrofitService listner,
                    String title,
                    boolean showProgress, Context activity) {
        //tt=t;
        this.listner = listner;

        this.activity = activity;

        if (showProgress) {
        }
    }

    public void showError(JSONObject obj) {
        if (!obj.isNull("message")) {

        }

    }


    @Override
    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
        try {
            if (!response.isSuccessful()) {

                listner.onSuccess(response.message(), 2, null);
            } else {
                String res = response.body().string();
                Logger.getInstance().d("response-->0" + res);
                listner.onSuccess(res, 0, null);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            Logger.getInstance().d("response-->1" + e.getLocalizedMessage());
            listner.onSuccess("", 2, null);
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Logger.getInstance().d("response-->2" + t.getLocalizedMessage());
        listner.onSuccess("", 1, t);
        if (t != null)
            t.printStackTrace();
    }
}
