package com.mall.common.threadlocal;


import com.mall.common.bean.User;

/**
 * 当前线程的user
 *
 * @author gp6
 * @date 2018-10-18
 */
public class UserThreadLocal {
    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(User user) {
        USER_THREAD_LOCAL.set(user);
    }

    public static User get() {
        return USER_THREAD_LOCAL.get();
    }

    public static void remove() {
        USER_THREAD_LOCAL.remove();
    }
}
