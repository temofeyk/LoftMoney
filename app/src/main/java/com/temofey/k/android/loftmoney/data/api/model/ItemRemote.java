package com.temofey.k.android.loftmoney.data.api.model;

public class ItemRemote {

    private String id;
    private String name;
    private Integer price;
    private String type;

    private String created_at;
    private String updated_at;
    private String user_id;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
