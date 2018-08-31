package com.mall.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 实体类工具
 *
 * @author gp6
 * @date 2018/8/31
 */
public class BeanUtil<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);

    public ResponseEntity<T> isNull(T t) {
        try {
            if (null == t) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(t);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
