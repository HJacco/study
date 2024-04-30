package com.study.guice.infra;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.study.guice.annotation.Scan;
import com.study.guice.annotation.Service;
import com.study.guice.service.BillService;
import com.study.guice.service.PurchaseService;
import com.study.guice.service.impl.BillServiceImpl;
import com.study.guice.service.impl.PayService;
import com.study.guice.service.impl.PurchaseServiceIMpl;
import org.reflections.Reflections;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

public class Application {

    public static Injector run(Class<?> mainClass, String ... args) {
//        String[] packages = getScanPackages(mainClass);
//
//        final Set<Class<?>> clazzSet = new HashSet<>();
//        for (String pkg : packages) {
//            clazzSet.addAll(list(pkg));
//        }
//        if (CollectionUtils.isEmpty(clazzSet)) {
//            return Guice.createInjector(new AbstractModule() {
//                @Override
//                protected void configure() {
//                    super.configure();
//                }
//            });
//        }
//
//        return Guice.createInjector(new AbstractModule() {
//            @Override
//            protected void configure() {
//                for (Class<?> clz : clazzSet) {
//                    Class<?>[] interfaces = clz.getInterfaces();
//                    if (interfaces.length == 0) {
//
//                    } else {
//
//                    }
//                }
//            }
//        });
        return Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(BillService.class).to(BillServiceImpl.class).in(Scopes.SINGLETON);
                bind(PayService.class).in(Scopes.SINGLETON);
                bind(PurchaseService.class).to(PurchaseServiceIMpl.class).in(Scopes.SINGLETON);
            }
        });
    }

    private static String[] getScanPackages(Class<?> mainClass) {
        Scan annotation = mainClass.getAnnotation(Scan.class);
        if (null != annotation) {
            return annotation.packages();
        }
        return new String[]{mainClass.getPackage().getName()};
    }

    private static Set<Class<?>> list(String pkg) {
        Reflections reflections = new Reflections(pkg);
        return reflections.getTypesAnnotatedWith(Service.class);
    }
}
