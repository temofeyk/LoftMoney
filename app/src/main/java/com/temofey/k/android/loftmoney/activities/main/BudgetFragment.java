package com.temofey.k.android.loftmoney.activities.main;

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
import com.temofey.k.android.loftmoney.data.api.WebFactory;
import com.temofey.k.android.loftmoney.data.api.model.ItemRemote;
import com.temofey.k.android.loftmoney.data.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BudgetFragment extends Fragment {

    private static final int REQUEST_CODE = 100;
    private static final String COLOR_ID = "colorId";
    private static final String TYPE = "fragmentType";

    private ItemsAdapter adapter;
    private List<Disposable> disposables = new ArrayList<>();

    static BudgetFragment newInstance(final int colorId, final String type) {
        BudgetFragment budgetFragment = new BudgetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(COLOR_ID, colorId);
        bundle.putString(TYPE, type);
        budgetFragment.setArguments(bundle);
        return budgetFragment;

    }

    @Override
    public void onStop() {

        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
        disposables.clear();
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_budget, container);

        FloatingActionButton fabAddItem = view.findViewById(R.id.fabBudgetAddItem);
        fabAddItem.setOnClickListener(v -> startActivityForResult(new Intent(getActivity(), AddItemActivity.class), REQUEST_CODE));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerBudgetItemsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation()));

        adapter = new ItemsAdapter(Objects.requireNonNull(getArguments()).getInt(COLOR_ID));
        recyclerView.setAdapter(adapter);
        loadItems();
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

    private void loadItems() {
        Disposable response = WebFactory.getInstance().getItemsRequest().request(Objects.requireNonNull(getArguments()).getString(TYPE))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemsResponse -> {
                            List<Item> itemsList = new ArrayList<>();
                            for (ItemRemote itemRemote : itemsResponse.getData()) {
                                itemsList.add(new Item(itemRemote));
                            }
                            adapter.setItemsList(itemsList);
                        }

                );
        disposables.add(response);
    }
}