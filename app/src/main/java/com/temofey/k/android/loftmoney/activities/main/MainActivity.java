package com.temofey.k.android.loftmoney.activities.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.temofey.k.android.loftmoney.R;
import com.temofey.k.android.loftmoney.activities.AddItemActivity;
import com.temofey.k.android.loftmoney.data.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddItem = findViewById(R.id.fabMainAddItem);
        fabAddItem.setOnClickListener(v -> startActivityForResult(new Intent(MainActivity.this, AddItemActivity.class), REQUEST_CODE));

        RecyclerView recyclerView = findViewById(R.id.recyclerMainItemsList);
        adapter = new ItemsAdapter();
        List<Item> itemsList = new ArrayList<>();
        itemsList.add(new Item("Гречка", 1200, Item.getNewId()));
        itemsList.add(new Item("Патроны", 4500, Item.getNewId()));
        itemsList.add(new Item("Туалетная бумага", 600, Item.getNewId()));
        itemsList.add(new Item("Сковородка с антипригарным покрытием", 2600, Item.getNewId()));
        adapter.setItemsList(itemsList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            List<Item> itemsList = new ArrayList<>(adapter.getItemsList());
            Item item = (Item) Objects.requireNonNull(data).getSerializableExtra(Item.ITEM_INTENT_KEY);
            itemsList.add(0, item);
            adapter.setItemsList(itemsList);
        }
    }
}
