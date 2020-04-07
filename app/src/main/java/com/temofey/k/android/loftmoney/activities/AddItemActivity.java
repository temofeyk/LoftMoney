package com.temofey.k.android.loftmoney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.temofey.k.android.loftmoney.App;
import com.temofey.k.android.loftmoney.R;
import com.temofey.k.android.loftmoney.data.api.WebFactory;
import com.temofey.k.android.loftmoney.data.model.Item;
import com.temofey.k.android.loftmoney.data.prefs.Prefs;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddItemActivity extends AppCompatActivity {

    public static final String COLOR_INTENT_KEY = "textColor";
    public static final String TYPE_INTENT_KEY = "type";

    private Button btnAdd;

    private String strName;
    private String strPrice;
    private List<Disposable> disposables = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Intent intent = getIntent();
        int colorId = intent.getIntExtra(COLOR_INTENT_KEY, R.color.white);
        String type = intent.getStringExtra(TYPE_INTENT_KEY);

        TextInputEditText etName = findViewById(R.id.etAddItemName);
        etName.setTextColor(ContextCompat.getColor(this, colorId));
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
        etPrice.setTextColor(ContextCompat.getColor(this, colorId));
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

                btnAdd.setEnabled(false);
                int finalPrice = price;
                Prefs prefs = ((App) getApplication()).getPrefs();
                String token = prefs.getToken();
                Disposable response = WebFactory.getInstance().getPostItemRequest()
                        .request(price, strName, type, token)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(addItemResponse -> {
                                    String id = addItemResponse.getId();
                                    Item item = new Item(strName, finalPrice, id);
                                    setResult(
                                            RESULT_OK,
                                            new Intent().putExtra(Item.ITEM_INTENT_KEY, item));
                                    finish();
                                }, throwable -> {
                                    Toast.makeText(this, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    btnAdd.setEnabled(true);
                                }

                        );
                disposables.add(response);
            }
        });
    }

    @Override
    public void onStop() {

        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
        disposables.clear();
        super.onStop();
    }

    private void checkEditTextHasText() {
        btnAdd.setEnabled(!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strPrice));
    }
}
