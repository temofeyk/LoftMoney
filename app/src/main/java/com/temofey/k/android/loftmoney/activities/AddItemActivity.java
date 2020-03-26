package com.temofey.k.android.loftmoney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.temofey.k.android.loftmoney.R;

public class AddItemActivity extends AppCompatActivity {

    private Button btnAdd;

    private String strName;
    private String strPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        EditText etName = findViewById(R.id.etAddItemName);
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(final Editable editable) {
                strName = editable.toString();
                checkEditTextHasText();
            }

        });
        EditText etPrice = findViewById(R.id.etAddItemPrice);
        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                strPrice = s.toString();
                checkEditTextHasText();
            }
        });

        btnAdd = findViewById(R.id.btnAddItemAdd);
        btnAdd.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strPrice)) {
                setResult(
                        RESULT_OK,
                        new Intent().putExtra("name", strName).putExtra("price", strPrice));
                finish();
            }
        });
    }

    private void checkEditTextHasText() {
        btnAdd.setEnabled(!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strPrice));
    }
}
