package com.atomscat.provider.config.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public DynamicDataSource(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}
