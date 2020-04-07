package com.temofey.k.android.loftmoney.data.api;

import com.temofey.k.android.loftmoney.data.api.model.ItemRemote;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetItemsRequest {
    String EXPENSE = "expense";
    String INCOME = "income";

    @GET("./items")
    Single<List<ItemRemote>> request(@Query("type") String type,
                                     @Query("auth-token") String token);
}