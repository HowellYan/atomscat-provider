Data Id: atomscat-provider.yaml
Group: DEFAULT_GROUP
```
spring:
  application:
    name: atomscat-provider
  main:
    allow-bean-definition-overriding: true
  redis:
    host: 127.0.0.1
    password: admin123456
    database: 2
    port: 6379
  datasources:
    - key: atomscat-provider
      url: jdbc:mysql://127.0.0.1:3306/atomscat-provider?allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false
      username: root
      password: 123456
      type: com.alibaba.druid.pool.DruidDataSource
      driver-class-name: com.mysql.cj.jdbc.Driver
    - key: atomscat-customer
      url: jdbc:mysql://127.0.0.1:3306/atomscat-customer?allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false
      username: root
      password: 123456
      type: com.alibaba.druid.pool.DruidDataSource
      driver-class-name: com.mysql.cj.jdbc.Driver


elastic-job:
  namespace: atomscat_elastic_job

mybatis:
  mapper-locations: classpath:/mapper/*Mapper.xml
  type-aliases-package: com.atomscat.provider.entity
  type-handlers-package: com.atomscat.provider.entity

```

Data Id: elastic-job
Group: servers
```
elastic-job:
  servers: 192.168.1.12:2181
```


Data Id: dubbo
Group: provider
```
dubbo:
  registry:
    address: nacos://192.168.1.14:8848
    parameters:
      namespace: 5f5afe75-b943-40dc-ae5a-6f3e9ab24cf8
  consumer:
    check: false
  protocol:
    port: -1
    name: dubbo
```

Data Id: dubbo
Group: consumer
```
dubbo:
  registry:
    address: nacos://192.168.1.14:8848
    parameters:
      namespace: 5f5afe75-b943-40dc-ae5a-6f3e9ab24cf8
    check: false
  consumer:
    check: false
```

```
-Dspring.profiles.active=dev -Xms64m -Xmx256m -Xmn256m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=64m
```