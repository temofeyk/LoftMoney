package com.temofey.k.android.loftmoney.data.api;

import com.temofey.k.android.loftmoney.data.api.model.ItemsResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetItemsRequest {
    String EXPENSE = "expense";
    String INCOME = "income";

    @GET("./items")
    Single<ItemsResponse> request(@Query("type") String string);
}