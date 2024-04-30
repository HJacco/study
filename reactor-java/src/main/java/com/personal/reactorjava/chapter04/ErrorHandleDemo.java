package com.personal.reactorjava.chapter04;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

public class ErrorHandleDemo {
    public static void main(String[] args) {
        // 响应式序列中的任何错误都是一个终止事件

        // 1、subscribe中定义error处理
        Flux.range(1, 5)
                .map(i -> {
                    if (i == 5) {
                        throw new IllegalArgumentException("error");
                    }
                    return i;
                })
                .map(i -> i * 2)
                .subscribe(
                        System.out::println,
                        err -> System.out.println(err.getMessage())
                );

        System.out.println("====================delimeter line ======================");
        // 2、基于onErrorReturn 算子
        Flux.range(1, 5)
                .map(i -> {
                    if (i == 4) {
                        throw new IllegalArgumentException("error2");
                    }
                    return i;
                })
                .map(i -> i * 2)
                // 类似降级操作， 只能返回一个值
                .onErrorReturn(1000)
                .subscribe(System.out::println);

        System.out.println("=====================delimeter line2==========================");
        // 3、基于onErrorResume 算子，类似降级，会执行降级方法
        Flux.range(1, 10)
                .map(i -> {
                    if (i == 2) {
                        throw new IllegalArgumentException("error3");
                    }
                    return i;
                })
                // 降级操作，当原始的流发生错误，这里可以转变成一个新的流
                .onErrorResume(error -> Flux.range(6,4))
                .map(i -> i * 2)
                .subscribe(System.out::println);

        // 如果要将捕获到的异常转换成另一种异常有两种方式：1、onErrorResume算子，2、onErrorMap算子
        Flux.range(1, 10)
                .map(i -> {
                    if (i == 2) {
                        throw new IllegalArgumentException("error3");
                    }
                    return i;
                })
                // 降级操作，当原始的流发生错误，这里可以转变成一个新的流
                .onErrorResume(error -> Flux.error(new RuntimeException("resume map error")))
                .map(i -> i * 2)
                .subscribe(System.out::println);

        Flux.range(1, 10)
                .map(i -> {
                    if (i == 2) {
                        throw new IllegalArgumentException("error3");
                    }
                    return i;
                })
                // 降级操作，当原始的流发生错误，这里可以转变成一个新的流
                .onErrorMap(err -> new RuntimeException("onErrorMap"))
                .map(i -> i * 2)
                .subscribe(System.out::println);

        // 如果想捕获异常，但是不修改异常序列，可以使用doOnError算子
        Flux.range(1, 10)
                .map(i -> {
                    if (i == 2) {
                        throw new IllegalArgumentException("error3");
                    }
                    return i;
                })
                // 降级操作，当原始的流发生错误，这里可以转变成一个新的流
                .doOnError(error -> System.out.println(error.getMessage()))
                .map(i -> i * 2)
                .subscribe(System.out::println, error -> System.out.println("subscribe error:" + error.getMessage()));

        // doFinally 相当于try finally语义

        // try with resource语义的实现：Flux.using
        Disposable disposableInst = new Disposable() {
            @Override
            public void dispose() {
                this.isDisposed();
            }

            @Override
            public String toString() {
                return "Disposed";
            }
        };
        Flux.using(
                // 生成一个resource
                () -> disposableInst,
                // 对resource的处理
                disposable -> Flux.just(disposable.toString()),
                // 当resource处理结束时，执行下面的语句
                Disposable::dispose
        );
        // retry算子，当异常时，会重新订阅原来的算子
    }
}
