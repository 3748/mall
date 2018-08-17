<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<!-- <script type="text/javascript">
		alert($);
		$(function() {
			$.ajax({
				url : "http://taomanage.com/json.jsp",
				type : "get",
				dataType : "json",
				success : function(data) {
					alert(data);
				}
			})
		})
	</script>  -->

	<!--
		2:
			发现： 请求资源可以正常请求，但是，报js解析出错。
			原因： Script标签加载到资源后，会将资源当做是js脚本解析，但是我们返回的是json数据，所以导致解析失败。
			解决：只需要返回js脚本即可。
			
	-->
	<!-- <script type="text/javascript">
		alert($);
	</script> 
	<script type="text/javascript" src="http://manage.mall.com/json.jsp"></script> -->

	<!--
		3:
			发现: 返回的js脚本成功解析，但是，fun没有定义。
			解决：定义个一个fun方法即可。
	-->
	<!-- <script type="text/javascript">
		alert($);

		function fun(data) {
			alert(data.abc)
		}
	</script>
	<script type="text/javascript" src="http://manage.mall.com/json.jsp"></script> -->
	
	
	<!--
		4:
			jsonp优化
	-->
	<!-- <script type="text/javascript">
		alert($);
	
		function fun(data){
			alert(data.abc)
		}
	</script> 
	<script type="text/javascript" src="http://manage.mall.com/json.jsp?callback=fun"></script> -->
	
	<!-- 
		5:
			通过jQuery使用jsonp请求
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
		
		function fun(data){
			alert(data.abc)
		}
	</script> 
	
</body>

</body>
</html>