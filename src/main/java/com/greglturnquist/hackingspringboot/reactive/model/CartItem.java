package com.greglturnquist.hackingspringboot.reactive.model;

import com.greglturnquist.hackingspringboot.reactive.domain.Item;

public class CartItem {
    private Item item;
    private int quantity;

    private CartItem() {}

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increment() {
        this.quantity += 1;
    }
}
