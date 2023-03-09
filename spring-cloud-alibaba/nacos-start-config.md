# nacos 启动配置
## 单机版
> mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U

Maven ➕ pom.xml，刷新 maven ✅

---
Nacos系统参数介绍: <https://nacos.io/en-us/docs/system-configurations.html>

#### nacos-1.4.3 启动类 [Nacos.java](./spring-cloud-alibaba-nacos-1.4.3/console/src/main/java/com/alibaba/nacos/Nacos.java)
```properties
VM options:
-Dnacos.standalone=true -Dnacos.home=spring-cloud-alibaba/nacos-1.4.3-home
```

将数据源改成 MySQL：
[application.properties](./spring-cloud-alibaba-nacos-1.4.3/console/src/main/resources/application.properties)
```properties
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos1?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=root
db.password.0=root
```

```shell
mysql -uroot -proot
create database nacos1
执行建表语句 👇
```
[nacos-mysql.sql](./docs/nacos-mysql.sql)

启动：<http://127.0.0.1:8841/nacos>

#### nacos-2.0.4 启动类 [Nacos.java](./spring-cloud-alibaba-nacos-2.0.4/console/src/main/java/com/alibaba/nacos/Nacos.java)
```properties
VM options:
-Dnacos.standalone=true -Dnacos.home=spring-cloud-alibaba/nacos-2.0.4-home
```

将数据源改成 MySQL：
[application.properties](./spring-cloud-alibaba-nacos-2.0.4/console/src/main/resources/application.properties)
```properties
server.port=8842

spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos2?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=root
db.password.0=root
```
```shell
mysql -uroot -proot
create database nacos2
执行建表语句 👇
```
[nacos-mysql.sql](./docs/nacos-mysql.sql)

启动：<http://127.0.0.1:8848/nacos>