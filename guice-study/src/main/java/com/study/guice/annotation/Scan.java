package com.study.guice.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scan {
    /**
     * 要扫描的包集合
     */
    String[] packages() default {""};
}
