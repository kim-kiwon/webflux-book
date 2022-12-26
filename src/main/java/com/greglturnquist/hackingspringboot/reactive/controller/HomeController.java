package com.greglturnquist.hackingspringboot.reactive.controller;

import com.greglturnquist.hackingspringboot.reactive.domain.Cart;
import com.greglturnquist.hackingspringboot.reactive.domain.CartRepository;
import com.greglturnquist.hackingspringboot.reactive.domain.ItemRepository;
import com.greglturnquist.hackingspringboot.reactive.model.CartItem;
import com.greglturnquist.hackingspringboot.reactive.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    private ItemRepository itemRepository;
    private CartRepository cartRepository;
    private CartService cartService;

    public HomeController(ItemRepository itemRepository, CartRepository cartRepository, CartService cartService) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
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

    @PostMapping("/add/{id}") // id로 받은놈을 Cart에 추가
    Mono<String> addToCart(@PathVariable String id) {
        return this.cartService.addToCart("My Cart", id)
            .thenReturn("redirect:/");
    }
}
