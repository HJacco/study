package com.personal.reactorjava.chapter04;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class ScheduleDemo {
    public static void main(String[] args) {
        // 立即执行，可以理解为没有创建Schedule线程
        Scheduler s1 = Schedulers.immediate();

        // 一个单一的可重用线程
        Scheduler s2 = Schedulers.single();

        // 每次调用会新建一个线程
        Scheduler s3 = Schedulers.newSingle("");

        // 有界弹性线程池
        Scheduler s4 = Schedulers.boundedElastic();

        // 固定worker池  默认是处理器核数
        Schedulers.newParallel("parallel");

        Flux<Integer> fluxLongSeq = Flux.range(1, 10)
                .map(i -> {
                    System.out.println(Thread.currentThread().getName() + " map i:" + i);
                    return i * 2;
                })
                // 此处开始将后续序列的处理转变成在S4线程池上执行，包括subscribe动作
                .publishOn(s4)
                .map(i -> {
                    System.out.println(Thread.currentThread().getName() + "new map i:" + i);
                    return i / 2;
                });
        fluxLongSeq.subscribe(i -> {
            System.out.println(Thread.currentThread().getName() + "subscribe i:" + i);
            System.out.println(i);
        });

    }
}
