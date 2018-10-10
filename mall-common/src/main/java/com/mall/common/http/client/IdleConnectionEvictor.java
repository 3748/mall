package com.mall.common.http.client;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时关闭无效连接
 *
 * @author gp6
 * @date 2018-08-21
 */
public class IdleConnectionEvictor extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdleConnectionEvictor.class);

    private final HttpClientConnectionManager connMgr;

    private volatile boolean shutdown;

    public IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
        // 启动当前线程
        this.start();
    }

    @Override
    public void run() {
        try {
            // 此处为死循环,5秒钟关闭一次
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    // 关闭失效的连接
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (InterruptedException e) {
            // 结束
            LOGGER.error("关闭无效连接失败" + e.getMessage());
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
