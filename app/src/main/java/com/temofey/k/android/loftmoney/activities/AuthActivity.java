package com.temofey.k.android.loftmoney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.temofey.k.android.loftmoney.App;
import com.temofey.k.android.loftmoney.R;
import com.temofey.k.android.loftmoney.activities.main.MainActivity;
import com.temofey.k.android.loftmoney.data.api.WebFactory;
import com.temofey.k.android.loftmoney.data.prefs.Prefs;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthActivity extends AppCompatActivity {

    private static final String USER_ID = "temofey_k";
    private Button btnEnter;
    private List<Disposable> disposables = new ArrayList<>();
    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        prefs = ((App) getApplication()).getPrefs();

        if (!prefs.isFirstLaunch()) {
            startMainActivity();
        }

        btnEnter = findViewById(R.id.btnAuthEnter);
        btnEnter.setOnClickListener(this::onClick);
    }

    @Override
    public void onStop() {

        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
        disposables.clear();
        super.onStop();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(intent);
    }

    private void login() {
        btnEnter.setEnabled(false);
        Disposable response = WebFactory.getInstance().getAuthRequest().request(USER_ID)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authResponse -> {
                            String token = authResponse.getAuthToken();
                            if (authResponse.statusIsSuccess()) {
                                prefs.setToken(token);
                                startMainActivity();
                            } else {
                                String status = authResponse.getStatus();
                                Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
                                btnEnter.setEnabled(true);
                            }
                        }, throwable -> {
                            Toast.makeText(this, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            btnEnter.setEnabled(true);
                        }
                );
        disposables.add(response);
    }

    private void onClick(View v) {
        login();
    }
}
