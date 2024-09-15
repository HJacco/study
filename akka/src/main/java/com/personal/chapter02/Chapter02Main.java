package com.personal.chapter02;

import akka.actor.*;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class Chapter02Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("chapter02");

        // 消息模式
        ActorRef actorDemoInstance = system.actorOf(Props.create(ActorDemo.class), "actorDemo");
        actorDemoInstance.tell("hello world", ActorRef.noSender());

        // ask 模式
        ActorRef askActorInstance = system.actorOf(Props.create(AskActorDemo.class), "askActor");
        Timeout timeout = new Timeout(Duration.create(2L, TimeUnit.SECONDS));
        Future<Object> future = Patterns.ask(askActorInstance, "hello, this is jacco", timeout);
        future.onComplete(new OnComplete<Object>() {
            @Override
            public void onComplete(Throwable failure, Object success) throws Throwable {
                if (null != failure) {
                    System.out.println(failure.toString());
                }
                else {
                    System.out.println(success);
                }
            }
        }, system.dispatcher());

        // 消息转发
        ActorRef forward = system.actorOf(Props.create(ForwardActor.class), "forwardActor");
        forward.tell("hello router", forward);
        forward.tell(PoisonPill.getInstance(), ActorRef.noSender());
//        forward.tell(Kill.getInstance(), ActorRef.noSender());
    }
}
