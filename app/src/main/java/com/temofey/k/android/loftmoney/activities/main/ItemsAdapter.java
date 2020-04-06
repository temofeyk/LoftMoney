package com.temofey.k.android.loftmoney.activities.main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.temofey.k.android.loftmoney.R;
import com.temofey.k.android.loftmoney.data.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private final int colorId;
    private List<Item> itemsList = new ArrayList<>();

    ItemsAdapter(int colorId) {
        this.colorId = colorId;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.item_view, null);
        TextView textView = itemView.findViewById(R.id.txtItemPrice);
        textView.setTextColor(ContextCompat.getColor(itemView.getContext(), colorId));
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bindItem(itemsList.get(position));
    }

    List<Item> getItemsList() {
        return itemsList;
    }

    void setItemsList(List<Item> newItemsList) {
        ItemsDiffUtil itemsDiffCallback = new ItemsDiffUtil(itemsList, newItemsList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(itemsDiffCallback);
        itemsList = newItemsList;
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;
        private TextView priceView;

        ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.txtItemName);
            priceView = itemView.findViewById(R.id.txtItemPrice);
        }

        void bindItem(final Item item) {
            nameView.setText(item.getName());
            priceView.setText(
                    priceView.getContext().getResources().getString(R.string.price_template, String.valueOf(item.getPrice()))
            );
        }
    }
}
