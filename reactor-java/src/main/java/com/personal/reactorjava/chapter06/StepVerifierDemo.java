package com.personal.reactorjava.chapter06;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.test.publisher.PublisherProbe;
import reactor.tools.agent.ReactorDebugAgent;

public class StepVerifierDemo {
    public static void main(String[] args) {

        // 激活调试模式，全局设置，影响性能
        Hooks.onOperatorDebug();

        // java代理，增强代码并添加调试信息，没有额外运行时的开销；
        ReactorDebugAgent.init();

        Flux<String> source = Flux.just("a", "b");

        // 创建定制化的StepVerifier
        StepVerifierOptions options = StepVerifierOptions.create().scenarioName("学习测试");
        // StepVerifier 逐步测试一个序列是否遵循给定的场景
        StepVerifier.create(appendBoomError(source), options)
                .expectNext("a")
                .as("expect first element is a")
                .expectNext("b")
                .expectError(IllegalArgumentException.class)
                .verify();

        Flux<String> source1 = Flux.just("a", "b");
        source1.subscribe(
                System.out::println,
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("done")
        );
        StepVerifierOptions options1 = StepVerifierOptions.create().scenarioName("学习测试1");

        // 创建探针，检查执行路径
        PublisherProbe<String> probe = PublisherProbe.of(source1);
        StepVerifier.create(probe.flux(), options1)
                .expectNext("a")
                .expectNext("b")
                .verifyComplete();
        probe.assertWasSubscribed();
        probe.assertWasNotCancelled();


    }

    static <T> Flux<T> appendBoomError(Flux<T> source) {
        return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
    }
}
