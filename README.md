### 下载安装

- [git](https://git-scm.com/)
- [java8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 
- [gradle](https://gradle.org/) 
- [mongodb](https://www.mongodb.com/)
- [redis](http://redis.io/download)
- [rabbitmq](http://rabbitmq.io/download)
- [lombok](http://plugins.jetbrains.com/plugin/6317-lombok)

### 运行环境

| 服务 | 服务名 | 端口 |
|----|----|----|
| 数据库 | mongodb | 27017 |
| 缓存 | redis | 6379 |
| 消息队列 | rabbitmq | 5672 |

### 编译 & 启动
1. 导入数据 db\\mongodb\\*.json
2. 启动服务 

| 服务 | 简介 | 地址 |
|----|----|----|
| eureka | 注册中心 | http://localhost:8761 |
| authorization | 授权服务 | http://localhost:8000 |
| gateway | 网关 | http://localhost:8443 |
| producer | 服务提供者 | http://localhost:9001 |
| consumer | 服务消费者 | http://localhost:9002 |

### 网关说明

* `管理接口` http://localhost:8443/swagger-ui.html
* `获取令牌` POST android:admin@localhost:8000/oauth/token?scope=read&grant_type=password&username=admin&password=admin
* `调用服务` GET localhost:8443/test?name=123 `必须填入正确的Bearer Token`
