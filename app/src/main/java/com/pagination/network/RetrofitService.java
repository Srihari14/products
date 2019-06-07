package com.pagination.network;

/**
 * Created by Rajkumar.
 *
 */
public interface RetrofitService {
    void onSuccess(String result, int pos, Throwable t);
}
