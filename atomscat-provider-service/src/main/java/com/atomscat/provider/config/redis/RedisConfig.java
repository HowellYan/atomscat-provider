//package com.atomscat.provider.config.redis;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisSentinelPool;
//
//import java.util.HashSet;
//
///**
// * @author Howell.Yang
// */
//@Configuration
//public class RedisConfig {
//
//    @Autowired
//    private JedisConnectionFactory jedisConnectionFactory;
//
//    @Autowired
//    private RedisProperties redisProperties;
//
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
//
//    /**
//     * 非哨兵模式
//     *
//     * @return
//     */
//    @Bean
//    public JedisPool jedisPool() {
//        if (redisProperties.getSentinel() != null && redisProperties.getSentinel().getNodes().size() > 0) {
//            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//            JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(jedisConnectionFactory.getSentinelConfiguration().getMaster().getName(),
//                    new HashSet<>(redisProperties.getSentinel().getNodes()),
//                    jedisConnectionFactory.getPoolConfig(), jedisConnectionFactory.getTimeout(), jedisConnectionFactory.getPassword(), jedisConnectionFactory.getDatabase());
//            return new JedisPool(jedisPoolConfig,
//                    jedisSentinelPool.getCurrentHostMaster().getHost(), jedisSentinelPool.getCurrentHostMaster().getPort(),
//                    jedisConnectionFactory.getTimeout(), jedisConnectionFactory.getPassword());
//        } else {
//            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//            jedisPoolConfig.setMaxTotal(1000);
//            return new JedisPool(jedisPoolConfig,
//                    jedisConnectionFactory.getHostName(), jedisConnectionFactory.getPort(),
//                    jedisConnectionFactory.getTimeout(), jedisConnectionFactory.getPassword());
//        }
//    }
//
//    /**
//     * 哨兵模式
//     *
//     * @return
//     */
//    @Bean
//    public JedisSentinelPool jedisSentinelPool() {
//        if (redisProperties.getSentinel() != null && redisProperties.getSentinel().getNodes().size() > 0) {
//            JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(jedisConnectionFactory.getSentinelConfiguration().getMaster().getName(),
//                    new HashSet<>(redisProperties.getSentinel().getNodes()),
//                    jedisConnectionFactory.getPoolConfig(), jedisConnectionFactory.getTimeout(), jedisConnectionFactory.getPassword(), jedisConnectionFactory.getDatabase());
//            return jedisSentinelPool;
//        } else {
//            return null;
//        }
//    }
//
//
//}