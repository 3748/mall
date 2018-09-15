商城(mall)

	简介:
		1: Mall商城是一个综合性的B2C平台
		2: 会员可以在商城浏览商品并下单,参加各种活动
		3: 管理员和运营可以在平台后台管理系统中管理商品,订单和会员
		4: 客服可以在后台管理系统中处理用户咨询和投诉

	用到的技术:
		Git
		Spring+SpringMVC+Mybatis
		Maven
		Mysql
		Redis
		RabbitMQ 
		DUBBO
		Lucene
		Solr
		HttpClient
		Nginx
		Quartz

	系统说明:
		后台管理系统(mall-manage):
			管理商品,订单,类目,用户等

		前台系统(mall-web):
			用户可以在前台系统中进行登录,注册浏览商品,下单等操作

		会员系统():


		订单系统(mall-order):
			提供下单,查询订单,修改订单,定时处理订单等功能

		搜索系统():
			提供商品搜索功能
			
		单点登录系统(mall-soo):
			为多个系统之间提供用户登录凭证以及查询登陆用户信息