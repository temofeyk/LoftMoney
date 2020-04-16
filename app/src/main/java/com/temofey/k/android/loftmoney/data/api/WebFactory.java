package com.temofey.k.android.loftmoney.data.api;

import com.temofey.k.android.loftmoney.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebFactory {
    private static final String BaseUrl = BuildConfig.URL;
    private static WebFactory instance = null;
    private Retrofit retrofit;

    private WebFactory() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static WebFactory getInstance() {
        if (instance == null) {
            instance = new WebFactory();
        }
        return instance;
    }

    public AuthRequest getAuthRequest() {
        return retrofit.create(AuthRequest.class);
    }

    public GetItemsRequest getItemsRequest() {
        return retrofit.create(GetItemsRequest.class);
    }

    public PostItemRequest getPostItemRequest() {
        return retrofit.create(PostItemRequest.class);
    }

    public RemoveItemRequest getRemoveItemRequest() {
        return retrofit.create(RemoveItemRequest.class);

    }
}
