package com.temofey.k.android.loftmoney.data.api.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    private final String SUCCESS = "success";

    private @SerializedName("status")
    String status;
    private @SerializedName("id")
    String userId;
    private @SerializedName("auth_token")
    String authToken;

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public Boolean statusIsSuccess() {
        return status.equals(SUCCESS);
    }
}
