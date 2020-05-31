package com.atomscat.provider.config.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}
