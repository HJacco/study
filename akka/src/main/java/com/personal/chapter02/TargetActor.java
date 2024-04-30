package com.personal.chapter02;

import akka.actor.AbstractLoggingActor;
import akka.japi.pf.ReceiveBuilder;

public class TargetActor extends AbstractLoggingActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, this::process)
                .build();
    }

    public void process(String msg) {
        log().info("receive msg :" + msg);
    }
}
