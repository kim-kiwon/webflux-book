package com.greglturnquist.hackingspringboot.reactive.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

// SpringData 모듈에서 기본적인 CRUD를 추상화해서 제공한다.
// ReactiveCrudRepository는 연산들의 반환형이 Mono/Flux이다.
public interface ItemRepository extends ReactiveCrudRepository<Item, String> {

}
