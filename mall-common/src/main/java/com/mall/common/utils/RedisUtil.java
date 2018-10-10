package com.mall.common.utils;

import com.mall.common.redis.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Redis相关操作工具类
 *
 * @author gp6
 * @date 2018-08-22
 */
@Service
public class RedisUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * 如果Spring中有就注入,没有就忽略
     */
    @Autowired(required = false)
    private ShardedJedisPool shardedJedisPool;

    /**
     * T 不确定类型
     * 在类名中声明  RedisUtil<T>,全局生效
     * 也可以在方法中声明  <T>,只在指定方法中生效
     *
     * @param function 参数为Function<T, E>,此时E类型确定
     * @param <T>      泛型T
     * @return 返回值为T
     */
    private <T> T execute(Function<T, ShardedJedis> function) {
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();
        } catch (Exception e) {
            LOGGER.error("从连接池中获取到jedis分片对象失败" + e.getMessage());
        } finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }

        // 返回泛型
        return function.callback(shardedJedis);
    }

    /**
     * 执行set操作
     *
     * @param key   Redis中的key
     * @param value Redis中的key
     * @return String
     */
    public String set(final String key, final String value) {
        /*
        return this.execute(new Function<String, ShardedJedis>() {
            @Override
            public String callback(ShardedJedis e) {
                return e.set(key, value);
            }
        });
        */
        // 使用Lambda表达式代替
        return this.execute(e -> e.set(key, value));
    }

    /**
     * 执行get操作
     *
     * @param key Redis中的key
     * @return String
     */
    public String get(final String key) {
        return this.execute(e -> e.get(key));
    }

    /**
     * 执行删除操作
     *
     * @param key Redis中的key
     * @return String
     */
    public Long del(final String key) {
        return this.execute(e -> e.del(key));
    }

    /**
     * 设置生存时间，单位为：秒
     *
     * @param key Redis中的key
     * @return String
     */
    public Long expire(final String key, final Integer seconds) {
        return this.execute(e -> e.expire(key, seconds));
    }

    /**
     * 执行set操作并且设置生存时间，单位为：秒
     *
     * @param key     Redis中的key
     * @param value   Redis中的key
     * @param seconds 有效时间
     * @return String
     */
    public String set(final String key, final String value, final Integer seconds) {
        return this.execute(e -> {
            String str = e.set(key, value);
            e.expire(key, seconds);
            return str;
        });
    }

}
