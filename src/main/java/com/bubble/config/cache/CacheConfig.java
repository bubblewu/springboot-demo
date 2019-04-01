package com.bubble.config.cache;

import com.alicp.jetcache.CacheBuilder;
import com.alicp.jetcache.anno.CacheConsts;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.embedded.CaffeineCacheBuilder;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.alicp.jetcache.support.FastjsonKeyConvertor;
import com.alicp.jetcache.support.JavaValueDecoder;
import com.alicp.jetcache.support.JavaValueEncoder;
import com.google.common.collect.Maps;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 配置JetCache二级缓存：本地Caffeine + 远程Redis
 * 注意：
 * - 同类中的方法直接互调缓存失效。
 * 因为Spring Cache的注解是采用Spring Aop来动态代理的，同个类中的调用无法生效。
 * - 实体类（xxxEntity）需要继承Serializable接口
 *
 * @author wugang
 * date: 2019-04-01 18:51
 **/
@Configuration
@EnableMethodCache(basePackages = "com.bubble")
@EnableCreateCacheAnnotation
public class CacheConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    private static final int DEFAULT_MAXSIZE = 100000;
    private static final int DEFAULT_TTL = 24;

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;

    private JedisPool jedisPool() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(50); // 最大空闲连接数, 默认8个
        poolConfig.setMinIdle(10); // 最小空闲连接数
        poolConfig.setMaxTotal(1000); // 最大连接数, 默认8个
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间, 默认-1
        poolConfig.setMaxWaitMillis(2000);
        poolConfig.setTestOnBorrow(false);  // 向连接池借用连接时是否做连接有效性检测(ping)，无效连接会被移除，每次借用多执行一次ping命令
        poolConfig.setTestOnReturn(true); // 还回线程池时进行有效性检查
        poolConfig.setBlockWhenExhausted(true); // 连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
        poolConfig.setLifo(true); // 是否启用后进先出, 默认true
        JedisPool jedisPool = new JedisPool(poolConfig, host, port, 10000);
        if ("PONG".equals(jedisPool.getResource().ping())) {
            LOGGER.info("connect redis [{}-{}] success.", host, port);
        } else {
            LOGGER.error("connect redis [{}-{}] failed.", host, port);
        }
        return jedisPool;
    }

    @Bean
    public Pool<Jedis> pool() {
        return jedisPool();
    }

    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider();
    }

    @Bean
    public GlobalCacheConfig config(SpringConfigProvider configProvider, Pool<Jedis> pool) {
        Map<String, CacheBuilder> localBuilders = Maps.newHashMapWithExpectedSize(1);
        CaffeineCacheBuilder caffeineCacheBuilder = CaffeineCacheBuilder.createCaffeineCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                .cacheNullValue(false)
                .expireAfterWrite(DEFAULT_TTL, TimeUnit.HOURS)
                .expireAfterAccess(0, TimeUnit.HOURS)
                .limit(DEFAULT_MAXSIZE);
        localBuilders.put(CacheConsts.DEFAULT_AREA, caffeineCacheBuilder);

        Map<String, CacheBuilder> remoteBuilders = Maps.newHashMapWithExpectedSize(1);
        RedisCacheBuilder redisCacheBuilder = RedisCacheBuilder.createRedisCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                .valueEncoder(JavaValueEncoder.INSTANCE)
                .valueDecoder(JavaValueDecoder.INSTANCE)
                .cacheNullValue(false)
                .expireAfterWrite(DEFAULT_TTL, TimeUnit.HOURS)
                .expireAfterAccess(0, TimeUnit.HOURS)
                .jedisPool(pool);
        remoteBuilders.put(CacheConsts.DEFAULT_AREA, redisCacheBuilder);

        GlobalCacheConfig globalCacheConfig = new GlobalCacheConfig();
        globalCacheConfig.setConfigProvider(configProvider);
        globalCacheConfig.setLocalCacheBuilders(localBuilders);
        globalCacheConfig.setRemoteCacheBuilders(remoteBuilders);
        globalCacheConfig.setStatIntervalMinutes(10);
        String[] hiddenPackages = {"com.bubble"};
        globalCacheConfig.setHiddenPackages(hiddenPackages);
        globalCacheConfig.setEnableMethodCache(true);
        return globalCacheConfig;
    }

}
