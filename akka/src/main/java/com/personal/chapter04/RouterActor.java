package com.personal.chapter04;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;

public class RouterActor extends AbstractLoggingActor {

    private Router router;

    @Override
    public void preStart() throws Exception {
        List<Routee> routeeList = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            ActorRef ref = getContext().actorOf(Props.create(RouteeActor.class), "routee_" + i);
            routeeList.add(new ActorRefRoutee(ref));
        }
        router = new Router(new RoundRobinRoutingLogic(), routeeList);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, this::routerStringMsg)
                .build();
    }

    public void routerStringMsg(String msg) {
        this.router.route(msg, getSender());
    }
}
