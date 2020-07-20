package com.atomscat.provider.config.elastic;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("'${elastic-job.servers}'.length() > 0")
public class JobRegistryCenterConfig {

    @NacosValue(value = "${elastic-job.servers}", autoRefreshed = true)
    private String servers;

    @NacosValue(value = "${elastic-job.namespace}", autoRefreshed = true)
    private String namespace;

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter() {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(servers, namespace));
    }

}