package com.temofey.k.android.loftmoney.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefsImpl implements Prefs {

    private static final String PREFS_NAME = "prefs";
    private static final String KEY_TOKEN = "token";

    private Context context;

    public PrefsImpl(Context context) {
        this.context = context;
    }

    private SharedPreferences getPrefs() {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isFirstLaunch() {
        return getToken().isEmpty();
    }

    @Override
    public String getToken() {
        return getPrefs().getString(KEY_TOKEN, "");
    }

    @Override
    public void setToken(String token) {
        getPrefs().edit().putString(KEY_TOKEN, token).apply();
    }


}
