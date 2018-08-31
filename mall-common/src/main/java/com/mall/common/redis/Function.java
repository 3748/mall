package com.mall.common.redis;

/**
 * @author gp6
 * @date 2018/8/30
 */
public interface Function<T, E> {

    /**
     * RedisUtil中使用
     * @param e
     * @return T
     */
    T callback(E e);
}
