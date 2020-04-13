package com.temofey.k.android.loftmoney.activities.main;

import com.temofey.k.android.loftmoney.data.model.Item;

public interface ItemsAdapterListener {

    void onItemClick(Item item, int position);

    void onItemLongClick(Item item, int position);
}
