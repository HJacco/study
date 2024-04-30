package com.personal.reactorjava.chapter04;

import reactor.core.publisher.Mono;

public class MonoDemo {
    public static void main(String[] args) {
        // 创建一个Mono
        Mono<String> emptyMono = Mono.empty();

        // 创建只有一个元素的mono
        Mono<String> oneMono = Mono.just("foo");
    }
}
