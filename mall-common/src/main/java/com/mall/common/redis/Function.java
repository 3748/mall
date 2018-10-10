package com.mall.common.redis;

/**
 * @author gp6
 * @date 2018/8/30
 */
public interface Function<T, E> {

    /**
     * 参数类型和返回类型不确定,由泛型代替
     *
     * @param e 参数
     * @return T
     */
    T callback(E e);
}
