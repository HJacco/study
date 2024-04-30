package com.personal.chapter02;

import akka.actor.AbstractLoggingActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

public class ActorDemo extends AbstractLoggingActor {

    private LoggingAdapter log = Logging.getLogger(this.getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, this::onReceiveStringMsg)
                .build();
    }

    public void onReceiveStringMsg(String msg) {
        log.info("接收到:{}", msg);
    }
}
