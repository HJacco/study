package com.personal.chapter02;

import akka.actor.AbstractLoggingActor;
import akka.japi.pf.ReceiveBuilder;

public class AskActorDemo extends AbstractLoggingActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, this::onStringMsg)
                .build();
    }

    public void onStringMsg(String msg) throws InterruptedException {
        log().info("receive timeout msg:{}", msg);
        getSender().tell("hello, I have received msg:" + msg, getSender());
    }
}
