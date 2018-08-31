package com.mall.manage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 获取env.properties中的信息
 *
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class PropertiesService {

    @Value("${image_base_url}")
    public String imageBaseUrl;

    @Value("${upload_dir}")
    public String uploadDir;
}
