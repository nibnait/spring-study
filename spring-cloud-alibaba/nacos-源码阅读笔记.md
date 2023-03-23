## 注册中心

### Spring Boot启动调用Nacos API
<https://www.processon.com/view/link/5f26acfae0b34d54dadaa68c>  
![](./docs/img/springboot-nacos.png)

### Nacos客户端向服务端发起服务注册
Nacos 2.x  
入口：[NacosNamingService#registerInstance()](./spring-cloud-alibaba-nacos-2.0.4/client/src/main/java/com/alibaba/nacos/client/naming/NacosNamingService.java#L143)  
<https://www.processon.com/view/link/63b8b98acd1c781ac5d8f273>

Nacos 1.x  
入口：NacosNamingService#registerInstance()  
<https://www.processon.com/view/link/63f670dfe4d0535d96d39d2f>


### Nacos服务端处理服务注册
Nacos 2.x    
入口：[InstanceRequestHandler#handle](./spring-cloud-alibaba-nacos-2.0.4/naming/src/main/java/com/alibaba/nacos/naming/remote/rpc/handler/InstanceRequestHandler.java#L48)  
<https://www.processon.com/view/link/63b8cb71cd1c781ac5d8f76f>

Nacos 1.x  
<https://www.processon.com/view/link/63f765af075f2179bc99ee34>

### 心跳机制与健康检查
Nacos 2.x  
<https://www.processon.com/view/link/63b8db2fcb1e204fc5f12976>

Nacos 1.x  
<https://www.processon.com/view/link/63f773b1e4d0535d96d5a294>

### 服务发现与订阅及通知客户端
Nacos 2.x  
UserController#instances->this#discoveryClient#getInstances(serviceId)>NacosNamingService#selectInstances(params)  
<https://www.processon.com/view/link/63b91cd402b9ad68cd921785>

Nacos 1.x 服务发现[简介]  
<https://www.processon.com/view/link/63f7aabd86258975be3765ad>


## 配置中心

### 发布配置
<https://www.processon.com/view/link/63c17fa5392a4b25feb611a5>

### 获取配置
<https://www.processon.com/view/link/63c2453ca7d181715d1055f4>

### Nacos添加监听与配置变更
<https://www.processon.com/view/link/63c29709279b425769b6f9a6>