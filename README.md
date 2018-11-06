## 商城(mall)

#### 项目启动:
	1: 首先启动	mall-manage
	2: mall-manage启动后,进行商品更新或者新增操作,会在MQ中声明一个交换机
	3: 启动	mall-web(必须先启动dubbo服务----sso-query) 和	mall-search,会自动声明两个队列 , mall-web-item-queue 和 mall-search-item-queue
	4: 将队列手工绑定到交换机中,绑定规则:
			To-queue : mall-web-item-queue
			Routing key : item_update

			To-queue : mall-web-item-queue
			Routing key : item_delete


			To-queue : mall-search-item-queue
			Routing key : item_insert

			To-queue : mall-search-item-queue
			Routing key : item_update

			To-queue : mall-search-item-queue
			Routing key : item_delete




#### 简介:
	1: Mall商城是一个综合性的B2C平台
	2: 会员可以在商城浏览商品并下单,参加各种活动
	3: 管理员和运营可以在平台后台管理系统中管理商品,订单和会员
	4: 客服可以在后台管理系统中处理用户咨询和投诉

#### 使用技术:
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

#### 系统说明:
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




