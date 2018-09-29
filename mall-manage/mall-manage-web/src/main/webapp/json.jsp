<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // 1
    //out.print("{\"abc\":123}");

    // 3  返回js脚本
    //out.print("fun({\"abc\":123})");

    // 4 jsonp优化
    // 4.2 接收函数名
    String callback = request.getParameter("callback");
    if (null != callback) {
        out.print(callback + "({\"abc\":123})");
    } else {
        out.print("{\"abc\":123}");
    }
%>