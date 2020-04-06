package com.temofey.k.android.loftmoney.data.model;

import com.temofey.k.android.loftmoney.activities.main.ItemsAdapter;
import com.temofey.k.android.loftmoney.data.api.model.ItemRemote;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Item implements Serializable {

    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private String name;
    private int price;
    private Integer id;

    public Item(ItemRemote itemRemote) {
        this.id = itemRemote.getId();
        this.name = itemRemote.getName();
        this.price = itemRemote.getPrice();
    }

    public Item(String name, int price, Integer id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public static int getNewId() {
        return COUNTER.getAndIncrement();
    }

    public static final String ITEM_INTENT_KEY = "item";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getPrice() == item.getPrice() &&
                getId() == item.getId() &&
                getName().equals(item.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getId());
    }

}
