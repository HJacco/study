package com.personal.reactorjava;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Map<String, AtomicInteger> recorder = new ConcurrentHashMap<>();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                recorder.computeIfAbsent("a", s -> new AtomicInteger(0));
                for (int i = 1; i < 4; i ++) {
                    recorder.computeIfPresent("a", new BiFunction<String, AtomicInteger, AtomicInteger>() {
                        @Override
                        public AtomicInteger apply(String s, AtomicInteger atomicInteger) {
                            atomicInteger.incrementAndGet();
                            return atomicInteger;
                        }
                    });
                }
            }
        });
        t.start();
        t.join();
        System.out.println(recorder.get("a").get());
    }
}