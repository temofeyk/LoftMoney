package com.temofey.k.android.loftmoney;

import android.app.Application;

import com.temofey.k.android.loftmoney.data.prefs.Prefs;
import com.temofey.k.android.loftmoney.data.prefs.PrefsImpl;

public class App extends Application {

    private Prefs prefs;

    @Override
    public void onCreate() {
        super.onCreate();

        prefs = new PrefsImpl(this);
    }

    public Prefs getPrefs() {
        return prefs;
    }
}
