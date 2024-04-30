package com.study.guice;

import com.google.inject.Injector;
import com.study.guice.infra.Application;
import com.study.guice.service.PurchaseService;

public class Main {
    public static void main(String[] args) {
        Injector run = Application.run(Main.class);
        PurchaseService instance = run.getInstance(PurchaseService.class);
        System.out.println(instance.process(1L));
    }
}
