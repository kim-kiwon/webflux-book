package com.greglturnquist.hackingspringboot.reactive.model;

import com.greglturnquist.hackingspringboot.reactive.domain.Item;

public class CartItem {
    private Item item;
    private int quantity;

    private CartItem() {}

    CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }
}
