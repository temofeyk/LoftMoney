package com.temofey.k.android.loftmoney.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemOperationResponse extends OperationResponse {

    private @SerializedName("id")
    String id;

    public String getId() {
        return id;
    }
}
