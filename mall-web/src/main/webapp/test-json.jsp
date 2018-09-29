<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Json测试</title>
</head>
<body>
	<script src="http://code.jquery.com/jquery-1.4.1.js"></script>

	<!--
		1:
			alert($) 可以正常弹出
			alert(data) 不能够正常的弹出，出现跨域问题
			
			结论：script标签的src可以跨域请求资源，但是ajax请求不可以跨域请求。
			疑问：能否借助script标签的src进行加载数据？ -- 可以的。
	-->
	<%--<script type="text/javascript">
		alert($);
		$(function() {
			$.ajax({
				url : "http://mall.manage.com/json.jsp",
				type : "get",
				dataType : "json",
				success : function(data) {
					alert(data);
				}
			})
		})
	</script>--%>

	<!--
		2:
			发现： 请求资源可以正常请求，但是，报js解析出错。
			原因： Script标签加载到资源后，会将资源当做是js脚本解析，但是我们返回的是json数据，所以导致解析失败。
			解决：只需要返回js脚本即可。
			
	-->
	<%--<script type="text/javascript">
		alert($);
	</script> 
	<script type="text/javascript" src="http://manage.mall.com/json.jsp"></script>--%>

	<!--
		3:
			发现: 返回的js脚本成功解析，但是，fun没有定义。
			解决：定义个一个fun方法即可。
	-->
	<%--<script type="text/javascript">
		alert($);

        /*
        	3.1
				在客户端test-json.jsp中定义fun()函数解析返回数据
				在服务端json.jsp中也要将方法定义为fun()

				其他客户端调用json.jsp,方法可能不是用fun()函数解析

				因此将客户端中解析函数名称传递至服务端,实现服务端灵活定义方法名
        */
		function fun(data) {
			alert(data.abc)
		}
	</script>
	<script type="text/javascript" src="http://manage.mall.com/json.jsp"></script>--%>
	
	
	<!--
		4: jsonp优化
	-->
	<%--<script type="text/javascript">
		alert($);

		function fun(data){
			alert(data.abc)
		}
	</script>
	<!--4.1将客户端方法名传递至服务端-->
	<script type="text/javascript" src="http://manage.mall.com/json.jsp?callback=fun"></script>--%>
	
	 <!--
		5: 通过jQuery使用jsonp请求
	 -->
	 <script type="text/javascript">
	 	$(function() {
			$.ajax({
				url : "http://manage.mall.com/json.jsp",
				type : "get",
				dataType : "jsonp",
				success : function(data) {
					alert(data.abc);
				}
			})
		})
	</script>
	
	<!-- 测试跨域请求数据 -->
	<%--<script type="text/javascript">
	 	$(function() {
			$.ajax({
				url : "http://manage.mall.com/rest/api/item/cat",
				type : "get",
				dataType : "jsonp",
				success : function(data) {
					console.log(data);
				}
			})
		})
	</script> --%>
	
</body>
</html>