package com.greglturnquist.hackingspringboot.reactive.domain;

import com.greglturnquist.hackingspringboot.reactive.model.CartItem;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;

public class Cart {
    private @Id String id;
    private List<CartItem> cartItems;

    private Cart() {}

    // id만 받는 생성자
    public Cart(String id) {
        this(id, new ArrayList<>());
    }

    // id와 카트 아이템 목록을 받는 생성자
    public Cart(String id, List<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }

    public String getId() {
        return id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
