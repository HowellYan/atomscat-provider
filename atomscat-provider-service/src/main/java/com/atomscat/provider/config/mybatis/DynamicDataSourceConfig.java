package com.atomscat.provider.config.mybatis;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class DynamicDataSourceConfig implements EnvironmentAware {

    private static final Object DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";

    private Binder binder;

    @Override
    public void setEnvironment(Environment environment) {
        binder = Binder.get(environment);
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(Map<Object, Object> dataSources) {
        return new DynamicDataSource(dataSources);
    }

    @Bean
    public Map<Object, Object> dataSources(){
        List<Map> configs = binder.bind("spring.datasources", Bindable.listOf(Map.class)).get();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        for (Map map : configs) {
            DataSource dataSource = buildDataSource(map);
            if (dataSource != null) {
                dataSourceMap.put(map.get("key").toString(), dataSource);
            }
        }
        return dataSourceMap;
    }

    private DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            Object type = dsMap.get("type");
            if (type == null) {
                // 默认DataSourceType
                type = DATASOURCE_TYPE_DEFAULT;
            }
            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driverClassName = dsMap.get("driver-class-name").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (Throwable e) {
            log.error("buildDataSource failed!",e);
        }
        return null;
    }


}
