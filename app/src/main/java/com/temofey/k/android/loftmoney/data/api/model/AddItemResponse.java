package com.temofey.k.android.loftmoney.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddItemResponse {
    private @SerializedName("status")
    String status;
    private @SerializedName("id")
    String id;

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }
}
