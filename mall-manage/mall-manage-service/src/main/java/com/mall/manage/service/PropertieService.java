package com.mall.manage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertieService {

	@Value("${image_base_url}")
	public String IMAGE_BASE_URL;

	@Value("${upload_dir}")
	public String UPLOAD_DIR;
}
