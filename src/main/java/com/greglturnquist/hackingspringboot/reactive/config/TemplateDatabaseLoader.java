package com.greglturnquist.hackingspringboot.reactive.config;

import com.greglturnquist.hackingspringboot.reactive.domain.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

@Configuration
public class TemplateDatabaseLoader {
    // CommandLineRunner는 어플리케이션 시작시점에 자동으로 실행됨.
    // 어플리케이션 시작시점에 논블로킹 코드 작성하면. Subscriber 가 Netty를 데드락에 빠뜨릴 수 있음.
    // MongoOperations 사용하면 동기로 사용하게 해준다.
    @Bean
    CommandLineRunner intialize(MongoOperations mongo) {
        return args -> {
            mongo.save(new Item("Alf alaram clock", 19.99));
            mongo.save(new Item("Smurf TV tray", 24.99));
        };
    }
}
