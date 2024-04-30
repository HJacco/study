package com.personal.chapter02;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class ForwardActor extends AbstractLoggingActor {

    private ActorRef target = getContext().actorOf(Props.create(TargetActor.class), "targetActor");

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, this::processForward)
                .build();
    }

    private void processForward(String msg) {
        log().info("forward msg:" + msg);
        target.forward(msg, getContext());
    }
}
