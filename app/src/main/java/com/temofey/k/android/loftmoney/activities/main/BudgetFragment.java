package com.temofey.k.android.loftmoney.activities.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.temofey.k.android.loftmoney.R;
import com.temofey.k.android.loftmoney.activities.AddItemActivity;
import com.temofey.k.android.loftmoney.data.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BudgetFragment extends Fragment {

    private static final int REQUEST_CODE = 100;
    private ItemsAdapter adapter;
    private int position;

    BudgetFragment(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_budget, null);

        FloatingActionButton fabAddItem = view.findViewById(R.id.fabBudgetAddItem);
        fabAddItem.setOnClickListener(v -> startActivityForResult(new Intent(getActivity(), AddItemActivity.class), REQUEST_CODE));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerBudgetItemsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation()));

        adapter = new ItemsAdapter(position);
        List<Item> itemsList = new ArrayList<>();
        if (position == MainActivity.BudgetPagerAdapter.PAGE_OUTCOMES) {
            itemsList.add(new Item("Гречка", 1200, Item.getNewId()));
            itemsList.add(new Item("Патроны", 4500, Item.getNewId()));
            itemsList.add(new Item("Туалетная бумага", 600, Item.getNewId()));
            itemsList.add(new Item("Сковородка с антипригарным покрытием", 2600, Item.getNewId()));
        } else if (position == MainActivity.BudgetPagerAdapter.PAGE_INCOMES) {
            itemsList.add(new Item("Долг за алюминий", 15000, Item.getNewId()));
            itemsList.add(new Item("Аванс", 30000, Item.getNewId()));
        }
        adapter.setItemsList(itemsList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            List<Item> itemsList = new ArrayList<>(adapter.getItemsList());
            Item item = (Item) Objects.requireNonNull(data).getSerializableExtra(Item.ITEM_INTENT_KEY);
            itemsList.add(0, item);
            adapter.setItemsList(itemsList);
        }
    }

}