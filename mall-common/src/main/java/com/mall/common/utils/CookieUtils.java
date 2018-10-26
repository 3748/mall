package com.mall.common.utils;

import com.mall.common.enums.NumberEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie工具
 *
 * @author gp6
 * @date 2018/9/3
 */
public class CookieUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtils.class);

    /**
     * 得到Cookie的值, 不编码
     *
     * @param request    HttpServletRequest
     * @param cookieName cookie名称
     * @return String
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 得到Cookie的值
     *
     * @param request    HttpServletRequest
     * @param cookieName cookie名称
     * @param isDecoder  是否编码
     * @return String
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } else {
                        retValue = cookie.getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Cookie Decode Error.", e);
        }
        return retValue;
    }

    /**
     * 得到Cookie的值
     *
     * @param request    HttpServletRequest
     * @param cookieName cookie名称
     * @return String
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookie.getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Cookie Decode Error.", e);
        }
        return retValue;
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 设置Cookie的值 在指定时间内生效,但不编码
     * <p>
     * 将cookieMaxage时间增大,即可实现自动登录功能
     */
    private static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }

    /**
     * 设置Cookie的值 不设置生效时间,但编码(Cookie中有中文时,使用该方法)
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
    }

    /**
     * 删除Cookie带cookie域名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > NumberEnum.ZERO.getValue()) {
                cookie.setMaxAge(cookieMaxage);
            }
            if (null != request) {
                // 设置域名的cookie
                cookie.setDomain(getDomainName(request));
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            LOGGER.error("Cookie Encode Error.", e);
        }
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > NumberEnum.ZERO.getValue()) {
                cookie.setMaxAge(cookieMaxage);
            }
            if (null != request) {
                // 设置域名的cookie
                cookie.setDomain(getDomainName(request));
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            LOGGER.error("Cookie Encode Error.", e);
        }
    }

    /**
     * 得到cookie的域名
     */
    private static String getDomainName(HttpServletRequest request) {
        String domainName;

        // http://sso.mall.com/sso/login
        String serverName = request.getRequestURL().toString();
        if (StringUtils.isEmpty(serverName)) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(NumberEnum.SEVEN.getValue());
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(NumberEnum.ZERO.getValue(), end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > NumberEnum.THREE.getValue()) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len > NumberEnum.ONE.getValue()) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        String a = ":";
        if (domainName.indexOf(a) > NumberEnum.ZERO.getValue()) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }
}
