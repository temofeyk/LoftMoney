package com.temofey.k.android.loftmoney.data.api.model;

import com.google.gson.annotations.SerializedName;

public class OperationResponse {
    private final String SUCCESS = "success";

    private @SerializedName("status")
    String status;

    public String getStatus() {
        return status;
    }

    public Boolean statusIsSuccess() {
        return status.equals(SUCCESS);
    }
}
