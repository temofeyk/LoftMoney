package com.temofey.k.android.loftmoney.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.temofey.k.android.loftmoney.R;

public class HelloWorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        Button btn = findViewById(R.id.btn_hello_world_start);
        btn.setOnClickListener(v -> {
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.hello_title, Toast.LENGTH_SHORT);
            toast.show();
        });

    }
}
