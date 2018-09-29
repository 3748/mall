package com.mall.manage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.enums.ImageTypeEnum;
import com.mall.common.utils.DateTimeUtil;
import com.mall.common.vo.UploadImgVo;
import com.mall.manage.service.PropertiesService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    private PropertiesService propertiesService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * produces: 指定相应类型
     *
     * @param imageFile 图片文件
     * @return 文本类型的json数据
     * @throws Exception 异常
     */
    @RequestMapping(value = "/img", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String uploadImg(@RequestParam("imageFile") MultipartFile imageFile) throws Exception {

        // 校验图片格式
        boolean isLegal = false;
        for (ImageTypeEnum type : ImageTypeEnum.values()) {
            if (StringUtils.endsWithIgnoreCase(imageFile.getOriginalFilename(), type.getValue())) {
                isLegal = true;
                break;
            }
        }

        // 封装Result对象，并且将文件的byte数组放置到result对象中
        UploadImgVo uploadImgResult = new UploadImgVo();

        // 状态
        uploadImgResult.setError(isLegal ? 0 : 1);

        // 文件新路径
        String filePath = getFilePath(imageFile.getOriginalFilename());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Pic file upload .[{}] to [{}] .", imageFile.getOriginalFilename(), filePath);
        }

        // 生成图片的绝对引用地址
        String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath, propertiesService.uploadDir), "\\", "/");
        uploadImgResult.setUrl(propertiesService.imageBaseUrl + picUrl);

        File newFile = new File(filePath);

        // 写文件到磁盘
        imageFile.transferTo(newFile);

        // 校验图片是否合法,图片是否有宽和高
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

        // 将一个java对象序列化成json字符串
        return MAPPER.writeValueAsString(uploadImgResult);
    }

    /**
     * 生成文件路径
     *
     * @param sourceFileName 上传的文件名称
     * @return 文件路径
     */
    private String getFilePath(String sourceFileName) {
        String baseFolder = propertiesService.uploadDir + File.separator + "images";
        // 目录格式 yyyy/MM/dd
        String fileFolder = baseFolder + File.separator + DateTimeUtil.CURRENTYEAR + File.separator + DateTimeUtil.CURRENTMOUTH + File.separator + DateTimeUtil.CURRENTDATE;
        File file = new File(fileFolder);
        if (!file.isDirectory()) {
            // 如果目录不存在，则创建目录
            file.mkdirs();
        }
        // 生成新的文件名,当前时间+随机数
        String fileName = DateTimeUtil.CURRENTTIME.toString() + RandomUtils.nextInt(100, 9999) + "."
                + StringUtils.substringAfterLast(sourceFileName, ".");

        return fileFolder + File.separator + fileName;
    }
}
