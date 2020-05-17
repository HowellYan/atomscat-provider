package com.atomscat.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableDubbo
@EnableAutoConfiguration
public class Bootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Bootstrap.class).
                web(WebApplicationType.NONE).
                run(args);
    }
}
