package com.mall.manage.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.constant.Constants;
import com.mall.manage.service.PropertieService;
import com.mall.manage.vo.UploadImgResult;

/**
 * 图片上传
 * 
 * @author gp6
 * @date 2018-07-10
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private PropertieService propertieService;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@RequestMapping(value = "/img", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImg(@RequestParam("imageFile") MultipartFile imageFile, HttpServletResponse response)
			throws Exception {
		// 校验图片格式
		boolean isLegal = false;
		for (String type : Constants.IMAGE_TYPE) {
			if (StringUtils.endsWithIgnoreCase(imageFile.getOriginalFilename(), type)) {
				isLegal = true;
				break;
			}
		}

		// 封装Result对象，并且将文件的byte数组放置到result对象中
		UploadImgResult uploadImgResult = new UploadImgResult();

		// 状态
		uploadImgResult.setError(isLegal ? 0 : 1);

		// 文件新路径
		String filePath = getFilePath(imageFile.getOriginalFilename());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Pic file upload .[{}] to [{}] .", imageFile.getOriginalFilename(), filePath);
		}

		// 生成图片的绝对引用地址
		String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath, propertieService.uploadDir), "\\",
				"/");
		uploadImgResult.setUrl(propertieService.imageBaseUrl + picUrl);

		File newFile = new File(filePath);

		// 写文件到磁盘
		imageFile.transferTo(newFile);

		// 校验图片是否合法
		isLegal = false;
		try {
			BufferedImage image = ImageIO.read(newFile);
			if (image != null) {
				uploadImgResult.setWidth(image.getWidth() + "");
				uploadImgResult.setHeight(image.getHeight() + "");
				isLegal = true;
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		// 状态
		uploadImgResult.setError(isLegal ? 0 : 1);

		if (!isLegal) {
			// 不合法，将磁盘上的文件删除
			newFile.delete();
		}

		response.setContentType(MediaType.TEXT_HTML_VALUE);
		return MAPPER.writeValueAsString(uploadImgResult);
	}

	private String getFilePath(String sourceFileName) {
		String baseFolder = propertieService.uploadDir + File.separator + "images";
		Date nowDate = new Date();
		// yyyy/MM/dd
		String fileFolder = baseFolder + File.separator + new DateTime(nowDate).toString("yyyy") + File.separator
				+ new DateTime(nowDate).toString("MM") + File.separator + new DateTime(nowDate).toString("dd");
		File file = new File(fileFolder);
		if (!file.isDirectory()) {
			// 如果目录不存在，则创建目录
			file.mkdirs();
		}
		// 生成新的文件名
		String fileName = new DateTime(nowDate).toString("yyyyMMddhhmmssSSSS") + RandomUtils.nextInt(100, 9999) + "."
				+ StringUtils.substringAfterLast(sourceFileName, ".");
		return fileFolder + File.separator + fileName;
	}
}
