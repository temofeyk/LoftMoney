package com.temofey.k.android.loftmoney.data.api;

import com.temofey.k.android.loftmoney.data.api.model.ItemOperationResponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RemoveItemRequest {
    @POST("./items/remove")
    @FormUrlEncoded
    Single<ItemOperationResponse> request(@Field("id") String id,
                                          @Field("auth-token") String token);
}
