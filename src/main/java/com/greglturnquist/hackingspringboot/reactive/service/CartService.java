package com.greglturnquist.hackingspringboot.reactive.service;

import com.greglturnquist.hackingspringboot.reactive.domain.Cart;
import com.greglturnquist.hackingspringboot.reactive.domain.CartRepository;
import com.greglturnquist.hackingspringboot.reactive.domain.ItemRepository;
import com.greglturnquist.hackingspringboot.reactive.model.CartItem;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Service
public class CartService {
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public CartService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public Mono<String> addToCart(String cartId, String id) { // cartId와 itemId
        return this.cartRepository.findById("My Cart") // "My Cart"를 Id로 하는 Cart를 DB에서 찾아라
            .defaultIfEmpty(new Cart("My Cart")) // 없다면 "My Cart"라는 Cart를 생성한다.
            .flatMap(cart -> cart.getCartItems().stream()   // Cart 안의 cartItems 필드를 스트림으로.
                .filter(cartItem -> cartItem.getItem() // 각 cartItem에 대해 Item을 확인한다.
                    .getId().equals(id)) // 아이템들 중 path로 받은 id와 동일한 놈을 찾고
                .findAny()
                .map(cartItem -> { // 찾은 cartItem에 대해 갯수를 1 증가시키고. Cart 데이터를 발행한다.
                    cartItem.increment();
                    return Mono.just(cart);
                })
                .orElseGet(() -> { // 해당 id의 Item이 cartItem에 없다면. 해당 Id의 아이템을 찾아서 새로운 CartItem으로 만든뒤 Cart에 추가한다.
                    return this.itemRepository.findById(id)
                        .map(item -> new CartItem(item))
                        .map(cartItem -> {
                            cart.getCartItems().add(cartItem);
                            return cart;
                        });
                }))
            .flatMap(cart -> this.cartRepository.save(cart))
            .thenReturn("redirect:/");
    }
}
