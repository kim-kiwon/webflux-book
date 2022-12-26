package com.greglturnquist.hackingspringboot.reactive.controller;

import com.greglturnquist.hackingspringboot.reactive.domain.Cart;
import com.greglturnquist.hackingspringboot.reactive.domain.CartRepository;
import com.greglturnquist.hackingspringboot.reactive.domain.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    private ItemRepository itemRepository;
    private CartRepository cartRepository;

    public HomeController(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
            .modelAttribute("items", // 모든 items를 모델에 담아 넘김
                this.itemRepository.findAll())
            .modelAttribute("cart",
                this.cartRepository.findById("My Cart") // My Cart라는 카트가 있으면 해당 카트를 담아 넘기고 없으면 새로 생성.
                    .defaultIfEmpty(new Cart("My Cart")))
            .build());
    }

}
