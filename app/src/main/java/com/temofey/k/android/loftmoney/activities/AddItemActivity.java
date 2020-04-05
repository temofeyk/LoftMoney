package com.temofey.k.android.loftmoney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.temofey.k.android.loftmoney.R;
import com.temofey.k.android.loftmoney.data.model.Item;

public class AddItemActivity extends AppCompatActivity {

    private Button btnAdd;

    private String strName;
    private String strPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        TextInputEditText etName = findViewById(R.id.etAddItemName);
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
        TextInputEditText etPrice = findViewById(R.id.etAddItemPrice);
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
                int price = 0;
                try {
                    price = Integer.parseInt(strPrice);

                } catch (NumberFormatException ignored) {
                }
                Item item = new Item(strName, price, Item.getNewId());
                setResult(
                        RESULT_OK,
                        new Intent().putExtra(Item.ITEM_INTENT_KEY, item));
                finish();
            }
        });
    }

    private void checkEditTextHasText() {
        btnAdd.setEnabled(!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strPrice));
    }
}
