package com.temofey.k.android.loftmoney.activities.main;

import androidx.recyclerview.widget.DiffUtil;

import com.temofey.k.android.loftmoney.data.model.Item;

import java.util.List;

public class ItemsDiffUtil extends DiffUtil.Callback {
    private final List<Item> oldList;
    private final List<Item> newList;

    ItemsDiffUtil(List<Item> oldList, List<Item> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
