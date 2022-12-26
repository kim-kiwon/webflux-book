package com.greglturnquist.hackingspringboot.reactive;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class KitchenService {
    // 랜덤한 요리 선정할 때 사용하는 Random 객체
    private Random picker = new Random();

    // 필드로 메뉴들을 가짐.
    private List<Dish> menu = Arrays.asList(
        new Dish("Sesame chicken"),
        new Dish("Lo mein noodles, plain"),
        new Dish("Sweet & sour beef"));

    // Random 객체로 무작위 메뉴 반환하는 함수
    private Dish randomDish() {
        return menu.get(picker.nextInt(menu.size()));
    }

    /**
     * 요리 스트림 생성
     * Flux.generate 함수를 통해. 함수 기반 데이터를 발행할 수 있다.
     * sink.next로 다음 원소를 정의.
     * 다음 원소 딜레이는 250ms로 설정.
     */
    Flux<Dish> getDishes() {
        return Flux.<Dish> generate(sink -> sink.next(randomDish()))
            .delayElements(Duration.ofMillis(250));
    }






}
