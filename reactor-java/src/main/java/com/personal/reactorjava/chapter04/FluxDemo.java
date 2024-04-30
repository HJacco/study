package com.personal.reactorjava.chapter04;

import reactor.core.Disposables;
import reactor.core.publisher.Flux;

import java.util.Arrays;

public class FluxDemo {
    public static void main(String[] args) {
        // 从一个数组创建一个flux
        Flux<String> fluxFromArray = Flux.just("foo", "bar", "foobar");

        // 从列表创建一个flux
        Flux<String> fluxFromList = Flux.fromIterable(Arrays.asList("foo", "bar", "foobar"));

        Flux<Integer> fluxFromRange = Flux.range(1, 10);

        // 订阅数据流
        fluxFromRange.subscribe(System.out::println);

        // 异常处理，异常信号和完成信号都是终止事件， 当数据流发生错误，是会直接终止后续流的发射
        fluxFromRange.map(i -> {
            if (i % 7 == 0) {
                throw new IllegalArgumentException("illegal element :" + i);
            }
            return i;
        }).subscribe(System.out::println, err -> System.out.println(err.getMessage()));

        // 异常监听和完成监听只会有一个运行
        fluxFromRange.map(i -> {
            if (i % 7 == 0) {
                throw new IllegalArgumentException("illegal element :" + i);
            }
            return i;
        }).subscribe(System.out::println, err -> System.out.println(err.getMessage()), () -> System.out.println("done!"));


        // 以编程的方式创建Flux，适合，每轮发出一次元素
        Flux<String> fluxFromGenerate = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + (3 * state));
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                }
        );

        Flux<String> fluxFromGenerate2 = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + (3 * state));
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                },
                // 一般用来最后做清理工作
                (state) -> System.out.println("flux create success,  state=" + state)
        );
        fluxFromGenerate2.subscribe(System.out::println);

        // 基于create方法创建Flux， 适合每轮多次发出，可以桥接api

        // 基于push 方法创建，类似Create， 适合单个生产者的事件

        // 基于handle, handle会链接到一个现有的源上，存在于mono和flux中，有点类似Map算子
    }
}
