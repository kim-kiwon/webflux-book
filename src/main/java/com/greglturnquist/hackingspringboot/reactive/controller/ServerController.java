package com.greglturnquist.hackingspringboot.reactive.controller;

import com.greglturnquist.hackingspringboot.reactive.model.Dish;
import com.greglturnquist.hackingspringboot.reactive.service.KitchenService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ServerController {
    private final KitchenService kitchen;

    // KitchenService 주입받음
    public ServerController(KitchenService kitchen) {
        this.kitchen = kitchen;
    }

    @GetMapping(value = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> serveDishes() {
        return this.kitchen.getDishes();
    }

    @GetMapping(value = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> deliverDishes() {
        return this.kitchen.getDishes()
            .map(dish -> dish.deliver(dish));
    }
}
