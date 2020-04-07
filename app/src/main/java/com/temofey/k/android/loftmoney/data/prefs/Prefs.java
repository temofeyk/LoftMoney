package com.temofey.k.android.loftmoney.data.prefs;

public interface Prefs {

    boolean isFirstLaunch();

    String getToken();

    void setToken(String token);

}
