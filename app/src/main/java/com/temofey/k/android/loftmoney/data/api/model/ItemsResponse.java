package com.temofey.k.android.loftmoney.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemsResponse {

    private @SerializedName("status")
    String status;
    private @SerializedName("data")
    List<ItemRemote> data;

    public String getStatus() {
        return status;
    }

    public List<ItemRemote> getData() {
        return data;
    }
}
