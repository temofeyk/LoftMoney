package com.temofey.k.android.loftmoney.data.api.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponse extends OperationResponse {
    private @SerializedName("id")
    String userId;
    private @SerializedName("auth_token")
    String authToken;

    public String getUserId() {
        return userId;
    }

    public String getAuthToken() {
        return authToken;
    }

}
