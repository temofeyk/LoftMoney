package com.temofey.k.android.loftmoney.activities.main;

import android.util.SparseBooleanArray;
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
    private ItemsAdapterListener itemsAdapterListener;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    ItemsAdapter(int colorId) {
        this.colorId = colorId;
    }

    void setItemsAdapterListener(ItemsAdapterListener itemsAdapterListener) {
        this.itemsAdapterListener = itemsAdapterListener;
    }

    void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    void clearItem(final int position) {
        selectedItems.put(position, false);
        notifyItemChanged(position);
    }

    void toggleItem(final int position) {
        selectedItems.put(position, !selectedItems.get(position));
        notifyItemChanged(position);
    }

    int getSelectedSize() {
        int result = 0;
        for (int i = 0; i < itemsList.size(); i++) {
            if (selectedItems.get(i)) {
                result++;
            }
        }

        return result;
    }

    List<Item> getSelectedItems() {
        List<Item> result = new ArrayList<>();
        int i = 0;
        for (Item item : itemsList) {
            if (selectedItems.get(i)) {
                result.add(item);
            }
            i++;
        }
        return result;
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
        holder.bindItem(itemsList.get(position), selectedItems.get(position));
        holder.setListener(itemsAdapterListener, itemsList.get(position), position);
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
        private View itemView;

        ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.itemView = itemView;
            nameView = itemView.findViewById(R.id.txtItemName);
            priceView = itemView.findViewById(R.id.txtItemPrice);
        }

        void bindItem(final Item item, final boolean isSelected) {
            itemView.setSelected(isSelected);
            nameView.setText(item.getName());
            priceView.setText(
                    priceView.getContext().getResources().getString(R.string.price_template, String.valueOf(item.getPrice()))
            );
        }

        void setListener(final ItemsAdapterListener listener, final Item item, int position) {

            itemView.setOnClickListener(view -> listener.onItemClick(item, position));

            itemView.setOnLongClickListener(view -> {
                        listener.onItemLongClick(item, position);
                        return false;
                    }
            );


        }

    }
}
