## nacos
各版本 源码 下载地址：<https://github.com/alibaba/nacos/tags>

> mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U

Maven ➕ pom.xml，刷新 maven ✅

---
Nacos系统参数介绍: <https://nacos.io/en-us/docs/system-configurations.html>

nacos-1.4.3 启动类 [Nacos.java](./spring-cloud-alibaba-nacos-1.4.3/console/src/main/java/com/alibaba/nacos/Nacos.java)  
VM options: -Dnacos.standalone=true -Dnacos.home=nacos-home-standalone

nacos-1.4.3 启动类 [Nacos.java](./spring-cloud-alibaba-nacos-2.0.4/console/src/main/java/com/alibaba/nacos/Nacos.java)  
VM options: -Dnacos.standalone=true -Dnacos.home=nacos-home-standalone
