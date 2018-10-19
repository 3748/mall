<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String staticUrl = "http://static.mall.com";
	request.setAttribute("staticUrl", staticUrl);
%>
<link rel="stylesheet" type="text/css"
	href="${staticUrl}/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${staticUrl}/js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="${staticUrl}/css/mall.css" />
<script type="text/javascript"
	src="${staticUrl}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${staticUrl}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${staticUrl}/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${staticUrl}/js/common.js"></script>