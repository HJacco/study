package com.personal.chapter04;

import akka.actor.AbstractLoggingActor;
import akka.japi.pf.ReceiveBuilder;

public class RouteeActor extends AbstractLoggingActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, this::processStr)
                .build();
    }

    public void processStr(String msg) {
        log().info("routee receive msg:{}", msg);
    }
}
