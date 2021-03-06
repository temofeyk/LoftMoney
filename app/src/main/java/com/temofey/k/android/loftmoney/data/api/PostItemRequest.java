package com.temofey.k.android.loftmoney.data.api;

import com.temofey.k.android.loftmoney.data.api.model.ItemOperationResponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostItemRequest {
    @POST("./items/add")
    @FormUrlEncoded
    Single<ItemOperationResponse> request(@Field("price") Integer price, @Field("name") String name,
                                          @Field("type") String type,
                                          @Field("auth-token") String token);
}
