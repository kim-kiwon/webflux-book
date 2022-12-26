package com.greglturnquist.hackingspringboot.reactive;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    @GetMapping
    Mono<String> home() {
        return Mono.just("home"); // 그냥 String인 home을 반환하는건데 왜 굳이 Mono 사용?
    }
}
