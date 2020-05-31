package com.atomscat.provider.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.atomscat.provider.mapper")
public class MybatisConfig {
}
