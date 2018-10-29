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

    @Value("${MANAGE_IMAGE_BASE_URL}")
    public String manageImageBaseUrl;

    @Value("${MANAGE_UPLOAD_DIR}")
    public String manageUploadDir;
}
